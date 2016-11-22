package com.myproject.activity;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.example.ard_myproject.R;
import com.myproject.util.DistanceUtil;

public class ZouYiZou_Activity extends Activity {

	MapView mMapView = null;  
	
	//显示商户地址
//	String address;
	String city;
	String interest;

	@Bind(R.id.edit_find)
	EditText edCity;
	@Bind(R.id.btn_find_choice)
	Button choice;
	@Bind(R.id.btn_find_search)
	Button search;
	
	BaiduMap mbaiduMap;
	
	//百度定位
	LocationClient mLocationClient;
	BDLocationListener myListener;
	public LatLng myLocation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//在使用SDK各组件之前初始化context信息，传入ApplicationContext  
        //注意该方法要再setContentView方法之前实现  
        SDKInitializer.initialize(getApplicationContext());
        
		setContentView(R.layout.activity_zou_yi_zou);
		
		ButterKnife.bind(this);

		//获取地图控件引用  
        mMapView = (MapView) findViewById(R.id.bmapView);  
        
        //定位及覆盖物
        mbaiduMap=mMapView.getMap();
        mbaiduMap.setMaxAndMinZoomLevel(20, 16);//最初缩放比例尺
        mbaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {//给所有的覆盖物添加信息
			@Override
			public boolean onMarkerClick(Marker marker) {
				Bundle bundle = marker.getExtraInfo();
				if (bundle==null) {
					return false;//点击自己的时候不做处理
				}
				
				String name=bundle.getString("name");
				String address=bundle.getString("address");
				String phone=bundle.getString("phone");
				double lat=bundle.getDouble("lat");
				double lng=bundle.getDouble("lng");
				
				LinearLayout view=(LinearLayout) getLayoutInflater().inflate(R.layout.map_bundle, null);
				
				TextView tvname=(TextView) view.findViewById(R.id.infor_name);
				TextView tvaddress=(TextView) view.findViewById(R.id.infor_address);
				TextView tvphone=(TextView) view.findViewById(R.id.infor_phone);
				TextView tvjuli=(TextView) view.findViewById(R.id.infor_juli);
				tvname.setText(name);
				tvaddress.setText(address);
				tvphone.setText(phone);
				
				//计算（lat, lng）与自己之间的距离
				double distance=DistanceUtil.getDistance(myLocation, new LatLng(lat, lng));
				tvjuli.setText("距离 "+distance+"m");
				
				InfoWindow infoWindow=new InfoWindow(view, new LatLng(lat, lng), -40);//数据显示的位置
				mbaiduMap.showInfoWindow(infoWindow);
				
				return true;
			}
		});
        
        showMyLocation();
        
	}
	
	
//	/**
//	 * 把传递过来的商户地址（address）显示在地图上
//	 */
//		private void showAddress(final String city) {
//			//1)把地址翻译成经纬度（lat, lng）
//			
//			//从地址---地理位置编码（GeoCoder）--->经纬度
//			//从经纬度---地理位置解码（反向编码）（ReverseGeoCoder）--->地址
//			GeoCoder geoCoder=GeoCoder.newInstance();
//			GeoCodeOption option=new GeoCodeOption();
//			
//			//异步创建监听器
//			geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
//				@Override
//				public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) { //返回的结果
//					//ReverseGeoCoder反向编码回调该方法
//				}
//				@Override
//				public void onGetGeoCodeResult(GeoCodeResult arg0) { //返回的结果
//					//GeoCoder地理位置编码回调该方法
//					if (arg0==null || arg0.error!=SearchResult.ERRORNO.NO_ERROR) {
//						Toast.makeText(ZouYiZou_Activity.this, "地址不详", 0).show();
//						return;
//					}
//					
//					/**--2)要根据得到的经纬度（lat, lng），创建覆盖物
//					  		然后把 "覆盖物" 添加到地图上显示-*/
//					LatLng latLng = arg0.getLocation();//封装好了latLng.latitude(纬度)，latLng.longitude（经度）
//					
////					OverlayOptions overlay;//Google的
//					MarkerOptions overlay =new MarkerOptions();
//					//overlay的经纬度
//					overlay.position(latLng);
//					
//					//overlay的图标
//					overlay.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding));//位置小图标
//					
//					mbaiduMap.addOverlay(overlay); //创建覆盖物
//					
//					//移动屏幕显示的中心点
//					MapStatusUpdate msu=MapStatusUpdateFactory.newLatLng(latLng);//创建
//					mbaiduMap.animateMapStatus(msu);//移动
//					
//					/**
//					 * 信息窗
//					 * View arg0：信息窗显示内容视图
//					 * LatLng arg1 ：信息窗在百度地图上显示的位置
//					 * int arg2：向上偏移负数，向下偏移正数--纯数字一般都指的是px，信息窗在百度地图上显示时的偏移位置
//					 */
//					TextView view =new TextView(ZouYiZou_Activity.this);
////					view.setText(address);
//					view.setText(city);
//					view.setBackgroundColor(Color.LTGRAY);//浅灰
//					
//					view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);//参数一是单位
//					
//					view.setTextColor(Color.BLUE);
//					int padding=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
//					view.setPadding(padding, padding, padding, padding);
//					
//					InfoWindow infor =new InfoWindow(view, latLng, -50);//参数三是图标与文字的间距
//					mbaiduMap.showInfoWindow(infor);
//				}
//			});
//			option.city(city);//
////			option.address(address);
//			geoCoder.geocode(option);//需要异步查询
//		}
	
	/**
	 * 发起定位并显示在地图
	 */
	private void showMyLocation() {
		mLocationClient=new LocationClient(this);//声明LocationClient类
		myListener=new MyLocationListener();
		mLocationClient.registerLocationListener(myListener);//注册监听函数
		
		initLocation();
	}
	
	//LocationClientOption类，该类用来设置定位SDK的定位方式
		private void initLocation() {
			LocationClientOption option = new LocationClientOption();
	        
			option.setLocationMode(LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
	        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
	        int span=1000*60*5;//五分鐘
	        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
	        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
	        option.setOpenGps(true);//可选，默认false,设置是否使用gps
	        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
	        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
	        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
	        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死  
	        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
	        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
	       
	        mLocationClient.setLocOption(option);
	        mLocationClient.start();//start：启动定位SDK。 stop：关闭定位SDK。调用start之后只需要等待定位结果自动回调即可(接口中)。
		}	

		//定位的監聽器
		public class MyLocationListener implements BDLocationListener {
			@Override
			public void onReceiveLocation(BDLocation location) { //返回的結果
				int code=location.getLocType();
				double lat=0;
				double lng=0;
				
				if (code==61 || code==66 || code==161) {
					//定位成功
					lat=location.getLatitude();
					lng=location.getLongitude();
					
				}else {
					//定位“失败”，返回-1
					//自带模拟器会失败---手动指定经纬度--潘家园
					lat=39.876457;
					lng=116.464899;
				}
//				Log.d("TAG", "定位结果："+lat+"/"+lng);
				
				/*****--定位成功后停止发送请求--***/
				if (mLocationClient.isStarted()) {
					mLocationClient.stop();
					mLocationClient.unRegisterLocationListener(myListener);
				}
				myLocation=new LatLng(lat, lng);
				
				//定位结果显示在屏幕上（添加覆盖物-换个图标）
				MarkerOptions overlay =new MarkerOptions();
				//overlay的经纬度
				overlay.position(myLocation);
				
				//overlay的图标
				overlay.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_card_blue));//位置小图标
				
				mbaiduMap.addOverlay(overlay); //创建覆盖物
				
				//移动屏幕中心点
				MapStatusUpdate msu=MapStatusUpdateFactory.newLatLng(myLocation);//创建
				mbaiduMap.animateMapStatus(msu);//移动
				
				//添加inforWindows(“我在这”)
				TextView view =new TextView(ZouYiZou_Activity.this);
				view.setText("你在这");
				view.setBackgroundColor(Color.RED);//浅灰
				
				view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);//参数一是单位
				
				view.setTextColor(Color.WHITE);
				int padding=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 
						5, getResources().getDisplayMetrics());
				view.setPadding(padding, padding, padding, padding);
				
				InfoWindow infor =new InfoWindow(view, myLocation, -50);//参数三是图标与文字的间距
				mbaiduMap.showInfoWindow(infor);
			}
		}		

		@OnClick(R.id.btn_find_choice)
		public void choice(View v) {
			final String[] items = new String[]{ "普通地图","卫星地图"};////开启交通图   mBaiduMap.setTrafficEnabled(true);
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			
			builder.setTitle("请选择...");
			builder.setItems(items, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					String item = items[which];
					if (item.equals("普通地图")) {
//						edCity.setHint("请输入搜索城市");
						mbaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); 
						choice.setVisibility(View.INVISIBLE);
						search.setVisibility(View.VISIBLE);
						
					} else {
						edCity.setHint("请输入周边");
//						mbaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE); 
						mbaiduMap.setTrafficEnabled(true);
						choice.setVisibility(View.INVISIBLE);
						search.setVisibility(View.VISIBLE);
					} 
				}
			});
			builder.create().show();
		}
		/**
		 * 按钮，搜索附近点
		 * POI:兴趣点 Point of Interest
		 */
		@OnClick(R.id.btn_find_search)
		public void search(View v) {
			if (edCity.getHint().toString().equals("请输入周边")) {
				interest = edCity.getText().toString();
				//POISearch
				PoiSearch poiSearch= PoiSearch.newInstance();
				poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
					@Override
					public void onGetPoiResult(PoiResult arg0) {
						if (arg0.error!=SearchResult.ERRORNO.NO_ERROR || arg0==null) {
							Toast.makeText(ZouYiZou_Activity.this, "搜索有误", 0).show();
							return;
						}
						//清除原有"覆盖物"
						mbaiduMap.clear();//但我自己的位置也没了
						
						List<PoiInfo> pois=arg0.getAllPoi();
						for (PoiInfo poi : pois) {
							//							Log.i("TAG", "哪些:"+poi.name+"/"+poi.address);
							//添加覆盖物
							LatLng latLng=poi.location;
							
							MarkerOptions overlay=new MarkerOptions();
							overlay.position(latLng);
							overlay.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_select_point));//位置小图标
							
							Marker marker=(Marker) mbaiduMap.addOverlay(overlay);//创建覆盖物--(Overlay-原先Google)
							
							Bundle bundle=new Bundle();//TODO
							bundle.putString("name", poi.name);
							bundle.putString("address", poi.address);
							bundle.putString("phone", poi.phoneNum);
							bundle.putDouble("lat", latLng.latitude);
							bundle.putDouble("lng", latLng.longitude);
							
							marker.setExtraInfo(bundle);//先是取出了marker信息，在把数据放在bundle里，取数据在91行
						}
						//重新添加我自己的覆盖物
						MarkerOptions overlay=new MarkerOptions();
						overlay.position(myLocation);
						overlay.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_card_nav_blue));//位置小图标
						mbaiduMap.addOverlay(overlay);//创建覆盖物
					}
					@Override
					public void onGetPoiDetailResult(PoiDetailResult arg0) {
						// 此是详细的地址
					}
				});
				
				PoiNearbySearchOption option= new PoiNearbySearchOption();
				//中心点
				option.location(myLocation);//把其从局部变量--变为了成员变量
				option.radius(3000);//搜索半径
				option.keyword(interest);//关键词
				
				poiSearch.searchNearby(option);//结果在监听器里
		
				choice.setVisibility(View.VISIBLE);
				search.setVisibility(View.INVISIBLE);
//			} else {
//				city = edCity.getText().toString();
//				showAddress(city);
//				choice.setVisibility(View.VISIBLE);
//				search.setVisibility(View.INVISIBLE);
		}
	}
		
	@Override  
    protected void onDestroy() {  
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
     // 退出时销毁定位
        mLocationClient.stop();
     // 关闭定位图层
        mbaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();  
        mMapView = null;
        super.onDestroy();  
    }  
	
    @Override  
    protected void onResume() {  
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
        mMapView.onResume();  
        super.onResume();  
    }  
    
    @Override  
    protected void onPause() {  
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
        mMapView.onPause();  
        super.onPause();  
    }  

}

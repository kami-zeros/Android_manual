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
	
	//��ʾ�̻���ַ
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
	
	//�ٶȶ�λ
	LocationClient mLocationClient;
	BDLocationListener myListener;
	public LatLng myLocation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext  
        //ע��÷���Ҫ��setContentView����֮ǰʵ��  
        SDKInitializer.initialize(getApplicationContext());
        
		setContentView(R.layout.activity_zou_yi_zou);
		
		ButterKnife.bind(this);

		//��ȡ��ͼ�ؼ�����  
        mMapView = (MapView) findViewById(R.id.bmapView);  
        
        //��λ��������
        mbaiduMap=mMapView.getMap();
        mbaiduMap.setMaxAndMinZoomLevel(20, 16);//������ű�����
        mbaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {//�����еĸ����������Ϣ
			@Override
			public boolean onMarkerClick(Marker marker) {
				Bundle bundle = marker.getExtraInfo();
				if (bundle==null) {
					return false;//����Լ���ʱ��������
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
				
				//���㣨lat, lng�����Լ�֮��ľ���
				double distance=DistanceUtil.getDistance(myLocation, new LatLng(lat, lng));
				tvjuli.setText("���� "+distance+"m");
				
				InfoWindow infoWindow=new InfoWindow(view, new LatLng(lat, lng), -40);//������ʾ��λ��
				mbaiduMap.showInfoWindow(infoWindow);
				
				return true;
			}
		});
        
        showMyLocation();
        
	}
	
	
//	/**
//	 * �Ѵ��ݹ������̻���ַ��address����ʾ�ڵ�ͼ��
//	 */
//		private void showAddress(final String city) {
//			//1)�ѵ�ַ����ɾ�γ�ȣ�lat, lng��
//			
//			//�ӵ�ַ---����λ�ñ��루GeoCoder��--->��γ��
//			//�Ӿ�γ��---����λ�ý��루������룩��ReverseGeoCoder��--->��ַ
//			GeoCoder geoCoder=GeoCoder.newInstance();
//			GeoCodeOption option=new GeoCodeOption();
//			
//			//�첽����������
//			geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
//				@Override
//				public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) { //���صĽ��
//					//ReverseGeoCoder�������ص��÷���
//				}
//				@Override
//				public void onGetGeoCodeResult(GeoCodeResult arg0) { //���صĽ��
//					//GeoCoder����λ�ñ���ص��÷���
//					if (arg0==null || arg0.error!=SearchResult.ERRORNO.NO_ERROR) {
//						Toast.makeText(ZouYiZou_Activity.this, "��ַ����", 0).show();
//						return;
//					}
//					
//					/**--2)Ҫ���ݵõ��ľ�γ�ȣ�lat, lng��������������
//					  		Ȼ��� "������" ��ӵ���ͼ����ʾ-*/
//					LatLng latLng = arg0.getLocation();//��װ����latLng.latitude(γ��)��latLng.longitude�����ȣ�
//					
////					OverlayOptions overlay;//Google��
//					MarkerOptions overlay =new MarkerOptions();
//					//overlay�ľ�γ��
//					overlay.position(latLng);
//					
//					//overlay��ͼ��
//					overlay.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding));//λ��Сͼ��
//					
//					mbaiduMap.addOverlay(overlay); //����������
//					
//					//�ƶ���Ļ��ʾ�����ĵ�
//					MapStatusUpdate msu=MapStatusUpdateFactory.newLatLng(latLng);//����
//					mbaiduMap.animateMapStatus(msu);//�ƶ�
//					
//					/**
//					 * ��Ϣ��
//					 * View arg0����Ϣ����ʾ������ͼ
//					 * LatLng arg1 ����Ϣ���ڰٶȵ�ͼ����ʾ��λ��
//					 * int arg2������ƫ�Ƹ���������ƫ������--������һ�㶼ָ����px����Ϣ���ڰٶȵ�ͼ����ʾʱ��ƫ��λ��
//					 */
//					TextView view =new TextView(ZouYiZou_Activity.this);
////					view.setText(address);
//					view.setText(city);
//					view.setBackgroundColor(Color.LTGRAY);//ǳ��
//					
//					view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);//����һ�ǵ�λ
//					
//					view.setTextColor(Color.BLUE);
//					int padding=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
//					view.setPadding(padding, padding, padding, padding);
//					
//					InfoWindow infor =new InfoWindow(view, latLng, -50);//��������ͼ�������ֵļ��
//					mbaiduMap.showInfoWindow(infor);
//				}
//			});
//			option.city(city);//
////			option.address(address);
//			geoCoder.geocode(option);//��Ҫ�첽��ѯ
//		}
	
	/**
	 * ����λ����ʾ�ڵ�ͼ
	 */
	private void showMyLocation() {
		mLocationClient=new LocationClient(this);//����LocationClient��
		myListener=new MyLocationListener();
		mLocationClient.registerLocationListener(myListener);//ע���������
		
		initLocation();
	}
	
	//LocationClientOption�࣬�����������ö�λSDK�Ķ�λ��ʽ
		private void initLocation() {
			LocationClientOption option = new LocationClientOption();
	        
			option.setLocationMode(LocationMode.Hight_Accuracy);//��ѡ��Ĭ�ϸ߾��ȣ����ö�λģʽ���߾��ȣ��͹��ģ����豸
	        option.setCoorType("bd09ll");//��ѡ��Ĭ��gcj02�����÷��صĶ�λ�������ϵ
	        int span=1000*60*5;//����
	        option.setScanSpan(span);//��ѡ��Ĭ��0��������λһ�Σ����÷���λ����ļ����Ҫ���ڵ���1000ms������Ч��
	        option.setIsNeedAddress(true);//��ѡ�������Ƿ���Ҫ��ַ��Ϣ��Ĭ�ϲ���Ҫ
	        option.setOpenGps(true);//��ѡ��Ĭ��false,�����Ƿ�ʹ��gps
	        option.setLocationNotify(true);//��ѡ��Ĭ��false�������Ƿ�gps��Чʱ����1S1��Ƶ�����GPS���
	        option.setIsNeedLocationDescribe(true);//��ѡ��Ĭ��false�������Ƿ���Ҫλ�����廯�����������BDLocation.getLocationDescribe��õ�����������ڡ��ڱ����찲�Ÿ�����
	        option.setIsNeedLocationPoiList(true);//��ѡ��Ĭ��false�������Ƿ���ҪPOI�����������BDLocation.getPoiList��õ�
	        option.setIgnoreKillProcess(false);//��ѡ��Ĭ��true����λSDK�ڲ���һ��SERVICE�����ŵ��˶������̣������Ƿ���stop��ʱ��ɱ��������̣�Ĭ�ϲ�ɱ��  
	        option.SetIgnoreCacheException(false);//��ѡ��Ĭ��false�������Ƿ��ռ�CRASH��Ϣ��Ĭ���ռ�
	        option.setEnableSimulateGps(false);//��ѡ��Ĭ��false�������Ƿ���Ҫ����gps��������Ĭ����Ҫ
	       
	        mLocationClient.setLocOption(option);
	        mLocationClient.start();//start��������λSDK�� stop���رն�λSDK������start֮��ֻ��Ҫ�ȴ���λ����Զ��ص�����(�ӿ���)��
		}	

		//��λ�ıO ��
		public class MyLocationListener implements BDLocationListener {
			@Override
			public void onReceiveLocation(BDLocation location) { //���صĽY��
				int code=location.getLocType();
				double lat=0;
				double lng=0;
				
				if (code==61 || code==66 || code==161) {
					//��λ�ɹ�
					lat=location.getLatitude();
					lng=location.getLongitude();
					
				}else {
					//��λ��ʧ�ܡ�������-1
					//�Դ�ģ������ʧ��---�ֶ�ָ����γ��--�˼�԰
					lat=39.876457;
					lng=116.464899;
				}
//				Log.d("TAG", "��λ�����"+lat+"/"+lng);
				
				/*****--��λ�ɹ���ֹͣ��������--***/
				if (mLocationClient.isStarted()) {
					mLocationClient.stop();
					mLocationClient.unRegisterLocationListener(myListener);
				}
				myLocation=new LatLng(lat, lng);
				
				//��λ�����ʾ����Ļ�ϣ���Ӹ�����-����ͼ�꣩
				MarkerOptions overlay =new MarkerOptions();
				//overlay�ľ�γ��
				overlay.position(myLocation);
				
				//overlay��ͼ��
				overlay.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_card_blue));//λ��Сͼ��
				
				mbaiduMap.addOverlay(overlay); //����������
				
				//�ƶ���Ļ���ĵ�
				MapStatusUpdate msu=MapStatusUpdateFactory.newLatLng(myLocation);//����
				mbaiduMap.animateMapStatus(msu);//�ƶ�
				
				//���inforWindows(�������⡱)
				TextView view =new TextView(ZouYiZou_Activity.this);
				view.setText("������");
				view.setBackgroundColor(Color.RED);//ǳ��
				
				view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);//����һ�ǵ�λ
				
				view.setTextColor(Color.WHITE);
				int padding=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 
						5, getResources().getDisplayMetrics());
				view.setPadding(padding, padding, padding, padding);
				
				InfoWindow infor =new InfoWindow(view, myLocation, -50);//��������ͼ�������ֵļ��
				mbaiduMap.showInfoWindow(infor);
			}
		}		

		@OnClick(R.id.btn_find_choice)
		public void choice(View v) {
			final String[] items = new String[]{ "��ͨ��ͼ","���ǵ�ͼ"};////������ͨͼ   mBaiduMap.setTrafficEnabled(true);
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			
			builder.setTitle("��ѡ��...");
			builder.setItems(items, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					String item = items[which];
					if (item.equals("��ͨ��ͼ")) {
//						edCity.setHint("��������������");
						mbaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); 
						choice.setVisibility(View.INVISIBLE);
						search.setVisibility(View.VISIBLE);
						
					} else {
						edCity.setHint("�������ܱ�");
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
		 * ��ť������������
		 * POI:��Ȥ�� Point of Interest
		 */
		@OnClick(R.id.btn_find_search)
		public void search(View v) {
			if (edCity.getHint().toString().equals("�������ܱ�")) {
				interest = edCity.getText().toString();
				//POISearch
				PoiSearch poiSearch= PoiSearch.newInstance();
				poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
					@Override
					public void onGetPoiResult(PoiResult arg0) {
						if (arg0.error!=SearchResult.ERRORNO.NO_ERROR || arg0==null) {
							Toast.makeText(ZouYiZou_Activity.this, "��������", 0).show();
							return;
						}
						//���ԭ��"������"
						mbaiduMap.clear();//�����Լ���λ��Ҳû��
						
						List<PoiInfo> pois=arg0.getAllPoi();
						for (PoiInfo poi : pois) {
							//							Log.i("TAG", "��Щ:"+poi.name+"/"+poi.address);
							//��Ӹ�����
							LatLng latLng=poi.location;
							
							MarkerOptions overlay=new MarkerOptions();
							overlay.position(latLng);
							overlay.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_select_point));//λ��Сͼ��
							
							Marker marker=(Marker) mbaiduMap.addOverlay(overlay);//����������--(Overlay-ԭ��Google)
							
							Bundle bundle=new Bundle();//TODO
							bundle.putString("name", poi.name);
							bundle.putString("address", poi.address);
							bundle.putString("phone", poi.phoneNum);
							bundle.putDouble("lat", latLng.latitude);
							bundle.putDouble("lng", latLng.longitude);
							
							marker.setExtraInfo(bundle);//����ȡ����marker��Ϣ���ڰ����ݷ���bundle�ȡ������91��
						}
						//����������Լ��ĸ�����
						MarkerOptions overlay=new MarkerOptions();
						overlay.position(myLocation);
						overlay.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_card_nav_blue));//λ��Сͼ��
						mbaiduMap.addOverlay(overlay);//����������
					}
					@Override
					public void onGetPoiDetailResult(PoiDetailResult arg0) {
						// ������ϸ�ĵ�ַ
					}
				});
				
				PoiNearbySearchOption option= new PoiNearbySearchOption();
				//���ĵ�
				option.location(myLocation);//����Ӿֲ�����--��Ϊ�˳�Ա����
				option.radius(3000);//�����뾶
				option.keyword(interest);//�ؼ���
				
				poiSearch.searchNearby(option);//����ڼ�������
		
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
        //��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���  
     // �˳�ʱ���ٶ�λ
        mLocationClient.stop();
     // �رն�λͼ��
        mbaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();  
        mMapView = null;
        super.onDestroy();  
    }  
	
    @Override  
    protected void onResume() {  
        //��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onResume();  
        super.onResume();  
    }  
    
    @Override  
    protected void onPause() {  
        //��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onPause();  
        super.onPause();  
    }  

}

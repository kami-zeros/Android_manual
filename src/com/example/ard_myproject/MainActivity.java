package com.example.ard_myproject;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.CustomViewBehind;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.myproject.activity.MoreActivity;
import com.myproject.adapter.MyFragmentPagerAdapter;
import com.myproject.fragment.Fragment01;
import com.myproject.fragment.Fragment02;
import com.myproject.fragment.SlidingListFragment;
import com.myproject.fragment.SlidingListFragment.MyListener;
import com.myproject.util.DialogUtil;
import com.myproject.util.ImageUtil;
import com.myproject.util.TextCanvasUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends SlidingFragmentActivity implements MyListener{

	SlidingMenu sm;
	RelativeLayout backg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sm= getSlidingMenu();//此处拿出来是为了上面的广告条
		
		setActionBar();//设置ActionBar
		setViewPager();	//设置ViewPager
		initialFragment();	//初始化Fragment
		setPagerListener();//滑动监听
		setBottomListenter();//bottom监听
		initSlidingMenu(savedInstanceState);//初始化侧滑菜单栏
	}

	/***--设置--actionbar-包括右上角optionmenu-*/
	Bitmap bmp2;
	private void setActionBar(){
		ActionBar bar=getActionBar();
		
		bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_bar_bg));//R.drawable-->Drawable
		
		Resources res=this.getResources();    //this或许是mainactivity
		if (bmp2==null) {
			bmp2=BitmapFactory.decodeResource(res, R.drawable.icon_user_head);//资源-->Bitmap
		}
        Log.d("TAG", "actionbar:"+bmp2.toString());
		
        Bitmap roundPhoto=ImageUtil.bitmapFormat(bmp2);//此是调用方法绘制圆形头像,返回Bitmap
		BitmapDrawable backDraw=new BitmapDrawable(res, roundPhoto);//Bitmap-->Drawable
		bar.setLogo(backDraw);//logo属性，它不会被作为桌面图标，仅作为activity的导航图
//		bar.setIcon(backDraw);//同时设置了icon与logo属性时（闲的），logo会完全覆盖掉icon
//		bar.setLogo(R.drawable.ic_message);
		
		bar.setHomeButtonEnabled(true);//图片可以点击
		
		bar.setDisplayShowTitleEnabled(false);
		bar.setDisplayShowCustomEnabled(true);
		ImageView im=new ImageView(this);
		im.setImageResource(R.drawable.main_screen_logo);
		bar.setCustomView(im);
	}
	
	/***-接口-*/
	MyListener myListener;
	@Override
	public void send(String imgurl) {
		if (imgurl.isEmpty()) {
			bmp2=BitmapFactory.decodeResource(getResources(), R.drawable.icon_user_head);
			return;
		}
		bmp2=ImageLoader.getInstance().loadImageSync(imgurl);
		
		Log.d("TAG", "send:"+bmp2.toString());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		
		MenuItem item=menu.add(0, 100, 101, "获取正版");	
		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		Bitmap bmp3=TextCanvasUtil.textBitmap();//绘制自己的，返回BItmap
		BitmapDrawable backtxt=new BitmapDrawable(getResources(), bmp3);//Bitmap-->Drawable
		item.setIcon(backtxt);
		
//		Bitmap bmp4=BitmapFactory.decodeResource(getResources(), R.drawable.android);//资源-->Bitmap
//		Bitmap roundim=ImageUtil.bitmapFormat(bmp4);//绘制圆形，返回Bitmap
//		BitmapDrawable backim=new BitmapDrawable(getResources(), roundim);//Bitmap-->Drawable
//		item.setIcon(backim);
//		item.setIcon(R.drawable.imooc);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			break;
		case 100:
			Toast.makeText(this, "获取正版", 0).show();//TODO
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	/**********----上面的ViewPager布局-广告条---****************/
	private int currentPager;
	private ViewPager vpbanner;
	
	public void setViewPager(){
		vpbanner=(ViewPager) findViewById(R.id.bannerPagerId);
		PagerAdapter adapter=new InnerAdapter();
		vpbanner.setAdapter(adapter);
		
		currentPager=10000/2;//从中间页码开始
		vpbanner.setCurrentItem(currentPager);
		
		sm.addIgnoredView(vpbanner);//此处是禁止当viewpager滚动时，侧滑菜单的响应
//		sm.clearIgnoredViews();
		
		final Handler h=new Handler();
		h.postDelayed(new Runnable() {
			@Override
			public void run() {
				currentPager++;
				if(currentPager==10000){
					currentPager=0;
				}
				vpbanner.setCurrentItem(currentPager);
				h.postDelayed(this, 3000);
			}
		}, 3000);
	}
	
	int images[]={R.drawable.banner01,R.drawable.banner03,R.drawable.banner04,R.drawable.robot};
	class InnerAdapter extends PagerAdapter{
		@Override
		public int getCount() {
			return 10000;
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView imv=new ImageView(MainActivity.this);
			imv.setImageResource(images[position%4]);//因为是4张图，对4取余数：0.1.2.3
			
			imv.setScaleType(ScaleType.FIT_XY);
			container.addView(imv);
			return imv;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}
	}
	
	
	/***********----初始化,添加fragment----****************/
	private Fragment fragment01;
	private Fragment fragment02;
	private ViewPager vpcontent;
	
	public void initialFragment(){
		vpcontent=(ViewPager) findViewById(R.id.contentPagerId);
//		sm.addIgnoredView(vpcontent);
		
		linXiahua=(LinearLayout) findViewById(R.id.linXiahua);
		//自定义的FragmentPagerAdapter
		MyFragmentPagerAdapter pagerAdapter=new MyFragmentPagerAdapter(this.getSupportFragmentManager());
		
		fragment01=new Fragment01();
		fragment02=new Fragment02();
		
		pagerAdapter.addFragment(fragment01);
		pagerAdapter.addFragment(fragment02);
		vpcontent.setAdapter(pagerAdapter);
		
		vpcontent.setCurrentItem(0,false);//设置最初的页面
	}
	
	/****************-----滑动监听--------*************************/
	private LinearLayout linXiahua;
	
	private void setPagerListener(){
//		Log.i("TAG", "count="+vpcontent.getCurrentItem());
//		Log.i("TAG", "count="+vpcontent.getChildCount());//此两值都为0；
		for (int i = 0; i < 2; i++) {
			ImageView imXiahua=new ImageView(this);
			imXiahua.setBackgroundResource(R.drawable.a5z);//a5z
			
			linXiahua.addView(imXiahua);
			imXiahua.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if(event.getAction()==MotionEvent.ACTION_DOWN){
						vpcontent.setCurrentItem(linXiahua.indexOfChild(v),true);
					}
					return true;
				}
			});
		}
		linXiahua.setGravity(Gravity.CENTER_HORIZONTAL);
		linXiahua.getChildAt(0).setBackgroundResource(R.drawable.a5x);//a5x
		//第二种
	/*	ImageView imXiahua=new ImageView(this);
		linXiahua.addView(imXiahua);
		linXiahua.setBackgroundResource(R.drawable.main_page_one);*/
		
		/*--viewPager的监听--*/
		vpcontent.setOnPageChangeListener(new OnPageChangeListener() {
			private int prePos;
			@Override
			public void onPageSelected(int position) {
				linXiahua.getChildAt(position).setBackgroundResource(R.drawable.a5x);
				linXiahua.getChildAt(prePos).setBackgroundResource(R.drawable.a5z);
				prePos=position;
				
				if (position==1) {
					sm.addIgnoredView(vpcontent);
				} else if(position==0){
					sm.removeIgnoredView(vpcontent);
				}
				/*//第二种
				if (position==1) {
					linXiahua.setBackgroundResource(R.drawable.main_page_two);
				} else {
					linXiahua.setBackgroundResource(R.drawable.main_page_one);
				}*/
			}
			//当检测到有滑动时，执行此参数，arg1滑动百分比0%-99.9..%，arg2滑动距离px，当滑动100%时执行上个方法
			@Override
			public void onPageScrolled(int position, float positionOffset,int positionOffsetPixels) {
				//页面滚动用的				
			}
			@Override
			public void onPageScrollStateChanged(int state) {
				//页面滚动用的
			}
		});
		
	}

	/********----radioButton 的监听-------****************/
	private RadioButton radioG_sao;
	private RadioButton radioG_more;
	private void setBottomListenter() {
		radioG_sao=(RadioButton) findViewById(R.id.saoId);
		radioG_more=(RadioButton) findViewById(R.id.moreId);
		
		radioG_sao.setOnClickListener(new MyOnClickListener());
		radioG_more.setOnClickListener(new MyOnClickListener());
	}
	public class MyOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.saoId:
				DialogUtil.showOpenCamera(MainActivity.this);//自己的方法
				break;
				
			case R.id.moreId:
				Intent intent=new Intent(MainActivity.this, MoreActivity.class);
				startActivity(intent);
				break;
			default:
				break;
			}
		}
	}
	
	/******************-----返回键的延迟线程-----**********/
	private boolean isExit;
	@Override
	public void onBackPressed() {
		if (isExit) {
			Toast.makeText(this, "再按一次则退出", Toast.LENGTH_SHORT).show();
			isExit=true;
			
			Handler handler=new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					isExit=false;
				}
			}, 3000);
		
		} else {
			super.onBackPressed();
		}
	}
	
	/**************-----侧滑菜单-------**********/
	private void initSlidingMenu(Bundle savedInstanceState) {
		
		setBehindContentView(R.layout.slidingframemain);//相当于把自己的布局放在此位置上ps:可以在此初始化，（若非空白则布局内容重复）
		
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.slidingframemain, new SlidingListFragment()).commit();//此处就是我之前图片重复的原因--应该用个空白界面
		
		// 实例化滑动菜单对象
//		SlidingMenu sm = getSlidingMenu();
			sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置在页面哪个部位滑动
			sm.setShadowDrawable(R.drawable.shadow);//侧滑菜单边界的图片
			sm.setShadowWidthRes(R.dimen.shadow_width);//此处可以自己写（不用res）
			sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);//此处是让主界面留的剩余空间
			sm.setFadeDegree(0.35f);// 设置渐入渐出效果的值//淡化系数，越大越模糊
//			sm.setBehindScrollScale(0.0f);// 设置下方视图的在滚动时的缩放比例
			
	}
	
}


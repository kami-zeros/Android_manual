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
		
		sm= getSlidingMenu();//�˴��ó�����Ϊ������Ĺ����
		
		setActionBar();//����ActionBar
		setViewPager();	//����ViewPager
		initialFragment();	//��ʼ��Fragment
		setPagerListener();//��������
		setBottomListenter();//bottom����
		initSlidingMenu(savedInstanceState);//��ʼ���໬�˵���
	}

	/***--����--actionbar-�������Ͻ�optionmenu-*/
	Bitmap bmp2;
	private void setActionBar(){
		ActionBar bar=getActionBar();
		
		bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_bar_bg));//R.drawable-->Drawable
		
		Resources res=this.getResources();    //this������mainactivity
		if (bmp2==null) {
			bmp2=BitmapFactory.decodeResource(res, R.drawable.icon_user_head);//��Դ-->Bitmap
		}
        Log.d("TAG", "actionbar:"+bmp2.toString());
		
        Bitmap roundPhoto=ImageUtil.bitmapFormat(bmp2);//���ǵ��÷�������Բ��ͷ��,����Bitmap
		BitmapDrawable backDraw=new BitmapDrawable(res, roundPhoto);//Bitmap-->Drawable
		bar.setLogo(backDraw);//logo���ԣ������ᱻ��Ϊ����ͼ�꣬����Ϊactivity�ĵ���ͼ
//		bar.setIcon(backDraw);//ͬʱ������icon��logo����ʱ���еģ���logo����ȫ���ǵ�icon
//		bar.setLogo(R.drawable.ic_message);
		
		bar.setHomeButtonEnabled(true);//ͼƬ���Ե��
		
		bar.setDisplayShowTitleEnabled(false);
		bar.setDisplayShowCustomEnabled(true);
		ImageView im=new ImageView(this);
		im.setImageResource(R.drawable.main_screen_logo);
		bar.setCustomView(im);
	}
	
	/***-�ӿ�-*/
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
		
		MenuItem item=menu.add(0, 100, 101, "��ȡ����");	
		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		Bitmap bmp3=TextCanvasUtil.textBitmap();//�����Լ��ģ�����BItmap
		BitmapDrawable backtxt=new BitmapDrawable(getResources(), bmp3);//Bitmap-->Drawable
		item.setIcon(backtxt);
		
//		Bitmap bmp4=BitmapFactory.decodeResource(getResources(), R.drawable.android);//��Դ-->Bitmap
//		Bitmap roundim=ImageUtil.bitmapFormat(bmp4);//����Բ�Σ�����Bitmap
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
			Toast.makeText(this, "��ȡ����", 0).show();//TODO
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	/**********----�����ViewPager����-�����---****************/
	private int currentPager;
	private ViewPager vpbanner;
	
	public void setViewPager(){
		vpbanner=(ViewPager) findViewById(R.id.bannerPagerId);
		PagerAdapter adapter=new InnerAdapter();
		vpbanner.setAdapter(adapter);
		
		currentPager=10000/2;//���м�ҳ�뿪ʼ
		vpbanner.setCurrentItem(currentPager);
		
		sm.addIgnoredView(vpbanner);//�˴��ǽ�ֹ��viewpager����ʱ���໬�˵�����Ӧ
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
			imv.setImageResource(images[position%4]);//��Ϊ��4��ͼ����4ȡ������0.1.2.3
			
			imv.setScaleType(ScaleType.FIT_XY);
			container.addView(imv);
			return imv;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}
	}
	
	
	/***********----��ʼ��,���fragment----****************/
	private Fragment fragment01;
	private Fragment fragment02;
	private ViewPager vpcontent;
	
	public void initialFragment(){
		vpcontent=(ViewPager) findViewById(R.id.contentPagerId);
//		sm.addIgnoredView(vpcontent);
		
		linXiahua=(LinearLayout) findViewById(R.id.linXiahua);
		//�Զ����FragmentPagerAdapter
		MyFragmentPagerAdapter pagerAdapter=new MyFragmentPagerAdapter(this.getSupportFragmentManager());
		
		fragment01=new Fragment01();
		fragment02=new Fragment02();
		
		pagerAdapter.addFragment(fragment01);
		pagerAdapter.addFragment(fragment02);
		vpcontent.setAdapter(pagerAdapter);
		
		vpcontent.setCurrentItem(0,false);//���������ҳ��
	}
	
	/****************-----��������--------*************************/
	private LinearLayout linXiahua;
	
	private void setPagerListener(){
//		Log.i("TAG", "count="+vpcontent.getCurrentItem());
//		Log.i("TAG", "count="+vpcontent.getChildCount());//����ֵ��Ϊ0��
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
		//�ڶ���
	/*	ImageView imXiahua=new ImageView(this);
		linXiahua.addView(imXiahua);
		linXiahua.setBackgroundResource(R.drawable.main_page_one);*/
		
		/*--viewPager�ļ���--*/
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
				/*//�ڶ���
				if (position==1) {
					linXiahua.setBackgroundResource(R.drawable.main_page_two);
				} else {
					linXiahua.setBackgroundResource(R.drawable.main_page_one);
				}*/
			}
			//����⵽�л���ʱ��ִ�д˲�����arg1�����ٷֱ�0%-99.9..%��arg2��������px��������100%ʱִ���ϸ�����
			@Override
			public void onPageScrolled(int position, float positionOffset,int positionOffsetPixels) {
				//ҳ������õ�				
			}
			@Override
			public void onPageScrollStateChanged(int state) {
				//ҳ������õ�
			}
		});
		
	}

	/********----radioButton �ļ���-------****************/
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
				DialogUtil.showOpenCamera(MainActivity.this);//�Լ��ķ���
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
	
	/******************-----���ؼ����ӳ��߳�-----**********/
	private boolean isExit;
	@Override
	public void onBackPressed() {
		if (isExit) {
			Toast.makeText(this, "�ٰ�һ�����˳�", Toast.LENGTH_SHORT).show();
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
	
	/**************-----�໬�˵�-------**********/
	private void initSlidingMenu(Bundle savedInstanceState) {
		
		setBehindContentView(R.layout.slidingframemain);//�൱�ڰ��Լ��Ĳ��ַ��ڴ�λ����ps:�����ڴ˳�ʼ���������ǿհ��򲼾������ظ���
		
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.slidingframemain, new SlidingListFragment()).commit();//�˴�������֮ǰͼƬ�ظ���ԭ��--Ӧ���ø��հ׽���
		
		// ʵ���������˵�����
//		SlidingMenu sm = getSlidingMenu();
			sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//������ҳ���ĸ���λ����
			sm.setShadowDrawable(R.drawable.shadow);//�໬�˵��߽��ͼƬ
			sm.setShadowWidthRes(R.dimen.shadow_width);//�˴������Լ�д������res��
			sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);//�˴���������������ʣ��ռ�
			sm.setFadeDegree(0.35f);// ���ý��뽥��Ч����ֵ//����ϵ����Խ��Խģ��
//			sm.setBehindScrollScale(0.0f);// �����·���ͼ���ڹ���ʱ�����ű���
			
	}
	
}


package com.myproject.guide;

import java.util.ArrayList;
import java.util.List;

import com.example.ard_myproject.R;
import com.viewpagerindicator.CirclePageIndicator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GuideActivity extends FragmentActivity {

	ViewPager viewPager;
	MyPagerAdapter adapter;
	CirclePageIndicator cpi;  //Բ��ҳָʾ������viewpager���ʹ�ã�
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		
		initViewPager();
	}

	private void initViewPager() {
		viewPager=(ViewPager) findViewById(R.id.vPager);
		adapter = new MyPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		
		cpi=(CirclePageIndicator) findViewById(R.id.guide_cpi_indicator);
		cpi.setViewPager(viewPager);//cpi�Ѿ�������viewpager�������¼�������������
		
		//Ȧ���
		cpi.setRadius(8);//һ��û�е�λ����Ĭ����px����Ϊ��λ��
		
		//Ȧ����ɫ����Щ
		cpi.setFillColor(Color.parseColor("#ffff6633"));
		
		//���������һҳ��cpi��ʧ
		cpi.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				if (arg0==3) {
					cpi.setVisibility(View.INVISIBLE);
				} else {
					cpi.setVisibility(View.VISIBLE);//����û�д˾䣬�򻬵����һҳ�ڻػ���Ҳ��ʧ
				}
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {	}
			@Override
			public void onPageScrollStateChanged(int arg0) {	}
		});
		
	}

	public class MyPagerAdapter extends FragmentPagerAdapter{
		List<Fragment> fragments;
		
		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			fragments = new ArrayList<Fragment>();
			fragments.add(new FragmentA());
			fragments.add(new FragmentB());
			fragments.add(new FragmentC());
			fragments.add(new FragmentD());
		}
		@Override
		public Fragment getItem(int arg0) {
			return fragments.get(arg0);
		}
		@Override
		public int getCount() {
			return fragments.size();
		}
	}

}

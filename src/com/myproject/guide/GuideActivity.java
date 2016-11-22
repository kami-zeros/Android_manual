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
	CirclePageIndicator cpi;  //圆形页指示器（与viewpager配合使用）
	
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
		cpi.setViewPager(viewPager);//cpi已经接收了viewpager的所有事件（包括监听）
		
		//圈变大
		cpi.setRadius(8);//一般没有单位的是默认以px像素为单位的
		
		//圈的颜色鲜艳些
		cpi.setFillColor(Color.parseColor("#ffff6633"));
		
		//滑动到最后一页，cpi消失
		cpi.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				if (arg0==3) {
					cpi.setVisibility(View.INVISIBLE);
				} else {
					cpi.setVisibility(View.VISIBLE);//假如没有此句，则滑到最后一页在回滑则也消失
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

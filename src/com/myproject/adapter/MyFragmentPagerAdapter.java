package com.myproject.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

	List<Fragment> fragments=new ArrayList<Fragment>();
	public MyFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	
	public void addFragment(Fragment fragment){
		if(fragment!=null){
			fragments.add(fragment);
		}
	}
	
	
	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

}

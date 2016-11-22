package com.myproject.activity;

import com.example.ard_myproject.R;
import com.example.ard_myproject.R.layout;
import com.example.ard_myproject.R.menu;
import com.myproject.view.MyTitleView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
/**
 * TODO:历史上的今天
 * @author huangshang 2015-11-23 下午3:33:18
 */
public class KanYiKan_Activity extends BaseWebActivity {
	private MyTitleView title;
	private static String webURL="http://www.todayonhistory.com/";// 历史上的今天官网
	
	public KanYiKan_Activity() {
		super(R.layout.activity_kan_yi_kan, webURL);
	}

	@Override
	public void initView() {
		title=(MyTitleView) findViewById(R.id.kan_kan_title);
		title.setTitletext("历史上的今天");
//		title.setRightVisibility(View.GONE);
		setWebView(R.id.kan_webview);
		setEmpty(R.id.kan_emptyview);//listview自带的一种当没有适配器或数据源时显示此（可以写进度条）
		setTitle(title);
	}

	@Override
	public void initData() {
		
	}

}

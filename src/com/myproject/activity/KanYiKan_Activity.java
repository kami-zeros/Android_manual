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
 * TODO:��ʷ�ϵĽ���
 * @author huangshang 2015-11-23 ����3:33:18
 */
public class KanYiKan_Activity extends BaseWebActivity {
	private MyTitleView title;
	private static String webURL="http://www.todayonhistory.com/";// ��ʷ�ϵĽ������
	
	public KanYiKan_Activity() {
		super(R.layout.activity_kan_yi_kan, webURL);
	}

	@Override
	public void initView() {
		title=(MyTitleView) findViewById(R.id.kan_kan_title);
		title.setTitletext("��ʷ�ϵĽ���");
//		title.setRightVisibility(View.GONE);
		setWebView(R.id.kan_webview);
		setEmpty(R.id.kan_emptyview);//listview�Դ���һ�ֵ�û��������������Դʱ��ʾ�ˣ�����д��������
		setTitle(title);
	}

	@Override
	public void initData() {
		
	}

}

package com.myproject.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.ard_myproject.R;
import com.example.ard_myproject.R.layout;
import com.example.ard_myproject.R.menu;
import com.myproject.entity.JavaContent;
import com.myproject.util.AndroidInfoUtil;
import com.myproject.util.JavaInfoUtil;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.ExpandableListView.OnChildClickListener;

public class Activity_androidbasic extends ActionBarActivity {

	ExpandableListView explistView;
	SimpleExpandableListAdapter adapter;//JavaContent_Adapter
	List<String> titles;//主标题
	
	List<Map<String, Object>> groupData = new ArrayList<Map<String,Object>>();
	List<List<Map<String, Object>>> childData = new ArrayList<List<Map<String,Object>>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_androidbasic);
		
		initView();
	}

	private void initView() {
		initActionBar(R.drawable.android_basic_logo, "Android基础", 1);//actionbar
		
		explistView=(ExpandableListView) this.findViewById(R.id.content_listview);
		
		//得到主标题
		List<JavaContent> contents=AndroidInfoUtil.getAndroidContent("android_basic", this);
		titles=new ArrayList<String>();
		String title;
		for (int i = 0; i < contents.size(); i++) {
			if (contents.get(i).getImportance()==9) {
				title=contents.get(i).getTitle();
				titles.add(title);
			}
		}
		
		
		for (int i = 0; i < titles.size(); i++) {
				String titlezhu = titles.get(i);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("groupkey", titlezhu);
				groupData.add(map);
			
				int k = 1;
				List<Map<String, Object>> eachGroup=new ArrayList<Map<String,Object>>();
				for (int j = 1; j < contents.size(); j++) {
					Map<String,Object> childmap=new HashMap<String, Object>();
					switch (i) {
					case 1:
						if (contents.get(j).getShowFlag()==100 || contents.get(j).getShowFlag()==101
								&& contents.get(j).getImportance()!=9 ) {
							title=contents.get(j).getTitle2();
							childmap.put("childkey", k+"."+title);
							eachGroup.add(childmap);
							k++;
						}
						break;
					case 2:
						if (contents.get(j).getShowFlag()==102 && contents.get(j).getImportance()!=9) {
							title=contents.get(j).getTitle2();
							childmap.put("childkey", k+"."+title);
							eachGroup.add(childmap);
							k++;
						}
						break;
					case 3:
						if (contents.get(j).getShowFlag()==103 && contents.get(j).getImportance()!=9) {
							title=contents.get(j).getTitle2();
							childmap.put("childkey", k+"."+title);
							eachGroup.add(childmap);
							k++;
						}
						break;
					case 4:
						if (contents.get(j).getShowFlag()==104 && contents.get(j).getImportance()!=9) {
							title=contents.get(j).getTitle2();
							childmap.put("childkey", k+"."+title);
							eachGroup.add(childmap);
							k++;
						}
						break;
					case 5:
						if (contents.get(j).getShowFlag()==105 && contents.get(j).getImportance()!=9) {
							title=contents.get(j).getTitle2();
							childmap.put("childkey", k+"."+title);
							eachGroup.add(childmap);
							k++;
						}
						break;
					case 6:
						if (contents.get(j).getShowFlag()==106 && contents.get(j).getImportance()!=9) {
							title=contents.get(j).getTitle2();
							childmap.put("childkey", k+"."+title);
							eachGroup.add(childmap);
							k++;
						}
						break;
					case 7:
						if (contents.get(j).getShowFlag()==107 && contents.get(j).getImportance()!=9) {
							title=contents.get(j).getTitle2();
							childmap.put("childkey", k+"."+title);
							eachGroup.add(childmap);
							k++;
						}
						break;
					case 8:
						if (contents.get(j).getShowFlag()==108 && contents.get(j).getImportance()!=9) {
							title=contents.get(j).getTitle2();
							childmap.put("childkey", k+"."+title);
							eachGroup.add(childmap);
							k++;
						}
						break;
					case 9:
						if (contents.get(j).getShowFlag()==109 && contents.get(j).getImportance()!=9) {
							title=contents.get(j).getTitle2();
							childmap.put("childkey", k+"."+title);
							eachGroup.add(childmap);
							k++;
						}
						break;
					case 10:
						if (contents.get(j).getShowFlag()==111 && contents.get(j).getImportance()!=9) {
							title=contents.get(j).getTitle2();
							childmap.put("childkey", k+"."+title);
							eachGroup.add(childmap);
							k++;
						}
						break;
					}
				}
				childData.add(eachGroup);
		}
		
		adapter=new SimpleExpandableListAdapter(this,groupData,android.R.layout.simple_expandable_list_item_1,
				android.R.layout.simple_expandable_list_item_1, //collapsedGroupLayout收缩布局
				new String[]{"groupkey"}, new int[]{android.R.id.text1}, //主标题分组的key
				childData, android.R.layout.simple_expandable_list_item_1, 
				new String[]{"childkey"}, new int[]{android.R.id.text1});
		
		explistView.setAdapter(adapter);//此处错误是因为actionbar布局高度写成match_parent
		
		explistView.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				return true;//为true后不再显示其他监听
			}
		});
	}

}

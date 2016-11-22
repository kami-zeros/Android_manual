package com.myproject.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SimpleExpandableListAdapter;

import com.example.ard_myproject.R;
import com.myproject.entity.JavaContent;
import com.myproject.util.JavaInfoUtil;

public class Activity_javabasic extends ActionBarActivity {

	ExpandableListView explistView;
	SimpleExpandableListAdapter adapter;//JavaContent_Adapter
	List<String> titles;//主标题
//	List<String> titlesfu;//副标题
	
	List<Map<String, Object>> groupData = new ArrayList<Map<String,Object>>();
	List<List<Map<String, Object>>> childData = new ArrayList<List<Map<String,Object>>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_javabasic);
		
		initView();
	}

	private void initView() {
		initActionBar(R.drawable.javabasic_logo, "Java 基础", 1);//actionbar
		
		explistView=(ExpandableListView) this.findViewById(R.id.content_listview);
		
		//得到主标题
		List<JavaContent> contents=JavaInfoUtil.getJavaContent("java_basic", this);
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
			for (int j = 0; j < contents.size(); j++) {
				Map<String,Object> childmap=new HashMap<String, Object>();
				switch (i) {
				case 0:
					if (contents.get(j).getShowFlag()==100 || contents.get(j).getShowFlag()==159
							&& contents.get(j).getImportance()!=9 ) {
						title=contents.get(j).getTitle2();
						childmap.put("childkey", k+"."+title);
						eachGroup.add(childmap);
						k++;
					}
					break;
				case 1:
					if (contents.get(j).getShowFlag()==149 && contents.get(j).getImportance()!=9) {
						title=contents.get(j).getTitle2();
						childmap.put("childkey", k+"."+title);
						eachGroup.add(childmap);
						k++;
					}
					break;
				case 2:
					if (contents.get(j).getShowFlag()==150 && contents.get(j).getImportance()!=9) {
						title=contents.get(j).getTitle2();
						childmap.put("childkey", k+"."+title);
						eachGroup.add(childmap);
						k++;
					}
					break;
				case 3:
					if (contents.get(j).getShowFlag()==151 && contents.get(j).getImportance()!=9) {
						title=contents.get(j).getTitle2();
						childmap.put("childkey", k+"."+title);
						eachGroup.add(childmap);
						k++;
					}
					break;
				case 4:
					if (contents.get(j).getShowFlag()==152 && contents.get(j).getImportance()!=9) {
						title=contents.get(j).getTitle2();
						childmap.put("childkey", k+"."+title);
						eachGroup.add(childmap);
						k++;
					}
					break;
				case 5:
					if (contents.get(j).getShowFlag()==153 && contents.get(j).getImportance()!=9) {
						title=contents.get(j).getTitle2();
						childmap.put("childkey", k+"."+title);
						eachGroup.add(childmap);
						k++;
					}
					break;
				case 6:
					if (contents.get(j).getShowFlag()==154 && contents.get(j).getImportance()!=9) {
						title=contents.get(j).getTitle2();
						childmap.put("childkey", k+"."+title);
						eachGroup.add(childmap);
						k++;
					}
					break;
				case 7:
					if (contents.get(j).getShowFlag()==155 && contents.get(j).getImportance()!=9) {
						title=contents.get(j).getTitle2();
						childmap.put("childkey", k+"."+title);
						eachGroup.add(childmap);
						k++;
					}
					break;
				case 8:
					if (contents.get(j).getShowFlag()==160 && contents.get(j).getImportance()!=9) {
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

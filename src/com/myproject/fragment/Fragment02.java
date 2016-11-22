package com.myproject.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ard_myproject.R;
import com.myproject.entity.GridItem;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class Fragment02 extends Fragment {

	public Fragment02() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.fragment_fragment02, container, false);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		setGridView();
		super.onActivityCreated(savedInstanceState);
	}
	
	
	/*****--中间网格布局-****/
	private GridView gridView;
	private View view;
	
	private void setGridView(){
		gridView=(GridView) view.findViewById(R.id.contentGridVId2);
		ArrayAdapter<GridItem> adapter=new ArrayAdapter<GridItem>(getActivity(), R.layout.grid_item_1,gItem){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View v=View.inflate(getActivity(), R.layout.grid_item_1, null);
				GridItem item=(GridItem) getItem(position);
				
				ImageView im=(ImageView) v.findViewById(R.id.imVId);
				TextView tv=(TextView) v.findViewById(R.id.txtVId);
				im.setImageResource(item.getImage());
				tv.setText(item.getText());
				
				v.setBackgroundResource(item.getBackColor());//设置背景
				return v;
			}
		};
//		ArrayInnerAdapter adapter=new ArrayInnerAdapter(getActivity(), R.layout.grid_item_1, gItem);
		gridView.setAdapter(adapter);
		
	}

	//设置数据
//	private GridItem item;
	public static final int BACKCOLOR=R.drawable.item_background02;
	private List<GridItem> gItem=new ArrayList<GridItem>();
	{
		gItem.add(new GridItem("Android进阶", R.drawable.framework,  BACKCOLOR));
		gItem.add(new GridItem("数据存储", R.drawable.android_datastorage,  BACKCOLOR));
		gItem.add(new GridItem("网络应用", R.drawable.android_network,  BACKCOLOR));
		gItem.add(new GridItem("游戏开发", R.drawable.android_games,  BACKCOLOR));
		gItem.add(new GridItem("面试题", R.drawable.android_interview,  BACKCOLOR));
		gItem.add(new GridItem("我的笔记", R.drawable.docedit_large,  BACKCOLOR));
		gItem.add(new GridItem("官方网站", R.drawable.android,  BACKCOLOR));
	}

}

package com.myproject.fragment;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ard_myproject.R;
import com.myproject.activity.Activity_androidbasic;
import com.myproject.activity.Activity_androidcomponment;
import com.myproject.activity.Activity_javaadvance;
import com.myproject.activity.Activity_javabasic;
import com.myproject.activity.Activity_javadatabase;
import com.myproject.activity.Activity_javaweb;
import com.myproject.entity.GridItem;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class Fragment01 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.fragment_fragment01, container, false);
		setGridView();
		return view;
	}
	
//	@Override
//	public void onActivityCreated(Bundle savedInstanceState) {
////		setGridView();
//		super.onActivityCreated(savedInstanceState);
//	}
	
	
	/*****--中间网格布局-****/
//	private HorizontalScrollView HScrollView;
	private GridView gridView;
	private View view;
	
	private void setGridView(){
//		HScrollView=(HorizontalScrollView) findViewById(R.id.HscrollId);
//		HScrollView.setHorizontalScrollBarEnabled(false);//隐藏滚动条
//		this.getView().findViewById(R.id.contentGridVId);
		gridView=(GridView) view.findViewById(R.id.contentGridVId);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));//取消gridview默认选择器指定的背景色
		
		ArrayAdapter<GridItem> adapter=new ArrayAdapter<GridItem>(getActivity(), R.layout.grid_item_1,gItem){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View v=View.inflate(getActivity(), R.layout.grid_item_1, null);
				GridItem item=(GridItem) getItem(position);
				
				ImageView im=(ImageView) v.findViewById(R.id.imVId);
				TextView tv=(TextView) v.findViewById(R.id.txtVId);
				
				im.setImageResource(item.getImage());//宽高被我限制了
				tv.setText(item.getText());
				
				v.setBackgroundResource(item.getBackColor());//设置背景
				return v;
			}
		};
//		ArrayInnerAdapter adapter=new ArrayInnerAdapter(getActivity(), R.layout.grid_item_1, gItem);
		gridView.setAdapter(adapter);
		
		gridView.setOnItemClickListener(new MyOnItemClickListener());
	}

	class MyOnItemClickListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			switch (position) {
			case 0:
				Intent intent=new Intent(getActivity(), Activity_javabasic.class);
				startActivity(intent);//Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				startActivity(new Intent(getActivity(), Activity_javaadvance.class));
				break;
			case 2:
				startActivity(new Intent(getActivity(), Activity_javaweb.class));
				break;
			case 3:
				startActivity(new Intent(getActivity(), Activity_javadatabase.class));
				break;
			case 4:
				startActivity(new Intent(getActivity(), Activity_androidbasic.class));
				break;
			case 5:
				startActivity(new Intent(getActivity(), Activity_androidcomponment.class));
				break;
			case 6:
				Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
				break;
			case 7:
				Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	}
	
	//半透明：#e0000000,透明：#00000000
	//设置数据
//	public static final int BACKCOLOR=R.drawable.item_background;
	public static final int BACKCOLOR=R.drawable.item_background02;
	private List<GridItem> gItem=new ArrayList<GridItem>();
	{
		gItem.add(new GridItem("Java基础",R.drawable.javabasic,BACKCOLOR));
		gItem.add(new GridItem("Java进阶",R.drawable.java_advance,BACKCOLOR));
		gItem.add(new GridItem("Java web",R.drawable.javaee,BACKCOLOR));
		gItem.add(new GridItem("数据库基础",R.drawable.database,BACKCOLOR));
		gItem.add(new GridItem("Android基础",R.drawable.android_basic,BACKCOLOR));
		gItem.add(new GridItem("Android组件",R.drawable.android_components,BACKCOLOR));
		gItem.add(new GridItem("用戶界面",R.drawable.android_userinterface,BACKCOLOR));
		gItem.add(new GridItem("Android设备",R.drawable.android_device,BACKCOLOR));

	}
}

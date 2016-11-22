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
	
	
	/*****--�м����񲼾�-****/
//	private HorizontalScrollView HScrollView;
	private GridView gridView;
	private View view;
	
	private void setGridView(){
//		HScrollView=(HorizontalScrollView) findViewById(R.id.HscrollId);
//		HScrollView.setHorizontalScrollBarEnabled(false);//���ع�����
//		this.getView().findViewById(R.id.contentGridVId);
		gridView=(GridView) view.findViewById(R.id.contentGridVId);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));//ȡ��gridviewĬ��ѡ����ָ���ı���ɫ
		
		ArrayAdapter<GridItem> adapter=new ArrayAdapter<GridItem>(getActivity(), R.layout.grid_item_1,gItem){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View v=View.inflate(getActivity(), R.layout.grid_item_1, null);
				GridItem item=(GridItem) getItem(position);
				
				ImageView im=(ImageView) v.findViewById(R.id.imVId);
				TextView tv=(TextView) v.findViewById(R.id.txtVId);
				
				im.setImageResource(item.getImage());//��߱���������
				tv.setText(item.getText());
				
				v.setBackgroundResource(item.getBackColor());//���ñ���
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
	
	//��͸����#e0000000,͸����#00000000
	//��������
//	public static final int BACKCOLOR=R.drawable.item_background;
	public static final int BACKCOLOR=R.drawable.item_background02;
	private List<GridItem> gItem=new ArrayList<GridItem>();
	{
		gItem.add(new GridItem("Java����",R.drawable.javabasic,BACKCOLOR));
		gItem.add(new GridItem("Java����",R.drawable.java_advance,BACKCOLOR));
		gItem.add(new GridItem("Java web",R.drawable.javaee,BACKCOLOR));
		gItem.add(new GridItem("���ݿ����",R.drawable.database,BACKCOLOR));
		gItem.add(new GridItem("Android����",R.drawable.android_basic,BACKCOLOR));
		gItem.add(new GridItem("Android���",R.drawable.android_components,BACKCOLOR));
		gItem.add(new GridItem("�Ñ�����",R.drawable.android_userinterface,BACKCOLOR));
		gItem.add(new GridItem("Android�豸",R.drawable.android_device,BACKCOLOR));

	}
}

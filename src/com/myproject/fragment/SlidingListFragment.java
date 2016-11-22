package com.myproject.fragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ard_myproject.R;
import com.myproject.activity.LoginActivity;
import com.myproject.view.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class SlidingListFragment extends Fragment implements OnClickListener {
	
	CircleImageView list_fram_icon;
	TextView list_fram_nickname;
	
	private View list_fram1;
	private View list_fram2;
	private View list_fram3;
	private View list_fram4;
	private View list_fram5;
	private View list_fram6;
	private View list_fram7;
	private View list_fram8;
	private View list_fram9;
//	private ImageView list_fram7;
//	private TextView list_fram8;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view=inflater.inflate(R.layout.list_fragment, container, false);
		findViews(view);//
		return view;
	}
	
	private void findViews(View view) {
		list_fram_icon=(CircleImageView) view.findViewById(R.id.list_fram_icon);
		list_fram_nickname=(TextView) view.findViewById(R.id.list_fram_nickname);
		
		list_fram1=view.findViewById(R.id.list_fram1);
		list_fram2=view.findViewById(R.id.list_fram2);
		list_fram3=view.findViewById(R.id.list_fram3);
		list_fram4=view.findViewById(R.id.list_fram4);
		list_fram5=view.findViewById(R.id.list_fram5);
		list_fram6=view.findViewById(R.id.list_fram6);
		list_fram7=view.findViewById(R.id.list_fram7);
		list_fram8=view.findViewById(R.id.list_fram8);
		list_fram9=view.findViewById(R.id.list_fram9);
		
		list_fram_icon.setOnClickListener(this);
		list_fram_nickname.setOnClickListener(this);
		list_fram1.setOnClickListener(this);
		list_fram2.setOnClickListener(this);
		list_fram3.setOnClickListener(this);
		list_fram4.setOnClickListener(this);
		list_fram5.setOnClickListener(this);
		list_fram6.setOnClickListener(this);
		list_fram7.setOnClickListener(this);
		list_fram8.setOnClickListener(this);
		list_fram9.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.list_fram_icon:
		case R.id.list_fram_nickname:
//			Toast.makeText(getActivity(), "昵称", Toast.LENGTH_SHORT).show();
			if (list_fram_nickname.getText().toString().equals("未登录")) {
				Intent intent=new Intent(getActivity(), LoginActivity.class);
				startActivityForResult(intent, 100);
				
			} else {
				//TODO 可以展示详情页面--在此我是退出登录
				AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
				builder.setTitle("是否退出登录？");
				builder.setIcon(android.R.drawable.ic_menu_info_details);
				
				builder.setNegativeButton("否", null);
				builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//清空
						avatar_url=null;
						list_fram_icon.setImageResource(R.drawable.icon_user_head);
						list_fram_nickname.setText("未登录");
					}
				});
				builder.create().show();
			}
			break;
		case R.id.list_fram1:
			Toast.makeText(getActivity(), "检查更新", 0).show();
			break;
		case R.id.list_fram2:
			Toast.makeText(getActivity(), "学习历程", 0).show();
			break;
		case R.id.list_fram3:
			Toast.makeText(getActivity(), "消息中心", 0).show();
			break;
		case R.id.list_fram4:
			Toast.makeText(getActivity(), "推荐好友", 0).show();
			break;
		case R.id.list_fram5:
			Toast.makeText(getActivity(), "帮助信息", 0).show();
			break;
		case R.id.list_fram6:
			Toast.makeText(getActivity(), "意见反馈", 0).show();
			break;
		case R.id.list_fram7:
		case R.id.list_fram8:
			Toast.makeText(getActivity(), "设置", 0).show();
			break;
		case R.id.list_fram9:
			Toast.makeText(getActivity(), "温度", 0).show();
			break;
		default:
			break;
		}
	}
	
	String avatar_url;
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode==getActivity().RESULT_OK && requestCode==100) {
			String name=data.getStringExtra("name");
			list_fram_nickname.setText(name);
			
			avatar_url=data.getStringExtra("avatar");
			if (TextUtils.isEmpty(avatar_url)) {//用户在注册的时候未指定头像
				list_fram_icon.setImageResource(R.drawable.icon_user_head);
			
			} else {//用户在注册的时候指定了头像
				ImageLoader.getInstance().displayImage(avatar_url, list_fram_icon);
				
				for (MyListener mrl : list) {
					mrl.send(avatar_url);
					Log.d("TAG", "onActivityResult:"+avatar_url);
				}
			}
		}
	}
	
	private static List<MyListener> list=new ArrayList<SlidingListFragment.MyListener>();
	public interface MyListener {
	    void send(String imgurl);
	}
	
}

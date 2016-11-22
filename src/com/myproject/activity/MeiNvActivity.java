package com.myproject.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.android.volley.Response;
import com.example.ard_myproject.R;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.myproject.adapter.MeinvAdapter;
import com.myproject.entity.MeinvBeans;
import com.myproject.entity.MeinvBeans.Newslist;
import com.myproject.http.HttpUtil;

public class MeiNvActivity extends Activity {

	PullToRefreshListView ptrListView;
	ListView listView;
	List<MeinvBeans.Newslist> newslists;
	ImageView img_emptyview;//进度条
	MeinvAdapter adapter;
	
	@Bind(R.id.rest_actionbar_title)
	TextView meinv_title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mei_nv);
		
		ButterKnife.bind(this);
		initListView();
		meinv_title.setText("");
	}
	
	@OnClick(R.id.rest_actionbar_left)
	public void back(View v){
		finish();
	}
	
	@OnClick(R.id.rest_actionbar_title)
	public void bianhua(View v){
		if (meinv_title.getText().toString().isEmpty()) {
			setContentView(R.layout.meinv_bianhua);
			
			TextView img_title=(TextView) findViewById(R.id.rest_actionbar_title);
			img_title.setText("请你欣赏");
			img_title.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent=new Intent( MeiNvActivity.this, MeiNvActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//刷新---这样只有一个activity
					startActivity(intent);
				}
			});
			
			ImageView img_back=(ImageView) findViewById(R.id.rest_actionbar_left);
			img_back.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
		} 
	}

	private void initListView() {
		ptrListView=(PullToRefreshListView) findViewById(R.id.ptr_lsv_img);
		listView=ptrListView.getRefreshableView();//真正的listview
		img_emptyview=(ImageView) findViewById(R.id.img_emptyview);
		
		newslists=new ArrayList<Newslist>();
		adapter=new MeinvAdapter(this, newslists);
		listView.setAdapter(adapter);
		
		listView.setEmptyView(img_emptyview);
		((AnimationDrawable)img_emptyview.getDrawable()).start();//启动动画
		
		//下拉刷新
		ptrListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String apikey="26d8e0591405bb0ce972933b80fcbc02";
				String url="http://apis.baidu.com/txapi/mvtp/meinv"+apikey;
				HttpUtil.loadMeinvBean(MeiNvActivity.this, "20", new Response.Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						Gson gson=new Gson();
						MeinvBeans beans=gson.fromJson(arg0, MeinvBeans.class);
						adapter.addData(beans.getNewslists(), true);
						ptrListView.onRefreshComplete();
					}
				});
			}
		});
	}
	
	@Override
	protected void onResume() {//跳转activity可以刷新数据
		super.onResume();
		refresh();
	}

	private void refresh() {
		HttpUtil.loadMeinvBean(this, "30", new Response.Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				Gson gson=new Gson();
//				Log.d("TAG", "MeiNvActivity.gson:"+arg0);
				
				//TuanContentBean bean=gson.fromJson(arg0, TuanContentBean.class);
				MeinvBeans beans=gson.fromJson(arg0, MeinvBeans.class);
//				Log.i("TAG", "refresh3:"+beans.getNewslists());
				
				adapter.addData(beans.getNewslists(), true);
			}
		});
	}


}

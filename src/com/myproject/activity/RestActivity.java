package com.myproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ard_myproject.R;

public class RestActivity extends Activity {

	ImageView actionbar_left;
	LinearLayout zou_zou, kan_kan, xiao_xiao, ping_ping;
	LinearLayout jingxi;
	LinearLayout jing_xi;
	View tv1;//������ʧ��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rest);
		
		initialView();
	}
	
	private void initialView() {
		actionbar_left=(ImageView) findViewById(R.id.img_actionbar_left);
		zou_zou=(LinearLayout) findViewById(R.id.zou_zou);
		kan_kan=(LinearLayout) findViewById(R.id.kan_kan);
		xiao_xiao=(LinearLayout) findViewById(R.id.xiao_xiao);
		ping_ping=(LinearLayout) findViewById(R.id.ping_ping);
		
		jingxi=(LinearLayout) findViewById(R.id.jingxi);
		jingxi.setOnLongClickListener(new MyOnLongClickListener());
		
		jing_xi=(LinearLayout) findViewById(R.id.jingxi_content);
		tv1=findViewById(R.id.test1);//������ʧ
		
	}
	
	MediaPlayer music;
	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.img_actionbar_left:
			finish();
			break;
		case R.id.zou_zou:
			Intent intent1=new Intent(this, ZouYiZou_Activity.class);
			startActivity(intent1);
			break;
		case R.id.kan_kan:
			Intent intent2=new Intent(this, KanYiKan_Activity.class);
			startActivity(intent2);
			break;
		case R.id.xiao_xiao:
			Toast.makeText(this, "ЦһЦ", 0).show();//TODO
			break;
		case R.id.ping_ping:
			Intent intent=new Intent();
			intent.setClassName("com.example.tuan1", "com.example.tuan1.MainActivity");
			startActivity(intent);//new Intent("dianping.app.main")
			break;
		case R.id.jingxi_content:
			Intent intent3=new Intent(this, MeiNvActivity.class);//Toast.makeText(this, "��ϲ", 0).show();
			startActivity(intent3);
			break;
		default:
			break;
		}
	}
	
	/***-���ڵ�ĳ���ؼ�������ʱ����ô�����Ļ����ؿؼ�������ײ㣬�����������ã�һ����˵��Ҫ��������ؼ�������������
	 * �������ַ�����1.������ؼ��������layout�����layout���ؼ�ͬ��ͬ�ߣ�����������򣬵��button���ж����button�ɼ��������أ�
	 * 				�����ɼ��˵��layout������ʾbutton��
	 * 			2.�ı�ؼ���ɫ����͸��״̬^_^-*/
	public class MyOnLongClickListener implements OnLongClickListener{
		@Override
		public boolean onLongClick(View v) {
			if (jing_xi.getVisibility()==v.INVISIBLE) {
				jing_xi.setVisibility(v.VISIBLE);
				tv1.setVisibility(v.VISIBLE);
				music=MediaPlayer.create(RestActivity.this, R.raw.appear);//�������
				
			
			} else if (jing_xi.getVisibility()==v.VISIBLE) {
				jing_xi.setVisibility(v.INVISIBLE);
				tv1.setVisibility(v.INVISIBLE);
				music=MediaPlayer.create(RestActivity.this, R.raw.disappear);
//				music.start();
			}
			music.start();
			return true;
		}
		
	}


}

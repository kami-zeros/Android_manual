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
	View tv1;//用于消失用
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
		tv1=findViewById(R.id.test1);//用于消失
		
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
			Toast.makeText(this, "笑一笑", 0).show();//TODO
			break;
		case R.id.ping_ping:
			Intent intent=new Intent();
			intent.setClassName("com.example.tuan1", "com.example.tuan1.MainActivity");
			startActivity(intent);//new Intent("dianping.app.main")
			break;
		case R.id.jingxi_content:
			Intent intent3=new Intent(this, MeiNvActivity.class);//Toast.makeText(this, "惊喜", 0).show();
			startActivity(intent3);
			break;
		default:
			break;
		}
	}
	
	/***-由于当某个控件被隐藏时，那么这样的话隐藏控件是在最底层，根本起不了作用！一般来说是要配合其他控件触发它出来！
	 * 但有两种方法：1.在这个控件外面包层layout。这层layout跟控件同宽同高，你点击这个区域，点击button，判断如果button可见，则隐藏，
	 * 				若不可见了点击layout，则显示button！
	 * 			2.改变控件颜色，如透明状态^_^-*/
	public class MyOnLongClickListener implements OnLongClickListener{
		@Override
		public boolean onLongClick(View v) {
			if (jing_xi.getVisibility()==v.INVISIBLE) {
				jing_xi.setVisibility(v.VISIBLE);
				tv1.setVisibility(v.VISIBLE);
				music=MediaPlayer.create(RestActivity.this, R.raw.appear);//添加声音
				
			
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

package com.myproject.util;

import com.example.ard_myproject.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.provider.MediaStore;
import android.sax.StartElementListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class DialogUtil {
	
	/*****---弹出打开摄像头对话框--******/
	public static void showOpenCamera(final Context context){
		AlertDialog.Builder builder=new AlertDialog.Builder(context);
//		builder.setIcon(R.drawable.y8);
//		builder.setTitle("Android学习手册申请访问摄像头");

		//标题居中
		LinearLayout layout2 = new LinearLayout(context);  //默认水平
		ImageView im=new ImageView(context);
		im.setImageResource(R.drawable.y8);
		TextView title=new TextView(context);
		title.setText("Android学习手册申请访问摄像头");
		layout2.addView(im);
		layout2.addView(title);
		layout2.setGravity(Gravity.CENTER);
		layout2.setBackgroundResource(R.drawable.top_bar_bg);
//		layout2.setHorizontalGravity(Gravity.CENTER);
		builder.setCustomTitle(layout2);
		
	    //文本居中
		 LinearLayout layout = new LinearLayout(context);  
		 layout.setOrientation(LinearLayout.VERTICAL);//设置布局是垂直的
		 TextView agree = new TextView(context);  
		 agree.setText("允许");
		 agree.setTextSize(20f);
		 agree.setGravity(Gravity.CENTER);//文本居中
		 agree.setBackgroundResource(R.drawable.audio_background);
		 TextView cancel=new TextView(context);
		 cancel.setText("取消");
		 cancel.setTextSize(20f);
		 cancel.setGravity(Gravity.CENTER);
		 cancel.setBackgroundResource(R.drawable.audio_background);
		 LinearLayout.LayoutParams pm = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
		 layout.addView(agree, pm); 
		 layout.addView(cancel,pm);
		 layout.setGravity(Gravity.CENTER);  //让layout布局居中或是布局里的内容居中
		 layout.setBackgroundResource(R.drawable.bookshelf);
		 builder.setView(layout);  
	
		 //让对话框从窗口底部滑出
		final AlertDialog dialog=builder.create();//创建对话框
		Window window=dialog.getWindow();
		window.setGravity(Gravity.BOTTOM);//此处可以设置dialog显示的位置//再配合上dialog.setContentView，完全是想怎么搞就怎么搞
		window.setWindowAnimations(R.style.mydialogstyle);//添加动画
		dialog.show();//显示对话框	
		
		//同意与取消的按钮
		agree.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				context.startActivity(intent);
//				context.startActivityForResult(intent, 101);//Toast.makeText(context, "启动摄像头", 0).show();
				
				dialog.dismiss();
				return true;
			}
		});
		
		cancel.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				dialog.dismiss();
				return true;
			}
		});
		
		
		
	}
	
	/*
	 * 		//title居中
//	    LinearLayout layout = new LinearLayout(context);  
//	    TextView title = new TextView(context);  
//	    title.setText("测试测试测\n测试测试测\n测试测试测\n测试测试测");  
//	    LinearLayout.LayoutParams pm = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
//	    layout.addView(title, pm);  
//	    layout.setGravity(Gravity.CENTER); 
//	    builder.setView(layout);//以上其实是文本
//	    	TextView title2 = new TextView(context);  
//	    	title2.setText("提示");  
//	   	 	title2.setGravity(Gravity.CENTER); 
//	    builder.setCustomTitle(title2);//以上是标题（setTitle）
 * 
 * 	/*
		//列表选项对话框
		String items[]=new String[]{ "允许","拒绝" };
		builder.setItems(items, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					Toast.makeText(context, "启动摄像头", 0).show();
					dialog.dismiss();
					break;
				case 1:
					dialog.dismiss();
					break;
				}
			}
		});*/
		 /*此就是setContentView
			 * LayoutInflater li=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
	        // View v=li.inflate(R.layout.dialog_layout, null);//自定义一个xml布局
	        // dialog.setContentView(v);
	        dialog.setContentView(R.layout.dialog_layout);
	        dialog.setTitle("Custom Dialog");*/
}

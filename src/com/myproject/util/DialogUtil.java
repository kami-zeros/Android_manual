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
	
	/*****---����������ͷ�Ի���--******/
	public static void showOpenCamera(final Context context){
		AlertDialog.Builder builder=new AlertDialog.Builder(context);
//		builder.setIcon(R.drawable.y8);
//		builder.setTitle("Androidѧϰ�ֲ������������ͷ");

		//�������
		LinearLayout layout2 = new LinearLayout(context);  //Ĭ��ˮƽ
		ImageView im=new ImageView(context);
		im.setImageResource(R.drawable.y8);
		TextView title=new TextView(context);
		title.setText("Androidѧϰ�ֲ������������ͷ");
		layout2.addView(im);
		layout2.addView(title);
		layout2.setGravity(Gravity.CENTER);
		layout2.setBackgroundResource(R.drawable.top_bar_bg);
//		layout2.setHorizontalGravity(Gravity.CENTER);
		builder.setCustomTitle(layout2);
		
	    //�ı�����
		 LinearLayout layout = new LinearLayout(context);  
		 layout.setOrientation(LinearLayout.VERTICAL);//���ò����Ǵ�ֱ��
		 TextView agree = new TextView(context);  
		 agree.setText("����");
		 agree.setTextSize(20f);
		 agree.setGravity(Gravity.CENTER);//�ı�����
		 agree.setBackgroundResource(R.drawable.audio_background);
		 TextView cancel=new TextView(context);
		 cancel.setText("ȡ��");
		 cancel.setTextSize(20f);
		 cancel.setGravity(Gravity.CENTER);
		 cancel.setBackgroundResource(R.drawable.audio_background);
		 LinearLayout.LayoutParams pm = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
		 layout.addView(agree, pm); 
		 layout.addView(cancel,pm);
		 layout.setGravity(Gravity.CENTER);  //��layout���־��л��ǲ���������ݾ���
		 layout.setBackgroundResource(R.drawable.bookshelf);
		 builder.setView(layout);  
	
		 //�öԻ���Ӵ��ڵײ�����
		final AlertDialog dialog=builder.create();//�����Ի���
		Window window=dialog.getWindow();
		window.setGravity(Gravity.BOTTOM);//�˴���������dialog��ʾ��λ��//�������dialog.setContentView����ȫ������ô�����ô��
		window.setWindowAnimations(R.style.mydialogstyle);//��Ӷ���
		dialog.show();//��ʾ�Ի���	
		
		//ͬ����ȡ���İ�ť
		agree.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				context.startActivity(intent);
//				context.startActivityForResult(intent, 101);//Toast.makeText(context, "��������ͷ", 0).show();
				
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
	 * 		//title����
//	    LinearLayout layout = new LinearLayout(context);  
//	    TextView title = new TextView(context);  
//	    title.setText("���Բ��Բ�\n���Բ��Բ�\n���Բ��Բ�\n���Բ��Բ�");  
//	    LinearLayout.LayoutParams pm = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
//	    layout.addView(title, pm);  
//	    layout.setGravity(Gravity.CENTER); 
//	    builder.setView(layout);//������ʵ���ı�
//	    	TextView title2 = new TextView(context);  
//	    	title2.setText("��ʾ");  
//	   	 	title2.setGravity(Gravity.CENTER); 
//	    builder.setCustomTitle(title2);//�����Ǳ��⣨setTitle��
 * 
 * 	/*
		//�б�ѡ��Ի���
		String items[]=new String[]{ "����","�ܾ�" };
		builder.setItems(items, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					Toast.makeText(context, "��������ͷ", 0).show();
					dialog.dismiss();
					break;
				case 1:
					dialog.dismiss();
					break;
				}
			}
		});*/
		 /*�˾���setContentView
			 * LayoutInflater li=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
	        // View v=li.inflate(R.layout.dialog_layout, null);//�Զ���һ��xml����
	        // dialog.setContentView(v);
	        dialog.setContentView(R.layout.dialog_layout);
	        dialog.setTitle("Custom Dialog");*/
}

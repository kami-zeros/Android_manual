package com.myproject.activity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ard_myproject.R;

public class MoreActivity extends Activity  { //implements OnClickListener

	 	private ImageView result; 
	 	ImageView actionbar_left;
	 	private TextView  more_result;  
	 	private LinearLayout rest, study;
	    
	    private AnimationDrawable anim;  
	    private Button start;  
	    private Handler mHandler;  
	  
	    @Override  
	    protected void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.activity_more);  
	  
	        initialView();
	  
	        initialAnim();  
	    }
	    
		private void initialView() {
			more_result = (TextView) findViewById(R.id.more_result);  
	        start = (Button) findViewById(R.id.btn_start);  
	        result = (ImageView) findViewById(R.id.result);
	        
	        actionbar_left=(ImageView) findViewById(R.id.imageView_actionbar_left);
	        rest=(LinearLayout) findViewById(R.id.more_rest);
	        study=(LinearLayout) findViewById(R.id.more_study);
//	        start.setOnClickListener(this);  
		}  

		public void doClick(View v) {
			switch (v.getId()) {
			case R.id.imageView_actionbar_left:
				finish();
				break;
			case R.id.more_rest:
				Intent intent=new Intent(this, RestActivity.class);
				startActivity(intent);
				break;
			case R.id.more_study:
				finish();
				break;
			}
		}
		
		//动画
		int[] dis = {R.drawable.dice_action_0, 
				R.drawable.dice_action_1, 
	    		R.drawable.dice_action_2, 
	    		R.drawable.dice_action_3}; 
		private void initialAnim() {
			mHandler = new Handler() {  //默认关联当前线程的Looper
	            @Override  
	            public void handleMessage(Message msg) {  //工作在主线程，由上述Looper决定
	            	
	            	anim.stop();  //动画停下
	                int r = new Random().nextInt(6);  
	                 
	                int res = -1;  
	                switch (r) {  
	                    case 0:  
	                        res = R.drawable.dice_1;  
	                        break;  
	                    case 1:  
	                        res = R.drawable.dice_2;  
	                        break;  
	                    case 2:  
	                        res = R.drawable.dice_3;  
	                        break;  
	                    case 3:  
	                        res = R.drawable.dice_4;  
	                        break;  
	                    case 4:  
	                        res = R.drawable.dice_5;  
	                        break;  
	                    case 5:  
	                        res = R.drawable.dice_6;  
	                        break;  
	                }  
	                if (r != -1) {  
	                    result.setImageDrawable(getResources().getDrawable(res));//setBackgroundDrawable(getResources().getDrawable(res));  
	                    more_result.setVisibility(View.VISIBLE);
	                }  
	                if (r==0 ||r==1 ||r==2) {
						more_result.setText((r+1)+" 是小！哎，继续学习吧...-_-|||");
					} else {
						more_result.setText((r+1)+" 是大！哈哈，休息一下...^_^");
					}
	            }  
	        };
		}
	    
	    //按钮
	    public void start(View v) { //private 
//	    	if (anim == null) {  
	    		anim = new AnimationDrawable();  
	            for (int i = 0; i < 4; i++) {  //利用循环制作帧动画
	            	anim.addFrame(getResources().getDrawable(dis[i]), 150);  
	            }  
	    		//result.setImageDrawable(getResources().getDrawable(R.anim.anim_dice));//此句不行
	            anim.setOneShot(false);  
	            result.setImageDrawable(anim);//setBackgroundDrawable(anim);  
//	        }  
//	        if (anim.isRunning()) {  
//	            return;  
//	        }  
	    	anim.start();  
	        Timer timer = new Timer();  
	        timer.schedule(new TimerTask() {  
	            @Override  
	            public void run() {  
	               mHandler.sendEmptyMessage(0); 
	            }  
	        }, 2000);   
	    }
//		@Override
//		public void onClick(View v) {
//			start();
//		}
		
}

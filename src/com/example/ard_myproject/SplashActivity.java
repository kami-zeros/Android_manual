package com.example.ard_myproject;

import com.myproject.guide.GuideActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {

	private ImageView imageView;
	private Animation anim_splash;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		imageView=(ImageView) findViewById(R.id.splash_im);
		
		anim_splash=AnimationUtils.loadAnimation(this, R.anim.splash);//将动画文件转换为animation对象
		imageView.setAnimation(anim_splash);//将动画应用到图片上
		anim_splash.start();
		
		anim_splash.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				Intent intent=new Intent(SplashActivity.this, GuideActivity.class);
				startActivity(intent);
				
				overridePendingTransition(R.anim.splash_out, R.anim.splash_in);
				finish();
			}
		});
	}

}

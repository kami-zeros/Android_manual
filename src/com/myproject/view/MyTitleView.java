package com.myproject.view;

import com.example.ard_myproject.R;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyTitleView extends LinearLayout {

	private ImageView leftIcon, rightIcon;// 左右侧图片
	private TextView actionbar_title;// 中间标题
	Context context;
	
	public MyTitleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public MyTitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public MyTitleView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context2) {
		this.context=context2;
		LayoutInflater.from(getContext()).inflate(R.layout.include_rest_actionbar, this);
		leftIcon=(ImageView) findViewById(R.id.rest_actionbar_left);
		actionbar_title=(TextView) findViewById(R.id.rest_actionbar_title);
//		rightIcon
		leftIcon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((Activity)context).finish();
			}
		});
	}
	
	public TextView getTitletext() {
		return actionbar_title;
	}
	public void setTitletext(String text) {
		this.actionbar_title.setText(text);
	}
	/**
	 * 设置左边图片是否显示(默认显示)
	 * @param visibility
	 */
	public void setLeftVisibility(int visibility) {
		leftIcon.setVisibility(visibility);
	}

	/**
	 * 设置右边图片是否显示(默认不显示)
	 * @param visibility
	 */
	public void setRightVisibility(int visibility) {
		rightIcon.setVisibility(visibility);
	}

}

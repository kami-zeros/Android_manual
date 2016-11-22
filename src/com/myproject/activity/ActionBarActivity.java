package com.myproject.activity;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ard_myproject.R;

public class ActionBarActivity extends Activity {

//	LinearLayout actionbar;
	
	public void initActionBar(int leftId, String title, int rightId) {
//		if (actionbar==null) {
//			return;
//		}
		
		ImageView image_left=(ImageView) findViewById(R.id.contentbar_left);
		ImageView image_right=(ImageView) findViewById(R.id.contentbar_right);
		final TextView text_title=(TextView) findViewById(R.id.contentbar_title);
		final EditText edit=(EditText) findViewById(R.id.contentbar_edit);
		
		if (leftId==-1) {
			image_left.setVisibility(View.INVISIBLE);
		} else {
			image_left.setImageResource(leftId);
		}
		
		if (rightId==-1) {
			image_right.setVisibility(View.INVISIBLE);
		} else {
			image_right.setImageResource(R.drawable.navibar_search_icon_search);
		}
		
		if (title==null) {
			text_title.setVisibility(View.INVISIBLE);
		} else {
			text_title.setText(title);
		}
		
		//test
		image_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		image_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (text_title.getVisibility()==v.VISIBLE) {
					text_title.setVisibility(View.GONE);
					edit.setVisibility(View.VISIBLE);
				} else {
					text_title.setVisibility(View.VISIBLE);
					edit.setVisibility(View.GONE);
				}
			}
		});
		
	}

}

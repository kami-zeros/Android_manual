package com.myproject.activity;

import com.example.ard_myproject.R;
import com.example.ard_myproject.R.layout;
import com.example.ard_myproject.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Activity_arduserinterface extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arduserinterface);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_arduserinterface, menu);
		return true;
	}

}

package com.myproject.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ard_myproject.MainActivity;
import com.example.ard_myproject.R;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class FragmentD extends Fragment {

	public FragmentD() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=getLayoutInflater(savedInstanceState).inflate(R.layout.fragmentd, container, false);
		Button btn=(Button) view.findViewById(R.id.btnId);
		
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), MainActivity.class);
				startActivity(intent);
				
				getActivity().finish();
			}
		});
		return view;
	}

}

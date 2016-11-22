package com.myproject.adapter;

import java.util.List;

import com.example.ard_myproject.R;
import com.myproject.entity.JavaContent;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class JavaContent_Adapter extends MyBaseAdapter<String>{

	public JavaContent_Adapter(Context context) {
		super(context);
	}

	ViewHolder holder;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.include_content, parent, false);
			
			holder.content_title=(TextView) convertView.findViewById(R.id.content_title);
			convertView.setTag(holder);
		} else {
			holder=(ViewHolder) convertView.getTag();
		}
		
		String content=getItem(position);
		holder.content_title.setText(content);
		
		return convertView;
	}

	class ViewHolder{
		TextView content_title;
	}
}

package com.myproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ard_myproject.R;
import com.myproject.entity.JavaContent;
import com.myproject.entity.JavaInfo;


public class JavaInfo_Adapter extends MyBaseAdapter<JavaContent> {	//JavaInfo开始的测试

	public JavaInfo_Adapter(Context context) {
		super(context);
	}

	private ViewHolder holder;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView==null) {
			
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.include_content, null);
			
			holder.content_title = (TextView) convertView.findViewById(R.id.content_title);
			
			convertView.setTag(holder);
		} else {
			holder=(ViewHolder) convertView.getTag();
		}
		
		JavaContent info=getItem(position);//JavaInfo开始的测试
//		Log.d("TAG", "打印："+info.getTitle());
		
		holder.content_title.setText(info.getTitle());
		
		return convertView;
	}
	
	class ViewHolder{
		TextView content_title;//内容标题
	}
}

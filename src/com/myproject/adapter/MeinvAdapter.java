package com.myproject.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ard_myproject.R;
import com.myproject.entity.MeinvBeans;
import com.myproject.entity.MeinvBeans.Newslist;
import com.myproject.http.HttpUtil;

public class MeinvAdapter extends MyBaseAdapter<MeinvBeans.Newslist>{

	public MeinvAdapter(Context context, List<Newslist> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.include_meinv, null);
			
			holder.img1=(ImageView) convertView.findViewById(R.id.first_img);
//			holder.img2=(ImageView) convertView.findViewById(R.id.second_img);
//			holder.img3=(ImageView) convertView.findViewById(R.id.third_img);
			
			convertView.setTag(holder);
		} else {
			holder=(ViewHolder) convertView.getTag();
		}
		
		Newslist newslist=getItem(position);
		String url=newslist.getPicUrl();
//		Log.d("TAG", "MeinvAdapter.url:"+url);
		
		HttpUtil.loadImage(url, context, holder.img1);
		
		return convertView;
	}

	/**
	 * 清空现有数据源中数据
	 */
	public void clear() {
		datas.clear();//在父类中
		notifyDataSetChanged();
	}
	
	class ViewHolder{
		ImageView img1;
		ImageView img2;
		ImageView img3;
	}
}

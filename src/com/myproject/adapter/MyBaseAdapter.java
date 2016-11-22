package com.myproject.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * 此处写是为了练习
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

	Context context;
	List<T> datas=new ArrayList<T>();
	LayoutInflater inflater;
	
//	public MyBaseAdapter() {	}

	public MyBaseAdapter(Context context) {
		inflater=LayoutInflater.from(context);
		this.context = context;
	}

	public MyBaseAdapter(Context context, List<T> list) {
		inflater=LayoutInflater.from(context);
		this.context = context;
		this.datas = list;//用于多条数据初始化，相当于new ArrayList
	}

	/** 添加一条数据 */
	public void addData(T t) {
		if (t!=null) {
			datas.add(t);
		}
	}
	/**
	 * 添加多条数据
	 * @param list
	 */
	public void addData(List<T> list) {
		if (list!=null) {
			if (list.size()>0) {
				datas.addAll(list);
			}
		}
		notifyDataSetChanged();
	}
	
	public void addData(List<T> lst, boolean isClear) {
		if (isClear) {
			removeAllData();
			addData(lst);
		} 
		addData(lst);//调用上面方法
		notifyDataSetChanged();
	}
	
	//获得数据适配器中所有数据
	public List<T> getAllDatas() {
		return datas;
	}
	
	//删除一条数据
	public void removeData(int index) {
		if (index>=0) {
			datas.remove(index);
		}
	}
	
	//清空数据
	public void removeAllData() {
		datas.clear();
	}
	
	
	
	@Override
	public int getCount() {
		Log.d("TAG", "---getCount---"+datas.size());
		return datas.size();
	}

	@Override
	public T getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public abstract View getView(int position, View convertView, ViewGroup parent);
	
}

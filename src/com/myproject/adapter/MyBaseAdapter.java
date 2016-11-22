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
 * �˴�д��Ϊ����ϰ
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
		this.datas = list;//���ڶ������ݳ�ʼ�����൱��new ArrayList
	}

	/** ���һ������ */
	public void addData(T t) {
		if (t!=null) {
			datas.add(t);
		}
	}
	/**
	 * ��Ӷ�������
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
		addData(lst);//�������淽��
		notifyDataSetChanged();
	}
	
	//�����������������������
	public List<T> getAllDatas() {
		return datas;
	}
	
	//ɾ��һ������
	public void removeData(int index) {
		if (index>=0) {
			datas.remove(index);
		}
	}
	
	//�������
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

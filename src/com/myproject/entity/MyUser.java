package com.myproject.entity;

import cn.bmob.v3.BmobObject;

public class MyUser extends BmobObject{	//Bmob��֧�ֻ������ͣ���֧�ְ�װ����
	String name;
	String password;
	String avatar;//�û�ͷ��
	Boolean gender;//true�� -- falseŮ------boolean�İ�װ����Boolean
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	
	
	
}

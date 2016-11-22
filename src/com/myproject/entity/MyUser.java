package com.myproject.entity;

import cn.bmob.v3.BmobObject;

public class MyUser extends BmobObject{	//Bmob不支持基本类型，而支持包装类型
	String name;
	String password;
	String avatar;//用户头像
	Boolean gender;//true男 -- false女------boolean的包装类型Boolean
	
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

package com.myproject.entity;

import java.io.Serializable;
import java.util.List;

public class MeinvBeans {
	private int code;
	private String msg;
	List<Newslist> newslist;
	
	public static class Newslist {
		String ctime;
		String title;
		String description;
		String picUrl;
		String url;
		public String getCtime() {
			return ctime;
		}
		public void setCtime(String ctime) {
			this.ctime = ctime;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getPicUrl() {
			return picUrl;
		}
		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		@Override
		public String toString() {
			return "Newslist [ctime=" + ctime + ", title=" + title
					+ ", description=" + description + ", picUrl=" + picUrl
					+ ", url=" + url + "]";
		}
		
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Newslist> getNewslists() {
		return newslist;
	}
	public void setNewslists(List<Newslist> newslists) {
		this.newslist = newslists;
	}
	@Override
	public String toString() {
		return "MeinvImg [code=" + code + ", msg=" + msg + ", newslists="
				+ newslist + "]";
	}
	
}

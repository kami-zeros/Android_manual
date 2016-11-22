package com.myproject.entity;

public class ListMenu {
	
	private String include_listtxt;
	private int include_listimg;
	
	public ListMenu(String include_listtxt, int include_listimg) {
		super();
		this.include_listtxt = include_listtxt;
		this.include_listimg = include_listimg;
	}
	public String getInclude_listtxt() {
		return include_listtxt;
	}
	public void setInclude_listtxt(String include_listtxt) {
		this.include_listtxt = include_listtxt;
	}
	public int getInclude_listimg() {
		return include_listimg;
	}
	public void setInclude_listimg(int include_listimg) {
		this.include_listimg = include_listimg;
	}
	@Override
	public String toString() {
		return "ListMenu [include_listtxt=" + include_listtxt
				+ ", include_listimg=" + include_listimg + "]";
	}
	
	
}

package com.myproject.entity;

public class JavaInfo {
	private int id;//
	private String title;//所有标题
	private int importance;//程度
	
	
	public JavaInfo() {
		super();
	}

	public JavaInfo(int id, String title, int importance) {
		super();
		this.id = id;
		this.title = title;
		this.importance = importance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	@Override
	public String toString() {
		return "JavaInfo [id=" + id + ", title=" + title + ", importance="
				+ importance + "]";
	}
	
}

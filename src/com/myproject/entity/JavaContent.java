package com.myproject.entity;

public class JavaContent {
	private int id;//
	private String title;//���б���1
	private String title2;//���б���2
	private String content;//��������
	private int importance;//�̶�
	private int showFlag;//�̶�
	
	public JavaContent() {
		super();
	}
	public JavaContent(int id, String title, String title2, String content,
			int importance, int showFlag) {
		super();
		this.id = id;
		this.title = title;
		this.title2 = title2;
		this.content = content;
		this.importance = importance;
		this.showFlag = showFlag;
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
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getImportance() {
		return importance;
	}
	public void setImportance(int importance) {
		this.importance = importance;
	}
	public int getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(int showFlag) {
		this.showFlag = showFlag;
	}
	@Override
	public String toString() {
		return "JavaContent [id=" + id + ", title=" + title + ", title2="
				+ title2 + ", content=" + content + ", importance="
				+ importance + ", showFlag=" + showFlag + "]";
	}
	
	
}

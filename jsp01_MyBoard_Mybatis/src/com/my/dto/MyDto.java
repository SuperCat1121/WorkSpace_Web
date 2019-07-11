package com.my.dto;

import java.util.Date;

public class MyDto {
	private int no;
	private String name;
	private String title;
	private String content;
	private Date date;
	public MyDto() {
		super();
	}
	public MyDto(int no, String name, String title, String content, Date date) {
		super();
		this.no = no;
		this.name = name;
		this.title = title;
		this.content = content;
		this.date = date;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	
}

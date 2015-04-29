package com.example.notepad;

public class Item {
	String filename;
	String title;
	String content;
	
	public Item(String filename, String title, String content) {
		super();
		this.filename = filename;
		this.title = title;
		this.content = content;
	}
	public String getFileName() {
		return filename;
	}
	public void setFileName(String filename) {
		this.filename = filename;
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
	

}

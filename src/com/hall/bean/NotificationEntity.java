package com.hall.bean;

import java.io.Serializable;

import android.graphics.Bitmap;

public class NotificationEntity implements Serializable{

	/**
	 * 通知名字
	 */
	private String name;
	
	/**
	 * 跳转事件
	 */
	private String downurl;
	
	/**
	 * 图片
	 */
	private Bitmap bitmap;
	
	/**
	 * 介绍
	 */
	private String title;
	
	/**
	 * 文字提示
	 */
	private String warnings;
	
	public String getWarnings() {
		return warnings;
	}

	public void setWarnings(String warnings) {
		this.warnings = warnings;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDownurl() {
		return downurl;
	}

	public void setDownurl(String downurl) {
		this.downurl = downurl;
	}
	
	

}

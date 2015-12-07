package com.hall.view;

import android.graphics.Paint.Style;

public class CriBean {
	
	public static final String DEFALUTBORDERCOLORSTR="#ff0085cf";
	public static final String DEFALUTBORDERCOLORPRESSSTR="#ff00529c";
	
	public static final int DEFAULTCORDER=8;//默认圆角的弧度，根据View的大小（宽高来设置），
											 //如果宽是屏幕大小默认15就行了，如果小于屏幕一半建议设置5-10  
	public static final int DEFAULTSTYLE=1;//默认是实心（空心：0 实心：1）
	public static final int DEFAULTBORDERWIDTH=1;//默认的边框的宽度（不建议修改）
	public static final Style FINALSTYLE=Style.FILL;//默认的边框的宽度（不建议修改）
	private int corder; //圆角弧度
	private Style style=Style.FILL ;//实心|空心
	private int borderWidth;//边框宽度（只有设置style为空心才可以看得出来）
	private int borderColor;//边框颜色（只有设置style为空心才可以看得出来）
	public int getCorder() {
		return corder;
	}
	public void setCorder(int corder) {
		this.corder = corder;
	}
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}
	public int getBorderWidth() {
		return borderWidth;
	}
	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}
	public int getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(int borderColor) {
		this.borderColor = borderColor;
	}
	
	private int groundNormal;
	private int groundPress;
	private int groundSelect;
	private int groundForce;
	public int getGroundNormal() {
		return groundNormal;
	}
	public void setGroundNormal(int groundNormal) {
		this.groundNormal = groundNormal;
	}
	public int getGroundPress() {
		return groundPress;
	}
	public void setGroundPress(int groundPress) {
		this.groundPress = groundPress;
	}
	public int getGroundSelect() {
		return groundSelect;
	}
	public void setGroundSelect(int groundSelect) {
		this.groundSelect = groundSelect;
	}
	public int getGroundForce() {
		return groundForce;
	}
	public void setGroundForce(int groundForce) {
		this.groundForce = groundForce;
	}
	
}

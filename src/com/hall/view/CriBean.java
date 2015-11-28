package com.hall.view;

import android.graphics.Paint.Style;

public class CriBean {
	public static final int DEFAULTCORDER=15;
	public static final int DEFAULTSTYLE=1;
	public static final int DEFAULTBORDERWIDTH=1;
	private int corder;
	private Style style;
	private int borderWidth;
	private int borderColor;
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
}

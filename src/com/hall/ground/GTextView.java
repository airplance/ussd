package com.hall.ground;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class GTextView extends TextView {

	public GTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 非button类型的控件，如果需要状态背景/颜色切换的话，记得在xml加上android:clickable="true"这个属性 
	 */
	@SuppressLint("NewApi")
	public GTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		CGUtil.setGroundFromView(this, attrs);
	}
}

package com.hall.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.hall.ground.CGUtil;

public class GLinearLayout extends LinearLayout {

	public GLinearLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("NewApi")
	public GLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		CGUtil.setGroundFromView(this, attrs);
	}
}

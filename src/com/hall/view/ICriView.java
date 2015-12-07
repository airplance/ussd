package com.hall.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * 注意生命：
 * 如需要使用圆角，那么就必须要放弃背景色的设置
 * @author john
 */
public interface ICriView {
	/**
	 * 根据参数，重新draw视图
	 * @param canvas
	 */
	void onDrawCri(Canvas canvas);
	/**
	 * 根据xml设置的参数，设置一些需要用到的参数
	 * @param context
	 * @param attrs
	 */
	void initAttr(Context context, AttributeSet attrs);
}

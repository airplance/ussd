package com.hall.view;

import android.graphics.Canvas;

/**
 * 注意生命：
 * 如需要使用圆角，那么就必须要放弃背景色的设置
 * @author john
 */
public interface ICriView {
	void onDrawCri(Canvas canvas);
}

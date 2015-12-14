package com.hall.ground;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.Button;

public class CButton extends Button {
	private final Paint paint;
	private final Context context;

	public CButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.paint = new Paint();
		this.paint.setAntiAlias(true); // 消除锯齿
		this.paint.setStyle(Style.STROKE); // 绘制空心圆或 空心矩形
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		// 实例化一支画笔
		Paint paint = new Paint();
		paint.setStrokeWidth(1);
		paint.setStyle(Style.STROKE); // 设置样式-空心矩形
		paint.setColor(android.graphics.Color.DKGRAY); // 设置颜色
		paint.setAntiAlias(true); // 抗锯齿
		RectF rectF = new RectF(2, 0, this.getWidth() - 2, this.getHeight() - 2);
		canvas.drawRoundRect(rectF, 20, 20, paint); // 绘制一个矩形
	}
}

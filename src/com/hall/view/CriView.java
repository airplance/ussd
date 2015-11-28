package com.hall.view;

import com.hall.util.CriUtil;
import com.online.hall.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class CriView extends LinearLayout implements ICriView {

	private String bordercolorstr = "#ffffff";
	private CriBean criBean;
	private Canvas canvas;

	public CriView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initAttr(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		this.canvas = canvas;
		onDrawCri(canvas);
		super.onDraw(canvas);
		// CriUtil.setAllBackGroundState(getContext(),this);
	}

	protected void initAttr(Context context, AttributeSet attrs) {
		criBean = new CriBean();
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.criView);
		criBean.setBorderWidth(a.getInt(R.styleable.criView_borderWidth,
				CriBean.DEFAULTBORDERWIDTH));

		int styl = a.getInt(R.styleable.criView_style, CriBean.DEFAULTSTYLE);
		if (styl == 1) {
			criBean.setStyle(Style.FILL);
		} else {
			criBean.setStyle(Style.STROKE);
		}
		bordercolorstr = a.getString(R.styleable.criView_borderColor);
		if (bordercolorstr == null || bordercolorstr.equals("")) {
			bordercolorstr = "#ffffffff";
		}

		criBean.setBorderColor(Color.parseColor(bordercolorstr));
		criBean.setCorder(a.getInt(R.styleable.criView_corder,
				CriBean.DEFAULTCORDER));
	}

	@Override
	public void onDrawCri(Canvas canvas) {
		// TODO Auto-generated method stub
		// 实例化一支画笔
		Paint paint = new Paint();
		paint.setStrokeWidth(criBean.getBorderWidth());
		paint.setStyle(criBean.getStyle()); // 设置样式-空心矩形
		paint.setColor(criBean.getBorderColor()); // 设置颜色
		paint.setAntiAlias(true); // 抗锯齿
		int p = 2;
		RectF rectF = new RectF(0, 0, this.getWidth()
				- criBean.getBorderWidth() + p, this.getHeight()
				- criBean.getBorderWidth() + p);
		canvas.drawRoundRect(rectF, criBean.getCorder(), criBean.getCorder(),
				paint); // 绘制一个矩形

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		CriUtil.onTouchEvent(this, canvas, criBean, bordercolorstr, event);
		return super.onTouchEvent(event);
	}
}

package com.hall.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

import com.online.hall.R;

public class CriTextView extends TextView implements ICriView {

	private CriBean criBean;

	public CriTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initAttr(context, attrs);
	}

	public CriTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		onDrawCri(canvas);
		super.onDraw(canvas);
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
		String string = a.getString(R.styleable.criView_borderColor);
		if (string==null ||string.equals("")) {
			string="#ffffff";
		} 
		criBean.setBorderColor(Color.parseColor(string));
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
		RectF rectF = new RectF(0, 0, this.getWidth()
				- criBean.getBorderWidth(), this.getHeight()
				- criBean.getBorderWidth());
		canvas.drawRoundRect(rectF, criBean.getCorder(), criBean.getCorder(),
				paint); // 绘制一个矩形
	}

}

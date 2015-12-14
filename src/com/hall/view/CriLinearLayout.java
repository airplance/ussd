package com.hall.view;

import com.hall.ground.CGUtil;
import com.online.hall.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class CriLinearLayout extends LinearLayout implements ICriView {
	private CriBean criBean;
	private Canvas canvas;

	public CriLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initAttr(context, attrs);
	}

	@SuppressLint("NewApi")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		this.canvas = canvas;
		if (criBean != null) {
			onDrawCri(canvas);
		}
		super.onDraw(canvas);
		setBackgroundColor(Color.parseColor("#ffffffff"));
	}

	@Override
	public void onDrawCri(Canvas canvas) {
		// TODO Auto-generated method stub
		// 实例化一支画笔
		Paint paint = new Paint();
		paint.setStrokeWidth(CriBean.DEFAULTBORDERWIDTH);
		paint.setStyle(CriBean.FINALSTYLE); // 设置样式-空心矩形
		paint.setColor(criBean.getBorderColor()); // 设置颜色
		paint.setAntiAlias(true); // 抗锯齿
		RectF rectF = new RectF(0, 0, this.getWidth()
				- criBean.getBorderWidth(), this.getHeight()
				- criBean.getBorderWidth());

		criBean.setCorder(CriBean.DEFAULTCORDER);
		canvas.drawRoundRect(rectF, criBean.getCorder(), criBean.getCorder(),
				paint); // 绘制一个矩形
	}

	@Override
	public void initAttr(Context context, AttributeSet attrs) {
		// TODO Auto-generated method stub
		criBean = new CriBean();
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.criView);
		String groundnormal = a.getString(R.styleable.criView_groundnormal);
		String groundpress = a.getString(R.styleable.criView_groundpress);
		String groundforce = a.getString(R.styleable.criView_groundforce);
		String groundselect = a.getString(R.styleable.criView_groundselect);
		if (groundnormal == null || groundnormal.equals("")) {
			groundnormal = CriBean.DEFALUTBORDERCOLORSTR;
		}
		if (groundpress == null || groundpress.equals("")) {
			groundpress = CriBean.DEFALUTBORDERCOLORPRESSSTR;
		}
		if (groundforce == null || groundforce.equals("")) {
			groundforce = CriBean.DEFALUTBORDERCOLORSTR;
		}
		if (groundselect == null || groundselect.equals("")) {
			groundselect = CriBean.DEFALUTBORDERCOLORSTR;
		}
		criBean.setGroundNormal(Color.parseColor(groundnormal));
		criBean.setGroundPress(Color.parseColor(groundpress));
		criBean.setGroundForce(Color.parseColor(groundforce));
		criBean.setGroundSelect(Color.parseColor(groundselect));

		//
		criBean.setBorderColor(criBean.getGroundNormal());
	}

	@SuppressLint("WrongCall")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		CGUtil.onTouchEvent(this, canvas, criBean, event);
		return super.onTouchEvent(event);
	}
}

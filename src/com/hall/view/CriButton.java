package com.hall.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.hall.bean.HallUserInfo;
import com.hall.ground.CGUtil;
import com.hall.ui.RegAndLogActivity;
import com.online.hall.R;

import cxh.voctex.utils.ToastUtil;

@SuppressLint("NewApi")
public class CriButton extends Button implements ICriView {
	private CriBean criBean;
	private Canvas canvas;
	private CriButtonType buttonType;
	private CustomDialog.Builder builder;
	private boolean isCheckLogin = true;

	public void setCheckLogin(boolean isCheckLogin) {
		this.isCheckLogin = isCheckLogin;
	}

	public void setCriBean(CriBean criBean) {
		this.criBean = criBean;
	}

	public enum CriButtonType {
		CriButtonTypeOpen, CriButtonTypeCancle, CriButtonTypeCheck
	}

	public CriButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initAttr(context, attrs);
	}

	public CriButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		this.canvas = canvas;
		if (criBean != null) {
			onDrawCri(canvas);
		}
		super.onDraw(canvas);
	}

	@Override
	public void initAttr(Context context, AttributeSet attrs) {
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

	@SuppressLint("WrongCall")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		CGUtil.onTouchEvent(this, canvas, criBean, event);
		return super.onTouchEvent(event);
	}

	private void initDialog() {
		// TODO Auto-generated method stub
		builder = new CustomDialog.Builder(getContext());
		builder.setMessage("");
		builder.setPositiveButton(getResources().getString(R.string.open),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 设置你的操作事项
						if (onClickListener != null) {
							onClickListener.callBack();
						}
					}
				});

		builder.setNegativeButton(getResources().getString(R.string.cancle),
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
	}

	@Override
	public void setOnClickListener(OnClickListener l) {
		// TODO Auto-generated method stub
		onClickListener = new CSelfOnClick(l);
		super.setOnClickListener(onClickListener);
	}

	private CSelfOnClick onClickListener;

	private class CSelfOnClick implements OnClickListener {
		private OnClickListener ll;
		private View v;

		public CSelfOnClick(OnClickListener l) {
			this.ll = l;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (isCheckLogin && !HallUserInfo.isPhone()) {
				ToastUtil.showS(getContext(), "请先登录账号");
				Intent tent = new Intent(getContext(), RegAndLogActivity.class);
				tent.putExtra("frist", false);
				getContext().startActivity(tent);
				return;
			}
			this.v = v;
			if (buttonType == CriButtonType.CriButtonTypeOpen) {
				// 弹出来
				initDialog();
				builder.create().show();
			} else {
				ll.onClick(v);
			}
		}

		public void callBack() {
			ll.onClick(v);
		}

	}

}

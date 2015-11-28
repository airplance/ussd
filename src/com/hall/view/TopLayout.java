package com.hall.view;

import com.online.hall.R;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TopLayout extends LinearLayout {
	public static final int TOPID = 86743;
	private TextView title;
	private Button back;
	private BackOnClick onClick;

	public TopLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView();
	}

	private void initView() {
		setId(TOPID);
		LayoutInflater.from(getContext()).inflate(R.layout.toplayout, this);
		back = (Button) findViewById(R.id.top_layout_back);
		title = (TextView) findViewById(R.id.top_layout_title);
		if (!isInEditMode())
			back.setOnClickListener(defaultOnClick);
	}

	/**
	 * @param title 显示标题
	 * @param showBack 是否隐藏返回按钮
	 * @param onClick  返回按钮回调接口
	 */
	public void setTitleAndBack(String title,int showBack, BackOnClick onClick) {
		this.title.setText(title);
		this.onClick = onClick;
		this.back.setVisibility(showBack);
	}

	interface BackOnClick {
		void doBackThing();
	}

	private OnClickListener defaultOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (onClick != null) {
				onClick.doBackThing();
			}
			((Activity) getContext()).finish();
		}
	};
}

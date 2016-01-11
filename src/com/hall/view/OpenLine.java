package com.hall.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.online.hall.R;

public class OpenLine extends LinearLayout {

	@ViewInject(R.id.openline_title)
	private TextView title;
	@ViewInject(R.id.openline_show_layout)
	private LinearLayout layout;
	@ViewInject(R.id.openline_check)
	private Button check;
	@ViewInject(R.id.openline_status_txt)
	private TextView status_txt;
	@ViewInject(R.id.openline_cancle)
	private Button cancle;
	@ViewInject(R.id.openline_open)
	private Button open;

	public OpenLine(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.openline, this);
	}

}

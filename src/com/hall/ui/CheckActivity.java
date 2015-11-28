package com.hall.ui;

import android.os.Bundle;
import android.view.View;

import com.hall.util.BaseActivity;
import com.hall.view.TopLayout;
import com.online.hall.R;

public class CheckActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check);
		TopLayout top = (TopLayout) findViewById(TopLayout.TOPID);
		top.setTitleAndBack("查询", View.VISIBLE, null);
	}
}

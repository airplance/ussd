package com.hall.ui;

import android.os.Bundle;
import android.view.View;

import com.hall.util.BaseActivity;
import com.hall.view.TopLayout;
import com.online.hall.R;

public class OtherActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other);
		TopLayout top = (TopLayout) findViewById(TopLayout.TOPID);
		top.setTitleAndBack("其他功能", View.VISIBLE, null);
	}

}

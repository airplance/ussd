package com.hall.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.hall.MainActivity;
import com.hall.util.BaseActivity;
import com.hall.view.TopLayout;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.online.hall.R;

public class RegisterActivity extends BaseActivity {

	@ViewInject(R.id.register_ok)
	private Button ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		ViewUtils.inject(this);
		TopLayout top = (TopLayout) findViewById(TopLayout.TOPID);
		top.setTitleAndBack("注册用户", View.VISIBLE, null);
		ok.setOnClickListener(OnClick);

	}

	private OnClickListener OnClick = new OnClickListener() {

		@SuppressLint("InlinedApi")
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			switch (id) {
			case R.id.register_ok:
				Intent tent = new Intent(getBaseContext(), MainActivity.class);
				tent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				tent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(tent);
				finish();
				
				
				break;

			default:
				break;
			}
		}
	};
}

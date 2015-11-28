package com.hall.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.hall.MainActivity;
import com.hall.util.BaseActivity;
import com.hall.view.TopLayout;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.online.hall.R;

public class LoginActivity extends BaseActivity {
	@ViewInject(R.id.login_findpass)
	private TextView find;
	@ViewInject(R.id.login_ok)
	private TextView ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		ViewUtils.inject(this);
		TopLayout top = (TopLayout) findViewById(TopLayout.TOPID);
		top.setTitleAndBack("用户登录", View.VISIBLE, null);
		find.setOnClickListener(OnClick);
		ok.setOnClickListener(OnClick);

	}

	private OnClickListener OnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			Intent tent = new Intent();
			int id = v.getId();
			switch (id) {
			case R.id.login_findpass:
				tent.setClass(getBaseContext(), FindPassActivity.class);
				startActivity(tent);
				break;
			case R.id.login_ok:
				tent.setClass(getBaseContext(), MainActivity.class);
				startActivity(tent);
				finish();
				break;
			default:
				break;
			}

		}
	};
}

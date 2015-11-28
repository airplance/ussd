package com.hall.ui;

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

public class FindPassActivity extends BaseActivity {

	@ViewInject(R.id.find_ok)
	private Button ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findpasswd);
		ViewUtils.inject(this);
		TopLayout top = (TopLayout) findViewById(TopLayout.TOPID);
		top.setTitleAndBack("找回密码", View.VISIBLE, null);
		ok.setOnClickListener(OnClick);
	}

	private OnClickListener OnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			switch (id) {
			case R.id.find_ok:
				Intent tent = new Intent(getBaseContext(), MainActivity.class);
				tent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(tent);
				finish();
				break;

			default:
				break;
			}
		}
	};
}

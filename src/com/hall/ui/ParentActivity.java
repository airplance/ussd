package com.hall.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.hall.util.ActivityControl;
import com.online.hall.R;

import cxh.voctex.utils.ToastUtil;

public class ParentActivity extends FragmentActivity {

	private FragmentManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parentactivity);
		ActivityControl.addActivity(this);
		manager = getSupportFragmentManager();
		try {
			String abcd = getIntent().getStringExtra("name");
			Class xmlmenu;
			xmlmenu = Class.forName(abcd);
			Fragment fragment = (Fragment) xmlmenu.newInstance();
			initFragment(fragment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ToastUtil.showS(this, "找不到类对象");
		}
	}

	// 初始化Fragment(FragmentActivity中呼叫)
	public void initFragment(Fragment f) {
		changeFragment(f, true);
	}

	// 切換Fragment
	public void changeFragment(Fragment f) {
		changeFragment(f, false);
	}

	private void changeFragment(Fragment f, boolean init) {
		FragmentTransaction ft = manager.beginTransaction();
		ft.replace(R.id.parent_layout, f);
		if (!init)
			ft.addToBackStack(null);
		ft.commit();
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		ActivityControl.removeActivity(this);
		overridePendingTransition(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);
	}

	@Override
	public void startActivity(Intent intent) {
		// TODO Auto-generated method stub
		super.startActivity(intent);
		overridePendingTransition(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);
	}
}

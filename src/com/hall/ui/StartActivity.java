package com.hall.ui;

import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.hall.util.BaseActivity;

public class StartActivity extends BaseActivity {

	private ImageView cover;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		startActivity(new Intent(getApplicationContext(),
				RegAndLogActivity.class));
		// startActivity(new Intent(getApplicationContext(),
		// RegAndLogActivity.class));
		finish();
		// cover = new ImageView(this);
		// cover.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
		// cover.setImageResource(R.drawable.app_icon);
		// cover.setScaleType(ScaleType.FIT_XY);
		// setContentView(cover);
		//
		// Handler h = new Handler();
		// h.postDelayed(new Runnable() {
		//
		//
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		// startActivity(new Intent(getApplicationContext(),
		// MainActivity.class));
		// }
		// }, 5 * 1000);
	}

	public static void switchLanguage(Context c, String language) {
		Resources resources = c.getResources();
		Configuration configuration = resources.getConfiguration();
		DisplayMetrics displayMetrics = resources.getDisplayMetrics();
		if (language.equals("it")) {
			configuration.locale = Locale.ITALIAN;
		} else {
			configuration.locale = Locale.SIMPLIFIED_CHINESE;
		}
		resources.updateConfiguration(configuration, displayMetrics);
	}
}

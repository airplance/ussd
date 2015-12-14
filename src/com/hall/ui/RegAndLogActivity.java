package com.hall.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.hall.MainActivity;
import com.hall.util.BaseActivity;
import com.online.hall.R;

public class RegAndLogActivity extends BaseActivity {
	private ArrayList<View> viewContainter = new ArrayList<View>();
	private ViewPager Pager;
	private Button reg, log;
	private final int[] imgsRid = { R.drawable.splash,
			R.drawable.home_left_one, R.drawable.home_paycharge };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regandlog);
		Pager = (ViewPager) findViewById(R.id.regandlog_viewpager);
		reg = (Button) findViewById(R.id.regandlog_reg);
		log = (Button) findViewById(R.id.regandlog_log);
		log.setOnClickListener(twoO);
		reg.setOnClickListener(twoO);
		for (int i = 0; i < 1; i++) {
			ImageView img = new ImageView(this);
			img.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
			img.setScaleType(ScaleType.FIT_XY);
			img.setImageResource(imgsRid[i]);
			viewContainter.add(img);
		}
		Pager.setAdapter(ada);
		Pager.setOnPageChangeListener(onPageLis);
		if (getIntent().getBooleanExtra("frist", true)) {
			log.setVisibility(View.INVISIBLE);
			reg.setVisibility(View.INVISIBLE);
			new Handler().postDelayed(intentR, 3 * 1000);
		}
	}

	private Runnable intentR = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Intent tent = new Intent(RegAndLogActivity.this, MainActivity.class);
			startActivity(tent);
			finish();
		}
	};

	private OnClickListener twoO = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent tent = new Intent();
			int id = v.getId();
			switch (id) {
			case R.id.regandlog_reg:
				tent.setClass(getBaseContext(), RegisterActivity.class);
				break;
			case R.id.regandlog_log:
				tent.setClass(getBaseContext(), LoginActivity.class);
				break;

			default:
				break;
			}
			startActivity(tent);
		}
	};

	private PagerAdapter ada = new PagerAdapter() {

		// viewpager中的组件数量
		@Override
		public int getCount() {
			return viewContainter.size();
		}

		// 滑动切换的时候销毁当前的组件
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView(viewContainter.get(position));
		}

		// 每次滑动的时候生成的组件
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			((ViewPager) container).addView(viewContainter.get(position));
			return viewContainter.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}

	};

	private OnPageChangeListener onPageLis = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}
	};
}

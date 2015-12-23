package com.hall;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.hall.fragment.TabItemFragmentHome;
import com.hall.fragment.TabItemFragmentMy;
import com.hall.ui.StartActivity;
import com.hall.util.ActivityControl;
import com.hall.view.BadgeView;
import com.online.hall.R;

import cxh.voctex.utils.LogUtil;
import cxh.voctex.utils.ToastUtil;

/**
 * @author voctex 2015-09-20
 * 
 */
@SuppressLint({ "InflateParams", "NewApi" })
public class MainActivity extends FragmentActivity {

	private FragmentTabHost tabHost;
	private String[] tabTexts;
	private int[] tabImgs = new int[] { R.drawable.selector_tab_item_icon0,
			R.drawable.selector_tab_item_icon1, };
	private Class<?>[] fragments = new Class[] { TabItemFragmentHome.class,
			TabItemFragmentMy.class };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StartActivity.switchLanguage(this, "it");

		tabHost = (FragmentTabHost) findViewById(R.id.tab_fa_fth);
		tabHost.setup(this, getSupportFragmentManager(),
				R.id.tab_fa_maincontent);
		tabTexts = new String[] {
				getResources().getString(R.string.tab_bottom_home),
				getResources().getString(R.string.tab_bottom_my) };
		for (int i = 0; i < tabTexts.length; i++) {
			TabSpec spec = tabHost.newTabSpec(tabTexts[i]).setIndicator(
					getView(i));
			tabHost.addTab(spec, fragments[i], null);
		}
		// 设置tabs之间的分隔线不显示
		// tabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
		tabHost.setCurrentTab(0);
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				LogUtil.showI(LogUtil.Voc, "tabId=" + tabId);

			}
		});

		TabItemFragmentHome firstFragment = (TabItemFragmentHome) getSupportFragmentManager()
				.findFragmentByTag(tabTexts[0]);

	}

	public BadgeView getBadgeView(int index) {
		BadgeView badgeView = (BadgeView) tabHost.getTabWidget()
				.getChildAt(index).getTag();
		return badgeView;
	}

	private View getView(int index) {
		final View view = LayoutInflater.from(this).inflate(
				R.layout.tab_fa_item, null, true);
		LinearLayout linearLayout = (LinearLayout) view
				.findViewById(R.id.tab_fa_item_layout);
		TextView textView = (TextView) view.findViewById(R.id.tab_fa_item_text);
		ImageView imageView = (ImageView) view
				.findViewById(R.id.tab_fa_item_img);
		textView.setText(tabTexts[index]);
		imageView.setImageResource(tabImgs[index]);
		return view;
	}

	private long waitTime = 2000;
	private long touchTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& KeyEvent.KEYCODE_BACK == keyCode) {
			long currentTime = System.currentTimeMillis();
			if ((currentTime - touchTime) >= waitTime) {
				ToastUtil.showS(getBaseContext(),
						getResources().getString(R.string.back_app), true);
				touchTime = currentTime;

			} else {
				ActivityControl.finishProgrom();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		TabItemFragmentMy my = (TabItemFragmentMy) getSupportFragmentManager()
				.findFragmentByTag(tabTexts[1]);
		my.onActivityResultForPop(requestCode, resultCode, data);
		// if (popPhotoView != null) {
		// popPhotoView.onActivityResult(requestCode, resultCode, data);
		// }
	}

}

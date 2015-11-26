package com.hall;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.hall.fragment.TabItemFragmentFirst;
import com.hall.fragment.TabItemFragmentFour;
import com.hall.fragment.TabItemFragmentThree;
import com.hall.fragment.TabItemFragmentTwo;
import com.hall.view.BadgeView;
import com.online.hall.R;

import cxh.voctex.utils.LogUtil;

/**
 * @author voctex  2015-09-20
 *
 */
@SuppressLint({ "InflateParams", "NewApi" })
public class MainActivity extends FragmentActivity {

	private FragmentTabHost tabHost;
	private String[] tabTexts = new String[] { "通话", "个人" };
	private int[] tabImgs = new int[] { R.drawable.selector_tab_item_icon0,
			R.drawable.selector_tab_item_icon1,};
	private Class<?>[] fragments = new Class[] { TabItemFragmentFirst.class,
			TabItemFragmentTwo.class};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tabHost = (FragmentTabHost) findViewById(R.id.tab_fa_fth);
		tabHost.setup(this, getSupportFragmentManager(),
				R.id.tab_fa_maincontent);

		for (int i = 0; i < tabTexts.length; i++) {
			TabSpec spec = tabHost.newTabSpec(tabTexts[i]).setIndicator(
					getView(i));
			tabHost.addTab(spec, fragments[i], null);
		}
		// 设置tabs之间的分隔线不显示
		tabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
		tabHost.setCurrentTab(0);
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				LogUtil.showI(LogUtil.Voc, "tabId=" + tabId);
				
			}
		});
		
		TabItemFragmentFirst firstFragment = (TabItemFragmentFirst) getSupportFragmentManager()
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

		BadgeView badgeView = new BadgeView(MainActivity.this, linearLayout);
		badgeView.setText("7");
		badgeView.setBadgeMargin(7);
		badgeView.setTextSize(11.8f);
//		badgeView.show();

		view.setTag(badgeView);
		if (index == 0) {
			imageView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					LogUtil.showI(LogUtil.Voc,
							"I click this linearlayout in first item");
					((BadgeView) view.getTag()).toggle();
				}
			});
		}

		return view;
	}

}

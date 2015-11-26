package com.hall.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.online.hall.R;
import com.hall.MainActivity;
import com.hall.view.BadgeView;

import cxh.voctex.utils.LogUtil;

public class TabItemFragmentFirst extends Fragment {

	private Activity mActivity;
	private ViewGroup view;
	private int index = 0;
	private LinearLayout fourItemPay, fourItemCheck, fourItemFlow,
			fourItemOther;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.mActivity = activity;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		LogUtil.showI(LogUtil.Voc, "first----oncreateview");
		if (view == null) {
			view = (ViewGroup) inflater.inflate(
					R.layout.tab_item_fragment_main, container, false);
			fourItemPay = (LinearLayout) view
					.findViewById(R.id.home_fouritem_pay);
			fourItemPay.setOnClickListener(ItemOnclick);
			fourItemPay = (LinearLayout) view
					.findViewById(R.id.home_fouritem_check);
			fourItemPay.setOnClickListener(ItemOnclick);
			fourItemPay = (LinearLayout) view
					.findViewById(R.id.home_fouritem_flow);
			fourItemPay.setOnClickListener(ItemOnclick);
			fourItemPay = (LinearLayout) view
					.findViewById(R.id.home_fouritem_other);
			fourItemPay.setOnClickListener(ItemOnclick);
		} else {
			ViewGroup viewGroup = (ViewGroup) view.getParent();
			if (viewGroup != null) {
				viewGroup.removeAllViewsInLayout();
			}
			LogUtil.showI(LogUtil.Voc, "first----view!=null");
		}
		return view;
	}

	private OnClickListener ItemOnclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

		}
	};

}

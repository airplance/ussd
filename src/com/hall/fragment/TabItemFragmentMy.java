package com.hall.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.online.hall.R;
import com.hall.MainActivity;
import com.hall.ui.RegAndLogActivity;
import com.hall.view.BadgeView;
import com.hall.view.TopLayout;

import cxh.voctex.utils.LogUtil;

public class TabItemFragmentMy extends Fragment {

	private Activity mActivity;
	private ViewGroup view;
	private int index;

	private RelativeLayout itemList, itemFlow, itemPack, itemPay;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.mActivity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		LogUtil.showI(LogUtil.Voc, "second----oncreateview");
		if (view == null) {
			view = (ViewGroup) inflater.inflate(R.layout.tab_item_fragment_my,
					container, false);
			TopLayout top=(TopLayout)view.findViewById(TopLayout.TOPID);
			top.setTitleAndBack("我的", View.INVISIBLE, null);
			itemList = (RelativeLayout) view
					.findViewById(R.id.my_item_layout_listcost);
			itemFlow = (RelativeLayout) view
					.findViewById(R.id.my_item_layout_flow);
			itemPack = (RelativeLayout) view
					.findViewById(R.id.my_item_layout_package);
			itemPay = (RelativeLayout) view
					.findViewById(R.id.my_item_layout_pay);
			itemList.setOnClickListener(itemsO);
			itemFlow.setOnClickListener(itemsO);
			itemPack.setOnClickListener(itemsO);
			itemPay.setOnClickListener(itemsO);
			LogUtil.showI(LogUtil.Voc, "second----view==null");
		} else {
			ViewGroup viewGroup = (ViewGroup) view.getParent();
			if (viewGroup != null) {
				viewGroup.removeAllViewsInLayout();
			}
			LogUtil.showI(LogUtil.Voc, "second----view!=null");
		}
		return view;
	}

	private OnClickListener itemsO = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			switch (id) {
			case R.id.my_item_layout_listcost:
				Intent tent = new Intent(mActivity, RegAndLogActivity.class);
				tent.putExtra("frist", false);
				startActivity(tent);
				break;
			case R.id.my_item_layout_flow:

				break;
			case R.id.my_item_layout_package:

				break;
			case R.id.my_item_layout_pay:

				break;

			default:
				break;
			}
		}
	};
}

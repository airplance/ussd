package com.hall.fragment;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hall.ui.CheckActivity;
import com.hall.ui.OtherActivity;
import com.hall.ui.PackageActivity;
import com.hall.ui.PayCostActivity;
import com.hall.view.TopLayout;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.online.hall.R;

import cxh.voctex.utils.LogUtil;
import cxh.voctex.utils.ToastUtil;

public class TabItemFragmentHome extends Fragment {

	private Activity mActivity;
	private ViewGroup view;
	private int index = 0;
	// private RelativeLayout fourCenterGoodPakc, fourCenter4G, fourCenterCheck,
	// fourCenterBusnis;

	@ViewInject(R.id.home_fouritem_pay)
	private LinearLayout fourItemPay;
	@ViewInject(R.id.home_fouritem_check)
	private LinearLayout fourItemCheck;
	@ViewInject(R.id.home_fouritem_flow)
	private LinearLayout fourItemFlow;
	@ViewInject(R.id.home_fouritem_other)
	private LinearLayout fourItemOther;

	@ViewInject(R.id.home_center_goodpack)
	private RelativeLayout fourCenterGoodPakc;
	@ViewInject(R.id.home_center_4gserver)
	private RelativeLayout fourCenter4G;
	@ViewInject(R.id.home_center_checkkno)
	private RelativeLayout fourCenterCheck;
	@ViewInject(R.id.home_center_bunisopen)
	private RelativeLayout fourCenterBusnis;

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
			TopLayout top = (TopLayout) view.findViewById(TopLayout.TOPID);
			top.setTitleAndBack("天天营业厅", View.INVISIBLE, null);
			ViewUtils.inject(this, view);
			fourCenterGoodPakc.setOnClickListener(ItemOnclick);
			fourCenter4G.setOnClickListener(ItemOnclick);
			fourCenterCheck.setOnClickListener(ItemOnclick);
			fourCenterBusnis.setOnClickListener(ItemOnclick);

			fourItemPay.setOnClickListener(ItemOnclick);
			fourItemCheck.setOnClickListener(ItemOnclick);
			fourItemFlow.setOnClickListener(ItemOnclick);
			fourItemOther.setOnClickListener(ItemOnclick);
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
			Intent intent = new Intent();
			int id = v.getId();
			switch (id) {
			case R.id.home_fouritem_pay:
				intent.setClass(mActivity, PayCostActivity.class);
				break;
			case R.id.home_fouritem_check:
			case R.id.home_center_checkkno:
				intent.setClass(mActivity, CheckActivity.class);

				break;
			case R.id.home_fouritem_flow:
			case R.id.home_center_goodpack:
				intent.setClass(mActivity, PackageActivity.class);

				break;
			case R.id.home_fouritem_other:
				intent.setClass(mActivity, OtherActivity.class);
				break;

			case R.id.home_center_4gserver:
				// intent.setClass(mActivity, PackageActivity.class);
				ToastUtil.showS(mActivity, "4G服务", true);
				break;   
			case R.id.home_center_bunisopen:
				// intent.setClass(mActivity, PackageActivity.class);
				ToastUtil.showS(mActivity, "业务开通", true);

				break;
			default:
				break;
			}
			ComponentName component = intent.getComponent();

			if (component != null)
				startActivity(intent);
		}
	};

}

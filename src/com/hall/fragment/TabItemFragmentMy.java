package com.hall.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hall.ui.RegAndLogActivity;
import com.hall.view.RoundImageView;
import com.hall.view.TopLayout;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.online.hall.R;

import cxh.voctex.utils.LogUtil;

public class TabItemFragmentMy extends Fragment {

	private Activity mActivity;
	private ViewGroup view;

	@ViewInject(R.id.my_item_layout_listcost)
	private RelativeLayout my_item_layout_listcost;
	@ViewInject(R.id.my_item_layout_flow)
	private RelativeLayout my_item_layout_flow;
	@ViewInject(R.id.my_item_layout_package)
	private RelativeLayout my_item_layout_package;
	@ViewInject(R.id.my_item_layout_pay)
	private RelativeLayout my_item_layout_pay;

	@ViewInject(R.id.my_img)
	private RoundImageView img;

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
			ViewUtils.inject(this, view);
			TopLayout top = (TopLayout) view.findViewById(TopLayout.TOPID);
			top.setTitleAndBack("我的", View.INVISIBLE, null);
			my_item_layout_listcost.setOnClickListener(itemsO);
			my_item_layout_flow.setOnClickListener(itemsO);
			my_item_layout_package.setOnClickListener(itemsO);
			my_item_layout_pay.setOnClickListener(itemsO);
			LogUtil.showI(LogUtil.Voc, "second----view==null");
			img.setImageBitmap(BitmapFactory.decodeResource(
					mActivity.getResources(), R.drawable.banner_default_img));
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

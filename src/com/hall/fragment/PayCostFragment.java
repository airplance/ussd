package com.hall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.online.hall.R;

public class PayCostFragment extends BaseFragment {

	public static final String TAG = "com.hall.fragment.PayCostFragment";

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mViewGroup == null) {
			mViewGroup = (ViewGroup) inflater.inflate(R.layout.paycost,
					container, false);
		} else {
			ViewGroup viewGroup = (ViewGroup) mViewGroup.getParent();
			if (viewGroup != null) {
				viewGroup.removeAllViewsInLayout();
			}
		}
		setTitleAndBack("充值中心", View.VISIBLE, null);
		return mViewGroup;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}

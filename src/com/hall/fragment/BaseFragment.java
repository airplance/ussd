package com.hall.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.hall.view.TopLayout;
import com.hall.view.TopLayout.BackOnClick;

public class BaseFragment extends Fragment {
	public Activity mActivity;
	public ViewGroup mViewGroup;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.mActivity = activity;
	}

	protected void setTitleAndBack(String title, int showBack,
			BackOnClick onClick) {
		TopLayout top = (TopLayout) mActivity.findViewById(TopLayout.TOPID);
		top.setTitleAndBack(title, showBack, onClick);
	}
}

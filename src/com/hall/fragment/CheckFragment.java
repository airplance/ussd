package com.hall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hall.view.TopLayout;
import com.online.hall.R;

public class CheckFragment extends BaseFragment {

	public static final String TAG = "com.hall.fragment.CheckFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mViewGroup == null) {
			mViewGroup = (ViewGroup) inflater.inflate(R.layout.check,
					container, false);
		} else {
			ViewGroup viewGroup = (ViewGroup) mViewGroup.getParent();
			if (viewGroup != null) {
				viewGroup.removeAllViewsInLayout();
			}
		}
		setTitleAndBack("查询", View.VISIBLE, null);
		return mViewGroup;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}

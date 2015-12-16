package com.hall.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.online.hall.R;

public class AddBusFragment extends BaseFragment {

	public static final String TAG = "com.hall.fragment.AddBusFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mViewGroup == null) {
			mViewGroup = (ViewGroup) inflater.inflate(R.layout.addbus,
					container, false);
		} else {
			ViewGroup viewGroup = (ViewGroup) mViewGroup.getParent();
			if (viewGroup != null) {
				viewGroup.removeAllViewsInLayout();
			}
		}
		setTitleAndBack("增值业务", View.VISIBLE, null);
		return mViewGroup;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}

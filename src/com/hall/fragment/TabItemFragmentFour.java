package com.hall.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.online.hall.R;

import cxh.voctex.utils.LogUtil;

public class TabItemFragmentFour extends Fragment {

	private Activity mActivity;
	private ViewGroup view;

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
		LogUtil.showI(LogUtil.Voc, "four----oncreateview");
		if (view == null) {
			view = (ViewGroup) inflater.inflate(
					R.layout.tab_item_fragment_main, container, false);
			TextView text = (TextView) view
					.findViewById(R.id.tab_item_fragment_text);
			text.setText("this is four fragment");
			LogUtil.showI(LogUtil.Voc, "four----view==null");
		} else {
			ViewGroup viewGroup = (ViewGroup) view.getParent();
			if (viewGroup != null) {
				viewGroup.removeAllViewsInLayout();
			}
			LogUtil.showI(LogUtil.Voc, "four----view!=null");
		}
		return view;
	}

}

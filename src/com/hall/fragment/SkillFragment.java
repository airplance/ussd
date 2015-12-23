package com.hall.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.hall.util.CallUssd;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.online.hall.R;

public class SkillFragment extends BaseFragment {

	public static final String TAG = "com.hall.fragment.SkillFragment";

	private enum SkillCode {
		NET6OPEN("*103*06*1#"), NET6CANCLE("*103*06*0#"), NET15OPEN(
				"*103*55*1#"), NET15CANCLE("*103*55*0#"), NETSTAND("*103*51*0#");
		// 成员变量
		private String code;

		// 构造方法
		private SkillCode(String code) {
			this.code = code;
		}
	}

	@ViewInject(R.id.skill_6)
	private Button net6;
	@ViewInject(R.id.skill_net15)
	private Button net15;
	@ViewInject(R.id.net_stand)
	private Button netStand;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mViewGroup == null) {
			mViewGroup = (ViewGroup) inflater.inflate(R.layout.skill,
					container, false);
			ViewUtils.inject(this, mViewGroup);
			net6.setOnClickListener(OnClick);
			net15.setOnClickListener(OnClick);
			netStand.setOnClickListener(OnClick);
		} else {
			ViewGroup viewGroup = (ViewGroup) mViewGroup.getParent();
			if (viewGroup != null) {
				viewGroup.removeAllViewsInLayout();
			}
		}
		setTitleAndBack(
				mActivity.getResources().getString(R.string.home_skill),
				View.VISIBLE, null);
		return mViewGroup;
	}

	private OnClickListener OnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {

			case R.id.skill_6:
				CallUssd.Call(mActivity, SkillCode.NET6OPEN.code);
				break;
			case R.id.skill_net15:
				CallUssd.Call(mActivity, SkillCode.NET15OPEN.code);
				break;
			case R.id.net_stand:
				CallUssd.Call(mActivity, SkillCode.NETSTAND.code);
				break;
			}
		}
	};

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}

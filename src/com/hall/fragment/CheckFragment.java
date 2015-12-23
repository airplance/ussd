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

public class CheckFragment extends BaseFragment {

	public static final String TAG = "com.hall.fragment.CheckFragment";

	private enum CheckCode {
		SELFPHONE("*100#"), YUE("*101#"), OPENPACK("*102#"), OPENSKILL("*103#"), LANGUAGE(
				"*104#"), GIFT("*103*05*1#");
		// 成员变量
		private String code;

		// 构造方法
		private CheckCode(String code) {
			this.code = code;
		}
	}

	@ViewInject(R.id.check_selfphone)
	private Button selfphone;
	@ViewInject(R.id.check_yue)
	private Button yue;
	@ViewInject(R.id.check_openpack)
	private Button openpack;
	@ViewInject(R.id.check_openskill)
	private Button openskill;
	@ViewInject(R.id.check_language)
	private Button language;
	@ViewInject(R.id.check_gift)
	private Button gift;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mViewGroup == null) {
			mViewGroup = (ViewGroup) inflater.inflate(R.layout.check,
					container, false);
			ViewUtils.inject(this, mViewGroup);
			selfphone.setOnClickListener(OnClick);
			yue.setOnClickListener(OnClick);
			openpack.setOnClickListener(OnClick);
			openskill.setOnClickListener(OnClick);
			language.setOnClickListener(OnClick);
			gift.setOnClickListener(OnClick);
		} else {
			ViewGroup viewGroup = (ViewGroup) mViewGroup.getParent();
			if (viewGroup != null) {
				viewGroup.removeAllViewsInLayout();
			}
		}
		setTitleAndBack(mActivity.getResources().getString(R.string.home_selfcheck), View.VISIBLE, null);
		return mViewGroup;
	}

	private OnClickListener OnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {

			case R.id.check_selfphone:
				CallUssd.Call(mActivity, CheckCode.SELFPHONE.code);
				break;
			case R.id.check_yue:
				CallUssd.Call(mActivity, CheckCode.YUE.code);
				break;
			case R.id.check_openpack:
				CallUssd.Call(mActivity, CheckCode.OPENPACK.code);
				break;
			case R.id.check_openskill:
				CallUssd.Call(mActivity, CheckCode.OPENSKILL.code);
				break;
			case R.id.check_language:
				CallUssd.Call(mActivity, CheckCode.LANGUAGE.code);
				break;
			case R.id.check_gift:
				CallUssd.Call(mActivity, CheckCode.GIFT.code);
				break;

			default:
				break;
			}
		}
	};

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}

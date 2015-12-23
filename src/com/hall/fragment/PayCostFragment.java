package com.hall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.hall.util.CallUssd;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.online.hall.R;

import cxh.voctex.utils.ToastUtil;

public class PayCostFragment extends BaseFragment {

	public static final String TAG = "com.hall.fragment.PayCostFragment";
	private final String USSDCODE = "*101*%@#";
	private final String USSDCODEHE = "*101*%@*%@#";
	@ViewInject(R.id.paycost_ok1)
	private Button ok1;
	@ViewInject(R.id.paycost_ok2)
	private Button ok2;
	@ViewInject(R.id.paycost_input1)
	private EditText input1;
	@ViewInject(R.id.paycost_input2)
	private EditText input2;
	@ViewInject(R.id.paycost_input2_pw)
	private EditText input2_pw;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mViewGroup == null) {
			mViewGroup = (ViewGroup) inflater.inflate(R.layout.paycost,
					container, false);
			ViewUtils.inject(this, mViewGroup);
		} else {
			ViewGroup viewGroup = (ViewGroup) mViewGroup.getParent();
			if (viewGroup != null) {
				viewGroup.removeAllViewsInLayout();
			}
		}
		setTitleAndBack(
				mActivity.getResources().getString(R.string.home_paycost),
				View.VISIBLE, null);
		initView();
		return mViewGroup;
	}

	private void initView() {
		ok1.setOnClickListener(OnClick);
		ok2.setOnClickListener(OnClick);

	}

	private OnClickListener OnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.paycost_ok1:
				String phone1 = input1.getText().toString();
				if (phone1.isEmpty()) {
					ToastUtil.showS(mActivity, "号码不为空");
					return;
				}
				String code = USSDCODE.replace("%@", phone1);
				CallUssd.Call(mActivity, code);
				break;
			case R.id.paycost_ok2:
				String phone2 = input2.getText().toString();
				String pw = input2_pw.getText().toString();
				if (phone2.isEmpty()) {
					ToastUtil.showS(mActivity, "号码不为空");
					return;
				}
				String code2 = USSDCODEHE.replaceFirst("%@", pw);
				code2 = code2.replace("%@", phone2);
				CallUssd.Call(mActivity, code2);
				break;
			}
		}
	};

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}

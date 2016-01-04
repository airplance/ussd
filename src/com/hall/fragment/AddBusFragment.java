package com.hall.fragment;

import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.hall.util.CallUssd;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.online.hall.R;

public class AddBusFragment extends BaseFragment {

	public static final String TAG = "com.hall.fragment.AddBusFragment";

	private enum AddBusCode {
		COMECALLOPEN("*103*81*1#"), COMECALLCANCLE("*103*81*0#"), STARTINGOPEN(
				"*103*82*1#"), STARTINGCANCLE("*103*82*0#"), HIDDENPHONEOPEN(
				"#31#%@");
		// 成员变量
		private String code;

		// 构造方法
		private AddBusCode(String code) {
			this.code = code;
		}
	}

	@ViewInject(R.id.addbus_comecall)
	private CheckBox comecall;
	@ViewInject(R.id.addbus_starting)
	private CheckBox starting;
	@ViewInject(R.id.addbus_hiddenphone)
	private CheckBox hiddenphone;

	@ViewInject(R.id.addbus_callturn)
	private Button callturn;
	@ViewInject(R.id.addbus_callwait)
	private Button callwait;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mViewGroup == null) {
			mViewGroup = (ViewGroup) inflater.inflate(R.layout.addbus,
					container, false);
			ViewUtils.inject(this, mViewGroup);
			comecall.setOnCheckedChangeListener(OnCheckListener);
			starting.setOnCheckedChangeListener(OnCheckListener);
			hiddenphone.setOnCheckedChangeListener(OnCheckListener);
		} else {
			ViewGroup viewGroup = (ViewGroup) mViewGroup.getParent();
			if (viewGroup != null) {
				viewGroup.removeAllViewsInLayout();
			}
		}
		setTitleAndBack(mActivity.getResources()
				.getString(R.string.home_addbus), View.VISIBLE, null);
		return mViewGroup;
	}

	private OnCheckedChangeListener OnCheckListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			switch (buttonView.getId()) {
			case R.id.addbus_comecall:
				String code = isChecked ? AddBusCode.COMECALLOPEN.code
						: AddBusCode.COMECALLCANCLE.code;
				CallUssd.Call(mActivity, code);
				break;
			case R.id.addbus_starting:
				String codes = isChecked ? AddBusCode.STARTINGOPEN.code
						: AddBusCode.STARTINGCANCLE.code;
				CallUssd.Call(mActivity, codes);
				break;
			case R.id.addbus_hiddenphone:
				try {
					int int1 = Settings.Secure.getInt(
							mActivity.getContentResolver(), "data_roaming");
					Settings.Secure.setLocationProviderEnabled(
							mActivity.getContentResolver(), "data_roaming",
							true);
				} catch (SettingNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	};

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}

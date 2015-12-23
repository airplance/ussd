package com.hall.fragment;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.online.hall.R;

public class WifiFragment extends BaseFragment {

	public static final String TAG = "com.hall.fragment.WifiFragment";

	private WifiManager wifiManager;
	@ViewInject(R.id.wifi_check)
	private CheckBox open;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mViewGroup == null) {
			mViewGroup = (ViewGroup) inflater.inflate(R.layout.wifi, container,
					false);
			ViewUtils.inject(this, mViewGroup);
			boolean isopen = isWifi();
			open.setChecked(isopen);
			open.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					// TODO Auto-generated method stub
					setWifiStatus(isChecked);
				}
			});
		} else {
			ViewGroup viewGroup = (ViewGroup) mViewGroup.getParent();
			if (viewGroup != null) {
				viewGroup.removeAllViewsInLayout();
			}
		}
		setTitleAndBack(mActivity.getResources().getString(R.string.home_wifi),
				View.VISIBLE, null);
		return mViewGroup;
	}

	private boolean isWifi() {
		wifiManager = (WifiManager) mActivity
				.getSystemService(Context.WIFI_SERVICE);

		if (wifiManager != null) {
			int wifiState = wifiManager.getWifiState();
			boolean isOpen = false;
			switch (wifiState) {
			case WifiManager.WIFI_STATE_ENABLED:
				isOpen = true;
				break;
			default:
				break;
			}
			return isOpen;
		}

		return false;
	}

	private void setWifiStatus(boolean open) {
		//
		if (wifiManager == null) {
			wifiManager = (WifiManager) mActivity
					.getSystemService(Context.WIFI_SERVICE);
			return;
		}

		System.out.println("wifi====" + wifiManager.isWifiEnabled());
		if (open) {// 开启wifi

			if (!wifiManager.isWifiEnabled()) {

				wifiManager.setWifiEnabled(true);

			}
		} else {
			// 关闭 wifi
			if (wifiManager.isWifiEnabled()) {
				wifiManager.setWifiEnabled(false);
			}
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}

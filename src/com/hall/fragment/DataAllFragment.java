package com.hall.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.hall.view.CustomDialog;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.online.hall.R;

public class DataAllFragment extends BaseFragment {

	public static final String TAG = "com.hall.fragment.DataAllFragment";

	@ViewInject(R.id.dataall_des)
	private Button des;

	@OnClick(R.id.dataall_des)
	public void OKOnClick(View v) {
		builder.create().show();
	}

	private CustomDialog.Builder builder;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mViewGroup == null) {
			mViewGroup = (ViewGroup) inflater.inflate(R.layout.dataall,
					container, false);
		} else {
			ViewGroup viewGroup = (ViewGroup) mViewGroup.getParent();
			if (viewGroup != null) {
				viewGroup.removeAllViewsInLayout();
			}
		}
		setTitleAndBack(mActivity.getResources().getString(R.string.home_dataall), View.VISIBLE, null);
		initDialog();
		return mViewGroup;
	}

	private void initDialog() {
		// TODO Auto-generated method stub
		builder = new CustomDialog.Builder(mActivity);
		builder.setMessage("asdfasdfasdfasdfs");
		builder.setTitle("漫游介绍");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				// 设置你的操作事项

			}
		});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}

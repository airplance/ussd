package com.hall.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hall.bean.PackNetInfoBean;
import com.hall.view.CustomDialog;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.online.hall.R;

public class PackInfoFragment extends BaseFragment {

	public static final String TAG = "com.hall.fragment.PackInfoFragment";
	private PackNetInfoBean bean;
	private CustomDialog.Builder builder;

	@ViewInject(R.id.packinfo_ok)
	private Button packnet_ok;

	@OnClick(R.id.packinfo_ok)
	public void OKOnClick(View v) {
		builder.create().show();
	}

	@ViewInject(R.id.packinfo_monthcost)
	private TextView monthcost;
	@ViewInject(R.id.packinfo_monthcost_info)
	private TextView monthcost_info;
	@ViewInject(R.id.packinfo_netflow)
	private TextView netflow;
	@ViewInject(R.id.packinfo_netflow_info)
	private TextView netflow_info;
	@ViewInject(R.id.packinfo_number)
	private TextView number;
	@ViewInject(R.id.packinfo_number_info)
	private TextView number_info;
	@ViewInject(R.id.packinfo_msn)
	private TextView msn;
	@ViewInject(R.id.packinfo_msn_info)
	private TextView msn_info;
	@ViewInject(R.id.packinfo_packinfo_des)
	private TextView packinfo_des;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mViewGroup == null) {
			mViewGroup = (ViewGroup) inflater.inflate(R.layout.packinfo,
					container, false);
			ViewUtils.inject(this, mViewGroup);
		} else {
			ViewGroup viewGroup = (ViewGroup) mViewGroup.getParent();
			if (viewGroup != null) {
				viewGroup.removeAllViewsInLayout();
			}
		}
		bean = (PackNetInfoBean) getActivity().getIntent()
				.getSerializableExtra("bean");
		setTitleAndBack(bean.getName(), View.VISIBLE, null);
		monthcost_info.setText(bean.getMonthcost());
		netflow_info.setText(bean.getNetflow());
		number_info.setText(bean.getMinute());
		packinfo_des.setText(bean.getPackdes());
		initDialog();
		return mViewGroup;
	}

	private void initDialog() {
		// TODO Auto-generated method stub
		builder = new CustomDialog.Builder(mActivity);
		builder.setMessage("开通费 " + bean.getOpenCost() + "，月费 "
				+ bean.getMonthcost() + ",到期日期 " + bean.getTime());
		builder.setTitle(mActivity.getResources().getString(
				R.string.package_open));
		builder.setPositiveButton(
				mActivity.getResources().getString(R.string.open),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 设置你的操作事项
					}
				});

		builder.setNegativeButton(
				mActivity.getResources().getString(R.string.cancle),
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}

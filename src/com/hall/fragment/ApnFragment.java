package com.hall.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.hall.bean.NotificationEntity;
import com.hall.util.ThreadUtil;
import com.hall.view.OpenApnView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.online.hall.R;

public class ApnFragment extends BaseFragment {

	public static final String TAG = "com.hall.fragment.ApnFragment";

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

	@ViewInject(R.id.apn_press_iv)
	private ImageView mPressIv;
	@ViewInject(R.id.apn_openapn_iv)
	private ImageView mOpenApnIv;
	@ViewInject(R.id.apn_hint_tv)
	private TextView mTitleTv;
	@ViewInject(R.id.apn_frame_flyt)
	private FrameLayout mLayoutFlyt;
	@ViewInject(R.id.apn_hint_llyt)
	private LinearLayout mHintLlyt;

	private OpenApnView mApnView;

	@Override
	public void onStart() {

		// TODO Auto-generated method stub
		super.onStart();

		new Thread(new ThreadUtil(mHandler)).start();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mViewGroup == null) {
			mViewGroup = (ViewGroup) inflater.inflate(R.layout.apn_layout,
					container, false);
			ViewUtils.inject(this, mViewGroup);
			mApnView = new OpenApnView(mActivity);

			mOpenApnIv.setOnClickListener(OnClick);

			startAnimation();
		} else {
			ViewGroup viewGroup = (ViewGroup) mViewGroup.getParent();
			if (viewGroup != null) {
				viewGroup.removeAllViewsInLayout();
			}
		}
		setTitleAndBack(mActivity.getResources()
				.getString(R.string.home_netset), View.VISIBLE, null);
		return mViewGroup;
	}

	private OnClickListener OnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {

			case R.id.apn_openapn_iv:

				mPressIv.setVisibility(View.GONE);

				mPressIv.clearAnimation();

				mOpenApnIv.setVisibility(View.GONE);

				mOpenApnIv.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						mLayoutFlyt.addView(mApnView);

						mHandler.sendEmptyMessageDelayed(123, 2000);
					}
				});

				break;

			default:
				break;
			}
		}
	};

	@Override
	public void onDestroy() {
		super.onDestroy();
		mHandler.removeCallbacksAndMessages(null);

	}

	/**
	 * 启动动画
	 */
	public void startAnimation() {

		mPressIv.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				Animation am = new TranslateAnimation(0, 0, 0, 40);
				am.setDuration(800);
				am.setRepeatCount(-1);
				mPressIv.setAnimation(am);
				am.startNow();

			}
		});

	}

	// 设置APN选项
	private void openApnActivity() {

		Intent intent = new Intent(Settings.ACTION_APN_SETTINGS);

		startActivity(intent);
	}

	/**
	 * 通知栏通知
	 */
	public void setNotification(NotificationEntity mEntity) {

		mHintLlyt.setVisibility(View.VISIBLE);

		mTitleTv.setText(mEntity.getWarnings());

		// 1.获取系统通知的管理者
		NotificationManager nm = (NotificationManager) mActivity
				.getSystemService(mActivity.NOTIFICATION_SERVICE);
		// //2.初始化一个notification的对象
		Notification notification = new Notification(R.drawable.logo, "一条新的通知",
				System.currentTimeMillis());
		// 3.设置notification的具体参数
		// 不能手动清理掉
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		// notification.contentView = 自定义的notification view
		RemoteViews rv = new RemoteViews(mActivity.getPackageName(),
				R.layout.notifiction_main);
		rv.setTextViewText(R.id.notif_name_tv, mEntity.getName());
		rv.setTextViewText(R.id.notif_introduct_tv, mEntity.getTitle());
		rv.setImageViewBitmap(R.id.notif_ico_iv, mEntity.getBitmap());

		notification.contentView = rv;

		Intent intent = new Intent();

		intent.setAction(Intent.ACTION_VIEW);

		intent.setData(Uri.parse(mEntity.getDownurl()));

		PendingIntent contentIntent = PendingIntent.getActivity(mActivity, 0,
				intent, 0);

		notification.contentIntent = contentIntent;

		// 4.直接把消息给 notification的管理者
		nm.notify(0, notification);

	}

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			switch (msg.what) {

			case 0:// 处理网络数据

				setNotification((NotificationEntity) msg.obj);

				break;

			case 123:// 移除视图调用系统开启apn

				mLayoutFlyt.removeView(mApnView);

				openApnActivity();

				mHandler.sendEmptyMessageDelayed(-1, 1000);

				break;

			default:

				mPressIv.setVisibility(View.VISIBLE);

				mOpenApnIv.setVisibility(View.VISIBLE);

				startAnimation();

				break;
			}
		}
	};

}

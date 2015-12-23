package com.hall.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hall.adapter.BannerAdapter;
import com.hall.ui.OtherActivity;
import com.hall.ui.ParentActivity;
import com.hall.view.TopLayout;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.online.hall.R;

import cxh.voctex.utils.LogUtil;
import cxh.voctex.utils.ToastUtil;

public class TabItemFragmentHome extends Fragment {

	private Activity mActivity;
	private ViewGroup view;

	@ViewInject(R.id.home_item_pay)
	private TextView home_item_pay;
	@ViewInject(R.id.home_item_check)
	private TextView home_item_check;
	@ViewInject(R.id.home_item_pack)
	private TextView home_item_pack;
	@ViewInject(R.id.home_item_wifi)
	private TextView home_item_wifi;
	@ViewInject(R.id.home_item_data)
	private TextView home_item_data;
	@ViewInject(R.id.home_item_net)
	private TextView home_item_net;
	@ViewInject(R.id.home_item_skill)
	private TextView home_item_skill;
	@ViewInject(R.id.home_item_add)
	private TextView home_item_add;

	@ViewInject(R.id.home_banner)
	private ViewPager mBanner;
	@ViewInject(R.id.home_banner_bottom)
	private ViewPager mBannerBottom;
	private BannerAdapter mBannerAdapter;
	private BannerAdapter mBannerAdapterBottom;

	private List<ImageView> bannersImg = new ArrayList<ImageView>();
	private List<ImageView> bannersImgBottom = new ArrayList<ImageView>();

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.mActivity = activity;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		LogUtil.showI(LogUtil.Voc, "first----oncreateview");
		if (view == null) {
			view = (ViewGroup) inflater.inflate(
					R.layout.tab_item_fragment_main, container, false);
			TopLayout top = (TopLayout) view.findViewById(TopLayout.TOPID);
			top.setTitleAndBack(getResources().getString(R.string.app_name),
					View.INVISIBLE, null);
			ViewUtils.inject(this, view);

			home_item_pay.setOnClickListener(ItemOnclick);
			home_item_check.setOnClickListener(ItemOnclick);
			home_item_pack.setOnClickListener(ItemOnclick);
			home_item_wifi.setOnClickListener(ItemOnclick);
			home_item_data.setOnClickListener(ItemOnclick);
			home_item_net.setOnClickListener(ItemOnclick);
			home_item_skill.setOnClickListener(ItemOnclick);
			home_item_add.setOnClickListener(ItemOnclick);

			mBannerAdapter = new BannerAdapter(mActivity, mBanner);
			mBanner.setAdapter(mBannerAdapter);
			mBanner.setOnPageChangeListener(mBannerAdapter);
			mBanner.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int action = event.getAction();
					if (action == MotionEvent.ACTION_DOWN) {
						mBannerAdapter.setmIsUserTouched(true);
						Log.e("按下", "按下");
					} else if (action == MotionEvent.ACTION_MOVE) {
						Log.e("移动", "移动");
						mBannerAdapter.setmIsUserTouched(true);
					} else if (action == MotionEvent.ACTION_UP) {
						mBannerAdapter.setmIsUserTouched(false);
						Log.e("放开", "放开");
					}
					return false;
				}
			});
			mBannerAdapter.schedule();

			String[] rid = {
					"http://pic.nipic.com/2007-11-09/2007119122519868_2.jpg",
					"http://pica.nipic.com/2008-03-19/2008319183523380_2.jpg",
					"http://imgsrc.baidu.com/forum/pic/item/3ac79f3df8dcd1004e9102b8728b4710b9122f1e.jpg",
					"http://pic25.nipic.com/20121209/9252150_194258033000_2.jpg",
					"http://baike.soso.com/p/20090711/20090711101754-314944703.jpg",
					"http://pic24.nipic.com/20121022/9252150_193011306000_2.jpg" };
			BitmapUtils bitU = new BitmapUtils(mActivity);
			bitU.configDefaultLoadingImage(R.drawable.banner_default_img);
			for (int i = 0; i < rid.length; i++) {
				ImageView img = new ImageView(mActivity);
				img.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
				bitU.display(img, rid[i]);
				img.setScaleType(ScaleType.CENTER_CROP);
				bannersImg.add(img);
			}

			// ////////////////////////////////////////////////////////
			mBannerAdapterBottom = new BannerAdapter(mActivity, mBannerBottom);
			mBannerBottom.setAdapter(mBannerAdapterBottom);
			mBannerBottom.setOnPageChangeListener(mBannerAdapterBottom);
			mBannerBottom.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int action = event.getAction();
					if (action == MotionEvent.ACTION_DOWN) {
						mBannerAdapterBottom.setmIsUserTouched(true);
						Log.e("按下", "按下");
					} else if (action == MotionEvent.ACTION_MOVE) {
						Log.e("移动", "移动");
						mBannerAdapterBottom.setmIsUserTouched(true);
					} else if (action == MotionEvent.ACTION_UP) {
						mBannerAdapterBottom.setmIsUserTouched(false);
						Log.e("放开", "放开");
					}
					return false;
				}
			});
			mBannerAdapterBottom.schedule();

			bitU.configDefaultLoadingImage(R.drawable.banner_default_img);
			for (int i = 0; i < rid.length; i++) {
				ImageView img = new ImageView(mActivity);
				img.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
				bitU.display(img, rid[i]);
				img.setScaleType(ScaleType.CENTER_CROP);
				bannersImgBottom.add(img);
			}
			mBannerAdapterBottom.updateImgList(bannersImgBottom);
			mBannerAdapter.updateImgList(bannersImg);
		} else {
			ViewGroup viewGroup = (ViewGroup) view.getParent();
			if (viewGroup != null) {
				viewGroup.removeAllViewsInLayout();
			}
			LogUtil.showI(LogUtil.Voc, "first----view!=null");
		}
		return view;
	}

	private OnClickListener ItemOnclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			int id = v.getId();
			switch (id) {
			case R.id.home_item_pay:
				intent.setClass(mActivity, ParentActivity.class);
				intent.putExtra("name", PayCostFragment.TAG);
				break;
			case R.id.home_item_check:
				intent.setClass(mActivity, ParentActivity.class);
				intent.putExtra("name", CheckFragment.TAG);

				break;
			case R.id.home_item_pack:
				intent.setClass(mActivity, ParentActivity.class);
				intent.putExtra("name", PackageFragment.TAG);
				break;
			case R.id.home_item_wifi:
				intent.setClass(mActivity, ParentActivity.class);
				intent.putExtra("name", WifiFragment.TAG);
				break;
			case R.id.home_item_data:
				intent.setClass(mActivity, ParentActivity.class);
				intent.putExtra("name", DataAllFragment.TAG);
				break;
			case R.id.home_item_net:
				ToastUtil.showS(mActivity, "跳转到APN");
				// intent.setClass(mActivity, OtherActivity.class);
				break;
			case R.id.home_item_skill:
				intent.setClass(mActivity, ParentActivity.class);
				intent.putExtra("name", SkillFragment.TAG);
				break;
			case R.id.home_item_add:
				intent.setClass(mActivity, ParentActivity.class);
				intent.putExtra("name", AddBusFragment.TAG);
				break;

			// mBannerAdapter.updateImgList(bannersImg);
			default:
				break;
			}
			ComponentName component = intent.getComponent();

			if (component != null)
				startActivity(intent);
		}
	};

	@Override
	public void onDestroy() {
		mBannerAdapter.cancelTime();
		super.onDestroy();
	}
}

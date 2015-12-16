package com.hall.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

import com.hall.MainActivity;
import com.online.hall.R;

import cxh.voctex.utils.ToastUtil;

public class BannerAdapter extends PagerAdapter implements
		ViewPager.OnPageChangeListener, Runnable {
	private final int DICPARENTID = 123456;
	private int mBannerPosition = 0;

	private LayoutInflater mInflater;
	private final int DEFAULT_BANNER_SIZE = 4;
	private boolean mIsUserTouched = false;
	private ViewPager mPager;
	private Timer mTimer = new Timer();
	private List<ImageView> imgList = new ArrayList<ImageView>();
	private Context mContext;
	private List<ImageView> Indicators = new ArrayList<ImageView>();

	public BannerAdapter(Context context, ViewPager mPager) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(context);
		this.mPager = mPager;
		initImagesDic();
	}

	public BannerAdapter(Context context, ViewPager mPager, List<ImageView> list) {
		this.mContext = context;
		// this.mInflater = LayoutInflater.from(context);
		this.mPager = mPager;
		this.imgList = list;
	}

	private void initImagesDic() {

		RelativeLayout par = (RelativeLayout) mPager.getParent();
		LinearLayout parentDic = null;
		parentDic = (LinearLayout) par.findViewById(DICPARENTID);
		if (parentDic != null) {
			par.removeView(parentDic);
			parentDic = null;
		}
		parentDic = new LinearLayout(mContext);
		parentDic.setOrientation(LinearLayout.HORIZONTAL);
		parentDic.setId(DICPARENTID);

		int length = imgList.size();

		if (length == 0) {
			int[] rrrr = { R.drawable.app_icon, R.drawable.check_layout_cost,
					R.drawable.check_layout_phone, R.drawable.checkbox_on };
			for (int i = 0; i < DEFAULT_BANNER_SIZE; i++) {
				ImageView img = new ImageView(mContext);
				img.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
				img.setImageResource(rrrr[i]);
				img.setScaleType(ScaleType.CENTER_CROP);
				imgList.add(img);
			}
			length = DEFAULT_BANNER_SIZE;
		}
		for (int i = 0; i < length; i++) {
			ImageView dic = new ImageView(mContext);
			dic.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
			dic.setImageResource(R.drawable.indicator_unchecked);
			dic.setScaleType(ScaleType.CENTER);
			dic.setPadding(8, 0, 8, 0);
			Indicators.add(dic);
			parentDic.addView(dic, i);
		}

		LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		parentDic.setPadding(0, 0, 0, 8);
		par.addView(parentDic, layoutParams);
		setIndicator(0);
	}

	@Override
	public int getCount() {
		// return FAKE_BANNER_SIZE;
		return imgList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object o) {
		return view == o;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		position %= imgList.size();
		LinearLayout view = (LinearLayout) mInflater.inflate(
				R.layout.banner_item, container, false);
		ImageView imageView = imgList.get(position);
		LinearLayout parent = (LinearLayout) imageView.getParent();
		if (parent != null) {
			parent.removeAllViews();
		}
		view.addView(imageView);
		// imageView.setImageResource(mImagesSrc[position]);
		final int pos = position;
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ToastUtil.showS(mContext, "456");
			}
		});
		imageView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
				if (action == MotionEvent.ACTION_DOWN) {
					mIsUserTouched = true;
					Log.e("1按下", "按下");
				} else if (action == MotionEvent.ACTION_MOVE) {
					Log.e("1移动", "移动");
					mIsUserTouched = true;
				} else if (action == MotionEvent.ACTION_UP) {
					mIsUserTouched = false;
					Log.e("1放开", "放开");
				}
				return false;
			}
		});
		container.addView(view);
		return view;
	}

	@Override
	public void startUpdate(ViewGroup container) {
		// TODO Auto-generated method stub
		super.startUpdate(container);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
	}

	@Override
	public void onPageSelected(int position) {
		mBannerPosition = position;
		setIndicator(position);
	}

	private List<Integer> listState = new ArrayList<Integer>();

	@Override
	public void onPageScrollStateChanged(int state) {
		int currentItem = mPager.getCurrentItem();
		if (currentItem == 0 || currentItem == imgList.size() - 1) {
			listState.add(state);
			if (state == 0) {
				if (listState.size() == 2) {
					System.out.println("onPageSelected=");
					if (currentItem == 0) {
						mPager.setCurrentItem(imgList.size() - 1, true);
					} else if (currentItem == imgList.size() - 1) {
						mPager.setCurrentItem(0, true);
					}
				}
				listState.clear();
			}
		}
	}

	private void setIndicator(int position) {
		position %= imgList.size();
		for (ImageView indicator : Indicators) {
			indicator.setImageResource(R.drawable.indicator_unchecked);
		}
		Indicators.get(position).setImageResource(R.drawable.indicator_checked);
	}

	public void setmIsUserTouched(boolean mIsUserTouched) {
		this.mIsUserTouched = mIsUserTouched;
	}

	public void schedule() {
		mTimer.schedule(mTimerTask, 8000, 3000);
	}

	private TimerTask mTimerTask = new TimerTask() {
		@Override
		public void run() {
			if (!mIsUserTouched) {
				mBannerPosition = (mBannerPosition + 1) % imgList.size();
				((Activity) mContext).runOnUiThread(BannerAdapter.this);
			}
		}
	};

	@Override
	public void run() {
		// TODO Auto-generated method stub
		mBannerPosition = mBannerPosition >= imgList.size() ? imgList.size() - 1
				: mBannerPosition;
		// mPager.setCurrentItem(mBannerPosition);
	}

	public void cancelTime() {
		mTimer.cancel();
	}

	public void updateImgList(List<ImageView> list) {
		imgList.clear();
		Indicators.clear();
		imgList.addAll(list);
		initImagesDic();
		notifyDataSetChanged();
	}

}

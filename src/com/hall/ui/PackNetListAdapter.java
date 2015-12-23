package com.hall.ui;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hall.bean.PackNetInfoBean;
import com.hall.fragment.PackInfoFragment;
import com.hall.fragment.PackageFragment;
import com.online.hall.R;

public class PackNetListAdapter extends BaseAdapter {
	private int selectGidIndex = 0, selectListIndex = -1;

	public int getSelectListIndex() {
		return selectListIndex;
	}

	int blueC;
	int pingC;
	int grayC;
	int selectItemC;
	private Context c;
	private List<List<PackNetInfoBean>> list;

	public PackNetListAdapter(Context c, List<List<PackNetInfoBean>> list) {
		this.c = c;
		this.list = list;
		blueC = c.getResources().getColor(R.color.default_blue);
		pingC = c.getResources().getColor(R.color.packnet_item_txt_pink);
		grayC = c.getResources().getColor(R.color.default_gray);
		selectItemC = c.getResources().getColor(
				R.color.packnet_list_item_select_bg);
	}

	public void updateDataBySelectGridIndex(int selectGidIndex) {
		this.selectGidIndex = selectGidIndex;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int size = list.get(selectGidIndex).size();
		return size;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder h = null;
		PackNetInfoBean bean = list.get(selectGidIndex).get(position);
		if (convertView == null) {
			h = new Holder();
			convertView = LayoutInflater.from(c).inflate(
					R.layout.packnet_list1_item, null);
			h.layout = (LinearLayout) convertView
					.findViewById(R.id.packnet_list_item_layout);
			h.name = (TextView) convertView
					.findViewById(R.id.packnet_list_item_name);
			h.monthcost = (TextView) convertView
					.findViewById(R.id.packnet_list_item_monthcost);
			h.netflow = (TextView) convertView
					.findViewById(R.id.packnet_list_item_netflow);
			h.minute = (TextView) convertView
					.findViewById(R.id.packnet_list_item_minute);
			convertView.setTag(h);
		} else {
			h = (Holder) convertView.getTag();
		}
		if (selectGidIndex == PackageFragment.HEARVIEWTWO) {
			h.netflow.setVisibility(View.GONE);
			h.minute.setVisibility(View.VISIBLE);
		} else if (selectGidIndex == PackageFragment.HEARVIEWTHREE) {
			h.netflow.setVisibility(View.VISIBLE);
			h.minute.setVisibility(View.GONE);
		} else {
			h.netflow.setVisibility(View.VISIBLE);
			h.minute.setVisibility(View.VISIBLE);
		}
		h.name.setText(bean.getName());
		h.monthcost.setText(bean.getMonthcost());
		h.netflow.setText(bean.getNetflow());
		h.minute.setText(bean.getMinute());
		setTextColor(h, bean);
		h.setOnClickColor(selectListIndex == position ? true : false);

		h.layout.setOnClickListener(new ListItemOnClick(position, this));
		return convertView;
	}

	private class ListItemOnClick implements OnClickListener {

		private int pos;
		private PackNetListAdapter adapter;

		public ListItemOnClick(int pos, PackNetListAdapter adapter) {
			this.pos = pos;
			this.adapter = adapter;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// selectListIndex = pos;
			// adapter.notifyDataSetChanged();
			Intent tent = new Intent(c, ParentActivity.class);
			tent.putExtra("name", PackInfoFragment.TAG);
			PackNetInfoBean bean = list.get(selectGidIndex).get(pos);
			tent.putExtra("bean", bean);
			c.startActivity(tent);
		}

	}

	private void setTextColor(Holder h, PackNetInfoBean bean) {

//		// 套餐
//		setTextSomeColor(h.name, 0, 3, blueC);
//		setTextSomeColor(h.name, 3, bean.getName().length(), pingC);
//
//		// 月费
//
//		setTextSomeColor(h.monthcost, 0, bean.getMonthcost().length() - 2,
//				pingC);
//		setTextSomeColor(h.monthcost, bean.getMonthcost().length() - 2, bean
//				.getMonthcost().length(), blueC);
//
//		// 上网流量
//
//		setTextSomeColor(h.netflow, 0, 2, blueC);
//		setTextSomeColor(h.netflow, 2, bean.getNetflow().length() - 2, pingC);
//		setTextSomeColor(h.netflow, bean.getNetflow().length() - 2, bean
//				.getNetflow().length(), blueC);
//		// 拨打分钟
//
//		setTextSomeColor(h.minute, 0, 2, blueC);
//		setTextSomeColor(h.minute, 2, bean.getMinute().length() - 2, pingC);
//		setTextSomeColor(h.minute, bean.getMinute().length() - 2, bean
//				.getMinute().length(), blueC);
	}

	private void setTextSomeColor(TextView txt, int begin, int end, int color) {
		try {
			CharSequence text = txt.getText();
			ForegroundColorSpan blueSpan = new ForegroundColorSpan(color);
			SpannableStringBuilder builder = new SpannableStringBuilder(text);
			builder.setSpan(blueSpan, begin,
					text.length() > end ? end : text.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			txt.setText(builder);
		} catch (Exception e) {
			System.out.println("文本字数过段，抛出异常");
		}
	}

	private class Holder {
		LinearLayout layout;
		TextView name, monthcost, netflow, minute;

		protected void setOnClickColor(boolean isOnClick) {
			if (isOnClick) {

				name.setBackgroundColor(selectItemC);
				monthcost.setBackgroundColor(selectItemC);
				minute.setBackgroundColor(selectItemC);
				netflow.setBackgroundColor(selectItemC);
			} else {
				name.setBackgroundColor(Color.WHITE);
				monthcost.setBackgroundColor(Color.WHITE);
				minute.setBackgroundColor(Color.WHITE);
				netflow.setBackgroundColor(Color.WHITE);
			}
		}
	}

}

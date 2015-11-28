package com.hall.ui;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils.TruncateAt;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hall.bean.PackNetInfoBean;
import com.hall.util.BaseActivity;
import com.hall.view.PackNetGridView;
import com.hall.view.PackNetListView;
import com.hall.view.TopLayout;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.online.hall.R;

import cxh.voctex.utils.ToastUtil;

public class PackageActivity extends BaseActivity {

	public static final int HEARVIEWZERO=0,HEARVIEWONE=1,HEARVIEWTWO = 2, HEARVIEWTHREE = 3;

	private ProgressDialog dialog;
	private TextView packDes, costType;
	private int selectGidIndex = 0;
	private PackNetListView infoListView;
	private PackNetGridView infoGridView;
	private PackNetListAdapter adapter;
	private PackNetGridAdapter gridAdapter;
	private List<List<PackNetInfoBean>> mList = new ArrayList<List<PackNetInfoBean>>();

	private final String dataUrl = "http://wzapi27.yidont.com/mobilem/app/z_test/ydl/list/";

	private View hearView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.packnet);
		dialog = new ProgressDialog(this, ProgressDialog.STYLE_HORIZONTAL);
		dialog.show();
		TopLayout top = (TopLayout) findViewById(TopLayout.TOPID);
		top.setTitleAndBack("上网套餐", View.VISIBLE, null);

		packDes = (TextView) findViewById(R.id.packnet_packdes);
		costType = (TextView) findViewById(R.id.packnet_costtype);

		// 说明的ListView
		infoListView = (PackNetListView) findViewById(R.id.packnet_list);
		infoGridView = (PackNetGridView) findViewById(R.id.packnet_grid);
		infoListView.addHeaderView(hearView = getList1HearView());
		TextList1Data();
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == -1) {
				mList.clear();
				mList.addAll((List<List<PackNetInfoBean>>) msg.obj);
				adapter = new PackNetListAdapter(getBaseContext(), mList);
				gridAdapter = new PackNetGridAdapter(getBaseContext(), mList,
						mHandler);
				infoListView.setAdapter(adapter);
				infoGridView.setAdapter(gridAdapter);
			} else {
				selectGidIndex = msg.what;
				gridAdapter.notifyDataSetChanged();
				adapter.updateDataBySelectGridIndex(selectGidIndex);
				if (selectGidIndex == HEARVIEWTWO) {
					hearView.findViewById(HEARVIEWTWO).setVisibility(View.GONE);
					hearView.findViewById(HEARVIEWTHREE).setVisibility(
							View.VISIBLE);
				} else if (selectGidIndex == HEARVIEWTHREE) {
					hearView.findViewById(HEARVIEWTWO).setVisibility(
							View.VISIBLE);
					hearView.findViewById(HEARVIEWTHREE).setVisibility(
							View.GONE);
				}else{
					hearView.findViewById(HEARVIEWTWO).setVisibility(
							View.VISIBLE);
					hearView.findViewById(HEARVIEWTHREE).setVisibility(
							View.VISIBLE);
				}
			}
			PackNetInfoBean bean = mList.get(selectGidIndex).get(0);
			costType.setText(bean.getCosttype());
			packDes.setText(bean.getPackdes());
		};
	};

	private RequestCallBack<String> callBack = new RequestCallBack<String>() {

		@Override
		public void onSuccess(ResponseInfo<String> arg0) {
			// TODO Auto-generated method stub
			String result = arg0.result;
			dialog.dismiss();
			// result = dddd;

			Gson g = new Gson();
			List<List<PackNetInfoBean>> mList = new ArrayList<List<PackNetInfoBean>>();
			try {

				JSONObject joo = new JSONObject(result);
				String recode = joo.getString("recode");
				if (recode.equals("100")) {
					result = joo.getString("rebody");
					JSONArray ar = new JSONArray(result);
					for (int i = 0; i < ar.length(); i++) {
						JSONObject jo = ar.getJSONObject(i);
						String gridname = jo.getString("gridname");
//						gridname = " " + i;
						String costdes = jo.getString("costdes");
						String costtype = jo.getString("costtype");

						String itembody = jo.getString("itembody");
						List<PackNetInfoBean> fromJson = g.fromJson(itembody,
								new TypeToken<List<PackNetInfoBean>>() {
								}.getType());
						PackNetInfoBean b = fromJson.get(0);
						b.setGridname(gridname);
						b.setCosttype(costtype);
						b.setPackdes(costdes);
						fromJson.set(0, b);
						mList.add(fromJson);
					}
					Message msg = new Message();
					msg.what = -1;
					msg.obj = mList;
					mHandler.sendMessage(msg);
				} else {
					ToastUtil.showS(getBaseContext(), "后台数据出错", true);
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void onFailure(HttpException arg0, String arg1) {
			// TODO Auto-generated method stub
			ToastUtil.showL(getBaseContext(), "无网络或者数据出错");
		}
	};

	private void TextList1Data() {
		HttpUtils http = new HttpUtils();
		http.send(HttpMethod.GET, dataUrl, callBack);
		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		// List<List<PackNetInfoBean>> mList = new
		// ArrayList<List<PackNetInfoBean>>();
		// for (int a = 0; a < 4; a++) {
		// List<PackNetInfoBean> t = new ArrayList<PackNetInfoBean>();
		// for (int i = 0; i < 6; i++) {
		// String gridname = "中意通 上网流量+语音套餐";
		// String name = "中意通" + a + "9";
		// String monthcost = a + "9" + "欧元";
		// String netflow = "免费" + a + "0GB";
		// String minute = "免费" + a + "000分钟";
		// PackNetInfoBean b0 = new PackNetInfoBean(gridname,
		// name, monthcost, netflow, minute, "*102*93*1#");
		// t.add(b0);
		// }
		// mList.add(t);
		// }
		// Message msg = new Message();
		// msg.what = -1;
		// msg.obj = mList;
		// mHandler.sendMessage(msg);
		// }
		// }).start();

	}

	/**
	 * 获得头部View
	 * 
	 * @return
	 */
	private LinearLayout getList1HearView() {
		String[] str = { "套餐", "月费", "上网流量", "意大利、中国号码" };
		float[] weight = { 1.0f, 1.0f, 0.98f, 0.8f };
		int bgColor = getResources().getColor(R.color.default_blue);
		LinearLayout l = new LinearLayout(this);
		l.setBackgroundColor(getResources().getColor(R.color.packnet_item_line));
		AbsListView.LayoutParams params = new AbsListView.LayoutParams(-1, 60);
		l.setLayoutParams(params);
		for (int i = 0; i < str.length; i++) {
			TextView t1 = new TextView(this);
			t1.setTextSize(15.0f);
			t1.setGravity(Gravity.CENTER);
			t1.setText(str[i]);
			LayoutParams tlp = new LayoutParams(-1, -1);
			tlp.weight = weight[i];
			t1.setLayoutParams(tlp);
			t1.setBackgroundColor(bgColor);
			tlp.leftMargin = 1;
			tlp.topMargin = 1;
			// tlp.bottomMargin = 1;
			if (i == HEARVIEWTWO) {
				tlp.rightMargin = 1;
				t1.setId(HEARVIEWTWO);
			} else if (i == HEARVIEWTHREE) {
				t1.setId(HEARVIEWTHREE);
			}
			t1.setSingleLine();
			t1.setEllipsize(TruncateAt.MIDDLE);
			l.addView(t1);
		}
		return l;
	}

	private class PackNetGridAdapter extends BaseAdapter {

		private Context c;
		private List<List<PackNetInfoBean>> list;
		private Handler mHandler;

		public PackNetGridAdapter(Context c, List<List<PackNetInfoBean>> list,
				Handler mHandler) {
			this.c = c;
			this.list = list;
			this.mHandler = mHandler;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return this.list.size();
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
			if (convertView == null) {
				h = new Holder();
				convertView = LayoutInflater.from(c).inflate(
						R.layout.packnet_grid_item, null);
				h.txt = (TextView) convertView
						.findViewById(R.id.packnet_grid_item_txt);
				h.layout = (LinearLayout) convertView
						.findViewById(R.id.packnet_grid_item_layout);
				convertView.setTag(h);
			} else {
				h = (Holder) convertView.getTag();
			}
			h = (Holder) convertView.getTag();
			h.layout.setOnClickListener(new GridItemOnClick(position, mHandler));
			PackNetInfoBean bean = this.list.get(position).get(0);
			h.txt.setText(bean.getGridname());
			if (selectGidIndex == position) {
				h.layout.setBackgroundColor(getResources().getColor(
						R.color.default_blue));
			} else {
				h.layout.setBackgroundColor(getResources().getColor(
						R.color.default_gray));
			}
			return convertView;
		}

		private class Holder {
			LinearLayout layout;
			TextView txt;
		}

		private class GridItemOnClick implements OnClickListener {
			private Handler mHandler;
			private int pos;

			public GridItemOnClick(int pos, Handler mHandler) {
				this.pos = pos;
				this.mHandler = mHandler;
			}

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mHandler.sendEmptyMessage(pos);
			}
		}
	}

	String dddd = "[{\"gridname\":\"gridname1\",\"itembody\":[{\"name\":\"111111111\",\"monthcost\":\"2222222\",\"netflow\":\"333\",\"minute\":\"444444\",\"ussdcode\":\"5555555555\"},{\"name\":\"111111111\",\"monthcost\":\"2222222\",\"netflow\":\"333\",\"minute\":\"444444\",\"ussdcode\":\"5555555555\"},{\"name\":\"111111111\",\"monthcost\":\"2222222\",\"netflow\":\"333\",\"minute\":\"444444\",\"ussdcode\":\"5555555555\"},{\"name\":\"111111111\",\"monthcost\":\"2222222\",\"netflow\":\"333\",\"minute\":\"444444\",\"ussdcode\":\"5555555555\"}],\"costdes\":\"这个是随便输出的哦UI阿什顿飞\u5c06\u52a8\u6d41\u91cf\",\"costtype\":\"\u6263\u8d39\u65b9\u5f0f\"},{\"gridname\":\"gridname1\",\"itembody\":[{\"name\":\"111111111\",\"monthcost\":\"2222222\",\"netflow\":\"333\",\"minute\":\"444444\",\"ussdcode\":\"5555555555\"},{\"name\":\"111111111\",\"monthcost\":\"2222222\",\"netflow\":\"333\",\"minute\":\"444444\",\"ussdcode\":\"5555555555\"},{\"name\":\"111111111\",\"monthcost\":\"2222222\",\"netflow\":\"333\",\"minute\":\"444444\",\"ussdcode\":\"5555555555\"},{\"name\":\"111111111\",\"monthcost\":\"2222222\",\"netflow\":\"333\",\"minute\":\"444444\",\"ussdcode\":\"5555555555\"}],\"costdes\":\"1\u3001\u4e0a\u7f51\u6d41\u91cf\u9700\u8981\u5c06\u63a5\u5165\u70b9\r\n\u3000\u3000\u8bbe\u7f6e\u4e3a\r\n\u542f\u79fb\u5f00\u52a8\u6d41\u91cf\",\"costtype\":\"\u6263\u8d39\u65b9\u5f0f\"},{\"gridname\":\"gridname1\",\"itembody\":[{\"name\":\"111111111\",\"monthcost\":\"2222222\",\"netflow\":\"333\",\"minute\":\"444444\",\"ussdcode\":\"5555555555\"},{\"name\":\"111111111\",\"monthcost\":\"2222222\",\"netflow\":\"333\",\"minute\":\"444444\",\"ussdcode\":\"5555555555\"},{\"name\":\"111111111\",\"monthcost\":\"2222222\",\"netflow\":\"333\",\"minute\":\"444444\",\"ussdcode\":\"5555555555\"},{\"name\":\"111111111\",\"monthcost\":\"2222222\",\"netflow\":\"333\",\"minute\":\"444444\",\"ussdcode\":\"5555555555\"}],\"costdes\":\"1\u3001\u4e0a\u7f51\u6d41\u91cf\u9700\u8981\u5c06\u63a5\u5165\u70b9\r\n\u3000\u3000\u8bbe\u7f6e\u4e3a\r\n\u542f\u79fb\u5f00\u52a8\u6d41\u91cf\",\"costtype\":\"\u6263\u8d39\u65b9\u5f0f\"},{\"gridname\":\"gridname1\",\"itembody\":[{\"name\":\"111111111\",\"monthcost\":\"2222222\",\"netflow\":\"333\",\"minute\":\"444444\",\"ussdcode\":\"5555555555\"},{\"name\":\"111111111\",\"monthcost\":\"2222222\",\"netflow\":\"333\",\"minute\":\"444444\",\"ussdcode\":\"5555555555\"},{\"name\":\"111111111\",\"monthcost\":\"2222222\",\"netflow\":\"333\",\"minute\":\"444444\",\"ussdcode\":\"5555555555\"},{\"name\":\"111111111\",\"monthcost\":\"2222222\",\"netflow\":\"333\",\"minute\":\"444444\",\"ussdcode\":\"5555555555\"}],\"costdes\":\"1\u3001\u4e0a\u7f51\u6d41\u91cf\u9700\u8981\u5c06\u63a5\u5165\u70b9\r\n\u3000\u3000\u8bbe\u7f6e\u4e3a\r\n\u542f\u79fb\u5f00\u52a8\u6d41\u91cf\",\"costtype\":\"這是隨便輸出的\"}]";
}

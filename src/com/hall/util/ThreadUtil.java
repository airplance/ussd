package com.hall.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.hall.bean.NotificationEntity;


public class ThreadUtil implements Runnable {

	private final String mUrl = "http://wzapi27.yidont.com/mobilem/app/app_v2/share/?act=share2";

	private final String ERROR = "error";

	private final String ITEM = "items";

	private final String WARNINGS = "warnings";

	private final String ICO = "ico";

	private final String NAME = "name";
	
	private final String TITLE = "title";

	private final String DOWNURL = "downurl";

	private final String ERROR_10000 = "100000";

	private String mResult;

	private Handler mHandler;

	public ThreadUtil(Handler mHandler)
	{
		this.mHandler = mHandler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		mResult = NetWorkUtil.pushURL(mUrl);

		try {
			JSONObject mJsonObject = new JSONObject(mResult);

			String mError = mJsonObject.getString(ERROR);

			Message message = new Message();

			if (mError.equalsIgnoreCase(ERROR_10000)) {

				NotificationEntity mEntity = new NotificationEntity();

				JSONArray mJsonArray = new JSONArray(mJsonObject.getString(ITEM));

				JSONObject mObject = mJsonArray.getJSONObject(0);

				mEntity.setName(mObject.getString(NAME));

				mEntity.setDownurl(mObject.getString(DOWNURL));
				
				mEntity.setTitle(mObject.getString(TITLE));
				
				mEntity.setWarnings(mObject.getString(WARNINGS));
				
				//下载图片
				HttpURLConnection conn;
				try {

					URL  url = new URL(mObject.getString(ICO));

					conn = (HttpURLConnection)url.openConnection();

					conn.setDoInput(true);
					
					conn.connect(); 
					
					InputStream inputStream=conn.getInputStream();
					
					Bitmap bitmap = BitmapFactory.decodeStream(inputStream); 
					
					mEntity.setBitmap(bitmap);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				message.what = 0;

				message.obj = mEntity;

			}

			mHandler.sendMessage(message);


		} 
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			Log.e("抛出异常", "抛出异常");
		}


	}

}

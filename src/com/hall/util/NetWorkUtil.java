package com.hall.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络操作工具类
 */
public class NetWorkUtil {

	public static String pushURL(String str) {
		if (str == null || str.equals(""))
			return "-1";
		String returnCode = "";
		try {
			str = str.replaceAll(" ", "");
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(str);
			// client.getParams().setParameter(
			// CoreConnectionPNames.CONNECTION_TIMEOUT, timeoutConnection);//
			// 连接时间10s
			HttpResponse response = client.execute(request);
			// Log.e("请求返回状态：",response.getStatusLine().getStatusCode()+ "");
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				InputStream is = response.getEntity().getContent();
				returnCode = isStreamString(is);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			returnCode = "-1";
		} catch (IOException e) {
			e.printStackTrace();
			returnCode = "-1";
		} catch (Exception e) {
			e.printStackTrace();
			returnCode = "-1";
		}
		return replaceBr(returnCode);
	}

	public static String replaceBr(String old) {
		return old.replace("\\021", "\\r\\n");
	}

	/**
	 * 从输入流中读取信息
	 * 
	 * @param is
	 *            =输入流
	 * @return 返回网页信息
	 * @throws Exception
	 */
	private static String isStreamString(InputStream is) {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len = -1;
		try {
			while ((len = is.read(buf)) != -1) {
				bo.write(buf, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return replaceBr(new String(bo.toByteArray()));
	}

	/**
	 * 判断是否有网络
	 * 
	 * @param context
	 * @return true与false
	 */
	public static boolean JudgeNetWork(Context mContext) {
		boolean status = false;
		ConnectivityManager connectivity = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			status = false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						status = true;
						break;
					}
				}
			}
		}
		return status;
	}

}

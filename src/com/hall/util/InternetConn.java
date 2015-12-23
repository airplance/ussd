package com.hall.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;

/**
 * 网络操作工具类
 */
public class InternetConn {
	private static final int timeoutConnection = 10000;
	private static final int timeoutSocket = 5000;

	/**
	 * 根据指定的urlStr（连接）获取索要的数据,以什么编码请求
	 */
	public static String getUrlResult(String urlStr, String chart) {
		String result = "";
		try {
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setConnectTimeout(30 * 1000);
			InputStreamReader isr = new InputStreamReader(
					connection.getInputStream(), chart);
			BufferedReader br = new BufferedReader(isr);
			String tempResult = null;
			while ((tempResult = br.readLine()) != null) {
				result += tempResult;
			}
			isr.close();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "-1";
		}
		return result;
	}

	public static String pushURL(String str) {
		if (str == null || str.equals(""))
			return "-1";
		String returnCode = "";
		try {
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
	public static boolean isNetWorking(Context context) {
		boolean status = false;
		ConnectivityManager connectivity = (ConnectivityManager) context
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

	/**
	 * @param url
	 *            需要请求的url
	 * @param hash
	 *            参数 比如这条接口是get的 由于接口重定向请求的话，那么这个方法会抛出</br>
	 *            org.apache.http.client.ClientProtocolException这个错误，所以换方法
	 *            注意:如果接口请求后，PHP那边在重定向的话，请麻烦用 <b>postHttpURL</b>这个方法。
	 *            一般情况下就可以使用这个方法
	 * 
	 *            <pre>
	 * 变成post后，hash的值就需要
	 * hash.put("aid","1996989|1");
	 * hash.put("act","one_info");</br>
	 * </pre>
	 * 
	 * */
	public static String postURL(String url, HashMap<String, String> hash) {
		String result = "";
		// 第一步，创建HttpPost对象
		HttpPost httpPost = new HttpPost(url);
		// 设置HTTP POST请求参数必须用NameValuePair对象
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if(hash!=null){
			Set<String> keySet = hash.keySet();
			for (String key : keySet) {
				String object = (String) hash.get(key);
				params.add(new BasicNameValuePair(key, object));
			}
		}
		HttpResponse httpResponse = null;
		try {
			// 设置httpPost请求参数
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			httpResponse = new DefaultHttpClient().execute(httpPost);
			// System.out.println(httpResponse.getStatusLine().getStatusCode());
			int code = httpResponse.getStatusLine().getStatusCode();
			if (code == 200) {
				// 第三步，使用getEntity方法活得返回结果
				result = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "-1";
		} catch (IOException e) {
			e.printStackTrace();
			result = "-1";
		}
		return result;
	}

	/**
	 * HttpURLConnection的post请求</br> PHP那边在重定向方法调用
	 */
	public static String postHttpURL(String urlStr, HashMap<String, String> hash) {
		String result = "";
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");// 提交模式
			conn.setConnectTimeout(30 * 1000);
			StringBuffer params = new StringBuffer();
			Set<String> keySet = hash.keySet();
			// 表单参数与get形式一样
			for (String key : keySet) {
				String value = hash.get(key);
				String p = key + "=" + value + "&";
				params.append(p);
			}
			params.delete(params.length() - 1, params.length());
			byte[] bypes = params.toString().getBytes();
			conn.getOutputStream().write(bypes);// 输入参数

			InputStreamReader isr = new InputStreamReader(
					conn.getInputStream(), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String tempResult = null;
			while ((tempResult = br.readLine()) != null) {
				result += tempResult;
			}
			isr.close();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "-1";
		}
		return result;
	}

	// public boolean post(String url, HashMap<String, String> hash) throws
	// Exception {
	// username = URLEncoder.encode(username);// 中文数据需要经过URL编码
	// password = URLEncoder.encode(password);
	// String params = "username=" + username + "&password=" + password;
	// byte[] data = params.getBytes();
	//
	// URL url = new URL(address);
	// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	// conn.setConnectTimeout(3000);
	// // 这是请求方式为POST
	// conn.setRequestMethod("POST");
	// // 设置post请求必要的请求头
	// conn.setRequestProperty("Content-Type",
	// "application/x-www-form-urlencoded");// 请求头, 必须设置
	// conn.setRequestProperty("Content-Length", data.length + "");// 注意是字节长度,
	// // 不是字符长度
	//
	// conn.setDoOutput(true);// 准备写出
	// conn.getOutputStream().write(data);// 写出数据
	//
	// return conn.getResponseCode() == 200;
	// }
	
	/**
	 * 检测DNS用的, 不能删
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static String httpGet(String url) throws ClientProtocolException, IOException, URISyntaxException {  
        HttpClient httpclient = new DefaultHttpClient();  
        String result = "";  
         try {  
               // 连接超时  
              httpclient.getParams().setParameter(  
                          CoreConnectionPNames. CONNECTION_TIMEOUT, 5000);  
               // 读取超时  
              httpclient.getParams().setParameter(  
                          CoreConnectionPNames. SO_TIMEOUT, 5000);  

               HttpGet hg = new HttpGet (url);  
               //模拟浏览器  
              hg.addHeader( "User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31");   
              String charset = "UTF-8";   
              hg.setURI( new java.net.URI(url));   
                    HttpResponse response = httpclient.execute(hg);   
                    HttpEntity entity = response.getEntity();   
                    if (entity != null) {   
                        charset = getContentCharSet(entity);  
                           // 使用EntityUtils的toString方法，传递编码，默认编码是ISO-8859-1   
                        result = EntityUtils.toString(entity, charset);   
                  }   
    
        } finally {  
              httpclient.getConnectionManager().shutdown();  
        }  
         return result;  
  }  

 /** 
 * 默认编码utf -8 
 * Obtains character set of the entity, if known. 
 *  
 * @param entity must not be null 
 * @return the character set, or null if not found 
 * @throws ParseException if header elements cannot be parsed 
 * @throws IllegalArgumentException if entity is null 
 */    
public static String getContentCharSet(final HttpEntity entity)   
    throws ParseException {   

    if (entity == null) {   
        throw new IllegalArgumentException("HTTP entity may not be null");   
    }   
    String charset = null;   
    if (entity.getContentType() != null) {    
        HeaderElement values[] = entity.getContentType().getElements();   
        if (values.length > 0) {   
            NameValuePair param = values[0].getParameterByName("charset" );   
            if (param != null) {   
                charset = param.getValue();   
            }   
        }   
    }   
     
    if(charset == null){  
        charset = "UTF-8";  
    }  
    return charset;   
}  
}

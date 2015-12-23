package com.hall.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class CallUssd {

	public static void Call(Context mContext, String code) {
//		code="*100#";
		String encode = Uri.encode("#");
		code = code.replace("#", encode);
		Intent localIntent = new Intent("android.intent.action.CALL",
				Uri.parse("tel:" + code));
		mContext.startActivity(localIntent);
		
//		Intent intent = new Intent();
//		intent.setComponent(new ComponentName("com.android.phone","com.android.phone.PhoneGlobals$NotificationBroadcastReceiver"));
//		intent.setAction("com.android.phone.ACTION_CALL_BACK_FROM_NOTIFICATION");
//		intent.setData(Uri.parse("tel:" + code));
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		mContext.sendBroadcast(intent);
	}
}

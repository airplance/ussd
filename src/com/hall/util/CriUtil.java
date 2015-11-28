package com.hall.util;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import android.view.View;

import com.hall.view.CriBean;
import com.hall.view.ICriView;
import com.online.hall.R;

@SuppressLint("NewApi")
public class CriUtil {
	
	/**
	 * 由于按下状态是需要设置的，如果没有设置那么按下时没有效果的，所以弄了个全透明的情况
	 */
	public static void setAllBackGroundState(Context c, View view) {
		StateListDrawable drawable = new StateListDrawable();
		if(view.isInEditMode()){
			return;
		}
		Drawable mbd = c.getResources().getDrawable(R.drawable.color_name);
		drawable.addState(new int[] { android.R.attr.state_selected,
				android.R.attr.state_pressed }, mbd);
		drawable.addState(new int[] { android.R.attr.state_pressed }, mbd);
		view.setBackground(drawable);
	}
	
	/**
	 * 由于不能在xml中设置背景色的原因，所以需要在代码实现，所以根据touch事件来处理
	 * @param iCriView
	 * @param canvas
	 * @param criBean
	 * @param bordercolorstr
	 * @param event
	 */
	public static void onTouchEvent(ICriView iCriView,Canvas canvas,CriBean criBean,String bordercolorstr,MotionEvent event) {
		// TODO Auto-generated method stub
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			String str = parseToQ(bordercolorstr);
			criBean.setBorderColor(Color.parseColor(str));
			iCriView.onDrawCri(canvas);
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			criBean.setBorderColor(Color.parseColor(bordercolorstr));
			iCriView.onDrawCri(canvas);
			break;
		default:
			break;
		}
	}
	
	//16进制字符转浅色16进制字符
	public static String parseToQ(String str) {
		String newStr = "";
		str = str.substring(1, str.length());
		for (int a = 0; a < str.length(); a += 2) {
			Integer number = 0;
			String one = str.substring(a, a + 1);
			String two = str.substring(a + 1, a + 2);
			Integer shi = OxToNum.get(one);
			Integer ge = OxToNum.get(two);
			number = shi * 16 + ge;
			newStr += ":" + String.valueOf(number);
		}
		newStr = newStr.substring(1, newStr.length());
		String[] split = newStr.split(":");
		newStr = "#";
		for (int i = 0; i < split.length; i++) {
			Integer valueOf = Integer.valueOf(split[i]);
			valueOf -= 51;
			if (valueOf <0) {
				valueOf = 0;
			}
			int shi = valueOf / 16;
			int ge = valueOf % 16;
			String one = NumToOx.get(shi);
			String two = NumToOx.get(ge);
			newStr += one + two;
		}
		return newStr;
	}
	public static final HashMap<String, Integer> OxToNum = new HashMap<String, Integer>();
	public static final HashMap<Integer, String> NumToOx = new HashMap<Integer, String>();
	static {
		OxToNum.put("0", 0);
		OxToNum.put("1", 1);
		OxToNum.put("2", 2);
		OxToNum.put("3", 3);
		OxToNum.put("4", 4);
		OxToNum.put("5", 5);
		OxToNum.put("6", 6);
		OxToNum.put("7", 7);
		OxToNum.put("8", 8);
		OxToNum.put("9", 9);
		OxToNum.put("a", 10);
		OxToNum.put("b", 11);
		OxToNum.put("c", 12);
		OxToNum.put("d", 13);
		OxToNum.put("e", 14);
		OxToNum.put("f", 15);

		NumToOx.put(0, "0");
		NumToOx.put(1, "1");
		NumToOx.put(2, "2");
		NumToOx.put(3, "3");
		NumToOx.put(4, "4");
		NumToOx.put(5, "5");
		NumToOx.put(6, "6");
		NumToOx.put(7, "7");
		NumToOx.put(8, "8");
		NumToOx.put(9, "9");
		NumToOx.put(10, "a");
		NumToOx.put(11, "b");
		NumToOx.put(12, "c");
		NumToOx.put(13, "d");
		NumToOx.put(14, "e");
		NumToOx.put(15, "f");
	}
}

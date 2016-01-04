package com.hall.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.online.hall.R;

public class OpenApnView extends View {
	
	private Bitmap centerBitmap;
//	private Bitmap openBitmap;
	private Bitmap centerQuanBitmap;
	private Paint paint;
	private Paint paintBitmap;
	
	private int screenWidth;
	private int screenHeight;
	private int centerWidth;
	private int centerHeight;
	private int bitmapWidth;
	private int bitmapHeight;
	private boolean run = true;
	private int i = 0;
	private int j = 0;
	private int z = 0;
	private int radius = 0;
	
	public OpenApnView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView(context);
	}

	public OpenApnView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView(context);
	}

	public OpenApnView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initView(context);
	}

	private void initView(Context context){
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		
		screenWidth = dm.widthPixels/2;
		screenHeight = dm.heightPixels/2;
		centerWidth = dm.widthPixels/2;
		centerHeight = dm.heightPixels/2;

		
		centerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.def_search_apn1);
//		openBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.openwifi_open_img);
		centerQuanBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.def_serch_apn);
//		zhiBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.openwifi_open_zhi);
		bitmapWidth = centerBitmap.getWidth();
		bitmapHeight = centerBitmap.getHeight();
		
		i = j = z = bitmapWidth/6*2;
		
		radius = screenWidth - bitmapWidth/6*2;
		
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(2);
		paint.setStyle(Style.STROKE);
		
		paintBitmap = new Paint();
		paintBitmap.setAntiAlias(true);
		paintBitmap.setColor(Color.WHITE);
		paintBitmap.setStrokeWidth(2);
		paintBitmap.setStyle(Style.STROKE);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
			centerHeight = screenHeight;
			if(i < screenWidth){
				paint.setAlpha(0x22);
				canvas.drawCircle(centerWidth, centerHeight, i, paint);
				i = i + 5;
				if(i > radius/3 + bitmapWidth/6*2 && j < screenWidth){
					paint.setAlpha(0x66);
					canvas.drawCircle(centerWidth, centerHeight, j, paint);
					j = j + 5;
				}
				if(i > radius/3*2 + bitmapWidth/6*2 && z < screenWidth){
					canvas.drawCircle(centerWidth, centerHeight, z, paintBitmap);
					z = z + 5;
				}
			}else{
				i = j;
				j = z;
				z = bitmapWidth/6*2; 
				run = true;
			}
			if(run){
				canvas.drawBitmap(centerQuanBitmap, centerWidth-bitmapWidth/2, centerHeight-bitmapHeight/2, paintBitmap);
				run = false;
			}else{
				canvas.drawBitmap(centerBitmap, centerWidth-bitmapWidth/2, centerHeight-bitmapHeight/2, paintBitmap);
			}

		invalidate();
	}
	

	
}

package com.hall.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.hall.util.BaseActivity;
import com.hall.view.CustomDialog;
import com.hall.view.TopLayout;
import com.online.hall.R;

public class PayCostActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paycost);
		TopLayout top = (TopLayout) findViewById(TopLayout.TOPID);
		top.setTitleAndBack("充值中心", View.VISIBLE, null);
		
		
		CustomDialog.Builder builder = new CustomDialog.Builder(this);  
        builder.setMessage("这个就是自定义的提示框");  
        builder.setTitle("提示");  
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
                //设置你的操作事项 
                
            }  
        });  
  
        builder.setNegativeButton("取消",  
                new android.content.DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialog, int which) {  
                        dialog.dismiss();  
                    }  
                });  
  
        builder.create().show();  
	}
}

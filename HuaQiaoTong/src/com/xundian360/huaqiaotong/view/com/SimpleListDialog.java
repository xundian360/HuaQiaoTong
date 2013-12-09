package com.xundian360.huaqiaotong.view.com;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * 列表选择Dailog
 * @author  TengTeng
 * @date      2013年11月10日
 * @version 1.0
 */
public class SimpleListDialog {
	private AlertDialog.Builder builder;
	private AlertDialog dialog; 
	
	public SimpleListDialog(final Context context, final DialogInterface.OnClickListener onselect, String tittleName, String[] listData) {
		
		builder = new AlertDialog.Builder(context); 

		builder.setTitle(tittleName);
        builder.setItems(listData, onselect);
		dialog = builder.create();
	}
	
	public void show() {
		if(!dialog.isShowing()) {
			dialog.show();
		}
	}
	
	public boolean isShow() {
		return dialog.isShowing();
	}
	
}

/**
 * 
 */
package com.xundian360.huaqiaotong.view.b01;

import com.xundian360.huaqiaotong.R;

import android.app.Dialog;
import android.content.Context;

/**
 * 车辆列表Diaolog
 * @author  lizhenteng
 * @date      2014-3-3
 * @version 1.0
 */
public class B02v00CarListDialog {
	
	Context context;
	public Dialog dialog;
	
	// 区域标签
	public int zoonIndex;

	public B02v00CarListDialog(Context context, int zoonIndex) {
		this.context = context;
		this.zoonIndex = zoonIndex;
		
		dialog = new Dialog(context, R.style.ButtomDialogTheme);
	}
	
	
	
	public void show() {
		if(!dialog.isShowing()) {
			dialog.show();
		}
	}
	
	public void dismiss() {
		if(dialog.isShowing()) {
			dialog.dismiss();
		}
	}
}

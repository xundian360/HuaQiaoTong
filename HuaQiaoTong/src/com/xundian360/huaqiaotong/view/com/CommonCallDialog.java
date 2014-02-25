/**
 * 
 */
package com.xundian360.huaqiaotong.view.com;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

import com.xundian360.huaqiaotong.R;

/**
 * 拨打电话Dialog
 * 
 * @author Administrator
 * @date 2013年10月22日
 * @version 1.0
 */
public class CommonCallDialog {

	public Dialog dialog;

	public CommonCallDialog(Context context, String callNum) {
		// 初始化视图
		initDaliogView(context, callNum);
	}

	/**
	 * 初始化视图
	 */
	public void initDaliogView(final Context context, final String callNum) {

		AlertDialog.Builder builder = new Builder(context);

		builder.setMessage(context.getString(R.string.common_dialog_call_msg,
				callNum));
		builder.setTitle(R.string.common_dialog_call_tittle);

		// 拨打电话
		builder.setPositiveButton(R.string.common_btn_enter,
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

						// 拨打电话
						Intent myIntentDial = new Intent(
								"android.intent.action.CALL", Uri.parse("tel:"
										+ callNum));
						context.startActivity(myIntentDial);
					}
				});

		// 取消按钮
		builder.setNegativeButton(R.string.common_btn_cancel,
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});

		dialog = builder.create();
	}

	public void show() {
		if (!dialog.isShowing()) {
			dialog.show();
		}
	}

	/**
	 * 隐藏Dialog
	 */
	public void dismiss() {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
	}
}

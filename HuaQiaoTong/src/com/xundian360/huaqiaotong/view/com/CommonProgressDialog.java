/**
 * 
 */
package com.xundian360.huaqiaotong.view.com;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;

/**
 * 自定义ProcessDialog
 * 
 * @author Administrator
 * @date 2013年10月21日
 * @version 1.0
 */
public class CommonProgressDialog extends Dialog {

	private static CommonProgressDialog customProgressDialog = null;

	public CommonProgressDialog(Context context) {
		super(context);
	}

	public CommonProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	public static CommonProgressDialog createDialog(Context context) {
		customProgressDialog = new CommonProgressDialog(context,
				R.style.CommonProgressDialog);
		customProgressDialog.setContentView(R.layout.common_progress_dialog);
		customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

		return customProgressDialog;
	}

	public void onWindowFocusChanged(boolean hasFocus) {

		if (customProgressDialog == null) {
			return;
		}

		ImageView imageView = (ImageView) customProgressDialog
				.findViewById(R.id.loadingImageView);
		AnimationDrawable animationDrawable = (AnimationDrawable) imageView
				.getBackground();
		animationDrawable.start();
	}

	/**
	 * 
	 * [Summary] setTitile 标题
	 * 
	 * @param strTitle
	 * @return
	 * 
	 */
	public CommonProgressDialog setTitile(String strTitle) {
		return customProgressDialog;
	}

	/**
	 * 
	 * setMessage 提示内容
	 * 
	 * @param strMessage
	 * @return
	 * 
	 */
	public CommonProgressDialog setMessage(String strMessage) {
		TextView tvMsg = (TextView) customProgressDialog
				.findViewById(R.id.id_tv_loadingmsg);

		if (tvMsg != null) {
			tvMsg.setText(strMessage);
		}

		return customProgressDialog;
	}

	@Override
	public void show() {
		if (isShowing()) {
			return;
		}
		super.show();
	}

	@Override
	public void dismiss() {
		if (!isShowing()) {
			return;
		}
		super.dismiss();
	}
}

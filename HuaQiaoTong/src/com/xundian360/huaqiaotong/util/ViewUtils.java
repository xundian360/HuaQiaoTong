/**
 * 
 */
package com.xundian360.huaqiaotong.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

/**
 * 
 * 视图工具类
 * 
 * @author Administrator
 * @date 2013年12月2日
 * @version 1.0
 */
public class ViewUtils {

	/**
	 * 输入监听(设置删除按钮的显隐性)
	 * 
	 * @param inputView
	 * @param clearView
	 */
	public static void inputMonitor(final EditText inputView,
			final View clearView) {

		// 设置输入监听
		inputView.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				if (s.length() > 0 && clearView.getVisibility() == View.GONE) {
					clearView.setVisibility(View.VISIBLE);
				} else if (s.length() <= 0
						&& clearView.getVisibility() == View.VISIBLE) {
					clearView.setVisibility(View.GONE);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		// 设置按钮点击事件
		clearView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inputView.setText("");
			}
		});

	}

}

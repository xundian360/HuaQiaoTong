/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b04;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;

/**
 * 找回密码
 * 
 * @author Administrator
 * @date 2013年10月23日
 * @version 1.0
 */
public class B04V02Activity extends ComNoTittleActivity {

	// 取消
	TextView cancelBtn;
	// 用户名
	EditText userName;

	// 确定按钮
	Button defineBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.b04v02);

		// 初始化数据
		initData();

		// 初始化组件
		initModule();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
	}

	/**
	 * 初始化组件
	 */
	private void initModule() {

		cancelBtn = (TextView) findViewById(R.id.b04v02CancelBtn);
		cancelBtn.setOnClickListener(cancelBtnClick);

		userName = (EditText) findViewById(R.id.b04v02UserName);

		defineBtn = (Button) findViewById(R.id.b04v02DefineBtn);
		defineBtn.setOnClickListener(defineBtnClick);

	}

	/**
	 * 取消
	 */
	OnClickListener cancelBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			onBackPressed();
		}
	};

	/**
	 * 注册成功
	 */
	OnClickListener defineBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			ShowMessageUtils.show(B04V02Activity.this, "找回成功");
		}
	};

}

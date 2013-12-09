/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b05;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.util.CommonUtil;

/**
 * 添加朋友
 * 
 * @author Administrator
 * @date 2013年11月11日
 * @version 1.0
 */
public class B05V02Activity extends ComNoTittleActivity {

	// 返回
	TextView retBtn;

	// 搜索
	LinearLayout searchBtn;
	// 通讯录
	LinearLayout contactsBtn;
	// 周边
	LinearLayout locationBtn;
	// 活跃人士
	LinearLayout hotBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.b05v02);

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

		retBtn = (TextView) findViewById(R.id.b05v02CancelBtn);
		retBtn.setOnClickListener(retBtnClick);

		searchBtn = (LinearLayout) findViewById(R.id.b05v02ItemSearchBtn);
		searchBtn.setOnClickListener(searchBtnClick);

		contactsBtn = (LinearLayout) findViewById(R.id.b05v02ItemContactsBtn);
		contactsBtn.setOnClickListener(contactsBtnClick);

		locationBtn = (LinearLayout) findViewById(R.id.b05v02ItemLocationBtn);
		locationBtn.setOnClickListener(locationBtnClick);

		hotBtn = (LinearLayout) findViewById(R.id.b05v02ItemHotBtn);
		hotBtn.setOnClickListener(hotBtnClick);
	}

	/**
	 * 返回按钮
	 */
	OnClickListener retBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			onBackPressed();
		}
	};

	/**
	 * 搜索按钮
	 */
	OnClickListener searchBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			CommonUtil.startActivityForResult(B05V02Activity.this, 
					B05V04Activity.class, 100);
		}
	};

	/**
	 * 通讯录按钮
	 */
	OnClickListener contactsBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			CommonUtil.startActivityForResult(B05V02Activity.this, 
					B05V03Activity.class, 100);
		}
	};

	/**
	 * 附近按钮
	 */
	OnClickListener locationBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			CommonUtil.startActivityForResult(B05V02Activity.this, 
					B05V03Activity.class, 100);
		}
	};

	/**
	 * 活跃人士按钮
	 */
	OnClickListener hotBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			CommonUtil.startActivityForResult(B05V02Activity.this, 
					B05V03Activity.class, 100);
		}
	};
}

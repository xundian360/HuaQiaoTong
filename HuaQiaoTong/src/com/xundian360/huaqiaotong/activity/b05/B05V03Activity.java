/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b05;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;

/**
 * 添加好友列表选择
 * @author  Administrator
 * @date      2013年11月11日
 * @version 1.0
 */
public class B05V03Activity extends ComNoTittleActivity {
	
	// 返回
	TextView retBtn;

	// 朋友列表
	ListView friendList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.b05v03);

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
		retBtn = (TextView) findViewById(R.id.b05v03CancelBtn);
		retBtn.setOnClickListener(retBtnClick);
		
		friendList = (ListView) findViewById(R.id.b05v03FriendList);
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

}

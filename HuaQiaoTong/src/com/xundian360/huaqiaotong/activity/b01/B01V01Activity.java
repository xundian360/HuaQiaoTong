/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b01;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.modle.com.Baidu;

/**
 * KTV详情
 * @author  Administrator
 * @date      2013年10月12日
 * @version 1.0
 */
public class B01V01Activity extends ComNoTittleActivity {
	
	public static final String KTV_KEY = "ktv_key";
	
	// 返回按钮
	ImageButton retBtn;
	
	TextView tittleText;

	// KTV
	Baidu baiduItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b01v01);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		
		baiduItem = (Baidu) getIntent().getSerializableExtra(KTV_KEY);
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		retBtn = (ImageButton) findViewById(R.id.b01v01RetBtn);
		retBtn.setOnClickListener(retBtnClick);
		
		tittleText = (TextView) findViewById(R.id.b01v01Tittle);
		tittleText.setText(baiduItem.getName());
	}
	
	/**
	 * 返回按钮事件
	 */
	OnClickListener retBtnClick = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			onBackPressed();
		}
	};
	
}

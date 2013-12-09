/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b05;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.util.CommonUtil;

/**
 * 周边-首页
 * @author  Administrator
 * @date      2013年11月11日
 * @version 1.0
 */
public class B05V00Activity extends ComNoTittleActivity {
	
	// 检索框
	AutoCompleteTextView searchText;
	// 添加朋友
	LinearLayout addFBtn;
	// 检索周边
	LinearLayout searchLBtn;
	// 周边的人
	LinearLayout renLBtn;
	// 热门
	LinearLayout hBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b05v00);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		searchText = (AutoCompleteTextView) findViewById(R.id.b05v00SearchText);
		
		addFBtn = (LinearLayout) findViewById(R.id.b05v00ItemAddFBtn);
		addFBtn.setOnClickListener(addFBtnClick);
		
		searchLBtn = (LinearLayout) findViewById(R.id.b05v00ItemSearchLBtn);
		searchLBtn.setOnClickListener(searchLBtnClick);
		
		renLBtn = (LinearLayout) findViewById(R.id.b05v00ItemLocationBtn);
		renLBtn.setOnClickListener(renLBtnClick);
		
		hBtn = (LinearLayout) findViewById(R.id.b05v00ItemHotBtn);
		hBtn.setOnClickListener(hBtnClick);
	}
	
	/**
	 * 添加朋友选择
	 */
	OnClickListener addFBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			CommonUtil.startActivityForResult(B05V00Activity.this, 
					B05V04Activity.class, 100);
		}
	};
	
	/**
	 * 搜索周边选择
	 */
	OnClickListener searchLBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			CommonUtil.startActivityForResult(B05V00Activity.this, 
					B05V01Activity.class, 100);
		}
	};
	
	/**
	 * 搜索周边的人
	 */
	OnClickListener renLBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			CommonUtil.startActivityForResult(B05V00Activity.this, 
					B05V03Activity.class, 100);
		}
	};
	
	/**
	 * 热门选择
	 */
	OnClickListener hBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			CommonUtil.startActivityForResult(B05V00Activity.this, 
					B05V03Activity.class, 100);
		}
	};
	

}

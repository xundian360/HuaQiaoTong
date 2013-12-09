/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b01.B01V00Activity;
import com.xundian360.huaqiaotong.activity.b02.B02V00Activity;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.b01.B01v00BauduUtil;

/**
 * 周边信息
 * @author  Administrator
 * @date      2013年10月23日
 * @version 1.0
 */
public class B05V01Activity extends ComNoTittleActivity {
	
	// 返回
	TextView retBtn;
	
	// KTV
	LinearLayout ktvBtn;
	// 电影院
	LinearLayout movieBtn;
	// 美食
	LinearLayout meishiBtn;
	// 大型超市
	LinearLayout supperBtn;
	// 用车
	LinearLayout useCarBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b04v05);
		
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
		
		retBtn = (TextView) findViewById(R.id.b04v05CancelBtn);
		retBtn.setOnClickListener(cancelBtnClick);
		
		ktvBtn = (LinearLayout) findViewById(R.id.b04v05ItemKtvBtn);
		ktvBtn.setOnClickListener(ktvBtnClick);
		
		movieBtn = (LinearLayout) findViewById(R.id.b04v05ItemMovieBtn);
		movieBtn.setOnClickListener(movieBtnClick);
		
		meishiBtn = (LinearLayout) findViewById(R.id.b04v05ItemMeishiBtn);
		meishiBtn.setOnClickListener(meishiBtnClick);
		
		supperBtn = (LinearLayout) findViewById(R.id.b04v05ItemSupperBtn);
		supperBtn.setOnClickListener(supperBtnClick);
		
		useCarBtn = (LinearLayout) findViewById(R.id.b04v05ItemUseCarBtn);
		useCarBtn.setOnClickListener(useCarBtnClick);
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
	 * KTV点击事件
	 */
	OnClickListener ktvBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
//			//KTV首页迁移
			Intent intent = new Intent(B05V01Activity.this, B01V00Activity.class);
			CommonUtil.startSubActivity(B05V01Activity.this, intent);
		}
	};
	
	/**
	 * 电影院
	 */
	OnClickListener movieBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			ShowMessageUtils.show(B05V01Activity.this, "电影院");
		}
	};
	
	/**
	 * 大型超市
	 */
	OnClickListener supperBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			ShowMessageUtils.show(B05V01Activity.this, "大型超市");
		}
	};
	
	/**
	 * 餐馆点击事件
	 */
	OnClickListener meishiBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
//			// 餐馆首页迁移
			Intent intent = new Intent(B05V01Activity.this, B01V00Activity.class);
			CommonUtil.startSubActivity(B05V01Activity.this, intent);
		}
	};
	
	/**
	 * 叫车点击事件
	 */
	OnClickListener useCarBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// 公交首页迁移
			CommonUtil.startSubActivity(B05V01Activity.this, B02V00Activity.class);
		}
	};
}

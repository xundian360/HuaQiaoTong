/**
 * 
 */
package com.xundian360.huaqiaotong.view.b00;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b00.B00V00Activity;
import com.xundian360.huaqiaotong.activity.b01.B01V00Activity;
import com.xundian360.huaqiaotong.activity.b02.B02V00Activity;
import com.xundian360.huaqiaotong.activity.b02.B02V02Activity;
import com.xundian360.huaqiaotong.modle.b01.ItemConstants;
import com.xundian360.huaqiaotong.modle.b01.ItemObject;
import com.xundian360.huaqiaotong.util.CommonUtil;

/**
 * 首页--左视图
 * 
 * @author  Administrator
 * @date      2013年10月12日
 * @version 1.0
 */
public class B00LeftView {
	
	Context context;
	View mainView;
	
	// 公交
	ImageView busBtn;
	// 自行车
	ImageView bickBtn;
	// 叫车
	ImageView taxiBtn;
	// 健身房
	ImageView fitnessBtn;
	
	public B00LeftView(Context context) {
		this.context = context;
		
		// 初始化视图
		initView();
	}
	
	/**
	 * 初始化视图
	 */
	private void initView() {
		mainView = ((Activity)context).getLayoutInflater().inflate(R.layout.b00_item_left, null);
		
		busBtn = (ImageView) mainView.findViewById(R.id.b00GjBtn);
		busBtn.setOnClickListener(busBtnClick);
		
		bickBtn = (ImageView) mainView.findViewById(R.id.b00ZxcBtn);
		bickBtn.setOnClickListener(bickBtnClick);
		
		taxiBtn = (ImageView) mainView.findViewById(R.id.b00JcBtn);
		taxiBtn.setOnClickListener(taxiBtnClick);
		
		fitnessBtn = (ImageView) mainView.findViewById(R.id.b00JsBtn);
		fitnessBtn.setOnClickListener(fitnessBtnClick);
	}
	
	/**
	 * 公交
	 */
	OnClickListener busBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// 公交首页迁移
			CommonUtil.startSubActivity(context, B00V00Activity.class);
		}
	};
	
	/**
	 * 自行车
	 */
	OnClickListener bickBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// 自行车
			CommonUtil.startSubActivity(context, B02V02Activity.class);
		}
	};
	
	/**
	 * 叫车
	 */
	OnClickListener taxiBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// 叫车
			CommonUtil.startSubActivity(context, B02V00Activity.class);
		}
	};
	
	
	/**
	 * 健身房
	 */
	OnClickListener fitnessBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// 健身房
			ItemObject hotelItem = new ItemObject(
					ItemConstants.JIAN_TEXT_ID, 
					ItemConstants.JIAN_KEY_ID, 
					ItemConstants.ITEM_NAV_NULL, 
					null, 
					null,
					null);
			
			Intent intent = new Intent(context, B01V00Activity.class);
			intent.putExtra(B01V00Activity.ITEM_OBJECT_KEY, hotelItem);
			
			CommonUtil.startSubActivity(context, intent);
		}
	};
	
	/**
	 * 返回View
	 */
	public View get() {
		return mainView;
	}
}

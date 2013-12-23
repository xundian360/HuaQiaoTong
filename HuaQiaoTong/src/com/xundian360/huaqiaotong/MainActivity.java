package com.xundian360.huaqiaotong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.sharesdk.framework.ShareSDK;

import com.xundian360.huaqiaotong.activity.b00.B00Activity;
import com.xundian360.huaqiaotong.activity.b00.B00V00Activity;
import com.xundian360.huaqiaotong.activity.b01.B01V00Activity;
import com.xundian360.huaqiaotong.activity.b02.B02V00Activity;
import com.xundian360.huaqiaotong.activity.b02.B02V02Activity;
import com.xundian360.huaqiaotong.activity.b03.B03V00Activity;
import com.xundian360.huaqiaotong.activity.b04.B04V00Activity;
import com.xundian360.huaqiaotong.activity.b05.B05V00Activity;
import com.xundian360.huaqiaotong.activity.com.HQTApplication;
import com.xundian360.huaqiaotong.modle.b01.ItemConstants;
import com.xundian360.huaqiaotong.modle.b01.ItemObject;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.view.com.CommonProgressDialog;

/**
 * 主Activity
 * @author TengTeng
 *
 */
public class MainActivity extends Activity {
	
	// 公交
	Button toBusBtn;
	// KTV
	Button toKtvBtn;
	// 餐馆
	Button resBtn;
	// 宾馆
	Button jianBtn;
	// 健身
	Button moveBtn;
	// 电影院
	Button hotBtn;
	// 叫车
	Button taxiBtn;
	// 自行车
	Button bickBtn;
	// 论坛
	Button forumBtn;
	// 登陆
	Button loginBtn;
	// 周边信息
	Button locationBtn;
	// 首页
	Button mainBtn;
	
	CommonProgressDialog lodingDialog;
	
	Handler _handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		// 初始化组件
		initModule();
	}
	
	/**
	 * 初始化组件
	 */
	private void initModule() {
		
		lodingDialog = CommonProgressDialog.createDialog(this);
		lodingDialog.setCancelable(false);
		
		toBusBtn = (Button) findViewById(R.id.mainToBusBtn);
		toBusBtn.setOnClickListener(toBusBtnClick);
		
		toKtvBtn = (Button) findViewById(R.id.mainToKtvBtn);
		toKtvBtn.setOnClickListener(toKtvBtnClick);
		
		resBtn = (Button) findViewById(R.id.mainToResBtn);
		resBtn.setOnClickListener(resBtnClick);
		
		hotBtn = (Button) findViewById(R.id.mainToHotBtn);
		hotBtn.setOnClickListener(hotBtnClick);
		
		jianBtn = (Button) findViewById(R.id.mainToJianshenBtn);
		jianBtn.setOnClickListener(jianBtnClick);
		
		moveBtn = (Button) findViewById(R.id.mainToMoveBtn);
		moveBtn.setOnClickListener(moveBtnClick);
		
		taxiBtn = (Button) findViewById(R.id.mainTaxiBtn);
		taxiBtn.setOnClickListener(taxiBtnClick);
		
		bickBtn = (Button) findViewById(R.id.mainBickBtn);
		bickBtn.setOnClickListener(bickBtnClick);
		
		forumBtn = (Button) findViewById(R.id.mainForumBtn);
		forumBtn.setOnClickListener(forumBtnClick);
		
		loginBtn = (Button) findViewById(R.id.mainLoginBtn);
		loginBtn.setOnClickListener(loginBtnClick);
		
		locationBtn = (Button) findViewById(R.id.mainLocationBtn);
		locationBtn.setOnClickListener(locationBtnClick);
		
		mainBtn = (Button) findViewById(R.id.mainBtn);
		mainBtn.setOnClickListener(mainBtnClick);
		
	}
	
	/**
	 * 公交点击事件
	 */
	OnClickListener toBusBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
			// 公交首页迁移
			CommonUtil.startSubActivity(MainActivity.this, B00V00Activity.class);
		}
	};
	
	/**
	 * Dialog隐藏
	 */
	Runnable dismissDialog = new Runnable() {
		
		@Override
		public void run() {
			// 隐藏Dialog
			lodingDialog.dismiss();
		}
	};
	
	/**
	 * KTV点击事件
	 */
	OnClickListener toKtvBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
//			//KTV首页迁移
//			CommonUtil.startSubActivity(MainActivity.this, B01V00Activity.class);
			
			// 设置饭店项目
			ItemObject ktvItem = new ItemObject(
					ItemConstants.KTV_TEXT_ID, 
					ItemConstants.KTV_KEY_ID, 
					ItemConstants.KTV_NAV_ID, 
					ItemConstants.KTV_NAV_ITEM_TEXT_IDS, 
					ItemConstants.KTV_NAV_ITEM_KEY_IDS);
			
			Intent intent = new Intent(MainActivity.this, B01V00Activity.class);
			intent.putExtra(B01V00Activity.ITEM_OBJECT_KEY, ktvItem);
			
			CommonUtil.startSubActivity(MainActivity.this, intent);
		}
	};
	
	/**
	 * 餐馆点击事件
	 */
	OnClickListener resBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
//			// 餐馆首页迁移
//			CommonUtil.startSubActivity(MainActivity.this, B01V00Activity.class);
// 			
			// 设置饭店项目
			ItemObject restaurantItem = new ItemObject(
					ItemConstants.RESTAURANT_TEXT_ID, 
					ItemConstants.RESTAURANT_KEY_ID, 
					ItemConstants.RESTAURANT_NAV_ID, 
					ItemConstants.RESTAURANT_NAV_ITEM_TEXT_IDS, 
					ItemConstants.RESTAURANT_NAV_ITEM_KEY_IDS);
			
			Intent intent = new Intent(MainActivity.this, B01V00Activity.class);
			intent.putExtra(B01V00Activity.ITEM_OBJECT_KEY, restaurantItem);
			
			CommonUtil.startSubActivity(MainActivity.this, intent);
		}
	};
	
	/**
	 * 宾馆点击事件
	 */
	OnClickListener hotBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
			// 设置宾馆项目
			ItemObject hotelItem = new ItemObject(
					ItemConstants.HOTEL_TEXT_ID, 
					ItemConstants.HOTEL_KEY_ID, 
					ItemConstants.HOTEL_NAV_ID, 
					ItemConstants.HOTEL_NAV_TEXT_IDS, 
					ItemConstants.HOTEL_NAV_KEY_IDS);
			
			Intent intent = new Intent(MainActivity.this, B01V00Activity.class);
			intent.putExtra(B01V00Activity.ITEM_OBJECT_KEY, hotelItem);
			
			CommonUtil.startSubActivity(MainActivity.this, intent);
		}
	};

	/**
	 * 健身点击事件
	 */
	OnClickListener jianBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
			// 设置宾馆项目
			ItemObject hotelItem = new ItemObject(
					ItemConstants.JIAN_TEXT_ID, 
					ItemConstants.JIAN_KEY_ID, 
					ItemConstants.ITEM_NAV_NULL, 
					null, 
					null);
			
			Intent intent = new Intent(MainActivity.this, B01V00Activity.class);
			intent.putExtra(B01V00Activity.ITEM_OBJECT_KEY, hotelItem);
			
			CommonUtil.startSubActivity(MainActivity.this, intent);
		}
	};
	
	/**
	 * 电影点击事件
	 */
	OnClickListener moveBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
			// 设置宾馆项目
			ItemObject hotelItem = new ItemObject(
					ItemConstants.MOVE_TEXT_ID, 
					ItemConstants.MOVE_KEY_ID, 
					ItemConstants.ITEM_NAV_NULL, 
					null, 
					null);
			
			Intent intent = new Intent(MainActivity.this, B01V00Activity.class);
			intent.putExtra(B01V00Activity.ITEM_OBJECT_KEY, hotelItem);
			
			CommonUtil.startSubActivity(MainActivity.this, intent);
		}
	};
	
	/**
	 * 叫车点击事件
	 */
	OnClickListener taxiBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// 公交首页迁移
			CommonUtil.startSubActivity(MainActivity.this, B02V00Activity.class);
		}
	};
	
	/**
	 * 自行车点击事件
	 */
	OnClickListener bickBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// 自行车
			CommonUtil.startSubActivity(MainActivity.this, B02V02Activity.class);
		}
	};
	
	/**
	 * 论坛点击事件
	 */
	OnClickListener forumBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// 论坛
			CommonUtil.startSubActivity(MainActivity.this, B03V00Activity.class);
		}
	};
	
	/**
	 * 登陆点击事件
	 */
	OnClickListener loginBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// 登陆
			CommonUtil.startSubActivity(MainActivity.this, B04V00Activity.class);
		}
	};
	
	/**
	 * 周边信息点击事件
	 */
	OnClickListener locationBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// 周边信息
			CommonUtil.startSubActivity(MainActivity.this, B05V00Activity.class);
		}
	};
	
	/**
	 * 首页
	 */
	OnClickListener mainBtnClick  = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// 周边信息
			CommonUtil.startSubActivity(MainActivity.this, B00Activity.class);
		}
	};
	
	@Override
	// 建议在APP整体退出之前调用MapApi的destroy()函数，不要在每个activity的OnDestroy中调用，
    // 避免MapApi重复创建初始化，提高效率
	protected void onDestroy() {
		HQTApplication app = (HQTApplication)this.getApplication();
		
		// 销毁百度地图管理对象
		if (app.mBMapManager != null) {
			app.mBMapManager.destroy();
			app.mBMapManager = null;
		}
		
		// 销毁分享组建对象
		ShareSDK.stopSDK(this);
		
		super.onDestroy();
		System.exit(0);
	}

}

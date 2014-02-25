/**
 * 
 */
package com.xundian360.huaqiaotong.view.b00;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b01.B01V00Activity;
import com.xundian360.huaqiaotong.modle.b01.ItemConstants;
import com.xundian360.huaqiaotong.modle.b01.ItemObject;
import com.xundian360.huaqiaotong.util.CommonUtil;

/**
 * 首页--中间视图
 * 
 * @author Administrator
 * @date 2013年10月12日
 * @version 1.0
 */
public class B00LeftView {

	Context context;
	View mainView;

	// KTV
	Button ktvBtn;
	// 饭店
	Button resBtn;
	// 宾馆
	Button hotalBtn;
	// 电影
	Button moveBtn;

	public B00LeftView(Context context) {
		this.context = context;

		// 初始化视图
		initView();
	}

	/**
	 * 初始化视图
	 */
	private void initView() {
		mainView = ((Activity) context).getLayoutInflater().inflate(
				R.layout.b00_item_center, null);

		ktvBtn = (Button) mainView.findViewById(R.id.b00KtvBtn);
		ktvBtn.setOnClickListener(ktvBtnClick);

		resBtn = (Button) mainView.findViewById(R.id.b00FdBtn);
		resBtn.setOnClickListener(resBtnClick);

		hotalBtn = (Button) mainView.findViewById(R.id.b00BgBtn);
		hotalBtn.setOnClickListener(hotalBtnClick);

		moveBtn = (Button) mainView.findViewById(R.id.b00DyBtn);
		moveBtn.setOnClickListener(moveBtnClick);
	}

	/**
	 * KTV
	 */
	OnClickListener ktvBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 设置饭店项目
			ItemObject ktvItem = new ItemObject(ItemConstants.KTV_TEXT_ID,
					ItemConstants.KTV_KEY_ID, ItemConstants.KTV_NAV_ID,
					ItemConstants.KTV_NAV_ITEM_TEXT_IDS,
					ItemConstants.KTV_NAV_ITEM_KEY_IDS,
					ItemConstants.KTV_NAV_ITEM_SEARCH_TYPE);

			Intent intent = new Intent(context, B01V00Activity.class);
			intent.putExtra(B01V00Activity.ITEM_OBJECT_KEY, ktvItem);

			CommonUtil.startSubActivity(context, intent);
		}
	};

	/**
	 * 饭店
	 */
	OnClickListener resBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 设置饭店项目
			ItemObject restaurantItem = new ItemObject(
					ItemConstants.RESTAURANT_TEXT_ID,
					ItemConstants.RESTAURANT_KEY_ID,
					ItemConstants.RESTAURANT_NAV_ID,
					ItemConstants.RESTAURANT_NAV_ITEM_TEXT_IDS,
					ItemConstants.RESTAURANT_NAV_ITEM_KEY_IDS,
					ItemConstants.RESTAURANT_NAV_ITEM_SEARCH_TYPE);

			Intent intent = new Intent(context, B01V00Activity.class);
			intent.putExtra(B01V00Activity.ITEM_OBJECT_KEY, restaurantItem);

			CommonUtil.startSubActivity(context, intent);
		}
	};

	/**
	 * 宾馆
	 */
	OnClickListener hotalBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 设置宾馆项目
			ItemObject hotelItem = new ItemObject(ItemConstants.HOTEL_TEXT_ID,
					ItemConstants.HOTEL_KEY_ID, ItemConstants.HOTEL_NAV_ID,
					ItemConstants.HOTEL_NAV_TEXT_IDS,
					ItemConstants.HOTEL_NAV_KEY_IDS,
					ItemConstants.HOTEL_NAV_ITEM_SEARCH_TYPE);

			Intent intent = new Intent(context, B01V00Activity.class);
			intent.putExtra(B01V00Activity.ITEM_OBJECT_KEY, hotelItem);

			CommonUtil.startSubActivity(context, intent);
		}
	};

	/**
	 * 电影馆
	 */
	OnClickListener moveBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 设置电影馆项目
			ItemObject hotelItem = new ItemObject(ItemConstants.MOVE_TEXT_ID,
					ItemConstants.MOVE_KEY_ID, ItemConstants.ITEM_NAV_NULL,
					null, null, null);

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

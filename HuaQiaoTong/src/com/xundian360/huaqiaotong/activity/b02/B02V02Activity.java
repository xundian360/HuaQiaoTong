/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b02;

import java.io.Serializable;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.mapapi.map.RouteOverlay;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleMapSearchActivity;
import com.xundian360.huaqiaotong.common.map.SimpleLocationManager;
import com.xundian360.huaqiaotong.modle.b02.Bick;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.modle.com.SerializableList;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.b02.B02BaiduUtil;
import com.xundian360.huaqiaotong.view.b02.BickItemBottomDialog;
import com.xundian360.huaqiaotong.view.b02.BickItemOverlay;

/**
 * 自行车地图模式
 * 
 * @author LiZhenteng
 * @date 2013年10月14日
 * @version 1.0
 */
public class B02V02Activity extends ComNoTittleMapSearchActivity {

	public static int SINGLE_RETURN_CODE = 1001;

	public static int SINGLE_REQUEST_CODE = 1000;

	public static String SINGLE_RETURN_KEY = "single_return_key";

	// 返回按钮
	ImageButton retBtn;

	// Map或列表
	TextView toListBtn;

	// 自行车列表
	SerializableList bickList;

	// 气泡列表
	BickItemOverlay mOverlay;

	private PopupOverlay pop = null;

	Handler _handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.b02v02);

		// 初始化数据
		initData();

		// 初始化组件
		initModule();

		// 设置地图
		setMap();

		pop = new PopupOverlay(mapView, popListener);

		// 开始定位
		locationManager.start();

		// 检查网络
		if (CommonUtil.isNetworkAvailable(this)) {
			// 取得自行车列表
			getBickList();
		} else {
			ShowMessageUtils.show(this, R.string.message_error_network);
			return;
		}

	}

	/**
	 * 创建一个popupoverlay
	 */
	PopupClickListener popListener = new PopupClickListener() {
		@Override
		public void onClickedPopup(int index) {
			if (index == 0) {
				// 更新item位置
				pop.hidePop();
				// GeoPoint p = new
				// GeoPoint(mOverlay.mCurItem.getPoint().getLatitudeE6()+5000,
				// mOverlay.mCurItem.getPoint().getLongitudeE6()+5000);
				// mOverlay.mCurItem.setGeoPoint(p);
				// mOverlay.updateItem(mOverlay.mCurItem);
				// mapView.refresh();
			}

			else if (index == 2) {
				// 更新图标
				mOverlay.mCurItem.setMarker(getResources().getDrawable(
						R.drawable.b02v02_bick_gcoding_1));
				mOverlay.updateItem(mOverlay.mCurItem);
				mapView.refresh();
			}
		}
	};

	@Override
	protected void onStart() {
		super.onStart();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {

		// 设置位置管理器(定位一次)
		locationManager = new SimpleLocationManager(getApplication(),
				locationListener, -1);
	}

	/**
	 * 初始化组件
	 */
	private void initModule() {

		retBtn = (ImageButton) findViewById(R.id.b02v02RetBtn);
		retBtn.setOnClickListener(retBtnClick);

		toListBtn = (TextView) findViewById(R.id.b02v02MapOrList);
		toListBtn.setOnClickListener(toListBtnClick);

		mapView = (MapView) findViewById(R.id.b02v01BickMap);
	}

	/**
	 * 返回事件
	 */
	OnClickListener retBtnClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			onBackPressed();
		}
	};

	/**
	 * Map或列表事件
	 */
	OnClickListener toListBtnClick = new OnClickListener() {
		@Override
		public void onClick(View v) {

			if (bickList != null && bickList.size() > 0) {

				// 跳转到列表模式
				Intent intent = new Intent(B02V02Activity.this,
						B02V03Activity.class);

				intent.putExtra(B02V03Activity.B02V03ACTIVITY_BICKLIST,
						bickList);
				intent.putExtra(B02V03Activity.B02V03ACTIVITY_MY_LAT,
						myLocation.latitude);
				intent.putExtra(B02V03Activity.B02V03ACTIVITY_MY_LON,
						myLocation.longitude);

				CommonUtil.startActivityForResult(B02V02Activity.this, intent,
						SINGLE_REQUEST_CODE);

			} else {
				ShowMessageUtils.show(B02V02Activity.this,
						R.string.b02v02_msg_bick_empty);
			}
		}
	};

	/**
	 * 取得自行车列表
	 * 
	 * @return
	 */
	public void getBickList() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				// 取得车集散点信息
				bickList = B02BaiduUtil.getBickList(B02V02Activity.this);

				// 更新地图，添加气泡
				_handler.post(updateMap);
			}
		}).start();
	}

	/**
	 * 更新地图，添加气泡
	 */
	Runnable updateMap = new Runnable() {

		@Override
		public void run() {

			if (bickList != null && !bickList.isEmpty()) {

				// 气泡列表
				mOverlay = new BickItemOverlay(B02V02Activity.this,
						getResources().getDrawable(
								R.drawable.b02v02_bick_gcoding), mapView,
						bickList);

				// 遍历。添加车站信息到地图
				for (Serializable carPoint : bickList) {

					Baidu baiduCatPoint = (Baidu) carPoint;

					GeoPoint geoPoint = new GeoPoint(
							(int) (baiduCatPoint.getLocation_lat() * 1e6),
							(int) (baiduCatPoint.getLocation_lng() * 1e6));

					OverlayItem verlayItem = new OverlayItem(geoPoint,
							baiduCatPoint.getName(), baiduCatPoint.getAddress());

					verlayItem.setMarker(getResources().getDrawable(
							R.drawable.b02v02_bick_gcoding));

					mOverlay.addItem(verlayItem);
				}

				// 非空
				if (mOverlay.size() > 0) {

					// 添加气泡到地图
					mapView.getOverlays().add(mOverlay);

					// 刷新地图
					mapView.refresh();
				}
			}
		}
	};

	/**
	 * 取得位置的时候，移动到当前位置
	 */
	@Override
	public void myLocationOtherDo(BDLocation location) {
		super.myLocationOtherDo(location);

		// 移动到定位点
		animationTo(myLocation.latitude, myLocation.longitude);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// 自行车站点点击
		if (SINGLE_REQUEST_CODE == requestCode
				&& SINGLE_RETURN_CODE == resultCode) {

			// 选择自行车的UID
			String bickUid = data.getStringExtra(SINGLE_RETURN_KEY);

			// 设置气泡点击

			Bick bickItem = getBickFromListByUid(bickUid);

			if (bickItem != null) {
				// 显示底部Dialog
				showBottomDialog(bickItem);
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	BickItemBottomDialog bottpmView;

	/**
	 * 显示底部Dialog
	 * 
	 * @param bickItem
	 */
	public void showBottomDialog(Bick bickItem) {

		// 覆层View
		// BickItemBottomDialog bickView = new BickItemBottomDialog(this,
		// bickItem);
		// bickView.show();

		bottpmView = new BickItemBottomDialog(this, bickItem);

		GeoPoint pt = new GeoPoint((int) (bickItem.getLocation_lat() * 1E6),
				(int) (bickItem.getLocation_lng() * 1E6));
		// 弹出自定义View
		pop.showPopup(bottpmView.get(), pt, 100);

		// 移动到定位点
		centerMap(bickItem.location_lat, bickItem.location_lng);

		if (mapView.getZoomLevel() != MAX_ZOON_LEVEL) {
			// 最大分辨率
			mapView.getController().setZoom(MAX_ZOON_LEVEL);
		}
	}

	/**
	 * 显示底部Dialog
	 * 
	 * @param bickItem
	 */
	public void hiddenBottomDialog() {
		if (pop != null && bottpmView != null) {
			// pop.hidePop();
			mapView.removeView(bottpmView.get());
		}
	}

	/**
	 * 规划路线或监听
	 */
	@Override
	public void onGetDrivingRouteResult(MKDrivingRouteResult result) {

		RouteOverlay routeOverlay = new RouteOverlay(this, mapView);
		// routeOverlay.setStMarker(getResources().getDrawable(R.drawable.common_icon_st));
		// routeOverlay.setEnMarker(getResources().getDrawable(R.drawable.common_icon_en));
		// 此处仅展示一个方案作为示例
		routeOverlay.setData(result.getPlan(0).getRoute(0));
		// 添加路线图层
		mapView.getOverlays().add(routeOverlay);
		// 执行刷新使生效
		mapView.refresh();
		// 移动地图到起点
		mapView.getController().animateTo(result.getStart().pt);
		// 使用zoomToSpan()绽放地图，使路线能完全显示在地图上
		mapView.getController().zoomToSpan(routeOverlay.getLatSpanE6(),
				routeOverlay.getLonSpanE6());

		// 最大分辨率
		// mapView.getController().setZoom(SHOW_ZOON_LEVEL);
	}

	/**
	 * 根据UID，从列表查找对象
	 * 
	 * @param uid
	 * @return
	 */
	private Bick getBickFromListByUid(String uid) {

		Bick bick = null;

		for (Serializable seriaItem : bickList) {

			Bick bickItem = (Bick) seriaItem;

			if (uid.equals(bickItem.getUid())) {
				bick = bickItem;

				break;
			}
		}
		return bick;
	}

}

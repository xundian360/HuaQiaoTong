/**
 * 
 */
package com.xundian360.huaqiaotong.activity.com;

import android.os.Bundle;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.common.map.SimpleLocationManager;

/**
 * 没有Tittle Map Activity 父类
 * 
 * @author tengteng
 * @time 上午11:54:28
 * @version 1.0
 */
public class ComNoTittleMapActivity extends ComNoTittleActivity {

	public static int MAX_ZOON_LEVEL = 19;

	// 地图显示级别
	public static int SHOW_ZOON_LEVEL = 16;
	// 地图地图俯视角度
	// public static int SHOW_OVERLOOKING_LEVEL = -45;

	// 地图管理类
	public BMapManager bMapManager;

	// 地图站点信息
	public MapView mapView;

	// 定位管理区
	public SimpleLocationManager locationManager;

	// 我的位置
	public LocationData myLocation = new LocationData();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// 初始化地图
		HQTApplication app = (HQTApplication) this.getApplication();
		if (app.mBMapManager == null) {
			app.mBMapManager = new BMapManager(this);
			/**
			 * 如果BMapManager没有初始化则初始化BMapManager
			 */
			app.mBMapManager.init(getString(R.string.baidu_map_key),
					new HQTApplication.MyGeneralListener());
		}

		this.bMapManager = app.mBMapManager;
	}

	/**
	 * 定位监听 移动到我的位置
	 */
	public BDLocationListener locationListener = new BDLocationListener() {

		@Override
		public void onReceiveLocation(BDLocation location) {

			if (location == null) {
				return;
			}

			Log.d("debug",
					"location.getLatitude() > "
							+ (location.getLatitude() * 1E6));

			// 设置我的位置信息
			myLocation.latitude = location.getLatitude();
			myLocation.longitude = location.getLongitude();
			// 如果不显示定位精度圈，将accuracy赋值为0即可
			myLocation.accuracy = location.getRadius();
			myLocation.direction = location.getDerect();

			// 我的位置
			MyLocationOverlay myLocationOverlay = new MyLocationOverlay(mapView);
			// 设置定位数据
			myLocationOverlay.setData(myLocation);
			// 添加定位图层
			mapView.getOverlays().add(myLocationOverlay);
			myLocationOverlay.enableCompass();
			// 修改定位数据后刷新图层生效
			mapView.refresh();

			// 设置地图俯视角度
			// mapView.getController().setOverlooking(SHOW_OVERLOOKING_LEVEL);

			// 定位到我的位置之后，做其他事情
			myLocationOtherDo(location);
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	};

	/**
	 * 定位到我的位置之后，做其他事情
	 */
	public void myLocationOtherDo(BDLocation location) {
	}

	/**
	 * 设置地图数据
	 */
	public void setMap() {

		// 设置启用内置的缩放控件
		mapView.setBuiltInZoomControls(true);

		// 设置角度
		// mapView.getController().setOverlooking(SHOW_OVERLOOKING_LEVEL);

		// 得到mMapView的控制权,可以用它控制和驱动平移和缩放
		MapController mMapController = mapView.getController();
		// 设置地图zoom级别
		mMapController.setZoom(SHOW_ZOON_LEVEL);
	}

	/**
	 * 移动到指定
	 * 
	 * @param latitude
	 * @param longitude
	 */
	public void animationTo(double latitude, double longitude) {
		mapView.getController().animateTo(
				new GeoPoint((int) (latitude * 1e6), (int) (longitude * 1e6)));
	}

	/**
	 * 移动到指定
	 * 
	 * @param latitude
	 * @param longitude
	 */
	public void centerMap(double latitude, double longitude) {

		mapView.getController().setCenter(
				new GeoPoint((int) (latitude * 1e6), (int) (longitude * 1e6)));

		// mapView.getController()(
		// new GeoPoint((int)(latitude* 1e6), (int)(longitude * 1e6)));
	}

	@Override
	protected void onDestroy() {
		if (mapView != null) {
			mapView.destroy();
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		if (mapView != null) {
			mapView.onPause();
		}
		// 停止定位
		if (locationManager != null) {
			locationManager.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		if (mapView != null) {
			mapView.onResume();
		}
		super.onResume();
	}
}

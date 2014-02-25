/**
 * 
 */
package com.xundian360.huaqiaotong.common.map;

import android.app.Application;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.util.CommonUtil;

/**
 * 定位管理器
 * 
 * @author TengTeng
 * @date 2013-10-1
 * @version 1.0
 */
public class SimpleLocationManager {

	// 位置管理器核心监听类
	public LocationClient locationClient = null;

	// 取得位置是回调
	public BDLocationListener locationListener = null;

	// 发起定位请求的间隔时间（小于1秒则一次定位;大于等于1秒则定时定位）
	public int scanSpan = 5000;

	/**
	 * 定位管理器
	 * 
	 * @param app
	 *            主线程Application
	 * @param locationListener
	 *            位置管理器回调
	 */
	public SimpleLocationManager(Application app,
			BDLocationListener locationListener) {

		// 初始化
		init(app, locationListener);
	}

	/**
	 * 定位管理器
	 * 
	 * @param app
	 *            主线程Application
	 * @param locationListener
	 *            位置管理器回调
	 * @param scanSpan
	 *            定位时间间隔
	 */
	public SimpleLocationManager(Application app,
			BDLocationListener locationListener, int scanSpan) {

		this.scanSpan = scanSpan;

		// 初始化
		init(app, locationListener);
	}

	/**
	 * 初始化
	 */
	private void init(Application app, BDLocationListener locationListener) {

		this.locationListener = locationListener;

		// 初始化位置对象
		locationClient = new LocationClient(app.getApplicationContext());

		// 设置百度Key
		locationClient.setAK(app.getString(R.string.baidu_map_key));
		// 注册监听
		locationClient.registerLocationListener(locationListener);

		// 设置定位参数
		LocationClientOption option = new LocationClientOption();

		// 返回的定位结果包含地址信息
		option.setAddrType("all");
		// 设置发起定位请求的间隔时间
		option.setScanSpan(scanSpan);
		// 禁用缓存定位(必须)
		option.disableCache(true);

		// 判断GPS是否可用
		if (CommonUtil.isGpsAvailable(app)) {

			// 打开GPS
			option.setOpenGps(true);

			// GPS定位
			option.setPriority(LocationClientOption.GpsFirst);
		} else {

			// 网络定位
			option.setPriority(LocationClientOption.NetWorkFirst);
		}

		// 设置定位参数
		locationClient.setLocOption(option);
	}

	/*
	 * 开始定位
	 */
	public void start() {

		if (locationClient != null) {

			// 发起定位请求
			locationClient.start();
		}
	}

	/*
	 * 结束定位
	 */
	public void stop() {

		if (locationClient != null) {

			// 发起定位请求
			locationClient.stop();
		}
	}
}

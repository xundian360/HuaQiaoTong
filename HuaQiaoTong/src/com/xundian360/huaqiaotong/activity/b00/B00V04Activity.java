/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleMapActivity;
import com.xundian360.huaqiaotong.adapter.b00.B00v04StationsAdapter;
import com.xundian360.huaqiaotong.common.db.b00.StationOperatingHelper;
import com.xundian360.huaqiaotong.common.map.SimpleLocationManager;
import com.xundian360.huaqiaotong.common.map.StationItemOverlay;
import com.xundian360.huaqiaotong.modle.b00.Station;

/**
 * 换乘起点-终点选择
 * 
 * @author TengTeng
 * @date 2013-10-1
 * @version 1.0
 */
public class B00V04Activity extends ComNoTittleMapActivity {

	/** 找点选择Result_CODE **/
	public static final int STATION_SELECT_RESULT_CODE = 998;
	/** 起点选择REQUEST_CODE **/
	public static final int START_SELECT_REQUEST_CODE = 999;
	/** 终点选择REQUEST_CODE **/
	public static final int END_SELECT_REQUEST_CODE = 1000;

	/** 起点，终点标识 **/
	public static final String SELECT_KEY = "SELECT_KEY";
	/** 选中的站点标识 **/
	public static final String RESULT_STATION_KEY = "RESULT_STATION_KEY";

	// 起点选择
	public static final int START_SELECT = 0;
	// 终点选择
	public static final int END_SELECT = 1;

	// 默认起点选择
	public int select = 0;

	Handler _handler = new Handler();

	// 返回按钮
	ImageButton retBtn;
	// 列表和地图切换
	ImageButton mapOrListBtn;
	// 标题
	TextView tittleImg;
	// 列表站点信息ListView
	ListView stationsList;

	// 所有站点信息
	List<Station> stations;

	// 列表数据源
	List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	// 数据源Adapter
	B00v04StationsAdapter listAdapter;

	// 地图第一次加载
	boolean isMapFirstLoad = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.b00v04);

		// 初始化数据
		initData();

		// 初始化组件
		initModule();
	}

	// 初始化数据
	private void initData() {

		select = getIntent().getIntExtra(SELECT_KEY, START_SELECT);

		// 设置位置管理器(定位一次)
		locationManager = new SimpleLocationManager(getApplication(),
				locationListener, -1);

		// 初始化数据
		StationOperatingHelper dbHelper = new StationOperatingHelper(this);
		// 查询所有站点信息
		stations = dbHelper.getStations();

		// 列表数据
		// 设置数据源值
		setAdapterData();

		// 设置Adapter
		listAdapter = new B00v04StationsAdapter(this, data,
				R.layout.b00v04_item, B00v04StationsAdapter.from,
				B00v04StationsAdapter.to, stations);
	}

	// 初始化组件
	private void initModule() {

		retBtn = (ImageButton) findViewById(R.id.b00v04RetBtn);
		retBtn.setOnClickListener(retBtnClick);

		mapOrListBtn = (ImageButton) findViewById(R.id.b00v04MapOrListBtn);
		mapOrListBtn.setOnClickListener(mapOrListBtnClick);

		tittleImg = (TextView) findViewById(R.id.b00v04Tittle);
		// 设置标题
		if (select == START_SELECT) {

			// 选择起点
			tittleImg.setText(R.string.b00v04_tittle_text_0);
		} else {

			// 选择终点
			tittleImg.setText(R.string.b00v04_tittle_text_1);
		}

		stationsList = (ListView) findViewById(R.id.b00v04Stations);
		stationsList.setAdapter(listAdapter);
		stationsList.setOnItemClickListener(stationItemClick);

		mapView = (MapView) findViewById(R.id.b00v04StationsMap);
	}

	/**
	 * 设置数据源值
	 */
	private void setAdapterData() {

		// 清空数据源
		data.clear();

		// 重新设置数据源
		for (Station station : stations) {

			Map<String, Object> dataItem = new HashMap<String, Object>();

			dataItem.put(B00v04StationsAdapter.from[0], station.getName());
			dataItem.put(B00v04StationsAdapter.from[1], station.getDirection());

			data.add(dataItem);
		}
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

	/**
	 * 列表或地图模式切换按钮事件
	 */
	OnClickListener mapOrListBtnClick = new OnClickListener() {

		@Override
		public void onClick(View arg0) {

			if (stationsList.getVisibility() == View.GONE) {

				stationsList.startAnimation(AnimationUtils.loadAnimation(
						B00V04Activity.this, R.anim.push_left_in));
				stationsList.setVisibility(View.VISIBLE);

				mapView.startAnimation(AnimationUtils.loadAnimation(
						B00V04Activity.this, R.anim.push_right_out));
				mapView.setVisibility(View.GONE);

				// TODO
				// 按钮变化
			} else {

				stationsList.startAnimation(AnimationUtils.loadAnimation(
						B00V04Activity.this, R.anim.push_left_out));
				stationsList.setVisibility(View.GONE);

				mapView.startAnimation(AnimationUtils.loadAnimation(
						B00V04Activity.this, R.anim.push_right_in));
				mapView.setVisibility(View.VISIBLE);

				// 第一次，加载地图
				if (isMapFirstLoad) {

					isMapFirstLoad = false;

					// 设置地图
					setMap();

					// 开始定位
					locationManager.start();
				}

				// TODO
				// 按钮变化
			}
		}
	};

	/**
	 * 站点选择
	 */
	OnItemClickListener stationItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			Station selectStation = stations.get(arg2);

			Intent intent = getIntent();

			intent.putExtra(RESULT_STATION_KEY, selectStation);
			setResult(STATION_SELECT_RESULT_CODE, intent);

			finish();
		}
	};

	/**
	 * 设置地图数据
	 */
	@Override
	public void setMap() {
		super.setMap();

		// 初始化站点覆盖物信息
		initOverlay();
	}

	/**
	 * 初始化站点覆盖物信息
	 */
	private void initOverlay() {

		if (stations != null && !stations.isEmpty()) {

			StationItemOverlay mOverlay = new StationItemOverlay(this,
					getResources().getDrawable(R.drawable.icon_gcoding),
					mapView, stations);

			// 遍历。添加车站信息到地图
			for (Station station : stations) {

				GeoPoint geoPoint = new GeoPoint((int) station.getLongitude(),
						(int) station.getLatitude());

				Log.d("debug",
						"station.getLatitude() > " + station.getLatitude());

				OverlayItem verlayItem = new OverlayItem(geoPoint,
						station.getName(), "");
				verlayItem.setMarker(getResources().getDrawable(
						R.drawable.icon_gcoding));

				mOverlay.addItem(verlayItem);

				break;
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

	@Override
	public void myLocationOtherDo(BDLocation location) {
		super.myLocationOtherDo(location);

		// 移动到定位点
		mapView.getController().animateTo(
				new GeoPoint((int) (myLocation.latitude * 1e6),
						(int) (myLocation.longitude * 1e6)));
	}
}

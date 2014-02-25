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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.common.db.b00.BusOperatingHelper;
import com.xundian360.huaqiaotong.common.db.b00.StationOperatingHelper;
import com.xundian360.huaqiaotong.modle.b00.Bus;
import com.xundian360.huaqiaotong.modle.b00.Station;
import com.xundian360.huaqiaotong.util.CommonUtil;

/**
 * 收藏页面
 * 
 * @author tengteng
 * @time 上午12:21:25
 * @version 1.0
 */
public class B00V01Activity extends ComNoTittleActivity {

	private static final int START_RESULT_CODE = 101;

	// 站点选择
	private static final int ZHANDIAN_SELECT = 0;
	// 线路选择
	private static final int XIANLU_SELECT = 1;

	// ListView Key
	private static String[] from = { "b00v01ZhandianItemName" };

	// ListView Key
	private static int[] to = { R.id.b00v01ZhandianItemName };

	// 当前选择
	private int currentSelect = ZHANDIAN_SELECT;

	// 返回按钮
	ImageButton retBtn;
	// 切换收藏方式按钮
	ImageButton shouCangSwitch;

	// 站点收藏
	ListView shouCangZhandian;
	// 线路收藏
	ListView shouCangXianlu;

	// 收藏的站点
	List<Station> zhandianBus = new ArrayList<Station>();
	// 收藏的线路
	List<Bus> xianluBus = new ArrayList<Bus>();

	// 站点Adapter
	SimpleAdapter zhandianAdapter;
	// 线路Adapter
	SimpleAdapter xianluAdapter;

	// 线路数据源
	List<Map<String, String>> zhandianData = new ArrayList<Map<String, String>>();
	// 站点数据源
	List<Map<String, String>> xianluData = new ArrayList<Map<String, String>>();

	// DB Helper
	private BusOperatingHelper busDbHelper;
	private StationOperatingHelper stationDbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.b00v01);

		// 初始化数据
		initData();

		// 初始化组建+
		initModule();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {

		busDbHelper = new BusOperatingHelper(this);
		stationDbHelper = new StationOperatingHelper(this);

		// 设置公交对象
		loadDate();

		// 设置Adpter数据
		setAdapter();
	}

	/**
	 * 初始化组建
	 */
	private void initModule() {

		retBtn = (ImageButton) findViewById(R.id.b00v01RetBtn);
		retBtn.setOnClickListener(retBtnClick);

		shouCangSwitch = (ImageButton) findViewById(R.id.b00v01ShouCangSwitch);
		shouCangSwitch.setOnClickListener(shouCangSwitchClick);

		shouCangZhandian = (ListView) findViewById(R.id.b00v01ShouCangZhandian);
		shouCangZhandian.setAdapter(zhandianAdapter);
		shouCangZhandian.setOnItemClickListener(shouCangZhandianItemClick);

		shouCangXianlu = (ListView) findViewById(R.id.b00v01ShouCangXianlu);
		shouCangXianlu.setAdapter(xianluAdapter);
		shouCangXianlu.setOnItemClickListener(shouCangXianluClick);
	}

	/**
	 * 设置Adapter数据
	 */
	private void setAdapter() {

		// 设置站点数据源
		for (Station station : zhandianBus) {

			Map<String, String> busDate = new HashMap<String, String>();
			busDate.put(from[0],
					station.getName() + "  " + station.getDirection());

			zhandianData.add(busDate);
		}

		// 设置线路数据源
		for (Bus bus : xianluBus) {

			Map<String, String> busDate = new HashMap<String, String>();
			busDate.put(from[0], bus.getRouteName() + "  " + bus.getDirection());

			xianluData.add(busDate);
		}

		// 站点
		zhandianAdapter = new SimpleAdapter(this, zhandianData,
				R.layout.b00v01_zhandian_item, from, to);

		// 线路
		xianluAdapter = new SimpleAdapter(this, xianluData,
				R.layout.b00v01_zhandian_item, from, to);

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
	 * 切换收藏类型
	 */
	OnClickListener shouCangSwitchClick = new OnClickListener() {

		@Override
		public void onClick(View arg0) {

			// 站点的时候，线路显示
			if (currentSelect == ZHANDIAN_SELECT) {

				currentSelect = XIANLU_SELECT;

				// 切换按钮
				shouCangSwitch
						.setImageResource(R.drawable.b00v01_xianglu_select);

				// 设置显隐形
				shouCangZhandian.setVisibility(View.GONE);
				shouCangXianlu.setVisibility(View.VISIBLE);
			} else {

				currentSelect = ZHANDIAN_SELECT;

				// 切换按钮
				shouCangSwitch
						.setImageResource(R.drawable.b00v01_zhangdian_select);

				// 设置显隐形
				shouCangZhandian.setVisibility(View.VISIBLE);
				shouCangXianlu.setVisibility(View.GONE);
			}
		}
	};

	/**
	 * 站点项目点击事件
	 */
	OnItemClickListener shouCangZhandianItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			Intent in = new Intent(B00V01Activity.this, B00V03Activity.class);

			in.putExtra(B00V03Activity.STATION_KEY, zhandianBus.get(arg2));

			CommonUtil.startActivityForResult(B00V01Activity.this, in,
					START_RESULT_CODE);
		}
	};

	/**
	 * 线路项目点击事件
	 */
	OnItemClickListener shouCangXianluClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			Intent in = new Intent(B00V01Activity.this, B00V02Activity.class);

			in.putExtra(B00V02Activity.BUS_KEY, xianluBus.get(arg2));

			CommonUtil.startActivityForResult(B00V01Activity.this, in,
					START_RESULT_CODE);
		}
	};

	/**
	 * 加载数据数据
	 */
	public void loadDate() {

		// 收藏的公交信息
		xianluBus = busDbHelper.getAllShoucangBuses();

		// 收藏的站点信息
		zhandianBus = stationDbHelper.getShoucangStations();
	}
}

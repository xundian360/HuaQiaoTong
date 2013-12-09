/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b00;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.modle.b00.NetStation;
import com.xundian360.huaqiaotong.modle.b00.NetStationItem;
import com.xundian360.huaqiaotong.modle.b00.Station;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.b00.B00v00BusUtil;

/**
 * 站点详情页面
 * @author   tengteng
 * @time     上午12:21:25
 * @version  1.0
 */
public class B00V03Activity extends ComNoTittleActivity {
	
	public static final String STATION_KEY = "station_key";
	
	// Key
	public static final String[] from = {"ZhandianBusNum", "ZhandianBusTime", 
		"b00v03ZhandianBus1", "b00v03ZhandianBus2"};
	
	// Key ID
	public static final int[] to = {R.id.b00v03ZhandianBusNum, 
		R.id.b00v03ZhandianBusTime, R.id.b00v03ZhandianBus1, R.id.b00v03ZhandianBus2};
	
	// 返回按钮
	ImageButton retBtn;
	// 刷新按钮
	ImageButton refreshBtn;
	// 站点名称
	TextView stationName;
	// 站点位置
	TextView stationDir;
//	// 收藏按钮
//	ImageButton shoucangBtn;
	
	// 线路
	ListView busesList;
	// 数据源
	SimpleAdapter adapter;
	
	// 站点
	Station station;
	
	Handler _handler = new Handler();
	
	List<Map<String, ?>> data = new ArrayList<Map<String,?>>();
	
	// 取得所有车辆信息
	List<NetStation> buses = null;
	
//	// 站点数据库操作类
//	BusOperatingHelper busDbHelper;
//	
//	StationOperatingHelper stationDbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b00v03);
		
		// 初始化数据
		initData();
		
		// 初始化组建+
		initModule();
	}
	
	/**
	 * 初始化数据
	 */
	private void initData(){
		
		station = (Station) getIntent().getSerializableExtra(STATION_KEY);
		
//		busDbHelper = new BusOperatingHelper(this);
//		stationDbHelper = new StationOperatingHelper(this);
		
		// 设置adapter
		adapter = new SimpleAdapter(this, 
				data, 
				R.layout.b00v03_zhandian_item, 
				from, 
				to);
		
		//  从网上加载公交信息
		getDataFromNet();
		
	}
	
	/**
	 * 初始化组建
	 */
	private void initModule() {
		
		retBtn = (ImageButton) findViewById(R.id.b00v03RetBtn);
		retBtn.setOnClickListener(retBtnClick);
	
		refreshBtn = (ImageButton) findViewById(R.id.b00v03RefreshBtn);
		refreshBtn.setOnClickListener(refreshBtnClick);
		
		stationName =  (TextView) findViewById(R.id.b00v03BusRoute);
		stationName.setText(station.getName());
		
		stationDir =  (TextView) findViewById(R.id.b00v03BusRouteDir);
		stationDir.setText(station.getDirection() + "站台");
		
//		shoucangBtn = (ImageButton) findViewById(R.id.b00v03ShoucangBtn);
//		shoucangBtn.setOnClickListener(shoucangBtnClick);
//		// 判断是否收藏
//		if(station.isSave()) {
//			shoucangBtn.setImageResource(R.drawable.b00v02_shuocang_btn);
//		}
		
		busesList = (ListView) findViewById(R.id.b00v03Stations);
		busesList.setAdapter(adapter);
	}
	
	/**
	 * 从网上加载公交信息
	 */
	private void getDataFromNet(){
		
		if(station != null) {
			
			// 访问网络，取得车辆信息
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					try {
						buses = B00v00BusUtil.getInfoOfStation(B00V03Activity.this, station.getStationId());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally{
						
						// 设置UI或消息提示(异步)
						_handler.post(new Runnable() {
							
							@Override
							public void run() {
								
								// 设置数据源
								setDateSource();
								
								// 更新车辆信息UI(ListView)
								adapter.notifyDataSetChanged();
							}
						});
					}
				}
			}).start();
		}
	}
	
	/**
	 * 设置数据源
	 */
	private void setDateSource() {
		
		// 设置数据源
		if(buses != null) {
			
			// 排序
			Collections.sort(buses);
			
			// 遍历，设置车辆信息
			for (NetStation bus : buses) {
				
				// 设置数据项目
				Map<String, String> dataItem = new HashMap<String, String>();
				
				List<NetStationItem> stationItems = bus.getStationItems();
				
				// 设置车辆信息
				for (NetStationItem netStationItem : stationItems) {
					
					// 设置车辆名称
					dataItem.put(from[0], bus.getRouteName());
					
					// 设置时间
					String stopCount = netStationItem.getStopCount();
					
					// 未发车
					if(NetStationItem.STOPCOUNT_0.equals(stopCount)) {
						dataItem.put(from[1], getString(R.string.b00v03_stopcount_0_text));
						dataItem.put(from[2], "");
						dataItem.put(from[3], "");
					}
					
					// 今日运营结束
					else if(NetStationItem.STOPCOUNT_1.equals(stopCount)) {
						dataItem.put(from[1], getString(R.string.b00v03_stopcount_1_text));
						dataItem.put(from[2], "");
						dataItem.put(from[3], "");
					}
					
					// 查询中
					else if(NetStationItem.STOPCOUNT_2.equals(stopCount)) {
						dataItem.put(from[1], getString(R.string.b00v03_stopcount_2_text));
						dataItem.put(from[2], "");
						dataItem.put(from[3], "");
					}
					
					// 设置时间
					else {
						dataItem.put(from[1], netStationItem.getTime() + getString(R.string.unit_fenzhong));
						dataItem.put(from[2], getString(R.string.b00v03_item_bus_1_text));
						dataItem.put(from[3], getString(R.string.b00v03_item_bus_2_text));
					}
				}

				data.add(dataItem);
			}
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
	 * 刷新按钮事件
	 */
	OnClickListener refreshBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			ShowMessageUtils.show(B00V03Activity.this, "刷新 TODO");
		}
	};

}

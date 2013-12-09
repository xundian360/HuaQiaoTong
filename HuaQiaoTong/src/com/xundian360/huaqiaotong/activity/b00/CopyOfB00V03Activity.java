/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b00;

import android.os.Bundle;

import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;

/**
 * 站点详情页面
 * @author   tengteng
 * @time     上午12:21:25
 * @version  1.0
 */
public class CopyOfB00V03Activity extends ComNoTittleActivity {
	
//	public static final String STATION_KEY = "station_key";
//	
//	// Key
//	public static final String[] from = {"ZhandianBusNum", "ZhandianBusTime",};
//	
//	// Key ID
//	public static final int[] to = {R.id.b00v03ZhandianBusNum, R.id.b00v03ZhandianBusTime};
//	
//	// 返回按钮
//	ImageButton retBtn;
//	// 刷新按钮
//	ImageButton refreshBtn;
//	// 站点名称
//	TextView stationName;
//	// 站点位置
//	TextView stationDir;
//	// 收藏按钮
//	ImageButton shoucangBtn;
//	
//	// 线路
//	ListView busesList;
//	// 数据源
//	SimpleAdapter adapter;
//	
//	// 站点
//	Station station;
//	
//	Handler _handler = new Handler();
//	
//	List<Map<String, ?>> data = new ArrayList<Map<String,?>>();
//	
//	// 取得所有车辆信息
//	List<Bus> buses = null;
//	
//	// 站点数据库操作类
//	BusOperatingHelper busDbHelper;
//	
//	StationOperatingHelper stationDbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
//		setContentView(R.layout.b00v03);
//		
//		// 初始化数据
//		initData();
//		
//		// 初始化组建+
//		initModule();
	}
	
//	/**
//	 * 初始化数据
//	 */
//	private void initData(){
//		
//		station = (Station) getIntent().getSerializableExtra(STATION_KEY);
//		
//		busDbHelper = new BusOperatingHelper(this);
//		stationDbHelper = new StationOperatingHelper(this);
//		
//		// 设置adapter
//		adapter = new SimpleAdapter(this, 
//				data, 
//				R.layout.b00v03_zhandian_item, 
//				from, 
//				to);
//		
//		//  从网上加载公交信息
//		getDataFromNet();
//		
//	}
//	
//	/**
//	 * 初始化组建
//	 */
//	private void initModule() {
//		
//		retBtn = (ImageButton) findViewById(R.id.b00v03RetBtn);
//		retBtn.setOnClickListener(retBtnClick);
//	
//		refreshBtn = (ImageButton) findViewById(R.id.b00v03RefreshBtn);
//		refreshBtn.setOnClickListener(refreshBtnClick);
//		
//		stationName =  (TextView) findViewById(R.id.b00v03BusRoute);
//		stationName.setText(station.getName());
//		
//		stationDir =  (TextView) findViewById(R.id.b00v03BusRouteDir);
//		stationDir.setText(station.getDirection() + "站台");
//		
//		shoucangBtn = (ImageButton) findViewById(R.id.b00v03ShoucangBtn);
//		shoucangBtn.setOnClickListener(shoucangBtnClick);
//		// 判断是否收藏
//		if(station.isSave()) {
//			shoucangBtn.setImageResource(R.drawable.b00v02_shuocang_btn);
//		}
//		
//		busesList = (ListView) findViewById(R.id.b00v03Stations);
//		busesList.setAdapter(adapter);
//	}
//	
//	/**
//	 * 从网上加载公交信息
//	 */
//	private void getDataFromNet(){
//		
//		if(station != null) {
//			
//			// 访问网络，取得车辆信息
//			new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
//					
//					try {
//						buses = B00v00BusUtil.getInfoOfStation(CopyOfB00V03Activity.this, station.getStationId());
//					} catch (JSONException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} finally{
//						
//						// 设置UI或消息提示(异步)
//						_handler.post(new Runnable() {
//							
//							@Override
//							public void run() {
//								
//								// 取得经过本站，但是没有到站数据的其他车辆信息
//								buses = busDbHelper.getStationBusNotInList(station, buses);
//								
//								// 设置车辆信息
//								station.setBuses(buses);
//								
//								// 设置数据源
//								setDateSource();
//								
//								// 更新车辆信息UI(ListView)
//								adapter.notifyDataSetChanged();
//							}
//						});
//					}
//				}
//			}).start();
//		}
//	}
//	
//	/**
//	 * 设置数据源
//	 */
//	private void setDateSource() {
//		
//		// 设置数据源
//		if(station != null) {
//			
//			// 取得所有车辆信息
//			List<Bus> buses = station.getBuses();
//			
//			// 遍历，设置车辆信息
//			for (Bus bus : buses) {
//				
//				// 设置数据项目
//				Map<String, String> dataItem = new HashMap<String, String>();
//				
//				dataItem.put(from[0], bus.getRouteKey());
//				
//				// 未知时间到达
//				if(bus.getDaozhangTime() == null) {
//					
//					dataItem.put(from[1], "未知时间");
//					
//				} else {
//					
//					dataItem.put(from[1], bus.getDaozhangTime() + getString(R.string.unit_fenzhong));
//				}
//
//				data.add(dataItem);
//			}
//		}
//	}
//	
//	/**
//	 * 返回按钮事件
//	 */
//	OnClickListener retBtnClick = new OnClickListener() {
//		
//		@Override
//		public void onClick(View arg0) {
//			
//			onBackPressed();
//		}
//	};
//	
//	/**
//	 * 刷新按钮事件
//	 */
//	OnClickListener refreshBtnClick = new OnClickListener() {
//		
//		@Override
//		public void onClick(View arg0) {
//			ShowMessageUtils.show(CopyOfB00V03Activity.this, "刷新 TODO");
//		}
//	};
//	
//	/**
//	 * 收藏按钮事件
//	 */
//	OnClickListener shoucangBtnClick = new OnClickListener() {
//		
//		@Override
//		public void onClick(View arg0) {
//			
//			 // 判断是否收藏
//	        if(station.isSave()) {
//	        	
//	        	// 取消收藏
//	        	station.setSave(false);
//		        // 更新数据库
//		        stationDbHelper.updateStatus(station);
//		        // 设值图片状态
//		        shoucangBtn.setImageResource(R.drawable.b00v02_no_shuocang_btn);
//		        
//		        ShowMessageUtils.show(CopyOfB00V03Activity.this, R.string.msg_no_shoucang_cancel);
//		        
//	        } else {
//	        	// 收藏
//	        	station.setSave(true);
//		        // 更新数据库
//		        stationDbHelper.updateStatus(station);
//		        // 设值图片状态
//		        shoucangBtn.setImageResource(R.drawable.b00v02_shuocang_btn);
//		        
//		        ShowMessageUtils.show(CopyOfB00V03Activity.this, R.string.msg_shoucang_success);
//	        }
//			
//		}
//	};
}

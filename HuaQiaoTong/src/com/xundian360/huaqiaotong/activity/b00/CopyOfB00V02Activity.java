package com.xundian360.huaqiaotong.activity.b00;

import android.os.Bundle;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;

/**
 * 车辆详情页面
 * @author   tengteng
 * @time     上午12:21:25
 * @version  1.0
 */
public class CopyOfB00V02Activity extends ComNoTittleActivity {
	
//	public static final String BUS_KEY = "bus_key";
//	
//	// 自动刷新时间间隔
//	private static final long REFRESH_TIME = 10000;
//	
//	// 返回按钮
//	ImageButton retBtn;
//	// 刷新按钮
//	ImageButton refreshBtn;
//	// 线路名称
//	TextView busRoute;
//	// 线路方向
//	TextView busRouteDir;
//	// 却换线路方向按钮
////	WiperSwitch switchDrection;
//	// 收藏按钮
//	ImageButton shoucangBtn;
//	
//	// 站点
//	ListView stationsListView;
//	// 数据源
//	B00v02StationsAdapter adapter;
//	
//	List<Map<String, ?>> data = new ArrayList<Map<String,?>>();
//	
//	// 传进来的Bus对象
//	Bus bus;
//	
//	// 车辆对应的站点信息
//	List<Station> stations;
//	
//	// 上行排序
//	boolean checkShangxingFlag = true;
//	
//	// 数据库帮助类
//	StationOperatingHelper dbHelper;
//	
//	// 数据库操作类
//	BusOperatingHelper busDbHelper;
//	
//	Handler _handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b00v02);
//		
//		// 初始化数据
//		initData();
//		
//		// 初始化组建+
//		initModule();
	}
//	
//	/**
//	 * 初始化数据
//	 */
//	private void initData(){
//		
//		// 数据库操作类
//		dbHelper= new StationOperatingHelper(this);
//		busDbHelper = new BusOperatingHelper(this);
//		
//		// 取得初始的车辆信息
//		bus = (Bus) getIntent().getSerializableExtra(BUS_KEY);
//		
//		// 设置车辆对应的站点信息
//		stations = dbHelper.getBusStation(this, bus);
//		bus.setStations(stations);
////		
////		Log.d("debug", "bus name > " + bus.getRouteName());
////		for (Station station : stations) {
////			Log.d("debug", "stations > " + station.getStationName());
////		}
//		
//		// 设置数据源
//		setDateSource();
//		
//		// 设置adapter
//		adapter = new B00v02StationsAdapter(this, 
//				data, 
//				R.layout.b00v02_xianlu_item, 
//				B00v02StationsAdapter.from, 
//				B00v02StationsAdapter.to,
//				bus,
//				stations);
//		
//		if(CommonUtil.isNetworkAvailable(this)) {
//			
//			//  从网上加载站点信息
//			getDataFromNet();
//		} else {
//			
//			ShowMessageUtils.show(this, R.string.message_error_network);
//		}
//	}
//	
//	/**
//	 * 设置数据源
//	 */
//	private void setDateSource() {
//
//		if (bus != null) {
//
//			// 清空数据源
//			data.clear();
//
//			// 重新设置数据源
//			if (stations == null) {
//				return;
//			}
//			
//			if(adapter != null) {
//				// 设置选中的站点
//				adapter.setNextStopIds(nextStopIds);
//			}
//
//			// 遍历，设置单个项目
//			for (int i = 0; i < stations.size(); i++) {
//				
//				Station station = stations.get(i);
//				String stationIs = station.getStationId();
//				
//				Map<String, Object> dateItem = new HashMap<String, Object>();
//
//				dateItem.put(B00v02StationsAdapter.from[0],station.getName());
//				
//				// 判断当前站点是否已到站，是否为当前站
//				if(nextStopIds != null && !nextStopIds.isEmpty() && nextStopIds.contains(stationIs)) {
//					// 当前站点即将到站
//					dateItem.put(B00v02StationsAdapter.from[1],R.drawable.b00v02_current_logo);
//				} else {
//					// 当前站点非即将到站
//					dateItem.put(B00v02StationsAdapter.from[1],R.drawable.b00v02_no_current_logo);
//				}
//				data.add(dateItem);
//			}
//		}
//	}
//	
//	/**
//	 * 初始化组建
//	 */
//	private void initModule() {
//		
//		retBtn = (ImageButton) findViewById(R.id.b00v02RetBtn);
//		retBtn.setOnClickListener(retBtnClick);
//	
//		refreshBtn = (ImageButton) findViewById(R.id.b00v02RefreshBtn);
//		refreshBtn.setOnClickListener(refreshBtnClick);
//		
//		busRoute = (TextView) findViewById(R.id.b00v02BusRoute);
//		busRoute.setText(bus.getRouteName());
//		
//		busRouteDir  = (TextView) findViewById(R.id.b00v02BusRouteDrection);
//		busRouteDir.setText(bus.getDirection());
////		
////		switchDrection = (WiperSwitch) findViewById(R.id.b00v02SwitchDrection);
////		switchDrection.setOnChangedListener(kaiguanChange);
//		
//		shoucangBtn = (ImageButton) findViewById(R.id.b00v02ShoucangBtn);
//		shoucangBtn.setOnClickListener(shoucangBtnClick);
//		// 判断是否收藏
//		if(bus.isSave()) {
//			shoucangBtn.setImageResource(R.drawable.b00v02_shuocang_btn);
//		}
//		
//		stationsListView = (ListView) findViewById(R.id.b00v02Stations);
//		stationsListView.setAdapter(adapter);
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
//			
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
//			// 判断是否收藏
//	        if(bus.isSave()) {
//	        	
//	        	// 取消收藏
//	        	bus.setSave(false);
//		        // 更新数据库
//	        	busDbHelper.updateBus(bus);
//		        // 设值图片状态
//	        	shoucangBtn.setImageResource(R.drawable.b00v02_no_shuocang_btn);
//		        
//		        ShowMessageUtils.show(CopyOfB00V02Activity.this, R.string.msg_no_shoucang_cancel);
//		        
//	        } else {
//	        	// 收藏
//	        	bus.setSave(true);
//		        // 更新数据库
//	        	busDbHelper.updateBus(bus);
//		        // 设值图片状态
//	        	shoucangBtn.setImageResource(R.drawable.b00v02_shuocang_btn);
//		        
//		        ShowMessageUtils.show(CopyOfB00V02Activity.this, R.string.msg_shoucang_success);
//	        }
//		}
//	};
//	
//	// 取得所有即将到站的站点信息
//	List<String> nextStopIds = null;
////	
////	/**
////	 * 开关改变
////	 */
////	OnChangedListener kaiguanChange = new OnChangedListener() {
////		
////		@Override
////		public void OnChanged(WiperSwitch wiperSwitch, boolean checkState) {
////			
////			if(checkShangxingFlag != checkState) {
////				
////				// 切换上下行状态
////				checkShangxingFlag = checkState;
////				
////				// 倒序车站
////				Collections.sort(stations, Collections.reverseOrder());
////				
////				Log.d("debug", "bus name > " + bus.getRouteName());
////				for (Station station : stations) {
////					Log.d("debug", "stations > " + station.getStationName());
////				}
////				
////				 // 设置数据源
////				setDateSource();
////				
////				// 更新ListView
////				adapter.notifyDataSetChanged();
////			}
////		}
////	};
//
//	/**
//	 * 从网上加载公交信息
//	 */
//	private void getDataFromNet(){
//		
//		if(bus != null) {
//			
//			// 访问网络，取得当前站点信息信息
//			new Thread(getDataFromNetRun).start();
//			
//			// 定时刷新
//			if(CommonUtil.isNetworkAvailable(this)) {
//				
//				ShowMessageUtils.show(CopyOfB00V02Activity.this, "刷新。。。");
//				
//				 _handler.postDelayed(getDataFromNetRun2, REFRESH_TIME);
//			}
//		}
//	}
//	
//	Runnable getDataFromNetRun2 = new Runnable() {
//		
//		@Override
//		public void run() {
//			
//			// 从网上加载公交信息
//			getDataFromNet();
//		}
//	};
//	
//	/**
//	 * 从网上加载公交信息线程
//	 */
//	Runnable getDataFromNetRun = new Runnable() {
//		@Override
//		public void run() {
//			try {
//				if(nextStopIds != null) {
//					nextStopIds.clear();
//				}
//				// 网上取得公交站点信息
//				nextStopIds = B00v00BusUtil.getInfoOfRoute(CopyOfB00V02Activity.this, bus.getRouteId());
//				
//				// 设置UI或消息提示(异步)
//				_handler.post(refreshUiRun);
//				
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} finally{
//			}
//		}
//	};
//	
//	/**
//	 * 刷新UI线程
//	 */
//	Runnable refreshUiRun = new Runnable() {
//		
//		@Override
//		public void run() {
//			// 非空
//			if(nextStopIds != null && !nextStopIds.isEmpty()) {
//				
//				// 设置数据源
//				setDateSource();
//				
//				// 更新车辆信息UI(ListView)
//				adapter.notifyDataSetChanged();
//			}
//		}
//	};
//	
//	protected void onPause() {
//		_handler.removeCallbacks(getDataFromNetRun2);
//		
//		super.onPause();
//	};
//	
}

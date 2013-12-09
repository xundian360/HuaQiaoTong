package com.xundian360.huaqiaotong.view.b00;

import android.content.Context;

/**
 * 线路视图
 *
 * @author   tengteng
 * @time     下午1:29:55
 * @version  1.0
 */
public class CopyOfB00v00HuanchengView {
	
//	// 驾车线路搜索Key
//	public static final int DRIVINGSEARCH_KEY = 0;
//	// 公交线路搜索Key
//	public static final int TRANSITSEARCH_KEY = 1;
//	// 步行线路搜索Key
//	public static final int WALKINGSEARCH_KEY = 2;
//	
//	// 开始位置获得焦点
//	public static final int FOCUS_FROM_KEY = 1;
//	// 结束位置获得焦点
//	public static final int FOCUS_TO_KEY = 2;
//	
//	String[] from_key = {"boov00HCPioTittle","boov00HCPioAddress"};
//	int[] to_key = {R.id.boov00HCPioTittle,R.id.boov00HCPioAddress};
//	
//	View mainView;
//	
//	Context context;
//	
//	// 切换地点
//	ImageButton switchTo;
//	// 开始位置
//	public EditText fromText;
//	// 语音输入开始位置
//	ImageButton fromYuyin;
//	// 开始位置定位
//	ImageButton toFromLocation;
//	// 结束位置
//	public EditText toText;
//	// 结束位置语音输入
//	ImageButton toYuyin;
//	// 结束位置定位
//	ImageButton toToLocation;
//	// 查询换乘路线
//	ImageButton huanchengSearch;
//	
//	// 兴趣点一览
//	ListView poisList;
//	
//	int focusKey;
//	
//	// 兴趣点数据源
//	SimpleAdapter poiListAdapter;
//	List<Map<String, String>> poiData = new ArrayList<Map<String,String>>();
//	
//	// poi数据列表
//	ArrayList<MKPoiInfo> searchPois;
//	
//	// 出发站点
//	public Baidu startStation = new Station();
//	// 结束站点
//	public Baidu endStation = new Station();
//	
//	// 显示位置选择按钮
//	boolean showLocationSelect = true;
//	
//	// 检索方式(默认公交)
//	int searchWay = TRANSITSEARCH_KEY;
//	
//	MKSearch mMKSearch = null;
	
	public CopyOfB00v00HuanchengView(Context context) {
		
//		this.context = context;
//		
//		// 初始化
//		init();
	}
	
//	public CopyOfB00v00HuanchengView(Context context, boolean showLocationSelect) {
//		
//		this.context = context;
//		this.showLocationSelect = showLocationSelect;
//		
//		// 初始化
//		init();
//	}
//	
//	/**
//	 * 初始化
//	 */
//	private void init() {
//		
//		poiListAdapter = new SimpleAdapter(context, 
//				poiData, R.layout.b00v00_huancheng_poi_item, from_key, to_key);
//		
//		// 初始化检索类
//		mMKSearch = new MKSearch();
////		mMKSearch.init(((ComNoTittleBMapManActivity)context).bMapManager,
////				new B00v00HCSearchListener(context, this));
//		
//		// 初始化视图
//		initView();
//	}
//	
//	/**
//	 * 初始化视图
//	 */
//	private void initView() {
//		
//		mainView = ((Activity)context).getLayoutInflater().inflate(R.layout.b00v00_huancheng, null);
//		
//		switchTo = (ImageButton) mainView.findViewById(R.id.b00v00Switch);
//		switchTo.setOnClickListener(switchToClick);
//		
//		fromText = (EditText) mainView.findViewById(R.id.b00v00FromText);
//		fromText.addTextChangedListener(fromTextWatcher);
//		
//		fromYuyin = (ImageButton) mainView.findViewById(R.id.b00v00FromYuyin);
//		fromYuyin.setOnClickListener(fromYuyinClick);
//		
//		toFromLocation = (ImageButton) mainView.findViewById(R.id.b00v00ToFromLocation);
//		toFromLocation.setOnClickListener(ToFromLocationClick);
//		toFromLocation.setVisibility(showLocationSelect ? View.VISIBLE : View.GONE);
//		
//		toText = (EditText) mainView.findViewById(R.id.b00v00ToText);
//		toText.addTextChangedListener(toTextWatcher);
//		
//		toYuyin = (ImageButton) mainView.findViewById(R.id.b00v00ToYuyin);
//		toYuyin.setOnClickListener(toYuyinClick);
//		
//		toToLocation = (ImageButton) mainView.findViewById(R.id.b00v00ToToLocation);
//		toToLocation.setOnClickListener(ToToLocationClick);
//		toToLocation.setVisibility(showLocationSelect ? View.VISIBLE : View.GONE);
//		
//		huanchengSearch = (ImageButton) mainView.findViewById(R.id.b00v00HuanchengSearch);
//		huanchengSearch.setOnClickListener(huanchengSearchClick);
//		
//		poisList = (ListView) mainView.findViewById(R.id.b00v00PoisList);
//		poisList.setAdapter(poiListAdapter);
//		poisList.setOnItemClickListener(poisListItemClick);
//	}
//	
//	/**
//	 * 兴趣点选择事件
//	 */
//	OnItemClickListener poisListItemClick = new OnItemClickListener() {
//
//		@Override
//		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//				long arg3) {
//			
//			MKPoiInfo poiInfo = searchPois.get(arg2);
//			
//			// 开始位置
//			if(FOCUS_FROM_KEY == focusKey) {
//				
//				fromText.setText(poiInfo.name);
//				
//				fromText.setSelection(poiInfo.name.length());
//				
//				startStation.getDataFromMKPoiInfo(poiInfo);
//			} 
//			// 结束位置
//			else if(FOCUS_TO_KEY == focusKey) {
//				
//				toText.setText(poiInfo.name);
//				
//				toText.setSelection(poiInfo.name.length());
//				
//				endStation.getDataFromMKPoiInfo(poiInfo);
//			}
//		}
//	};
//	
//	/**
//	 * 到达位置监听
//	 */
//	TextWatcher toTextWatcher = new TextWatcher() {
//		
//		@Override
//		public void onTextChanged(CharSequence s, int start, int before, int count) {
//			
//			// 到达位置得到焦点
//			focusKey = FOCUS_TO_KEY;
//			
////			// 清空到达位置
////			endStation = new Station();
//			
//			// 在城市内搜索
//			searchInCity(s);
//		}
//		
//		@Override
//		public void beforeTextChanged(CharSequence s, int start, int count,
//				int after) {}
//		@Override
//		public void afterTextChanged(Editable s) {}
//	};
//	
//	/**
//	 * 出发位置监听
//	 */
//	TextWatcher fromTextWatcher = new TextWatcher() {
//		
//		@Override
//		public void onTextChanged(CharSequence s, int start, int before, int count) {
//			
//			// 开始位置得到焦点
//			focusKey = FOCUS_FROM_KEY;
//			
////			// 清空开始位置
////			startStation = new Station();
//			
//			// 在城市内搜索
//			searchInCity(s);
//		}
//		
//		@Override
//		public void beforeTextChanged(CharSequence s, int start, int count,
//				int after) {}
//		@Override
//		public void afterTextChanged(Editable s) {}
//	};
//	
//	/**
//	 * 切换From，To
//	 */
//	OnClickListener switchToClick = new OnClickListener() {
//		
//		@Override
//		public void onClick(View v) {
//			
//			// 切换存储对象
//			Baidu stationTemp = startStation;
//			
//			startStation = endStation;
//			endStation = stationTemp;
//			
//			// 切换From，To输入
//			fromText.setText(startStation.getName());
//			toText.setText(endStation.getName());
//		}
//	};
//	
//	/**
//	 * From语音输入
//	 */
//	OnClickListener fromYuyinClick = new OnClickListener() {
//		
//		@Override
//		public void onClick(View v) {
//			
//		}
//	};
//	
//	/**
//	 * From位置定位
//	 */
//	OnClickListener ToFromLocationClick = new OnClickListener() {
//		
//		@Override
//		public void onClick(View v) {
//			
//			// To位置选择
//			zhandianSelect(B00V04Activity.START_SELECT, 
//					B00V04Activity.START_SELECT_REQUEST_CODE);
//		}
//	};
//	
//	/**
//	 * To语音输入
//	 */
//	OnClickListener toYuyinClick = new OnClickListener() {
//		
//		@Override
//		public void onClick(View v) {
//			
//		}
//	};
//	
//	/**
//	 * To位置定位
//	 */
//	OnClickListener ToToLocationClick = new OnClickListener() {
//		
//		@Override
//		public void onClick(View v) {
//			
//			// To位置选择
//			zhandianSelect(B00V04Activity.END_SELECT, 
//					B00V04Activity.END_SELECT_REQUEST_CODE);
//		}
//	};
//	
//	/**
//	 *  检索位置
//	 */
//	OnClickListener huanchengSearchClick = new OnClickListener() {
//		
//		@Override
//		public void onClick(View v) {
//			
//			// 非空判断
//			if(StringUtils.isBlank(startStation.getName())) {
//				
//				ShowMessageUtils.show(context, "请选择开始位置");
//				return;
//			}
//			if(StringUtils.isBlank(endStation.getName())) {
//				
//				ShowMessageUtils.show(context, "请选择结束位置");
//				return;
//			}
//			
//			// 组装数据
//			Intent intent = new Intent(context, B00V05Activity.class);
//			
//			intent.putExtra(B00V05Activity.START_STATION_KEY, startStation);
//			intent.putExtra(B00V05Activity.END_STATION_KEY, endStation);
//			intent.putExtra(B00V05Activity.SEARCH_WAY_KEY, searchWay);
//			
//			// 跳转
//			CommonUtil.startSubActivity(context, intent);
//		}
//	};
//	
//	/**
//	 * 在城市内搜索
//	 * @param searchKey
//	 */
//	private void searchInCity(CharSequence searchKey) {
//		
//		String search = searchKey.toString().trim();
//		
//		// 两个字母或一个汉字开始检索
//		if(search.getBytes().length >= 2) {
//			// 搜索兴趣点
//			mMKSearch.poiSearchInCity(context.getString(R.string.common_city),
//					searchKey.toString());
//		}
//	}
//	
//	/**
//	 * 站点选择
//	 * @param select_code
//	 * @param request_code
//	 */
//	private void zhandianSelect(int select_code, int request_code) {
//		
//		Intent in = new Intent(context, B00V04Activity.class);
//		
//		in.putExtra(B00V04Activity.SELECT_KEY, select_code);
//		
//		CommonUtil.startActivityForResult(context, in, request_code);
//	}
//	
//	/**
//	 * 设置站点
//	 * @param zhandianText
//	 * @param request_code
//	 */
//	public void setZhandianValue(Station station, int request_code) {
//		
//		// 出发站点
//		if(B00V04Activity.START_SELECT_REQUEST_CODE == request_code) {
//			startStation = station;
//			// 设置开始站点位置
//			fromText.setText(startStation.getName());
//			
//			// 设置光标
//			fromText.setSelection(startStation.getName().length());
//		} 
//		
//		// 终点
//		else if(B00V04Activity.END_SELECT_REQUEST_CODE == request_code) {
//			endStation = station;
//			// 设置开始站点位置
//			toText.setText(endStation.getName());
//			
//			// 设置光标
//			toText.setSelection(startStation.getName().length());
//		}
//	}
//	
//	/**
//	 * 刷新兴趣点
//	 */
//	public void refreshPoiList(ArrayList<MKPoiInfo> searchPois) {
//		
//		this.searchPois = searchPois;
//		
//		// 清空数据源
//		poiData.clear();
//		
//		// 设置数据源
//		for (MKPoiInfo mkPoiInfo : searchPois) {
//			Map<String, String> poiItem = new HashMap<String, String>();
//			
//			poiItem.put(from_key[0], mkPoiInfo.name);
//			poiItem.put(from_key[1], mkPoiInfo.address);
//			
//			poiData.add(poiItem);
//		}
//		
//		// 刷新兴趣点
//		poiListAdapter.notifyDataSetChanged();
//		
//		// 设置当前输入站点为第0个站点
//		if(searchPois.size() > 0) {
//			
//			MKPoiInfo firstMkPoiInfo = searchPois.get(0);
//			
//			// 出发站点
//			if (fromText.hasFocus()) {
//				startStation.getDataFromMKPoiInfo(firstMkPoiInfo);
//			} 
//			
//			// 终点
//			else if(toText.hasFocus()) {
//				endStation.getDataFromMKPoiInfo(firstMkPoiInfo);
//			}
//		}
//	}
//	
//	/**
//	 * 搜索方式
//	 * @param searchWay
//	 */
//	public void setSearchWay(int searchWay) {
//		this.searchWay = searchWay;
//	}
//	
//	/**
//	 * 返回View
//	 */
//	public View get() {
//		return mainView;
//	}
}

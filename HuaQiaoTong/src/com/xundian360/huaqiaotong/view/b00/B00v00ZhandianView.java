package com.xundian360.huaqiaotong.view.b00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.fortysevendeg.swipelistview.SwipeListView;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b00.B00V00Activity;
import com.xundian360.huaqiaotong.activity.b00.B00V03Activity;
import com.xundian360.huaqiaotong.adapter.b00.B00V00HisZdAdapter;
import com.xundian360.huaqiaotong.adapter.b00.B00v00ZhandianAdapter;
import com.xundian360.huaqiaotong.adapter.b00.SearchAdapter;
import com.xundian360.huaqiaotong.modle.b00.BusSavingModle;
import com.xundian360.huaqiaotong.modle.b00.Station;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.ViewUtils;
import com.xundian360.huaqiaotong.util.b00.HuanchengUtil;

/**
 * 站点视图
 * 
 * @author tengteng
 * @time 下午1:29:55
 * @version 1.0
 */
public class B00v00ZhandianView {

	View mainView;

	Context context;

	// 检索的站点
	AutoCompleteTextView zhanDianText;
	// 删除输入按钮
	ImageView inputDelete;
	// 搜索站点按钮
	Button zhanDianSearchBtn;
	// 搜索历史记录
	SwipeListView historyList;
	// 线路明细
	LinearLayout zhandianDtailLayout;

	// 站点Adapter
	SearchAdapter<String> zhandianAdapter;
	// 数据源
	List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

	// 所有站点信息
	List<Station> stations = new ArrayList<Station>();
	String[] stationNames;
	
	// 历史线路信息
	List<Station> hisStations = new ArrayList<Station>();
	List<Map<String, String>> hisData = new ArrayList<Map<String, String>>();
	B00V00HisZdAdapter hisAdapter;

	// 所有站点信息
	List<Station> shoucangStations;

	// 所有显示的线路信息
	List<Station> showStations = new ArrayList<Station>();

	// 当前检索的站点信息
	Station searchStation = null;

	Handler _handler = new Handler();
	
	// 存储历史记录
	BusSavingModle busSaving;

	public B00v00ZhandianView(Context context) {

		this.context = context;

		// 初始化数据
		initData();

		// 初始化视图
		initView();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		
		hisAdapter = new B00V00HisZdAdapter(context, hisStations, hisData, 
				R.layout.b00v00_zhandian_his_item, B00V00HisZdAdapter.from, B00V00HisZdAdapter.to);
		
		busSaving = new BusSavingModle(context);
		
		// 加载数据
		new Thread(laodData).start();
	}

	/**
	 * 加载数据
	 */
	Runnable laodData = new Runnable() {

		@Override
		public void run() {
			// 查询所有站点信息
			stations = initStations();

			// 设置数据源
			setAdapterData(null);

			// 刷新UI
			_handler.postDelayed(new Runnable() {

				@Override
				public void run() {

					zhandianAdapter = new SearchAdapter<String>(context,
							android.R.layout.simple_dropdown_item_1line,
							stationNames, SearchAdapter.ALL);// 速度优先

					zhanDianText.setAdapter(zhandianAdapter);
					
					// 设置历史站点信息
					onStart();
				}
			}, 1000);
		}
	};

	/**
	 * 初始化站点数据
	 * 
	 * @return
	 */
	public List<Station> initStations() {

		List<Station> stationsR = new ArrayList<Station>();

		String[] stationId = context.getResources().getStringArray(R.array.b00_station_id);
		String[] stationName = context.getResources().getStringArray(R.array.b00_station_name);
		String[] stationDir = context.getResources().getStringArray(R.array.b00_station_dir);

		for (int i = 0; i < stationId.length; i++) {

			Station station = new Station(stationId[i], stationName[i], 0, 0,
					stationDir[i]);

			stationsR.add(station);
		}

		return stationsR;
	}

	/**
	 * 设置数据源值
	 */
	private void setAdapterData(String referText) {

		// 清空数据源
		data.clear();
		showStations.clear();

		stationNames = new String[stations.size()];

		for (int i = 0; i < stations.size(); i++) {

			Station station = stations.get(i);

			// 设置包含特定字符的信息
			if (referText == null || station.getName().contains(referText)) {

				Map<String, Object> dataItem = new HashMap<String, Object>();

				dataItem.put(B00v00ZhandianAdapter.from[0], station.getName());
				dataItem.put(B00v00ZhandianAdapter.from[1],
						station.getDirection() + "站台");

				stationNames[i] = station.getName() + "  "
						+ station.getDirection() + "站台";

				data.add(dataItem);
				showStations.add(station);
			}
		}
	}

	/**
	 * 初始化视图
	 */
	private void initView() {
		// Main组件
		mainView = ((Activity) context).getLayoutInflater().inflate(
				R.layout.b00v00_zhangdian, null);

		// 其他组件
		zhanDianText = (AutoCompleteTextView) mainView
				.findViewById(R.id.b00v00ZhanDianText);
		zhanDianText.setOnItemClickListener(zhandianItemClick);
		zhanDianText.setOnEditorActionListener(zhanDianSearch);

		inputDelete = (ImageView) mainView
				.findViewById(R.id.b00v00ZhandianDelete);
		
		zhandianDtailLayout = (LinearLayout) mainView.findViewById(R.id.b00v00ZhandianDtailLayout);

		// 设置删除按钮的显隐性
		ViewUtils.inputMonitor(zhanDianText, inputDelete);

		zhanDianSearchBtn = (Button) mainView
				.findViewById(R.id.b00v00ZhanDianSearchBtn);
		zhanDianSearchBtn.setOnClickListener(zhanDianSearchClick);

		historyList = (SwipeListView) mainView.findViewById(R.id.b00v00ZhanDianList);
		historyList.setAdapter(hisAdapter);
		
		historyList.setAnimationTime(B00V00Activity.HIS_ANIMATION_TIME); 
		historyList.setOffsetLeft(CommonUtil.convertDpToPixel(context, B00V00Activity.HIS_OFFSET_LEFT));
		
		// 启动时调用
		onStart();
	}

	/**
	 * 开始搜索
	 */
	OnEditorActionListener zhanDianSearch = new OnEditorActionListener() {
		@Override
		public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
			if (EditorInfo.IME_ACTION_SEARCH == arg1) {
				// 搜索
				zhanDianSearch();
			}
			return true;
		}
	};

	/**
	 * 站点检索按钮
	 */
	OnClickListener zhanDianSearchClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 搜索
			zhanDianSearch();
		}
	};

	/**
	 * 搜索
	 */
	private void zhanDianSearch() {

		// 检查网络
		if (!CommonUtil.isNetworkAvailable(context)) {

			ShowMessageUtils.show(context, R.string.message_error_network);
			return;
		}
		// 设置检索数据
		String searchValue = zhanDianText.getText().toString();

		// TODO
		// 设置输入的站点信息NetListListL

		if (searchStation != null) {

			Intent in = new Intent(context, B00V03Activity.class);
			in.putExtra(B00V03Activity.STATION_KEY, searchStation);

			CommonUtil.startActivityForResult(context, in, 100);
			
//			// 设置历史线路信息
//			if(!busSaving.getZhandianIds().contains(searchStation.getStationId())) {
//				busSaving.setZhandianIds(busSaving.getZhandianIds() + BusSavingModle.SEPARATOR +searchStation.getStationId());
//				busSaving.save();
//			}

		} else {
			ShowMessageUtils.show(context, "请输入检索站点");
		}
	}

	/**
	 * 站点项目点击事件
	 */
	OnItemClickListener zhandianItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			// 设置检索站点对象
			String searchText = zhandianAdapter.getItem(arg2);
			
			for (int i = 0; i < stationNames.length; i++) {
				if(stationNames[i].equals(searchText)) {
					searchStation = stations.get(i);
					break;
				}
			}
		}
	};

	/**
	 * 返回View
	 */
	public View get() {
		return mainView;
	}

	/**
	 * 启动时调用
	 */
	public void onStart() {
		
		// 设置历史线路
		setHisadapter();
		
		// 判断是否显示说明
		if(hisData.size() > 0) {
			zhandianDtailLayout.setVisibility(View.GONE);
		} else {
			zhandianDtailLayout.setVisibility(View.VISIBLE);
		}
	}
	
	/**
	 * 设置历史线路
	 */
	private void setHisadapter() {
		busSaving.read();
		hisStations.clear();
		hisData.clear();
		
		// 加载存储的历史记录
		String[] stationIds = busSaving.getZhandianIds().split(BusSavingModle.SEPARATOR);
		String[] stationNames = busSaving.getZhandianNames().split(BusSavingModle.SEPARATOR);
		
		if (stationIds != null && stationIds.length > 0) {
			for (int i = 0; i < stationIds.length; i++) {
				
				String stationId = stationIds[i];
				
				// 设置历史站点信息
				Station hisStation = HuanchengUtil.getStationById(stations, stationId);
				
				if(hisStation == null) {
					continue;
				}
				
				hisStations.add(hisStation);
				
				// 设置历史线路Adapter 数据
				Map<String, String> hisDataItem = new HashMap<String, String>();
				
				hisDataItem.put(B00V00HisZdAdapter.from[0], hisStation.getName());
				hisDataItem.put(B00V00HisZdAdapter.from[1], hisStation.getDirection() + "站台");
				hisDataItem.put(B00V00HisZdAdapter.from[2], stationNames[i]);
				
				hisData.add(hisDataItem);
			}
		}
		
		// 更新ListView
		hisAdapter.notifyDataSetChanged();
	}
}

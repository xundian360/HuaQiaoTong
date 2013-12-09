package com.xundian360.huaqiaotong.view.b00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b00.B00V03Activity;
import com.xundian360.huaqiaotong.adapter.b00.B00v00ZhandianAdapter;
import com.xundian360.huaqiaotong.adapter.b00.SearchAdapter;
import com.xundian360.huaqiaotong.modle.b00.Station;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.ViewUtils;

/**
 * 站点视图
 *
 * @author   tengteng
 * @time     下午1:29:55
 * @version  1.0
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
	
	// 站点Adapter
	SearchAdapter<String> zhandianAdapter;
	// 数据源
	List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
	
	// 所有站点信息
	List<Station> stations = new ArrayList<Station>();
	String[] stationNames;
	
	// 所有站点信息
	List<Station> shoucangStations;
	
	// 所有显示的线路信息
	List<Station> showStations = new ArrayList<Station>();
	
	// 当前检索的站点信息
	Station searchStation = null;
	
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
		
		// 查询所有站点信息
		stations = initStations();
		
		// 设置数据源
		setAdapterData(null);
		
		zhandianAdapter = new SearchAdapter<String>(context,
    			android.R.layout.simple_dropdown_item_1line, stationNames,SearchAdapter.ALL);//速度优先	
	}
	
	/**
	 * 初始化站点数据
	 * @return 
	 */
	public List<Station> initStations() {
		
		List<Station> stationsR = new ArrayList<Station>();
		
		String[] stationId = context.getResources().getStringArray(R.array.b00_station_id);
		String[] stationName = context.getResources().getStringArray(R.array.b00_station_name);
		String[] stationDir = context.getResources().getStringArray(R.array.b00_station_dir);
		
		for (int i = 0; i < stationId.length; i++) {
			
			Station station = new Station(stationId[i], stationName[i], 0, 0, stationDir[i]);
			
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
			if(referText == null || station.getName().contains(referText)) {
				
				Map<String, Object> dataItem = new HashMap<String, Object>();
				
				dataItem.put(B00v00ZhandianAdapter.from[0], station.getName());
				dataItem.put(B00v00ZhandianAdapter.from[1], station.getDirection() + "站台");
				
				stationNames[i] = station.getName() + "  " + station.getDirection() + "站台";
				
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
		mainView = ((Activity)context).getLayoutInflater().inflate(R.layout.b00v00_zhangdian, null);
		
		// 其他组件
		zhanDianText = (AutoCompleteTextView) mainView.findViewById(R.id.b00v00ZhanDianText);
		zhanDianText.setAdapter(zhandianAdapter);
		zhanDianText.setOnItemClickListener(zhandianItemClick);
		
		inputDelete = (ImageView) mainView.findViewById(R.id.b00v00ZhandianDelete);
		
		// 设置删除按钮的显隐性
		ViewUtils.inputMonitor(zhanDianText, inputDelete);
		
		zhanDianSearchBtn = (Button) mainView.findViewById(R.id.b00v00ZhanDianSearchBtn);
		zhanDianSearchBtn.setOnClickListener(zhanDianSearchClick);
		
	}
	
	/**
	 * 站点检索按钮
	 */
	OnClickListener zhanDianSearchClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			 // 检查网络
			if(!CommonUtil.isNetworkAvailable(context)) {
				
				ShowMessageUtils.show(context, R.string.message_error_network);
				return;
			}
			// 设置检索数据
			String searchValue = zhanDianText.getText().toString();
			
			// TODO
			// 设置输入的站点信息NetListListL
				
			if(searchStation != null) {
				
				Intent in = new Intent(context, B00V03Activity.class);
				in.putExtra(B00V03Activity.STATION_KEY, searchStation);
				
				CommonUtil.startActivityForResult(context, in, 100);
				
			} else {
				ShowMessageUtils.show(context, "请输入检索站点");
			}
		}
	};
	
	/**
	 * 站点项目点击事件
	 */
	OnItemClickListener zhandianItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			// 设置检索站点对象
			searchStation = showStations.get(arg2);
		}
	};
	
	/**
	 * 返回View
	 */
	public View get() {
		return mainView;
	}
}

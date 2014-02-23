/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.search.MKLine;
import com.baidu.mapapi.search.MKPlanNode;
import com.baidu.mapapi.search.MKRoute;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKTransitRoutePlan;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleBMapManActivity;
import com.xundian360.huaqiaotong.adapter.b00.B00V05PlanAdapter;
import com.xundian360.huaqiaotong.common.map.MySearchListener;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.view.b00.B00v00HuanchengView;
import com.xundian360.huaqiaotong.view.com.SimpleListDialog;

/**
 * 换乘方案列表
 * @author  TengTeng
 * @date      2013-10-1
 * @version 1.0
 */
public class B00V05Activity extends ComNoTittleBMapManActivity {
	
	private String[] from = {"b00v06ItemImg", "b00v06ItemText"};
	private int[] to = {R.id.b00v06ItemImg, R.id.b00v06ItemText};
	
	public final static String START_STATION_KEY = "startStationKey";
	public final static String END_STATION_KEY = "endStationKey";
	
	public final static String SEARCH_WAY_KEY = "searchWayKey";

	// 返回按钮
	ImageButton retBtn;
	// 换乘信息ListView
	ListView huanchengList;
	
	TextView tittleText;
	
	TextView otherFangan;
	
	// 数据源
	B00V05PlanAdapter adapter;
	List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
	
	// 出发站点
	Baidu startStation;
	// 结束站点
	Baidu endStation;
	
	// 换乘方案
	MKTransitRouteResult huanchengs;
	
	MKSearch mSearch;
	
	// 检索方式(默认公交)
	int searchWay = B00v00HuanchengView.TRANSITSEARCH_KEY;
	
	Handler _handler = new Handler();
	
	// 方案列表选择
	SimpleListDialog fanganListDialog;
	// 方案列表
	String[] fanganListData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b00v05);
		
		mSearch = new MKSearch();
		mSearch.init(bMapManager, hCSearchListener);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
	}
	
	@Override
	protected void onStart() {
		
		// 开启规划路线
		getPlanData();
		
		super.onStart();
	}
	
//	@Override
//	public void onWindowFocusChanged(boolean hasFocus) {
//
//		if (hasFocus) {
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					// 开启规划路线
//					getPlanData();
//				}
//			}).start();
//		}
//		
//		super.onWindowFocusChanged(hasFocus);
//	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		
		// 取得传递来的参数
		startStation = (Baidu) getIntent().getSerializableExtra(START_STATION_KEY);
		endStation = (Baidu) getIntent().getSerializableExtra(END_STATION_KEY);
		
		searchWay = getIntent().getIntExtra(SEARCH_WAY_KEY, B00v00HuanchengView.TRANSITSEARCH_KEY);
		
		// 设置adapter
		adapter = new B00V05PlanAdapter(this, 
				data, 
				R.layout.b00v06_item, 
				from, 
				to);
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		retBtn = (ImageButton) findViewById(R.id.b00v05RetBtn);
		retBtn.setOnClickListener(retBtnClick);
		
		otherFangan =  (TextView) findViewById(R.id.b00v05OtherFangan);
		otherFangan.setOnClickListener(otherFanganClick);
		
		tittleText = (TextView) findViewById(R.id.b00v05Tittle);
		
		huanchengList = (ListView) findViewById(R.id.b00v05Huancheng);
		huanchengList.setAdapter(adapter);
		huanchengList.setOnItemClickListener(huanchengItemClick);
		
		otherFangan = (TextView) findViewById(R.id.b00v05OtherFangan);
		otherFangan.setOnClickListener(otherFanganClick);
	}
	
	
	/**
	 * 取得换乘方案
	 */
	private void getPlanData(){
		
		// 开始位置
		MKPlanNode startNote = new MKPlanNode();
		
		// 判断是否根据经纬度查询
		if(startStation.location_lat != 0) {
			startNote.pt = new GeoPoint((int)startStation.location_lat, 
					(int)startStation.location_lng);
		} else {
			startNote.name = startStation.getName();
		}
		
		// 结束位置
		MKPlanNode endNote = new MKPlanNode();
		
		// 判断是否根据经纬度查询
		if(startStation.location_lat != 0) {
			endNote.pt = new GeoPoint((int)endStation.location_lat, 
					(int)endStation.location_lng);
		} else {
			endNote.name = endStation.getName();
		}
		
		// 检索驾车
		if(searchWay == B00v00HuanchengView.DRIVINGSEARCH_KEY) {
			mSearch.drivingSearch(
					getString(R.string.common_city),
					startNote,
					getString(R.string.common_city),
					endNote);
		}
		
		// 步行
		else if(searchWay == B00v00HuanchengView.WALKINGSEARCH_KEY) {
			mSearch.walkingSearch(
					getString(R.string.common_city),
					startNote,
					getString(R.string.common_city),
					endNote);
		}
		
		// 检索公交
		else {
			mSearch.transitSearch(
					getString(R.string.common_city),
					startNote,
					endNote);
		}
	}
	
	/**
	 * 设置数据源值
	 */
	private void setAdapterData() {
		
		// 清空数据源
		data.clear();
		
		// 设置方案数组
		fanganListData = new String[huanchengs.getNumPlan()];
		
		// 设置方案选择数据
		for (int i = 0; i < huanchengs.getNumPlan(); i++) {
			
			// 线路规划
			MKTransitRoutePlan tranPlan = huanchengs.getPlan(i);
			
			String fanganTittle = getString(R.string.b00v05_fangan_text, (i + 1) + "");
			
			// 设置方案选择Dialog数据
			fanganListData[i] = fanganTittle + tranPlan.getContent();
			
			// 默认，第一个方案显示
			if(i == 0) {
				// 更新选中的方案
				updatePlanView(0);
			}
		}
		
		// 创建方案选择Dialog
		fanganListDialog = new SimpleListDialog(this, onPlanSelect, getString(R.string.b00v05_other_fangan_text), fanganListData);
	}
	
	/**
	 * 方案选择监听
	 */
	DialogInterface.OnClickListener onPlanSelect = new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// 更新选中的方案
			updatePlanView(which);
			
			// 更新数据
			adapter.notifyDataSetChanged();
		}
	};
	
	// 当前选择的方案
	int curSelectPlan = -1;
	
	/**
	 * 更新选中的方案
	 * @param index
	 */
	private void updatePlanView(int index) {
		// 判断当前显示方案不是选择的方案
		if(curSelectPlan != index) {
			
			curSelectPlan = index;
			
			// 清空数据源
			data.clear();
			
			// 线路规划
			MKTransitRoutePlan tranPlan = huanchengs.getPlan(index);
			
			// 设置起点
			addItem(R.drawable.b00v01_qidian_logo, startStation.getName());
			
			/*** 设置其他节点  ***/
			// 步行线路路段
			int routeNum = tranPlan.getNumRoute();
			
			// 公交线路路段
			int lineNum = tranPlan.getNumLines();
			
			for (int i = 0; i < routeNum; i++) {
				// 步行线路
				MKRoute mkRoute = tranPlan.getRoute(i);
				
				// 设置显示Item
				addItem(R.drawable.b00v01_buxing_logo, mkRoute.getTip());
				
				if(lineNum > i) {
					// 公交线路
					MKLine mkLine = tranPlan.getLine(i);
					
					// 设置显示Item
					addItem(R.drawable.b00v01_gongjian_logo, mkLine.getTip());
				}
			}
			
			// 设置终点
			addItem(R.drawable.b00v01_zhongdian_logo, endStation.getName());
			
		} else {
			ShowMessageUtils.show(this, R.string.b00v05_no_select_fangan_text);
		}
	}
	
	/**
	 * 设置显示Item
	 * @param imgRes
	 * @param tip
	 */
	private void addItem(int imgRes, String tip) {
		Map<String, Object> mkLineItem = new HashMap<String, Object>();
		mkLineItem.put(from[0], imgRes);
		mkLineItem.put(from[1], tip);
		data.add(mkLineItem);
	}
	
	/**
	 * 取得公交线路描述
	 */
	private String getTransitRoutePlanDisc(MKTransitRoutePlan tranPlan) {
		
		StringBuffer planDisc = new StringBuffer();
		
		// 步行线路路段
		int routeNum = tranPlan.getNumRoute();
		
		// 公交线路路段
		int lineNum = tranPlan.getNumLines();
		
		for (int i = 0; i < routeNum; i++) {
			// 步行线路
			MKRoute mkRoute = tranPlan.getRoute(i);
			planDisc.append(mkRoute.getTip());
			
			if(lineNum > i) {
				// 公交线路
				MKLine mkLine = tranPlan.getLine(i);
				planDisc.append(mkLine.getTip());
			}
		}
		
		return planDisc.toString();
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
	 * 其他方案选择事件
	 */
	OnClickListener otherFanganClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
			// 非空判断
			if(fanganListData == null && fanganListData.length == 1) {
				ShowMessageUtils.show(B00V05Activity.this, R.string.b00v05_no_other_fangan_text);
			}
			
			// 显示Dialog
			fanganListDialog.show();
		}
	};
	
	/**
	 * 换乘方案选择
	 */
	OnItemClickListener huanchengItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
//			// 跳转
//			CommonUtil.startActivityForResult(B00V05Activity.this, 
//					B00V06Activity.class, 
//					B00V06Activity.TRANSIT_ROUTE_PLAN_KEY, 
//					huanchengs.getPlan(arg2), 
//					100);
		}
	};
	
	/**
	 * 换成方案
	 */
	MySearchListener hCSearchListener = new MySearchListener() {
		
		/**
		 * 公交线路检索
		 */
		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult result, int iError) {

			// 错误号可参考MKEvent中的定义
			if (iError == MKEvent.ERROR_RESULT_NOT_FOUND){
				Toast.makeText(B00V05Activity.this, "抱歉，未找到结果",Toast.LENGTH_LONG).show();
				return ;
			}
			else if (iError != 0 || result == null) {
				Toast.makeText(B00V05Activity.this, "搜索出错啦..", Toast.LENGTH_LONG).show();
				return;
			}
			
			// 设置换乘
			huanchengs = result;
			
			// 设置标题
			tittleText.setText(getString(R.string.b00v05_tittle_text, result.getNumPlan() + ""));
			
			// 设置数据源
			setAdapterData();
			
			adapter.notifyDataSetChanged();
		}
	};
	
}

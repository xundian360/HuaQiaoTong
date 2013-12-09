/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

//import com.baidu.location.BDLocation;
//import com.baidu.mapapi.map.MapView;
//import com.baidu.mapapi.map.OverlayItem;
//import com.baidu.platform.comapi.basestruct.GeoPoint;
//import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleMapActivity;
//import com.xundian360.huaqiaotong.adapter.b01.B01v00KtvAdapter;
//import com.xundian360.huaqiaotong.adapter.b01.B01v00KtvAdapter.OnToMapCallBack;
//import com.xundian360.huaqiaotong.common.map.BaiduItemOverlay;
//import com.xundian360.huaqiaotong.common.map.SimpleLocationManager;
//import com.xundian360.huaqiaotong.modle.com.Baidu;
//import com.xundian360.huaqiaotong.util.CommonUtil;
//import com.xundian360.huaqiaotong.util.ShowMessageUtils;
//import com.xundian360.huaqiaotong.util.StringUtils;
//import com.xundian360.huaqiaotong.util.b01.B01v00BauduUtil;

/**
 * KTV列表
 * @author  TengTeng
 * @date      2013-10-3
 * @version 1.0
 */
public class CopyOfB01V00Activity extends ComNoTittleMapActivity {

//	public static final String QUERY_KEY = "query_key";
//	
//	// Tittle View
//	TextView tittleText;
//	
//	// 返回按钮
//	ImageButton retBtn;
//	// 地图和列表切换按钮
//	ImageButton mapOrListBtn;
//	// KTV List
//	ListView ktvsList;
//	// MapView
//	
//	// 数据源
//	List<Baidu> ktvs;
//	List<Map<String, ?>> data = new ArrayList<Map<String,?>>();
//	
//	// Adapter
//	B01v00KtvAdapter adapter;
//	
//	// 当前范围内KTV的总数量
//	int totalKtv = 0;
//	
//	Handler _handler = new Handler();
//	
//	// 地图第一次加载
//	boolean isMapFirstLoad = true;
//	
//	// 当前选中的Ktv
//	Baidu selectKtv;
//	
//	// 搜索关键字
//	String queryString;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		setContentView(R.layout.b01v00);
//		
//		// 初始化数据
//		initData();
//		
//		// 初始化组件
//		initModule();
//		
//		// 设置地图
//		setMap();
	}
	
//	@Override
//	protected void onStart() {
//		
//		if(CommonUtil.isNetworkAvailable(this)) {
//			// 开始定位
//			locationManager.start();
//		} else {
//			ShowMessageUtils.show(this, R.string.message_error_network);
//		}
//		
//		super.onStart();
//	}
//	
//	/**
//	 *  初始化数据
//	 */
//	private void initData(){
//		
//		queryString = getIntent().getStringExtra(QUERY_KEY);
//		
//		// 设置位置管理器(定位一次)
//		locationManager = new SimpleLocationManager(getApplication(), locationListener, -1);
//		
//		// 设置Adapter对象
//		adapter = new B01v00KtvAdapter(this, data, 
//				R.layout.b01v00_item, B01v00KtvAdapter.from, 
//				B01v00KtvAdapter.to, itemToMapCallBack, ktvs);
//	}
//	
//	/**
//	 *  初始化组件
//	 */
//	private void initModule(){
//		
//		tittleText = (TextView) findViewById(R.id.b01v00Tittle);
//		tittleText.setText(queryString.toUpperCase());
//		
//		retBtn = (ImageButton) findViewById(R.id.b01v00RetBtn);
//		retBtn.setOnClickListener(retBtnClick);
//		
//		mapOrListBtn = (ImageButton) findViewById(R.id.b01v00MapOrListBtn);
//		mapOrListBtn.setOnClickListener(mapOrListBtnClick);
//		
//		ktvsList = (ListView) findViewById(R.id.b01v00Ktvs);
//		ktvsList.setAdapter(adapter);
//		
//		mapView = (MapView) findViewById(R.id.b01v00Map);
//	}
//	
//	/**
//	 * 返回按钮事件
//	 */
//	OnClickListener retBtnClick = new OnClickListener() {
//		
//		@Override
//		public void onClick(View arg0) {
//			onBackPressed();
//		}
//	};
//	
//	/**
//	 * 地图或列表模式切换按钮事件
//	 */
//	OnClickListener mapOrListBtnClick = new OnClickListener() {
//		
//		@Override
//		public void onClick(View arg0) {
//			
//			if(ktvsList.getVisibility() == View.GONE) {
//				
//				// 显示List
//				showList();
//				
//			} else {
//				
//				// 显示地图
//				showMap(0, 0);
//			}
//		}
//	};
//	
//	/**
//	 * 显示List
//	 */
//	private void showList() {
//		
//		ktvsList.startAnimation(AnimationUtils.loadAnimation(CopyOfB01V00Activity.this, R.anim.push_left_in));
//		ktvsList.setVisibility(View.VISIBLE);
//		
//		mapView.startAnimation(AnimationUtils.loadAnimation(CopyOfB01V00Activity.this, R.anim.push_right_out));
//		mapView.setVisibility(View.GONE);
//		
//		// TODO
//		// 按钮变化
//	}
//	
//	/**
//	 * 显示地图
//	 * @param isMyLocation 
//	 */
//	public void showMap(double latitude, double longitude) {
//		
//		ktvsList.startAnimation(AnimationUtils.loadAnimation(CopyOfB01V00Activity.this, R.anim.push_left_out));
//		ktvsList.setVisibility(View.GONE);
//		
//		mapView.startAnimation(AnimationUtils.loadAnimation(CopyOfB01V00Activity.this, R.anim.push_right_in));
//		mapView.setVisibility(View.VISIBLE);
//		
//		// 第一次，加载地图
//		if(isMapFirstLoad) {
//			
//			isMapFirstLoad = false;
//			
//			// 初始化站点覆盖物信息
//			initOverlay();
//		}
//		
//		// 定位到我的位置
//		if(latitude == 0 && myLocation != null) {
//			mapView.getController().animateTo(
//					new GeoPoint((int)(myLocation.latitude* 1e6), (int)(myLocation.longitude *  1e6)));
//		} else {
//			mapView.getController().animateTo(
//					new GeoPoint((int)(latitude* 1e6), (int)(longitude *  1e6)));
//		}
//		
//		// TODO
//		// 按钮变化
//		
//	}
//	
//	/**
//	 * 定位到我的位置之后
//	 */
//	@Override
//	public void myLocationOtherDo(BDLocation location) {
//        // 访问网络，取得KTV信息
//        new Thread(getKtvFromNetRun).start();
//	};
//	
//	/**
//	 * 访问网络，取得KTV信息线程
//	 */
//	Runnable getKtvFromNetRun = new Runnable() {
//		
//		@SuppressWarnings("unchecked")
//		@Override
//		public void run() {
//			
//			// 取得圆形区域内的KTV信息
//			Map<String, Object> ktvAll = B01v00BauduUtil.searchRadiusKtv(
//					CopyOfB01V00Activity.this, 
//					queryString,
//					B01v00BauduUtil.DEFAULT_PAGE_SIZE, 
//					B01v00BauduUtil.DEFAULT_PAGE_NUM, 
//					myLocation.latitude + "," + myLocation.longitude, 
//					B01v00BauduUtil.RADIUS_KEY);
//			
//			// 非空,且取到了KTV信息
//			if(ktvAll != null && ktvAll.containsKey(B01v00BauduUtil.KTV_RESULTS_KEY)) {
//				
//				// 可取的的KTV总数量
//				String totalKtvS = (String) ktvAll.get(B01v00BauduUtil.KTV_TOTAL_KEY);
//				
//				if(StringUtils.isNotBlank(totalKtvS)) {
//					totalKtv = Integer.parseInt(totalKtvS);
//				}
//				
//				// KTV信息
//				ktvs = (List<Baidu>)ktvAll.get(B01v00BauduUtil.KTV_RESULTS_KEY);
//			}
//			
//			// 非空
//			if(ktvs != null && !ktvs.isEmpty()) {
//				
//				// 更新ListView
//				_handler.post(updateList);
//			}
//		}
//	};
//	
//	/**
//	 * 更新ListView
//	 */
//	Runnable updateList = new Runnable() {
//		
//		@Override
//		public void run() {
//			
//			// 设置数据源
//			setAdapterData();
//			
//			// 更新ListView
//			adapter.notifyDataSetChanged();
//			
//			// 设置数据
//			adapter.setKtvs(ktvs);
//		}
//	};
//	
//	/**
//	 * 设置数据源值
//	 */
//	private void setAdapterData() {
//		
//		// 清空数据源
//		data.clear();
//		
//		// 遍历，设置ListView项目
//		for (Baidu ktv : ktvs) {
//			
//			// 数据项目
//			Map<String, Object> dataItem = new HashMap<String, Object>();
//			dataItem.put(B01v00KtvAdapter.from[1], ktv.getName());
////			dataItem.put(B01v00KtvAdapter.from[2], ktv.getDistance());
//			
//			dataItem.put(B01v00KtvAdapter.from[2], 
//					StringUtils.formatDoubleToString1(StringUtils.paseDouble(ktv.getDistance(), 0) / 1000) + "KM");
//			
//			dataItem.put(B01v00KtvAdapter.from[3], ktv.getPrice());
//			
//			// 添加到数据源
//			data.add(dataItem);
//		}
//	}
//
//	/**
//	 * 设置地图数据
//	 */
//	public void setMap() {
//		super.setMap();
//	};
//	
//	/**
//	 *  初始化站点覆盖物信息
//	 */
//	private void initOverlay(){
//		
//		if(ktvs != null && !ktvs.isEmpty()) {
//			
//			BaiduItemOverlay mOverlay = 
//					new BaiduItemOverlay(this, 
//							getResources().getDrawable(R.drawable.icon_gcoding), 
//							mapView, ktvs);
//			
//			// 遍历。添加KTV信息到地图
//			for (Baidu ktv : ktvs) {
//				
//		         GeoPoint geoPoint = new GeoPoint ((int)(ktv.getLocation_lat() * 1e6),(int)(ktv.getLocation_lng()* 1e6));
//		         
//		         Log.d("debug", "station.getLocation_lat() > " + (int)(ktv.getLocation_lat()* 1e6));
//		         Log.d("debug", "station.getLocation_lng() > " + (int)(ktv.getLocation_lng()* 1e6));
//		         
//		         OverlayItem verlayItem = new OverlayItem(geoPoint, 
//		        		 ktv.getName(), ktv.getAddress());
//		         
//		         verlayItem.setMarker(getResources().getDrawable(R.drawable.icon_gcoding));
//		         
//		         mOverlay.addItem(verlayItem);
//			}
//			
//			// 非空
//			if(mOverlay.size() > 0) {
//				
//				// 添加气泡到地图
//				mapView.getOverlays().add(mOverlay);
//				
//		         // 刷新地图
//				mapView.refresh();
//			}
//		}
//	}
//
//	OnToMapCallBack itemToMapCallBack = new OnToMapCallBack() {
//		
//		@Override
//		public void onToMapCallBack(Baidu ktv) {
//
//			// 显示地图
//			showMap(ktv.getLocation_lat(), ktv.getLocation_lng());
//			
//			// 定位
//			mapView.getController()
//				.animateTo(new GeoPoint((int)(ktv.getLocation_lat()* 1e6), 
//						(int)(ktv.getLocation_lng() *  1e6)));
//		}
//	};
}

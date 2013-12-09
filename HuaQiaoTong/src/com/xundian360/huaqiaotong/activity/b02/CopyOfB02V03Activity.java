/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b02;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.RouteOverlay;
import com.baidu.mapapi.map.TransitOverlay;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPlanNode;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleMapActivity;
import com.xundian360.huaqiaotong.common.map.MySearchListener;
import com.xundian360.huaqiaotong.common.map.SimpleLocationManager;
import com.xundian360.huaqiaotong.modle.b02.Bick;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;

/**
 * 自行车导航
 * @author    LiZhenteng
 * @date      2013年10月14日
 * @version   1.0
 */
public class CopyOfB02V03Activity extends ComNoTittleMapActivity {
	
	public static final String Bick_KEY = "bick_key";
	
	// 返回按钮
	ImageButton retBtn;
	
	// 公共自行车
	Bick bick;
	
	Handler _handler = new Handler();
	
	// 线路图层
	RouteOverlay routeOverlay = null;
	//搜索相关（搜索模块，也可去掉地图模块独立使用）
	MKSearch mSearch = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b02v03);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
		
		// 设置地图
		setMap();
		
		// 设置线路规划
		planRoute();
		
		// 开始定位
		locationManager.start();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		
		bick = (Bick) getIntent().getSerializableExtra(Bick_KEY);
		
		// 设置位置管理器(定位一次)
		locationManager = new SimpleLocationManager(getApplication(), locationListener, -1);
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		retBtn = (ImageButton) findViewById(R.id.b02v03RetBtn);
		retBtn.setOnClickListener(retBtnClick);
		
//		mapView = (MapView) findViewById(R.id.b02v03BickMap);
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
	 * 定位到我的位置之后
	 */
	@Override
	public void myLocationOtherDo(BDLocation location) {
		super.myLocationOtherDo(location);
		
		GeoPoint location_pt = 
				new GeoPoint((int)(myLocation.latitude* 1e6), (int)(myLocation.longitude *  1e6));
		
		GeoPoint end_pt = new GeoPoint((int)(bick.getLocation_lat() * 1e6), (int)(bick.getLocation_lng() *  1e6));
		
		// 移动到定位点
		mapView.getController().animateTo(location_pt);
		
		// 设置开始位置和结束位置
		MKPlanNode stNode = new MKPlanNode();
		stNode.name = getString(R.string.common_text_location);
		stNode.pt = location_pt;
		
		MKPlanNode enNode = new MKPlanNode();
		enNode.name = bick.getAddress();
		stNode.pt = end_pt;
		
		mSearch.setDrivingPolicy(MKSearch.ECAR_TIME_FIRST);  
		// 检索路线
		mSearch.drivingSearch("苏州", stNode,  "苏州", enNode);
	};
	
	/**
	 * 设置线路规划
	 */
	private void planRoute() {
		
        // 初始化搜索模块，注册事件监听
        mSearch = new MKSearch();

        // 初始化监听
        mSearch.init(bMapManager, searchListener);
	}
	
	TransitOverlay transitOverlay;
	
	/**
	 * 检索结果监听
	 */
	MySearchListener searchListener = new MySearchListener() {
		
		/**
		 * 驾车路线搜索结果
		 */
		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult result, int iError) {
			
			//起点或终点有歧义，需要选择具体的城市列表或地址列表
			if (iError == MKEvent.ERROR_ROUTE_ADDR){
				ShowMessageUtils.show(CopyOfB02V03Activity.this, R.string.b02v03_no_search_text);
				return;
			}
			// 错误号可参考MKEvent中的定义
			if (iError != 0 || result == null) {
				ShowMessageUtils.show(CopyOfB02V03Activity.this, R.string.b02v03_no_search_text);
				return;
			}
			
			routeOverlay = new RouteOverlay(CopyOfB02V03Activity.this, mapView);
    	    routeOverlay.setStMarker(getResources().getDrawable(R.drawable.common_icon_st));
    	    routeOverlay.setEnMarker(getResources().getDrawable(R.drawable.common_icon_en));
		    // 此处仅展示一个方案作为示例
		    routeOverlay.setData(result.getPlan(0).getRoute(0));
		    //清除其他图层
		    mapView.getOverlays().clear();
		    //添加路线图层
		    mapView.getOverlays().add(routeOverlay);
		    //执行刷新使生效
		    mapView.refresh();
		    //移动地图到起点
		    mapView.getController().animateTo(result.getStart().pt);
		    // 使用zoomToSpan()绽放地图，使路线能完全显示在地图上
		    mapView.getController().zoomToSpan(routeOverlay.getLatSpanE6(), routeOverlay.getLonSpanE6());
		}
	};
	
}

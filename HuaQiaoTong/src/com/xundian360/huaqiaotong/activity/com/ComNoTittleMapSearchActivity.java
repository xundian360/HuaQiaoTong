/**
 * 
 */
package com.xundian360.huaqiaotong.activity.com;

import android.os.Bundle;

import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.RouteOverlay;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKRoute;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.common.map.MySearchListener;
import com.xundian360.huaqiaotong.modle.b02.Bick;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;

/**
 * 带搜索监听的Activity
 * 
 * @author TengTeng
 * @date 2013年11月12日
 * @version 1.0
 */
public class ComNoTittleMapSearchActivity extends ComNoTittleMapActivity {

	// 搜索相关
	public MKSearch mSearch = null;

	RouteOverlay routeOverlay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 初始化搜索模块，注册事件监听
		mSearch = new MKSearch();

		// 初始化监听
		mSearch.init(bMapManager, searchListener);
	}

	/**
	 * 规划驾车路线
	 */
	public void planDrivingLine(Bick bickItem) {

		GeoPoint location_pt = new GeoPoint((int) (myLocation.latitude * 1e6),
				(int) (myLocation.longitude * 1e6));

		GeoPoint end_pt = new GeoPoint(
				(int) (bickItem.getLocation_lat() * 1e6),
				(int) (bickItem.getLocation_lng() * 1e6));
		//
		// // 设置开始位置和结束位置
		// MKPlanNode stNode = new MKPlanNode();
		// // stNode.name = getString(R.string.common_text_location);
		// stNode.pt = location_pt;
		//
		// MKPlanNode enNode = new MKPlanNode();
		// // enNode.name = bickItem.getName();
		// stNode.pt = end_pt;
		//
		// mSearch.setDrivingPolicy(MKSearch.ECAR_TIME_FIRST);
		// // 检索路线
		// mSearch.drivingSearch(getString(R.string.common_p_city), stNode,
		// getString(R.string.common_p_city), enNode);

		GeoPoint[] step1 = new GeoPoint[2];
		step1[0] = location_pt;
		step1[1] = end_pt;

		// 用站点数据构建一个MKRoute
		MKRoute route = new MKRoute();
		route.customizeRoute(location_pt, end_pt, step1);
		// 将包含站点信息的MKRoute添加到RouteOverlay中
		routeOverlay = new RouteOverlay(this, mapView);
		routeOverlay.setData(route);
		// 向地图添加构造好的RouteOverlay
		mapView.getOverlays().add(routeOverlay);
		// 执行刷新使生效
		mapView.refresh();
	}

	/**
	 * 检索结果监听
	 */
	MySearchListener searchListener = new MySearchListener() {

		/**
		 * 驾车路线搜索结果
		 */
		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult result,
				int iError) {

			// 起点或终点有歧义，需要选择具体的城市列表或地址列表
			if (iError == MKEvent.ERROR_ROUTE_ADDR) {
				ShowMessageUtils.show(ComNoTittleMapSearchActivity.this,
						R.string.b02v03_no_search_text);
				return;
			}
			// 错误号可参考MKEvent中的定义
			if (iError != 0 || result == null) {
				ShowMessageUtils.show(ComNoTittleMapSearchActivity.this,
						R.string.b02v03_no_search_text);
				return;
			}

			// 做其他事情
			ComNoTittleMapSearchActivity.this.onGetDrivingRouteResult(result);
		}
	};

	/**
	 * 驾车路线搜索结果
	 */
	public void onGetDrivingRouteResult(MKDrivingRouteResult result) {

	}

}

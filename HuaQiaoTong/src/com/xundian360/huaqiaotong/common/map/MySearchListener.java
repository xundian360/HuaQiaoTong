/**
 * 
 */
package com.xundian360.huaqiaotong.common.map;

import android.util.Log;

import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKShareUrlResult;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;

/**
 * 实现MKSearchListener接口,用于实现异步搜索服务，得到搜索结果
 * @author  TengTeng
 * @date      2013-10-3
 * @version 1.0
 */
public abstract class MySearchListener implements MKSearchListener {
	/**
	 * 根据经纬度搜索地址信息结果
	 * @param result 搜索结果
	 * @param iError 错误号（0表示正确返回）
	 */
	@Override
	public void onGetAddrResult(MKAddrInfo result, int iError) {
		Log.d("debug", "onGetAddrResult");
	}

	/**
	 * 驾车路线搜索结果
	 * @param result 搜索结果
	 * @param iError 错误号（0表示正确返回）
	 */
	@Override
	public void onGetDrivingRouteResult(MKDrivingRouteResult result, int iError) {
		Log.d("debug", "onGetDrivingRouteResult");
	}

	/**
	 * POI搜索结果（范围检索、城市POI检索、周边检索）
	 * @param result 搜索结果
	 * @param type 返回结果类型（11,12,21:poi列表 7:城市列表）
	 * @param iError 错误号（0表示正确返回）
	 */
	@Override
	public void onGetPoiResult(MKPoiResult result, int type, int iError) {
		Log.d("debug", "onGetPoiResult");
	}

	/**
	 * 公交换乘路线搜索结果
	 * @param result 搜索结果
	 * @param iError 错误号（0表示正确返回）
	 */
	@Override
	public void onGetTransitRouteResult(MKTransitRouteResult result, int iError) {
		Log.d("debug", "onGetTransitRouteResult");
	}

	/**
	 * 步行路线搜索结果
	 * @param result 搜索结果
	 * @param iError 错误号（0表示正确返回）
	 */
	@Override
	public void onGetWalkingRouteResult(MKWalkingRouteResult result, int iError) {
		Log.d("debug", "onGetWalkingRouteResult");
	}

	@Override
	public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
		Log.d("debug", "onGetBusDetailResult");		
	}

	@Override
	public void onGetPoiDetailSearchResult(int arg0, int arg1) {
		Log.d("debug", "onGetPoiDetailSearchResult");
	}

	@Override
	public void onGetShareUrlResult(MKShareUrlResult arg0, int arg1, int arg2) {
		Log.d("debug", "onGetShareUrlResult");		
	}

	@Override
	public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
		Log.d("debug", "onGetSuggestionResult");		
	}
}

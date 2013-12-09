/**
 * 
 */
package com.xundian360.huaqiaotong.util.b00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.b00.Huancheng;
import com.xundian360.huaqiaotong.modle.b00.NetLine;
import com.xundian360.huaqiaotong.modle.b00.NetStation;
import com.xundian360.huaqiaotong.modle.b00.NetStationItem;
import com.xundian360.huaqiaotong.util.BaseHttpClient;
import com.xundian360.huaqiaotong.util.StringUtils;

/**
 * 公交信息工具类
 * @author  TengTeng
 * @date      2013-9-25
 * @version 1.0
 */
public class B00v00BusUtil {
	
	private final static String BUSSTATIONKEY = "busStation";
	private final static String ROUTEIDKEY = "routeId";
	
	private final static String HUANGCHENG_FROM_KEY = "busStopId0";
	private final static String HUANGCHENG_TO_KEY = "busStopId1";
	
//	private final static String BUSSTATIONKEY = "busStation";
	
	/**
	 * 取得指定站点的车辆信息
	 * @param busStationId
	 * @return
	 * @throws JSONException 
	 */
	public static List<NetStation> getInfoOfStation(Context context, String busStationId) throws JSONException {
		
		List<NetStation> buses = new ArrayList<NetStation>();
		
		// 网络上加载车辆信息
		String infoOfStationJson = getInfoOfStationFromNet(context, busStationId);
		
		// 未去到网上的车辆信息
		if(StringUtils.isBlank(infoOfStationJson)) {
			return null;
		}
		
		// 解析Json数据，设置车辆List
		setBusFromJson(buses, infoOfStationJson);
		
		return buses;
	}
	
	/** --------站点查询相关-------- **/
	/**
	 * 从网络上取得指定站点的车辆信息
	 * @param busStationId
	 * @return
	 */
	@SuppressWarnings("finally")
	private static String getInfoOfStationFromNet(Context context, String busStationId) {
		
		String infoOfStationJson = null;
		
		// 设置参数
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(BUSSTATIONKEY, busStationId);
		
		// 捕获异常
		try{
			// 访问网络
			infoOfStationJson = 
					BaseHttpClient.doGetRequest(context.getString(R.string.comm_get_bus_station), params);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return infoOfStationJson;
		}
	}
	
	/**
	 * 设置Json数据为车辆信息
	 * @param buses
	 * @param json
	 * @throws JSONException 
	 */
	private static void setBusFromJson(List<NetStation> buses, String json) throws JSONException {
		
		JSONObject busesInfo = new JSONObject(json);
		
		// 取得车辆数组
		JSONArray busesArr = busesInfo.getJSONArray("reportInfoList");
		
		// 非空
		if(busesArr != null) {
			
			// 遍历数组，设置Bus
			for (int i = 0; i < busesArr.length(); i++) {
				
				JSONObject busJ = busesArr.getJSONObject(i);
				
				NetStation netStation = new NetStation();
				
				netStation.setRouteId(busJ.getString("routeId"));
				netStation.setLastName(busJ.getString("lastName"));
				netStation.setRouteName(busJ.getString("routeName"));
				
				// 取得经过的车辆信息
				List<NetStationItem> stationItems = getStationItems(busJ.getString("stationInfoNoList"));
				netStation.setStationItems(stationItems);
				
				// 添加Bus到数组
				buses.add(netStation);
			}
		}
		
		Log.d("debug", "json > " + json);
	}
	
	/**
	 * 设置经过的车辆信息
	 * @param jsonItem
	 * @return
	 * @throws JSONException 
	 */
	private static List<NetStationItem> getStationItems(String jsonItem) throws JSONException {
		
		List<NetStationItem> stationItems = new ArrayList<NetStationItem>();
		
		JSONArray busItemArr = new JSONArray(jsonItem);
		
		if(busItemArr != null && busItemArr.length() > 0) {
			
			// 遍历数组，设置Bus Item
			for (int i = 0; i < busItemArr.length(); i++) {
				JSONObject netStationItemJ = busItemArr.getJSONObject(i);
				
				NetStationItem stationItem = new NetStationItem();
				stationItem.setDistance(netStationItemJ.getString("distance"));
				stationItem.setItemIndex(netStationItemJ.getString("itemIndex"));
				stationItem.setLicence(netStationItemJ.getString("licence"));
				stationItem.setNextId(netStationItemJ.getString("nextId"));
				stationItem.setNextName(netStationItemJ.getString("nextName"));
				stationItem.setStopCount(netStationItemJ.getString("stopCount"));
				stationItem.setTime(netStationItemJ.getString("time"));
				
				stationItems.add(stationItem);
			}
		}
		
		return stationItems;
	}
	
	
	/** --------线路查询相关-------- **/
	/**
	 * 取得指定车辆的站点ID信息
	 * @param busRouteId
	 * @return
	 * @throws JSONException 
	 */
	public static List<NetLine> getInfoOfRoute(Context context, String busRouteId) throws JSONException {
		
		List<NetLine> stationIds = null;
		
		// 网络上加载站点信息
		String infoOfStationJson = getInfoOfRouteFromNet(context, busRouteId);
		
		// 未去到网上的站点信息
		if(StringUtils.isBlank(infoOfStationJson)) {
			return null;
		}
		
		Log.d("debug", "json > " + infoOfStationJson);
		
		// 解析Json数据，设置站点List
		stationIds = setRouteFromJson(infoOfStationJson);
		
		return stationIds;
	}
	
	/**
	 * 从网络上取得指定车辆的站点ID信息
	 * @param busStationId
	 * @return
	 */
	@SuppressWarnings("finally")
	private static String getInfoOfRouteFromNet(Context context, String busRouteId) {
		
		String infoOfStationJson = null;
		
		// 设置参数
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(ROUTEIDKEY, busRouteId);
		
		// 捕获异常
		try{
			// 访问网络
			infoOfStationJson = 
					BaseHttpClient.doGetRequest(context.getString(R.string.comm_get_route_bus_info), params);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return infoOfStationJson;
		}
	}
	
	/**
	 * 设置Json数据为站点ID信息
	 * @param json
	 * @throws JSONException 
	 */
	private static List<NetLine> setRouteFromJson(String json) throws JSONException {
		
		List<NetLine> stationIds = new ArrayList<NetLine>();
		
		// JSON
		JSONObject stationInfo = new JSONObject(json);
		
		// 取得车辆数组
		JSONArray stationArr = stationInfo.getJSONArray("stopNoList");
		
		// 非空
		if(stationArr != null) {
			
			// 遍历数组，设置Bus
			for (int i = 0; i < stationArr.length(); i++) {
				
				JSONObject stationJ = stationArr.getJSONObject(i);
				
				NetLine netLine = new NetLine();
				
				netLine.setDistance(stationJ.getString("distance"));
				netLine.setLatitude(stationJ.getString("latitude"));
				netLine.setLicence(stationJ.getString("licence"));
				netLine.setLongitude(stationJ.getString("longitude"));
				netLine.setNextStopId(stationJ.getString("nextStopId"));
				netLine.setNextStopNo(stationJ.getString("nextStopNo"));
				netLine.setTime(stationJ.getString("time"));

				stationIds.add(netLine);
			}
		}
		
		Log.d("debug", "stationIds > " + stationIds);
		
		return stationIds;
	}

	/** --------换乘相关-------- **/
	/**
	 * 取得指定站点的换乘信息
	 * @param busStopIdFrom
	 * 	 * @param busStopIdTo
	 * @return
	 * @throws JSONException 
	 */
	public static List<Huancheng> getInfoOfHuancheng(Context context, String busStopIdFrom, String busStopIdTo) throws JSONException {
		
		List<Huancheng> huanchengs = new ArrayList<Huancheng>();
		
		// 网络上加载站点信息
		String infoOfHuanchengJson = getInfoOfHuanchengFromNet(context, busStopIdFrom, busStopIdTo);
		
		Log.d("debug", "json > " + infoOfHuanchengJson);
		
		// 未去到网上的站点信息
		if(StringUtils.isBlank(infoOfHuanchengJson)) {
			return null;
		}
		
		// 组中对象
		huanchengs = setHuanhchengFromJson(infoOfHuanchengJson);
		
		return huanchengs;
	}
	
	/**
	 * 从网络上取得换乘信息
	 * @param busStopIdFrom
	 * 	 * @param busStopIdTo
	 * @return
	 */
	@SuppressWarnings("finally")
	private static String getInfoOfHuanchengFromNet(Context context, String busStopIdFrom, String busStopIdTo) {
		
		String infoOfStationJson = null;
		
		// 设置参数
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(HUANGCHENG_FROM_KEY, busStopIdFrom);
		params.put(HUANGCHENG_TO_KEY, busStopIdTo);
		
		// 捕获异常
		try{
			// 访问网络
			infoOfStationJson = 
					BaseHttpClient.doGetRequest(context.getString(R.string.comm_get_change_bus_info), params);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return infoOfStationJson;
		}
	}
	
	/**
	 * 设置Json数据为换乘信息
	 * @param json
	 * @throws JSONException 
	 */
	private static List<Huancheng> setHuanhchengFromJson(String json) throws JSONException {
		
		List<Huancheng> huanchengs = new ArrayList<Huancheng>();
		
		// JSON
		JSONObject huanchengInfo = new JSONObject(json);
		
		// 取得换乘数组
		JSONArray huanchengArr = huanchengInfo.getJSONArray("changeList");
		
		// 非空
		if(huanchengArr != null) {
			
			// 遍历数组，设置Bus
			for (int i = 0; i < huanchengArr.length(); i++) {
				
				JSONObject stationJ = huanchengArr.getJSONObject(i);

				// 设置换乘对象
				Huancheng huancheng = new Huancheng();
				
				huancheng.setDisNo1(stationJ.getString("disNo1"));
				huancheng.setDisNo2(stationJ.getString("disNo2"));
				huancheng.setDisNo3(stationJ.getString("disNo3"));
				huancheng.setRouteId1(stationJ.getString("routeId1"));
				huancheng.setRouteId2(stationJ.getString("routeId2"));
				huancheng.setRouteId3(stationJ.getString("routeId3"));
				huancheng.setStopId1(stationJ.getString("stopId1"));
				huancheng.setStopId2(stationJ.getString("stopId2"));
				
				huanchengs.add(huancheng);
			}
		}
		
		Log.d("debug", "huanchengs > " + huanchengs.toString());
		
		return huanchengs;
	}
}

package com.xundian360.huaqiaotong.util.b02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.b02.Bick;
import com.xundian360.huaqiaotong.modle.b02.Texi;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.modle.com.SerializableList;
import com.xundian360.huaqiaotong.util.BaiduUtil;
import com.xundian360.huaqiaotong.util.BaseHttpClient;
import com.xundian360.huaqiaotong.util.StringUtils;

public class B02BaiduUtil extends BaiduUtil{
	
	/**
	 * 取得车集散点信息
	 * @param context
	 * @return
	 */
	@SuppressWarnings("finally")
	public static SerializableList getCarsPoint(Context context) {
		
		// 返回值
		SerializableList carsPint = new SerializableList();
		
		// 访问URL
		String url = context.getString(R.string.baidu_place_cars_point);
		
		try{
			// 取得黑车点数据
			String carsPointJson = BaseHttpClient.doGetRequest(url, null);
			Log.d("debug", carsPointJson);
			
			// 设置Json数据到对象
			carsPint = getCarsPointFromJson(carsPointJson);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
			return carsPint;
		}
	}
	
	/**
	 * 取得车集散点信息
	 * @param context
	 * @return
	 */
	@SuppressWarnings("finally")
	public static List<Texi> getCarsList(Context context, String zoonId) {
		
		List<Texi> taxis = null;
		
		// 访问URL
		String url = context.getString(R.string.get_taxi_list);
		
		try{
			
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("taxiAddId", zoonId);
			
			// 取得黑车点数据
			String carsJson = BaseHttpClient.doGetRequest(url, params);
			Log.d("debug", carsJson);
			
			// 设置Json数据到对象
			taxis = getCarsFromJson(carsJson);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return taxis;
		}
	}
	
	/**
	 * 取得自行车列表
	 * @param context
	 * @return
	 */
	@SuppressWarnings("finally")
	public static SerializableList getBickList(Context context) {
		// 返回值
		SerializableList bickList = new SerializableList();
		
		// 访问URL
		String url = context.getString(R.string.baidu_place_yongjiu_bick_point);
		
		try{
			// 取得自行车数据
			String bickJson = BaseHttpClient.doGetRequest(url, null);
			Log.d("debug", bickJson);
			
			// 设置Json数据到对象
			bickList = getBickListJson(bickJson);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return bickList;
		}
	}
	
	/**
	 * 从Json数据中取得自行车列表
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	@SuppressWarnings("finally")
	private static SerializableList getBickListJson(String json)
			throws JSONException {
		// 返回值
		SerializableList bickList = new SerializableList();

		// 解析JSON数据
		JSONObject bickListJson = new JSONObject(json);

		// 查询状态
		String status = bickListJson.getString("status");
		// 结果集
		JSONArray bickListResults = bickListJson.getJSONArray("pois");

		try {

			// 当查询成功，并且结果集部为空的时候
			if (KTV_STATUS_OK_KEY.equals(status) && bickListResults != null
					&& bickListResults.length() > 0) {

				// 遍历，设置JSON数据到对象
				for (int i = 0; i < bickListResults.length(); i++) {

					JSONObject bickItemJson = bickListResults.getJSONObject(i);

					// 设置查询的自行车
					Bick bickItem = new Bick();

					bickItem.setUid(bickItemJson.getString("id"));
					bickItem.setName(bickItemJson.getString("title"));
					bickItem.setAddress(bickItemJson.getString("address"));
					bickItem.setLastNum(bickItemJson.getInt("last_num"));
					bickItem.setAllNum(bickItemJson.getInt("all_num"));

					// 设置位置
					JSONArray locationJ = bickItemJson.getJSONArray("location");
					if (locationJ != null) {
						bickItem.setLocation_lat(StringUtils.paseDouble(
								locationJ.get(1).toString(), 0));
						bickItem.setLocation_lng(StringUtils.paseDouble(
								locationJ.get(0).toString(), 0));
					}

					// 添加到列表
					bickList.add(bickItem);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return bickList;
		}
	}

	/**
	 * 从Json数据中获得车集散点信息
	 * @param json
	 * @return
	 * @throws JSONException 
	 */
	private static SerializableList getCarsPointFromJson(String json) throws JSONException {
		
		// 返回值
		SerializableList carsPint = new SerializableList();
		
		// 解析JSON数据
		JSONObject carsPointJson = new JSONObject(json);
		
		// 查询状态
		String status = carsPointJson.getString("status");
		// 结果集
		JSONArray carsPointResults = carsPointJson.getJSONArray("pois");
		
		// 当查询成功，并且结果集部为空的时候
		if(KTV_STATUS_OK_KEY.equals(status) 
				&& carsPointResults != null
				&& carsPointResults.length() > 0) {
			
			// 遍历，设置JSON数据到对象
			for (int i = 0; i < carsPointResults.length(); i++) {
				
				JSONObject carPointItem = carsPointResults.getJSONObject(i);
				
				// 设置查询的百度对象
				Baidu baiduItem = new Baidu();
				
				baiduItem.setUid(carPointItem.getString("id"));
				baiduItem.setName(carPointItem.getString("title"));
				baiduItem.setAddress(carPointItem.getString("address"));
				
				// 设置位置
				JSONArray locationJ = carPointItem.getJSONArray("location");
				if(locationJ != null) {
					baiduItem.setLocation_lat(
							StringUtils.paseDouble(locationJ.get(1).toString(), 0));
					baiduItem.setLocation_lng(
							StringUtils.paseDouble(locationJ.get(0).toString(), 0));
				}
				
				// 添加到列表
				carsPint.add(baiduItem);
			}
		}
		
		return carsPint;
	}
	
	/**
	 * 取得车辆信息
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	private static List<Texi> getCarsFromJson(String json) throws JSONException {
		
		List<Texi> taxis = new ArrayList<Texi>();
		
		// 解析JSON数据
		JSONObject carsJson = new JSONObject(json);
		
		// 查询状态
		String status = carsJson.getString("status");
		
		// 结果集
		JSONArray carsResults = carsJson.getJSONArray("results");
		
		// 当查询成功，并且结果集部为空的时候
		if(KTV_STATUS_OK_KEY.equals(status) 
				&& carsResults != null
				&& carsResults.length() > 0) {
			
			// 遍历，设置JSON数据到对象
			for (int i = 0; i < carsResults.length(); i++) {
				
				JSONObject carItem = carsResults.getJSONObject(i);
				
				// 设置出租车信息
				Texi taxi = new Texi();
				taxi.setId(carItem.getString("id"));
				taxi.setName(carItem.getString("taxi_name"));
				taxi.setAdd(carItem.getString("taxi_add"));
				taxi.setPhone(carItem.getString("taxi_tel"));
				taxi.setCarType(carItem.getString("taxi_car"));
				taxi.setTime(carItem.getString("taxi_time"));
				taxi.setCom(carItem.getString("taxi_com"));
				
				taxis.add(taxi);
			}
		}
		
		return taxis;
	}
	
}

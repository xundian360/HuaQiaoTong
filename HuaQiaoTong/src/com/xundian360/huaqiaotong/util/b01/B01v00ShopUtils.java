/**
 * 
 */
package com.xundian360.huaqiaotong.util.b01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.util.BaiduUtil;
import com.xundian360.huaqiaotong.util.BaseHttpClient;
import com.xundian360.huaqiaotong.util.JsonUtil;
import com.xundian360.huaqiaotong.util.StringUtils;

/**
 * 服务器商店帮助类
 * @author  TengTeng
 * @date      2013-10-4
 * @version 1.0
 */
public class B01v00ShopUtils extends BaiduUtil{
	
	// 描述分隔符
	private static final String DISC_DELIMITER = "|||";
	// 换行符
	private static final String NEWLINE = "\n";
	
	/**
	 * 取得商店信息列表
	 * @param context
	 * @param key     检索关键字
	 *  饭店：10001
	 *	KTV：10002
	 *  宾馆：10003
	 *	电影院：10004
	 *	健身房：10005
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public static Map<String, Object> getShopList(Context context, String key, int pageSize, int pageNum) {
		// 访问URL
		String url = context.getString(R.string.comm_get_shop_list);
		
		// 设置参数
		HashMap<String, String> parameter = new HashMap<String, String>();
		parameter.put("key", key );
		parameter.put("pageSize", pageSize + "");
		parameter.put("pageNum", pageNum + "");
		
		// 访问百度，设置参数
		Map<String, Object> ktvs = searchShop(context, url, parameter);
		
		return ktvs;
	}
	
	/**
	 * 取得商店信息列表
	 * @param context
	 * @param key     检索关键字
	 * @param minPrice
	 * @param maxPrice
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public static Map<String, Object> getShopListByPrice(Context context, 
			String key, String minPrice, 
			String maxPrice, int pageSize, int pageNum) {
		
		// 访问URL
		String url = context.getString(R.string.comm_get_shop_list_by_price);
		
		// 设置参数
		HashMap<String, String> parameter = new HashMap<String, String>();
		parameter.put("key", key );
		parameter.put("minPrice", minPrice + "");
		parameter.put("maxPrice", maxPrice + "");
		parameter.put("pageSize", pageSize + "");
		parameter.put("pageNum", pageNum + "");
		
		// 访问百度，设置参数
		Map<String, Object> ktvs = searchShop(context, url, parameter);
		
		return ktvs;
	}
	
	/**
	 * 取得信息列表（关注数量）
	 * @param context
	 * @param key     检索关键字
	 * @param minPrice
	 * @param maxPrice
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public static Map<String, Object> getShopListByAttention(Context context, 
			String key, String minL, 
			String maxL, int pageSize, int pageNum) {
		
		// 访问URL
		String url = context.getString(R.string.comm_get_shop_list_by_price);
		
		// 设置参数
		HashMap<String, String> parameter = new HashMap<String, String>();
		parameter.put("key", key );
		parameter.put("minL", minL + "");
		parameter.put("maxL", maxL + "");
		parameter.put("pageSize", pageSize + "");
		parameter.put("pageNum", pageNum + "");
		
		// 访问百度，设置参数
		Map<String, Object> ktvs = searchShop(context, url, parameter);
		
		return ktvs;
	}
	
	/**
	 * 取得商店信息列表（关键字检索）
	 * @param context
	 * @param key     检索关键字
	 * @param minPrice
	 * @param maxPrice
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public static Map<String, Object> getShopListByTag(Context context, 
			String key, String searchText, int pageSize, int pageNum) {
		
		// 访问URL
		String url = context.getString(R.string.get_search_shop_list);
		
		// 设置参数
		HashMap<String, String> parameter = new HashMap<String, String>();
		parameter.put("key", key );
		parameter.put("searchText", searchText + "");
		parameter.put("pageSize", pageSize + "");
		parameter.put("pageNum", pageNum + "");
		
		// 访问百度，设置参数
		Map<String, Object> ktvs = searchShop(context, url, parameter);
		
		return ktvs;
	}
	
	/**
	 *  KTV检索
	 * @param URL              访问URL
	 * @param parameter  参数
	 */
	@SuppressWarnings("finally")
	private static Map<String, Object> searchShop(Context context, String url, HashMap<String, String> parameter) {
		
		// 返回值
		Map<String, Object> ktvs = new HashMap<String, Object>();
		
		try {
			// 请求百度服务器，返回JSON
			String ktvJson = BaseHttpClient.doGetRequest(url, parameter);
			Log.d("debug", ktvJson);
		
			// 处理JSON，返回组装对象
			ktvs = getShopFromJson(ktvJson);
		} catch (Exception e) {
			// 异常处理
			ktvs = null;
			e.printStackTrace();
		} finally {
			return ktvs;
		}
	}
	
	/**
	 * 从JSON中取得KTV对象和总数量
	 * @param ktvJson
	 * @return
	 * @throws JSONException 
	 */
	private static Map<String, Object> getShopFromJson(String ktvJsonString) throws JSONException {
		
		// 返回值
		Map<String, Object> ktvs = new HashMap<String, Object>();
		
		// 解析JSON数据
		JSONObject ktvJson = new JSONObject(ktvJsonString);
		
		// 取得检索的总数量
		String totalNum = ktvJson.getString("total");
		// 检索数量添加到返回值
		ktvs.put(TOTAL_KEY, totalNum);

		JSONArray ktvResults = ktvJson.getJSONArray("results"); 
		
		// 非空
		if(ktvResults != null) {
			
			// KTV 数组
			List<Baidu> ktvList = new ArrayList<Baidu>();
			
			// 遍历，设置KTV 
			for (int i = 0; i < ktvResults.length(); i++) {
				
				JSONObject ktvJ = ktvResults.getJSONObject(i);
				
				// 设置KTV对象
				Baidu ktv = new Baidu();
				ktv.setName(JsonUtil.getString(ktvJ, "name"));
				ktv.setAddress(JsonUtil.getString(ktvJ, "address"));
				ktv.setTelephone(JsonUtil.getString(ktvJ, "tel"));
				ktv.setUid(JsonUtil.getString(ktvJ, "id"));
				ktv.setTime(JsonUtil.getString(ktvJ, "shop_time"));
				
				// 设置位置
				ktv.setLocation_lat(StringUtils.paseDouble(JsonUtil.getString(ktvJ, "location_w"), 0));
				ktv.setLocation_lng(StringUtils.paseDouble(JsonUtil.getString(ktvJ, "location_j"), 0));
				
				ktv.setPrice(JsonUtil.getString(ktvJ, "shop_money"));
				ktv.setOverall_rating(JsonUtil.getString(ktvJ, "attention"));
				ktv.setShop_pic_soulue(JsonUtil.getString(ktvJ, "shop_pic_soulue"));
				
				String shop_des = JsonUtil.getString(ktvJ, "shop_des");
				shop_des = shop_des.replace(DISC_DELIMITER, NEWLINE);
				
				ktv.setDisc_tittle(shop_des);
				ktv.setDisc(shop_des);
				
				// 添加到数组
				ktvList.add(ktv);
			}
			
			// KTV对象添加到返回值
			ktvs.put(RESULTS_KEY, ktvList);
		}
		return ktvs;
	}
	
}

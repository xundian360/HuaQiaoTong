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
import com.xundian360.huaqiaotong.util.JsonUtil;
import com.xundian360.huaqiaotong.util.StringUtils;

/**
 * KTV百度API帮助类
 * 
 * @author TengTeng
 * @date 2013-10-4
 * @version 1.0
 */
public class B01v00BauduUtil extends BaiduUtil {

	/**
	 * 城市内检索
	 * 
	 * @param context
	 * @param pageSize
	 *            返回记录数量，默认为10条记录，最大返回结果为20条
	 * @param pageNum
	 *            分页页码，默认为0,0代表第一页，1代表第二页，以此类推。
	 * @param region
	 *            检索区域，如果取值为“全国”或某省份，则返回指定区域的POI
	 * @return
	 */
	public static Map<String, Object> searchRegionKtv(Context context,
			String pageSize, String pageNum, String region) {

		// 访问URL
		String url = context.getString(R.string.baidu_place_v2_search_url);

		// 设置参数
		HashMap<String, String> parameter = new HashMap<String, String>();
		parameter.put("output", OUTPUT_KEY);
		parameter.put("query", QUERY_KEY);
		parameter.put("page_size", pageSize);
		parameter.put("page_num", pageNum);
		parameter.put("scope", SCOPE_KEY);
		parameter.put("region", region);

		// 访问百度，设置参数
		Map<String, Object> ktvs = searchKtv(context, url, parameter);

		return ktvs;
	}

	/**
	 * 矩形区域内检索
	 * 
	 * @param context
	 * @param pageSize
	 *            返回记录数量，默认为10条记录，最大返回结果为20条
	 * @param pageNum
	 *            分页页码，默认为0,0代表第一页，1代表第二页，以此类推。
	 * @param bounds
	 *            检索矩形区域（39.915,116.404,39.975,116.414）
	 * @return
	 */
	public static Map<String, Object> searchBoundsKtv(Context context,
			String query, String pageSize, String pageNum, String bounds) {

		// 访问URL
		String url = context.getString(R.string.baidu_place_v2_search_url);

		// 设置参数
		HashMap<String, String> parameter = new HashMap<String, String>();
		parameter.put("output", OUTPUT_KEY);
		parameter.put("query", query);
		parameter.put("page_size", pageSize);
		parameter.put("page_num", pageNum);
		parameter.put("scope", SCOPE_KEY);
		parameter.put("bounds", bounds);

		// 访问百度，设置参数
		Map<String, Object> ktvs = searchKtv(context, url, parameter);

		return ktvs;
	}

	/**
	 * 圆形区域内检索
	 * 
	 * @param context
	 * @param query
	 *            检索关键字
	 * @param pageSize
	 *            返回记录数量，默认为10条记录，最大返回结果为20条
	 * @param pageNum
	 *            分页页码，默认为0,0代表第一页，1代表第二页，以此类推。
	 * @param location
	 *            周边检索中心点（39.915,116.404）
	 * @param radius
	 *            周边检索半径，单位为米
	 * @return
	 */
	public static Map<String, Object> searchRadiusKtv(Context context,
			String query, String pageSize, String pageNum, String location,
			String radius) {

		// 访问URL
		String url = context.getString(R.string.baidu_place_v2_search_url);

		// 设置参数
		HashMap<String, String> parameter = new HashMap<String, String>();
		parameter.put("output", OUTPUT_KEY);
		parameter.put("query", query);
		parameter.put("page_size", pageSize);
		parameter.put("page_num", pageNum);
		parameter.put("scope", SCOPE_KEY);
		parameter.put("location", location);
		parameter.put("radius", radius);

		// 访问百度，设置参数
		Map<String, Object> ktvs = searchKtv(context, url, parameter);

		return ktvs;
	}

	/**
	 * KTV检索
	 * 
	 * @param URL
	 *            访问URL
	 * @param parameter
	 *            参数
	 */
	@SuppressWarnings("finally")
	private static Map<String, Object> searchKtv(Context context, String url,
			HashMap<String, String> parameter) {

		// 返回值
		Map<String, Object> ktvs = new HashMap<String, Object>();

		try {
			// 请求百度服务器，返回JSON
			String ktvJson = searchBaudi(context, url, parameter);
			Log.d("debug", ktvJson);

			// 处理JSON，返回组装对象
			ktvs = getKtvFromJson(ktvJson);
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
	 * 
	 * @param ktvJson
	 * @return
	 * @throws JSONException
	 */
	private static Map<String, Object> getKtvFromJson(String ktvJsonString)
			throws JSONException {

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
		if (ktvResults != null) {

			// KTV 数组
			List<Baidu> ktvList = new ArrayList<Baidu>();

			// 遍历，设置KTV
			for (int i = 0; i < ktvResults.length(); i++) {

				JSONObject ktvJ = ktvResults.getJSONObject(i);

				// 设置KTV对象
				Baidu ktv = new Baidu();
				ktv.setName(JsonUtil.getString(ktvJ, "name"));
				ktv.setAddress(JsonUtil.getString(ktvJ, "address"));
				ktv.setTelephone(JsonUtil.getString(ktvJ, "telephone"));
				ktv.setUid(JsonUtil.getString(ktvJ, "uid"));

				// 设置位置
				JSONObject locationJ = ktvJ.getJSONObject("location");
				if (locationJ != null) {
					ktv.setLocation_lat(StringUtils.paseDouble(
							JsonUtil.getString(locationJ, "lat"), 0));
					ktv.setLocation_lng(StringUtils.paseDouble(
							JsonUtil.getString(locationJ, "lng"), 0));
				}

				// KTV详细信息
				JSONObject detail = ktvJ.getJSONObject("detail_info");
				if (detail != null) {
					ktv.setDistance(JsonUtil.getString(detail, "distance"));
					ktv.setDetail_url(JsonUtil.getString(detail, "detail_url"));
					ktv.setPrice(JsonUtil.getString(detail, "price"));
					ktv.setOverall_rating(JsonUtil.getString(detail,
							"overall_rating"));
					ktv.setComment_num(JsonUtil
							.getString(detail, "comment_num"));
					ktv.setImage_num(JsonUtil.getString(detail, "image_num"));
				}

				// 添加到数组
				ktvList.add(ktv);
			}

			// KTV对象添加到返回值
			ktvs.put(RESULTS_KEY, ktvList);
		}

		return ktvs;
	}

}

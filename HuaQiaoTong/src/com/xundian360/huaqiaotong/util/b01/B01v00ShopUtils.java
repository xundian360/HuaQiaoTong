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
import com.xundian360.huaqiaotong.modle.com.BaiduComment;
import com.xundian360.huaqiaotong.util.BaiduUtil;
import com.xundian360.huaqiaotong.util.BaseHttpClient;
import com.xundian360.huaqiaotong.util.JsonUtil;
import com.xundian360.huaqiaotong.util.StringUtils;

/**
 * 服务器商店帮助类
 * 
 * @author TengTeng
 * @date 2013-10-4
 * @version 1.0
 */
public class B01v00ShopUtils extends BaiduUtil {

	// 描述分隔符
	private static final String DISC_DELIMITER = "|||";
	// 换行符
	private static final String NEWLINE = "\n";

	/**
	 * 取得商店信息列表
	 * 
	 * @param context
	 * @param key
	 *            检索关键字 饭店：10001 KTV：10002 宾馆：10003 电影院：10004 健身房：10005
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public static Map<String, Object> getShopList(Context context, String key,
			int pageSize, int pageNum) {
		// 访问URL
		String url = context.getString(R.string.comm_get_shop_list);

		// 设置参数
		HashMap<String, String> parameter = new HashMap<String, String>();
		parameter.put("key", key);
		parameter.put("pageSize", pageSize + "");
		parameter.put("pageNum", pageNum + "");

		// 访问百度，设置参数
		Map<String, Object> ktvs = searchShop(context, url, parameter);

		return ktvs;
	}

	/**
	 * 取得商店信息列表
	 * 
	 * @param context
	 * @param key
	 *            检索关键字
	 * @param minPrice
	 * @param maxPrice
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public static Map<String, Object> getShopListByPrice(Context context,
			String key, String minPrice, String maxPrice, int pageSize,
			int pageNum) {

		// 访问URL
		String url = context.getString(R.string.comm_get_shop_list_by_price);

		// 设置参数
		HashMap<String, String> parameter = new HashMap<String, String>();
		parameter.put("key", key);
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
	 * 
	 * @param context
	 * @param key
	 *            检索关键字
	 * @param minPrice
	 * @param maxPrice
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public static Map<String, Object> getShopListByAttention(Context context,
			String key, String minL, String maxL, int pageSize, int pageNum) {

		// 访问URL
		String url = context
				.getString(R.string.comm_get_shop_list_by_attention);

		// 设置参数
		HashMap<String, String> parameter = new HashMap<String, String>();
		parameter.put("key", key);
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
	 * 
	 * @param context
	 * @param key
	 *            检索关键字
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
		parameter.put("key", key);
		parameter.put("searchText", searchText + "");
		parameter.put("pageSize", pageSize + "");
		parameter.put("pageNum", pageNum + "");

		// 访问百度，设置参数
		Map<String, Object> ktvs = searchShop(context, url, parameter);

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
	private static Map<String, Object> searchShop(Context context, String url,
			HashMap<String, String> parameter) {

		// 返回值
		Map<String, Object> ktvs = new HashMap<String, Object>();

		try {
			// 请求百度服务器，返回JSON
			String ktvJson = BaseHttpClient.doPostRequest(url, parameter);
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
	 * 取得商店头图图片信息
	 * 
	 * @param context
	 * @param shopId
	 *            检索关键字
	 * @return
	 */
	public static List<String> getShopTittleImg(Context context, String shopId) {

		List<String> imgs = new ArrayList<String>();

		// 设置请求参数，取得请求数据
		HashMap<String, String> parameter = new HashMap<String, String>();
		parameter.put("shopId", shopId);

		try {

			String picsJsonS = BaseHttpClient.doPostRequest(
					context.getString(R.string.get_shop_tittle_img_list),
					parameter);

			System.out.println("picsJsonS > " + picsJsonS);

			if (StringUtils.isBlank(picsJsonS)) {
				return imgs;
			}

			// 解析JSON数据
			JSONObject picsJson = new JSONObject(picsJsonS);

			JSONArray picResults = picsJson.getJSONArray("results");

			// 非空
			if (picResults != null) {

				// 遍历，设置图片路径
				for (int i = 0; i < picResults.length(); i++) {

					JSONObject picJ = picResults.getJSONObject(i);

					String picItem = JsonUtil.getString(picJ, "img");

					imgs.add(picItem);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return imgs;
		} catch (Exception e) {
			e.printStackTrace();
			return imgs;
		}

		return imgs;
	}

	/**
	 * 取得商店评论列表
	 * 
	 * @param context
	 * @param getShopPlByShop
	 *            取得商店评论
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public static Map<String, Object> getShopPlList(Context context,
			String shopId) {
		// 访问URL
		String url = context.getString(R.string.get_shop_pl_list);

		// 设置参数
		HashMap<String, String> parameter = new HashMap<String, String>();
		parameter.put("shopId", shopId);

		// 访问百度，设置参数
		Map<String, Object> shopPl = searchShopPl(url, parameter);

		return shopPl;
	}

	/**
	 * 商店评论检索
	 * 
	 * @param URL
	 *            访问URL
	 * @param parameter
	 *            参数
	 */
	private static Map<String, Object> searchShopPl(String url,
			HashMap<String, String> parameter) {

		// 返回值
		Map<String, Object> shopPl = new HashMap<String, Object>();

		try {
			// 请求百度服务器，返回JSON
			String plJson = BaseHttpClient.doPostRequest(url, parameter);

			System.out.println("plJson > " + plJson);

			if (StringUtils.isBlank(plJson)) {
				return null;
			}

			// 解析JSON数据
			// 解析JSON数据
			JSONObject ktvJson = new JSONObject(plJson);

			// 取得检索的总数量
			String totalNum = ktvJson.getString("total");
			// 检索数量添加到返回值
			shopPl.put(TOTAL_KEY, totalNum);

			// 评论列表
			JSONArray plResults = ktvJson.getJSONArray("results");

			// 非空
			if (plResults != null) {

				// 评论数组
				List<BaiduComment> baiduComments = new ArrayList<BaiduComment>();

				// 遍历，设置评论
				for (int i = 0; i < plResults.length(); i++) {

					JSONObject commentJ = plResults.getJSONObject(i);

					// 设置评论对象
					BaiduComment commentItem = new BaiduComment();
					commentItem.setId(JsonUtil.getString(commentJ, "id"));
					commentItem.setShopId(JsonUtil.getString(commentJ,
							"shop_id"));
					commentItem.setUserId(JsonUtil.getString(commentJ,
							"user_id"));
					commentItem.setUserName(JsonUtil.getString(commentJ,
							"user_name"));
					commentItem.setUserLogoPath(JsonUtil.getString(commentJ,
							"user_logo_path"));
					commentItem.setScore(JsonUtil.getString(commentJ,
							"shop_score"));
					commentItem.setDic(JsonUtil.getString(commentJ,
							"shop_comment"));
					commentItem.setTime(JsonUtil.getString(commentJ, "time"));

					baiduComments.add(commentItem);
				}

				// 添加评论列表到返回值
				shopPl.put(RESULTS_KEY, baiduComments);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return shopPl;
		}

		return shopPl;
	}

	/**
	 * 添加商店评论
	 * 
	 * @param context
	 * @param shopId
	 *            商店ID
	 * @param userId
	 *            用户ID
	 * @param shopScore
	 *            评分
	 * @param shopComment
	 *            评论内容
	 * @return
	 */
	@SuppressWarnings("finally")
	public static boolean addShopPingLun(Context context, String shopId,
			String userId, String shopScore, String shopComment) {

		boolean addShopPingLun = false;

		try {

			// 访问URL
			String url = context.getString(R.string.add_shop_pl);

			// 设置参数
			HashMap<String, String> parameter = new HashMap<String, String>();
			parameter.put("shopId", shopId);
			parameter.put("userId", userId);
			parameter.put("shopScore", shopScore);
			parameter.put("shopComment", shopComment);

			// 访问百度，设置参数
			String addPlReturn = BaseHttpClient.doPostRequest(url, parameter);

			// 评论成功
			if (STATUS_OK_KEY.equals(addPlReturn)) {
				addShopPingLun = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			addShopPingLun = false;
		} finally {
			return addShopPingLun;
		}
	}

	/**
	 * 从JSON中取得KTV对象和总数量
	 * 
	 * @param ktvJson
	 * @return
	 * @throws JSONException
	 */
	private static Map<String, Object> getShopFromJson(String ktvJsonString)
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
				ktv.setTelephone(JsonUtil.getString(ktvJ, "tel"));
				ktv.setUid(JsonUtil.getString(ktvJ, "id"));
				ktv.setTime(JsonUtil.getString(ktvJ, "shop_time"));

				// 设置位置
				ktv.setLocation_lat(StringUtils.paseDouble(
						JsonUtil.getString(ktvJ, "location_w"), 0));
				ktv.setLocation_lng(StringUtils.paseDouble(
						JsonUtil.getString(ktvJ, "location_j"), 0));

				ktv.setPrice(JsonUtil.getString(ktvJ, "shop_money"));
				ktv.setOverall_rating(JsonUtil.getString(ktvJ, "attention"));
				ktv.setShop_pic_soulue(JsonUtil.getString(ktvJ,
						"shop_pic_soulue"));

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

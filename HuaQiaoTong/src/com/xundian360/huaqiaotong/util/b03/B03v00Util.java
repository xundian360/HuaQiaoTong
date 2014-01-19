/**
 * 
 */
package com.xundian360.huaqiaotong.util.b03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.lidroid.xutils.http.RequestCallBack;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.b03.Posts;
import com.xundian360.huaqiaotong.modle.b03.PostsItem;
import com.xundian360.huaqiaotong.util.BaiduUtil;
import com.xundian360.huaqiaotong.util.BaseHttpClient;
import com.xundian360.huaqiaotong.util.StringUtils;

/**
 * 论坛工具类
 * @author  TengTeng
 * @date      2013年10月28日
 * @version 1.0
 * @disc
 *        论坛帖子一览
*        {	
 *            "status": 0, 	
 *            "total": 206, 	
 *            "results": [	
 *                {	
 *                    "tittle": "花桥地铁开通了", 	
 *                    "tDtail": "花桥地铁开通了花桥地铁开通了花桥地铁开通了花桥地铁开通了花桥地铁开通了", 	
 *                    "commentN": "152", 	
 *                    "author": "鬼太郎", 	
 *                    "img": "http://developer.baidu.com/map/static/img/developer980-370.jpg", 	
 *                    "uid": "befdb292767279f887154123"	
 *                }, 	
 *                {	
 *                    "tittle": "花桥地铁开通了", 	
 *                    "tDtail": "花桥地铁开通了花桥地铁开通了花桥地铁开通了花桥地铁开通了花桥地铁开通了", 	
 *                    "commentN": "152", 	
 *                    "author": "鬼太郎", 	
 *                    "img": "http://developer.baidu.com/map/static/img/developer980-370.jpg", 	
 *                    "uid": "befdb292767279f887154123"	
 *                }	
 *            ]	
 *        }	
 *        
 *        帖子明细
*        {	
 *            "status": 0, 	
 *            "tittle": "花桥地铁开通了", 	
 *            "commentN": "152", 	
 *            "author": "鬼太郎", 	
 *            "uid": "befdb292767279f887154123"	
 *            "dtailL": [	
 *                {	
 *                    "type": "txt", 	
 *                    "msg": "花桥地铁开通了花桥地铁开通了花桥地铁开通了花桥地铁开通了花桥地铁开通了"	
 *                }, 	
 *                {	
 *                    "type": "img", 	
 *                    "msg": "http://developer.baidu.com/map/static/img/developer980-370.jpg"	
 *                }	
 *            ]	
 *        }	
 * 
 */
public class B03v00Util extends BaiduUtil{
	
	/**
	 * 根据ID，设置帖子对象明细
	 * @param jsonString
	 * @return
	 * @throws JSONException 
	 */
	public static List<PostsItem> getPostsDetailData(Context context, String themeId) throws JSONException {
		
		String url = context.getString(R.string.get_theme_list);
		
		// 设置参数
		HashMap<String, String> parameter = new HashMap<String, String>();
		parameter.put("themeId", themeId );
		
		// 帖子描述数据
		String jsonString = BaseHttpClient.doPostRequest(url, parameter);
		
		Log.d("debug", "jsonString > " + jsonString);
		
		if(jsonString != null) {
			return getPostsItemListFromJson(jsonString);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("finally")
	private static List<PostsItem> getPostsItemListFromJson(String jsonString) throws JSONException {

		List<PostsItem> postsItems = new ArrayList<PostsItem>();
		
		try {
			JSONObject postsItemsJson = new JSONObject(jsonString);
			
			// 判断数据状态为OK
			if(STATUS_OK_KEY.equals(postsItemsJson.getString("status"))) {
				
				JSONArray jsonArray = postsItemsJson.getJSONArray("results");
				
				if(jsonArray != null && jsonArray.length() > 0) {
					
					for (int i = 0; i < jsonArray.length(); i++) {
						
						// 帖子Json对象
						JSONObject postsItemJson = jsonArray.getJSONObject(i);
						
						// 设置帖子对象
						PostsItem postsItem = new PostsItem();
						
						postsItem.setType(postsItemJson.getString("itemType"));
						postsItem.setMsg(postsItemJson.getString("itemMsg"));
						
						// 对象添加到List
						postsItems.add(postsItem);
					}
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return postsItems;
		}
	}
	
	/**
	 * 取得帖子描述
	 * @param context
	 * @param themeGroupId  组ID
	 * @param pageNum           页
	 * @param pageSize             每页显示
	 * @return
	 */
	public static Map<String, Object>getPostsData(Context context, 
			String themeGroupId, int pageNum, int pageSize) {
		
		String url = context.getString(R.string.get_theme_list);
		
		// 设置参数
		HashMap<String, String> parameter = new HashMap<String, String>();
		parameter.put("themeGroupId", themeGroupId );
		parameter.put("pageSize", pageSize + "");
		parameter.put("pageNum", pageNum + "");
		
		// 帖子描述数据
		String jsonString = BaseHttpClient.doPostRequest(url, parameter);
		
		Log.d("debug", "jsonString > " + jsonString);
		
		if(jsonString != null) {
			return getPostsDataFromJson(jsonString);
		} else {
			return null;
		}
	}
	
	
	/**
	 * 根据JSON数据设置贴纸对象
	 * @param jsonString 论坛List的JSON数据
	 * @return
	 * @throws JSONException 
	 */
	@SuppressWarnings("finally")
	public static Map<String, Object> getPostsDataFromJson(String jsonString) {
		
		Map<String, Object> postses = new HashMap<String, Object>();
		
		try {
			JSONObject postsListJson = new JSONObject(jsonString);
			
			// 判断数据状态为OK
			if(STATUS_OK_KEY.equals(postsListJson.getString("status"))) {
				
				// 设置总数量
				postses.put(TOTAL_KEY, postsListJson.getString("total"));
				
				// 设置帖子
				postses.put(RESULTS_KEY, getPostsListFromJson(postsListJson.getJSONArray("results")));
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return postses;
		}
	}
	
	/**
	 * 根据JSON Array设置帖子对象
	 * @param jsonArray
	 * @return
	 * @throws JSONException 
	 */
	private static List<Posts> getPostsListFromJson(JSONArray jsonArray) throws JSONException {
		
		List<Posts> postsList = new ArrayList<Posts>();
		
		// 设置帖子
		if(jsonArray != null && jsonArray.length() > 0) {
			
			for (int i = 0; i < jsonArray.length(); i++) {
				
				// 帖子Json对象
				JSONObject postsJson = jsonArray.getJSONObject(i);
				
				// 设置帖子对象
				Posts posts = new Posts();
				
				posts.setUid(postsJson.getString("themeId"));
				posts.setTittle(postsJson.getString("themeTitle"));
				posts.settDtail(postsJson.getString("themeMessage"));
				posts.setCommentN(postsJson.getString("commentCount"));
				posts.setAuthorId(postsJson.getString("userId"));
				posts.setAuthor(postsJson.getString("userName")); 
				posts.setAuthorPic(postsJson.getString("userIconPath"));
				posts.setImg(postsJson.getString("themePic"));
				posts.setTime(postsJson.getString("creatTime"));
				posts.setGroupId(postsJson.getString("groupId"));
				posts.setTopFlg(postsJson.getString("topFlg"));
				posts.setRecommendFlag(postsJson.getString("recommendFlag"));
				
				// 对象添加到List
				postsList.add(posts);
			}
		}
		
		return postsList;
	}
	
	/**
	 * 插入帖子
	 * @param context 
	 * @param userId         用户ID 
	 * @param groupId      帖子所属组
	 * @param themeTitle 帖子标题
	 * @param themeMessage
	 * <dis>
	 *	{
	 *	    "pl_sub": [
	 *	        {
	 *	            "txt": "6"
	 *	        }, 
	 *	        {
	 *	            "txt": "6"
	 *	        }, 
	 *	        {
	 *	            "img": "6"
	 *	        }
	 *	    ]
	 *	}
	 * </dis>
	 * @param callBack   上传回调
	 * @param fileKeys    上传图片Key
	 * @param filePaths   上传图片路径
	 */
	@SuppressWarnings("finally")
	public static String sendPosts(Context context,  String userId, String groupId, 
			String themeTitle, String themeMessage, 
			RequestCallBack<String> callBack, List<String> fileKeys, List<String> filePaths) {
		
		String returnVal = STATUS_ERROR_KEY;
		
		// 访问URL
		String url = context.getString(R.string.insert_theme);
		
		try{
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("userId", userId);
			params.put("groupId", groupId);
			params.put("themeTitle", themeTitle);
			params.put("themeMessage", themeMessage);
			
			// 上传数据
			BaseHttpClient.doPostRequestWithFile(url, params, fileKeys, filePaths, callBack);
			
		} catch(Exception e) {
			e.printStackTrace();
			returnVal = STATUS_ERROR_KEY;
		} finally {
			return returnVal;
		}
	}
	
	public static final int[] GROUP_KEYS = {
		R.string.b03v00_btn_item_key_0,
		R.string.b03v00_btn_item_key_1,
		R.string.b03v00_btn_item_key_2,
		R.string.b03v00_btn_item_key_3,
		R.string.b03v00_btn_item_key_4,
		R.string.b03v00_btn_item_key_5,
		R.string.b03v00_btn_item_key_6,
		R.string.b03v00_btn_item_key_7,
		R.string.b03v00_btn_item_key_8,
		R.string.b03v00_btn_item_key_9,
		R.string.b03v00_btn_item_key_10,
		R.string.b03v00_btn_item_key_11		
	};

	public static final int[] GROUP_NAMES = {
		R.string.b03v00_btn_item_0,
		R.string.b03v00_btn_item_1,
		R.string.b03v00_btn_item_2,
		R.string.b03v00_btn_item_3,
		R.string.b03v00_btn_item_4,
		R.string.b03v00_btn_item_5,
		R.string.b03v00_btn_item_6,
		R.string.b03v00_btn_item_7,
		R.string.b03v00_btn_item_8,
		R.string.b03v00_btn_item_9,
		R.string.b03v00_btn_item_10,
		R.string.b03v00_btn_item_11
	};
}

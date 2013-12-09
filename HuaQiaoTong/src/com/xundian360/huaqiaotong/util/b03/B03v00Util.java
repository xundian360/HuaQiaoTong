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

import com.xundian360.huaqiaotong.modle.b03.Posts;
import com.xundian360.huaqiaotong.modle.b03.PostsItem;
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
public class B03v00Util {
	
	// 论坛消息状态（OK）
	private static final String STATUS_OK = "0";
	public static final String POSTS_TOTAL_KEY = "posts_total_key";
	public static final String POSTS_DATA_KEY = "posts_data_key";
	
	/**
	 * 根据JSON，设置帖子对象明细
	 * @param jsonString
	 * @return
	 * @throws JSONException 
	 */
	public static Posts getPostsDetailData(String jsonString) throws JSONException {
		
		Posts posts = new Posts();
		
		JSONObject postsJson = new JSONObject(jsonString);
		
		// 判断数据状态为OK
		if(STATUS_OK.equals(postsJson.getString("status"))) {
			
			posts.setUid(postsJson.getString("uid"));
			posts.setTittle(postsJson.getString("tittle"));
			posts.setCommentN(postsJson.getString("commentN"));
			posts.setAuthor(postsJson.getString("author"));
			posts.setTime(postsJson.getString("time"));
			posts.setStatus(STATUS_OK);
			
			// 设置显示的数据明细
			posts.setDtailL(getPostsItemListFromJson(postsJson.getJSONArray("dtailL")));
		}
		
		return posts;
	}
	
	private static List<PostsItem> getPostsItemListFromJson(JSONArray jsonArray) throws JSONException {
		
		List<PostsItem> postsItems = new ArrayList<PostsItem>();
		
		if(jsonArray != null && jsonArray.length() > 0) {
			
			for (int i = 0; i < jsonArray.length(); i++) {
				
				// 帖子Json对象
				JSONObject postsItemJson = jsonArray.getJSONObject(i);
				
				// 设置帖子对象
				PostsItem postsItem = new PostsItem();
				
				postsItem.setType(postsItemJson.getString("type"));
				postsItem.setMsg(postsItemJson.getString("msg"));
				
				// 对象添加到List
				postsItems.add(postsItem);
			}
		}
		
		return postsItems;
	}
	
	/**
	 * 根据JSON数据设置贴纸对象
	 * @param jsonString 论坛List的JSON数据
	 * @return
	 * @throws JSONException 
	 */
	public static Map<String, Object> getPostsDataFromJson(String jsonString) throws JSONException {
		
		Map<String, Object> postses = new HashMap<String, Object>();
		
		JSONObject postsListJson = new JSONObject(jsonString);
		
		// 判断数据状态为OK
		if(STATUS_OK.equals(postsListJson.getString("status"))) {
			
			int total = StringUtils.paseInt(postsListJson.getString("total"), 0);
			
			// 设置总数量
			postses.put(POSTS_TOTAL_KEY, total);
			
			// 设置帖子
			postses.put(POSTS_DATA_KEY, getPostsListFromJson(postsListJson.getJSONArray("results")));
		}
		
		return postses;
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
				
				posts.setUid(postsJson.getString("uid"));
				posts.setTittle(postsJson.getString("tittle"));
				posts.settDtail(postsJson.getString("tDtail"));
				posts.setCommentN(postsJson.getString("commentN"));
				posts.setAuthor(postsJson.getString("author"));
				posts.setImg(postsJson.getString("img"));
				posts.setStatus(STATUS_OK);
				
				// 对象添加到List
				postsList.add(posts);
			}
		}
		
		return postsList;
	}

}

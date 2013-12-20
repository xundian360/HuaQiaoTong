/**
 * 
 */
package com.xundian360.huaqiaotong.util;

import java.util.HashMap;

import android.content.Context;

import com.xundian360.huaqiaotong.R;

/**
 * @author  Administrator
 * @date      2013年11月8日
 * @version 1.0
 */
public class BaiduUtil {
	
	public static final String TOTAL_KEY = "total_key";
	public static final String RESULTS_KEY = "results_key";
	
	// API访问状态，成功0
	public static final String STATUS_OK_KEY = "0";
	
	// API访问状态，错误1
	public static final String STATUS_ERROR_KEY = "0";
	
	// KTV检索关键字
	public static final String QUERY_KEY = "ktv";
	// KTV检索关键字
	public static final String QUERY_RESTAURANT_KEY = "饭馆";
	// KTV检索输出格式
	public static final String OUTPUT_KEY = "json";
	// 检索直径（米）
	public static final String RADIUS_KEY = "5000";
	// 检索结果详细程度。取值为1 或空，则返回基本信息；取值为2，返回检索POI详细信息
	public static final String SCOPE_KEY = "2";
	
	// 返回记录数量，默认为10条记录
	public static final String DEFAULT_PAGE_SIZE = "10";
	// 分页页码，默认为0
	public static final String DEFAULT_PAGE_NUM = "0";
	
	/**
	 * 检索百度数据
	 * @param context
	 * @param url
	 * @param parameter
	 * @return
	 */
	public static String searchBaudi(Context context, 
			String url, HashMap<String, String> parameter) {
		
		// 百度Key
		String baidu_key = context.getString(R.string.baidu_server_key);
		// 添加百度Key检索信息
		parameter.put("ak", baidu_key);
		
		return BaseHttpClient.doGetRequest(url, parameter);
	}

}

/**
 * 
 */
package com.xundian360.huaqiaotong.util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * JSON工具类
 * 
 * @author TengTeng
 * @date 2013-10-5
 * @version 1.0
 */
public class JsonUtil {

	/**
	 * 取得对应JSON对象的String值
	 * 
	 * @param obj
	 * @param key
	 * @return
	 * @throws JSONException
	 */
	public static String getString(JSONObject obj, String key)
			throws JSONException {

		String value = "";

		// 非空
		if (obj.has(key)) {
			value = obj.getString(key);
		}

		return value;
	}
}

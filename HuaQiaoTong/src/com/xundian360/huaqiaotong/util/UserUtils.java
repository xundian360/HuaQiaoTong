/**
 * 
 */
package com.xundian360.huaqiaotong.util;

import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.xundian360.huaqiaotong.modle.com.User;
import com.xundian360.huaqiaotong.modle.com.UserModle;

/**
 * @author  TeneTeng
 * @date      2013-12-27
 * @version 1.0
 */
public class UserUtils extends BaiduUtil {
	
	public static final String USER_ICON_200 = "200" + File.separatorChar;
	public static final String USER_ICON_170 = "170" + File.separatorChar;
	public static final String USER_ICON_100 = "100" + File.separatorChar;
	public static final String USER_ICON_64 = "64" + File.separatorChar;
	
	/**
	 * 设置User数据
	 * @param json
	 */
	@SuppressWarnings("finally")
	public static User setUser(String json) {
		
		User user = null;
		try {
			JSONObject jsonU = new JSONObject(json);
			
			Log.e("debug", "json > " +json);
			
			// 设置数据
			user = new User();
			
			user.setUserId(JsonUtil.getString(jsonU, "user_id"));
			user.setName(JsonUtil.getString(jsonU, "user_name"));
			user.setLoginName(JsonUtil.getString(jsonU, "user_login"));
			user.setLogoPath(JsonUtil.getString(jsonU, "user_pic"));
			user.setSex(StringUtils.paseInt(JsonUtil.getString(jsonU, "user_sex"), 0));
			user.setQq(JsonUtil.getString(jsonU, "user_qq"));
			user.setDisc(JsonUtil.getString(jsonU, "user_des"));
			
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			return user;
		}
	}
	
	/**
	 * 是否登录
	 * @param context
	 * @return
	 */
	public static boolean isLogin(Context context) {
		
		UserModle userModle = new UserModle(context);
		
		// 判断是否存储
		if(StringUtils.isNotBlank(userModle.user.getUserId())) {
			return true;
		} else {
			return false;
		}
	}

}

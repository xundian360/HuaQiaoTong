/**
 * 
 */
package com.xundian360.huaqiaotong.modle.com;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 用户对象（存储）
 * @author  TengTeng
 * @date      2013年11月4日
 * @version 1.0
 */
public class UserModle {
	
	private static final String PREFS_NAME = "com.xundian360.huaqiaotong.user";

	private static final String ITEM_USER_LOGINNAME = "com.xundian360.huaqiaotong.user.loginName";
	private static final String ITEM_USER_NAME = "com.xundian360.huaqiaotong.user.name";
	private static final String ITEM_USER_SEX = "com.xundian360.huaqiaotong.user.sex";
	private static final String ITEM_USER_LOCATION = "com.xundian360.huaqiaotong.user.location";
	private static final String ITEM_USER_DISC = "com.xundian360.huaqiaotong.user.disc";
	private static final String ITEM_USER_QQ = "com.xundian360.huaqiaotong.user.qq";
	private static final String ITEM_USER_COMPANY = "com.xundian360.huaqiaotong.user.company";
	
	// 存储分享信息
	SharedPreferences settings = null;
	Context context = null;
	
	// 用户
	public User user;
	
	public UserModle(Context context) {
		this.context = context;
		this.settings = context.getSharedPreferences(PREFS_NAME, 0);
		
		read();
	}
	
	public void read() {
		user = new User();
		
		user.setLoginName(settings.getString(ITEM_USER_LOGINNAME, ""));
		user.setName(settings.getString(ITEM_USER_NAME, ""));
		user.setSex(settings.getInt(ITEM_USER_SEX, -1));
		user.setLocation(settings.getString(ITEM_USER_LOCATION, ""));
		user.setDisc(settings.getString(ITEM_USER_DISC, ""));
		user.setQq(settings.getString(ITEM_USER_QQ, ""));
		user.setCompany(settings.getString(ITEM_USER_COMPANY, ""));
	}
	
	public User get() {
		return user;
	}
	
	public void save() {
		
		Editor editor = this.settings.edit();
		
		editor.putString(ITEM_USER_LOGINNAME, user.getLoginName());
		editor.putString(ITEM_USER_NAME, user.getName());
		editor.putInt(ITEM_USER_SEX, user.getSex());
		editor.putString(ITEM_USER_LOCATION, user.getLocation());
		editor.putString(ITEM_USER_DISC, user.getDisc());
		editor.putString(ITEM_USER_QQ, user.getQq());
		editor.putString(ITEM_USER_COMPANY, user.getCompany());
		
		editor.commit();
	}

}

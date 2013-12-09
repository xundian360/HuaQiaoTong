/**
 * 
 */
package com.xundian360.huaqiaotong.modle.com;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 公共对象（存储）
 * @author  TengTeng
 * @date      2013年11月4日
 * @version 1.0
 */
public class SettingModle {
	
	private static final String PREFS_NAME = "com.xundian360.huaqiaotong.setting";

	private static final String ITEM_SETTING_PUSHALIAS = "com.xundian360.huaqiaotong.setting.pushalias";
	
	// 存储分享信息
	SharedPreferences settings = null;
	Context context = null;
	
	// 推送别名
	private String pushAlias;
	
	public SettingModle(Context context) {
		this.context = context;
		this.settings = context.getSharedPreferences(PREFS_NAME, 0);
		
		read();
	}
	
	public void read() {
		pushAlias = settings.getString(ITEM_SETTING_PUSHALIAS, "");
	}
	
	public void save() {
		
		Editor editor = this.settings.edit();
		
		editor.putString(ITEM_SETTING_PUSHALIAS, pushAlias);
		editor.commit();
	}

	public String getPushAlias() {
		return pushAlias;
	}

	public void setPushAlias(String pushAlias) {
		this.pushAlias = pushAlias;
	}

}

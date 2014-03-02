/**
 * 
 */
package com.xundian360.huaqiaotong.modle.b00;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 存储历史记录
 * @author  TeneTeng
 * @date      2014-2-25
 * @version 1.0
 */
public class BusSavingModle {
	
	public static final String SEPARATOR = ",";
	public static final String ZHANDIN_SEPARATOR = "，";
	
	private static final String BUS_SAVING_KEY = "com.xundian360.huaqiaotong.bus.key";
	
	private static final String BUS_SAVING_XIANLU_KEY = "com.xundian360.huaqiaotong.bus.xianlu.key";
	
	private static final String BUS_SAVING_ZHANDIAN_KEY = "com.xundian360.huaqiaotong.bus.zhandian.key";
	
	private static final String BUS_SAVING_ZHANDIAN_NAME_KEY = "com.xundian360.huaqiaotong.bus.zhandian.name.key";
	
	// 线路ID
	private String xianluIds;
	// 站点ID
	private String zhandianIds;
	// 站点Name
	private String zhandianNames;
	
	// 存储分享信息
	SharedPreferences settings = null;
	Context context = null;
	
	public BusSavingModle(Context context) {
		this.context = context;
		this.settings = context.getSharedPreferences(BUS_SAVING_KEY, 0);

		read();
	}
	
	public void read() {
		xianluIds = settings.getString(BUS_SAVING_XIANLU_KEY, "");
		zhandianIds = settings.getString(BUS_SAVING_ZHANDIAN_KEY, "");
		zhandianNames = settings.getString(BUS_SAVING_ZHANDIAN_NAME_KEY, "");
	}
	
	public void save() {

		Editor editor = this.settings.edit();

		editor.putString(BUS_SAVING_XIANLU_KEY, xianluIds);
		editor.putString(BUS_SAVING_ZHANDIAN_KEY, zhandianIds);
		editor.putString(BUS_SAVING_ZHANDIAN_NAME_KEY, zhandianNames);
		editor.commit();
	}

	public String getXianluIds() {
		return xianluIds;
	}

	public void setXianluIds(String xianluIds) {
		this.xianluIds = xianluIds;
	}

	public String getZhandianIds() {
		return zhandianIds;
	}

	public void setZhandianIds(String zhandianIds) {
		this.zhandianIds = zhandianIds;
	}

	public String getZhandianNames() {
		return zhandianNames;
	}

	public void setZhandianNames(String zhandianNames) {
		this.zhandianNames = zhandianNames;
	}
	
}

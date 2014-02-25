/**
 * 
 */
package com.xundian360.huaqiaotong.activity.com;

import android.os.Bundle;

import com.baidu.mapapi.BMapManager;
import com.xundian360.huaqiaotong.R;

/**
 * 没有Tittle Map BMapManager 父类
 * 
 * @author tengteng
 * @time 上午11:54:28
 * @version 1.0
 */
public class ComNoTittleBMapManActivity extends ComNoTittleActivity {

	// 地图管理类
	public BMapManager bMapManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// 初始化地图
		HQTApplication app = (HQTApplication) this.getApplication();
		if (app.mBMapManager == null) {
			app.mBMapManager = new BMapManager(this);
			/**
			 * 如果BMapManager没有初始化则初始化BMapManager
			 */
			app.mBMapManager.init(getString(R.string.baidu_map_key),
					new HQTApplication.MyGeneralListener());
		}

		this.bMapManager = app.mBMapManager;
	}
}

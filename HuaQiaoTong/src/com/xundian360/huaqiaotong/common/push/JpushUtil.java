/**
 * 
 */
package com.xundian360.huaqiaotong.common.push;

import java.util.HashMap;
import java.util.Set;

import android.app.Application;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.com.SettingModle;
import com.xundian360.huaqiaotong.util.BaseHttpClient;
import com.xundian360.huaqiaotong.util.StringUtils;

/**
 * JPush 工具类
 * 
 * @author LiZhenteng
 * @date 2013年10月17日
 * @version 1.0
 */
public class JpushUtil {

	private static SettingModle settingModle;

	/**
	 * 初始化JPush SDK 在应用程序启动时调用一次该 API
	 * 
	 * @param app
	 */
	public static void jPushInterfaceInit(Application app) {

		JPushInterface.setDebugMode(true);
		JPushInterface.init(app);

		// 设置别名
		setAlias(app);
	}

	/**
	 * 设置别名
	 */
	private static void setAlias(final Application app) {

		settingModle = new SettingModle(app);

		Log.d("debug", "getPushAlias >" + settingModle.getPushAlias());

		// 没有设置推送别名
		if (StringUtils.isBlank(settingModle.getPushAlias())) {

			// 激活设备URL
			String url = app.getString(R.string.device_cctivation);

			String pushAlias = StringUtils.getUniqueString(30, false);
			settingModle.setPushAlias(pushAlias);

			// 设置别名
			JPushInterface.setAlias(app, pushAlias, callback);

			// 激活设备推送
			deviceCctivation(url, pushAlias);
		}
	}

	/**
	 * 激活设备线程
	 */
	private static void deviceCctivation(final String url,
			final String pushAlias) {
		new Thread(new Runnable() {
			@Override
			public void run() {

				HashMap<String, String> params = new HashMap<String, String>();
				params.put("push_id", pushAlias);

				BaseHttpClient.doGetRequest(url, params);
			}
		}).start();
	}

	/**
	 * 设置推送别名回调
	 */
	private static TagAliasCallback callback = new TagAliasCallback() {

		@Override
		public void gotResult(int code, String arg1, Set<String> arg2) {
			// 设置成功
			if (code == 0) {
				if (settingModle != null) {
					settingModle.save();
				}
			}
		}
	};
}

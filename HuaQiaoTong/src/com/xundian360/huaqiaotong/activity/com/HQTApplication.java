/**
 * 
 */
package com.xundian360.huaqiaotong.activity.com;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;
import cn.sharesdk.framework.ShareSDK;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.common.push.JpushUtil;

/**
 * 程序Application对象
 * 
 * @author LiZhenteng
 * @date 2013年10月17日
 * @version 1.0
 */
@ReportsCrashes(formKey = "", formUri = "http://www.backendofyourchoice.com/reportpath", customReportContent = {
		ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME,
		ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL,
		ReportField.CUSTOM_DATA, ReportField.STACK_TRACE, ReportField.LOGCAT }, reportType = org.acra.sender.HttpSender.Type.JSON)
public class HQTApplication extends Application {

	private static HQTApplication mInstance = null;

	/** 百度地图相关 **/
	public BMapManager mBMapManager = null;

	@Override
	public void onCreate() {
		super.onCreate();

		mInstance = this;

		// 启动推送(在应用程序启动时调用一次)
		JpushUtil.jPushInterfaceInit(this);

		// 崩溃报告
		ACRA.init(this);

		// 初始化图片加载类
		initImageLoader(this);

		// 初始化百度地图
		initEngineManager(this);

		// 初始化分享组件
		ShareSDK.initSDK(this);
	}

	/**
	 * 初始化百度地图
	 * 
	 * @param context
	 */
	public void initEngineManager(Context context) {
		if (mBMapManager == null) {
			mBMapManager = new BMapManager(context);
		}

		if (!mBMapManager.init(getString(R.string.baidu_map_key),
				new MyGeneralListener())) {
			Toast.makeText(
					HQTApplication.getInstance().getApplicationContext(),
					"百度地图初始化错误!", Toast.LENGTH_LONG).show();
		}
	}

	// 常用事件监听，用来处理通常的网络错误，授权验证错误等
	static class MyGeneralListener implements MKGeneralListener {

		@Override
		public void onGetNetworkState(int iError) {
			if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
				Toast.makeText(
						HQTApplication.getInstance().getApplicationContext(),
						"您的网络出错啦！", Toast.LENGTH_LONG).show();
			} else if (iError == MKEvent.ERROR_NETWORK_DATA) {
				Toast.makeText(
						HQTApplication.getInstance().getApplicationContext(),
						"输入正确的检索条件！", Toast.LENGTH_LONG).show();
			}
		}

		@Override
		public void onGetPermissionState(int iError) {
		}
	}

	/**
	 * 当前对象
	 * 
	 * @return
	 */
	public static HQTApplication getInstance() {
		return mInstance;
	}

	/**
	 * 初始化图片加载类
	 * 
	 * @param context
	 */
	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // 开发使用（发布时移除） TODO
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}

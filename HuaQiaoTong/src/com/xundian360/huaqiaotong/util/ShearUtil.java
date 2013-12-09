/**
 * 
 */
package com.xundian360.huaqiaotong.util;

import android.content.Context;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * 分享工具类
 * @author  TengTeng
 * @date      2013年11月13日
 * @version 1.0
 */
public class ShearUtil {
	
	/**
	 * 分享到新浪微博
	 * @param context
	 * @param text
	 * @param imagePath
	 * @param paListener
	 */
	public static void shearToSinaWeibo(Context context, String text, String imagePath, 
			PlatformActionListener paListener) {
		Platform.ShareParams sp = new SinaWeibo.ShareParams();
		sp.text = text;
		sp.imagePath = imagePath;

		Platform weibo = ShareSDK.getPlatform(context, SinaWeibo.NAME);
		weibo.setPlatformActionListener(paListener); // 设置分享事件回调
		// 执行图文分享
		weibo.share(sp);
	}
	
	/**
	 * 分享到微信
	 * @param context
	 * @param title
	 * @param msg
	 * @param imagePath
	 */
	public static void shearPicToWxWithPath(Context context, String title, 
			String msg, String imagePath, PlatformActionListener paListener) {
		
		Platform plat = ShareSDK.getPlatform(context, Wechat.NAME);
		plat.setPlatformActionListener(paListener);
		
		Wechat.ShareParams sp = new Wechat.ShareParams();
		sp.title = title;
		sp.text = msg;
		sp.shareType = Platform.SHARE_IMAGE;
		sp.imagePath = imagePath;
		
		plat.share(sp);
	}
	
	/**
	 * 分享到微信朋友圈
	 * @param context
	 * @param title
	 * @param msg
	 * @param imagePath
	 */
	public static void shearPicToWxFWithPath(Context context, String title, 
			String msg, String imagePath, PlatformActionListener paListener) {
		
		Platform plat = ShareSDK.getPlatform(context, WechatMoments.NAME);
		plat.setPlatformActionListener(paListener);
		
		Wechat.ShareParams sp = new Wechat.ShareParams();
		sp.title = title;
		sp.text = msg;
		sp.shareType = Platform.SHARE_IMAGE;
		sp.imagePath = imagePath;
		
		plat.share(sp);
	}

}

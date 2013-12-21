/**
 * 
 */
package com.xundian360.huaqiaotong.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.xundian360.huaqiaotong.activity.b01.B01V01Activity;
import com.xundian360.huaqiaotong.activity.b01.B01V03Activity;
import com.xundian360.huaqiaotong.activity.b04.B04V00Activity;
import com.xundian360.huaqiaotong.modle.com.UserModle;

import android.R;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

/**
 * 一些常用的工具类
 * @author   tengteng
 * @time     上午12:35:44
 * @version  1.0
 */
public class CommonUtil {
	
	public final static String PIC_PNG = "png";
	public final static String PIC_JPEG  = "jpeg";
	public final static String PIC_JPG  = "jpg";
	public final static String PIC_WEBP  = "webp";
	
	/*********************   startActivity    ******************************/
	public static void startActivity(Context ctx, Class<?> clazz) {
		Intent main = new Intent(ctx, clazz);
		ctx.startActivity(main);
	}

	public static void startSubActivity(Context ctx, Intent intent) {
		ctx.startActivity(intent);
		
		((Activity)ctx).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	}
	
	public static void startSubActivity(Context ctx, Class<?> clazz) {
		Intent main = new Intent(ctx, clazz);
		ctx.startActivity(main);
		
		((Activity)ctx).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	}
	
	public static void startSubActivity(Context ctx, String action) {
		Intent main = new Intent(action);
		ctx.startActivity(main);
		
		((Activity)ctx).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	}
	
	/*********************   startActivityForResult    ******************************/
	public static void startActivityForResult(Context ctx, Class<?> clazz, int code) {
		Intent main = new Intent(ctx, clazz);
		((Activity) ctx).startActivityForResult(main, code);
	}

	public static void startSubActivityForResult(Context ctx, Intent intent, int code) {
		((Activity) ctx).startActivityForResult(intent, code);
		
		((Activity)ctx).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
	
	public static void startSubActivityForResult(Context ctx, Class<?> clazz, int code) {
		Intent main = new Intent(ctx, clazz);
		((Activity) ctx).startActivityForResult(main, code);
		
		((Activity)ctx).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
	
	public static void startSubActivityForResult(Context ctx, String action, int code) {
		Intent main = new Intent(action);
		((Activity) ctx).startActivityForResult(main, code);
		
		((Activity)ctx).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
	
	public static void startActivityForResult(Context ctx, Class<?> clazz, String key, Serializable obj, int code) {
		Intent main = new Intent(ctx, clazz);
		main.putExtra(key, obj);
		((Activity) ctx).startActivityForResult(main, code);
		
		((Activity)ctx).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
	
	public static void startActivityForResult(Context ctx, Intent in,int code) {
		((Activity) ctx).startActivityForResult(in, code);
		
		((Activity)ctx).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		
	}
	
	/** 从相册选择跳转 **/
	public static void startAlbumActivityForResult(Context ctx, int code) {
		
		Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        ((Activity) ctx).startActivityForResult(Intent.createChooser(intent, "Select Picture"),code);
        
        ((Activity)ctx).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
	
	/*********************   Other Method    ******************************/
	public static boolean isNetworkAvailable(Context context) {

		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}
	
	// 判断GPS是否可用
	public static boolean isGpsAvailable(Context context)
	{
		LocationManager locationManager = ((LocationManager)context
				.getSystemService(Context.LOCATION_SERVICE));
		List<String> accessibleProviders = locationManager.getProviders(true);
		
		if(accessibleProviders != null && accessibleProviders.size() > 0) {
			return true;
		}
		return false;
	}

	// wifi是否打开
	public static boolean isWifiAvailable(Context context)
	{
	ConnectivityManager mgrConn = (ConnectivityManager) context
			.getSystemService(Context.CONNECTIVITY_SERVICE);  
	TelephonyManager mgrTel = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);  
	
	return ((mgrConn.getActiveNetworkInfo()!=null && mgrConn.getActiveNetworkInfo().getState()==NetworkInfo.State.CONNECTED)  
			|| mgrTel.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
	}

	public static String getString(Context context, int resId) {
		String s = "";
		try {
			s = ((Activity) context).getString(resId);
			if (null == s) {
				s = "";
			}
		} catch (Exception e) {
		}
		return s;
	}



	public static void stopTask(AsyncTask<?, ?, ?> task) {
		if (null == task)
			return;
		task.cancel(true);
	}
	
	public static int getDayOrNight() {
		return new Date().getHours();
	}
	
	public static long getTime(String fomat, String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fomat);
		try {
			
			return simpleDateFormat.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static String getMMddHHss() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 hh时mm分");	
		return simpleDateFormat.format(new Date());
	}
	
	public static String getYYYYMMdd() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");	
		return simpleDateFormat.format(new Date());
	}
	
	public static String getYYYYMMdd(Date date) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");	
		return simpleDateFormat.format(date);
	}
	
	public static String getMMddHHss(Date date) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 hh时mm分");	
		return simpleDateFormat.format(date);
	}
	
	public static String getYYmmDDhhSS(Date date) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		return simpleDateFormat.format(date);
	}
	
	/*
	 * 取得Key， Value组成的JsonString
	 */
	public static String toJsonStringFromKey(String[] keys, String[] values) {
		
		String returnValueString;
		
		StringBuffer returnValue = new StringBuffer();
		returnValue.append("{");
		
		for(int i = 0; i < keys.length; i++) {
			returnValue.append("\"")
					   .append(keys[i])
					   .append("\":\"");
					   
		    if(values[i] == null) {
		    	values[i] = "";
		    }
			returnValue.append(values[i])
					   .append("\",");
		}
		
		returnValueString = returnValue.substring(0, returnValue.length() - 1);
		
		return returnValueString + "}";
	}
	
	/*********************** 取得经纬度之间的距离 **************************/
    public static enum GaussSphere{
        Beijing54,
        Xian80,
        WGS84,
    } 
    private static double rad(double d){
        return d * Math.PI / 180.0;
    }
    
	public static double distanceOfTwoPoints(double lng1,double lat1,double lng2,double lat2,
	   GaussSphere gs){
	        double radLat1 = rad(lat1);
	        double radLat2 = rad(lat2);
	        double a = radLat1 - radLat2;
	        double b = rad(lng1) - rad(lng2);
	        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
	         Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b/2),2)));
	        s = s * (gs == GaussSphere.WGS84 ? 6378137.0 : (gs == GaussSphere.Xian80 ? 6378140.0 : 6378245.0));
	        s = Math.round(s * 10000) / 10000;
	        return s;
	 }
	 
	 /*********************** 打电话  **************************/
	 /*
	  * 打电话
	  */
	public static  void callPhone(Context context, String phoneNum) {	
		
		if(StringUtils.isBlank(phoneNum)) {
			ShowMessageUtils.show(context, 
					com.xundian360.huaqiaotong.R.string.common_call_phone_formart_error);
			return;
		}
		
		// 打电话
		Intent myIntentDial = new Intent ( "android.intent.action.CALL", Uri.parse("tel:"+phoneNum));
		
		context.startActivity(myIntentDial); 
	}
	
	
	/**
	 * 取得屏幕高度
	 * @param ctx
	 * @return
	 */
	public static int getDisplayHeight(Window dialogWindow) {
		int hight = 0;

		// 获取屏幕宽、高用
		Display d = dialogWindow.getWindowManager().getDefaultDisplay();
		
		hight= d.getHeight();
		
		return hight;
	}
	
	/*
	 * SD卡有无判断 
	 */
	public static boolean existSDCard() {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} 
		return false;
	}
	
	/*
	 * 文件存在判断 
	 */
	public static boolean existFile(String filePath) {
		File file = new File(filePath);
		
		return file.exists();
	}
	
	/*
	 * 存储Bitmap对象到SD卡
	 * 支持文件格式：
	 * 	PNG（默认）
	 *  JPEG
	 *  JPG
	 *  WEBP 
	 */
	public static void saveBitmapInSdCard(Bitmap bitmap, String pathAndFileName) {
		 File file = new File(pathAndFileName);
		 FileOutputStream output = null;
		 
		 try {
			 output = new FileOutputStream(file);
			 
			 String picFormat = pathAndFileName.substring(pathAndFileName.lastIndexOf(".") - 1, pathAndFileName.length());
			 
			 Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.PNG;
			 
			 // JPEG, JPG格式
			 if(picFormat.equalsIgnoreCase(PIC_JPEG) || picFormat.equalsIgnoreCase(PIC_JPG)) {
				 compressFormat = Bitmap.CompressFormat.JPEG;
			 }
			 
			 Log.d("debug", picFormat);
			 
			 bitmap.compress(compressFormat, 100, output);
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(output != null) {
				try {
					output.flush();
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * 图片保存到相册
	 */
	public static String saveBitmapInAlbum(Context ctx, Bitmap bitmap, String picName) {
		
		ContentResolver cr = ctx.getContentResolver();
		
		return MediaStore.Images.Media.insertImage(cr, bitmap, picName, "IBuick Save.");
		
	}
	
	/*
	 * 删除文件 
	 */
	public static void deleteFile(String filePath) {
		
		File file = new File(filePath);
		
		if(file.exists()) {
			file.delete();
		}
	}
	
	/*
	 * 取的double数据的最大值
	 */
	public static Double getMaxOfArray(Double[] values) {
		
		Double value = values[0];
		
		for (int i = 1; i < values.length; i++) {
			if(values[i] > values[i - 1]) {
				value = values[i];
			}
		}

		return value;
	}

	/*
	 * 取的double数据的最大值
	 */
	public static double getMaxOfArray(double[] values) {
		
		double value = values[0];
		
		for (int i = 1; i < values.length; i++) {
			if(values[i] > values[i - 1]) {
				value = values[i];
			}
		}

		return value;
	}
	
	/*
	 * 隐藏键盘
	 */
	public static void hideInput(Context ctx) {
		View inputView =  ((Activity) ctx).getCurrentFocus();
		if(inputView != null) {
			((InputMethodManager)ctx.getSystemService(ctx.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(inputView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
	
	/*
	 * 显示键盘
	 */
	public static void showInput(Context ctx) {
		View inputView =  ((Activity) ctx).getCurrentFocus();
		if(inputView != null) {
			((InputMethodManager)ctx.getSystemService(ctx.INPUT_METHOD_SERVICE)).showSoftInputFromInputMethod(inputView.getWindowToken(), InputMethodManager.SHOW_IMPLICIT);
		}
	}
	
	/**
	 * 取得屏幕高度
	 * @param ctx
	 * @return
	 */
	public static int getDisplayWidth(Window dialogWindow) {
		int width = 0;
		
		// 获取屏幕宽、高用
		Display d = dialogWindow.getWindowManager().getDefaultDisplay();
		
		width= d.getWidth();
		
		return width;
	}
	
	/**
	 * 判断是否登录
	 * @param ctx
	 * @return
	 */
	public static String isLogin(Context ctx) {
		
		String userId = null;
		
		UserModle userModle = new UserModle(ctx);
		
		if(userModle.user != null && StringUtils.isNotBlank(userModle.user.getUserId())) {
			
			userId = userModle.user.getUserId();
			
		} else {
			
			// 登录页面迁移
			ShowMessageUtils.show(ctx, com.xundian360.huaqiaotong.R.string.b04v00_login_msg);
			
			CommonUtil.startActivityForResult(ctx, B04V00Activity.class, 100);
		}
		
		return userId;
	}
}

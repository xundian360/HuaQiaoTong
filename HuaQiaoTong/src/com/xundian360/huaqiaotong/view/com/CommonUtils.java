package com.xundian360.huaqiaotong.view.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import android.R;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.xundian360.huaqiaotong.util.StringUtils;

public class CommonUtils {
	
	public final static String COMMON_PATH = "/common";
	public final static String TABLET_PIC = "/tablet";
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
		
		((Activity)ctx).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
	
	public static void startSubActivity(Context ctx, Class<?> clazz) {
		Intent main = new Intent(ctx, clazz);
		ctx.startActivity(main);
		
		((Activity)ctx).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
	
	public static void startSubActivity(Context ctx, String action) {
		Intent main = new Intent(action);
		ctx.startActivity(main);
		
		((Activity)ctx).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//		((Activity)ctx).overridePendingTransition(com.android.ibuick.R.anim.activity_zoom_enter, com.android.ibuick.R.anim.activity_zoom_exit);
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

	public static String encode(String str) {
		String encodeStr = "";
		try {
			encodeStr = new String(Base64.encode(str.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			encodeStr = new String(Base64.encode(str.getBytes()));
		}
		return encodeStr;
	}

	public static String decode(String str) {
		if (TextUtils.isEmpty(str)) {
			return null;
		}
		String decodeStr = "";
		try {
			decodeStr = new String(Base64.decode(str.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			decodeStr = new String(Base64.decode(str.getBytes()));
		}
		return decodeStr;
	}

	public static void stopTask(AsyncTask<?, ?, ?> task) {
		if (null == task)
			return;
		task.cancel(true);
	}

	public static Properties getNetConfigProperties(String filepath) {
		Properties props = new Properties();
		InputStream in = CommonUtils.class.getResourceAsStream(filepath);
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
	
	public static String getPropertyValue(String filepath, String key) {
		return getNetConfigProperties(filepath).getProperty(key);
	}
	
	public static Drawable loadImageFromUrl(String url) {  
        URL m;  
        InputStream i = null;  
        Drawable d = null;

        try {
			m = new URL(url);
		 
	        i = (InputStream) m.getContent();  
	
	        if( null != i ) {
	        	d = Drawable.createFromStream(i, "src");  
	        }
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//        Log.d("ImageLoaderTask", "loaded image is null ? --- " + (d == null));
        
        return d;
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
			return;
		}
		
		// 打电话
		Intent myIntentDial = new Intent ( "android.intent.action.CALL", Uri.parse("tel:"+phoneNum));
		
		context.startActivity(myIntentDial); 
	}
	
	
	public static String getStringDate(String sdate,int year) {
		
		String s_nd = sdate.substring(0, 4); // 年份
		String s_yf = sdate.substring(5, 7); // 月份
		String s_rq = sdate.substring(8, 10); // 日期
		String sreturn = "";
		int syear = Integer.valueOf(s_nd);

		if (year == syear) {
			s_nd = "今年";
		} else if (year == syear + 1) {
			s_nd = "去年";
		} else if (year == syear - 1) {
			s_nd = "明年";
		} else {
			s_nd = s_nd + "年";
		}

		sreturn = s_nd + s_yf + "月" + s_rq + "日";

		return sreturn;
	}
	
	/*
	 * 取得当前程序在SD卡上的绝对路径
	 */
	public static String getDir(Context ctx) {
		if( existSDCard() ) {
			return ctx.getExternalFilesDir(COMMON_PATH + TABLET_PIC).getAbsolutePath();
		}
		return null;
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
			 
//			 // WEBP格式
//			 else if(picFormat.equalsIgnoreCase(PIC_WEBP)) {
//				 compressFormat = Bitmap.CompressFormat.WEBP;
//			 }
			 
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
	 * 取得屏幕宽度
	 */
	public static int getScreenWidth(Context ctx) {
		
		// 获取屏幕宽度
		WindowManager windowManager = ((Activity) ctx).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        
        return display.getWidth();
	}
	
	/*
	 * 取得屏幕高度
	 */
	public static int getScreenHighe(Context ctx) {
		
		// 获取屏幕宽度
		WindowManager windowManager = ((Activity) ctx).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        
        return display.getHeight();
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
	 * 隐藏键盘
	 */
	public static void showInput(Context ctx) {
		
		View inputView =  ((Activity) ctx).getCurrentFocus();
		
		if(inputView != null) {
			
			((InputMethodManager)ctx.getSystemService(ctx.INPUT_METHOD_SERVICE)).showSoftInputFromInputMethod(inputView.getWindowToken(), InputMethodManager.SHOW_IMPLICIT);
			
		}
		
	}
	
}

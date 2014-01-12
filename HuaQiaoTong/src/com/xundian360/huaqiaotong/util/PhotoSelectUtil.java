/**
 * 
 */
package com.xundian360.huaqiaotong.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;

/**
 * 图片选择---工具类
 * 
 * @author  TeneTeng
 * @date      2014-1-12
 * @version 1.0
 */
public class PhotoSelectUtil {
	
    /**
     * 将进行剪裁后的图片显示到UI界面上
     * @param picdata
     * @return
     */
	public static Bitmap getPic(Intent picdata) {
    	Bitmap photo = null;
        Bundle bundle = picdata.getExtras();
        if (bundle != null) {
            photo = bundle.getParcelable("data");
        }
        return photo;
    }
	
	/**
	 *  使用系统当前日期加以调整作为照片的名称
	 * @return
	 */
	public static  String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }
	
	/**
	 * 转换图片的大小
	 * @return
	 */
	public static Bitmap changePostItemImgSize(Context context, Bitmap source) {
		
		// 屏幕的1/8
		int displayWidth = (int) (CommonUtil.getDisplayWidth(((Activity)context).getWindow()) * 0.9);
		
		Bitmap trBitmap = null;
		
		int source_w = source.getWidth();
		int source_h = source.getHeight();
		
		// 图片需要压缩
		if(source_w > displayWidth) {
			trBitmap = ThumbnailUtils.extractThumbnail(source, displayWidth, source_h * displayWidth / source_w);
		} else {
			trBitmap = ThumbnailUtils.extractThumbnail(source, source_w, source_h);
		}
		
		return trBitmap;
	}
	
}

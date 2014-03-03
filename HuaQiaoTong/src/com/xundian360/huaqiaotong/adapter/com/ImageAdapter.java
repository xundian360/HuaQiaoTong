package com.xundian360.huaqiaotong.adapter.com;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xundian360.huaqiaotong.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * 图片数据源
 * 
 * @author TeneTeng
 * @date 2014-2-15
 * @version 1.0
 */
public class ImageAdapter extends BaseAdapter {

	private Context mContext;
	int[] imgRes = null;
	String[] imgResString = null;
	
	// 图片缓存
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.b01v00_item_dafilt_img)
			.showImageForEmptyUri(R.drawable.b01v00_item_dafilt_img)
			.showImageOnFail(R.drawable.b01v00_item_dafilt_img)
			.cacheOnDisc(true).build();
	
	public ImageAdapter(Context c) {
		mContext = c;
	}

	public ImageAdapter(Context c, int[] imgRes) {
		mContext = c;
		this.imgRes = imgRes;
	}
	
	public ImageAdapter(Context c, String[] imgResString) {
		mContext = c;
		this.imgResString = imgResString;
	}

	@Override
	public int getCount() {
		if(imgRes != null ) {
			return imgRes.length;
		} else {
			return imgResString.length;
		}
	}

	@Override
	public Object getItem(int arg0) {
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ImageView img = new ImageView(mContext);
		
		if(imgRes != null ) {
			img.setImageResource(imgRes[arg0]);
		} else {
			ImageLoader.getInstance().displayImage(imgResString[arg0], img, options);
		}
		
		return img;
	}
}

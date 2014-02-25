package com.xundian360.huaqiaotong.adapter.com;

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

	public ImageAdapter(Context c) {
		mContext = c;
	}

	public ImageAdapter(Context c, int[] imgRes) {
		mContext = c;
		this.imgRes = imgRes;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imgRes.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ImageView img = new ImageView(mContext);
		img.setImageResource(imgRes[arg0]);
		return img;
	}
}

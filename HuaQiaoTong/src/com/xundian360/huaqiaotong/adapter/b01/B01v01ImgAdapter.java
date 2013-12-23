/**
 * 
 */
package com.xundian360.huaqiaotong.adapter.b01;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.view.com.GalleryFlow;

/**
 * 商店详细（头部图片）
 * @author  TengTeng
 * @date      2013-10-4
 * @version 1.0
 */
public class B01v01ImgAdapter extends SimpleAdapter {
	
	public static final String[] from = {"b01v01TittleItemImg"};
	
	public static final int[] to = {R.id.b01v01TittleItemImg};
	
	Context context;
	
	List<String> tittlePics;
	
	int xWidth = 0;
	int yWidth = 0;
	
	// 图片缓存
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.b01v00_item_dafilt_img)
			.showImageForEmptyUri(R.drawable.b01v00_item_dafilt_img)
			.showImageOnFail(R.drawable.b01v00_item_dafilt_img)
			.cacheOnDisc(true)
			// .displayer(new RoundedBitmapDisplayer(20))
			.build();
	
	public B01v01ImgAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);

		this.context = context;
		
		xWidth = CommonUtil.getDisplayWidth(((Activity)context).getWindow()) / 2;
		yWidth = CommonUtil.getDisplayHeight(((Activity)context).getWindow()) / 5;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		View view = super.getView(position, convertView, parent);
		
		ImageView imgView = (ImageView) view.findViewById(R.id.b01v01TittleItemImg);
		// 加载图片
		ImageLoader.getInstance().displayImage(tittlePics.get(position), imgView, options);
		
		view.setLayoutParams(new GalleryFlow.LayoutParams(xWidth, yWidth));
			
		return view;
	}
	
	/**
	 * 设置图片
	 * @param tittlePics
	 */
	public void setTittlePics(List<String> tittlePics) {
		this.tittlePics = tittlePics;
	}
}

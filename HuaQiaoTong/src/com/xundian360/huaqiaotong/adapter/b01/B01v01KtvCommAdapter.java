/**
 * 
 */
package com.xundian360.huaqiaotong.adapter.b01;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.com.BaiduComment;

/**
 * KTV评论列表Adpter
 * @author  TengTeng
 * @date      2013-10-4
 * @version 1.0
 */
public class B01v01KtvCommAdapter extends SimpleAdapter {
	
	public static final String[] from = {"b01v00KtvCommPic", "b01v00KtvCommAuther", 
		"b01v00KtvCommRating", "b01v00KtvCommDatail", "b01v00KtvCommTime"};
	public static final int[] to = {R.id.b01v00KtvCommPic, R.id.b01v00KtvCommAuther, 
		R.id.b01v00KtvCommRating, R.id.b01v00KtvCommDatail, R.id.b01v00KtvCommTime};
	
	Context context;
	
	List<BaiduComment> shopComments;
	
	// 图片缓存
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.b01v00_item_dafilt_img)
			.showImageForEmptyUri(R.drawable.b01v00_item_dafilt_img)
			.showImageOnFail(R.drawable.b01v00_item_dafilt_img)
			.cacheOnDisc(true)
			// .displayer(new RoundedBitmapDisplayer(20))
			.build();
	
	public B01v01KtvCommAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = getView(position, convertView, parent);
		
		ImageView userLogo = (ImageView) view.findViewById(R.id.b01v00KtvCommPic);
		// 加载图片
		ImageLoader.getInstance().displayImage(shopComments.get(position).getUserLogoPath(), userLogo, options);
		
		return view;
	}

	public void setShopComments(List<BaiduComment> shopComments) {
		this.shopComments = shopComments;
	}

}

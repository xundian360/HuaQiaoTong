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
import android.widget.RatingBar;
import android.widget.SimpleAdapter;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.com.BaiduComment;
import com.xundian360.huaqiaotong.util.StringUtils;

/**
 * KTV评论列表Adpter
 * @author  TengTeng
 * @date      2013-10-4
 * @version 1.0
 */
public class B01v01KtvCommAdapter extends SimpleAdapter {
	
	public static final String[] from = {"b01v00KtvCommPic", "b01v00KtvCommAuther", "b01v00KtvCommDatail", "b01v00KtvCommTime"};
	public static final int[] to = {R.id.b01v00KtvCommPic, R.id.b01v00KtvCommAuther,  R.id.b01v00KtvCommDatail, R.id.b01v00KtvCommTime};
	
	Context context;
	
	List<BaiduComment> shopComments;
	
	// 图片缓存
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.b01v00_item_pic_bg)
			.showImageForEmptyUri(R.drawable.b01v00_item_pic_bg)
			.showImageOnFail(R.drawable.b01v00_item_pic_bg)
			.cacheOnDisc(true)
			.displayer(new RoundedBitmapDisplayer(5))
			.build();
	
	public B01v01KtvCommAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		
		BaiduComment shopComment = shopComments.get(position);
		
		 ImageView userLogo = (ImageView) view.findViewById(R.id.b01v00KtvCommPic);
		 // 加载图片
		 ImageLoader.getInstance().displayImage(shopComment.getUserLogoPath(), userLogo, options);
		
		// 设置评分
		RatingBar itemRating = (RatingBar) view.findViewById(R.id.b01v00KtvCommRating);
		itemRating.setRating(StringUtils.paseFloat(shopComment.getScore(), 0));
		itemRating.setClickable(false);
		
		return view;
	}

	public void setShopComments(List<BaiduComment> shopComments) {
		this.shopComments = shopComments;
	}

}

/**
 * 
 */
package com.xundian360.huaqiaotong.adapter.b01;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b01.B01V01Activity;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.StringUtils;

/**
 * KTV列表显示Adpter
 * 
 * @author TengTeng
 * @date 2013-10-4
 * @version 1.0
 */
public class B01v00KtvAdapter extends SimpleAdapter {

	public static final String[] from = { "v01v00ItemImg", "v01v00ItemTittle",
			"b01v00ItemRecommendTittle", "b01v00ItemRecommend",
			"v01v00ItemPrice" };

	public static final int[] to = { R.id.v01v00ItemImg, R.id.v01v00ItemTittle,
			R.id.b01v00ItemRecommendTittle, R.id.b01v00ItemRecommend,
			R.id.v01v00ItemPrice };

	Context context;

	// 当前ListView显示的items信息
	List<Baidu> items;

	// 图片缓存
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.b01v00_item_dafilt_img)
			.showImageForEmptyUri(R.drawable.b01v00_item_dafilt_img)
			.showImageOnFail(R.drawable.b01v00_item_dafilt_img)
			.cacheOnDisc(true)
			// .displayer(new RoundedBitmapDisplayer(20))
			.build();

	public B01v00KtvAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);

		this.context = context;
	}

	public B01v00KtvAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to, List<Baidu> items) {
		super(context, data, resource, from, to);

		this.context = context;
		this.items = items;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View view = super.getView(position, convertView, parent);

		final Baidu item = items.get(position);

		// 设置图片
		ImageView imgView = (ImageView) view.findViewById(R.id.v01v00ItemImg);
		ImageLoader.getInstance().displayImage(item.getShop_pic_soulue(),
				imgView, options);

		// 设置评分
		RatingBar rating = (RatingBar) view.findViewById(R.id.v01v00ItemRating);
		rating.setRating(StringUtils.paseFloat(item.getOverall_rating(), 0));
		rating.setClickable(false);

		// 设置电话
		LinearLayout callBtn = (LinearLayout) view
				.findViewById(R.id.b01v00PoiCall);
		callBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CommonUtil.callPhone(context, item.getTelephone());
			}
		});

		// View 点击事件
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CommonUtil
						.startActivityForResult(context, B01V01Activity.class,
								B01V01Activity.KTV_KEY, item, 100);
			}
		});

		return view;
	}

	public void setItems(List<Baidu> items) {
		this.items = items;
	}
}

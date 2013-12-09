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
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b01.B01V01Activity;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.util.CommonUtil;

/**
 * KTV列表显示Adpter
 * @author  TengTeng
 * @date      2013-10-4
 * @version 1.0
 */
public class B01v00KtvAdapter extends SimpleAdapter {
	
	public static final String[] from = {"v01v00ItemImg1", "v01v00ItemTittle1",
		"v01v00ItemPrice1", "b01v00ItemRecommend1", "v01v00ItemImg2", 
		"v01v00ItemTittle2","v01v00ItemPrice2", "b01v00ItemRecommend2"};
	
	public static final int[] to = {R.id.v01v00ItemImg1, R.id.v01v00ItemTittle1, 
		R.id.v01v00ItemPrice1, R.id.b01v00ItemRecommend1, R.id.v01v00ItemImg2, 
		R.id.v01v00ItemTittle2, R.id.v01v00ItemPrice2, R.id.b01v00ItemRecommend2};
	
	Context context;
	
	// 当前ListView显示的items信息
	List<Baidu> items;
	
	public B01v00KtvAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
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
		
		LinearLayout leftView = (LinearLayout) view.findViewById(R.id.v01v00ItemLeft);
		leftView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 详情页面迁移
				CommonUtil.startActivityForResult(context, B01V01Activity.class, B01V01Activity.KTV_KEY, items.get(position * 2), 1000);
			}
		});
		
		LinearLayout rightView = (LinearLayout) view.findViewById(R.id.v01v00ItemRight);
		rightView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 详情页面迁移
				CommonUtil.startActivityForResult(context, B01V01Activity.class, B01V01Activity.KTV_KEY, items.get(position * 2 + 1), 1000);
			}
		});
		
		return view;
	}
}

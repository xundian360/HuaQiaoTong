/**
 * 
 */
package com.xundian360.huaqiaotong.adapter.b01;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.com.Baidu;

/**
 * KTV列表显示Adpter
 * @author  TengTeng
 * @date      2013-10-4
 * @version 1.0
 */
public class B01v00KtvAdapter extends SimpleAdapter {
	
	public static final String[] from = {"v01v00ItemImg", "v01v00ItemTittle",
		"b01v00ItemRecommendTittle", "b01v00ItemRecommend", "v01v00ItemPrice"};
	
	public static final int[] to = {R.id.v01v00ItemImg, R.id.v01v00ItemTittle, 
		R.id.b01v00ItemRecommendTittle, R.id.b01v00ItemRecommend, R.id.v01v00ItemPrice};
	
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
		
		return view;
	}
}

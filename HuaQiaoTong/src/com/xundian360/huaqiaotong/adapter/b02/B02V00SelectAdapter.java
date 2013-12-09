/**
 * 
 */
package com.xundian360.huaqiaotong.adapter.b02;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.com.SimpleItem;

/**
 * 条件选择Adapter
 * @author    LiZhenteng
 * @date      2013年10月15日
 * @version   1.0
 */
public class B02V00SelectAdapter extends SimpleAdapter {
	
	public static final String[] from = {"b02v00ItemName"};
	public static final int[] to = {R.id.b02v00ItemName};
	
	List<SimpleItem> circleItems;
	Context context;

	public B02V00SelectAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to, List<SimpleItem> circleItems) {
		super(context, data, resource, from, to);
		
		this.context = context;
		this.circleItems = circleItems;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		
		// TODO
		
		return view;
	}

}

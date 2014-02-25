/**
 * 
 */
package com.xundian360.huaqiaotong.adapter.b00;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.xundian360.huaqiaotong.R;

/**
 * @author TeneTeng
 * @date 2014-2-23
 * @version 1.0
 */
public class B00V03StationAdapter extends SimpleAdapter {

	public B00V03StationAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);

		// 设置终点底线不显示
		ImageView itemLine = (ImageView) view
				.findViewById(R.id.b00v03ZhandianItemLine);

		if (position == (getCount() - 1)) {
			itemLine.setVisibility(View.INVISIBLE);
		} else {
			itemLine.setVisibility(View.VISIBLE);
		}

		return view;
	}

}

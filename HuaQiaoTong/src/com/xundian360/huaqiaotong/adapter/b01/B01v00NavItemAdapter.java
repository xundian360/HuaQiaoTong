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

/**
 * 导航Item
 * 
 * @author TengTeng
 * @date 2013-10-4
 * @version 1.0
 */
public class B01v00NavItemAdapter extends SimpleAdapter {

	public static String[] from = { "b01v00NavItemName" };
	public static int[] to = { R.id.b01v00NavItemName };

	Context context;

	public B01v00NavItemAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);

		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = super.getView(position, convertView, parent);

		return view;
	}
}

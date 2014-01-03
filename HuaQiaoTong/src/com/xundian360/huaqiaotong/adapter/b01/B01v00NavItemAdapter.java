/**
 * 
 */
package com.xundian360.huaqiaotong.adapter.b01;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;

/**
 * 导航Item
 * @author  TengTeng
 * @date      2013-10-4
 * @version 1.0
 */
public class B01v00NavItemAdapter extends SimpleAdapter {
	
	public static String[] from = {"b01v00NavItemName"};
	public static int[] to = {R.id.b01v00NavItemName};
	
	Context context;
	
	int selectIndex = 0;

	public B01v00NavItemAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);

		this.context = context;
	}
	
	public B01v00NavItemAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
					int[] to, int intem_select_intex) {
		super(context, data, resource, from, to);

		this.context = context;
		this.selectIndex = intem_select_intex;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = super.getView(position, convertView, parent);
			
		LinearLayout itemNavView = (LinearLayout) view.findViewById(R.id.b01v00NavItemView);
		TextView itemNavName =  (TextView) view.findViewById(R.id.b01v00NavItemName);
		
		if(selectIndex == position) {
			view.setBackgroundColor(context.getResources().getColor(R.color.b04v05_item_supper_color));
			itemNavView.setBackgroundColor(context.getResources().getColor(R.color.b04v05_item_supper_color));
			itemNavName.setTextColor(Color.WHITE);
		} else {
			view.setBackgroundColor(Color.WHITE);
			itemNavView.setBackgroundColor(Color.WHITE);
			itemNavName.setTextColor(context.getResources().getColor(R.color.text_color_2));
		}
		return view;
	}
	
	public void setSelectIndex(int selectIndex) {
		this.selectIndex = selectIndex;
	}
}

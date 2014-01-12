/**
 * 
 */
package com.xundian360.huaqiaotong.adapter.b03;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleExpandableListAdapter;

import com.xundian360.huaqiaotong.R;

/**
 * 导航ListVIew
 * 
 * @author  TengTeng
 * @date      2013年11月3日
 * @version 1.0
 */
public class B03V00NavAdapter extends SimpleExpandableListAdapter {
	
	// Key
	public static final String[] p_from = {"b00v02NavLevel1ItemLogo", "b00v02NavLevel1ItemName"};
	// Key ID
	public static final int[] p_to = {R.id.b00v02NavLevel1ItemLogo, R.id.b00v02NavLevel1ItemName};
	
	public static final String[] from = {"b03v00NavItemName"};
	public static final int[] to = {R.id.b03v00NavItemName};
	
	Context context;
	
	public static int[] nav_logo_ids = {
			R.drawable.b03v00_nav_new_0,
			R.drawable.b03v00_nav_fre_0,
			R.drawable.b03v00_nav_fang_0,
			R.drawable.b03v00_nav_rec_0,
			R.drawable.b03v00_nav_er_0
	};
	
	public static int[] level2Ids = {
//			R.array.v03v00_nav_level_1_0,
//			R.array.v03v00_nav_level_1_1,
//			R.array.v03v00_nav_level_1_2,
//			R.array.v03v00_nav_level_1_3,
//			R.array.v03v00_nav_level_1_4
	};

	public B03V00NavAdapter(Context context,
			List<? extends Map<String, ?>> groupData, int expandedGroupLayout,
			int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, ?>>> childData,
			int childLayout, int lastChildLayout, String[] childFrom,
			int[] childTo) {
		super(context, groupData, expandedGroupLayout, collapsedGroupLayout, groupFrom,
				groupTo, childData, childLayout, lastChildLayout, childFrom, childTo);
		
		this.context = context;
	}

	public B03V00NavAdapter(Context context,
			List<? extends Map<String, ?>> groupData, int expandedGroupLayout,
			int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, ?>>> childData,
			int childLayout, String[] childFrom, int[] childTo) {
		super(context, groupData, expandedGroupLayout, collapsedGroupLayout, groupFrom,
				groupTo, childData, childLayout, childFrom, childTo);
			
		this.context = context;
	}

	public B03V00NavAdapter(Context context,
			List<? extends Map<String, ?>> groupData, int groupLayout,
			String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, ?>>> childData,
			int childLayout, String[] childFrom, int[] childTo) {
		super(context, groupData, groupLayout, groupFrom, groupTo, childData,
				childLayout, childFrom, childTo);

		this.context = context;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		
		View view = super.getGroupView(groupPosition, isExpanded, convertView, parent);
//		
//		// 设置图标
//		ImageView logoView = (ImageView) view.findViewById(R.id.b00v02NavLevel1ItemLogo);
//		logoView.setImageResource(nav_logo_ids[groupPosition]);
//		
//		TextView navNameView = (TextView) view.findViewById(R.id.b00v02NavLevel1ItemName);
//		navNameView.setText(context.getResources().getStringArray(R.array.v03v00_nav_level_0)[groupPosition]);
//		
		return view;
	}
}

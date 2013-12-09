/**
 * 
 */
package com.xundian360.huaqiaotong.adapter.b01;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;

/**
 * KTV评论列表Adpter
 * @author  TengTeng
 * @date      2013-10-4
 * @version 1.0
 */
public class B01v01KtvCommAdapter extends SimpleExpandableListAdapter {
	
	public static final String[] p_from = {"b01v00KtvCommPic", 
		"b01v00KtvCommAuther", "b01v00KtvCommDatail"};
	public static final int[] p_to = {R.id.b01v00KtvCommPic, 
		R.id.b01v00KtvCommAuther, R.id.b01v00KtvCommDatail};
	
	public static final String[] c_from = {"b01v00KtvCommChildDatail"};
	public static final int[] c_to = {R.id.b01v00KtvCommChildDatail};
	
	Context context;
	
	List<? extends Map<String, ?>> groupData;
	List<? extends List<? extends Map<String, ?>>> childData;
	
	LayoutInflater inflater;
	
	public B01v01KtvCommAdapter(Context context,
			List<? extends Map<String, ?>> groupData, int expandedGroupLayout,
			int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, ?>>> childData,
			int childLayout, int lastChildLayout, String[] childFrom,
			int[] childTo) {
		super(context, groupData, expandedGroupLayout, collapsedGroupLayout, groupFrom,
				groupTo, childData, childLayout, lastChildLayout, childFrom, childTo);

		this.context = context;
		this.groupData = groupData;
		this.childData = childData;
		inflater = ((Activity)context).getLayoutInflater();
	}

	public B01v01KtvCommAdapter(Context context,
			List<? extends Map<String, ?>> groupData, int groupLayout,
			String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, ?>>> childData,
			int childLayout, String[] childFrom, int[] childTo) {
		super(context, groupData, groupLayout, groupFrom, groupTo, childData,
				childLayout, childFrom, childTo);

		this.context = context;
		this.groupData = groupData;
		this.childData = childData;
		inflater = ((Activity)context).getLayoutInflater();
	}

	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// 设置Group视图
		View groupView = inflater.inflate(R.layout.b01v01_item, null);
		
		@SuppressWarnings("unchecked")
		Map<String, Object> dataItem =  (Map<String, Object>) groupData.get(groupPosition);
		
		// 评论人
		TextView commAuther = (TextView) groupView.findViewById(R.id.b01v00KtvCommAuther);
		commAuther.setText(dataItem.get(p_from[1]).toString());
		
		// 评论详细
		TextView commDatail = (TextView) groupView.findViewById(R.id.b01v00KtvCommDatail);
		commDatail.setText(dataItem.get(p_from[2]).toString());
		
		return groupView;
	}
	
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		@SuppressWarnings("unchecked")
		Map<String, Object> dataItem =  (Map<String, Object>) groupData.get(groupPosition);
		
		// 设置子视图
		View childView = inflater.inflate(R.layout.b01v01_child_item, null);
		
		TextView commChildDatail = (TextView) childView.findViewById(R.id.b01v00KtvCommChildDatail);
		commChildDatail.setText(dataItem.get(p_from[2]).toString());
		
		return childView;
	}
}

/**
 * 
 */
package com.xundian360.huaqiaotong.view.b01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b01.B01V00Activity;
import com.xundian360.huaqiaotong.modle.b01.ItemObject;

/**
 * 导航视图
 * 
 * @author  Administrator
 * @date      2013年12月5日
 * @version 1.0
 */
public class B01v00NavItemView {
	
	public static String[] from = {"b01v00NavItemName"};
	public static int[] to = {R.id.b01v00NavItemName};
	
	Context context;
	
	View mainView;
	
	LinearLayout navView;
	
	// 导航
	TextView navName;
	// 导航展开标记
	ImageView navStatusImg;
	
	ListView navItems;
	
	// 当前项目显示的分类
	ItemObject itemObject;
	
	// 当前导航项目所表示的编号
	int index;
	
	// 项目数据源
	SimpleAdapter itemAdapter;
	List<Map<String, String>> data = new ArrayList<Map<String, String>>();
	
	// 导航展开标志
	public boolean isExpansion = false;
	
	public B01v00NavItemView(Context context, 
			ItemObject itemObject, int index) {
		this.context = context;
		this.itemObject = itemObject;
		this.index = index;
		
		// 初始化数据
		initData();
		
		// 初始化视图
		initView();
	}
	
	/**
	 * 初始化数据
	 */
	private void initData() {
		
		// 设置数据源
		setAdapterData();
		
		// 设置Adapter
		itemAdapter = new SimpleAdapter(context, data, R.layout.b01v00_nav_item_item, from, to);
	}
	
	/**
	 * 初始化视图
	 */
	private void initView() {
		
		mainView = LayoutInflater.from(context)
				.inflate(R.layout.b01v00_nav_item, null);
		
		navView = (LinearLayout) mainView.findViewById(R.id.b01v00NavView);
		navView.setOnClickListener(navViewClick);
		
		navName = (TextView) mainView.findViewById(R.id.b01v00NavName);
		navName.setText(context.getResources().getStringArray(itemObject.getNavId())[index]);
		
		navItems = (ListView) mainView.findViewById(R.id.b01v00NavItems);
		navItems.setAdapter(itemAdapter);
		
		navStatusImg = (ImageView) mainView.findViewById(R.id.b01v00NavImg);
	}
	
	
	/**
	 * 设置数据源
	 */
	private void setAdapterData() {
		
		// 设置数据源
		String[] navItem = context.getResources().getStringArray(itemObject.getNavItemTextId()[index]);
		
		for (int i = 0; i < navItem.length; i++) {
			Map<String, String> item = new HashMap<String, String>();
			
			item.put(from[0], navItem[i]);
			
			data.add(item);
		}
	}
	
	/**
	 * 导航点击
	 */
	OnClickListener navViewClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			// 判断显示，隐藏子项目
			if(isExpansion) {   // 隐藏子项目
				
				// 选中当前导航
				isExpansionAction();
			} else {    // 显示子项目
				
				// 失去当前导航
				lossExpansionAction();
			}
		}
	};
	
	/**
	 * 选中当前导航
	 */
	public void isExpansionAction() {
		// 设置背景白色
		navView.setBackgroundColor(Color.WHITE);
		// 设置文字颜色
		navName.setTextColor(context.getResources().getColor(R.color.b04v05_item_supper_color));
		// 设置箭头方向图片
		navStatusImg.setImageResource(R.drawable.b01v00_nav_arr_0);
		// 设置表示反转
		isExpansion = !isExpansion;
		
		// TODO 设置子项目隐藏
		navItems.setVisibility(View.GONE);
	}
	
	/**
	 * 失去当前导航
	 */
	public void lossExpansionAction() {
		// 设置背景
		navView.setBackgroundColor(context.getResources().getColor(R.color.b04v05_item_supper_color));
		// 设置文字颜色
		navName.setTextColor(Color.WHITE);
		// 设置箭头方向图片
		navStatusImg.setImageResource(R.drawable.b01v00_nav_arr_1);
		// 设置选中的导航不显示
		((B01V00Activity)context).setSelectNav(index);
		// 设置表示反转
		isExpansion = !isExpansion;
		
		// TODO 设置子项目显示
		navItems.setVisibility(View.VISIBLE);
	}
	
	/** 返回View */
	public View get() {
		return mainView;
	}
}

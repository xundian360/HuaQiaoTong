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
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b01.B01V00Activity;
import com.xundian360.huaqiaotong.adapter.b01.B01v00NavItemAdapter;
import com.xundian360.huaqiaotong.modle.b01.ItemConstants;
import com.xundian360.huaqiaotong.modle.b01.ItemObject;
import com.xundian360.huaqiaotong.util.StringUtils;
import com.xundian360.huaqiaotong.util.b01.B01v00ShopUtils;

/**
 * 导航视图
 * 
 * @author  Administrator
 * @date      2013年12月5日
 * @version 1.0
 */
public class B01v00NavItemView {
	
	Context context;
	
	View mainView;
	
	LinearLayout navView;
	
	// 导航
	TextView navName;
	// 导航展开标记
	ImageView navStatusImg;
	
	// 导航项目
	ListView navItems;
	
	// 当前项目显示的分类
	ItemObject itemObject;
	
	// 当前导航项目所表示的编号
	int index;
	
	// 选中的条件
	int item_select_index = 0;
	
	// 项目数据源
	B01v00NavItemAdapter itemAdapter;
	List<Map<String, String>> data = new ArrayList<Map<String, String>>();
	
	// 设置数据源
	String[] navItem;
	
	// 导航展开标志
	public boolean isExpansion = false;
	
	Handler _handler = new Handler();
	
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
		itemAdapter = new B01v00NavItemAdapter(context, data, 
				R.layout.b01v00_nav_item_item, 
				B01v00NavItemAdapter.from, 
				B01v00NavItemAdapter.to,
				item_select_index);
	}
	
	
	/**
	 *  项目点击事件
	 */
	OnItemClickListener navItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			if(item_select_index != arg2) {
				// 设值选中条件
				item_select_index = arg2;
				
				// 加载数据
				loadData(0, true);
			}
			
			// TODO 设置子项目隐藏
			navItems.setVisibility(View.GONE);
			
			// 设置其他项目
			((B01V00Activity)context).setUnSelectNav(index);
		}
	};
	
	/**
	 * 加载数据
	 * @param index
	 */
	public void loadData(final int pageNum, final boolean isClear) {
		
		// 取得排序方法
		final int searchKey = itemObject.getNavItemSearchKey()[index];
		
		// 加载数据
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				// 返回值
				Map<String, Object> items = new HashMap<String, Object>();
				
				// 项目值
				String itemText = navItem[item_select_index];
				
				// 全部选择的时候
				if(item_select_index == 0) {
					// 取得所有商店信息
					items = B01v00ShopUtils.getShopList(context, 
							context.getString(itemObject.getKeyId()), 
							ItemConstants.DEL_PAGE_SIZT, 
							pageNum);
				}
				
				// 按价格搜索
				else if(searchKey == ItemConstants.ITEM_SERACH_BY_PRICE) {
					
					String minPrice = "";
					String maxPrice = "";
					
					// XXX以下
					if(item_select_index == 1) {
						minPrice = "0";
						maxPrice = StringUtils.getNumInString(itemText, 0);
					} else {
						minPrice = StringUtils.getNumInString(itemText, 0);
						maxPrice = StringUtils.getNumInString(itemText, 1);
						if(StringUtils.isBlank(maxPrice)) {
							maxPrice = Integer.MAX_VALUE + "";
						}
					}
					
					items = B01v00ShopUtils.getShopListByPrice(context, 
							context.getString(itemObject.getKeyId()), 
							minPrice, 
							maxPrice, 
							ItemConstants.DEL_PAGE_SIZT, 
							pageNum);
					
				}
				
				// 按关注搜索
				else if(searchKey == ItemConstants.ITEM_SERACH_BY_ATTENTION) {
					
					String minL = "";
					String maxL = "";
					
					// XXX以下
					if(item_select_index == 1) {
						minL = "0";
						maxL = StringUtils.getNumInString(itemText, 0);
					}else {
						minL = StringUtils.getNumInString(itemText, 0);
						maxL = StringUtils.getNumInString(itemText, 1);
						
						if(StringUtils.isBlank(maxL)) {
							maxL = Integer.MAX_VALUE + "";
						}
					}
					
					items = B01v00ShopUtils.getShopListByAttention(context, 
							context.getString(itemObject.getKeyId()), 
							minL, 
							maxL, 
							ItemConstants.DEL_PAGE_SIZT, 
							pageNum);
				}
				
				// 按关键字搜索
				else if(searchKey == ItemConstants.ITEM_SERACH_BY_KEY) {
					
					String searchText = itemText;
					
					items = B01v00ShopUtils.getShopListByTag(context, 
							context.getString(itemObject.getKeyId()), 
							searchText, 
							ItemConstants.DEL_PAGE_SIZT, 
							pageNum);
				}
				
				// 取得了数据,更新UI
				((B01V00Activity)context).setShopItems(items, isClear);
				
				// 判断数据是否取得成功
				if(items != null && !items.isEmpty() && items.size() > 0 
						&& items.get(B01v00ShopUtils.RESULTS_KEY) != null) {
					
					// 设置菜系
					if (searchKey == ItemConstants.ITEM_SERACH_BY_KEY) {

						final String navTextTemp;
						
						// 选择全部的时候，显示菜系
						if (item_select_index == 0) {
							navTextTemp = context.getString(R.string.b01v01_1_nav_caixi);
						} else {
							navTextTemp = navItem[item_select_index];
						}
						
						// 设置消息显示
						_handler.post(new Runnable() {
							@Override
							public void run() {
								navName.setText(navTextTemp);								
							}
						});
					}
					return;
				}
				
			}
		}).start();
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
		navItems.setOnItemClickListener(navItemClick);
		
		navStatusImg = (ImageView) mainView.findViewById(R.id.b01v00NavImg);
	}
	
	/**
	 * 设置数据源
	 */
	private void setAdapterData() {
		
		// 设置数据源
		navItem = context.getResources().getStringArray(itemObject.getNavItemTextId()[index]);
		
		for (int i = 0; i < navItem.length; i++) {
			Map<String, String> item = new HashMap<String, String>();
			item.put(B01v00NavItemAdapter.from[0], navItem[i]);
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
		itemAdapter.setSelectIndex(item_select_index);
		itemAdapter.notifyDataSetChanged();
	}
	
	public void setItem_select_index(int item_select_index) {
		this.item_select_index = item_select_index;
	}
	
	public int getItem_select_index() {
		return item_select_index;
	}
	
	/** 返回View */
	public View get() {
		return mainView;
	}
}

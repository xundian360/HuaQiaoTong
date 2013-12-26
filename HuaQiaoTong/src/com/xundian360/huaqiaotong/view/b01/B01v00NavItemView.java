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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b01.B01V00Activity;
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
	
	public static String[] from = {"b01v00NavItemName"};
	public static int[] to = {R.id.b01v00NavItemName};
	
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
	int intem_select_intex = 0;
	
	// 项目数据源
	SimpleAdapter itemAdapter;
	List<Map<String, String>> data = new ArrayList<Map<String, String>>();
	
	// 设置数据源
	String[] navItem;
	
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
	 *  项目点击事件
	 */
	OnItemClickListener navItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			if(intem_select_intex == arg2) {
				return;
			}
			
			// 设值选中条件
			intem_select_intex = arg2;
			
			// 加载数据
			loadData(0);
			
			// TODO 设置子项目隐藏
			navItems.setVisibility(View.GONE);
		}
	};
	
	/**
	 * 加载数据
	 * @param index
	 */
	public void loadData(final int pageNum) {
		
		// 取得排序方法
		final int searchKey = itemObject.getNavItemSearchKey()[index];
		
		// 加载数据
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				// 返回值
				Map<String, Object> items = new HashMap<String, Object>();
				
				// 项目值
				String itemText = navItem[intem_select_intex];
				
				// 全部选择的时候
				if(intem_select_intex == 0) {
					// 取得所有商店信息
					items = B01v00ShopUtils.getShopList(context, 
							context.getString(itemObject.getKeyId()), 
							ItemConstants.DEL_PAGE_SIZT, 
							pageNum);
				}
				
				// 按价格搜索
				else if(searchKey == ItemConstants.ITEM_SERACH_BY_PRICE) {
					
					String minPrice = StringUtils.getNumInString(itemText, 0);
					String maxPrice = StringUtils.getNumInString(itemText, 1);
					if(StringUtils.isBlank(maxPrice)) {
						maxPrice = Integer.MAX_VALUE + "";
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
					
					String minL = StringUtils.getNumInString(itemText, 0);
					String maxL = StringUtils.getNumInString(itemText, 1);
					if(StringUtils.isBlank(maxL)) {
						maxL = Integer.MAX_VALUE + "";
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
				if(items != null && !items.isEmpty()) {
					((B01V00Activity)context).setShopItems(items);
				}
			}
		}).start();
	}
	
	Runnable loadDataRun = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
	};
	
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

/**
 * 
 */
package com.xundian360.huaqiaotong.view.b01;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.xundian360.huaqiaotong.modle.b01.ItemSearchCondition;

/**
 * 导航视图
 * 
 * @author Administrator
 * @date 2013年12月5日
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

	// 项目数据源
	B01v00NavItemAdapter itemAdapter;
	List<Map<String, String>> data = new ArrayList<Map<String, String>>();

	// 设置数据源
	String[] navItem;

	// 当前导航时候可展开
	boolean canExpansion = false;
	// 选中的条件
	int item_select_index = -1;
	// 检索条件
	int searchKey;

	// 导航展开标志
	public boolean isExpansion = false;
	
	Handler _handler = new Handler();
	
	ItemSearchCondition searchCondition;

	public B01v00NavItemView(Context context, ItemObject itemObject, int index, 
			ItemSearchCondition searchCondition) {
		this.context = context;
		this.itemObject = itemObject;
		this.index = index;
		this.searchCondition = searchCondition;

		// 初始化数据
		initData();

		// 初始化视图
		initView();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		// 设置导航检索条件
		searchKey = itemObject.getNavItemSearchKey()[index];
		
		// 判断导航是否可展开(可展开)
		if(Arrays.asList(ItemConstants.ITEM_CAN_EXPANSION_KEY).contains(itemObject.getNavItemKeyId())) {
			canExpansion = true;
		}
		
		// 设置数据源
		setAdapterData();

		// 设置Adapter
		itemAdapter = new B01v00NavItemAdapter(context, data,
				R.layout.b01v00_nav_item_item, B01v00NavItemAdapter.from,
				B01v00NavItemAdapter.to);
	}
	
	/**
	 * 初始化视图
	 */
	private void initView() {

		mainView = LayoutInflater.from(context).inflate(R.layout.b01v00_nav_item, null);

		navView = (LinearLayout) mainView.findViewById(R.id.b01v00NavView);
		navView.setOnClickListener(navViewClick);

		navName = (TextView) mainView.findViewById(R.id.b01v00NavName);
		navName.setText(context.getResources().getStringArray(itemObject.getNavId())[index]);

		navItems = (ListView) mainView.findViewById(R.id.b01v00NavItems);
		navItems.setAdapter(itemAdapter);
		navItems.setOnItemClickListener(navItemClick);

		navStatusImg = (ImageView) mainView.findViewById(R.id.b01v00NavImg);
		
		// 不可展开，设置为双向箭头
		if(!canExpansion) {
			
			// 判断箭头方向（价格默认升序，关注默认降序）
			// 价格默认升序
			if (searchKey == ItemConstants.ITEM_SERACH_BY_PRICE) {
				navStatusImg.setImageResource(R.drawable.b01v00_nav_arr_j_0);
			}

			// 关注默认降序
			else if (searchKey == ItemConstants.ITEM_SERACH_BY_ATTENTION) {
				navStatusImg.setImageResource(R.drawable.b01v00_nav_arr_s_0);
			}
		}
	}

	/**
	 * 项目点击事件
	 */
	OnItemClickListener navItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			if(canExpansion) {
				if (item_select_index != arg2) {
					// 设值选中条件
					item_select_index = arg2;

					// 加载数据
					loadData();
				}

				// 设置子项目隐藏
				navItems.setVisibility(View.GONE);
			}
		}
	};

	/**
	 * 加载数据
	 */
	public void loadData() {
		
		// 项目值
		String itemText = navItem[item_select_index];
		
		// 设置检索条件
		searchCondition.setSearchText(itemText);
		
		// 加载数据
		((B01V00Activity)context).setDataWithClear();
		
	}
	
	/**
	 * 设置数据源
	 */
	private void setAdapterData() {
		
		// 判断导航是否可展开(可展开)
		if(canExpansion) {
			// 设置展开项目数据源
			navItem = context.getResources().getStringArray(
					itemObject.getNavItemTextId()[index]);

			for (int i = 0; i < navItem.length; i++) {
				Map<String, String> item = new HashMap<String, String>();
				item.put(B01v00NavItemAdapter.from[0], navItem[i]);
				data.add(item);
			}
		}

	}

	/**
	 * 导航点击
	 */
	OnClickListener navViewClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			
			// 可以展开的时候，展开
			if(canExpansion) {
				// 判断显示，隐藏子项目
				if (isExpansion) { // 隐藏子项目
					// 选中当前导航
					isExpansionAction();
				} else { // 显示子项目

					// 失去当前导航
					lossExpansionAction();
				}
			} else {
				// 不可以展开的时候，设置箭头方向
				// 价格
				if (searchKey == ItemConstants.ITEM_SERACH_BY_PRICE) {
					// 设置价格排序
					if((int)searchCondition.getPriceOrder() == ItemSearchCondition.ORDER_DOWN) {
						searchCondition.setPriceOrder(ItemSearchCondition.ORDER_UP);
						// 设置图片
						navStatusImg.setImageResource(R.drawable.b01v00_nav_arr_j_0);
					} else {
						searchCondition.setPriceOrder(ItemSearchCondition.ORDER_DOWN);
						// 设置图片
						navStatusImg.setImageResource(R.drawable.b01v00_nav_arr_s_0);
					}
					
					// 设置价格为第一排序条件
					searchCondition.setOrderWhich(ItemSearchCondition.FIRST_ORDER_PRICE);
				}

				// 关注
				else if (searchKey == ItemConstants.ITEM_SERACH_BY_ATTENTION) {
					// 设置关注排序
					if((int)searchCondition.getAttentionOrder() == ItemSearchCondition.ORDER_DOWN) {
						searchCondition.setAttentionOrder(ItemSearchCondition.ORDER_UP);
						// 设置图片
						navStatusImg.setImageResource(R.drawable.b01v00_nav_arr_j_0);
					} else {
						searchCondition.setAttentionOrder(ItemSearchCondition.ORDER_DOWN);
						// 设置图片
						navStatusImg.setImageResource(R.drawable.b01v00_nav_arr_s_0);
					}
					
					// 设置关注为第一排序条件
					searchCondition.setOrderWhich(ItemSearchCondition.FIRST_ORDER_ATTENTION);
				}
				
				// 加载数据
				((B01V00Activity)context).setDataWithClear();
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
		navName.setTextColor(context.getResources().getColor(
				R.color.b04v05_item_supper_color));
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
		navView.setBackgroundColor(context.getResources().getColor(
				R.color.b04v05_item_supper_color));
		// 设置文字颜色
		navName.setTextColor(Color.WHITE);
		// 设置箭头方向图片
		navStatusImg.setImageResource(R.drawable.b01v00_nav_arr_1);
		// 设置选中的导航不显示
//		((B01V00Activity) context).setSelectNav(index);
		// 设置表示反转
		isExpansion = !isExpansion;

		// TODO 设置子项目显示
		navItems.setVisibility(View.VISIBLE);
//		itemAdapter.setSelectIndex(item_select_index);
		itemAdapter.notifyDataSetChanged();
	}

	/** 返回View */
	public View get() {
		return mainView;
	}
}

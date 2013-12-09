/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.adapter.b01.B01v00KtvAdapter;
import com.xundian360.huaqiaotong.modle.b01.ItemObject;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.view.b01.B01v00NavItemView;

/**
 * KTV,饭店，宾馆列表
 * @author  TengTeng
 * @date      2013-10-3
 * @version 1.0
 */
public class B01V00Activity extends ComNoTittleActivity {
	
	public static final String ITEM_OBJECT_KEY = "item_object_key";
	
	LinearLayout.LayoutParams navViewPar = new LinearLayout.LayoutParams(
			LayoutParams.MATCH_PARENT, 
			LayoutParams.WRAP_CONTENT,
			1);
	
	// Tittle
	RelativeLayout tittleLayout;
	// 搜索框
	LinearLayout searchLayout;
	// 返回按钮
	ImageButton retBtn;
	// 标题
	TextView tittleText;
	// 搜索按钮
	ImageButton searchBtn;
	// 搜索输入Text
	AutoCompleteTextView searchText;
	// 取消搜索
	TextView cancelBtnText;
	// 导航容器
	LinearLayout selectConditionCon;
	// 项目 List
	ListView itemListView;
	// 去地图
	ImageView toMapBtn;
	
	// 数据源
	List<Baidu> itemsData = new ArrayList<Baidu>();
	List<Map<String, ?>> data = new ArrayList<Map<String,?>>();
	// Adapter
	B01v00KtvAdapter adapter;
	
	// 当前项目显示的分类
	ItemObject itemObject;
	
	// 导航
	List<B01v00NavItemView> navItems = new ArrayList<B01v00NavItemView>();
	
	Handler _handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b01v00);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		
		itemObject = (ItemObject) getIntent().getSerializableExtra(ITEM_OBJECT_KEY);
		
		// 设置数据
		setData();
		setAdapterData();
		
		// 设置Adapter对象
		adapter = new B01v00KtvAdapter(this, data, 
				R.layout.b01v00_item, B01v00KtvAdapter.from, 
				B01v00KtvAdapter.to, itemsData);
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		tittleLayout = (RelativeLayout) findViewById(R.id.b01v00TittleLayout1);
		searchLayout= (LinearLayout) findViewById(R.id.b01v00TittleLayout2);
		
		retBtn = (ImageButton) findViewById(R.id.b01v00RetBtn);
		retBtn.setOnClickListener(retBtnClick);
		
		tittleText = (TextView) findViewById(R.id.b01v00Tittle);
		tittleText.setText(getString(itemObject.getTittleId()));
		
		searchBtn = (ImageButton) findViewById(R.id.b01v00SearchBtn);
		searchBtn.setOnClickListener(searchBtnClick);
		
		searchText = (AutoCompleteTextView) findViewById(R.id.b01v00SearchText);
		searchText.setHint(getString(R.string.b01v01_tittle_hint, getString(itemObject.getTittleId())));
		
		cancelBtnText = (TextView) findViewById(R.id.b01v00CancelBtn);
		cancelBtnText.setOnClickListener(cancelBtClick);
		
		selectConditionCon = (LinearLayout) findViewById(R.id.b01v00SelectCondition);
		
		itemListView = (ListView) findViewById(R.id.b01v00Items);
		itemListView.setAdapter(adapter);
		
		toMapBtn = (ImageView) findViewById(R.id.b01v00ToMapBtn);
		toMapBtn.setOnClickListener(toMapBtnClick);
		
		// 动态设置筛选条件
		autoSetSelection();
	}
	
	/**
	 * 返回按钮事件
	 */
	OnClickListener retBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			onBackPressed();
		}
	};
	
	/**
	 * 搜索按钮事件
	 */
	OnClickListener searchBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// 切换头部显示
			switchTittle();
		}
	};
	
	/**
	 * 切换头部显示
	 */
	private void switchTittle() {
		
		if(tittleLayout.getVisibility() == View.VISIBLE) {
			
			// 设置显隐性
			tittleLayout.startAnimation(AnimationUtils.loadAnimation(B01V00Activity.this, R.anim.b01v00_tittle_out));
			tittleLayout.setVisibility(View.GONE);
			
			searchLayout.startAnimation(AnimationUtils.loadAnimation(B01V00Activity.this, R.anim.b01v00_tittle_in));
			searchLayout.setVisibility(View.VISIBLE);
			
			// 设置焦点
			searchText.requestFocus();
			
			// 打开键盘
			CommonUtil.showInput(B01V00Activity.this);
		} else {
			
			// 设置显隐性
			tittleLayout.startAnimation(AnimationUtils.loadAnimation(B01V00Activity.this, R.anim.b01v00_tittle_in));
			tittleLayout.setVisibility(View.VISIBLE);
			
			searchLayout.startAnimation(AnimationUtils.loadAnimation(B01V00Activity.this, R.anim.b01v00_tittle_out));
			searchLayout.setVisibility(View.GONE);
			
			// 设置焦点
			searchText.clearFocus();
			
			// 关闭键盘
			CommonUtil.hideInput(B01V00Activity.this);
		}
	}
	
	/**
	 * 搜索按钮事件
	 */
	OnClickListener cancelBtClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// 切换头部显示
			switchTittle();
		}
	};
	
	/**
	 * 地图按钮事件
	 */
	OnClickListener toMapBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
			Intent intent = new Intent(B01V00Activity.this, B01V04Activity.class);
			intent.putExtra(B01V00Activity.ITEM_OBJECT_KEY, itemObject);
			
			CommonUtil.startSubActivity(B01V00Activity.this, intent);
		}
	};
	
	/**
	 * 动态设置筛选条件
	 */
	private void autoSetSelection(){
		
		// 所有导航项目
		String[] navTexts = getResources().getStringArray(itemObject.getNavId());
		
		// 设置容器比重
		selectConditionCon.setWeightSum(navTexts.length);
		
		// 分别添加导航View
		for (int i = 0; i < navTexts.length; i++) {
			// 导航View
			B01v00NavItemView navView = new B01v00NavItemView(this, itemObject, i);
			
			// 添加到容器
			selectConditionCon.addView(navView.get(), navViewPar);
			
			// 添加到导航列表
			navItems.add(navView);
		}
	}
	
	/**
	 * 设置选中的导航
	 */
	public void setSelectNav(int selectIndex) {
		
		// 设置其它View显示
		for (int i = 0; i < navItems.size(); i++) {
			
			B01v00NavItemView navView = navItems.get(i);
			
			// 设置其它导航不显示
			if(selectIndex != i) {
				
				// 当前View显示
				if(navView.isExpansion) {
					navView.isExpansionAction();
				}
			}
		}
	}
	
	/**
	 * 更新ListView
	 */
	Runnable updateList = new Runnable() {
		
		@Override
		public void run() {
			
			// 设置数据源
			setAdapterData();
			
			// 更新ListView
			adapter.notifyDataSetChanged();
		}
	};
	
	/**
	 *  设置数据
	 */
	private void setData() {
		
		for (int i = 0; i < 12; i++) {
			
			Baidu baiduItem = new Baidu();
			
			baiduItem.setName("湘聚缘" + i);
			baiduItem.setPrice("￥" + 10 * i + "");
			baiduItem.setDisc_tittle("推荐菜：");
			baiduItem.setDisc("双椒鱼头,上海红烧肉,海鲜炒饭,佛跳墙");
			
			itemsData.add(baiduItem);
		}
	}
	
	/**
	 * 设置数据源值
	 */
	private void setAdapterData() {
		
		// 清空数据源
		data.clear();
		
		// 遍历，设置ListView项目
		for (int i = 0; i < itemsData.size(); i++) {
			
			Baidu itemFirst = itemsData.get(i);
			
			// 数据项目
			Map<String, Object> dataItem = new HashMap<String, Object>();
			
			dataItem.put(B01v00KtvAdapter.from[0], R.drawable.test_b01v00_item_img);
			dataItem.put(B01v00KtvAdapter.from[1], itemFirst.getName());
			dataItem.put(B01v00KtvAdapter.from[2], itemFirst.getDisc_tittle());
			dataItem.put(B01v00KtvAdapter.from[3], itemFirst.getDisc());
			dataItem.put(B01v00KtvAdapter.from[4], itemFirst.getPrice());
			
			// 添加到数据源
			data.add(dataItem);
		}
	}
}

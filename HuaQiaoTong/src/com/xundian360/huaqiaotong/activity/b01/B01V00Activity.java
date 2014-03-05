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
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.adapter.b01.B01v00KtvAdapter;
import com.xundian360.huaqiaotong.modle.b01.ItemConstants;
import com.xundian360.huaqiaotong.modle.b01.ItemObject;
import com.xundian360.huaqiaotong.modle.b01.ItemSearchCondition;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.StringUtils;
import com.xundian360.huaqiaotong.util.b01.B01v00ShopUtils;
import com.xundian360.huaqiaotong.view.b01.B01v00NavItemView;
import com.xundian360.huaqiaotong.view.com.CommonProgressDialog;
import com.xundian360.huaqiaotong.view.com.XListView;
import com.xundian360.huaqiaotong.view.com.XListView.IXListViewListener;

/**
 * KTV,饭店，宾馆列表
 * 
 * @author TengTeng
 * @date 2013-10-3
 * @version 1.0
 */
public class B01V00Activity extends ComNoTittleActivity {

	public static final String ITEM_OBJECT_KEY = "item_object_key";

	LinearLayout.LayoutParams navViewPar = new LinearLayout.LayoutParams(
			LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);

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
	XListView itemListView;
	// 去地图
	ImageView toMapBtn;

	LinearLayout conditionBg;

	// 数据源
	List<Baidu> itemsData = new ArrayList<Baidu>();
	List<Map<String, ?>> data = new ArrayList<Map<String, ?>>();
	// Adapter
	B01v00KtvAdapter adapter;

	// 当前项目显示的分类
	ItemObject itemObject;

	// 导航
	List<B01v00NavItemView> navItems = new ArrayList<B01v00NavItemView>();

	Handler _handler = new Handler();

	CommonProgressDialog processDialog;

	// 商店数量
	int totalNum = 0;
	// 查询条件
	ItemSearchCondition searchCondition;
	
	// 当前是否加载
	boolean canLoad = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.b01v00);

		// 初始化数据
		initData();

		// 初始化组件
		initModule();

		// 动态设置筛选条件
		autoSetSelection();
		
		// 设置数据
		setDataNotClear();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {

		itemObject = (ItemObject) getIntent().getSerializableExtra(
				ITEM_OBJECT_KEY);
		
		searchCondition = new ItemSearchCondition(getString(itemObject.getKeyId()), 10, 0, 
				ItemSearchCondition.ORDER_UP, 
				ItemSearchCondition.ORDER_DOWN);

		processDialog = new CommonProgressDialog(this);

		adapter = new B01v00KtvAdapter(this, data, R.layout.b01v00_item,
				B01v00KtvAdapter.from, B01v00KtvAdapter.to, itemsData);
	}

	/**
	 * 初始化组件
	 */
	private void initModule() {

		tittleLayout = (RelativeLayout) findViewById(R.id.b01v00TittleLayout1);
		searchLayout = (LinearLayout) findViewById(R.id.b01v00TittleLayout2);

		retBtn = (ImageButton) findViewById(R.id.b01v00RetBtn);
		retBtn.setOnClickListener(retBtnClick);

		tittleText = (TextView) findViewById(R.id.b01v00Tittle);
		tittleText.setText(getString(itemObject.getTittleId()));

		searchBtn = (ImageButton) findViewById(R.id.b01v00SearchBtn);
		searchBtn.setOnClickListener(searchBtnClick);

		searchText = (AutoCompleteTextView) findViewById(R.id.b01v00SearchText);
		searchText.setHint(getString(R.string.b01v01_tittle_hint,
				getString(itemObject.getTittleId())));
		searchText.setOnEditorActionListener(searchTextAction);

		cancelBtnText = (TextView) findViewById(R.id.b01v00CancelBtn);
		cancelBtnText.setOnClickListener(cancelBtClick);

		selectConditionCon = (LinearLayout) findViewById(R.id.b01v00SelectCondition);

		itemListView = (XListView) findViewById(R.id.b01v00Items);
		itemListView.setPullLoadEnable(canLoad);
		itemListView.setAdapter(adapter);
		itemListView.setXListViewListener(itemListPush);

		toMapBtn = (ImageView) findViewById(R.id.b01v00ToMapBtn);
		toMapBtn.setOnClickListener(toMapBtnClick);

		conditionBg = (LinearLayout) findViewById(R.id.b01v00SelectConditionBg);
	}

	/**
	 * 键盘事件
	 */
	OnEditorActionListener searchTextAction = new OnEditorActionListener() {

		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

			// 点击键盘的Search按钮
			if (actionId == EditorInfo.IME_ACTION_SEARCH) {

				searchCondition.setPageNum(0);

				// 取得搜索的店铺信息
				setSearchDate();

				switchTittle();
			}
			return false;
		}
	};

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

	IXListViewListener itemListPush = new IXListViewListener() {

		@Override
		public void onRefresh() {
			_handler.postDelayed(new Runnable() {
				@Override
				public void run() {

					// 加载数据
					loadData();
				}
			}, 2000);

		}

		@Override
		public void onLoadMore() {
			_handler.postDelayed(new Runnable() {
				@Override
				public void run() {

					// 加载数据
					loadData();
				}
			}, 2000);

		}
	};

	/**
	 * 加载完成
	 */
	private void onLoad() {
		itemListView.stopRefresh();
		itemListView.stopLoadMore();
		canLoad = true;
	}

	/**
	 * 加载数据
	 */
	private void loadData() {

		// 判断是不是最后一页
		if (itemsData.size() == 0 || itemsData.size() < totalNum) {
			// 判断是否正在加载
			if (canLoad) {

				canLoad = false;

				// 页面++
				searchCondition.setPageNum(searchCondition.getPageNum() + 1);

				// 取得所有数据
				setDataNotClear();
				
				// 加载完成
				onLoad();
			} else {
				ShowMessageUtils.show(this, "数据正在加载...");
			}
		} else {
			canLoad = false;
			itemListView.setPullLoadEnable(canLoad);
			ShowMessageUtils.show(this, "已经是最后一页...");
			// 加载完成
			onLoad();
		}
	}

	/**
	 * 切换头部显示
	 */
	private void switchTittle() {

		if (tittleLayout.getVisibility() == View.VISIBLE) {

			// 设置显隐性
			tittleLayout.startAnimation(AnimationUtils.loadAnimation(
					B01V00Activity.this, R.anim.b01v00_tittle_out));
			tittleLayout.setVisibility(View.GONE);

			searchLayout.startAnimation(AnimationUtils.loadAnimation(
					B01V00Activity.this, R.anim.b01v00_tittle_in));
			searchLayout.setVisibility(View.VISIBLE);

			// 设置焦点
			searchText.requestFocus();

			// 打开键盘
			CommonUtil.showInput(B01V00Activity.this);
		} else {

			// 设置显隐性
			tittleLayout.startAnimation(AnimationUtils.loadAnimation(
					B01V00Activity.this, R.anim.b01v00_tittle_in));
			tittleLayout.setVisibility(View.VISIBLE);

			searchLayout.startAnimation(AnimationUtils.loadAnimation(
					B01V00Activity.this, R.anim.b01v00_tittle_out));
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

			Intent intent = new Intent(B01V00Activity.this,
					B01V04Activity.class);
			intent.putExtra(B01V00Activity.ITEM_OBJECT_KEY, itemObject);

			CommonUtil.startSubActivity(B01V00Activity.this, intent);
		}
	};

	/**
	 * 动态设置筛选条件
	 */
	private void autoSetSelection() {

		// 存在筛选项目
		if (itemObject.getNavId() != ItemConstants.ITEM_NAV_NULL) {
			// 所有导航项目
			String[] navTexts = getResources().getStringArray(
					itemObject.getNavId());

			// 设置容器比重
			selectConditionCon.setWeightSum(navTexts.length);

			// 分别添加导航View
			for (int i = 0; i < navTexts.length; i++) {
				// 导航View
				B01v00NavItemView navView = new B01v00NavItemView(this,
						itemObject, i, searchCondition);

				// 添加到容器
				selectConditionCon.addView(navView.get(), navViewPar);

				// 添加到导航列表
				navItems.add(navView);
			}
		} else {
			conditionBg.setVisibility(View.GONE);
			((RelativeLayout.LayoutParams) itemListView.getLayoutParams())
					.setMargins(0, 10, 0, 0);
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
			adapter.setItems(itemsData);
			adapter.notifyDataSetChanged();

			processDialog.dismiss();
		}
	};

	/**
	 * 取得信息失败
	 */
	Runnable getMsgError = new Runnable() {

		@Override
		public void run() {
			ShowMessageUtils.show(B01V00Activity.this, "取得数据失败");
			processDialog.dismiss();
		}
	};

	/**
	 * 取得信息为空
	 */
	Runnable getMsgBlank = new Runnable() {

		@Override
		public void run() {
			ShowMessageUtils.show(B01V00Activity.this, "未取到数据");
			processDialog.dismiss();
		}
	};

	/**
	 * 取得商店数据(不清空原来数据)
	 */
	Runnable getShopDataNotClear = new Runnable() {
		@Override
		public void run() {

			// 取得所有商店信息
			Map<String, Object> shopItems = B01v00ShopUtils.getShopList(
					B01V00Activity.this, searchCondition);

			// 设置数据
			setShopItems(shopItems, false);
		}
	};
	
	/**
	 * 取得商店数据(清空原来数据)
	 */
	Runnable getShopDataWithClear = new Runnable() {
		@Override
		public void run() {

			// 取得所有商店信息
			Map<String, Object> shopItems = B01v00ShopUtils.getShopList(
					B01V00Activity.this, searchCondition);

			// 设置数据
			setShopItems(shopItems, true);
		}
	};

	/**
	 * 设置数据
	 * 
	 * @param shopItems
	 * @param isClear
	 *            是否清空数据
	 */
	@SuppressWarnings("unchecked")
	public void setShopItems(Map<String, Object> shopItems, boolean isClear) {
		if (shopItems == null || shopItems.isEmpty() || shopItems.size() <= 0) {
			// 更新UI
			_handler.post(getMsgError);
			return;
		}

		totalNum = StringUtils.paseInt(
				(String) shopItems.get(B01v00ShopUtils.TOTAL_KEY), 0);

		List<Baidu> shopItemsNet = (List<Baidu>) shopItems
				.get(B01v00ShopUtils.RESULTS_KEY);

		// 取得数据为空
		if (totalNum <= 0 || shopItemsNet == null || shopItemsNet.isEmpty()
				|| shopItemsNet.size() <= 0) {
			_handler.post(getMsgBlank);
			return;
		}

		if (isClear) {

			itemsData.clear();

			itemsData = shopItemsNet;

			// 设置页面为第一页
			searchCondition.setPageNum(0);
		} else {
			itemsData.addAll(shopItemsNet);
		}

		// 更新UI
		_handler.post(updateList);

		// 判断是不是最后一页
		if (itemsData.size() >= totalNum) {
			_handler.post(new Runnable() {
				@Override
				public void run() {
					canLoad = false;
					itemListView.setPullLoadEnable(canLoad);
				}
			});
		} else {
			_handler.post(new Runnable() {
				@Override
				public void run() {
					canLoad = true;
					itemListView.setPullLoadEnable(canLoad);
				}
			});
		}
	}

	/**
	 * 取得搜索的店铺信息
	 */
	private void setSearchDate() {

		String searchTextV = searchText.getText().toString();

		// 输入不为空
		if (StringUtils.isNotBlank(searchTextV)) {
			
			// 设置查询条件
			searchCondition.setSearchText2(searchTextV);

			// 取得商店数据
			setDataWithClear();
		}
	}

	/**
	 *  设置数据(不清空原来数据)
	 */
	public void setDataNotClear() {
		
		processDialog.show();

		// 取得商店数据
		new Thread(getShopDataNotClear).start();
	}
	
	/**
	 *  设置数据(清空原来数据)
	 */
	public void setDataWithClear() {
		
		processDialog.show();

		// 取得商店数据
		new Thread(getShopDataWithClear).start();
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

			dataItem.put(B01v00KtvAdapter.from[0],
					R.drawable.b01v00_item_dafilt_img);
			dataItem.put(B01v00KtvAdapter.from[1], itemFirst.getName());
			dataItem.put(B01v00KtvAdapter.from[2], itemFirst.getDisc_tittle());
			// dataItem.put(B01v00KtvAdapter.from[3], itemFirst.getDisc());
			dataItem.put(B01v00KtvAdapter.from[3], "");
			dataItem.put(B01v00KtvAdapter.from[4], itemFirst.getPrice());

			// 添加到数据源
			data.add(dataItem);
		}
	}
	
	/**
	 * 设置查询条件
	 * @return
	 */
	public ItemSearchCondition getSearchCondition() {
		return searchCondition;
	}

}

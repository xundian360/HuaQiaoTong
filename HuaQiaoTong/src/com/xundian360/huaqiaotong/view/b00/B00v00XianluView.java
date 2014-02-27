package com.xundian360.huaqiaotong.view.b00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.fortysevendeg.swipelistview.SwipeListView;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b00.B00V02Activity;
import com.xundian360.huaqiaotong.adapter.b00.B00v00XianluAdapter;
import com.xundian360.huaqiaotong.adapter.b00.SearchAdapter;
import com.xundian360.huaqiaotong.modle.b00.Bus;
import com.xundian360.huaqiaotong.modle.b00.BusSavingModle;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.ViewUtils;
import com.xundian360.huaqiaotong.util.b00.HuanchengUtil;

/**
 * 线路视图
 * 
 * @author tengteng
 * @time 下午1:29:55
 * @version 1.0
 */
public class B00v00XianluView {

	View mainView;

	Context context;

	// 检索的线路
	AutoCompleteTextView xianluText;
	// 删除输入按钮
	ImageView inputDelete;
	// 搜索线路按钮
	Button xianluSearchBtn;
	// 搜索历史记录
	SwipeListView historyList;

	// 线路Adapter
	SearchAdapter<String> xianluAdapter;
	// 数据源
	List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

	// 所有线路信息
	List<Bus> buses = new ArrayList<Bus>();
	String[] buseNames;

	// 所有收藏的线路信息
	List<Bus> shoucangBuses;

	// 历史线路信息
	List<Bus> hisBuses = new ArrayList<Bus>();
	List<Map<String, String>> hisData = new ArrayList<Map<String, String>>();
//	B00V00HisXianAdapter hisAdapter;

	// 所有显示的线路信息
	List<Bus> showBuses = new ArrayList<Bus>();

	Handler _handler = new Handler();

	// // 线路数据库操作类
	// BusOperatingHelper dbHelper;
	//
	// 当前检索的线路信息
	Bus searchBus = null;

	// 存储历史记录
	BusSavingModle busSaving;

	public B00v00XianluView(Context context) {

		this.context = context;

		// 初始化数据
		initData();

		// 初始化视图
		initView();

	}

	/**
	 * 初始化数据
	 */
	private void initData() {

		busSaving = new BusSavingModle(context);

		// 加载数据
		new Thread(laodData).start();
	}

	/**
	 * 加载数据
	 */
	Runnable laodData = new Runnable() {

		@Override
		public void run() {
			// // 初始化数据
			// dbHelper = new BusOperatingHelper(context);
			// 查询所有线路信息
			buses = HuanchengUtil.getAllBuses(context);

//			                                                                                                                                                                 = new B00V00HisXianAdapter(context, hisData,
//					R.layout.b00v00_xianli_item, from, to);

			// 设置数据源
			setAdapterData(null);

			// 刷新UI
			_handler.postDelayed(new Runnable() {

				@Override
				public void run() {

					// 设置Adapter
					xianluAdapter = new SearchAdapter<String>(context,
							android.R.layout.simple_dropdown_item_1line,
							buseNames, SearchAdapter.ALL);// 速度优先

					xianluText.setAdapter(xianluAdapter);
					// xianluAdapter.notifyDataSetChanged();
				}
			}, 1000);
		}
	};

	/**
	 * 设置数据源值
	 */
	private void setAdapterData(String referText) {

		// 清空数据源
		data.clear();
		showBuses.clear();

		buseNames = new String[buses.size()];

		for (int i = 0; i < buses.size(); i++) {

			Bus bus = buses.get(i);

			// 设置包含特定字符的信息
			if (referText == null || bus.getRouteName().contains(referText)) {

				Map<String, Object> dataItem = new HashMap<String, Object>();

				dataItem.put(B00v00XianluAdapter.from[0], bus.getRouteKey());

				dataItem.put(B00v00XianluAdapter.from[1], bus.getDirection());

				buseNames[i] = bus.getRouteKey() + " " + bus.getDirection();

				data.add(dataItem);
				showBuses.add(bus);
			}
		}

		Log.d("debug", "data > " + data);
	}

	/**
	 * 初始化视图
	 */
	private void initView() {

		mainView = ((Activity) context).getLayoutInflater().inflate(
				R.layout.b00v00_xianlu, null);

		xianluText = (AutoCompleteTextView) mainView
				.findViewById(R.id.b00v00XianluText);
		xianluText.setOnItemClickListener(xianluItemClick);
		xianluText.setOnEditorActionListener(xianluSearch);

		inputDelete = (ImageView) mainView
				.findViewById(R.id.b00v00XianluDelete);

		// 设置删除按钮的显隐性
		ViewUtils.inputMonitor(xianluText, inputDelete);

		xianluSearchBtn = (Button) mainView
				.findViewById(R.id.b00v00XianluSearchBtn);
		xianluSearchBtn.setOnClickListener(xianluSearchClick);

		historyList = (SwipeListView) mainView
				.findViewById(R.id.b00v00XianluList);
//		historyList.setAdapter(hisAdapter);

		// 启动时调用
		onStart();
	}

	/**
	 * 开始搜索
	 */
	OnEditorActionListener xianluSearch = new OnEditorActionListener() {
		@Override
		public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
			if (EditorInfo.IME_ACTION_SEARCH == arg1) {
				// 搜索
				xianluSearchAction();
			}
			return true;
		}
	};

	/**
	 * 线路检索按钮
	 */
	OnClickListener xianluSearchClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 搜索
			xianluSearchAction();
		}
	};

	/**
	 * 搜索
	 */
	private void xianluSearchAction() {
		// 检查网络
		if (!CommonUtil.isNetworkAvailable(context)) {

			ShowMessageUtils.show(context, R.string.message_error_network);
			return;
		}

		if (searchBus != null) {

			Intent in = new Intent(context, B00V02Activity.class);
			in.putExtra(B00V02Activity.BUS_KEY, searchBus);

			CommonUtil.startActivityForResult(context, in, 100);
		} else {
			ShowMessageUtils.show(context, "请输入检索线路");
		}
	}

	/**
	 * 线路项目点击事件
	 */
	OnItemClickListener xianluItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			searchBus = showBuses.get(arg2);
		}
	};

	/**
	 * 返回View
	 */
	public View get() {
		return mainView;
	}

	/**
	 * 启动时调用
	 */
	private void onStart() {

		hisBuses.clear();
		// 加载存储的历史记录
		String[] busIds = busSaving.getXianluIds().split(
				BusSavingModle.SEPARATOR);

		if (busIds != null && busIds.length > 0) {
			for (String busId : busIds) {
				Bus hisBus = HuanchengUtil.getBusById(buses, busId);
				hisBuses.add(hisBus);
			}
		}
//
//		// 更新ListView
//		hisAdapter.notifyDataSetChanged();
	}
}

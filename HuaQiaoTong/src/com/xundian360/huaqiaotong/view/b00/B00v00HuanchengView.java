package com.xundian360.huaqiaotong.view.b00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKSearch;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b00.B00V04Activity;
import com.xundian360.huaqiaotong.activity.b00.B00V05Activity;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleBMapManActivity;
import com.xundian360.huaqiaotong.modle.b00.Station;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.StringUtils;
import com.xundian360.huaqiaotong.util.b00.B00v00HCSearchListener;

/**
 * 线路视图
 * 
 * @author tengteng
 * @time 下午1:29:55
 * @version 1.0
 */
public class B00v00HuanchengView {

	// 驾车线路搜索Key
	public static final int DRIVINGSEARCH_KEY = 0;
	// 公交线路搜索Key
	public static final int TRANSITSEARCH_KEY = 1;
	// 步行线路搜索Key
	public static final int WALKINGSEARCH_KEY = 2;

	// 开始位置获得焦点
	public static final int FOCUS_FROM_KEY = 1;
	// 结束位置获得焦点
	public static final int FOCUS_TO_KEY = 2;

	String[] from_key = { "boov00HCPioTittle", "boov00HCPioAddress" };
	int[] to_key = { R.id.boov00HCPioTittle, R.id.boov00HCPioAddress };

	View mainView;

	Context context;

	// 切换地点
	ImageButton switchTo;
	// 开始位置
	public AutoCompleteTextView fromText;
	// 删除输入按钮
	ImageView fromInputDelete;
	// 结束位置
	public AutoCompleteTextView toText;
	// 删除输入按钮
	ImageView toInputDelete;
	// 查询换乘路线
	Button huanchengSearch;
	// 带图片的描述信息
	TextView infoView;

	int focusKey;

	// 兴趣点数据源
	SimpleAdapter poiListAdapter;
	List<Map<String, String>> poiData = new ArrayList<Map<String, String>>();

	// poi数据列表
	ArrayList<MKPoiInfo> searchPois;

	// 出发站点
	public Baidu startStation = new Station();
	// 结束站点
	public Baidu endStation = new Station();

	// 显示位置选择按钮
	boolean showLocationSelect = true;

	// 检索方式(默认公交)
	int searchWay = TRANSITSEARCH_KEY;

	MKSearch mMKSearch = null;

	public B00v00HuanchengView(Context context) {

		this.context = context;

		// 初始化
		init();
	}

	public B00v00HuanchengView(Context context, boolean showLocationSelect) {

		this.context = context;
		this.showLocationSelect = showLocationSelect;

		// 初始化
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {

		poiListAdapter = new SimpleAdapter(context, poiData,
				R.layout.b00v00_huancheng_poi_item, from_key, to_key);

		// 初始化检索类
		mMKSearch = new MKSearch();
		mMKSearch.init(((ComNoTittleBMapManActivity) context).bMapManager,
				new B00v00HCSearchListener(context, this));

		// 初始化视图
		initView();
	}

	/**
	 * 初始化视图
	 */
	private void initView() {

		mainView = ((Activity) context).getLayoutInflater().inflate(
				R.layout.b00v00_huancheng, null);

		switchTo = (ImageButton) mainView.findViewById(R.id.b00v00Switch);
		switchTo.setOnClickListener(switchToClick);

		fromText = (AutoCompleteTextView) mainView
				.findViewById(R.id.b00v00FromText);
		fromText.addTextChangedListener(fromTextWatcher);
		// fromText.setAdapter(poiListAdapter);
		fromText.setOnItemClickListener(poisListItemClick);

		toText = (AutoCompleteTextView) mainView
				.findViewById(R.id.b00v00ToText);
		toText.addTextChangedListener(toTextWatcher);
		// toText.setAdapter(poiListAdapter);
		toText.setOnItemClickListener(poisListItemClick);
		toText.setOnEditorActionListener(huanchengSearchAction);

		fromInputDelete = (ImageView) mainView
				.findViewById(R.id.b00v00FromDelete);
		fromInputDelete.setOnClickListener(fromInputDeleteClick);

		toInputDelete = (ImageView) mainView.findViewById(R.id.b00v00ToDelete);
		toInputDelete.setOnClickListener(toInputDeleteClick);

		huanchengSearch = (Button) mainView
				.findViewById(R.id.b00v00HuanchengSearch);
		huanchengSearch.setOnClickListener(huanchengSearchClick);

		infoView = (TextView) mainView.findViewById(R.id.b00v00InfoText);
		// 设置带图片的描述信息
		setInfoImg();
	}

	/**
	 * 设置带图片的描述信息
	 */
	private void setInfoImg() {
		String impFlag = "[smile]";
		String textString = context.getString(R.string.b00v00_switch_input_2,
				impFlag);

		// String textString = "11111";

		Drawable drawable = context.getResources().getDrawable(
				R.drawable.b00v00_hc_ms_icon);

		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());

		SpannableString spannable = new SpannableString(textString.toString());
		// 要让图片替代指定的文字就要用ImageSpan
		ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
		// 开始替换，注意第2和第3个参数表示从哪里开始替换到哪里替换结束（start和end）
		// 最后一个参数类似数学中的集合,[5,12)表示从5到12，包括5但不包括12
		spannable
				.setSpan(span, textString.indexOf("["),
						textString.indexOf("]") + 1,
						Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

		infoView.setText(spannable);
	}

	/**
	 * 兴趣点选择事件
	 */
	OnItemClickListener poisListItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			MKPoiInfo poiInfo = searchPois.get(arg2);

			// 开始位置
			if (FOCUS_FROM_KEY == focusKey) {

				fromText.setText(poiInfo.name);

				fromText.setSelection(poiInfo.name.length());

				startStation.getDataFromMKPoiInfo(poiInfo);
			}
			// 结束位置
			else if (FOCUS_TO_KEY == focusKey) {

				toText.setText(poiInfo.name);

				toText.setSelection(poiInfo.name.length());

				endStation.getDataFromMKPoiInfo(poiInfo);
			}
		}
	};

	/**
	 * 到达位置监听
	 */
	TextWatcher toTextWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

			// 到达位置得到焦点
			focusKey = FOCUS_TO_KEY;

			// 设置删除按钮的显隐性
			if (s.length() > 0 && toInputDelete.getVisibility() == View.GONE) {
				toInputDelete.setVisibility(View.VISIBLE);
			} else if (s.length() <= 0
					&& toInputDelete.getVisibility() == View.VISIBLE) {
				toInputDelete.setVisibility(View.GONE);
			}

			// 在城市内搜索
			searchInCity(s);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void afterTextChanged(Editable s) {
		}
	};

	/**
	 * 出发位置监听
	 */
	TextWatcher fromTextWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

			// 开始位置得到焦点
			focusKey = FOCUS_FROM_KEY;

			// 设置删除按钮的显隐性
			if (s.length() > 0 && fromInputDelete.getVisibility() == View.GONE) {
				fromInputDelete.setVisibility(View.VISIBLE);
			} else if (s.length() <= 0
					&& fromInputDelete.getVisibility() == View.VISIBLE) {
				fromInputDelete.setVisibility(View.GONE);
			}

			// 在城市内搜索
			searchInCity(s);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void afterTextChanged(Editable s) {
		}
	};

	/**
	 * 删除开始站点输入
	 */
	OnClickListener fromInputDeleteClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			fromText.setText("");
		}
	};

	/**
	 * 删除结束站点输入
	 */
	OnClickListener toInputDeleteClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			toText.setText("");
		}
	};

	/**
	 * 切换From，To
	 */
	OnClickListener switchToClick = new OnClickListener() {

		@Override
		public void onClick(View v) {

			// 切换存储对象
			Baidu stationTemp = startStation;

			startStation = endStation;
			endStation = stationTemp;

			// 切换From，To输入
			fromText.setText(startStation.getName());
			fromText.setSelection(startStation.getName().length());
			toText.setText(endStation.getName());
			toText.setSelection(endStation.getName().length());
		}
	};

	/**
	 * 开始搜索
	 */
	OnEditorActionListener huanchengSearchAction = new OnEditorActionListener() {
		@Override
		public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
			if (EditorInfo.IME_ACTION_SEARCH == arg1) {
				// 搜索
				huanchengSearch();
			}
			return true;
		}
	};

	/**
	 * 检索位置
	 */
	OnClickListener huanchengSearchClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 搜索
			huanchengSearch();
		}
	};

	/**
	 * 搜索
	 */
	private void huanchengSearch() {
		// 非空判断
		if (StringUtils.isBlank(startStation.getName())) {

			String startName = fromText.getText().toString().trim();

			if (StringUtils.isNotBlank(startName)) {
				startStation.setName(startName);
			} else {
				ShowMessageUtils.show(context, "请选择开始位置");
				return;
			}
		}
		if (StringUtils.isBlank(toText.getText().toString())
				|| StringUtils.isBlank(endStation.getName())) {

			String endName = toText.getText().toString().trim();

			if (StringUtils.isNotBlank(endName)) {
				endStation.setName(endName);
			} else {
				ShowMessageUtils.show(context, "请选择结束位置");
				return;
			}
		}

		// 组装数据
		Intent intent = new Intent(context, B00V05Activity.class);

		intent.putExtra(B00V05Activity.START_STATION_KEY, startStation);
		intent.putExtra(B00V05Activity.END_STATION_KEY, endStation);
		intent.putExtra(B00V05Activity.SEARCH_WAY_KEY, searchWay);

		// 跳转
		CommonUtil.startSubActivity(context, intent);
	}

	/**
	 * 在城市内搜索
	 * 
	 * @param searchKey
	 */
	private void searchInCity(CharSequence searchKey) {

		String search = searchKey.toString().trim();

		// 两个字母或一个汉字开始检索
		if (search.getBytes().length >= 2) {
			// 搜索兴趣点
			mMKSearch.poiSearchInCity(context.getString(R.string.common_city),
					searchKey.toString());
		}
	}

	/**
	 * 设置站点
	 * 
	 * @param zhandianText
	 * @param request_code
	 */
	public void setZhandianValue(Station station, int request_code) {

		// 出发站点
		if (B00V04Activity.START_SELECT_REQUEST_CODE == request_code) {
			startStation = station;
			// 设置开始站点位置
			fromText.setText(startStation.getName());

			// 设置光标
			fromText.setSelection(startStation.getName().length());
		}

		// 终点
		else if (B00V04Activity.END_SELECT_REQUEST_CODE == request_code) {
			endStation = station;
			// 设置开始站点位置
			toText.setText(endStation.getName());

			// 设置光标
			toText.setSelection(startStation.getName().length());
		}
	}

	/**
	 * 刷新兴趣点
	 */
	public void refreshPoiList(ArrayList<MKPoiInfo> searchPois) {

		this.searchPois = searchPois;

		// 清空数据源
		poiData.clear();

		// 设置数据源
		for (MKPoiInfo mkPoiInfo : searchPois) {
			Map<String, String> poiItem = new HashMap<String, String>();

			poiItem.put(from_key[0], mkPoiInfo.name);
			poiItem.put(from_key[1], mkPoiInfo.address);

			poiData.add(poiItem);
		}

		// 刷新兴趣点
		poiListAdapter.notifyDataSetChanged();

		// 设置当前输入站点为第0个站点
		if (searchPois.size() > 0) {

			MKPoiInfo firstMkPoiInfo = searchPois.get(0);

			// 出发站点
			if (fromText.hasFocus()) {
				startStation.getDataFromMKPoiInfo(firstMkPoiInfo);

				if (fromText.getAdapter() == null) {
					fromText.setAdapter(poiListAdapter);
				}

				if (!fromText.isShown()) {
					// 强制让下拉列表显示
					fromText.showDropDown();
				}
			}

			// 终点
			else if (toText.hasFocus()) {
				endStation.getDataFromMKPoiInfo(firstMkPoiInfo);

				if (toText.getAdapter() == null) {
					toText.setAdapter(poiListAdapter);
				}

				if (!toText.isShown()) {
					// 强制让下拉列表显示
					toText.showDropDown();
				}
			}
		}
	}

	/**
	 * 设置结束位置
	 * 
	 * @param baiduItem
	 */
	public void setEndPlace(Baidu baiduItem) {
		endStation.name = baiduItem.name;
		endStation.address = baiduItem.address;
		endStation.location_lat = baiduItem.location_lat;
		endStation.location_lng = baiduItem.location_lng;

		// 设置站点位置
		toText.setText(endStation.getName());
	}

	/**
	 * 搜索方式
	 * 
	 * @param searchWay
	 */
	public void setSearchWay(int searchWay) {
		this.searchWay = searchWay;
	}

	/**
	 * 返回View
	 */
	public View get() {
		return mainView;
	}
}

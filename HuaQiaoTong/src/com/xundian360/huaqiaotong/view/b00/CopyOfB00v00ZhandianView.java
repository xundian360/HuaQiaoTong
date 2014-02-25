package com.xundian360.huaqiaotong.view.b00;

import android.content.Context;

/**
 * 站点视图
 * 
 * @author tengteng
 * @time 下午1:29:55
 * @version 1.0
 */
public class CopyOfB00v00ZhandianView {

	// View mainView;
	//
	// Context context;
	//
	// // 站点历史记录按钮
	// ImageButton zhanDianHistoryBtn;
	// // 检索的站点
	// AutoCompleteTextView zhanDianText;
	// // 语音输入站点
	// ImageButton zhanDianYuyingBtn;
	// // 搜索站点按钮
	// ImageButton zhanDianSearchBtn;
	// // // 可选择的站点
	// // ListView zhandianList;
	//
	// // 站点Adapter
	// SearchAdapter<String> zhandianAdapter;
	// // 数据源
	// List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
	//
	// // 历史站点选择Dialog
	// B00SelectZhandianHistoryDialog zhandianSelectDialog;
	//
	// // 所有站点信息
	// List<Station> stations;
	// String[] stationNames;
	//
	// // 所有站点信息
	// List<Station> shoucangStations;
	//
	// // 所有显示的线路信息
	// List<Station> showStations = new ArrayList<Station>();
	//
	// // 站点数据库操作类
	// StationOperatingHelper dbHelper;
	//
	// // 当前检索的站点信息
	// Station searchStation = null;

	public CopyOfB00v00ZhandianView(Context context) {
		//
		// this.context = context;
		//
		// // 初始化数据
		// initData();
		//
		// // 初始化视图
		// initView();
	}

	// /**
	// * 初始化数据
	// */
	// private void initData() {
	// // 初始化数据
	// dbHelper = new StationOperatingHelper(context);
	// // 查询所有站点信息
	// stations = dbHelper.getStations();
	//
	// Log.d("debug", "stations > " + stations);
	//
	// // 设置数据源
	// setAdapterData(null);
	//
	// // 设置Adapter
	// // zhandianAdapter = new SearchAdapter(context, data,
	// R.layout.b00v00_zhandian_item,
	// // B00v00ZhandianAdapter.from, B00v00ZhandianAdapter.to);
	//
	// zhandianAdapter = new SearchAdapter<String>(context,
	// android.R.layout.simple_dropdown_item_1line,
	// stationNames,SearchAdapter.ALL);//速度优先
	// }
	//
	// /**
	// * 设置数据源值
	// */
	// private void setAdapterData(String referText) {
	//
	// // 清空数据源
	// data.clear();
	// showStations.clear();
	//
	// stationNames = new String[stations.size()];
	//
	// for (int i = 0; i < stations.size(); i++) {
	//
	// Station station = stations.get(i);
	//
	// // 设置包含特定字符的信息
	// if(referText == null || station.getName().contains(referText)) {
	//
	// Map<String, Object> dataItem = new HashMap<String, Object>();
	//
	// dataItem.put(B00v00ZhandianAdapter.from[0], station.getName());
	// dataItem.put(B00v00ZhandianAdapter.from[1], station.getDirection() +
	// "站台");
	//
	// stationNames[i] = station.getName() + "  " + station.getDirection() +
	// "站台";
	//
	// data.add(dataItem);
	// showStations.add(station);
	// }
	// }
	// //
	// // // 重新设置数据源
	// // for (Station station : stations) {
	// //
	// //
	// // // 设置包含特定字符的信息
	// // if(referText == null || station.getName().contains(referText)) {
	// //
	// // Map<String, Object> dataItem = new HashMap<String, Object>();
	// //
	// // dataItem.put(B00v00ZhandianAdapter.from[0], station.getName());
	// // dataItem.put(B00v00ZhandianAdapter.from[1], station.getDirection() +
	// "站台");
	// //
	// //
	// //
	// // data.add(dataItem);
	// // showStations.add(station);
	// // }
	// // }
	//
	// Log.d("debug", "data > " + data);
	// }
	//
	// /**
	// * 初始化视图
	// */
	// private void initView() {
	// // Main组件
	// mainView =
	// ((Activity)context).getLayoutInflater().inflate(R.layout.b00v00_zhangdian,
	// null);
	//
	// // 其他组件
	// zhanDianHistoryBtn = (ImageButton)
	// mainView.findViewById(R.id.b00v00ZhanDianHistoryBtn);
	// zhanDianHistoryBtn.setOnClickListener(zhanDianHistoryClick);
	//
	// zhanDianText = (AutoCompleteTextView)
	// mainView.findViewById(R.id.b00v00ZhanDianText);
	// // zhanDianText.addTextChangedListener(zhanDianTextChange);
	// zhanDianText.setAdapter(zhandianAdapter);
	// zhanDianText.setOnItemClickListener(zhandianItemClick);
	//
	// zhanDianYuyingBtn = (ImageButton)
	// mainView.findViewById(R.id.b00v00ZhanDianYuyingBtn);
	// zhanDianYuyingBtn.setOnClickListener(zhanDianYuyingClick);
	//
	// zhanDianSearchBtn = (ImageButton)
	// mainView.findViewById(R.id.b00v00ZhanDianSearchBtn);
	// zhanDianSearchBtn.setOnClickListener(zhanDianSearchClick);
	//
	// // zhandianList = (ListView)
	// mainView.findViewById(R.id.b00v00ZhandianList);
	// // zhandianList.setAdapter(zhandianAdapter);
	// // zhandianList.setOnItemClickListener(zhandianItemClick);
	// }
	//
	// /**
	// * 站点输入监听
	// */
	// TextWatcher zhanDianTextChange = new TextWatcher() {
	//
	// @Override
	// public void onTextChanged(CharSequence s, int start, int before, int
	// count) {
	//
	// // 更新站点提示信息
	// String zhanDianInText = zhanDianText.getText().toString();
	//
	// zhanDianInText = StringUtils.isBlank(zhanDianInText) ? null :
	// zhanDianInText;
	//
	// setAdapterData(zhanDianInText);
	// zhandianAdapter.notifyDataSetChanged();
	// }
	//
	// @Override
	// public void beforeTextChanged(CharSequence s, int start, int count,
	// int after) {}
	//
	// @Override
	// public void afterTextChanged(Editable s) {}
	// };
	//
	//
	//
	// /**
	// * 站点History按钮
	// */
	// OnClickListener zhanDianHistoryClick = new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	//
	// // 取得最新的收藏信息
	// shoucangStations = dbHelper.getShoucangStations();
	//
	// // 非空
	// if(shoucangStations != null && !shoucangStations.isEmpty()) {
	//
	// // 重建Dialog
	// zhandianSelectDialog = new B00SelectZhandianHistoryDialog(context, call,
	// shoucangStations);
	//
	// // 显示Dialog
	// zhandianSelectDialog.show();
	// } else {
	// // 当前没有收藏线站点信息
	// ShowMessageUtils.show(context, R.string.msg_no_shoucang_zhandian);
	// }
	// }
	// };
	//
	// /**
	// * 语音检索站点按钮
	// */
	// OnClickListener zhanDianYuyingClick = new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	//
	// }
	// };
	//
	// /**
	// * 站点检索按钮
	// */
	// OnClickListener zhanDianSearchClick = new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	//
	// // 检查网络
	// if(!CommonUtil.isNetworkAvailable(context)) {
	//
	// ShowMessageUtils.show(context, R.string.message_error_network);
	// return;
	// }
	// // 设置检索数据
	// String searchValue = zhanDianText.getText().toString();
	//
	// // TODO
	// // 设置输入的站点信息NetListListL
	//
	// if(searchStation != null) {
	//
	// Intent in = new Intent(context, B00V03Activity.class);
	// in.putExtra(B00V03Activity.STATION_KEY, searchStation);
	//
	// CommonUtil.startActivityForResult(context, in, 100);
	//
	// // searchStation.getStationId()
	// //
	// // ShowMessageUtils.show(context, searchValue);
	// } else {
	// ShowMessageUtils.show(context, "请输入检索站点");
	// }
	// }
	// };
	//
	// /**
	// * 站点项目点击事件
	// */
	// OnItemClickListener zhandianItemClick = new OnItemClickListener() {
	//
	// @Override
	// public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	// long arg3) {
	//
	// // 设置检索站点对象
	// searchStation = showStations.get(arg2);
	//
	// // // 设置检索地点
	// // zhanDianText.setText(searchStation.getName());
	// //
	// // // 设置光标
	// // zhanDianText.setSelection(searchStation.getName().length());
	// }
	// };
	//
	// /**
	// * 历史站点选择回调
	// */
	// OnZhandianHistorySelect call = new OnZhandianHistorySelect() {
	//
	// @Override
	// public void onZhandianSelect(Station station) {
	//
	// // 取消Dialog显示
	// zhandianSelectDialog.dismiss();
	//
	// // 设置检索站点对象
	// searchStation = station;
	//
	// // 设置检索地点Text
	// zhanDianText.setText(station.getName());
	// }
	// };
	//
	// /**
	// * 返回View
	// */
	// public View get() {
	// return mainView;
	// }
}

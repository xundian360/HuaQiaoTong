package com.xundian360.huaqiaotong.view.b00;

import android.content.Context;

/**
 * 线路视图
 * 
 * @author tengteng
 * @time 下午1:29:55
 * @version 1.0
 */
public class CopyOfB00v00XianluView {

	// View mainView;
	//
	// Context context;
	//
	// // 线路历史记录按钮
	// ImageButton xianluHistoryBtn;
	// // 检索的线路
	// AutoCompleteTextView xianluText;
	// // 语音输入线路
	// ImageButton xianluYuyingBtn;
	// // 搜索线路按钮
	// ImageButton xianluSearchBtn;
	// // // 可选择的线路
	// // ListView xianluList;
	//
	// // 线路Adapter
	// SearchAdapter<String> xianluAdapter;
	// // 数据源
	// List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
	//
	// // 历史线路选择Dialog
	// B00SelectXianluHistoryDialog xianluSelectDialog;
	//
	// // 所有线路信息
	// List<Bus> buses;
	// String[] buseNames;
	//
	// // 所有收藏的线路信息
	// List<Bus> shoucangBuses;
	//
	// // 所有显示的线路信息
	// List<Bus> showBuses = new ArrayList<Bus>();
	//
	// // 线路数据库操作类
	// BusOperatingHelper dbHelper;
	//
	// // 当前检索的线路信息
	// Bus searchBus = null;

	public CopyOfB00v00XianluView(Context context) {

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
	//
	// // 初始化数据
	// dbHelper = new BusOperatingHelper(context);
	// // 查询所有线路信息
	// buses = dbHelper.getAllBuses();
	//
	// Log.d("debug", "stations > " + buses);
	//
	// // 设置数据源
	// setAdapterData(null);
	//
	// // 设置Adapter
	// xianluAdapter = new SearchAdapter<String> (context,
	// android.R.layout.simple_dropdown_item_1line,
	// buseNames,SearchAdapter.ALL);//速度优先
	// }
	//
	// /**
	// * 设置数据源值
	// */
	// private void setAdapterData(String referText) {
	//
	// // 清空数据源
	// data.clear();
	// showBuses.clear();
	//
	// buseNames = new String[buses.size()];
	//
	// for (int i = 0; i < buses.size(); i++) {
	//
	// Bus bus = buses.get(i);
	//
	// // 设置包含特定字符的信息
	// if(referText == null || bus.getRouteName().contains(referText)) {
	//
	// Map<String, Object> dataItem = new HashMap<String, Object>();
	//
	// dataItem.put(B00v00XianluAdapter.from[0], bus.getRouteKey());
	//
	// dataItem.put(B00v00XianluAdapter.from[1], bus.getDirection());
	//
	// buseNames[i] = bus.getRouteKey() + " " + bus.getDirection();
	//
	// data.add(dataItem);
	// showBuses.add(bus);
	// }
	//
	// // // 重新设置数据源
	// // for (Bus bus : buses) {
	// //
	// // // 设置包含特定字符的信息
	// // if(referText == null || bus.getRouteName().contains(referText)) {
	// //
	// // Map<String, Object> dataItem = new HashMap<String, Object>();
	// //
	// // dataItem.put(B00v00XianluAdapter.from[0], bus.getRouteKey());
	// //
	// // dataItem.put(B00v00XianluAdapter.from[1], bus.getDirection());
	// //
	// //// dataItem.put(B00v00XianluAdapter.from[2],
	// R.drawable.b00v02_no_shuocang_btn);
	// //
	// // data.add(dataItem);
	// // showBuses.add(bus);
	// // }
	// //
	// // if(showBuses != null && showBuses.size() > 0) {
	// // // 设置当前站点为第一个
	// // searchBus = showBuses.get(0);
	// // }
	//
	// }
	//
	// Log.d("debug", "data > " + data);
	// }
	//
	// /**
	// * 初始化视图
	// */
	// private void initView() {
	//
	// mainView =
	// ((Activity)context).getLayoutInflater().inflate(R.layout.b00v00_xianlu,
	// null);
	//
	// // 其他组件
	// xianluHistoryBtn = (ImageButton)
	// mainView.findViewById(R.id.b00v00XianluHistoryBtn);
	// xianluHistoryBtn.setOnClickListener(xianluHistoryClick);
	//
	// xianluText = (AutoCompleteTextView)
	// mainView.findViewById(R.id.b00v00XianluText);
	// // xianluText.addTextChangedListener(xianluTextChange);
	// xianluText.setAdapter(xianluAdapter);
	// xianluText.setOnItemClickListener(xianluItemClick);
	//
	// xianluYuyingBtn = (ImageButton)
	// mainView.findViewById(R.id.b00v00XianluYuyingBtn);
	// xianluYuyingBtn.setOnClickListener(xianluYuyingClick);
	//
	// xianluSearchBtn = (ImageButton)
	// mainView.findViewById(R.id.b00v00XianluSearchBtn);
	// xianluSearchBtn.setOnClickListener(xianluSearchClick);
	// //
	// // xianluList = (ListView) mainView.findViewById(R.id.b00v00XianluList);
	// // xianluList.setAdapter(xianluAdapter);
	// // xianluList.setOnItemClickListener(xianluItemClick);
	// }
	//
	// /**
	// * 线路输入监听
	// */
	// TextWatcher xianluTextChange = new TextWatcher() {
	//
	// @Override
	// public void onTextChanged(CharSequence s, int start, int before, int
	// count) {
	//
	// // 更新站点提示信息
	// String xianluInText = xianluText.getText().toString();
	//
	// xianluInText = StringUtils.isBlank(xianluInText) ? null : xianluInText;
	//
	// setAdapterData(xianluInText);
	// xianluAdapter.notifyDataSetChanged();
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
	// /**
	// * 线路History按钮
	// */
	// OnClickListener xianluHistoryClick = new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	//
	// // 取得最新的收藏信息
	// shoucangBuses = dbHelper.getAllShoucangBuses();
	//
	// // 非空
	// if(shoucangBuses != null && !shoucangBuses.isEmpty()) {
	//
	// // 重建Dialog
	// xianluSelectDialog = new B00SelectXianluHistoryDialog(context, call,
	// shoucangBuses);
	//
	// // 显示Dialog
	// xianluSelectDialog.show();
	// } else {
	// // 当前没有收藏线的路信息
	// ShowMessageUtils.show(context, R.string.msg_no_shoucang_xianlu);
	// }
	// }
	// };
	//
	// /**
	// * 语音检索线路按钮
	// */
	// OnClickListener xianluYuyingClick = new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	//
	// }
	// };
	//
	// /**
	// * 线路检索按钮
	// */
	// OnClickListener xianluSearchClick = new OnClickListener() {
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
	// String searchValue = xianluText.getText().toString();
	//
	// // TODO
	// // 设置输入的线路信息NetListListL
	//
	// if(searchBus != null) {
	//
	// Intent in = new Intent(context, B00V02Activity.class);
	// in.putExtra(B00V02Activity.BUS_KEY, searchBus);
	//
	// CommonUtil.startActivityForResult(context, in, 100);
	//
	// // searchStation.getStationId()
	// //
	// // ShowMessageUtils.show(context, searchValue);
	// } else {
	// ShowMessageUtils.show(context, "请输入检索线路");
	// }
	// }
	// };
	//
	// /**
	// * 线路项目点击事件
	// */
	// OnItemClickListener xianluItemClick = new OnItemClickListener() {
	//
	// @Override
	// public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	// long arg3) {
	//
	// // 设置检索线路对象
	// // searchBus = buses.get(arg2);
	//
	// searchBus = showBuses.get(arg2);
	// //
	// // String busNum = searchBus.getRouteKey();
	// //
	// // // 设置检索地点
	// // xianluText.setText(busNum);
	// //
	// // xianluText.setSelection(busNum.length());
	// }
	// };
	//
	// /**
	// * 历史线路选择回调
	// */
	// OnXianluHistorySelect call = new OnXianluHistorySelect() {
	//
	// @Override
	// public void onXianluSelect(Bus bus) {
	//
	// // 取消Dialog显示
	// xianluSelectDialog.dismiss();
	//
	// // 设置检索线路对象
	// searchBus = bus;
	//
	// // 设置检索地点Text
	// xianluText.setText(bus.getRouteName());
	// }
	// };
	//
	// /**
	// * 返回View
	// */
	// public View get() {
	//
	// return mainView;
	// }
}

/**
 * 
 */
package com.xundian360.huaqiaotong.view.com;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.cloud.CloudPoiInfo;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;

/**
 * 地图覆层
 * 
 * @author TengTeng
 * @date 2013-10-22
 * @version 1.0
 */
public class CommonOverlayView {

	View mainView;

	Context context;

	// 标题
	TextView overlayTittle;
	// 明细
	TextView overlayDetail;

	// 到这里按钮
	Button overlayToHere;
	// 打电话按钮
	Button overlayCall;

	// 百度对象
	Baidu baiduObj = null;

	// LBP云对象
	CloudPoiInfo cloudItem = null;

	public CommonOverlayView(Context context, Baidu baiduObj) {

		this.context = context;
		this.baiduObj = baiduObj;

		// 初始化数据
		initData();

		// 初始化视图
		initView();
	}

	public CommonOverlayView(Context context, CloudPoiInfo cloudItem) {

		this.context = context;
		this.cloudItem = cloudItem;

		// 初始化数据
		initData();

		// 初始化视图
		initView();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
	}

	/**
	 * 初始化视图
	 */
	private void initView() {
		mainView = ((Activity) context).getLayoutInflater().inflate(
				R.layout.common_overlay_layout, null);

		overlayTittle = (TextView) mainView
				.findViewById(R.id.commonOverlayTittle);

		overlayDetail = (TextView) mainView
				.findViewById(R.id.commonOverlayDetail);

		// 判断什么对象
		if (baiduObj != null) {
			overlayTittle.setText(baiduObj.getName());
			overlayDetail.setText(baiduObj.getAddress());
		} else {
			overlayTittle.setText(cloudItem.title);
			overlayDetail.setText(cloudItem.address);
		}

		overlayToHere = (Button) mainView
				.findViewById(R.id.commonOverlayToHere);
		overlayToHere.setOnClickListener(overlayToHereClick);

		overlayCall = (Button) mainView.findViewById(R.id.commonOverlayCall);
		overlayCall.setOnClickListener(overlayCallClick);
	}

	/**
	 * 返回View
	 */
	public View get() {
		return mainView;
	}

	/**
	 * 规划线路事件
	 */
	OnClickListener overlayToHereClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			ShowMessageUtils.show(context, "规划线路事件");
		}
	};

	/**
	 * 打电话事件
	 */
	OnClickListener overlayCallClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			ShowMessageUtils.show(context, "打电话事件");
		}
	};

}

/**
 * 
 */
package com.xundian360.huaqiaotong.view.b01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b01.B01V01Activity;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.view.com.BottomDialog;

/**
 * 地图覆层
 * 
 * @author Administrator
 * @date 2013年12月6日
 * @version 1.0
 */
public class B01v04ItemPoiDialog extends BottomDialog {

	View mainView;
	Context context;

	TextView poiTittle;
	LinearLayout poiDetailBtn;
	LinearLayout toHereBtn;
	LinearLayout callBtn;

	// 百度对象
	Baidu bickObj = null;

	public B01v04ItemPoiDialog(Context context, Baidu baiduObj) {

		super(context);

		this.context = context;
		this.bickObj = baiduObj;

		// 初始化视图
		initView();

		dialog.setContentView(mainView, new ViewGroup.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	/**
	 * 初始化视图
	 */
	private void initView() {

		mainView = LayoutInflater.from(context).inflate(R.layout.b01v04_bottom,
				null);

		poiTittle = (TextView) mainView.findViewById(R.id.b01v04PoiTittle);
		poiTittle.setText(bickObj.getName());

		poiDetailBtn = (LinearLayout) mainView
				.findViewById(R.id.b01v04PoiDetailBtn);
		poiDetailBtn.setOnClickListener(poiDetailBtnClick);

		toHereBtn = (LinearLayout) mainView.findViewById(R.id.b01v04PoiToHere);
		toHereBtn.setOnClickListener(toHereBtnClick);

		callBtn = (LinearLayout) mainView.findViewById(R.id.b01v04PoiCall);
		callBtn.setOnClickListener(callBtnClick);
	}

	/**
	 * 详情
	 */
	OnClickListener poiDetailBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {

			// 详情页面迁移
			CommonUtil.startActivityForResult(context, B01V01Activity.class,
					B01V01Activity.KTV_KEY, bickObj, 1000);
		}
	};

	/**
	 * 导航
	 */
	OnClickListener toHereBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			ShowMessageUtils.show(context, "导航");
		}
	};

	/**
	 * 打电话
	 */
	OnClickListener callBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			ShowMessageUtils.show(context, "打电话");
		}
	};

}

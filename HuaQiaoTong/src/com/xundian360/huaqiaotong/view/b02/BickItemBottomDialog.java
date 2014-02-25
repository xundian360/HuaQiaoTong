/**
 * 
 */
package com.xundian360.huaqiaotong.view.b02;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleMapSearchActivity;
import com.xundian360.huaqiaotong.modle.b02.Bick;

/**
 * 自行车地图覆层
 * 
 * @author TengTeng
 * @date 2013-10-22
 * @version 1.0
 */
public class BickItemBottomDialog {

	View mainView;

	Context context;

	// 标题
	TextView bickName;
	// 所有数量
	TextView allNum;
	// 剩余数量
	TextView lastNum;

	// 导航
	Button overlayToHere;
	//
	// private Dialog dialog;

	// 百度对象
	Bick bickObj = null;

	public BickItemBottomDialog(Context context, Bick baiduObj) {

		// dialog = new Dialog(context, R.style.B02v02DialogTheme);

		this.context = context;
		this.bickObj = baiduObj;

		// dialog.setCancelable(true);

		// 初始化视图
		initView();
		//
		// dialog.setContentView(mainView,
		// new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT));

	}

	/**
	 * 初始化视图
	 */
	private void initView() {
		mainView = ((Activity) context).getLayoutInflater().inflate(
				R.layout.b02v02_bottom, null);

		bickName = (TextView) mainView
				.findViewById(R.id.b02v02BottomBickAddress);
		bickName.setText(bickObj.getName());

		allNum = (TextView) mainView.findViewById(R.id.b02v02BottomBickAllNum);
		allNum.setText(bickObj.getAllNum() + "");

		lastNum = (TextView) mainView
				.findViewById(R.id.b02v02BottomBickLastNum);
		lastNum.setText(bickObj.getLastNum() + "");

		overlayToHere = (Button) mainView
				.findViewById(R.id.b02v02BottomPlanBtn);
		overlayToHere.setOnClickListener(overlayToHereClick);
	}

	//
	// public void show() {
	// if(!dialog.isShowing()) {
	// dialog.show();
	// }
	// }

	/**
	 * 规划线路事件
	 */
	OnClickListener overlayToHereClick = new OnClickListener() {

		@Override
		public void onClick(View v) {

			// 规划路线
			((ComNoTittleMapSearchActivity) context).planDrivingLine(bickObj);
			//
			// // 隐藏Dialog
			// dialog.dismiss();
		}
	};

	public View get() {
		return mainView;
	}
}

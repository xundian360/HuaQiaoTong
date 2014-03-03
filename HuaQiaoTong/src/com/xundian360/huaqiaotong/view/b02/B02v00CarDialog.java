/**
 * 
 */
package com.xundian360.huaqiaotong.view.b02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b02.B02V00Activity;
import com.xundian360.huaqiaotong.adapter.b02.B02V00TexiAdapter;
import com.xundian360.huaqiaotong.modle.b02.Texi;

/**
 * 导航视图
 * 
 * @author Administrator
 * @date 2013年12月5日
 * @version 1.0
 */
public class B02v00CarDialog {

	Context context;
	
	public Dialog dialog;

	View mainView;

	// 标题
	TextView tittleView;
	// 取消按钮
	TextView cancelBtn;
	// 出租车列表
	ListView carList;

	// 设置数据源
	B02V00TexiAdapter taxiAdapter;

	List<Map<String, String>> data = new ArrayList<Map<String, String>>();

	// 标题
	String tittle;
	// 出租车列表
	List<Texi> taxis;

	// 导航展开标志
	public boolean isExpansion = false;

	public B02v00CarDialog(Context context, String tittle, List<Texi> taxis) {
		this.context = context;
		this.tittle = tittle;
		this.taxis = taxis;

		// 初始化数据
		initData();

		// 初始化视图
		initView();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		
		dialog = new Dialog(context, R.style.B02v00DialogTheme);

		// 设置数据源
		setAdapterDate();

		taxiAdapter = new B02V00TexiAdapter(context, data,
				R.layout.b02v00_car_item, B02V00TexiAdapter.from,
				B02V00TexiAdapter.to, taxis);

	}

	/**
	 * 初始化视图
	 */
	private void initView() {

		mainView = LayoutInflater.from(context).inflate(
				R.layout.b02v00_car_layout, null);

		tittleView = (TextView) mainView.findViewById(R.id.b02v00CarTittle);
		tittleView.setText(tittle);

		cancelBtn = (TextView) mainView.findViewById(R.id.b02v00CarCancelBtn);
		cancelBtn.setOnClickListener(cancelBtnClick);

		carList = (ListView) mainView.findViewById(R.id.b02v00CarList);
		carList.setAdapter(taxiAdapter);
		
		dialog.setContentView(mainView);
	}

	/**
	 * 设置数据源
	 */
	private void setAdapterDate() {

		if (taxis != null && taxis.size() > 0) {

			for (Texi taxi : taxis) {

				Map<String, String> item = new HashMap<String, String>();

				item.put(B02V00TexiAdapter.from[0], taxi.getName());
				item.put(B02V00TexiAdapter.from[1], "营业：" + taxi.getTime());

				data.add(item);
			}
		}
	}

	/**
	 * 取消按钮
	 */
	OnClickListener cancelBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {

			// 取消车辆显示
			dismiss();
		}
	};

	public void show() {
		if(!dialog.isShowing()) {
			dialog.show();
		}
	}
	
	public void dismiss() {
		if(dialog.isShowing()) {
			dialog.dismiss();
		}
	}
}

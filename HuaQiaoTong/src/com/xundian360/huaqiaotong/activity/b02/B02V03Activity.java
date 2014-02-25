/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b02;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.adapter.b02.B02V02BickAdapter;
import com.xundian360.huaqiaotong.modle.b02.Bick;
import com.xundian360.huaqiaotong.util.StringUtils;

/**
 * 自行车导航
 * 
 * @author LiZhenteng
 * @date 2013年10月14日
 * @version 1.0
 */
public class B02V03Activity extends ComNoTittleActivity {

	public static final String B02V03ACTIVITY_BICKLIST = "b02v03activity_bicklist";

	public static final String B02V03ACTIVITY_MY_LAT = "b02v03activity_my_lat";

	public static final String B02V03ACTIVITY_MY_LON = "b02v03activity_my_lon";

	// 返回按钮
	ImageButton retBtn;

	// 去Map
	TextView toMapBtn;

	// 自行车列表
	ArrayList<Bick> bickListData;

	// 自行车列表
	ListView bickList;
	// 数据源
	B02V02BickAdapter bickAdapter;
	List<Map<String, Object>> bickData = new ArrayList<Map<String, Object>>();

	// 我的位置坐标
	double my_lat = 0;
	// 我的位置坐标
	double my_lon = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.b02v03);

		// 初始化数据
		initData();

		// 初始化组件
		initModule();
	}

	/**
	 * 初始化数据
	 */
	@SuppressWarnings("unchecked")
	private void initData() {

		bickListData = (ArrayList<Bick>) getIntent().getSerializableExtra(
				B02V03ACTIVITY_BICKLIST);

		my_lat = (Double) getIntent().getDoubleExtra(B02V03ACTIVITY_MY_LAT, 0);
		my_lon = (Double) getIntent().getDoubleExtra(B02V03ACTIVITY_MY_LON, 0);

		// 排序
		sortAdapterData();

		// 设值数据源数据
		setAdapterData();

		// 初始化数据源
		bickAdapter = new B02V02BickAdapter(this, bickData,
				R.layout.b02v02_item, B02V02BickAdapter.from,
				B02V02BickAdapter.to, bickListData);
	}

	/**
	 * 初始化组件
	 */
	private void initModule() {
		retBtn = (ImageButton) findViewById(R.id.b02v03RetBtn);
		retBtn.setOnClickListener(retBtnClick);

		toMapBtn = (TextView) findViewById(R.id.b02v03ToMap);
		toMapBtn.setOnClickListener(toMapBtnClick);

		bickList = (ListView) findViewById(R.id.b02v03BickList);
		bickList.setAdapter(bickAdapter);
	}

	/**
	 * 排序
	 */
	@SuppressWarnings("unchecked")
	private void sortAdapterData() {

		// 克隆一个当前对象
		bickListData = (ArrayList<Bick>) bickListData.clone();

		// 计算每个自行车点与当前位置的距离
		for (Bick bickItem : bickListData) {
			bickItem.setRelaDistance(my_lat, my_lon);
		}

		// 排序
		Collections.sort(bickListData);
	}

	/**
	 * 设值数据源数据
	 */
	private void setAdapterData() {

		// 单个自行车数据
		for (Serializable bickItem : bickListData) {

			Bick bick = (Bick) bickItem;

			Map<String, Object> bickItemMap = new HashMap<String, Object>();

			bickItemMap.put(B02V02BickAdapter.from[0],
					R.drawable.b02v02_have_bick_img);
			bickItemMap.put(B02V02BickAdapter.from[1], bick.name);
			bickItemMap.put(B02V02BickAdapter.from[2], bick.getAllNum() + "");
			bickItemMap.put(B02V02BickAdapter.from[3], bick.getLastNum() + "");
			bickItemMap.put(B02V02BickAdapter.from[4], StringUtils
					.formatDoubleToString(bick.getRelaDistance() / 1000));

			// 添加到数据源
			bickData.add(bickItemMap);
		}
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
	 * Map或列表事件
	 */
	OnClickListener toMapBtnClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	};
}

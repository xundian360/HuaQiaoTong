/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b01;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleBMapManActivity;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.view.b00.B00v00HuanchengView;

/**
 * KTV到达换乘
 * @author  Administrator
 * @date      2013年10月12日
 * @version 1.0
 */
public class CopyOfB01V02Activity extends ComNoTittleBMapManActivity {
	
	public static final String BAIDU_DATA_KEY = "baidu_data_key";
	
	public static final String FROM_OR_TO_KEY = "from_or_to_key";
	
	// 开始位置标志
	public static final String FROM_KEY = "from_key";
	// to位置标志
	public static final String TO_KEY = "to_key";
	
	// 返回按钮
	ImageButton retBtn;
	
	// 到达方式：公交
	ImageButton hcBus;
	// 到达方式：小汽车
	ImageButton hcCar;
	// 到达方式：自行车
	ImageButton hcBick;
	
	// 换乘View容器
	LinearLayout huanchengViewContainer;
	
	// 换乘View
	B00v00HuanchengView huanchengView;
	
	// 到达的对象
	Baidu baiduObj;
	
	// 检索开始位置或结束位置
	String fromOrToKey;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b01v02);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		
		// 开始位置或到达位置
		fromOrToKey = getIntent().getStringExtra(FROM_OR_TO_KEY);
		
		baiduObj = (Baidu) getIntent().getSerializableExtra(BAIDU_DATA_KEY);
		
		huanchengView = new B00v00HuanchengView(this, false);
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		retBtn = (ImageButton) findViewById(R.id.commHcRetBtn);
		retBtn.setOnClickListener(retBtnClick);
		
		hcBus = (ImageButton) findViewById(R.id.commHcBusBtn);
		hcBus.setOnClickListener(hcBusClick);
		
		hcCar = (ImageButton) findViewById(R.id.commHcCarBtn);
		hcCar.setOnClickListener(hcCarClick);
		
		hcBick = (ImageButton) findViewById(R.id.commHcBickBtn);
		hcBick.setOnClickListener(hcBickClick);
		
		huanchengViewContainer = (LinearLayout) findViewById(R.id.b01v02DetailLayout);
		huanchengViewContainer.addView(huanchengView.get());
		
		// 设值我的检索的位置
		setSearchLocation();

	}
	
	/**
	 * 返回
	 */
	OnClickListener retBtnClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			onBackPressed();
		}
	};
	
	/**
	 * 公交换乘
	 */
	OnClickListener hcBusClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			huanchengView.setSearchWay(B00v00HuanchengView.TRANSITSEARCH_KEY);
		}
	};
	
	/**
	 * 小汽车换乘
	 */
	OnClickListener hcCarClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			huanchengView.setSearchWay(B00v00HuanchengView.DRIVINGSEARCH_KEY);
		}
	};
	
	/**
	 * 自行车换乘
	 */
	OnClickListener hcBickClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			huanchengView.setSearchWay(B00v00HuanchengView.WALKINGSEARCH_KEY);
		}
	};
	
	/**
	 * 设值检索的位置
	 */
	private void setSearchLocation() {
//		if(FROM_KEY.equals(fromOrToKey)) {
//			fromText.setText(baiduObj.getName());
//			fromText.setEnabled(false);
//			fromText.setTextColor(Color.BLACK);
//			toText.setText(R.string.common_text_location);
//			toText.setTextColor(Color.BLUE);
//		} else {
//			toText.setText(baiduObj.getName());
//			toText.setEnabled(false);
//			toText.setTextColor(Color.BLACK);
//			fromText.setText(R.string.common_text_location);
//			fromText.setTextColor(Color.BLUE);
//		}
		
		if(FROM_KEY.equals(fromOrToKey)) {
			
			huanchengView.fromText.setText(baiduObj.getName());
			huanchengView.startStation = baiduObj;
			huanchengView.fromText.setSelection(baiduObj.getName().length());
		} else {
			
			huanchengView.toText.setText(baiduObj.getName());
			huanchengView.endStation = baiduObj;
			huanchengView.toText.setSelection(baiduObj.getName().length());
		}
	}
}

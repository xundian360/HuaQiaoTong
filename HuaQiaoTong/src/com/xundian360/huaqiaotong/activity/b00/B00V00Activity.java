/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b00;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleBMapManActivity;
import com.xundian360.huaqiaotong.modle.b00.Station;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.view.b00.B00v00HuanchengView;
import com.xundian360.huaqiaotong.view.b00.B00v00XianluView;
import com.xundian360.huaqiaotong.view.b00.B00v00ZhandianView;
import com.xundian360.huaqiaotong.view.com.ResizeLayout;

/**
 * 公交首页
 * @author   tengteng
 * @time     上午12:21:25
 * @version  1.0
 */
public class B00V00Activity extends ComNoTittleBMapManActivity {
	
	private static final int START_RESULT_CODE = 100;
	
	// 站点查询
	private static final int ZHANDIAN_SELECT = 0;
	// 站点查询
	private static final int XIANLU_SELECT = 1;
	// 站点查询
	private static final int HUANCHENG_SELECT = 2;
	
	// 当前选择的项目
	private int currentSelect = XIANLU_SELECT;
	
	ResizeLayout rootLayout;
	
	// 返回按钮
	ImageButton retBtn;
	
	TextView tittle;
	
	// 公交站点
	Button zhanDianBtn;
	// 公交线路
	Button xianluBtn;
	// 公交换乘
	Button huanChengBtn;
	
	// 页面Layout
	RelativeLayout mainLayout;
	
	LinearLayout buttomLayout;
	
	// 站点视图
	B00v00ZhandianView zhandianView;
	// 线路视图
	B00v00XianluView xianluView;
	// 换乘视图
	B00v00HuanchengView huanchengView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b00v00);
		
		// 初始化数据
		initData();
		
		// 初始化组建
		initModule();
	}
	
	/**
	 * 初始化数据
	 */
	private void initData(){
		
		xianluView = new B00v00XianluView(this);
		zhandianView = new B00v00ZhandianView(this);
		huanchengView = new B00v00HuanchengView(this);
	}
	
	/**
	 * 初始化组建
	 */
	private void initModule() {
		
		rootLayout = (ResizeLayout) findViewById(R.id.b00v00RootLayout);
		
		retBtn = (ImageButton) findViewById(R.id.b00v00RetBtn);
		retBtn.setOnClickListener(retBtnClick);
		
		tittle = (TextView) findViewById(R.id.b00v00TittleText);
		
		zhanDianBtn = (Button) findViewById(R.id.b00v00ZhanDianBtn);
		zhanDianBtn.setOnClickListener(zhanDianBtnClick);
		
		xianluBtn = (Button) findViewById(R.id.b00v00XianluBtn);
		xianluBtn.setOnClickListener(xianluBtnClick);
		
		huanChengBtn = (Button) findViewById(R.id.b00v00HuanChengBtn);
		huanChengBtn.setOnClickListener(huanChengBtnClick);
		
		mainLayout = (RelativeLayout) findViewById(R.id.b00v00MainLayout);
		mainLayout.addView(xianluView.get(), 0);
		((RelativeLayout.LayoutParams)mainLayout.getLayoutParams()).setMargins(0, 0, 0, 0);
		
		buttomLayout = (LinearLayout) findViewById(R.id.b00v00Bottom);
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
	 * 收藏按钮事件
	 */
	OnClickListener shoucangBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			CommonUtil.startActivityForResult(B00V00Activity.this, 
					B00V01Activity.class, START_RESULT_CODE);
		}
	};
	
	/**
	 * 站点按钮事件
	 */
	OnClickListener zhanDianBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
			// 判断按钮重复点击
			if(currentSelect == ZHANDIAN_SELECT) {
				return;
			} else {
				// 切换UI
				switchViewWithAn(currentSelect, ZHANDIAN_SELECT, 
						mainLayout.getChildAt(0), zhandianView.get());
				currentSelect = ZHANDIAN_SELECT;
				
				// 设置标题
				tittle.setText(R.string.b00v00_station_search);
			}
			
			// 切换视图
			switchView();
			
		}
	};
	
	/**
	 * 线路按钮事件
	 */
	OnClickListener xianluBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
			// 判断按钮重复点击
			if(currentSelect == XIANLU_SELECT) {
				return;
			} else {
				// 切换UI
				switchViewWithAn(currentSelect, XIANLU_SELECT, 
						mainLayout.getChildAt(0), xianluView.get());
				currentSelect = XIANLU_SELECT;
				
				// 设置标题
				tittle.setText(R.string.b00v00_line_search);
			}
			
			// 切换视图
			switchView();
		}
	};
	/**
	 * 换乘按钮事件
	 */
	OnClickListener huanChengBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
			// 判断按钮重复点击
			if(currentSelect == HUANCHENG_SELECT) {
				return;
			} else {
				
				// 切换UI
				switchViewWithAn(currentSelect, HUANCHENG_SELECT, 
						mainLayout.getChildAt(0), huanchengView.get());
				currentSelect = HUANCHENG_SELECT;
				
				// 设置标题
				tittle.setText(R.string.b00v00_switch_search);
			}
			
			// 切换视图
			switchView();
			
		}
	};
	
	/**
	 * 切换显示的项目
	 */
	private void switchView() {
		
		switch (currentSelect) {
			case ZHANDIAN_SELECT:
				
				// 设置底部按钮选中状态
				zhanDianBtn.setBackgroundResource(R.color.comm_buttom_blue_1);
				xianluBtn.setBackgroundResource(R.color.comm_buttom_blue_0);
				huanChengBtn.setBackgroundResource(R.color.comm_buttom_blue_0);
				break;
				
			case XIANLU_SELECT:
				
				// 设置底部按钮选中状态
				zhanDianBtn.setBackgroundResource(R.color.comm_buttom_blue_0);
				xianluBtn.setBackgroundResource(R.color.comm_buttom_blue_1);
				huanChengBtn.setBackgroundResource(R.color.comm_buttom_blue_0);
				
				break;
				
			case HUANCHENG_SELECT:
				
				// 设置底部按钮选中状态
				zhanDianBtn.setBackgroundResource(R.color.comm_buttom_blue_0);
				xianluBtn.setBackgroundResource(R.color.comm_buttom_blue_0);
				huanChengBtn.setBackgroundResource(R.color.comm_buttom_blue_1);
				
				break;
	
			default:
				break;
		}
		
	}
	
	/**
	 * 
	 * 开启动画切换视图
	 * @param startIndex   开始位置
	 * @param endIndex     结束位置
	 * @param view1                            移除的视图
	 * @param view2                            添加的视图
	 */
	private void switchViewWithAn(int startIndex, int endIndex, View view1, View view2) {
		
			if(startIndex > endIndex) {
				view1.startAnimation(AnimationUtils.loadAnimation(this,R.anim.push_right_out));
				view2.startAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_in));
			} 
			
			else if(startIndex < endIndex) {
				view1.startAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_out));
				view2.startAnimation(AnimationUtils.loadAnimation(this,R.anim.push_right_in));
			}
			
			Log.d("debug", "view1 > " +view1.toString());
			Log.d("debug", "view2 > " +view2.toString());
			
			mainLayout.addView(view2);
			mainLayout.removeView(view1);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		// 选择换乘站点的时候
		if(resultCode == B00V04Activity.STATION_SELECT_RESULT_CODE) {
			
			Station selectStation = (Station) data.getSerializableExtra(B00V04Activity.RESULT_STATION_KEY);
			
			huanchengView.setZhandianValue(selectStation, requestCode);
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
}

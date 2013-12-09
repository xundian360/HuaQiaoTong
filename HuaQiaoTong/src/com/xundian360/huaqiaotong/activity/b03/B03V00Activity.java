/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b03;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.view.b03.B03V00LeftView;
import com.xundian360.huaqiaotong.view.b03.B03V00ModleView;
import com.xundian360.huaqiaotong.view.b03.B03V00RightView;

/**
 * 帖子列表
 * @author  Administrator
 * @date      2013年10月23日
 * @version 1.0
 */
public class B03V00Activity extends ComNoTittleActivity implements OnClickListener {
	
	// 显示中间视图
	private final int SHOW_M_KEY = 0;
	// 显示左边视图
	private final int SHOW_L_KEY = 1;
	// 显示右边视图
	private final int SHOW_R_KEY = 2;
	
	// 返回按钮
	ImageView retBtn;
	// 用户名
	TextView userName;
	// 用户描述
	TextView userDisc;
	// 用户头像
	ImageView userLogo;
	
	// 第一个导航项目
	LinearLayout groupL;
	TextView lText;
	ImageView lImg;
	
	// 第二个导航项目
	LinearLayout groupM;
	TextView mText;
	ImageView mImg;
	
	// 第三个导航项目
	LinearLayout groupR;
	TextView rText;
	ImageView rImg;
	
	// 底部明细
	LinearLayout mainLayout;
	LinearLayout.LayoutParams matchP = 
			new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	
	// 左边视图
	B03V00LeftView leftView;
	// 中间视图
	B03V00ModleView modleView;
	// 右边视图
	B03V00RightView rightView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b03v00);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		
		leftView = new B03V00LeftView(this);
		modleView = new B03V00ModleView(this);
		rightView = new B03V00RightView(this);
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		retBtn = (ImageView) findViewById(R.id.b03v00RetBtn);
		retBtn.setOnClickListener(this);
		userName = (TextView) findViewById(R.id.b03v00UserName);
		userDisc = (TextView) findViewById(R.id.b03v00UserDis);

		userLogo = (ImageView) findViewById(R.id.b03v00UserLogo);
		
		groupL = (LinearLayout) findViewById(R.id.b03v00GroupL);
		groupL.setOnClickListener(this);
		lText = (TextView) findViewById(R.id.b03v00LText);
		lImg = (ImageView) findViewById(R.id.b03v00LImg);
		
		groupM = (LinearLayout) findViewById(R.id.b03v00GroupM);
		groupM.setOnClickListener(this);
		mText = (TextView) findViewById(R.id.b03v00MText);
		mImg = (ImageView) findViewById(R.id.b03v00MImg);
		
		groupR = (LinearLayout) findViewById(R.id.b03v00GroupR);
		groupR.setOnClickListener(this);
		rText = (TextView) findViewById(R.id.b03v00RText);
		rImg = (ImageView) findViewById(R.id.b03v00RImg);
		
		mainLayout = (LinearLayout) findViewById(R.id.b03v00MainLayout);
		mainLayout.addView(modleView.get(), matchP);
//		mainLayout.addView(leftView.get());
//		mainLayout.addView(rightView.get());
	}

	@Override
	public void onClick(View v) {
		
		int clickViewId = v.getId();
		
		switch (clickViewId) {
		case R.id.b03v00RetBtn:
			// 返回
			onBackPressed();
			break;
			
		case R.id.b03v00GroupL:
			// 显示左边视图
			mainLayout.removeAllViews();
			mainLayout.addView(leftView.get(), matchP);
			swichView(SHOW_L_KEY);
			break;
			
		case R.id.b03v00GroupM:
			// 显示中间视图
			mainLayout.removeAllViews();
			mainLayout.addView(modleView.get(), matchP);
			swichView(SHOW_M_KEY);
			break;
			
		case R.id.b03v00GroupR:
			// 显示右边视图
			
			mainLayout.removeAllViews();
			mainLayout.addView(rightView.get(), matchP);
			swichView(SHOW_R_KEY);
			break;
			
		default:
			break;
		}
	}
	
	/**
	 * 切换视图
	 * @param switchToIndex
	 */
	private void swichView(int switchToIndex) {
		
//		// 当前视图不是选中试图
//		if(switchToIndex == mainLayout.getDisplayedChild()) {
//			return;
//		}
//		
//		// 视图切换
//		mainLayout.setDisplayedChild(switchToIndex);
//		mainLayout.setInAnimation(B03V00Activity.this, R.anim.push_left_in);   
//		mainLayout.setOutAnimation(B03V00Activity.this, R.anim.push_left_out);
		
		// 切换显示状态
		switch (switchToIndex) {
		case SHOW_L_KEY:
			
			// 设置字体颜色
			lText.setTextColor(getResources().getColor(R.color.b03_v01_group_tittle_color_1));
			mText.setTextColor(getResources().getColor(R.color.b03_v01_group_tittle_color_0));
			rText.setTextColor(getResources().getColor(R.color.b03_v01_group_tittle_color_0));
			
			// 设置选中状态
			lImg.setBackgroundResource(R.drawable.b03v00_group_line_1);
			mImg.setBackgroundResource(R.drawable.b03v00_group_line_0);
			rImg.setBackgroundResource(R.drawable.b03v00_group_line_0);
			
			break;
			
		case SHOW_M_KEY:
			
			// 设置字体颜色
			lText.setTextColor(getResources().getColor(R.color.b03_v01_group_tittle_color_0));
			mText.setTextColor(getResources().getColor(R.color.b03_v01_group_tittle_color_1));
			rText.setTextColor(getResources().getColor(R.color.b03_v01_group_tittle_color_0));
			
			// 设置选中状态
			lImg.setBackgroundResource(R.drawable.b03v00_group_line_0);
			mImg.setBackgroundResource(R.drawable.b03v00_group_line_1);
			rImg.setBackgroundResource(R.drawable.b03v00_group_line_0);
			
			break;
			
		case SHOW_R_KEY:
			
			// 设置字体颜色
			lText.setTextColor(getResources().getColor(R.color.b03_v01_group_tittle_color_0));
			mText.setTextColor(getResources().getColor(R.color.b03_v01_group_tittle_color_0));
			rText.setTextColor(getResources().getColor(R.color.b03_v01_group_tittle_color_1));
			
			// 设置选中状态
			lImg.setBackgroundResource(R.drawable.b03v00_group_line_0);
			mImg.setBackgroundResource(R.drawable.b03v00_group_line_0);
			rImg.setBackgroundResource(R.drawable.b03v00_group_line_1);
			
			break;

		default:
			break;
		}
	}
}

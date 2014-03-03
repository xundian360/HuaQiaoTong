package com.xundian360.huaqiaotong;

import org.taptwo.android.widget.FlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Scroller;
import cn.sharesdk.framework.ShareSDK;

import com.xundian360.huaqiaotong.activity.b04.B04V00Activity;
import com.xundian360.huaqiaotong.activity.b04.B04V03Activity;
import com.xundian360.huaqiaotong.activity.com.HQTApplication;
import com.xundian360.huaqiaotong.adapter.b00.B00ViewAdapter;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.UserUtils;

/**
 * 主页
 * 
 * @author Administrator
 * @date 2013年11月10日
 * @version 1.0
 */
public class MainActivity extends FragmentActivity  {

	// 个人中心按钮
	ImageButton meBtn;
	// 设置
	ImageButton setBtn;

	// 导航页
	ViewFlow navPages;

	// 导航页标识(左)
	ImageView leftPoint;
	// 导航页标识(中间)
	ImageView centerPoint;
	// 导航页标识(右)
	ImageView rightPoint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.b00);

		// 初始化数据
		initData();

		// 初始化组件
		initModule();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		
	}

	/**
	 * 初始化组件
	 */
	private void initModule() {

		meBtn = (ImageButton) findViewById(R.id.mainTittleLBtn);
		meBtn.setOnClickListener(meBtnClick);

		setBtn = (ImageButton) findViewById(R.id.mainTittleRBtn);
		setBtn.setOnClickListener(setBtnClick);

		leftPoint = (ImageView) findViewById(R.id.b00LeftPoint);
		centerPoint = (ImageView) findViewById(R.id.b00CenterPoint);
		rightPoint = (ImageView) findViewById(R.id.b00RightPoint);

		navPages = (ViewFlow) findViewById(R.id.b00NavPage);
		B00ViewAdapter adapter = new B00ViewAdapter(this);
		navPages.setAdapter(adapter);
		navPages.setFlowIndicator(itermSelect);
		navPages.setSelection(B00ViewAdapter.VIEW2);
	}

	FlowIndicator itermSelect = new FlowIndicator() {

		@Override
		public void onSwitched(View view, int position) {
			onItemSelected(position);
		}

		@Override
		public void setViewFlow(ViewFlow view) {
		}

		@Override
		public void onScrolled(int h, int v, int oldh, int oldv) {
		}
	};

	/**
	 * 选择
	 */
	public void onItemSelected(int index) {

		switch (index) {
		case B00ViewAdapter.VIEW1:

			leftPoint.setImageResource(R.drawable.b00_piont_1);
			centerPoint.setImageResource(R.drawable.b00_piont_0);
			rightPoint.setImageResource(R.drawable.b00_piont_0);
			break;
		case B00ViewAdapter.VIEW2:
			leftPoint.setImageResource(R.drawable.b00_piont_0);
			centerPoint.setImageResource(R.drawable.b00_piont_1);
			rightPoint.setImageResource(R.drawable.b00_piont_0);
			break;
		case B00ViewAdapter.VIEW3:
			leftPoint.setImageResource(R.drawable.b00_piont_0);
			centerPoint.setImageResource(R.drawable.b00_piont_0);
			rightPoint.setImageResource(R.drawable.b00_piont_1);
			break;

		default:
			break;
		}
	}

	/**
	 * 个人中心
	 */
	OnClickListener meBtnClick = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// 判断用户是否登录
			if (UserUtils.isLogin(MainActivity.this)) {
				// 个人中心
				CommonUtil.startSubActivity(MainActivity.this,
						B04V03Activity.class);
			} else {
				// 登陆
				Intent in = new Intent(MainActivity.this, B04V00Activity.class);
				in.putExtra(B04V00Activity.IS_CENTER_TO,
						B04V00Activity.IS_CENTER_TO);

				CommonUtil.startSubActivity(MainActivity.this, in);
			}
		}
	};

	/**
	 * 设置按钮
	 */
	OnClickListener setBtnClick = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			ShowMessageUtils.show(MainActivity.this, "设置页面");
		}
	};

	// 建议在APP整体退出之前调用MapApi的destroy()函数，不要在每个activity的OnDestroy中调用，
	// 避免MapApi重复创建初始化，提高效率
	@Override
	protected void onDestroy() {
		HQTApplication app = (HQTApplication) this.getApplication();

		// 销毁百度地图管理对象
		if (app.mBMapManager != null) {
			app.mBMapManager.destroy();
			app.mBMapManager = null;
		}

		// 销毁分享组建对象
		ShareSDK.stopSDK(this);

		super.onDestroy();
		System.exit(0);
	}

}

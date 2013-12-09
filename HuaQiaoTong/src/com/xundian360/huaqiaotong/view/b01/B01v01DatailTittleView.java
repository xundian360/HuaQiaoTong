/**
 * 
 */
package com.xundian360.huaqiaotong.view.b01;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vCup.slidView.LslideView;
import com.vCup.slidView.slideListener;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.util.CommonUtil;

/**
 * KTV评论的Tittle View
 * 
 * @author  Administrator
 * @date      2013年10月12日
 * @version 1.0
 */
public class B01v01DatailTittleView {
	
	Context context;
	View mainView;
	
	// 打电话
	ImageButton callBtn;
	// 营业时间
	TextView tradeTime;
	// 印象：不好
	TextView impressionNg;
	// 印象：非常好
	TextView impressionGood;
	// 印象：一般
	TextView impressionOk;
	
	LinearLayout callCupViewLayout;
	
	// 拨打电话滑块
	 LslideView callCupView;
	
	// KTV对象
	Baidu ktv;

	public B01v01DatailTittleView(Context context, Baidu ktv) {
		this.context = context;
		this.ktv = ktv;
		
		// 初始化视图
		initView();
	}
	
	/**
	 * 初始化视图
	 */
	private void initView() {
		mainView = ((Activity)context).getLayoutInflater().inflate(R.layout.b01v01_detail_tittle, null);
		
		callCupViewLayout = (LinearLayout) mainView.findViewById(R.id.b01v01CallCup);
		
		callCupView =  new LslideView(context, 
				null, null, null, 
				callSlideListener, 
				"滑动拨打电话");
		
//		callCupView.setText("滑动拨打电话");
		callCupView.setTextColor(Color.WHITE);
		callCupView.setTextSize(24);
//		callCupView.setslideListener(callSlideListener);
		callCupViewLayout.addView(callCupView, 
				new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		callBtn = (ImageButton) mainView.findViewById(R.id.b01v01DetailCallBtn);
		callBtn.setOnClickListener(callBtnClick);
		
		tradeTime = (TextView) mainView.findViewById(R.id.b01v01DetailTime);
		
		impressionNg = (TextView) mainView.findViewById(R.id.b01v01DetailImp0);
		impressionNg.setOnClickListener(impressionNgClick);
		
		impressionGood = (TextView) mainView.findViewById(R.id.b01v01DetailImp1);
		impressionGood.setOnClickListener(impressionGoodClick);
		
		impressionOk = (TextView) mainView.findViewById(R.id.b01v01DetailImp2);
		impressionOk.setOnClickListener(impressionOkClick);
	}
	
	slideListener callSlideListener = new slideListener() {
		
		@Override
		public void OnSlideMoveStart() {
//			ShowMessageUtils.show(context, "OnSlideMoveStart");
		}
		
		@Override
		public void OnSlideMoveGiveUp() {
//			ShowMessageUtils.show(context, "OnSlideMoveGiveUp");
		}
		
		@Override
		public void OnSlideMoveEnd() {
//			CommonUtil.callPhone(context, ktv.getTelephone());
		}
	};
	
	/**
	 * 拨打电话
	 */
	OnClickListener callBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			CommonUtil.callPhone(context, ktv.getTelephone());
		}
	};
	
	/**
	 * 印象：不好
	 */
	OnClickListener impressionNgClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
		}
	};
	
	/**
	 * 印象：非常好
	 */
	OnClickListener impressionGoodClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
		}
	};
	
	/**
	 * 印象：一般
	 */
	OnClickListener impressionOkClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
		}
	};
	
	/**
	 * 返回View
	 */
	public View get() {
		return mainView;
	}
}

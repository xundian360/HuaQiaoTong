/**
 * 
 */
package com.xundian360.huaqiaotong.view.b03;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b03.B03V01Activity;
import com.xundian360.huaqiaotong.activity.b03.B03V04Activity;
import com.xundian360.huaqiaotong.modle.b03.PostGroup;
import com.xundian360.huaqiaotong.util.CommonUtil;

/**
 * 论坛左边视图
 * @author  Administrator
 * @date      2013年11月19日
 * @version 1.0
 */
public class B03V00LeftView implements OnClickListener {
	
	public static final int REQUEST_CODE = 100;
	
	View mainView;
	
	Context context;
	// 第一幅图
	RelativeLayout firstImg;
	// 第一幅图标题
	TextView firstImgTittle;
	// 第一幅图时间
	TextView firstImgTime;
	
	// 租售房源
	LinearLayout zsBtn;
	// 房屋装修
	LinearLayout zxBtn;
	// 招聘求职
	LinearLayout zpBtn;
	// 兼职信息
	LinearLayout jzBtn;
	
	public B03V00LeftView(Context context) {
		
		this.context = context;
		
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
		
		mainView = ((Activity)context).getLayoutInflater().inflate(R.layout.b03v00_detail_l, null);
		
		firstImg = (RelativeLayout) mainView.findViewById(R.id.b03v00G0Img6Btn);
		firstImg.setOnClickListener(this);
		
		firstImgTittle = (TextView) mainView.findViewById(R.id.b03v00G0Img6Tittle);
		firstImgTime = (TextView) mainView.findViewById(R.id.b03v00G0Img6Time);
		
		zsBtn = (LinearLayout) mainView.findViewById(R.id.b03v00G0Item3Btn);
		zsBtn.setOnClickListener(this);
		
		zxBtn = (LinearLayout) mainView.findViewById(R.id.b03v00G0Item4Btn);
		zxBtn.setOnClickListener(this);
		
		zpBtn = (LinearLayout) mainView.findViewById(R.id.b03v00G0Item5Btn);
		zpBtn.setOnClickListener(this);
		
		jzBtn = (LinearLayout) mainView.findViewById(R.id.b03v00G0Item6Btn);
		jzBtn.setOnClickListener(this);
	}
	
	/**
	 * 返回View
	 */
	public View get() {
		return mainView;
	}

	@Override
	public void onClick(View v) {
		
		// 取得View Id
		int clickId = v.getId();
		
		// 根据不同ID，处理不同业务
		switch (clickId) {
		case R.id.b03v00G0Img6Btn:
			CommonUtil.startActivityForResult(context, 
					B03V01Activity.class, 
					REQUEST_CODE);
			
			break;
		case R.id.b03v00G0Item3Btn:
			CommonUtil.startActivityForResult(context, 
					B03V04Activity.class, 
					B03V04Activity.POST_GROUP_KEY, 
					new PostGroup(context.getString(R.string.b03v00_btn_item_key_2), context.getString(R.string.b03v00_btn_item_2)), 
					REQUEST_CODE);
			break;
		case R.id.b03v00G0Item4Btn:
			CommonUtil.startActivityForResult(context, 
					B03V04Activity.class, 
					B03V04Activity.POST_GROUP_KEY, 
					new PostGroup(context.getString(R.string.b03v00_btn_item_key_3), context.getString(R.string.b03v00_btn_item_3)), 
					REQUEST_CODE);
			break;
		case R.id.b03v00G0Item5Btn:
			CommonUtil.startActivityForResult(context, 
					B03V04Activity.class, 
					B03V04Activity.POST_GROUP_KEY, 
					new PostGroup(context.getString(R.string.b03v00_btn_item_key_4), context.getString(R.string.b03v00_btn_item_4)), 
					REQUEST_CODE);
			break;
		case R.id.b03v00G0Item6Btn:
			CommonUtil.startActivityForResult(context, 
					B03V01Activity.class, 
					B03V04Activity.POST_GROUP_KEY, 
					new PostGroup(context.getString(R.string.b03v00_btn_item_key_5), context.getString(R.string.b03v00_btn_item_5)), 
					REQUEST_CODE);
			break;

		default:
			break;
		}
	}

}

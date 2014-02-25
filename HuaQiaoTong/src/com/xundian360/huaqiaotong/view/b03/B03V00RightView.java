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
 * 论坛右边视图
 * 
 * @author Administrator
 * @date 2013年11月19日
 * @version 1.0
 */
public class B03V00RightView implements OnClickListener {

	public static final int REQUEST_CODE = 102;

	View mainView;

	Context context;

	// 第一幅图
	RelativeLayout firstImg;
	// 第一幅图标题
	TextView firstImgTittle;
	// 第一幅图时间
	TextView firstImgTime;

	// 家庭数码
	LinearLayout shumaBtn;
	// 出行工具
	LinearLayout chuxinBtn;
	// 家具家具
	LinearLayout jiajuBtn;
	// 物品交换
	LinearLayout jiaohuanBtn;
	// 虚拟物品
	LinearLayout xuniBtn;
	// 其他
	LinearLayout qitaBtn;

	public B03V00RightView(Context context) {

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
		mainView = ((Activity) context).getLayoutInflater().inflate(
				R.layout.b03v00_detail_r, null);

		firstImg = (RelativeLayout) mainView.findViewById(R.id.b03v00G0Img7Btn);
		firstImg.setOnClickListener(this);

		firstImgTittle = (TextView) mainView
				.findViewById(R.id.b03v00G0Img7Tittle);
		firstImgTime = (TextView) mainView.findViewById(R.id.b03v00G0Img7Time);

		shumaBtn = (LinearLayout) mainView.findViewById(R.id.b03v00G0Item7Btn);
		shumaBtn.setOnClickListener(this);

		chuxinBtn = (LinearLayout) mainView.findViewById(R.id.b03v00G0Item8Btn);
		chuxinBtn.setOnClickListener(this);

		jiajuBtn = (LinearLayout) mainView.findViewById(R.id.b03v00G0Item9Btn);
		jiajuBtn.setOnClickListener(this);

		jiaohuanBtn = (LinearLayout) mainView
				.findViewById(R.id.b03v00G0Item10Btn);
		jiaohuanBtn.setOnClickListener(this);

		xuniBtn = (LinearLayout) mainView.findViewById(R.id.b03v00G0Item11Btn);
		xuniBtn.setOnClickListener(this);

		qitaBtn = (LinearLayout) mainView.findViewById(R.id.b03v00G0Item12Btn);
		qitaBtn.setOnClickListener(this);
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
		case R.id.b03v00G0Img7Btn:
			CommonUtil.startActivityForResult(context, B03V01Activity.class,
					REQUEST_CODE);
			break;
		case R.id.b03v00G0Item7Btn:
			CommonUtil.startActivityForResult(
					context,
					B03V04Activity.class,
					B03V04Activity.POST_GROUP_KEY,
					new PostGroup(context
							.getString(R.string.b03v00_btn_item_key_6), context
							.getString(R.string.b03v00_btn_item_6)),
					REQUEST_CODE);
			break;
		case R.id.b03v00G0Item8Btn:
			CommonUtil.startActivityForResult(
					context,
					B03V04Activity.class,
					B03V04Activity.POST_GROUP_KEY,
					new PostGroup(context
							.getString(R.string.b03v00_btn_item_key_7), context
							.getString(R.string.b03v00_btn_item_7)),
					REQUEST_CODE);
			break;
		case R.id.b03v00G0Item9Btn:
			CommonUtil.startActivityForResult(
					context,
					B03V04Activity.class,
					B03V04Activity.POST_GROUP_KEY,
					new PostGroup(context
							.getString(R.string.b03v00_btn_item_key_8), context
							.getString(R.string.b03v00_btn_item_8)),
					REQUEST_CODE);
			break;
		case R.id.b03v00G0Item10Btn:
			CommonUtil.startActivityForResult(
					context,
					B03V04Activity.class,
					B03V04Activity.POST_GROUP_KEY,
					new PostGroup(context
							.getString(R.string.b03v00_btn_item_key_9), context
							.getString(R.string.b03v00_btn_item_9)),
					REQUEST_CODE);
			break;
		case R.id.b03v00G0Item11Btn:
			CommonUtil.startActivityForResult(
					context,
					B03V04Activity.class,
					B03V04Activity.POST_GROUP_KEY,
					new PostGroup(context
							.getString(R.string.b03v00_btn_item_key_10),
							context.getString(R.string.b03v00_btn_item_10)),
					REQUEST_CODE);
			break;
		case R.id.b03v00G0Item12Btn:
			CommonUtil.startActivityForResult(
					context,
					B03V04Activity.class,
					B03V04Activity.POST_GROUP_KEY,
					new PostGroup(context
							.getString(R.string.b03v00_btn_item_key_11),
							context.getString(R.string.b03v00_btn_item_11)),
					REQUEST_CODE);
			break;

		default:
			break;
		}
	}

}

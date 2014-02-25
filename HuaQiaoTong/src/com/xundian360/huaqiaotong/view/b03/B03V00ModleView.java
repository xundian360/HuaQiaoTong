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
 * 论坛中间视图
 * 
 * @author Administrator
 * @date 2013年11月19日
 * @version 1.0
 */
public class B03V00ModleView implements OnClickListener {

	public static final int REQUEST_CODE = 101;

	View mainView;

	Context context;

	// 第一幅图
	RelativeLayout firstImg;
	// 第一幅图标题
	TextView firstImgTittle;
	// 第一幅图时间
	TextView firstImgTime;

	// 第二幅图
	RelativeLayout secondImg;
	// 第二幅图标题
	TextView secondImgTittle;
	// 第二幅图时间
	TextView secondImgTime;

	// 第三幅图
	RelativeLayout thirdImg;
	// 第三幅图标题
	TextView thirdImgTittle;
	// 第三幅图时间
	TextView thirdImgTime;

	// 第四幅图
	RelativeLayout fourthImg;
	// 第四幅图标题
	TextView fourthImgTittle;
	// 第四幅图时间
	TextView fourthImgTime;

	// 第五幅图
	RelativeLayout fifthImg;
	// 第五幅图标题
	TextView fifthImgTittle;
	// 第五幅图时间
	TextView fifthImgTime;

	// 更多新闻
	LinearLayout moreBtn;
	// 更多朋友推荐
	LinearLayout jiaoyouBtn;

	public B03V00ModleView(Context context) {

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
				R.layout.b03v00_detail_m, null);

		firstImg = (RelativeLayout) mainView.findViewById(R.id.b03v00G0Img0Btn);
		firstImg.setOnClickListener(this);
		firstImgTittle = (TextView) mainView
				.findViewById(R.id.b03v00G0Img0Tittle);
		firstImgTime = (TextView) mainView.findViewById(R.id.b03v00G0Img0Time);

		secondImg = (RelativeLayout) mainView
				.findViewById(R.id.b03v00G0Img1Btn);
		secondImg.setOnClickListener(this);
		secondImgTittle = (TextView) mainView
				.findViewById(R.id.b03v00G0Img1Tittle);
		secondImgTime = (TextView) mainView.findViewById(R.id.b03v00G0Img1Time);

		thirdImg = (RelativeLayout) mainView.findViewById(R.id.b03v00G0Img3Btn);
		thirdImg.setOnClickListener(this);
		thirdImgTittle = (TextView) mainView
				.findViewById(R.id.b03v00G0Img3Tittle);
		thirdImgTime = (TextView) mainView.findViewById(R.id.b03v00G0Img3Time);

		fourthImg = (RelativeLayout) mainView
				.findViewById(R.id.b03v00G0Img4Btn);
		fourthImg.setOnClickListener(this);
		fourthImgTittle = (TextView) mainView
				.findViewById(R.id.b03v00G0Img4Tittle);
		fourthImgTime = (TextView) mainView.findViewById(R.id.b03v00G0Img4Time);

		fifthImg = (RelativeLayout) mainView.findViewById(R.id.b03v00G0Img5Btn);
		fifthImg.setOnClickListener(this);
		fifthImgTittle = (TextView) mainView
				.findViewById(R.id.b03v00G0Img5Tittle);
		fifthImgTime = (TextView) mainView.findViewById(R.id.b03v00G0Img5Time);

		moreBtn = (LinearLayout) mainView.findViewById(R.id.b03v00G0Item1Btn);
		moreBtn.setOnClickListener(this);

		jiaoyouBtn = (LinearLayout) mainView
				.findViewById(R.id.b03v00G0Item2Btn);
		jiaoyouBtn.setOnClickListener(this);
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
		case R.id.b03v00G0Img0Btn:
			CommonUtil.startActivityForResult(context, B03V01Activity.class,
					REQUEST_CODE);
			break;
		case R.id.b03v00G0Img1Btn:
			CommonUtil.startActivityForResult(context, B03V01Activity.class,
					REQUEST_CODE);
			break;
		case R.id.b03v00G0Img3Btn:
			CommonUtil.startActivityForResult(context, B03V01Activity.class,
					REQUEST_CODE);
			break;
		case R.id.b03v00G0Img4Btn:
			CommonUtil.startActivityForResult(context, B03V01Activity.class,
					REQUEST_CODE);
			break;
		case R.id.b03v00G0Img5Btn:
			CommonUtil.startActivityForResult(context, B03V01Activity.class,
					REQUEST_CODE);
			break;
		case R.id.b03v00G0Item1Btn:
			CommonUtil.startActivityForResult(
					context,
					B03V04Activity.class,
					B03V04Activity.POST_GROUP_KEY,
					new PostGroup(context
							.getString(R.string.b03v00_btn_item_key_0), context
							.getString(R.string.b03v00_btn_item_0)),
					REQUEST_CODE);
			break;
		case R.id.b03v00G0Item2Btn:
			CommonUtil.startActivityForResult(
					context,
					B03V04Activity.class,
					B03V04Activity.POST_GROUP_KEY,
					new PostGroup(context
							.getString(R.string.b03v00_btn_item_key_1), context
							.getString(R.string.b03v00_btn_item_1)),
					REQUEST_CODE);
			break;

		default:
			break;
		}

	}

}

/**
 * 
 */
package com.xundian360.huaqiaotong.view.b00;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b03.B03V04Activity;
import com.xundian360.huaqiaotong.modle.b03.PostGroup;
import com.xundian360.huaqiaotong.util.CommonUtil;

/**
 * 首页--中间视图
 * 
 * @author Administrator
 * @date 2013年10月12日
 * @version 1.0
 */
public class B00RightView implements OnClickListener, OnTouchListener {

	public static final int REQUEST_CODE = 100;

	String[] testPicsDis = { "[新鲜事]北京遭遇雾霾1", "[新鲜事]北京遭遇雾霾2", "[新鲜事]北京遭遇雾霾3",
			"[新鲜事]北京遭遇雾霾4", };
	
	int[] testPicsId = {
			R.drawable.test_b03_0,
			R.drawable.test_b03_1,
			R.drawable.test_b03_2,
			R.drawable.test_b03_3,
	};

	Context context;
	View mainView;

	// 头部图片
	ViewFlipper tittleImgs;
	// 标点状态
	LinearLayout viewFlowinDic;
	// 头部图片描述
	TextView tittleImgDis;
	// 头部图片状态
	LinearLayout tittleImgStatus;
	// 新鲜事
	Button xinxianBtn;
	// 交友
	Button jiaoyouBtn;
	// 房屋租售
	Button fangwuBtn;
	// 优惠活动
	Button youhuiBtn;
	// 求职招聘
	Button qiuzhiBtn;
	// 二手交易
	Button ershouBtn;

	public B00RightView(Context context) {
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
		mainView = ((Activity) context).getLayoutInflater().inflate(R.layout.b00_item_right, null);

		viewFlowinDic = (LinearLayout) mainView.findViewById(R.id.b00ViewFlowinDic);

		tittleImgs = (ViewFlipper) mainView.findViewById(R.id.b00RightImgs);
		tittleImgs.setOnTouchListener(this);
		
		for (int i = 0; i < testPicsId.length; i++) {
			ImageView img = new ImageView(context);
			
			img.setImageResource(testPicsId[i]);
			tittleImgs.addView(img);
		}

		tittleImgDis = (TextView) mainView.findViewById(R.id.b00RightImgTittle);
		tittleImgStatus = (LinearLayout) mainView
				.findViewById(R.id.b00RightImgStatus);

		xinxianBtn = (Button) mainView.findViewById(R.id.b00RightXxBtn);
		xinxianBtn.setOnClickListener(this);

		jiaoyouBtn = (Button) mainView.findViewById(R.id.b00RightJyBtn);
		jiaoyouBtn.setOnClickListener(this);

		fangwuBtn = (Button) mainView.findViewById(R.id.b00RightFwBtn);
		fangwuBtn.setOnClickListener(this);

		youhuiBtn = (Button) mainView.findViewById(R.id.b00RightYhBtn);
		youhuiBtn.setOnClickListener(this);

		qiuzhiBtn = (Button) mainView.findViewById(R.id.b00RightQzBtn);
		qiuzhiBtn.setOnClickListener(this);

		ershouBtn = (Button) mainView.findViewById(R.id.b00RightEsBtn);
		ershouBtn.setOnClickListener(this);
	}
//
//	/**
//	 * 头部图片选中事件
//	 */
//	OnItemSelectedListener tittleImgsSelected = new OnItemSelectedListener() {
//		@Override
//		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
//				long arg3) {
//
//			// 设置图片标题
//			tittleImgDis.setText(testPicsDis[arg2]);
//		}
//
//		@Override
//		public void onNothingSelected(AdapterView<?> arg0) {
//		}
//	};

	/**
	 * 按钮点击事件
	 */
	@Override
	public void onClick(View v) {
		// 取得View Id
		int clickId = v.getId();

		// 帖子组对象
		PostGroup selectPostGroup = null;

		// 根据不同ID，处理不同业务
		switch (clickId) {
		case R.id.b00RightXxBtn:
			// 设置参数
			selectPostGroup = new PostGroup(
					context.getString(R.string.b03v00_right_xx_key),
					context.getString(R.string.b03v00_right_xx));
			// 页面跳转
			toPostList(selectPostGroup);
			break;

		case R.id.b00RightJyBtn:
			// 设置参数
			selectPostGroup = new PostGroup(
					context.getString(R.string.b03v00_right_jy_key),
					context.getString(R.string.b03v00_right_jy));
			// 页面跳转
			toPostList(selectPostGroup);
			break;

		case R.id.b00RightFwBtn:
			// 设置参数
			selectPostGroup = new PostGroup(
					context.getString(R.string.b03v00_right_fw_key),
					context.getString(R.string.b03v00_right_fw));
			// 页面跳转
			toPostList(selectPostGroup);
			break;

		case R.id.b00RightYhBtn:
			// 设置参数
			selectPostGroup = new PostGroup(
					context.getString(R.string.b03v00_right_yh_key),
					context.getString(R.string.b03v00_right_yh));
			// 页面跳转
			toPostList(selectPostGroup);
			break;

		case R.id.b00RightQzBtn:
			// 设置参数
			selectPostGroup = new PostGroup(
					context.getString(R.string.b03v00_right_qz_key),
					context.getString(R.string.b03v00_right_qz));
			// 页面跳转
			toPostList(selectPostGroup);
			break;

		case R.id.b00RightEsBtn:
			// 设置参数
			selectPostGroup = new PostGroup(
					context.getString(R.string.b03v00_right_es_key),
					context.getString(R.string.b03v00_right_es));
			// 页面跳转
			toPostList(selectPostGroup);
			break;

		default:
			break;
		}
	}

	/**
	 * 跳转到帖子列表页面
	 * 
	 * @param selectPostGroup
	 */
	private void toPostList(PostGroup selectPostGroup) {
		// 页面跳转
		if (selectPostGroup != null) {
			CommonUtil.startActivityForResult(context, B03V04Activity.class,
					B03V04Activity.POST_GROUP_KEY, selectPostGroup,
					REQUEST_CODE);
		}
	}

	/**
	 * 返回View
	 */
	public View get() {
		return mainView;
	}
	
    private float touchDownX;  // 手指按下的X坐标
    private float touchUpX;  //手指松开的X坐标

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 取得左右滑动时手指按下的X坐标
            touchDownX = event.getX();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            // 取得左右滑动时手指松开的X坐标
            touchUpX = event.getX();
            
            if (touchUpX - touchDownX > 100) {
            	// 从左往右，看前一个View
            	tittleImgs.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.push_left_in));
            	tittleImgs.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.push_right_out));
            	tittleImgs.showPrevious();
            } else if (touchDownX - touchUpX > 100) {
            	// 从右往左，看后一个View
            	tittleImgs.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.push_right_in));
    			tittleImgs.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.push_left_out));
    			tittleImgs.showNext();
            }
            return true;
        }
        return false;
	}
}

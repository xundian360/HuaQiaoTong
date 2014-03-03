/**
 * 
 */
package com.xundian360.huaqiaotong.view.b01;

import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.xundian360.huaqiaotong.R;

/**
 * 明显页面Dialog
 * 
 * @author Administrator
 * @date 2013年10月22日
 * @version 1.0
 */
public class B00v00ImgDialog implements OnTouchListener {

	public Dialog dialog;

	View dialogView;

	Context context;
	List<String> imgPath;

	ViewFlipper imgs;

	ImageView preBtn;
	ImageView nextBtn;
	
	// 图片缓存
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.b01v00_item_dafilt_img)
			.showImageForEmptyUri(R.drawable.b01v00_item_dafilt_img)
			.showImageOnFail(R.drawable.b01v00_item_dafilt_img)
			.cacheOnDisc(true).build();

	public B00v00ImgDialog(Context context, List<String> imgPath) {

		this.context = context;
		this.imgPath = imgPath;

		// 初始化视图
		initView();

		// 初始化视图
		initDaliogView(context);
	}

	/**
	 * 初始化视图
	 */
	private void initView() {

		dialogView = LayoutInflater.from(context).inflate(R.layout.b01v01_img_dialog_layout, null);

		preBtn = (ImageView) dialogView.findViewById(R.id.b01v01DialogPre);
		preBtn.setOnClickListener(preBtnClick);

		nextBtn = (ImageView) dialogView.findViewById(R.id.b01v01DialogNext);
		nextBtn.setOnClickListener(nextBtnClick);

		imgs = (ViewFlipper) dialogView.findViewById(R.id.b01v01TittleImg);
		imgs.setOnTouchListener(this);
		

		// 设置View
		for (int i = 0; i < imgPath.size(); i++) {

			ImageView img = new ImageView(context);
			ImageLoader.getInstance().displayImage(imgPath.get(i), img, options, imageLoadingListener);
			
			imgs.addView(img, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		}
	}
	
	/**
	 * Image完成加载事件
	 */
	ImageLoadingListener imageLoadingListener = new ImageLoadingListener() {
		
		@Override
		public void onLoadingStarted(String arg0, View arg1) {}
		
		@Override
		public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {}
		
		@Override
		public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
			arg1.setBackgroundDrawable(new BitmapDrawable(arg2));
		}
		
		@Override
		public void onLoadingCancelled(String arg0, View arg1) {}
	};

	/**
	 * 上一张
	 */
	OnClickListener preBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			imgs.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.push_left_in));
			imgs.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.push_right_out));
			imgs.showPrevious();
		}
	};

	/**
	 * 下一张
	 */
	OnClickListener nextBtnClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			imgs.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.push_right_in));
			imgs.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.push_left_out));
			imgs.showNext();
		}
	};

	/**
	 * 初始化视图
	 */
	public void initDaliogView(Context context) {

		dialog = new Dialog(context, R.style.B01v01ImgDialogTheme);

		dialog.setCancelable(true);

		dialog.setContentView(dialogView);
	}

	public void show(int selectImg) {
		if (!dialog.isShowing()) {
			dialog.show();
		}

		// 设置选中View
		imgs.setDisplayedChild(selectImg);
	}

	/**
	 * 隐藏Dialog
	 */
	public void dismiss() {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
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
    			imgs.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.push_left_in));
    			imgs.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.push_right_out));
    			imgs.showPrevious();
            } else if (touchDownX - touchUpX > 100) {
            	// 从右往左，看后一个View
    			imgs.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.push_right_in));
    			imgs.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.push_left_out));
    			imgs.showNext();
            }
            return true;
        }
        return false;
	}

}

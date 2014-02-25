/**
 * 
 */
package com.xundian360.huaqiaotong.view.b01;

import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
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

	GestureDetector imgFilter;

	/**
	 * 初始化视图
	 */
	private void initView() {

		dialogView = LayoutInflater.from(context).inflate(
				R.layout.b01v01_img_dialog_layout, null);
		dialogView.setOnTouchListener(this);

		preBtn = (ImageView) dialogView.findViewById(R.id.b01v01DialogPre);
		preBtn.setOnClickListener(preBtnClick);

		nextBtn = (ImageView) dialogView.findViewById(R.id.b01v01DialogNext);
		nextBtn.setOnClickListener(nextBtnClick);

		imgFilter = new GestureDetector(context, imgFilterListener);

		imgs = (ViewFlipper) dialogView.findViewById(R.id.b01v01TittleImg);

		// 设置View
		for (int i = 0; i < imgPath.size(); i++) {

			ImageView img = new ImageView(context);
			ImageLoader.getInstance()
					.displayImage(imgPath.get(i), img, options);

			imgs.addView(img, i);
		}
	}

	/**
	 * 上一张
	 */
	OnClickListener preBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			imgs.setInAnimation(AnimationUtils.loadAnimation(context,
					R.anim.push_left_in));
			imgs.setOutAnimation(AnimationUtils.loadAnimation(context,
					R.anim.push_right_out));
			imgs.showPrevious();
		}
	};

	/**
	 * 下一张
	 */
	OnClickListener nextBtnClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			imgs.setInAnimation(AnimationUtils.loadAnimation(context,
					R.anim.push_right_in));
			imgs.setOutAnimation(AnimationUtils.loadAnimation(context,
					R.anim.push_left_out));
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

	OnGestureListener imgFilterListener = new OnGestureListener() {
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			if (e1.getX() - e2.getX() > 120) {
				imgs.setInAnimation(AnimationUtils.loadAnimation(context,
						R.anim.push_left_in));
				imgs.setOutAnimation(AnimationUtils.loadAnimation(context,
						R.anim.push_left_out));
				imgs.showNext();
				return true;
			} else if (e1.getX() - e2.getX() < -120) {
				imgs.setInAnimation(AnimationUtils.loadAnimation(context,
						R.anim.push_right_in));
				imgs.setOutAnimation(AnimationUtils.loadAnimation(context,
						R.anim.push_right_out));
				imgs.showPrevious();
				return true;
			}
			return false;
		}

		@Override
		public boolean onDown(MotionEvent e) {
			return false;
		}
	};

	/**
	 * 隐藏Dialog
	 */
	public void dismiss() {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return imgFilter.onTouchEvent(event);
	}
}

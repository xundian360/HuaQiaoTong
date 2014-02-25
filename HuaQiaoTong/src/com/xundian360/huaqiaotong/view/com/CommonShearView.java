/**
 * 
 */
package com.xundian360.huaqiaotong.view.com;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.util.ShearUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;

/**
 * 分享View
 * 
 * @author TengTeng
 * @date 2013-10-22
 * @version 1.0
 */
public class CommonShearView extends BottomDialog {

	View mainView;

	Context context;

	// 微信
	ImageView weixinImg;
	// 朋友圈
	ImageView pengyouImg;
	// 微博
	ImageView weiboImg;

	String shearText = "Test";
	String shearImgPath = "file:///android_asset/shear_img.png";

	Handler _handler;

	public CommonShearView(Context context) {

		this.context = context;
		_handler = new Handler();

		// 初始化Dialog
		initDaliogView(context);

		// 初始化数据
		initData();

		// 初始化视图
		initView();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {

		shearImgPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/pic.jpg";
	}

	/**
	 * 初始化视图
	 */
	private void initView() {
		mainView = ((Activity) context).getLayoutInflater().inflate(
				R.layout.common_shear_layout, null);

		weixinImg = (ImageView) mainView.findViewById(R.id.commonShearWeixin);
		weixinImg.setOnClickListener(weixinImgClick);

		pengyouImg = (ImageView) mainView.findViewById(R.id.commonShearPengyou);
		pengyouImg.setOnClickListener(pengyouImgClick);

		weiboImg = (ImageView) mainView.findViewById(R.id.commonShearWeibo);
		weiboImg.setOnClickListener(weiboImgClick);

		dialog.setContentView(mainView, new ViewGroup.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	SimpleInputDialog inputDialog;

	/**
	 * 微信分享
	 */
	OnClickListener weixinImgClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 分享到微信
			ShearUtil.shearPicToWxWithPath(context, "花桥测试", "测试测试测试测试测试测试",
					shearImgPath, paListener);
		}
	};

	/**
	 * 朋友圈分享
	 */
	OnClickListener pengyouImgClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 分享到朋友圈
			ShearUtil.shearPicToWxFWithPath(context, "花桥测试", "测试测试测试测试测试测试",
					shearImgPath, paListener);
		}
	};

	DialogInterface.OnClickListener weiboShearClick = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// 分享到新浪微博
			ShearUtil.shearToSinaWeibo(context, inputDialog.getMsg(),
					shearImgPath, paListener);
		}
	};

	/**
	 * 分享监听
	 */
	PlatformActionListener paListener = new PlatformActionListener() {

		@Override
		public void onError(Platform arg0, int arg1, Throwable arg2) {

			Log.d("debug", arg2.getMessage());

			_handler.post(new Runnable() {
				@Override
				public void run() {
					ShowMessageUtils.show(context, "分享失败");
				}
			});
		}

		@Override
		public void onComplete(Platform arg0, int arg1,
				HashMap<String, Object> arg2) {

			_handler.post(new Runnable() {
				@Override
				public void run() {
					ShowMessageUtils.show(context, "分享成功");
				}
			});
		}

		@Override
		public void onCancel(Platform arg0, int arg1) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * 微博分享
	 */
	OnClickListener weiboImgClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 弹出分享内容Dialog
			inputDialog = new SimpleInputDialog(context, weiboShearClick);
			inputDialog.show();
		}
	};

}

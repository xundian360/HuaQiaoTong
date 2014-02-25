package com.xundian360.huaqiaotong.view.com;

import java.util.HashMap;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;

/**
 * 分享选择Dialog
 * 
 * @author TeneTeng
 * @date 2013-12-29
 * @version 1.0
 */
public class BottonShearSelectDialog extends BottomDialog {

	Context context;
	View view;

	// 微信
	Button shearWeixin;
	// 朋友圈
	Button shearPengyou;
	// 微博
	Button shearWeibo;
	// 取消按钮
	Button cancleBtn;

	// 分享标题
	String shearTittle;
	// 分享内容
	String shearText;
	// 分享图片路径
	String shearImgPath;

	Handler _handler = new Handler();

	public BottonShearSelectDialog(Context context, String shearTittle,
			String shearText, String shearImgPath) {
		super(context);
		this.context = context;
		this.shearTittle = shearTittle;
		this.shearText = shearText;
		this.shearImgPath = shearImgPath;

		// 初始化视图
		initView();
	}

	/**
	 * 初始化视图
	 */
	private void initView() {

		view = LayoutInflater.from(context).inflate(
				R.layout.shear_select_dialog_layout, null);

		shearWeixin = (Button) view.findViewById(R.id.shearWeixin);
		shearWeixin.setOnClickListener(weixinClick);

		shearPengyou = (Button) view.findViewById(R.id.shearPengyou);
		shearPengyou.setOnClickListener(pengyouClick);

		shearWeibo = (Button) view.findViewById(R.id.shearWeibo);
		shearWeibo.setOnClickListener(weiboClick);

		cancleBtn = (Button) view.findViewById(R.id.shearSelectCancle);
		cancleBtn.setOnClickListener(cancleBtnClick);

		// 添加View到视图
		dialog.setContentView(view, new ViewGroup.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	/**
	 * 微信分享
	 */
	OnClickListener weixinClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 分享到微信
			CommonShearInputDialog shearMsgDialog = new CommonShearInputDialog(
					context, CommonShearInputDialog.SHEAR_WEIXIN_KEY,
					shearTittle, shearText, shearImgPath, paListener);

			shearMsgDialog.show();

			// 取消Dialog显示
			dismiss();
		}
	};

	/**
	 * 朋友圈分享
	 */
	OnClickListener pengyouClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 分享到朋友圈
			CommonShearInputDialog shearMsgDialog = new CommonShearInputDialog(
					context, CommonShearInputDialog.SHEAR_PENGYOU_KEY,
					shearTittle, shearText, shearImgPath, paListener);

			shearMsgDialog.show();

			// 取消Dialog显示
			dismiss();
		}
	};

	/**
	 * 微博分享
	 */
	OnClickListener weiboClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 分享到新浪微博
			CommonShearInputDialog shearMsgDialog = new CommonShearInputDialog(
					context, CommonShearInputDialog.SHEAR_WEIBO_KEY,
					shearTittle, shearText, shearImgPath, paListener);

			shearMsgDialog.show();

			// 取消Dialog显示
			dismiss();
		}
	};

	/**
	 * 取消按钮
	 */
	OnClickListener cancleBtnClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// 取消Dialog显示
			dismiss();
		}
	};

	/**
	 * 分享监听
	 */
	PlatformActionListener paListener = new PlatformActionListener() {

		@Override
		public void onError(Platform arg0, int arg1, Throwable arg2) {

			// Log.d("debug", arg2.getMessage());

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
		}
	};

}

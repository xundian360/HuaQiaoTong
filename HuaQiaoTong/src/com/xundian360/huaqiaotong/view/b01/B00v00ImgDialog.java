/**
 * 
 */
package com.xundian360.huaqiaotong.view.b01;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xundian360.huaqiaotong.R;

/**
 * 明显页面Dialog
 * @author  Administrator
 * @date      2013年10月22日
 * @version 1.0
 */
public class B00v00ImgDialog {
	
	public Dialog dialog;
	
	View dialogView;
	
	Context context;
	String imgPath;
	
	// 图片缓存
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.b01v00_item_dafilt_img)
			.showImageForEmptyUri(R.drawable.b01v00_item_dafilt_img)
			.showImageOnFail(R.drawable.b01v00_item_dafilt_img)
			.cacheOnDisc(true)
			.build();
	
	public B00v00ImgDialog(Context context, String imgPath) {
		
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
		
		ImageView img = (ImageView)dialogView.findViewById(R.id.b01v01TittleImg);
		
		// 加载图片
		ImageLoader.getInstance().displayImage(imgPath, img, options);
	}
	
	/**
	 * 初始化视图
	 */
	public void initDaliogView(Context context) {
		
		dialog = new Dialog(context, R.style.B01v01ImgDialogTheme);
		
		dialog.setCancelable(true);
		
		dialog.setContentView(dialogView);
	}

	public void show() {
		if(!dialog.isShowing()) {
			dialog.show();
		}
	}
	
	/**
	 * 隐藏Dialog
	 */
	public void dismiss(){
		if(dialog.isShowing()) {
			dialog.dismiss();
		}
	}
}

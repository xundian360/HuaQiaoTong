/**
 * 
 */
package com.xundian360.huaqiaotong.view.b03;

import java.io.File;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b03.B03V02Activity;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.PhotoSelectUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.view.com.SimpleListDialog;

/**
 * 帖子图片项目
 * 
 * @author TeneTeng
 * @date 2014-1-12
 * @version 1.0
 */
public class B03V02ItemImgView {

	Context context;

	View mainView;

	// 图片
	ImageView itemImg;
	// 图片名称
	TextView itemTittle;
	// 删除按钮
	ImageView itemDelete;

	// 图片路径
	String imgPath;

	// 当前视图在列表中的位置
	int index;

	SimpleListDialog operationSelect;
	String tittleName = "操作选择";
	String[] listData = new String[] { "在上边插入", "在下边插入", "移除" };

	public B03V02ItemImgView(Context context, String imgPath, int index) {

		this.context = context;
		this.imgPath = imgPath;
		this.index = index;

		// 初始化数据
		initData();

		// 初始化视图
		initView();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {

		operationSelect = new SimpleListDialog(context, onselect, tittleName,
				listData);

	}

	/**
	 * 帖子项目操作选项
	 */
	android.content.DialogInterface.OnClickListener onselect = new android.content.DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			ShowMessageUtils.show(context, "开发中...");
		}
	};

	/**
	 * 初始化视图
	 */
	private void initView() {
		mainView = LayoutInflater.from(context).inflate(
				R.layout.b03v02_item_img, null);

		itemImg = (ImageView) mainView.findViewById(R.id.b03v02ItemImg);
		itemImg.setOnClickListener(itemImgClick);
		// 设置图片
		setPic();

		itemTittle = (TextView) mainView.findViewById(R.id.b03v02ItemTittle);
		itemTittle.setText(imgPath.substring(imgPath
				.lastIndexOf(File.separatorChar)));

		itemDelete = (ImageView) mainView.findViewById(R.id.b03v02ItemDelete);
		itemDelete.setOnClickListener(itemDeleteClick);
	}

	/**
	 * 设置图片
	 */
	private void setPic() {

		Log.d("test setPic", ">>>>>>>>>>>>>>>>>>>>>1");

		Bitmap source = BitmapFactory.decodeFile(imgPath);

		Bitmap imgBitmap = PhotoSelectUtil.changePostItemImgSize(context,
				source);

		// 存储图片到SD卡
		String savePath = context.getCacheDir().getAbsolutePath()
				+ File.separatorChar + PhotoSelectUtil.getPhotoFileName();
		CommonUtil.saveBitmapInSdCard(imgBitmap, savePath);
		imgPath = savePath;

		Log.d("test setPic", ">>>>>>>>>>>>>>>>>>>>>2");

		// 设置图片
		itemImg.setImageBitmap(BitmapFactory.decodeFile(savePath));

		Log.d("test setPic", ">>>>>>>>>>>>>>>>>>>>>3");

		// 释放资源
		source.recycle();
		imgBitmap.recycle();
	}

	OnClickListener itemImgClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			operationSelect.show();
		}
	};

	/**
	 * 删除按钮
	 */
	OnClickListener itemDeleteClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			((B03V02Activity) context).removeImgFromView(index);
		}
	};

	/**
	 * 返回View
	 */
	public View get() {
		return mainView;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

}

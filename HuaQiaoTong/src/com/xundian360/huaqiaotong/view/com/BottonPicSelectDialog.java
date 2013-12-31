package com.xundian360.huaqiaotong.view.com;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

import com.xundian360.huaqiaotong.R;

/**
 * 图片选择Dialog
 * @author  TeneTeng
 * @date      2013-12-29
 * @version 1.0
 */
public class BottonPicSelectDialog extends BottomDialog {
	
	// 请求相机
	public static final int REQUEST_CAMARA_CODE = 1001;
	// 请求相册
	public static final int REQUEST_ALBAME_CODE = 1002;
	// 请求切割图片
	public static final int REQUEST_CUT_CODE = 1006;
	// 从相机返回
	public static final int RETURN_CAMARA_CODE = 1003;
	// 从相册返回
	public static final int RETURN_ALBAME_CODE = 1004;
	// 从切割图片
	public static final int RETURN_CUT_CODE = 1005;
	
	// 切割图片的大小
	private int cut_path = 200;
	
	// 创建一个以当前时间为名称的文件
	String basePath =Environment.getExternalStorageState()+ "/temple/";  
	public String cutImgPath = basePath + "cut" + getPhotoFileName() ;
    File tempFile = new File(basePath,getPhotoFileName());
    
	Context context;
	View view;
	
	public BottonPicSelectDialog(Context context) {
		super(context);
		this.context = context;
		
		// 初始化视图
		initView();
	}
	
	/**
	 * 初始化视图
	 */
	private void initView() {
		
		view = LayoutInflater.from(context).inflate(R.layout.pic_select_dialog_layout, null);
		
		// 相册选择
		Button albameSelect = (Button) view.findViewById(R.id.picSelectAlbam);
		albameSelect.setOnClickListener(albameSelectClick);
		// 相机选择
		Button camaraSelect = (Button) view.findViewById(R.id.picSelectCamara);
		camaraSelect.setOnClickListener(camaraSelectClick);
		// 取消按钮
		Button cancleSelect = (Button) view.findViewById(R.id.picSelectCancle);
		cancleSelect.setOnClickListener(cancleSelectClick);
		
		// 添加View到视图
		dialog.setContentView(view, 
				new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}
	
	/**
	 * 相册选择
	 */
	OnClickListener albameSelectClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
            ((Activity)context).startActivityForResult(intent, REQUEST_ALBAME_CODE);
            
			// 取消Dialog显示
			dismiss();
		}
	};
	
	/**
	 * 相机选择
	 */
	OnClickListener camaraSelectClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// 调用系统的拍照功能
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // 指定调用相机拍照后照片的储存路径
            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(tempFile));
            ((Activity)context).startActivityForResult(intent, REQUEST_CAMARA_CODE);
            
			// 取消Dialog显示
			dismiss();
		}
	};
	
	/**
	 * 取消按钮
	 */
	OnClickListener cancleSelectClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// 取消Dialog显示
			dismiss();
		}
	};
	
	/**
	 * 图片编辑
	 */
	public void startPhotoZoom(int requestCode, Intent data) {
		
		 switch (requestCode) {
	        case REQUEST_CAMARA_CODE:
	            startPhotoZoom(Uri.fromFile(tempFile), cut_path);
	            break;

	        case REQUEST_ALBAME_CODE:
	            if (data != null)
	                startPhotoZoom(data.getData(), cut_path);
	            break;
		 }
	}
	
	public void startPhotoZoom(Uri uri, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("return-data", true);

        ((Activity)context).startActivityForResult(intent, REQUEST_CUT_CODE);
    }
	
    /**
     * 将进行剪裁后的图片显示到UI界面上
     * @param picdata
     * @return
     */
	public Bitmap getPic(Intent picdata) {
    	Bitmap photo = null;
        Bundle bundle = picdata.getExtras();
        if (bundle != null) {
            photo = bundle.getParcelable("data");
        }
        return photo;
    }
	
	/**
	 *  使用系统当前日期加以调整作为照片的名称
	 * @return
	 */
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

}

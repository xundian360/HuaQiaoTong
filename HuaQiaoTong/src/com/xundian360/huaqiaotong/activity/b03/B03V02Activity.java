/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b03;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.view.com.SimpleListDialog;

/**
 * 帖子
 * @author  Administrator
 * @date      2013年10月23日
 * @version 1.0
 */
public class B03V02Activity extends ComNoTittleActivity {
	
	/*用来标识请求照相功能的activity*/  
    private static final int CAMERA_WITH_DATA = 3023;  
    /*用来标识请求相册的activity*/  
    private static final int PHOTO_PICKED_WITH_DATA = 3021;  
	
	// 取消
	TextView cancelBtn;
	// 提交
	TextView submitBtn;
	// 缩略图列表
	LinearLayout thumbnails;
	// 匿名发表
	CheckBox cnonymousCheck;
	// 调用相机
	ImageView callCamara;
	// 调用相册
	ImageView callPhoto;
	
	// 分类选择
	LinearLayout groupSelectBtn;
	TextView groupText;
	
	// 发送的消息Tittle
	EditText postTittle;
	
	// 发送的消息
	EditText postMsg;
	
	// 所有栏目信息
	SimpleListDialog groupListDialog;
	String[] allGroup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b03v02);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
		
		// 设置显示文字
		setEditText();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		
		allGroup = getResources().getStringArray(R.array.v03v00_nav_level_0);
		
		groupListDialog = new SimpleListDialog(this, 
				onGroupSelect, 
				getString(R.string.b03v02_group_select_tittle), 
				allGroup);
		
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		cancelBtn = (TextView) findViewById(R.id.b04v01CancelBtn);
		cancelBtn.setOnClickListener(cancelBtnClick);
		
		submitBtn = (TextView) findViewById(R.id.b04v01SubmitBtn);
		submitBtn.setOnClickListener(submitBtnClick);
		
		thumbnails = (LinearLayout) findViewById(R.id.b03v02Thumbnails);
		
		cnonymousCheck = (CheckBox) findViewById(R.id.b03v02AnonymousCheck);
		
		callCamara = (ImageView) findViewById(R.id.b03v02CallCamara);
		callCamara.setOnClickListener(callCamaraClick);
		
		callPhoto = (ImageView) findViewById(R.id.b03v02CallPhoto);
		callPhoto.setOnClickListener(callPhotoClick);
		
		groupSelectBtn = (LinearLayout) findViewById(R.id.b03v02GroupSelectBtn);
		groupSelectBtn.setOnClickListener(groupSelectBtnClick);
		
		groupText = (TextView) findViewById(R.id.b03v02GroupTittle);
		
		postMsg = (EditText) findViewById(R.id.b03v02PostsMsg);
		
		postTittle = (EditText) findViewById(R.id.b03v02PostsTittle);
	}
	
	/**
	 * 设置显示文字
	 */
	private void setEditText() {
		
		String text1 = "\n您目前是通过GPS获得的精确位置信息您目前是通过"
				+ "GPS获得的精确位置信息您目前是通过GPS获得的精确位置信"
				+ "您目前是通过GPS获得的精确位置信息息您目前是通过GPS获得的精确位置信息\n";
		
		Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        SpannableString spannable = new SpannableString(text1 + "[smile]" + text1);

        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);

        spannable.setSpan(span, text1.length(), 
        		text1.length()+"[smile]".length(), 
        		Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
         
         postMsg.setText(spannable);
	}
	
	/**
	 * 分组选择
	 */
	DialogInterface.OnClickListener onGroupSelect = new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// 设置选中的标题
			groupText.setText(allGroup[which]);
		}
	};
	
	/**
	 * 取消
	 */
	OnClickListener cancelBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			onBackPressed();
		}
	};
	
	/**
	 * 提交
	 */
	OnClickListener submitBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
		}
	};
	
	/**
	 * 调用相机
	 */
	OnClickListener callCamaraClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i = new Intent("android.media.action.IMAGE_CAPTURE");    
            startActivityForResult(i, CAMERA_WITH_DATA);  
		}
	};
	
	/**
	 * 调用相册
	 */
	OnClickListener callPhotoClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
			openAlbumIntent.setType("image/*");
			startActivityForResult(openAlbumIntent, PHOTO_PICKED_WITH_DATA);
		}
	};
	
	/**
	 * 栏目选择
	 */
	OnClickListener groupSelectBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			groupListDialog.show();
		}
	};
    
	/**
	 * Activity返回
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			
			case CAMERA_WITH_DATA:  
                //将保存在本地的图片取出并缩小后显示在界面上  
//                Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/image.jpg");  
				 Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
                
                ImageView img1 = new ImageView(this);
                img1.setImageBitmap(bitmap);
                
                thumbnails.addView(img1);
                
                break; 
			
			case PHOTO_PICKED_WITH_DATA:
				ContentResolver resolver = getContentResolver();
				//照片的原始资源地址
				Uri originalUri = data.getData();
//	            try {
	            	//使用ContentProvider通过URI获取原始图片
//					Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
	            	 Bitmap photo = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
					
	                ImageView img2 = new ImageView(this);
	                img2.setImageBitmap(photo);
	                
	                thumbnails.addView(img2);
//					
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}  
				break;
			
			default:
				break;
			}
		}
		
	}
}

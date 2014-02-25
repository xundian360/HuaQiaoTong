/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b03;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestCallBack;
import com.lidroid.xutils.http.ResponseInfo;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.modle.b03.PostGroup;
import com.xundian360.huaqiaotong.util.BaiduUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.StringUtils;
import com.xundian360.huaqiaotong.util.UserUtils;
import com.xundian360.huaqiaotong.util.b03.B03v00Util;
import com.xundian360.huaqiaotong.view.b03.B03V02ItemImgView;
import com.xundian360.huaqiaotong.view.com.CommonProgressDialog;
import com.xundian360.huaqiaotong.view.com.SimpleListDialog;

/**
 * 帖子
 * 
 * @author Administrator
 * @date 2013年10月23日
 * @version 1.0
 */
public class B03V02Activity extends ComNoTittleActivity {

	/* 用来标识请求照相功能的activity */
	private static final int CAMERA_WITH_DATA = 3023;
	/* 用来标识请求相册的activity */
	private static final int PHOTO_PICKED_WITH_DATA = 3021;

	// 取消
	ImageButton cancelBtn;
	// 提交
	TextView submitBtn;
	// 调用相机
	ImageView callCamara;
	// 调用相册
	ImageView callPhoto;

	// 分类选择
	LinearLayout groupSelectBtn;
	TextView groupText;

	// 发送的消息Tittle
	EditText postTittle;

	// 消息列表
	LinearLayout postsMsgs;

	// 默认消息输入框
	EditText defaultPostMsg;

	// 所有栏目信息
	SimpleListDialog groupListDialog;
	List<PostGroup> allGroup = new ArrayList<PostGroup>();
	String[] allGroupNames;

	PostGroup selectGroup;

	List<String> picPaths = new ArrayList<String>();
	private Uri photoUri;
	private String picPath = null;

	int index = 0;

	// 进度条
	CommonProgressDialog processDialog;

	List<B03V02ItemImgView> imgItemViews = new ArrayList<B03V02ItemImgView>();

	LinearLayout.LayoutParams imgItemParams = new LinearLayout.LayoutParams(
			LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.b03v02);

		// 初始化数据
		initData();

		// 初始化组件
		initModule();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {

		imgItemParams.topMargin = 5;
		imgItemParams.bottomMargin = 5;

		// 设置帖子分类
		setPostGroups();

		// 设置数据源
		groupListDialog = new SimpleListDialog(this, onGroupSelect,
				getString(R.string.b03v02_group_select_tittle), allGroupNames);

		// 进度条
		processDialog = new CommonProgressDialog(this);
	}

	/**
	 * 设置帖子分类
	 */
	private void setPostGroups() {

		allGroupNames = new String[B03v00Util.GROUP_NAMES.length];

		for (int i = 0; i < B03v00Util.GROUP_NAMES.length; i++) {

			String groupId = getString(B03v00Util.GROUP_KEYS[i]);
			String groupName = getString(B03v00Util.GROUP_NAMES[i]);

			allGroupNames[i] = groupName;

			allGroup.add(new PostGroup(groupId, groupName));
		}
	}

	/**
	 * 初始化组件
	 */
	private void initModule() {

		cancelBtn = (ImageButton) findViewById(R.id.b04v01CancelBtn);
		cancelBtn.setOnClickListener(cancelBtnClick);

		submitBtn = (TextView) findViewById(R.id.b04v01SubmitBtn);
		submitBtn.setOnClickListener(submitBtnClick);

		callCamara = (ImageView) findViewById(R.id.b03v02CallCamara);
		callCamara.setOnClickListener(callCamaraClick);

		callPhoto = (ImageView) findViewById(R.id.b03v02CallPhoto);
		callPhoto.setOnClickListener(callPhotoClick);

		groupSelectBtn = (LinearLayout) findViewById(R.id.b03v02GroupSelectBtn);
		groupSelectBtn.setOnClickListener(groupSelectBtnClick);

		groupText = (TextView) findViewById(R.id.b03v02GroupTittle);
		setSelectGroup(0);

		postsMsgs = (LinearLayout) findViewById(R.id.b03v02PostsMsgs);

		defaultPostMsg = (EditText) findViewById(R.id.b03v02PostsMsgDefault);

		postTittle = (EditText) findViewById(R.id.b03v02PostsTittle);
	}

	/**
	 * 分组选择
	 */
	DialogInterface.OnClickListener onGroupSelect = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// 设置选中的标题
			setSelectGroup(which);
		}
	};

	/**
	 * 设置选择Dialog
	 * 
	 * @param which
	 */
	private void setSelectGroup(int which) {
		// 设置选中的标题
		groupText.setText(allGroupNames[which]);
		selectGroup = allGroup.get(which);
	}

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

			// 显示上传Dialog
			processDialog.show();

			// 发送帖子
			sendPosts();
		}
	};

	/**
	 * 发送帖子
	 */
	private void sendPosts() {

		String userId = UserUtils.getUserId(this);
		String groupId = selectGroup.getGroupId();
		String themeTitle = postTittle.getText().toString();
		StringBuffer themeMessage = new StringBuffer();
		List<String> fileKeys = new ArrayList<String>();
		List<String> filePaths = new ArrayList<String>();

		themeMessage.append("{\"pl_sub\": [");

		// 编辑上传数据
		int childView = postsMsgs.getChildCount();
		for (int i = 0; i < childView; i++) {

			themeMessage.append("{");

			View itemView = postsMsgs.getChildAt(i);

			// 图片
			if (itemView.getClass() == RelativeLayout.class) {
				themeMessage.append("\"img\":\"");

				// 取得图片路径
				for (B03V02ItemImgView itemImgView : imgItemViews) {

					// 比较在内存中的位置
					if (itemView == itemImgView.get()) {

						String imgpathString = itemImgView.getImgPath();

						themeMessage.append(imgpathString);
						filePaths.add(imgpathString);
						fileKeys.add(imgpathString.substring(
								imgpathString.lastIndexOf(File.separatorChar) + 1,
								imgpathString.lastIndexOf(".")));

						break;
					}
				}

				themeMessage.append("");
			}

			// 文本
			else if (itemView.getClass() == EditText.class) {

				EditText editeViewTemp = (EditText) itemView;

				themeMessage.append("\"txt\":\"");
				themeMessage.append(editeViewTemp.getText().toString());
			}

			themeMessage.append("\"},");
		}

		themeMessage = new StringBuffer(themeMessage.subSequence(0,
				themeMessage.length() - 1));
		themeMessage.append("]}");

		// 上传图片
		B03v00Util
				.sendPosts(this, userId, groupId, themeTitle,
						themeMessage.toString(), sendPostsCallBack, fileKeys,
						filePaths);
	}

	/**
	 * 上传回调
	 */
	RequestCallBack<String> sendPostsCallBack = new RequestCallBack<String>() {

		@Override
		public void onSuccess(ResponseInfo<String> arg0) {

			// 取消Dialog显示
			processDialog.dismiss();

			String result = arg0.result;

			// 发表成功
			if (BaiduUtil.STATUS_OK_KEY.equals(result)) {
				ShowMessageUtils.show(B03V02Activity.this,
						R.string.b03v02_send_success_text);
				finish();
			} else {
				ShowMessageUtils.show(B03V02Activity.this,
						R.string.b03v02_send_success_text);
			}
		}

		@Override
		public void onFailure(HttpException arg0, String arg1) {

			ShowMessageUtils.show(B03V02Activity.this,
					R.string.b03v02_send_success_text);

			// 取消Dialog显示
			processDialog.dismiss();
		}
	};

	/**
	 * 调用相机
	 */
	OnClickListener callCamaraClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// "android.media.action.IMAGE_CAPTURE"
			/***
			 * 以下操作使用照相机拍照，拍照后的图片会存放在相册中的 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图
			 * 如果不实用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰
			 */
			ContentValues values = new ContentValues();
			photoUri = getContentResolver().insert(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
			intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
			startActivityForResult(intent, CAMERA_WITH_DATA);
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

		// 正常返回
		if (resultCode == RESULT_OK) {

			// 获取图片的路径
			doPhoto(requestCode, data);
		}
	}

	/**
	 * 选择图片后，获取图片的路径
	 * 
	 * @param requestCode
	 * @param data
	 */
	private void doPhoto(int requestCode, Intent data) {
		// 从相册取图片，有些手机有异常情况，请注意
		if (requestCode == PHOTO_PICKED_WITH_DATA) {
			if (data == null) {
				ShowMessageUtils.show(this, "选择图片文件出错");
				return;
			}
			photoUri = data.getData();
			if (photoUri == null) {
				ShowMessageUtils.show(this, "选择图片文件出错");
				return;
			}
		}

		// Log.d("test", ">>>>>>>>>>>>>>>>>>>>>1");

		String[] pojo = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(photoUri, pojo, null, null, null);
		if (cursor != null) {
			int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
			cursor.moveToFirst();
			picPath = cursor.getString(columnIndex);
			try {
				// 4.0以上的版本会自动关闭 (4.0--14;; 4.0.3--15)
				if (Integer.parseInt(Build.VERSION.SDK) < 14) {
					cursor.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (picPath != null
				&& (picPath.endsWith(".png") || picPath.endsWith(".PNG")
						|| picPath.endsWith(".jpg") || picPath.endsWith(".JPG"))) {

			// Log.d("test", ">>>>>>>>>>>>>>>>>>>>>2");

			// 添加图片到图片列表
			picPaths.add(picPath);

			// 添加View到视图
			addImgToView(picPath);

			// Log.d("test", ">>>>>>>>>>>>>>>>>>>>>3");

		} else {
			ShowMessageUtils.show(this, "选择图片文件不正确");
		}
	}

	/**
	 * 添加View到视图
	 */
	private void addImgToView(String imgPath) {

		// Log.d("test", ">>>>>>>>>>>>>>>>>>>>>1");

		index++;
		B03V02ItemImgView itemView = new B03V02ItemImgView(this, imgPath, index);
		postsMsgs.addView(itemView.get(), imgItemParams);
		imgItemViews.add(itemView);

		// Log.d("test", ">>>>>>>>>>>>>>>>>>>>>2");

		// 图片上一个View判断
		View preView = postsMsgs.getChildAt(postsMsgs.getChildCount() - 2);

		if (preView.getClass() == EditText.class) {

			EditText editeViewTemp = (EditText) preView;

			if (StringUtils.isBlank(editeViewTemp.getText().toString())) {
				postsMsgs.removeViewAt(postsMsgs.getChildCount() - 2);
			}
		}

		// 添加输入框
		EditText editeView = (EditText) LayoutInflater.from(this).inflate(
				R.layout.b03v02_item_txt, null);
		postsMsgs.addView(editeView);

		editeView.requestFocus();
	}

	/**
	 * 移除View到视图
	 */
	public void removeImgFromView(int index) {
		ShowMessageUtils.show(this, "开发中...");
	}
}

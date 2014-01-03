/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b04;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestCallBack;
import com.lidroid.xutils.http.ResponseInfo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.modle.com.User;
import com.xundian360.huaqiaotong.modle.com.UserModle;
import com.xundian360.huaqiaotong.util.BaseHttpClient;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.StringUtils;
import com.xundian360.huaqiaotong.util.UserUtils;
import com.xundian360.huaqiaotong.view.com.BottonPicSelectDialog;
import com.xundian360.huaqiaotong.view.com.CommonProgressDialog;

/**
 * 查看（编辑）个人信息
 * @author  Administrator
 * @date      2013年10月23日
 * @version 1.0
 */
public class B04V04Activity extends ComNoTittleActivity {
	
	// 用户LOGO
	ImageView userLogo;
	// 用户LOGO Layout
	LinearLayout userLogoLayout;
	
	ImageView userArrow;
	TextView userLogoText;
	
	// 返回按钮
	ImageButton retBtn;
	// 修改按钮
	TextView editeOrComBtn;
	// 昵称
	EditText name;
	// 性别
	EditText sex;
	RadioGroup userSexRadio;
	
	// 所在地
	EditText location;
	// 简介
	EditText disc;
	// QQ
	EditText qq;
	
	// 用户存储类
	UserModle userModle;

	// 图片选择Dialog
	BottonPicSelectDialog picSelectDialog;
	
	// 进度条
	CommonProgressDialog processDialog;
	
	Handler _handler = new Handler();
	
	// 图片缓存
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.b03v00_left_user_logo)
			.showImageForEmptyUri(R.drawable.b03v00_left_user_logo)
			.showImageOnFail(R.drawable.b03v00_left_user_logo)
			.cacheOnDisc(true)
			.build();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b04v04);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		userModle = new UserModle(this);
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		userLogo = (ImageView) findViewById(R.id.b03v00UserLogo);
		ImageLoader.getInstance().displayImage(
				userModle.user.getLogoPath().replace(UserUtils.USER_ICON_200, UserUtils.USER_ICON_100), 
				userLogo, options);
		
		userArrow = (ImageView) findViewById(R.id.b03v00UserLogoArrow);
		
		userLogoText =  (TextView) findViewById(R.id.b03v00UserLogoText);
		userLogoText.setText(userModle.user.getName());
		
		userLogoLayout = (LinearLayout) findViewById(R.id.b04v04ChangeUserLogo);
		userLogoLayout.setOnClickListener(userLogoLayoutClick);
		
		retBtn = (ImageButton) findViewById(R.id.b04v04CancelBtn);
		retBtn.setOnClickListener(retBtnClick);
		
		editeOrComBtn = (TextView) findViewById(R.id.b04v04EditeBtn);
		editeOrComBtn.setOnClickListener(editeOrComBtnClick);
		
		name = (EditText) findViewById(R.id.b04v04Name);
		name.setText(userModle.user.getName());
		
		sex = (EditText) findViewById(R.id.b04v04Sex);
		if(User.SEX_NAN == userModle.user.getSex()) {
			sex.setText(User.SEX_NAN_TEXT);
		} else {
			sex.setText(User.SEX_NV_TEXT);
		}
		
		userSexRadio = (RadioGroup) findViewById(R.id.b04v04UserSex);
		
		location = (EditText) findViewById(R.id.b04v04Location);
		location.setText(userModle.user.getLocation());
		
		disc = (EditText) findViewById(R.id.b04v04Thu);
		disc.setText(userModle.user.getDisc());
		
		qq = (EditText) findViewById(R.id.b04v04Qq);
		qq.setText(userModle.user.getQq());
		
		// 设置不可编辑
		setCanEdite(false);
	}
	
	/**
	 * 取消
	 */
	OnClickListener retBtnClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			onBackPressed();
		}
	};
	
	/**
	 * 修改（完成）
	 */
	OnClickListener editeOrComBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			// 修改状态
			if(name.isEnabled()) {
				
				// 设置按钮为“修改”
				editeOrComBtn.setText(R.string.b04v04_tittle_editet_text);
				
				// 更新用户信息
				updateUser();
				
			} else {		// 非修改状态
				
				// 设置按钮为“完成”
				editeOrComBtn.setText(R.string.b04v01_tittle_complete);
				
				// 设置EditeView为不可编辑状态
				setCanEdite(true);
				
				// 获得焦点
				name.findFocus();
				name.setSelection(name.getText().toString().length());
				// 打开键盘
				CommonUtil.showInput(B04V04Activity.this);
			}
		}
	};
	
	/**
	 * 更新用户信息
	 */
	private void updateUser() {
		
		// 输入有效性校验
		if(!inputCheck()) {
			return;
		}
		
		// 判断用户信息是否修改
		if(!userInfoIsModify()) {
			return;
		}
		
		// 显示Dialog
		if(processDialog == null){
			processDialog = new CommonProgressDialog(this);
		}
		processDialog.show();
		
		// 开启上传线程
		updateUserRun();
	}
	
	/**
	 * 上传数据线程
	 */
	private void updateUserRun() {
		
		String url = getString(R.string.update_user_url);
		
		// 设置参数
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("user_id", userModle.user.getUserId());
		params.put("user_login", userModle.user.getLoginName());
		params.put("user_name", name.getText().toString());
		params.put("user_qq", qq.getText().toString());
		params.put("user_city", location.getText().toString());
		params.put("user_des", disc.getText().toString());
		
		// 设置性别
		int checkedId = userSexRadio.getCheckedRadioButtonId();
		int sexV = 0;
		if(checkedId == R.id.b04v04UserNan) {
			sexV = User.SEX_NAN;
		} else {
			sexV = User.SEX_NV;
		}
		params.put("user_sex", sexV + "");
		
		// 设置图片参数
		String[] fileKeys = null;
		String[] filePaths = null;
		
		if(picSelectDialog != null && new File(picSelectDialog.cutImgPath).exists()) {
			fileKeys = new String[] {"img"};
			filePaths = new String[] {picSelectDialog.cutImgPath};
		}
		
		BaseHttpClient.doPostRequestWithFile(url, params, fileKeys, filePaths, callBack);
	};
	
	/**
	 * 上传回调
	 */
	RequestCallBack<String> callBack = new RequestCallBack<String>() {
		
		@Override
		public void onSuccess(ResponseInfo<String> arg0) {
			// 设置EditeView为可编辑状态
			setCanEdite(false);
			
			// 设置数据
			User user = UserUtils.setUser(arg0.result);
			
			if(user != null) {
				// 保存修改内容
				userModle.user = user;
				userModle.save();
				
				ShowMessageUtils.show(B04V04Activity.this, R.string.b04v04_update_success_text);
				
				// 取消Dialog显示
				processDialog.dismiss();
			} else {
				
			}
		}
		
		@Override
		public void onFailure(HttpException arg0, String arg1) {
			ShowMessageUtils.show(B04V04Activity.this, R.string.b04v04_update_error_text);
			
			// 取消Dialog显示
		    processDialog.dismiss();
		}
	};
	
	/**
	 * 输入有效性校验
	 * @return
	 */
	private boolean inputCheck() {
		
		// 用户名
		if(StringUtils.isBlank(name.getText().toString())) {
			ShowMessageUtils.show(this, R.string.b04v01_msg_login_nike_null);
			return false;
		}
		return true;
	}
	
	/**
	 * 判断用户信息是否修改
	 * @return
	 */
	private boolean userInfoIsModify() {
		
		if(picSelectDialog != null && new File(picSelectDialog.cutImgPath).exists()) {
			return true;
		}
		
		userModle.read();
		
		if(!name.getText().toString().equals(userModle.user.getName())) {
			return true;
		}
		
		String sexText = sex.getText().toString();
		int sexV = 0;
				
		if(User.SEX_NAN_TEXT.equals(sexText)) {
			sexV = User.SEX_NAN;
		} 
		
		else if(User.SEX_NV_TEXT.equals(sexText)) {
			sexV = User.SEX_NV;
		}
		
		if(sexV !=userModle.user.getSex()) {
			return true;
		}
		
		if(!location.getText().toString().equals(userModle.user.getLocation())) {
			return true;
		}
		
		if(!disc.getText().toString().equals(userModle.user.getDisc())) {
			return true;
		}
		
		if(!qq.getText().toString().equals(userModle.user.getQq())) {
			return true;
		}
		
		return true;
	}
	
	
	
	/**
	 * 用户图片选择事件
	 */
	OnClickListener userLogoLayoutClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			picSelectDialog = new BottonPicSelectDialog(B04V04Activity.this);
			picSelectDialog.show();
		}
	};
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		// 正常返回
		if(resultCode == RESULT_OK) {
			
			// 图片选择
			if(requestCode == BottonPicSelectDialog.REQUEST_CAMARA_CODE 
					||requestCode == BottonPicSelectDialog.REQUEST_ALBAME_CODE) {
				picSelectDialog.startPhotoZoom(requestCode, data);
			}
			
			// 切割图片
			if(requestCode == BottonPicSelectDialog.REQUEST_CUT_CODE){
				Bitmap pic = picSelectDialog.getPic(data);
				// 设置图片
				userLogo.setImageBitmap(pic);
				// 存储到SD卡
				CommonUtil.saveBitmapInSdCard(pic, picSelectDialog.cutImgPath);
			}
		}
	}
	
	/**
	 * 设置是否可以编辑
	 * @param canEdite
	 */
	private void setCanEdite(boolean canEdite) {
		
//		loginName.setEnabled(canEdite);
		userLogoLayout.setClickable(canEdite);
		name.setEnabled(canEdite);
		// sex.setEnabled(canEdite);
		location.setEnabled(canEdite);
		disc.setEnabled(canEdite);
		qq.setEnabled(canEdite);
		
		if(canEdite) {
			userArrow.setVisibility(View.VISIBLE);
			userSexRadio.setVisibility(View.VISIBLE);
			sex.setVisibility(View.GONE);
		} else {
			userArrow.setVisibility(View.GONE);
			userSexRadio.setVisibility(View.GONE);
			sex.setVisibility(View.VISIBLE);
		}
	}
	
}

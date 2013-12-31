/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b04;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.modle.com.SettingModle;
import com.xundian360.huaqiaotong.modle.com.User;
import com.xundian360.huaqiaotong.modle.com.UserModle;
import com.xundian360.huaqiaotong.util.BaiduUtil;
import com.xundian360.huaqiaotong.util.BaseHttpClient;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.StringUtils;
import com.xundian360.huaqiaotong.util.UserUtils;
import com.xundian360.huaqiaotong.view.com.CommonProgressDialog;

/**
 * 注册
 * @author  Administrator
 * @date      2013年10月23日
 * @version 1.0
 */
public class B04V01Activity extends ComNoTittleActivity {
	
	// 取消
	ImageButton cancelBtn;
	// 用户名
	EditText userName;
	// 密码
	EditText userPass;
	// 昵称
	EditText userNikeName;
	// QQ
	EditText userQq;
	// 性别选择
	RadioGroup sexView;
	
	// 注册按钮
	ImageView signBtn;
	
	// 用户存储类
	UserModle userModle;
	
	int sexV = User.SEX_NAN;
	
	// 进度条
	CommonProgressDialog processDialog;
	
	Handler _handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b04v01);
		
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
		processDialog = new CommonProgressDialog(this);
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		cancelBtn = (ImageButton) findViewById(R.id.b04v01RetBtn);
		cancelBtn.setOnClickListener(cancelBtnClick);
		
		userName = (EditText) findViewById(R.id.b04v01UserName);
		
		userPass = (EditText) findViewById(R.id.b04v01UserPas);
		
		userNikeName = (EditText) findViewById(R.id.b04v01UserNickName);
		
		userQq = (EditText) findViewById(R.id.b04v01UserQQ);
		
		sexView = (RadioGroup) findViewById(R.id.b04v01UserSex);
		sexView.setOnCheckedChangeListener(sexCheck);
		
		signBtn = (ImageView) findViewById(R.id.b04v01SignBtn);
		signBtn.setOnClickListener(signBtnClick);
		
	}
	
	/**
	 * 性别选择监听
	 */
	OnCheckedChangeListener sexCheck = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			if(checkedId == R.id.b04v01UserNan) {
				sexV = User.SEX_NAN;
			} else {
				sexV = User.SEX_NV;
			}
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
	 * 注册成功
	 */
	OnClickListener signBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
        	// 注册
			sign();
		}
	};
	
	/**
	 * 注册
	 */
	private void sign() {
    	
		String userNameText = userName.getText().toString();
		String userPassText = userPass.getText().toString();
		String userNikeText = userNikeName.getText().toString();
		String userQqText = userQq.getText().toString();
		
		// 用户名检验
    	if(StringUtils.isBlank(userNameText)) {
    		ShowMessageUtils.show(this, R.string.b04v01_msg_login_name_null);
    		return;
    	}
	    
    	// 密码校验
    	else if(StringUtils.isBlank(userPassText)) {
    		ShowMessageUtils.show(this, R.string.b04v01_msg_login_pass_null);
    		return;
    	}
    	
    	else if(StringUtils.isBlank(userNikeText)) {
    		ShowMessageUtils.show(this, R.string.b04v01_msg_login_nike_null);
    		return;
    	}
    	
    	else if(StringUtils.isBlank(userQqText)) {
    		ShowMessageUtils.show(this, R.string.b04v01_msg_login_qq_null);
    		return;
    	}
    	
    	// 显示Dialog
    	processDialog.show();
    	
    	// 注册
    	new Thread(registrationRun).start();
	}
	
	/**
	 * 注册
	 */
	Runnable registrationRun = new Runnable() {
		
		String errorMsg;
		
		@Override
		public void run() {
			
			try{
				String userNameText = userName.getText().toString();
				String userPassText = userPass.getText().toString();
				String userNikeText = userNikeName.getText().toString();
				String userQqText = userQq.getText().toString();
				
				// 推送ID
				SettingModle settingModle = new SettingModle(B04V01Activity.this);
				settingModle.read();
				
				String push_id = settingModle.getPushAlias();
				
				// 登陆URL
				String loginUrl = getString(R.string.registration_url);
				
				// 登陆参数
				Map<String, String> params = new HashMap<String, String>();
				params.put("user_name", userNikeText);
				params.put("user_login", userNameText);
				params.put("user_m", userPassText);
				params.put("user_mail", userNikeText);
				params.put("user_qq", userQqText);
				params.put("user_sex", sexV + "");
				params.put("push_id", push_id);
				
				// 设置蚕食
				final String userJson = BaseHttpClient.doPostRequest(loginUrl, params);
				
				if(StringUtils.isBlank(userJson) || BaiduUtil.STATUS_ERROR_KEY.equals(userJson)) {
					// 登陆失败
					errorMsg = getString(R.string.b04v01_msg_login_error);
				} else {
					// 注册成功
					_handler.post(new Runnable() {

						@Override
						public void run() {
							// 设置参数
							userModle.read();
							
							// 设置数据
							User user = UserUtils.setUser(userJson);
							
							if(user != null) {
								userModle.user = user;
								userModle.save();
								// 取消页面显示
								finish();
								
								// 个人中心页面迁移
								CommonUtil.startActivityForResult(B04V01Activity.this, B04V03Activity.class, 100);
							} else {
								errorMsg = getString(R.string.b04v01_msg_login_error);
							}
						}
					});
				}
				
			} catch(Exception e) {
				e.printStackTrace();
				errorMsg = getString(R.string.b04v01_msg_login_error);
			} finally {
				
				// 取消Dialog显示
				_handler.post(new Runnable() {
					
					@Override
					public void run() {
						processDialog.dismiss();
						// 注册失败
						if(StringUtils.isNotBlank(errorMsg)) {
							ShowMessageUtils.show(B04V01Activity.this, errorMsg);
						} else {
							// 成功
							ShowMessageUtils.show(B04V01Activity.this, R.string.b04v01_msg_sign_success);
							// 取消显示
							finish();
						}
					}
				});
			}
			}
	};
}

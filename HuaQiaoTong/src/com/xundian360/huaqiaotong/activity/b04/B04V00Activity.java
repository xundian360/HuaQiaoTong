/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b04;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.modle.com.UserModle;
import com.xundian360.huaqiaotong.util.BaiduUtil;
import com.xundian360.huaqiaotong.util.BaseHttpClient;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.StringUtils;
import com.xundian360.huaqiaotong.view.com.CommonProgressDialog;

/**
 * 登陆
 * @author  Administrator
 * @date      2013年10月23日
 * @version 1.0
 */
public class B04V00Activity extends ComNoTittleActivity {
	
	// 用户名
	EditText userName;
	// 密码
	EditText userPass;
	// 登陆
	TextView loginBtn;
	// 注册
	TextView signBtn;
	// QQ登陆
	ImageView qqLoginBtn;
	// 微博登陆
	ImageView wbLoginBtn;
	
	// 用户存储类
	UserModle userModle;
	
	// 进度条
	CommonProgressDialog processDialog;
	
	Handler _handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b04v00);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
	}
	
	@Override
	protected void onStart() {
		userModle.read();
		super.onStart();
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
		
		userName = (EditText) findViewById(R.id.b04v00UserName);
		
		userPass = (EditText) findViewById(R.id.b04v00UserPas);
		userPass.setOnEditorActionListener(pwActionListener);
		
		loginBtn = (TextView) findViewById(R.id.b04v01LoginBtn);
		loginBtn.setOnClickListener(loginBtnClick);
		
		signBtn = (TextView) findViewById(R.id.b04v01SignBtn);
		signBtn.setOnClickListener(signBtnClick);
		
		qqLoginBtn = (ImageView) findViewById(R.id.b04v01QqLogin);
		qqLoginBtn.setOnClickListener(qqLoginBtnClick);
		
		wbLoginBtn = (ImageView) findViewById(R.id.b04v01WbLogin);
		wbLoginBtn.setOnClickListener(wbLoginBtnClick);
	}
	
	/**
	 * 登陆
	 */
	OnClickListener loginBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			// 登陆
        	login();
		}
	};
	
	/**
	 * 注册
	 */
	OnClickListener signBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			CommonUtil.startActivityForResult(B04V00Activity.this, B04V01Activity.class, 100);
		}
	};
	
	/**
	 * QQ登陆
	 */
	OnClickListener qqLoginBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	};
	
	/**
	 * 微博登陆
	 */
	OnClickListener wbLoginBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	};
	
	/**
	 * 键盘按键监听
	 */
	EditText.OnEditorActionListener pwActionListener = new EditText.OnEditorActionListener() {

		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
	        // 完成按钮
			if (actionId == EditorInfo.IME_ACTION_DONE) {
	        	// 登陆
	        	login();
	            return true;    
	        }  
	        return false;  
		}
	};
	
	/**
	 * 登陆
	 */
	private void login() {
		
		String userNameText = userName.getText().toString();
		String userPassText = userPass.getText().toString();
		
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
    	
    	// 显示Dialog
    	processDialog.show();
    	
    	// 登陆
    	new Thread(loginRun).start();
	}
	
	/**
	 * 登陆线程
	 */
	Runnable loginRun = new Runnable() {
		
		String errorMsg;
		
		@Override
		public void run() {
			
			try{
				// 登陆URL
				String loginUrl = getString(R.string.login_url);
				final String userNameText = userName.getText().toString();
				
				// 登陆参数
				Map<String, String> params = new HashMap<String, String>();
				params.put("user_login", userNameText);
				params.put("user_m", userPass.getText().toString());
				
				// 设置蚕食
				final String userId = BaseHttpClient.doPostRequest(loginUrl, params);
				
				if(StringUtils.isBlank(userId) || BaiduUtil.STATUS_ERROR_KEY.equals(userId)) {
					// 登陆失败
					errorMsg = getString(R.string.b04v01_msg_login_error);
				} else {
					// 登陆成功
					_handler.post(new Runnable() {
						
						@Override
						public void run() {
							// 设置参数
							userModle.read();
							userModle.user.setUserId(userId);
							userModle.user.setName(userNameText);
							userModle.save();
							
							// 个人中心
				    		CommonUtil.startActivityForResult(B04V00Activity.this, B04V03Activity.class, 100);
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
						// 登陆失败
						if(StringUtils.isNotBlank(errorMsg)) {
							ShowMessageUtils.show(B04V00Activity.this, errorMsg);
						}
					}
				});
			}
		}
	};

}

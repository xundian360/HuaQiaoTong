/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b04;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.modle.com.UserModle;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.StringUtils;

/**
 * 注册
 * @author  Administrator
 * @date      2013年10月23日
 * @version 1.0
 */
public class B04V01Activity extends ComNoTittleActivity {
	
	// 取消
	TextView cancelBtn;
	// 用户名
	EditText userName;
	// 密码
	EditText userPass;
	
	// 注册按钮
	Button signBtn;
	// 同意协议
	CheckBox allowProtocol;
	
	// 用户存储类
	UserModle userModle;
	
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
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		cancelBtn = (TextView) findViewById(R.id.b04v01CancelBtn);
		cancelBtn.setOnClickListener(cancelBtnClick);
		
		userName = (EditText) findViewById(R.id.b04v01UserName);
		userPass = (EditText) findViewById(R.id.b04v01UserPas);
		userPass.setOnEditorActionListener(passwordActionListener);
		
		signBtn = (Button) findViewById(R.id.b04v01SignBtn);
		signBtn.setOnClickListener(signBtnClick);
		
		allowProtocol = (CheckBox) findViewById(R.id.b04v01AllowProtocol);
		
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
	 * 键盘按键监听
	 */
	EditText.OnEditorActionListener passwordActionListener = new EditText.OnEditorActionListener() {

		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
	        // 完成按钮
			if (actionId == EditorInfo.IME_ACTION_DONE) {
	        	// 注册
				sign();
	            return true;    
	        }  
	        return false;  
		}
	};
	
	/**
	 * 注册
	 */
	private void sign() {
    	
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
    	
    	// 注册
    	userModle.user.setLoginName(userNameText);
    	ShowMessageUtils.show(this, R.string.b04v01_msg_sign_success);
    	
    	// 存储用户信息
    	userModle.save();
    	
    	// 个人中心
		CommonUtil.startActivityForResult(B04V01Activity.this, B04V03Activity.class, 100);
	}
}

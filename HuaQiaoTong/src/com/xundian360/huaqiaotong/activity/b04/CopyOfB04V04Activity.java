/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b04;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.modle.com.UserModle;

/**
 * 查看（编辑）个人信息
 * @author  Administrator
 * @date      2013年10月23日
 * @version 1.0
 */
public class CopyOfB04V04Activity extends ComNoTittleActivity {
	
	// 返回按钮
	TextView retBtn;
	// 修改按钮
	TextView editeOrComBtn;
	// 登录名
	EditText loginName;
	// 昵称
	EditText name;
	// 性别
	EditText sex;
	// 所在地
	EditText location;
	// 简介
	EditText disc;
	// QQ
	EditText qq;
	// 公司
	EditText company;
	
	// Lable(基本信息时显示)
	TextView baseInfoLable;
	TextView companyLable;
	
	// 修改图像Layout
	LinearLayout changeUserLogo;
	
	// 用户存储类
	UserModle userModle;
	
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
		
		retBtn = (TextView) findViewById(R.id.b04v04CancelBtn);
		retBtn.setOnClickListener(retBtnClick);
		
		editeOrComBtn = (TextView) findViewById(R.id.b04v04EditeBtn);
		editeOrComBtn.setOnClickListener(editeOrComBtnClick);
		
		loginName = (EditText) findViewById(R.id.b04v04LoginName);
		loginName.setText(userModle.user.getLoginName());
		
		name = (EditText) findViewById(R.id.b04v04Name);
		name.setText(userModle.user.getName());
		
		sex = (EditText) findViewById(R.id.b04v04Sex);
//		sex.setText(userModle.user.getSex());
		
		location = (EditText) findViewById(R.id.b04v04Location);
		location.setText(userModle.user.getLocation());
		
		disc = (EditText) findViewById(R.id.b04v04Thu);
		disc.setText(userModle.user.getDisc());
		
		qq = (EditText) findViewById(R.id.b04v04Qq);
		qq.setText(userModle.user.getQq());
		
		company = (EditText) findViewById(R.id.b04v04Company);
		company.setText(userModle.user.getCompany());
		
		baseInfoLable = (TextView) findViewById(R.id.b04v04BaseInfoLable);
		companyLable = (TextView) findViewById(R.id.b04v04CompanyLable);
		
		changeUserLogo = (LinearLayout) findViewById(R.id.b04v04ChangeUserLogo);
		
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
			if(changeUserLogo.getVisibility() == View.VISIBLE) {
				
				// 设置按钮为“修改”
				editeOrComBtn.setText(R.string.b04v04_tittle_editet_text);
				
				// 设置修改图片View隐藏
				changeUserLogo.setVisibility(View.GONE);
				
				// 设置Lable显示
				baseInfoLable.setVisibility(View.VISIBLE);
				companyLable.setVisibility(View.VISIBLE);
				
				// 设置EditeView为可编辑状态
				setCanEdite(false);
				
				// 保存修改内容
				saveInfo();
				
			} else {		// 非修改状态
				
				// 设置按钮为“完成”
				editeOrComBtn.setText(R.string.b04v01_tittle_complete);
				
				// 设置修改图片View显示
				changeUserLogo.setVisibility(View.VISIBLE);
				
				// 设置Lable隐藏
				baseInfoLable.setVisibility(View.GONE);
				companyLable.setVisibility(View.GONE);
				
				// 设置EditeView为不可编辑状态
				setCanEdite(true);
			}
		}
	};
	
	/**
	 * 保存修改内容
	 */
	private void saveInfo() {
		
		userModle.user.setName(name.getText().toString());
		userModle.user.setSex(0);
		userModle.user.setLocation(location.getText().toString());
		userModle.user.setDisc(disc.getText().toString());
		userModle.user.setQq(qq.getText().toString());
		userModle.user.setCompany(company.getText().toString());
		
		userModle.save();
	}
	
	/**
	 * 设置是否可以编辑
	 * @param canEdite
	 */
	private void setCanEdite(boolean canEdite) {
		
//		loginName.setEnabled(canEdite);
		name.setEnabled(canEdite);
		sex.setEnabled(canEdite);
		location.setEnabled(canEdite);
		disc.setEnabled(canEdite);
		qq.setEnabled(canEdite);
		company.setEnabled(canEdite);
	}
	
}

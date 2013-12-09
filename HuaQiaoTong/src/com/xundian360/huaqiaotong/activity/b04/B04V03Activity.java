/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b04;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.modle.com.UserModle;
import com.xundian360.huaqiaotong.util.CommonUtil;

/**
 * 个人中心
 * @author  Administrator
 * @date      2013年10月23日
 * @version 1.0
 */
public class B04V03Activity extends ComNoTittleActivity {
	
	// 头部图像
	ImageView tittleImg;
	// 用户图像
	ImageView logoImg;
	// 发表新帖
	LinearLayout editePostsBtn;
	// 朋友列表
	LinearLayout friendListBtn;
	// 用户描述
	LinearLayout userDisc;
	// 显示更多
	LinearLayout showMoreBtn;
	// 关注数量
	TextView guanzhuNum;
	// 粉丝数量
	TextView fanNum;
	// 评论
	TextView commentNum;
	// 评论列表
	ListView commentList;
	// 返回按钮
	ImageButton retBtn;
	// 消息
	ImageButton msgBtn;
	// 附近按钮
	ImageButton locationBtn;
	// 设置按钮
	ImageButton settingBtn;
	
	// 用户存储类
	UserModle userModle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b04v03);
		
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
		
		tittleImg = (ImageView) findViewById(R.id.b04v03TittleImg);
		logoImg = (ImageView) findViewById(R.id.b04v03UserLogoImg);
		editePostsBtn = (LinearLayout) findViewById(R.id.b03v02EditePostsBtn);
		friendListBtn = (LinearLayout) findViewById(R.id.b03v02FriendListBtn);
		userDisc = (LinearLayout) findViewById(R.id.b03v02UserDiscLayout);
		showMoreBtn = (LinearLayout) findViewById(R.id.b04v03ShowMoreBtn);
		showMoreBtn.setOnClickListener(showMoreBtnClick);
		
		guanzhuNum = (TextView) findViewById(R.id.b04v03GuanzhuNum);
		fanNum = (TextView) findViewById(R.id.b04v03FanNum);
		commentNum = (TextView) findViewById(R.id.b04v03CommentNum);
//		commentNum.setText(R.string.b04v03_text_commont_num, "20");
		commentNum.setText(getString(R.string.b04v03_text_commont_num, "20"));
		
		commentList = (ListView) findViewById(R.id.b04v03CommentList);
		retBtn = (ImageButton) findViewById(R.id.b04v03ReturnBtn);
		msgBtn = (ImageButton) findViewById(R.id.b04v03MsgBtn);
		locationBtn = (ImageButton) findViewById(R.id.b04v03LocationBtn);
		settingBtn = (ImageButton) findViewById(R.id.b04v03SettingBtn);
	}
	
	/**
	 * 详细信息
	 */
	OnClickListener showMoreBtnClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			CommonUtil.startActivityForResult(B04V03Activity.this, B04V04Activity.class, 100);
		}
	};
	
	
}

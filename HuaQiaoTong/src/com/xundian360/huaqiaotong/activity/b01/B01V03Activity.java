/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b01;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.StringUtils;
import com.xundian360.huaqiaotong.util.b01.B01v00ShopUtils;
import com.xundian360.huaqiaotong.view.com.CommonProgressDialog;

/**
 * KTV评价
 * @author  Administrator
 * @date      2013年10月12日
 * @version 1.0
 */
public class B01V03Activity extends ComNoTittleActivity {
	
	// 取消按钮
	ImageButton cancelBtn;
	// 提交按钮
	ImageButton okBtn;
	// 标题
	TextView tittleText;
	
	// 评价：满意
	LinearLayout impGood;
	// 评价：一般
	LinearLayout impOk;
	// 评价：不满意
	LinearLayout impNg;
	
	// 评价 
	EditText impMsg;
	
	// 百度Item
	Baidu baiduItem;
	
	// 评分
	int score = 3;
	
	Handler _handler = new Handler();
	
	CommonProgressDialog processDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b01v03);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		
		// 取得百度对象
		baiduItem = (Baidu) getIntent().getSerializableExtra(B01V01Activity.KTV_KEY);
		
		processDialog = new CommonProgressDialog(this);
		
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		cancelBtn = (ImageButton) findViewById(R.id.b01v03Cance1Btn);
		cancelBtn.setOnClickListener(cancelBtnClick);
		
		okBtn = (ImageButton) findViewById(R.id.b01v03OkBtn);
		okBtn.setOnClickListener(okBtnClick);
		
		tittleText = (TextView) findViewById(R.id.b01v03OkTittleText);
		tittleText.setText(getString(R.string.b01v03_tittle_text, baiduItem.getName()));
		
		impGood = (LinearLayout) findViewById(R.id.b01v01ImpGood);
		impGood.setOnClickListener(impGoodClick);
		impOk = (LinearLayout) findViewById(R.id.b01v01ImpOk);
		impOk.setOnClickListener(impOkClick);
		impNg = (LinearLayout) findViewById(R.id.b01v01ImpNg);
		impNg.setOnClickListener(impNgClick);
		
		impMsg = (EditText) findViewById(R.id.b01v01DetailImpMsg);
	}
	
	/**
	 * 取消提交
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
	OnClickListener okBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			// 网络检查
			if(!CommonUtil.isNetworkAvailable(B01V03Activity.this)) {
				ShowMessageUtils.show(B01V03Activity.this, R.string.message_error_network);
				return;
			}
			
			// 用户ID
			final String userId = CommonUtil.isLogin(B01V03Activity.this);
			
			// 判断是否登录
			if(StringUtils.isBlank(userId)) {
				return;
			}
			
			// 评价
			final String msg = impMsg.getText().toString();
			if(StringUtils.isBlank(msg)) {
				ShowMessageUtils.show(B01V03Activity.this, R.string.b01v03_msg_null_text);
				return;
			}
			
			// 显示进度条
			processDialog.show();
			
			// 访问网络
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					// 添加商店评论
					final boolean plRe = B01v00ShopUtils.addShopPingLun(B01V03Activity.this, 
							baiduItem.getUid(), userId, score + "", msg);
					
					// 显示消息
					_handler.post(new Runnable() {
						@Override
						public void run() {
							// 评论成功
							if(plRe) {
								ShowMessageUtils.show(B01V03Activity.this, R.string.b01v03_msg_c_success);
								
								// 跳出当前Activity
								finish();
							} else {
								// 评论失败
								ShowMessageUtils.show(B01V03Activity.this, R.string.b01v03_msg_c_error);
							}
							
							// 取消Dialog显示
							processDialog.dismiss();
						}
					});
				}
			}).start();
		}
	};
	
	

	/**
	 * 评价：满意
	 */
	OnClickListener impGoodClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			score = 5;
			
			//打开键盘
			ShowMessageUtils.show(B01V03Activity.this, "评价：满意");
		}
	};
	
	/**
	 * 评价：一般
	 */
	OnClickListener impOkClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			score = 3;
			
			ShowMessageUtils.show(B01V03Activity.this, "评价：一般");
		}
	};
	
	/**
	 * 评价：不满意
	 */
	OnClickListener impNgClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			score = 1;
			
			ShowMessageUtils.show(B01V03Activity.this, "评价：不满意");
		}
	};
}

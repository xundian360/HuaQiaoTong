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
	
	// KTV
	Baidu ktv;
	
	Handler _handler = new Handler();

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
		// EditeText获得焦点
//		impMsg.requestFocus();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
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
			ShowMessageUtils.show(B01V03Activity.this, "提交成功！");
		}
	};

	/**
	 * 评价：满意
	 */
	OnClickListener impGoodClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//打开键盘
			CommonUtil.showInput(B01V03Activity.this);
			ShowMessageUtils.show(B01V03Activity.this, "评价：满意");
		}
	};
	
	/**
	 * 评价：一般
	 */
	OnClickListener impOkClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			ShowMessageUtils.show(B01V03Activity.this, "评价：一般");
		}
	};
	
	/**
	 * 评价：不满意
	 */
	OnClickListener impNgClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			ShowMessageUtils.show(B01V03Activity.this, "评价：不满意");
		}
	};
}

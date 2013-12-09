/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b00;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;

/**
 * 主页
 * @author  Administrator
 * @date      2013年11月10日
 * @version 1.0
 */
public class B00Activity extends ComNoTittleActivity {
	
	// 更多
	ImageButton moreBtn;
	// 设置
	ImageButton setBtn;
	
	// 我的生活
	ImageView myLifeBtn;
	// 花桥论坛
	ImageView hqBtn;
	// 吃喝玩乐
	ImageView chiBtn;
	// 我要用车
	ImageView carBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b00);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		moreBtn = (ImageButton) findViewById(R.id.mainTittleMoreBtn);
		setBtn = (ImageButton) findViewById(R.id.mainTittleSetBtn);
		
		myLifeBtn = (ImageView) findViewById(R.id.mainMyLifeBtn);
		myLifeBtn.setOnTouchListener(myLifeBtnTouch);
		
		hqBtn = (ImageView) findViewById(R.id.mainHqBtn);
		hqBtn.setOnTouchListener(hqBtnTouch);
		
		chiBtn = (ImageView) findViewById(R.id.mainChiBtn);
		chiBtn.setOnTouchListener(chiBtnTouch);
		
		carBtn = (ImageView) findViewById(R.id.mainCarBtn);
		carBtn.setOnTouchListener(carBtnTouch);
		AnimationDrawable an = (AnimationDrawable) carBtn.getDrawable();
		an.start();
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
	 * 我的生活点击事件
	 */
	OnTouchListener myLifeBtnTouch = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				v.startAnimation(AnimationUtils.loadAnimation(B00Activity.this, R.anim.main_life_rotate));	
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				
				ShowMessageUtils.show(B00Activity.this, "我的生活");

			}
			return true;
		}
	};
	
	/**
	 * 花桥论坛事件
	 */
	OnTouchListener hqBtnTouch = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				v.startAnimation(AnimationUtils.loadAnimation(B00Activity.this, R.anim.main_life_rotate));
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				ShowMessageUtils.show(B00Activity.this, "花桥论坛");
			}
			return true;
		}
	};
	
	/**
	 * 吃喝玩乐事件
	 */
	OnTouchListener chiBtnTouch = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
//				v.startAnimation(AnimationUtils.loadAnimation(B00Activity.this, R.anim.main_life_rotate));
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				ShowMessageUtils.show(B00Activity.this, "吃喝玩乐");
			}
			return true;
		}
	};
	
	/**
	 * 我的用车点击事件
	 */
	OnTouchListener carBtnTouch = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				v.startAnimation(AnimationUtils.loadAnimation(B00Activity.this, R.anim.rotate));
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				ShowMessageUtils.show(B00Activity.this, "我的用车");
			}

			return true;
		}
	};
	
}

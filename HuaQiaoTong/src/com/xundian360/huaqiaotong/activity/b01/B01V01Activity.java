/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b01;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.StringUtils;
import com.xundian360.huaqiaotong.view.com.GalleryFlow;

/**
 * KTV详情
 * @author  Administrator
 * @date      2013年10月12日
 * @version 1.0
 */
public class B01V01Activity extends ComNoTittleActivity {
	
	public static final String KTV_KEY = "ktv_key";
	
	// 返回按钮
	ImageButton retBtn;
	// 标题
	TextView tittleText;

	// 头部图片
	GalleryFlow tittlePic;
	
	// 店名
	TextView itemTittle;
	// 营业时间
	TextView itemTime;
	// 描述
	TextView itemRecommend;
	// 评分
	RatingBar itemRating;
	// 价格
	TextView itemPrice;
	// 到这里去
	LinearLayout goBtn;
	// 打电话
	LinearLayout callBtn;
	
	// 更多按钮
	ImageView moreBtn;
	// 评分按钮
	ImageView editeBtn;
	// 到这里去
	ImageView goBtn2;
	
	// KTV
	Baidu baiduItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b01v01);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		
		baiduItem = (Baidu) getIntent().getSerializableExtra(KTV_KEY);
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		retBtn = (ImageButton) findViewById(R.id.b01v01RetBtn);
		retBtn.setOnClickListener(retBtnClick);
		
		tittleText = (TextView) findViewById(R.id.b01v01Tittle);
		tittleText.setText(baiduItem.getName());
		
		tittlePic = (GalleryFlow) findViewById(R.id.b01v01Imgs);
		
		itemTittle = (TextView) findViewById(R.id.v01v01ItemTittle);
		itemTittle.setText(baiduItem.getName());
		
		itemTime = (TextView) findViewById(R.id.v01v00Time);
		itemTime.setText("营业时间:" + baiduItem.getTime());
		
		itemRecommend = (TextView) findViewById(R.id.b01v00Recommend);
		itemRecommend.setText(baiduItem.getDisc());
		
		itemRating = (RatingBar) findViewById(R.id.v01v00Rating);
		itemRating.setRating(StringUtils.paseFloat(baiduItem.getOverall_rating(), 0));
		itemRating.setClickable(false);
		
		itemPrice = (TextView) findViewById(R.id.v01v00Price);
		itemPrice.setText(baiduItem.getPrice());
		
		goBtn = (LinearLayout) findViewById(R.id.b01v00Go);
		goBtn.setOnClickListener(goBtnClick);
		
		callBtn = (LinearLayout) findViewById(R.id.b01v00Call);
		callBtn.setOnClickListener(callBtnClick);
		
		moreBtn = (ImageView) findViewById(R.id.b01v01MoreBtn);
		moreBtn.setOnClickListener(moreBtnClick);
		
		editeBtn = (ImageView) findViewById(R.id.b01v01EditeBtn);
		editeBtn.setOnClickListener(editeBtnClick);
		
		goBtn2 = (ImageView) findViewById(R.id.b01v01GoBtn);
		goBtn2.setOnClickListener(goBtnClick);
		
	}
	
	/**
	 * 评论按钮事件
	 */
	OnClickListener editeBtnClick = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			ShowMessageUtils.show(B01V01Activity.this, "评论按钮事件");
		}
	};
	
	/**
	 * 更多按钮事件
	 */
	OnClickListener moreBtnClick = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			ShowMessageUtils.show(B01V01Activity.this, "更多按钮事件");
		}
	};
	
	/**
	 * 到这里按钮事件
	 */
	OnClickListener goBtnClick = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			ShowMessageUtils.show(B01V01Activity.this, "到这里按钮事件");
		}
	};
	
	/**
	 * 电话
	 */
	OnClickListener callBtnClick = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			CommonUtil.callPhone(B01V01Activity.this, baiduItem.getTelephone());
		}
	};
	
	/**
	 * 返回按钮事件
	 */
	OnClickListener retBtnClick = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			onBackPressed();
		}
	};
	
}

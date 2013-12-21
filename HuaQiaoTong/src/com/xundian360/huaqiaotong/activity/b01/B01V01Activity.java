/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.adapter.b01.B01v01ImgAdapter;
import com.xundian360.huaqiaotong.adapter.b01.B01v01KtvCommAdapter;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.modle.com.BaiduComment;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.StringUtils;
import com.xundian360.huaqiaotong.util.b01.B01v00ShopUtils;
import com.xundian360.huaqiaotong.view.com.AllShowListView;
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
	GalleryFlow tittlePicView;
	
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
	// 评论数量
	TextView commentNum;
	// 评论列表
	AllShowListView commentList;
	
	// 评论面板
	LinearLayout commentLayout;
	
	LinearLayout btnLayout;
	
	// 更多按钮
	ImageView moreBtn;
	// 评分按钮
	ImageView editeBtn;
	// 到这里去
	ImageView goBtn2;
	
	// 头部图片数据源
	B01v01ImgAdapter tittlePicAdapter;
	List<String> tittlePics = new ArrayList<String>();
	List<Map<String, String>> tittleImgData = new ArrayList<Map<String,String>>();
	
	// 评论数据源
	B01v01KtvCommAdapter commAdapter;
	List<BaiduComment> shopComments = new ArrayList<BaiduComment>();
	List<Map<String, String>> commData = new ArrayList<Map<String,String>>();
	
	// KTV
	Baidu baiduItem;
	
	Handler _handler = new Handler();
	
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
		
		// 取得百度对象
		baiduItem = (Baidu) getIntent().getSerializableExtra(KTV_KEY);
		
		// 设置数据源
		tittlePicAdapter = new B01v01ImgAdapter(this, tittleImgData, 
				R.layout.b01v01_tittle_item, B01v01ImgAdapter.from, B01v01ImgAdapter.to);
		
		commAdapter = new B01v01KtvCommAdapter(this, commData, 
				R.layout.b01v01_item, B01v01KtvCommAdapter.from, B01v01KtvCommAdapter.to);
		
		// 取得头部图片
		setTittlePic();
		
		// 取得评论信息
		setCommData();
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		retBtn = (ImageButton) findViewById(R.id.b01v01RetBtn);
		retBtn.setOnClickListener(retBtnClick);
		
		tittleText = (TextView) findViewById(R.id.b01v01Tittle);
		tittleText.setText(baiduItem.getName());
		
		tittlePicView = (GalleryFlow) findViewById(R.id.b01v01Imgs);
		tittlePicView.setAdapter(tittlePicAdapter);
		
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
		
		commentNum = (TextView) findViewById(R.id.b01v00CommentNum);
		
		commentList = (AllShowListView) findViewById(R.id.b01v00CommentList);
		commentList.setAdapter(commAdapter);
		
		commentLayout = (LinearLayout) findViewById(R.id.b01v00CommentLayout);
		
		moreBtn = (ImageView) findViewById(R.id.b01v01MoreBtn);
		moreBtn.setOnClickListener(moreBtnClick);
		
		editeBtn = (ImageView) findViewById(R.id.b01v01EditeBtn);
		editeBtn.setOnClickListener(editeBtnClick);
		
		goBtn2 = (ImageView) findViewById(R.id.b01v01GoBtn);
		goBtn2.setOnClickListener(goBtnClick);
		
		btnLayout = (LinearLayout) findViewById(R.id.b01v01BtnLayout);
	}
	
	/**
	 * 取得头部图片
	 */
	private void setTittlePic() {
		new Thread(getTittlePicRun).start();
	}
	
	/**
	 * 取得头部图片
	 */
	private Runnable getTittlePicRun = new Runnable() {
		
		@Override
		public void run() {
			// 取得头部图片信息
			List<String> tittlePicsNet = B01v00ShopUtils
					.getShopTittleImg(B01V01Activity.this, baiduItem.getUid());
			
			// 非空判断
			if(tittlePicsNet != null && tittlePicsNet.size() > 0) {
				
				tittlePics = tittlePicsNet;
				
				// 更新UI
				_handler.post(updateTittlePic);
			}
		}
	};
	
	/**
	 * 更新头图图片
	 */
	private Runnable updateTittlePic = new Runnable() {
		
		@Override
		public void run() {
			// 遍历，设置数据源
			for (String tittlePic : tittlePics) {
				Map<String, String> item = new HashMap<String, String>();
				
				item.put(B01v01ImgAdapter.from[0], tittlePic);
				
				tittleImgData.add(item);
			}
			
			// 设置图片路径
			tittlePicAdapter.setTittlePics(tittlePics);
			
			// 刷新视图
			tittlePicAdapter.notifyDataSetChanged();
			
			// 设置选中编号
			if(tittleImgData.size() > 1) {
				tittlePicView.setSelection(1);
			}
		}
	};
	
	/**
	 * 取得评论信息
	 */
	private void setCommData() {
		new Thread(getCommDataRun).start();
	}
	
	/**
	 * 取得评论信息
	 */
	private Runnable getCommDataRun = new Runnable() {
		
		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			
			Map<String, Object> shopPlsMap = 
					B01v00ShopUtils.getShopPlList(B01V01Activity.this, baiduItem.getUid());
			
			if(shopPlsMap != null) {
				
				// 总数量
				int totalNum = StringUtils.paseInt((String)shopPlsMap.get(B01v00ShopUtils.TOTAL_KEY), 0);
				
				// 取得评论空
				if(totalNum > 0) {
					
					// 设置评论数据
					List<BaiduComment> shopCommentsNet = (List<BaiduComment>) shopPlsMap.get(B01v00ShopUtils.RESULTS_KEY);
					shopComments.addAll(shopCommentsNet);
					
					// 更新UI
					_handler.post(updateCommData);
				} else {
					// 隐藏评论面板
					_handler.post(hiddenCommentLayout);
				}
			} else {
				// 隐藏评论面板
				_handler.post(hiddenCommentLayout);
			}
		}
	};
	
	/**
	 * 隐藏评论面板
	 */
	Runnable hiddenCommentLayout = new Runnable() {
		
		@Override
		public void run() {
			// 隐藏评论面板
			commentLayout.setVisibility(View.GONE);
		}
	};
	
	/**
	 * 更新评论信息
	 */
	private Runnable updateCommData = new Runnable() {
		
		@Override
		public void run() {
			
			// 设置评论数量
			commentNum.setText(getString(R.string.b01v01_com_num, (shopComments.size() + "")));
			
			// 清空数据源
			commData.clear();
			
			// 设置数据评论源
			for (BaiduComment comment : shopComments) {
				
				Map<String, String> commentItem = new HashMap<String, String>();
				commentItem.put(B01v01KtvCommAdapter.from[1], comment.getUserName());
				// commentItem.put(B01v01KtvCommAdapter.from[2], comment.getScore());
				commentItem.put(B01v01KtvCommAdapter.from[2], comment.getDic());
				commentItem.put(B01v01KtvCommAdapter.from[3], comment.getTime());
				
				commData.add(commentItem);
			}
			
			// 设置数据
			commAdapter.setShopComments(shopComments);
			
			// 更新UI
			commAdapter.notifyDataSetChanged();
		}
	};
	
	/**
	 * 评论按钮事件
	 */
	OnClickListener editeBtnClick = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			
			String userId = CommonUtil.isLogin(B01V01Activity.this);
			
			// 判断是否登录
			if(StringUtils.isBlank(userId)) {
				return;
			}
			
			CommonUtil.startActivityForResult(B01V01Activity.this, B01V03Activity.class, B01V01Activity.KTV_KEY, baiduItem, 100);
		}
	};
	
	/**
	 * 更多按钮事件
	 */
	OnClickListener moreBtnClick = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			if(btnLayout.getVisibility() == View.GONE) {
				btnLayout.setVisibility(View.VISIBLE);
			} else {
				btnLayout.setVisibility(View.GONE);
			}
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

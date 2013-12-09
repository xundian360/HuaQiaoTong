/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.adapter.b01.B01v01KtvCommAdapter;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.view.b01.B01v01DatailTittleView;
import com.xundian360.huaqiaotong.view.com.CommonShearView;

/**
 * KTV详情
 * @author  Administrator
 * @date      2013年10月12日
 * @version 1.0
 */
public class CopyOfB01V01Activity extends ComNoTittleActivity {
	
	public static final String KTV_KEY = "ktv_key";
	
//	// 返回按钮
//	ImageButton retBtn;
//	// KTV图片
//	ImageView ktvImg;
//	// KTV名称
//	TextView ktvName;
//	// KTV地址
//	TextView ktvAddress;
//	// 到这里去
//	LinearLayout toBtn;
//	// 从这出发
//	LinearLayout fromBtn;
//	// 附近索搜按钮
//	LinearLayout nearbyBtn;
//	// 收藏按钮
//	LinearLayout saveBtn;
//	
//	// 评论和其他
//	ExpandableListView detailView;
//	
//	// 评论数据源
//	B01v01KtvCommAdapter commentAdapter;
//	List<Map<String, Object>> groupData = new ArrayList<Map<String,Object>>();
//	List<List<Map<String, Object>>> childData = new ArrayList<List<Map<String,Object>>>();
//	
//	// 我要评论
//	LinearLayout commentBtn;
//	// 分享按钮
//	LinearLayout shearBtn;
//	// 纠错按钮
//	LinearLayout currectionBtn;
//	
//	// 评论头部操作按钮
//	B01v01DatailTittleView datailTittleView;
//	
//	// KTV
//	Baidu ktv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		setContentView(R.layout.b01v01);
//		
//		// 初始化数据
//		initData();
//		
//		// 初始化组件
//		initModule();
	}
//	
//	/**
//	 *  初始化数据
//	 */
//	private void initData(){
//		
//		ktv = (Baidu) getIntent().getSerializableExtra(KTV_KEY);
//		
//		datailTittleView = new B01v01DatailTittleView(this, ktv);
//		
//		// 设置Adapter数据
//		setAdapterData();
//		
//		commentAdapter = new B01v01KtvCommAdapter(this, 
//				groupData, 
//				R.layout.b01v01_item, 
//				B01v01KtvCommAdapter.p_from, 
//				B01v01KtvCommAdapter.p_to, 
//				childData, 
//				R.layout.b01v01_child_item, 
//				B01v01KtvCommAdapter.c_from, 
//				B01v01KtvCommAdapter.c_to);
//	}
//	
//	/**
//	 *  初始化组件
//	 */
//	private void initModule(){
//		
//		retBtn = (ImageButton) findViewById(R.id.b01v01RetBtn);
//		retBtn.setOnClickListener(retBtnClick);
//		
//		ktvImg = (ImageView) findViewById(R.id.b01v01KtvImg);
//		
//		ktvName = (TextView) findViewById(R.id.b01v01KtvName);
//		ktvName.setText(ktv.getName());
//		
//		ktvAddress = (TextView) findViewById(R.id.b01v01KtvAddress);
//		ktvAddress.setText(ktv.getAddress());
//		
//		toBtn = (LinearLayout) findViewById(R.id.b01v01ToBtn);
//		toBtn.setOnClickListener(toBtnClick);
//		
//		fromBtn = (LinearLayout) findViewById(R.id.b01v01FromBtn);
//		fromBtn.setOnClickListener(fromBtnClick);
//		
//		nearbyBtn = (LinearLayout) findViewById(R.id.b01v01NearbyBtn);
//		nearbyBtn.setOnClickListener(nearbyBtnClick);
//		
//		saveBtn = (LinearLayout) findViewById(R.id.b01v01SaveBtn);
//		saveBtn.setOnClickListener(saveBtnClick);
//		
//		detailView = (ExpandableListView) findViewById(R.id.b01v01DetailView);
//		detailView.addHeaderView(datailTittleView.get());
//		detailView.setAdapter(commentAdapter);
//		
//		commentBtn = (LinearLayout) findViewById(R.id.b01v01CommentBtn);
//		commentBtn.setOnClickListener(commentBtnClick);
//		
//		shearBtn = (LinearLayout) findViewById(R.id.b01v01ShearBtn);
//		shearBtn.setOnClickListener(shearBtnClick);
//		
//		currectionBtn = (LinearLayout) findViewById(R.id.b01v01CurrectionBtn);
//		currectionBtn.setOnClickListener(currectionBtnClick);
//	}
//	
//	/**
//	 * 返回按钮事件
//	 */
//	OnClickListener retBtnClick = new OnClickListener() {
//		@Override
//		public void onClick(View arg0) {
//			onBackPressed();
//		}
//	};
//	
//	/**
//	 * 到这里去
//	 */
//	OnClickListener toBtnClick = new OnClickListener() {
//		@Override
//		public void onClick(View arg0) {
//			// 跳转
//			searchRoute(B01V02Activity.FROM_KEY);
//		}
//	};
//	
//	/**
//	 * 从这里出发
//	 */
//	OnClickListener fromBtnClick = new OnClickListener() {
//		@Override
//		public void onClick(View arg0) {
//			// 跳转
//			searchRoute(B01V02Activity.TO_KEY);
//		}
//	};
//	
//	/**
//	 * 在附近找
//	 */
//	OnClickListener nearbyBtnClick = new OnClickListener() {
//		@Override
//		public void onClick(View arg0) {
//			
//		}
//	};
//	
//	/**
//	 * 收藏
//	 */
//	OnClickListener saveBtnClick = new OnClickListener() {
//		@Override
//		public void onClick(View arg0) {
//			
//		}
//	};
//	
//	/**
//	 * 评论
//	 */
//	OnClickListener commentBtnClick = new OnClickListener() {
//		@Override
//		public void onClick(View arg0) {
//			
//			CommonUtil.startActivityForResult(CopyOfB01V01Activity.this,  
//					B01V03Activity.class, CopyOfB01V01Activity.KTV_KEY, ktv, 100);
//		}
//	};
//	
//	/**
//	 * 分享
//	 */
//	OnClickListener shearBtnClick = new OnClickListener() {
//		@Override
//		public void onClick(View arg0) {
//			
//			// 分享View
//			CommonShearView shearView = new CommonShearView(CopyOfB01V01Activity.this);
//			shearView.show();
//		}
//	};
//	
//	/**
//	 * 纠错
//	 */
//	OnClickListener currectionBtnClick = new OnClickListener() {
//		@Override
//		public void onClick(View arg0) {
//			
//		}
//	};
//	
//	/**
//	 * 跳转
//	 * @param key
//	 */
//	private void searchRoute(String key) {
//		
//		Intent intent = new Intent(CopyOfB01V01Activity.this, B01V02Activity.class);
//		// 设置标记Key
//		intent.putExtra(B01V02Activity.FROM_OR_TO_KEY, key);
//		// 设置检索对象
//		intent.putExtra(B01V02Activity.BAIDU_DATA_KEY, ktv);
//		
//		// 跳转
//		CommonUtil.startActivityForResult(CopyOfB01V01Activity.this, intent, 100);
//	}
//	
//	/**
//	 * 设置Adapter数据
//	 */
//	private void setAdapterData() {
//		
//		for (int i = 0; i < 9; i++) {
//			Map<String, Object> groupItem = new HashMap<String, Object>();
//			List<Map<String, Object>> childItem = new ArrayList<Map<String,Object>>();
//			
//			groupItem.put(B01v01KtvCommAdapter.p_from[1], "匿名" + i);
//			groupItem.put(B01v01KtvCommAdapter.p_from[2], "NO." +  i + "不是第一次去了，很喜欢里面的装"
//					+ "饰，蛮漂亮大方的，让我有种晕眩的赶脚~~她的服务也很到位很贴心。昨天因为几个同学临时过来玩");
//			
//			groupData.add(groupItem);
//			childItem.add(groupItem);
//			
//			childData.add(childItem);
//		}
//		
//	}
//	
}

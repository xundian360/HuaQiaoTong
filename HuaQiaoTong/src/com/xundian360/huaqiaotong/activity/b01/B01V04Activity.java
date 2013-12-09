/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b01;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleMapActivity;
import com.xundian360.huaqiaotong.common.map.BaiduItemOverlay;
import com.xundian360.huaqiaotong.common.map.SimpleLocationManager;
import com.xundian360.huaqiaotong.modle.b01.ItemObject;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;

/**
 * KTV列表
 * @author  TengTeng
 * @date      2013-10-3
 * @version 1.0
 */
public class B01V04Activity extends ComNoTittleMapActivity {

	// Tittle
	RelativeLayout tittleLayout;
	// 搜索框
	LinearLayout searchLayout;
	// 返回按钮
	ImageButton retBtn;
	// 标题
	TextView tittleText;
	// 搜索按钮
	ImageButton searchBtn;
	// 搜索输入Text
	AutoCompleteTextView searchText;
	// 取消搜索
	TextView cancelBtnText;
	
	// 当前项目显示的分类
	ItemObject itemObject;
	
	List<Baidu> itemsData = new ArrayList<Baidu>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b01v04);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
		
		// 设置地图
		setMap();
		
		// 初始化站点覆盖物信息
		initOverlay();
	}
	
	@Override
	protected void onStart() {
		
		if(CommonUtil.isNetworkAvailable(this)) {
			// 开始定位
			locationManager.start();
		} else {
			ShowMessageUtils.show(this, R.string.message_error_network);
		}
		
		super.onStart();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		
		itemObject = (ItemObject) getIntent().getSerializableExtra(B01V00Activity.ITEM_OBJECT_KEY);
		
		// 设置数据
		setData();
		
		// 设置位置管理器(定位一次)
		locationManager = new SimpleLocationManager(getApplication(), locationListener, -1);
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		tittleLayout = (RelativeLayout) findViewById(R.id.b01v04TittleLayout1);
		searchLayout= (LinearLayout) findViewById(R.id.b01v04TittleLayout2);
		
		retBtn = (ImageButton) findViewById(R.id.b01v04RetBtn);
		retBtn.setOnClickListener(retBtnClick);
		
		tittleText = (TextView) findViewById(R.id.b01v04Tittle);
		tittleText.setText(getString(itemObject.getTittleId()));
		
		searchBtn = (ImageButton) findViewById(R.id.b01v04SearchBtn);
		searchBtn.setOnClickListener(searchBtnClick);
		
		searchText = (AutoCompleteTextView) findViewById(R.id.b01v04SearchText);
		searchText.setHint(getString(R.string.b01v01_tittle_hint, getString(itemObject.getTittleId())));
		
		cancelBtnText = (TextView) findViewById(R.id.b01v04CancelBtn);
		cancelBtnText.setOnClickListener(cancelBtClick);
		
		mapView = (MapView) findViewById(R.id.b01v04Map);
	}
	
	/**
	 * 设置数据
	 */
	private void setData() {

		Baidu item = new Baidu();
		
		item.setName("集善新村");
		item.setAddress("苏州市昆山市集善新村");
//		item.setLocation_lat(121.07928125099);
//		item.setLocation_lng(31.311901849318);
		item.setLocation_lat(31.311901849318);
		item.setLocation_lng(121.07928125099);
		
		itemsData.add(item);
	}
	
	/**
	 * 返回按钮事件
	 */
	OnClickListener retBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			onBackPressed();
		}
	};
	
	/**
	 * 搜索按钮事件
	 */
	OnClickListener searchBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// 切换头部显示
			switchTittle();
		}
	};
	
	/**
	 * 切换头部显示
	 */
	private void switchTittle() {
		
		if(tittleLayout.getVisibility() == View.VISIBLE) {
			
			// 设置显隐性
			tittleLayout.startAnimation(AnimationUtils.loadAnimation(B01V04Activity.this, R.anim.b01v00_tittle_out));
			tittleLayout.setVisibility(View.GONE);
			
			searchLayout.startAnimation(AnimationUtils.loadAnimation(B01V04Activity.this, R.anim.b01v00_tittle_in));
			searchLayout.setVisibility(View.VISIBLE);
			
			// 设置焦点
			searchText.requestFocus();
			
			// 打开键盘
			CommonUtil.showInput(B01V04Activity.this);
		} else {
			
			// 设置显隐性
			tittleLayout.startAnimation(AnimationUtils.loadAnimation(B01V04Activity.this, R.anim.b01v00_tittle_in));
			tittleLayout.setVisibility(View.VISIBLE);
			
			searchLayout.startAnimation(AnimationUtils.loadAnimation(B01V04Activity.this, R.anim.b01v00_tittle_out));
			searchLayout.setVisibility(View.GONE);
			
			// 设置焦点
			searchText.clearFocus();
			
			// 关闭键盘
			CommonUtil.hideInput(B01V04Activity.this);
		}
	}
	
	/**
	 * 搜索按钮事件
	 */
	OnClickListener cancelBtClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// 切换头部显示
			switchTittle();
		}
	};
	
	/**
	 * 定位到我的位置之后
	 */
	@Override
	public void myLocationOtherDo(BDLocation location) {
//        mapView.getController().animateTo(
//				new GeoPoint((int)(location.getLatitude()* 1e6), (int)(location.getLongitude() *  1e6)));
		
		 mapView.getController().animateTo(
					new GeoPoint((int)(itemsData.get(0).getLocation_lat() * 1e6), (int)(itemsData.get(0).getLocation_lng()  *  1e6)));
	};

	/**
	 * 设置地图数据
	 */
	public void setMap() {
		super.setMap();
	};
	
	/**
	 *  初始化站点覆盖物信息
	 */
	private void initOverlay(){
		
		if(itemsData != null && !itemsData.isEmpty()) {
			
			BaiduItemOverlay mOverlay = 
					new BaiduItemOverlay(this, 
							getResources().getDrawable(R.drawable.b01v04_re_poi), 
							mapView, itemsData);
			
			// 遍历。添加KTV信息到地图
			for (Baidu ktv : itemsData) {
				
		         GeoPoint geoPoint = new GeoPoint ((int)(ktv.getLocation_lat() * 1e6),(int)(ktv.getLocation_lng()* 1e6));
		         
		         Log.d("debug", "station.getLocation_lat() > " + (int)(ktv.getLocation_lat()* 1e6));
		         Log.d("debug", "station.getLocation_lng() > " + (int)(ktv.getLocation_lng()* 1e6));
		         
		         OverlayItem verlayItem = new OverlayItem(geoPoint, 
		        		 ktv.getName(), ktv.getAddress());
		         
		         verlayItem.setMarker(getResources().getDrawable(R.drawable.b01v04_re_poi));
		         
		         mOverlay.addItem(verlayItem);
			}
			
			// 非空
			if(mOverlay.size() > 0) {
				
				// 添加气泡到地图
				mapView.getOverlays().add(mOverlay);
		         // 刷新地图
				mapView.refresh();
			}
		}
	}

}

/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b02;

import java.io.Serializable;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleMapActivity;
import com.xundian360.huaqiaotong.common.map.SimpleLocationManager;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.modle.com.SerializableList;
import com.xundian360.huaqiaotong.util.b02.B02BaiduUtil;
import com.xundian360.huaqiaotong.view.b02.CarsPointItemOverlay;

/**
 * 叫车服务（区域选择）
 * @author    LiZhenteng
 * @date      2013年10月14日
 * @version   1.0
 */
public class B02V00Activity extends ComNoTittleMapActivity {
	
	public static final String B02V00ACTIVITY_CARPIONTLIST = "carPiontList";
	
	// 返回按钮
	ImageButton retBtn;
	
	// 黑车集散点
	SerializableList carPiontList;
	
	Handler _handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b02v00);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
		
		// 设置地图
		setMap();
		
		// 开始定位
		locationManager.start();
		
		// 取得车集散点信息
		getCarsPoint();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
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
		retBtn = (ImageButton) findViewById(R.id.b02v00RetBtn);
		retBtn.setOnClickListener(retBtnClick);
		
		mapView = (MapView) findViewById(R.id.b02v00PointMap);
		
		// 设置位置管理器(定位一次)
		locationManager = new SimpleLocationManager(getApplication(), locationListener, -1);
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
	 * 取得车集散点信息
	 */
	private void getCarsPoint() {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				// 取得车集散点信息
				carPiontList = B02BaiduUtil.getCarsPoint(B02V00Activity.this);
				
				// 更新地图，添加气泡
				_handler.post(updateMap);
			}
		}).start();
	}
	
	/**
	 * 更新地图，添加气泡
	 */
	Runnable updateMap = new Runnable() {
		
		@Override
		public void run() {
			
			if(carPiontList != null && !carPiontList.isEmpty()) {
				
				// 气泡列表
				CarsPointItemOverlay mOverlay = new CarsPointItemOverlay(B02V00Activity.this, 
								getResources().getDrawable(R.drawable.icon_gcoding),
								mapView, carPiontList);
				
				// 遍历。添加车站信息到地图
				for (Serializable carPoint : carPiontList) {
					
					Baidu baiduCatPoint = (Baidu)carPoint;
					
			         GeoPoint geoPoint = new GeoPoint((int)(baiduCatPoint.getLocation_lat() * 1e6), 
			        		 (int)(baiduCatPoint.getLocation_lng() *  1e6));
			         
			         OverlayItem verlayItem = new OverlayItem(geoPoint, 
			        		 baiduCatPoint.getName(), 
			        		 baiduCatPoint.getAddress());
			         
			         verlayItem.setMarker(getResources().getDrawable(R.drawable.icon_gcoding));
			         
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
	};
	
	/**
	 * 取得位置的时候，移动到当前位置
	 */
	@Override
	public void myLocationOtherDo(BDLocation location) {
		super.myLocationOtherDo(location);
		
		// 移动到定位点
		mapView.getController()
			.animateTo(new GeoPoint((int)(myLocation.latitude* 1e6), (int)(myLocation.longitude *  1e6)));
	}
}

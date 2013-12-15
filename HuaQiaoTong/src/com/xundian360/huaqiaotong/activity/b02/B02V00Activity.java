/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b02;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleMapActivity;
import com.xundian360.huaqiaotong.common.map.SimpleLocationManager;
import com.xundian360.huaqiaotong.modle.b02.Texi;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.modle.com.SerializableList;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.b02.B02BaiduUtil;
import com.xundian360.huaqiaotong.view.b02.B02v00CarView;
import com.xundian360.huaqiaotong.view.b02.CarsPointItemOverlay;

/**
 * 叫车服务（区域选择）
 * @author    LiZhenteng
 * @date      2013年10月14日
 * @version   1.0
 */
public class B02V00Activity extends ComNoTittleMapActivity {
	
	public static final String B02V00ACTIVITY_CARPIONTLIST = "carPiontList";
	
	public static String[] from = {"b02v00ZoonItemName"};
	public static int[] to = {R.id.b02v00ZoonItemName};
	
	// 返回按钮
	ImageButton retBtn;
	
	// 黑车集散点
	SerializableList carPiontList;
	
	// 区域选择List
	ListView zoonList;
	
	// 数据源
	SimpleAdapter zoonAdapter;
	String[] textZoon;
	String[] idZoon;
 	List<Map<String, String>> data = new ArrayList<Map<String, String>>();
	
	// 区域选择按钮
	TextView zoonSelectBtn;
	
	// 车辆列表
	LinearLayout carListLayout;
	
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
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		
		// 设置数据源
		setAdapterDate();
		
		// 设置数据源
		zoonAdapter = new SimpleAdapter(this, data, R.layout.b02v00_zoon_item, from, to);
		
		// 设置位置管理器(定位一次)
		locationManager = new SimpleLocationManager(getApplication(), locationListener, -1);
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		retBtn = (ImageButton) findViewById(R.id.b02v00RetBtn);
		retBtn.setOnClickListener(retBtnClick);
		
		mapView = (MapView) findViewById(R.id.b02v00PointMap);
		
		zoonList = (ListView) findViewById(R.id.b02v00ZoonList);
		zoonList.setAdapter(zoonAdapter);
		zoonList.setOnItemClickListener(zoonListItemClick);
		
		zoonSelectBtn = (TextView) findViewById(R.id.b02v02ShowList);
		zoonSelectBtn.setOnClickListener(zoonSelectBtnClick);
		
		carListLayout = (LinearLayout) findViewById(R.id.b02v00CarListLayout);
	}
	
	/**
	 * 设置数据源
	 */
	private void setAdapterDate() {
		
		// 设置区域
		textZoon = getResources().getStringArray(R.array.b02v00_group_0_items);
		idZoon = getResources().getStringArray(R.array.b02v00_group_0_items_code);
		
		// 设置数据源
		for (int i = 0; i < textZoon.length; i++) {
			Map<String, String> item = new HashMap<String, String>();
			item.put(from[0], textZoon[i]);
			
			data.add(item);
		}
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
	 * 区域选择按钮事件
	 */
	OnClickListener zoonSelectBtnClick = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			
			// 显示可选择区域
			if(zoonList.getVisibility() == View.GONE) {
				zoonList.setVisibility(View.VISIBLE);
				
				// 取消车辆列表显示
				canceCarList();
			} else {
				// 隐藏可选择区域
				zoonList.setVisibility(View.GONE);
			}
		}
	};
	
	/**
	 * 区域选择
	 */
	OnItemClickListener zoonListItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			// 设置ListView不显示
			if(zoonList.getVisibility() == View.VISIBLE) {
				zoonList.setVisibility(View.GONE);
			}
			
			// 取得出租车信息
			getCarListView(arg2);
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
	 * 取得选中的区域对应的车辆信息
	 */
	public void getCarListView(final int zoonIndex) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				final String tittleText = textZoon[zoonIndex];
				String tittleId = idZoon[zoonIndex];
				
				// 取得出租车信息
				final List<Texi> taxis = B02BaiduUtil.getCarsList(B02V00Activity.this, tittleId);
				
				_handler.post(new Runnable() {
					@Override
					public void run() {
						// 判断取得的信息不为空
						if(taxis != null && taxis.size() > 0) {
							
							// 设置车辆列表View
							B02v00CarView carView = new B02v00CarView(B02V00Activity.this, tittleText, taxis);
							carListLayout.removeAllViews();
							carListLayout.addView(carView.get());
							
							// 车辆列表显示
							showCarList();
						} else {
							ShowMessageUtils.show(B02V00Activity.this, "取得数据失败...");
						}
					}
				});
			}
		}).start();
	}
	
	/**
	 * 车辆列表显示
	 */
	public void showCarList() {
		
		// 显示车辆列表
		if(carListLayout.getVisibility() == View.GONE) {
			carListLayout.setVisibility(View.VISIBLE);
		}
	}
	
	/**
	 * 取消车辆列表显示
	 */
	public void canceCarList() {
		
		// 隐藏车辆列表
		if(carListLayout.getVisibility() == View.VISIBLE) {
			carListLayout.setVisibility(View.GONE);
		}
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

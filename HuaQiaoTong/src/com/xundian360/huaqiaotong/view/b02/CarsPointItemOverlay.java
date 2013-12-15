/**
 * 
 */
package com.xundian360.huaqiaotong.view.b02;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.xundian360.huaqiaotong.activity.b02.B02V00Activity;
import com.xundian360.huaqiaotong.activity.b02.B02V01Activity;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.modle.com.SerializableList;
import com.xundian360.huaqiaotong.util.CommonUtil;

/**
 * 车集散点气泡
 * @author  TengTeng
 * @date      2013-10-3
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class CarsPointItemOverlay extends ItemizedOverlay {
	
	private Context context;
	
	// 黑车集散点
	SerializableList carPiontList;
	
	Drawable overlayDrawable;
	MapView mapView;

	public CarsPointItemOverlay(Context context, 
			Drawable arg0, MapView arg1, SerializableList carPiontList) {
		super(arg0, arg1);
		
		this.context = context;
		this.overlayDrawable = arg0;
		this.mapView = arg1;
		this.carPiontList = carPiontList;
	}
	
	@Override
	public boolean onTap(int index){
		
		Baidu carPointItem = (Baidu) carPiontList.get(index);
		
		// 取得出租车信息
		((B02V00Activity)context).getCarListView(index);
		
		return true;
	}
	
}

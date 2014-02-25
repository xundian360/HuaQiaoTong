/**
 * 
 */
package com.xundian360.huaqiaotong.view.b02;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.xundian360.huaqiaotong.activity.b02.B02V02Activity;
import com.xundian360.huaqiaotong.modle.b02.Bick;
import com.xundian360.huaqiaotong.modle.com.SerializableList;

/**
 * 自行车气泡
 * 
 * @author TengTeng
 * @date 2013-10-3
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class BickItemOverlay extends ItemizedOverlay {

	private Context context;

	// 自行车列表
	SerializableList bickList;

	Drawable overlayDrawable;
	MapView mapView;

	public OverlayItem mCurItem;

	public BickItemOverlay(Context context, Drawable arg0, MapView arg1,
			SerializableList bickList) {
		super(arg0, arg1);

		this.context = context;
		this.overlayDrawable = arg0;
		this.mapView = arg1;
		this.bickList = bickList;
	}

	@Override
	public boolean onTap(int index) {

		OverlayItem item = getItem(index);
		mCurItem = item;

		Bick bickItem = (Bick) bickList.get(index);

		// 显示底部Dialog
		((B02V02Activity) context).showBottomDialog(bickItem);

		return true;
	}

	public boolean onTap(GeoPoint pt, MapView mMapView) {

		// 隐藏POP
		((B02V02Activity) context).hiddenBottomDialog();

		return false;
	}

	public OverlayItem getCurItem() {
		return mCurItem;
	}
}

/**
 * 
 */
package com.xundian360.huaqiaotong.common.map;

import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.baidu.mapapi.cloud.CloudPoiInfo;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.view.com.BottomDialog;
import com.xundian360.huaqiaotong.view.com.CommonOverlayView;

/**
 * 云数据气泡
 * @author Administrator
 * @date 2013年11月7日
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class CloudOverlay extends ItemizedOverlay {

	List<CloudPoiInfo> mLbsPoints;
	Activity context;

	public CloudOverlay(Activity context, MapView mMapView) {
		super(null, mMapView);
		this.context = context;
	}

	public void setData(List<CloudPoiInfo> lbsPoints) {
		if (lbsPoints != null) {
			mLbsPoints = lbsPoints;
		}
		for (CloudPoiInfo rec : mLbsPoints) {
			GeoPoint pt = new GeoPoint((int) (rec.latitude * 1e6),
					(int) (rec.longitude * 1e6));
			OverlayItem item = new OverlayItem(pt, rec.title, rec.address);
			Drawable marker1 = this.context.getResources().getDrawable(
					R.drawable.icon_gcoding);
			item.setMarker(marker1);
			addItem(item);
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	protected boolean onTap(int arg0) {
		CloudPoiInfo cloudItem = mLbsPoints.get(arg0);
		
		// 覆层View
		CommonOverlayView overlayView = new CommonOverlayView(context, cloudItem);
		// 底部弹出的Dialog
		BottomDialog bottomDialog = new BottomDialog(context, overlayView.get());
		bottomDialog.show();
		
		return super.onTap(arg0);
	}
}

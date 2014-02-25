/**
 * 
 */
package com.xundian360.huaqiaotong.common.map;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.xundian360.huaqiaotong.modle.b00.Station;
import com.xundian360.huaqiaotong.view.com.BottomDialog;
import com.xundian360.huaqiaotong.view.com.CommonOverlayView;

/**
 * 站点气泡
 * 
 * @author TengTeng
 * @date 2013-10-3
 * @version 1.0
 */
public class StationItemOverlay extends ItemizedOverlay {

	private Context context;

	List<Station> objects;

	Drawable overlayDrawable;
	MapView mapView;

	public StationItemOverlay(Context context, Drawable arg0, MapView arg1,
			List<Station> objects) {
		super(arg0, arg1);

		this.context = context;
		this.overlayDrawable = arg0;
		this.mapView = arg1;
		this.objects = objects;
	}

	@Override
	public boolean onTap(int index) {

		Station stationItem = objects.get(index);

		// 覆层View
		CommonOverlayView overlayView = new CommonOverlayView(context,
				stationItem);
		// 底部弹出的Dialog
		BottomDialog bottomDialog = new BottomDialog(context, overlayView.get());
		bottomDialog.show();

		// ShowMessageUtils.show(context, stationItem.getStationName());

		return true;
	}

}

/**
 * 
 */
package com.xundian360.huaqiaotong.common.map;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.xundian360.huaqiaotong.modle.com.Baidu;
import com.xundian360.huaqiaotong.view.b01.B01v04ItemPoiDialog;

/**
 * 站点气泡
 * 
 * @author TengTeng
 * @date 2013-10-3
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class BaiduItemOverlay extends ItemizedOverlay {

	private Context context;

	List<Baidu> objects;

	public BaiduItemOverlay(Context context, Drawable arg0, MapView arg1,
			List<Baidu> objects) {
		super(arg0, arg1);

		this.context = context;
		this.objects = objects;
	}

	@Override
	public boolean onTap(int index) {

		Baidu baiduItem = objects.get(index);

		// 覆层View
		B01v04ItemPoiDialog poiDialog = new B01v04ItemPoiDialog(context,
				baiduItem);
		poiDialog.show();

		return true;
	}

}

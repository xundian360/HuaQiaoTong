/**
 * 
 */
package com.xundian360.huaqiaotong.adapter.b00;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.common.db.b00.StationOperatingHelper;
import com.xundian360.huaqiaotong.modle.b00.Station;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;

/**
 * 首页站点Adapter
 * 
 * @author TengTeng
 * @date 2013-9-25
 * @version 1.0
 */
public class B00v00ZhandianAdapter extends SimpleAdapter {

	// Key
	public static final String[] from = { "b00v00ZhandianItemName",
			"b00v00ZhandianItemDir", "b00v00ZhandianSaveFlag" };

	// Key ID
	public static final int[] to = { R.id.b00v00ZhandianItemName,
			R.id.b00v00ZhandianItemDir, R.id.b00v00ZhandianSaveFlag };

	Context context;

	// 显示的站点信息
	List<Station> stations;

	// 数据库操作类
	StationOperatingHelper stationDbHelper;

	public B00v00ZhandianAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to, List<Station> stations) {
		super(context, data, resource, from, to);

		this.context = context;
		this.stations = stations;
		stationDbHelper = new StationOperatingHelper(context);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		// 取得Item View
		View view = super.getView(position, convertView, parent);

		// 收藏按钮
		ImageView shoucangImg = (ImageView) view
				.findViewById(R.id.b00v00ZhandianSaveFlag);

		// 判断是否收藏，设值图片状态
		if (stations.get(position).isSave()) {
			shoucangImg.setImageResource(R.drawable.b00v02_shuocang_btn);
		} else {
			shoucangImg.setImageResource(R.drawable.b00v02_no_shuocang_btn);
		}

		// 设值点击事件
		shoucangImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// 当前站点信息
				Station station = stations.get(position);

				// 判断是否收藏
				if (station.isSave()) {

					// 取消收藏
					station.setSave(false);
					// 更新数据库
					stationDbHelper.updateStatus(station);
					// 设值图片状态
					((ImageView) v)
							.setImageResource(R.drawable.b00v02_no_shuocang_btn);

					ShowMessageUtils.show(context,
							R.string.msg_no_shoucang_cancel);

				} else {
					// 收藏
					station.setSave(true);
					// 更新数据库
					stationDbHelper.updateStatus(station);
					// 设值图片状态
					((ImageView) v)
							.setImageResource(R.drawable.b00v02_shuocang_btn);

					ShowMessageUtils.show(context,
							R.string.msg_shoucang_success);
				}
			}
		});

		return view;
	}

}

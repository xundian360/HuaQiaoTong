package com.xundian360.huaqiaotong.adapter.b00;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.common.db.b00.BusOperatingHelper;
import com.xundian360.huaqiaotong.modle.b00.Bus;
import com.xundian360.huaqiaotong.modle.b00.Station;

/**
 * 站点列表
 * 
 * @author TengTeng
 * 
 */
public class B00v04StationsAdapter extends SimpleAdapter {

	public static final String from[] = { "b00v04ItemStationName",
			"b00v04ItemStationAddress" };

	public static final int to[] = { R.id.b00v04ItemStationName,
			R.id.b00v04ItemStationAddress };

	Context context;

	// 传进来的站点对象
	List<Station> stations;

	// 数据库管理类
	BusOperatingHelper helper;

	public B00v04StationsAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to, List<Station> stations) {

		super(context, data, resource, from, to);
		this.context = context;
		this.stations = stations;

		helper = new BusOperatingHelper(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// 取得Item View
		View view = super.getView(position, convertView, parent);

		// 取得子项目
		TextView stationBusesTittle = (TextView) view
				.findViewById(R.id.b00v04ItemStationAddressTittle);

		TextView stationBuses = (TextView) view
				.findViewById(R.id.b00v04ItemStationAddress);

		// 查询当前站点对应的线路信息
		List<Bus> buses = helper.getStationBus(stations.get(position));

		// 给线路信息赋值
		if (buses != null && !buses.isEmpty()) {

			// 设置显示标题
			stationBusesTittle.setVisibility(View.VISIBLE);

			// 拼接公交信息
			StringBuffer busInfo = new StringBuffer();

			// 遍历公交信息
			for (Bus bus : buses) {
				busInfo.append(bus.getRouteKey());
				busInfo.append("，");
			}

			// 去掉最后一个点
			if (busInfo.length() > 0) {
				busInfo.replace(busInfo.length() - 1, busInfo.length(), "");
			}

			// 赋值
			stationBuses.setText(busInfo.toString());
		}
		return view;
	}

}

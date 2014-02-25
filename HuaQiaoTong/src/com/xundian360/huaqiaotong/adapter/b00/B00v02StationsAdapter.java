package com.xundian360.huaqiaotong.adapter.b00;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.b00.Bus;
import com.xundian360.huaqiaotong.modle.b00.NetLine;
import com.xundian360.huaqiaotong.modle.b00.Station;

/**
 * 站点列表
 * 
 * @author TengTeng
 * 
 */
public class B00v02StationsAdapter extends SimpleAdapter {

	// Key
	public static final String[] from = { "XuanluItemName", "XianluItemLogo" };

	// Key ID
	public static final int[] to = { R.id.b00v02XuanluItemName,
			R.id.b00v02XianluItemLogo };

	Context context;

	// 传进来的Bus对象
	private Bus bus;

	// 车辆对应的站点信息
	List<Station> stations;

	// 取得所有即将到站的站点信息
	List<NetLine> nextStopIds = null;

	public B00v02StationsAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to, Bus bus, List<Station> stations) {
		super(context, data, resource, from, to);
		this.context = context;
		this.bus = bus;
		this.stations = stations;
	}

	public B00v02StationsAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
		this.context = context;
	}

	public void setNextStopIds(List<NetLine> nextStopIds) {
		this.nextStopIds = nextStopIds;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// 取得Item View
		View view = super.getView(position, convertView, parent);

		Station station = stations.get(position);
		String stationIs = station.getStationId();

		// Item Logo
		ImageView itemLogo = (ImageView) view
				.findViewById(R.id.b00v02XianluItemLogo);

		// 设置站点颜色
		TextView textView = (TextView) view
				.findViewById(R.id.b00v02XuanluItemName);

		// 设置到站时间
		TextView forecastTime = (TextView) view
				.findViewById(R.id.b00v02XuanluItemTime);

		// 判断当前站点是否已到站，是否为当前站
		if (nextStopIds != null && !nextStopIds.isEmpty()) {

			// 判断当前站点是否快到站
			for (int i = 0; i < nextStopIds.size(); i++) {

				NetLine netLine = nextStopIds.get(i);

				if (stationIs.equals(netLine.getNextStopId())) {

					// 设置前边Logo
					itemLogo.setImageResource(R.drawable.b00_v00_bus_at_1);

					// 当前站点即将到站
					textView.setTextColor(context.getResources().getColor(
							R.color.b00_v02_item_color));

					// 设置预计时间
					forecastTime.setVisibility(View.VISIBLE);

					if ("0".equals(netLine.getTime())) {
						forecastTime.setText(context
								.getString(R.string.b00v02_forecast_text_1));
						forecastTime
								.setTextColor(context.getResources().getColor(
										R.color.b00_v02_forecast_item_color_1));
					} else {
						forecastTime.setText(context.getString(
								R.string.b00v02_forecast_text_0,
								netLine.getTime()));
						forecastTime
								.setTextColor(context.getResources().getColor(
										R.color.b00_v02_forecast_item_color_0));
					}

					continue;
				} else {

					// 设置前边Logo
					itemLogo.setImageResource(R.drawable.b00_v00_bus_at_0);

					// 设置预计时间
					forecastTime.setVisibility(View.GONE);
				}
			}

		} else {

			// 设置前边Logo
			itemLogo.setImageResource(R.drawable.b00_v00_bus_at_0);
			// 设置预计时间
			forecastTime.setVisibility(View.GONE);
		}

		return view;
	}

}

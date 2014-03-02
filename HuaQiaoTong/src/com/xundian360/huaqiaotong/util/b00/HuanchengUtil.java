/**
 * 
 */
package com.xundian360.huaqiaotong.util.b00;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.b00.Bus;
import com.xundian360.huaqiaotong.modle.b00.BusLineConstant;
import com.xundian360.huaqiaotong.modle.b00.Station;
import com.xundian360.huaqiaotong.util.BaiduUtil;

/**
 * 换成工具类
 * 
 * @author Administrator
 * @date 2013年11月8日
 * @version 1.0
 */
public class HuanchengUtil extends BaiduUtil {
	
	// 所有公交信息，放入内存
	public static List<Bus> busesAll;
	
	// 所有站点信息，放入内存
	public static List<Station> stationsAll;

	/**
	 * 根据公交线路，取得站点信息
	 * 
	 * @param context
	 * @param bus
	 * @return
	 */
	public static List<Station> getBusStation(Context context, Bus bus) {
		
		if(stationsAll == null || stationsAll.size() <= 0) {
			stationsAll = new ArrayList<Station>();

			// 该车辆在数组中的位置
			int busIndex = bus.getIndex();

			String[] stationIds = context.getResources().getStringArray(
					BusLineConstant.line_station_id_corr[busIndex]);

			String[] stationNames = context.getResources().getStringArray(
					BusLineConstant.line_station_corr[busIndex]);

			for (int i = 0; i < stationNames.length; i++) {

				Station station = new Station();

				station.set_id(stationIds[i]);
				station.setName(stationNames[i]);
				station.setStationId(stationIds[i]);

				stationsAll.add(station);
			}	
		}
		
		return stationsAll;
	}
	
	/**
	 * 
	 * @param context
	 * @return
	 */
	public static List<Bus> getAllBuses(Context context) {
		
		if(busesAll == null || busesAll.size() <= 0) {
			busesAll = new ArrayList<Bus>();

			String[] buseids = context.getResources().getStringArray(
					R.array.b00_line_id);
			String[] buseNames = context.getResources().getStringArray(
					R.array.b00_line_key);
			String[] buseDir = context.getResources().getStringArray(
					R.array.b00_line_dir);
			String[] buseStart = context.getResources().getStringArray(
					R.array.b00_line_start);

			for (int i = 0; i < buseids.length; i++) {

				Bus bus = new Bus(buseids[i], buseNames[i], buseNames[i],
						buseStart[i], buseDir[i], buseDir[i], i);

				busesAll.add(bus);
			}
		}
		
		return busesAll;
	}

	/**
	 * 根据ID取得车辆信息
	 * 
	 * @param allBuses
	 * @param busId
	 */
	public static Bus getBusById(List<Bus> allBuses, String busId) {

		for (Bus bus : allBuses) {
			if (bus.getRouteId().equals(busId)) {
				return bus;
			}
		}
		return null;
	}
	
	/**
	 * 根据ID取得站点信息
	 * 
	 * @param allBuses
	 * @param busId
	 */
	public static Station getStationById(List<Station> stations,
			String stationId) {
		for (Station station : stations) {
			if(station.getStationId().equals(stationId)) {
				return station;
			}
		}
		return null;
	}

}

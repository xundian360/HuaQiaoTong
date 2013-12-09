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
 * @author  Administrator
 * @date      2013年11月8日
 * @version 1.0
 */
public class HuanchengUtil extends BaiduUtil {
	
	/**
	 * 根据公交线路，取得站点信息
	 * @param context
	 * @param bus
	 * @return
	 */
	public static List<Station> getBusStation(Context context, Bus bus){
		
		List<Station> stations = new ArrayList<Station>();
		
		// 该车辆在数组中的位置
		int busIndex = bus.getIndex();
		
		String[] stationIds = context.getResources()
				.getStringArray(BusLineConstant.line_station_id_corr[busIndex]);
		
		String[] stationNames = context.getResources()
				.getStringArray(BusLineConstant.line_station_corr[busIndex]);
		
		for (int i = 0; i < stationNames.length; i++) {
			
			Station station = new Station();
			
			station.set_id(stationIds[i]);
			station.setName(stationNames[i]);
			station.setStationId(stationIds[i]);
			
			stations.add(station);
		}
		
		return stations;
	}
	
	/**
	 * 
	 * @param context
	 * @return
	 */
	public static List<Bus> getAllBuses(Context context) {
		List<Bus> busesR = new ArrayList<Bus>();
		
		String[] buseids = context.getResources().getStringArray(R.array.b00_line_id);
		String[] buseNames = context.getResources().getStringArray(R.array.b00_line_key);
		String[] buseDir = context.getResources().getStringArray(R.array.b00_line_dir);
		
		for (int i = 0; i < buseids.length; i++) {
			
			Bus bus = new Bus(buseids[i], buseNames[i], buseNames[i], buseDir[i], i);
			
			busesR.add(bus);
		}
		
		return busesR;
	}

}

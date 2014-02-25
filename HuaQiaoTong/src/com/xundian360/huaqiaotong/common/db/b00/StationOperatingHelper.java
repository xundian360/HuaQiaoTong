/**
 * 
 */
package com.xundian360.huaqiaotong.common.db.b00;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.xundian360.huaqiaotong.modle.b00.Bus;
import com.xundian360.huaqiaotong.modle.b00.Station;

/**
 * 站台DB操作类
 * 
 * @author TengTeng
 * @date 2013-9-23
 * @version 1.0
 */
public class StationOperatingHelper extends BusStationOperatingHelper {

	public StationOperatingHelper(Context context) {
		super(context);
	}

	/**
	 * 根据ID取得站点信息
	 * 
	 * @param id
	 * @return
	 */
	public Station getStationByStationId(String id) {

		Station station = null;

		// 取得查找Cursor
		Cursor cursor = selectRecode(TABLE_BUS_STATION, null, "busStopId"
				+ "=?", new String[] { id }, null, null, null);

		// 遍历所有数据库查询的对象
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

			station = new Station();

			// 设置数据库的值到对象
			setCursorToStation(station, cursor);
		}

		// 关闭数据库连接
		close();

		return station;
	}

	/**
	 * 更新站点信息
	 * 
	 * @param station
	 * @return
	 */
	public int updateStatus(Station station) {

		int updateStatus = -1;

		// 组装数据
		ContentValues cv = new ContentValues();

		// cv.put("busStopId", station.getStationId());
		cv.put("busStopName", station.getName());
		cv.put("longitude", station.getLongitude());
		cv.put("latitude", station.getLatitude());
		cv.put("direction", station.getDirection());

		// 收藏
		if (station.isSave()) {
			cv.put("isSave", IS_SAVE_FLAG);
		} else {
			cv.put("isSave", IS_NOT_SAVE_FLAG);
		}

		// 插入数据
		updateStatus = updateRecode(TABLE_BUS_STATION, cv, "busStopId" + "=?",
				new String[] { station.getStationId() });

		// 关闭数据库连接
		close();

		return updateStatus;
	}

	/**
	 * 根据ID取得站点名称
	 * 
	 * @param id
	 * @return
	 */
	public String getStationNameByStationId(String id) {

		String stationName = "未知";

		Station station = getStationByStationId(id);

		if (station != null) {

			stationName = station.getName();
		}

		return stationName;
	}

	/**
	 * 根据ID取得站点信息
	 * 
	 * @param id
	 * @return
	 */
	public List<Station> getStationByStationId(String[] ids) {

		List<Station> stations = new ArrayList<Station>();

		if (ids == null) {
			return stations;
		}

		// 遍历所有数据库查询的对象
		for (String id : ids) {
			// 组装对象
			Station station = getStationByStationId(id);

			// 添加到List
			stations.add(station);
		}

		// 取得查找Cursor
		// Cursor cursor = selectRecode(TABLE_BUS_STATION, null, "busStopId" +
		// " in ?",
		// new String[]{ids.toString()}, null, null, null);
		//
		// // 遍历所有数据库查询的对象
		// for (cursor.moveToFirst(); !cursor.isAfterLast();
		// cursor.moveToNext()) {
		//
		// // 组装对象
		// Station station = new Station();
		//
		// // 设置数据库的值到对象
		// station.set_id(cursor.getString(cursor.getColumnIndex("_id")));
		// station.setStationId(cursor.getString(cursor.getColumnIndex("busStopId")));
		// station.setStationName(cursor.getString(cursor.getColumnIndex("busStopName")));
		// station.setLongitude(cursor.getLong(cursor.getColumnIndex("longitude")));
		// station.setLatitude(cursor.getLong(cursor.getColumnIndex("latitude")));
		// station.setDirection(cursor.getString(cursor.getColumnIndex("direction")));
		//
		// // 添加到List
		// stations.add(station);
		// }
		//
		// // 关闭数据库连接
		// close();

		return stations;
	}

	/**
	 * 取得所有站点信息
	 * 
	 * @return
	 */
	public List<Station> getStations() {

		List<Station> stations = new ArrayList<Station>();

		// 取得查找Cursor
		Cursor cursor = selectRecode(TABLE_BUS_STATION, null, null, null, null,
				null, null);

		// 遍历所有数据库查询的对象
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

			// 组装对象
			Station station = new Station();

			// 设置数据库的值到对象
			setCursorToStation(station, cursor);

			// 添加到List
			stations.add(station);
		}

		// 关闭数据库连接
		close();

		return stations;
	}

	/**
	 * 取得所有收藏的站点信息
	 * 
	 * @return
	 */
	public List<Station> getShoucangStations() {

		List<Station> stations = new ArrayList<Station>();

		// 取得查找Cursor
		Cursor cursor = selectRecode(TABLE_BUS_STATION, null, "isSave" + "=?",
				new String[] { IS_SAVE_FLAG }, null, null, null);

		// 遍历所有数据库查询的对象
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

			// 组装对象
			Station station = new Station();

			// 设置数据库的值到对象
			setCursorToStation(station, cursor);

			// 添加到List
			stations.add(station);
		}

		// 关闭数据库连接
		close();

		return stations;
	}

	/**
	 * 取得不包含在指定List中的站点信息 并把取得的值放入参数返回
	 * 
	 * @return
	 */
	public List<Station> getStationsNotInList(List<Station> stations) {

		// 参数为空
		if (stations == null || stations.isEmpty()) {

			// 取得所有车辆信息，返回
			return getStations();
		}

		/*** 参数非空的时候 ****/

		// 拼接SQL查询条件
		StringBuffer sql_condition = new StringBuffer();
		sql_condition.append("\" busStopId NOT IN \\(");

		// 遍历
		for (Station station : stations) {
			sql_condition.append("'").append(station.getStationId())
					.append("',");
		}

		sql_condition.substring(0, sql_condition.length() - 1);
		sql_condition.append("\\)");

		// 查询数据库
		// 取得查找Cursor
		Cursor cursor = selectRecode(TABLE_BUS_STATION, null,
				sql_condition.toString(), null, null, null, null);

		// 遍历所有数据库查询的对象
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

			// 组装对象
			Station station = new Station();

			// 设置数据库的值到对象
			setCursorToStation(station, cursor);

			// 添加到List
			stations.add(station);
		}

		// 关闭数据库连接
		close();

		return stations;
	}

	/**
	 * 设置数据库的值到对象
	 * 
	 * @param station
	 * @param cursor
	 */
	private void setCursorToStation(Station station, Cursor cursor) {
		station.set_id(cursor.getString(cursor.getColumnIndex("_id")));
		station.setStationId(cursor.getString(cursor
				.getColumnIndex("busStopId")));
		station.setName(cursor.getString(cursor.getColumnIndex("busStopName")));
		station.setLongitude(cursor.getInt(cursor.getColumnIndex("longitude")));
		station.setLatitude(cursor.getInt(cursor.getColumnIndex("latitude")));
		station.setDirection(cursor.getString(cursor
				.getColumnIndex("direction")));

		// 收藏
		if (IS_SAVE_FLAG.equals(cursor.getString(cursor
				.getColumnIndex("isSave")))) {
			station.setSave(true);
		}
	}

	/**
	 * 取得所有站点信息
	 * 
	 * @return
	 */
	public List<Station> getStationsWithBus() {

		// 取得所有站点信息
		List<Station> stations = getStations();

		// 逐个取得各个站点信息对应的车辆信息

		return stations;
	}

	/**
	 * 取得Bus对应的Station信息
	 * 
	 * @param bus
	 */
	public List<Station> getBusStation(Context context, Bus bus) {

		// 当前车辆对应的站点ID（已经按照线路排序）
		List<String> stopIds = getBusStopIds(bus.getRouteId());

		// 取得对应的站点信息
		List<Station> stations = getStationByStationId((String[]) stopIds
				.toArray(new String[stopIds.size()]));

		return stations;
	}
}

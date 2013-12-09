/**
 * 
 */
package com.xundian360.huaqiaotong.common.db.b00;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.xundian360.huaqiaotong.modle.b00.Bus;
import com.xundian360.huaqiaotong.modle.b00.Station;

/**
 * 公交DB操作类
 * @author  TengTeng
 * @date      2013-9-23
 * @version 1.0
 */
public class BusOperatingHelper extends BusStationOperatingHelper {

	public BusOperatingHelper(Context context) {
		super(context);
	}
	
	/**
	 * 根据ID取得车辆信息
	 * @param id
	 * @return
	 */
	public Bus getBusByBusId(String id) {
		
		Bus bus = null;
		
		// 取得查找Cursor
		Cursor cursor = selectRecode(TABLE_BUS_ROUTE, null, "routeId" + "= ?",
				new String[] { id }, null, null, null);
		
		// 遍历所有数据库查询的对象
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			
			bus = new Bus();
			
			// 设置数据库的值到对象
			setCursorToBus(bus, cursor);
		}
		
		// 关闭数据库连接
		close();
		
		return bus;
	}
	
	/**
	 * 更新车辆信息
	 * @param bus
	 * @return
	 */
	public int updateBus(Bus bus) {
		
		int insertStatus = -1;
		
		// 组装数据
		ContentValues cv = new ContentValues();
		
		// cv.put("routeId", bus.getRouteId());
		cv.put("routeName", bus.getRouteName());
		cv.put("routeKey", bus.getRouteKey());
		cv.put("direction", bus.getDirection());
		
		// 收藏
		if(bus.isSave()) {
			cv.put("isSave", IS_SAVE_FLAG);
		} else {
			cv.put("isSave", IS_NOT_SAVE_FLAG);
		}
		
		// 更新数据库
		updateRecode(TABLE_BUS_ROUTE, cv, "routeId" + "= ?",
				new String[] { bus.getRouteId() });
		
		// 关闭数据库连接
		close();
		
		return insertStatus;
	}
	
	/**
	 * 根据ID取得车辆名称
	 * @param id
	 * @return
	 */
	public String getBusNameByBusId(String id) {
		
		String busName = "未知";
		
		Bus bus = getBusByBusId(id);
		
		if(bus != null) {
			
			busName = bus.getRouteKey();
		}
		
		return busName;
	}
	
	/**
	 * 根据ID取得车辆信息
	 * @param id
	 * @return
	 */
	public List<Bus> getBusByBusId(String[] ids) {
		
		List<Bus> buses = new ArrayList<Bus>();
		
		if(ids == null) {
			return buses;
		}
		
		// 遍历所有数据库查询的对象
		for (String id: ids) {
			// 组装对象
			Bus bus = getBusByBusId(id);
			
			// 添加到List
			buses.add(bus);
		}
		
		return buses;
	}
	
	/**
	 * 取得所有车辆信息
	 * @return
	 */
	public List<Bus> getAllBuses() {
		
		List<Bus> buses = new ArrayList<Bus>();
		
		// 取得查找Cursor
		Cursor cursor = selectRecode(TABLE_BUS_ROUTE, null, null,
				null, null, null, null);
		
		// 遍历所有数据库查询的对象
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			
			// 组装对象
			Bus bus = new Bus();
			
			// 设置数据库的值到对象
			setCursorToBus(bus, cursor);
			
			// 添加到List
			buses.add(bus);
		}
		
		// 关闭数据库连接
		close();
		
		return buses;
	}
	
	/**
	 * 取得所有收藏的车辆信息
	 * @return
	 */
	public List<Bus> getAllShoucangBuses() {
		
		List<Bus> buses = new ArrayList<Bus>();
		
		// 取得查找Cursor
		Cursor cursor = selectRecode(TABLE_BUS_ROUTE, null, "isSave" + "=?",
				new String[] { IS_SAVE_FLAG }, null, null, null);
		
		// 遍历所有数据库查询的对象
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			
			// 组装对象
			Bus bus = new Bus();
			
			// 设置数据库的值到对象
			setCursorToBus(bus, cursor);
			
			// 添加到List
			buses.add(bus);
		}
		
		// 关闭数据库连接
		close();
		
		return buses;
	}
	
	/**
	 * 设置数据库的值到对象
	 * @param bus
	 * @param cursor
	 */
	private void setCursorToBus(Bus bus, Cursor cursor) {
		
		// 设置数据库的值到对象
		bus.set_id(cursor.getString(cursor.getColumnIndex("_id")));
		bus.setRouteId(cursor.getString(cursor.getColumnIndex("routeId")));
		bus.setRouteName(cursor.getString(cursor.getColumnIndex("routeName")));
		bus.setRouteKey(cursor.getString(cursor.getColumnIndex("routeKey")));
		bus.setDirection(cursor.getString(cursor.getColumnIndex("direction")));
		
		// 收藏
		if(IS_SAVE_FLAG.equals(cursor.getString(cursor.getColumnIndex("isSave")))) {
			bus.setSave(true);
		}
	}
	
	/**
	 * 取得Station对应的BusID
	 * @param busStopId
	 * @return
	 */
	public List<String> getStationBusIds(String busStopId) {
		
		List<String> routeIds = new ArrayList<String>();
		
		// 取得查找Cursor
		Cursor cursor = selectRecode(TABLE_BUS_STOP_SIGN, null, "busStopId" + "=?",
				new String[] { busStopId }, null, null, null);
		
		// 遍历所有数据库查询的对象
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			
			routeIds.add(cursor.getString(cursor.getColumnIndex("routeId")));
			Log.d("debug", "busStopId > " + cursor.getString(cursor.getColumnIndex("busStopId")));
		}
		
		// 关闭数据库连接
		close();
		
		return routeIds;
	}
	
	/**
	 * 取得Station对应的Bus信息
	 * @param bus
	 */
	public List<Bus> getStationBus(Station station) {
		
		// 取得Station对应的BusID
		List<String> busesIds = getStationBusIds(station.getStationId());

		// 根据ID取得车辆信息
		List<Bus> buses = getBusByBusId((String[])busesIds.toArray(new String[busesIds.size()]));
		
		return buses;
	}
	
	/**
	 * 取得Station对应的Bus信息
	 * 不包含参数指定的信息
	 * @param bus
	 */
	public List<Bus> getStationBusNotInList(Station station, List<Bus> buses) {
		
		// 参数为空，返回全部车辆信息
		if(buses == null || buses.isEmpty()) {
			return getStationBus(station);
		}
		
		// 拼接SQL查询条件
		StringBuffer sql_condition = new StringBuffer();
		sql_condition.append("busStopId NOT IN (");
		
		// 遍历
		for (Bus bus : buses) {
			sql_condition.append("'")
									.append(bus.getRouteId())
									.append("',");
		}
		
		sql_condition = sql_condition.replace(sql_condition.length() - 1, sql_condition.length(), "");
		
		sql_condition.append(")")
								.append(" and busStopId=?");
		
		// 取得指定的ID信息
		List<String> routeIds = new ArrayList<String>();
		
		// 取得查找Cursor
		Cursor cursor = selectRecode(TABLE_BUS_STOP_SIGN, null, sql_condition.toString(),
				new String[] { station.getStationId() }, null, null, null);
		
		// 遍历所有数据库查询的对象
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			routeIds.add(cursor.getString(cursor.getColumnIndex("routeId")));
		}
		
		// 根据ID取得车辆信息
		List<Bus> buses2 = getBusByBusId((String[])routeIds.toArray(new String[routeIds.size()]));
		
		buses.addAll(buses2);

		return buses;
	}

}

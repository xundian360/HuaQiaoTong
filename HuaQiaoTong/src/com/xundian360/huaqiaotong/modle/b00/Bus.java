package com.xundian360.huaqiaotong.modle.b00;

import java.io.Serializable;
import java.util.List;

/**
 * 公交对象
 * 
 * @author TengTeng
 * 
 */
public class Bus implements Serializable {

	private static final long serialVersionUID = -5674686531986958670L;

	// // 上行
	// public static final String DIRECTION_UPLINK = "0";
	// public static final String DIRECTION_UPLINK_HAN = "上行";
	// // 下行
	// public static final String DIRECTION_DOWNSTREAM = "1";
	// public static final String DIRECTION_DOWNSTREAM_HAN = "下行";

	// 数据库使用主键
	private String _id;

	// 公交ID
	private String routeId;
	// 公交名称
	private String routeName;
	// 公交Key
	private String routeKey;
	// 公交方向（0:上行，1:下行）
	private String direction;
	// 首站
	private String firstStation;
	// 末站
	private String lastStation;
	// 当前站点Id
	private String currentStationId;
	// 当前站点
	private String currentStation;
	// 公交经过所有站点
	private List<Station> stations;
	// 到站时间
	private String daozhangTime;
	// 线路对应的站编号
	private List<String> routeStopNos;
	// 站编号对应的站点编号
	private List<String> busStopIds;
	// 是否收藏(默认没有收藏)
	private boolean isSave = false;

	// 当前线路在数字中的编号
	private int index = 0;

	public Bus() {
		super();
	}

	public Bus(String routeId, String routeName, String routeKey,
			String direction) {
		super();
		this.routeId = routeId;
		this.routeName = routeName;
		this.routeKey = routeKey;
		this.direction = direction;
	}

	public Bus(String routeId, String routeName, String routeKey,
			String direction, int index) {
		super();
		this.routeId = routeId;
		this.routeName = routeName;
		this.routeKey = routeKey;
		this.direction = direction;
		this.index = index;
	}

	public Bus(String routeId, String routeName, String routeKey, String start,
			String end, String direction, int index) {
		super();
		this.routeId = routeId;
		this.routeName = routeName;
		this.routeKey = routeKey;
		this.firstStation = start;
		this.lastStation = end;
		this.direction = direction;
		this.index = index;
	}

	public Bus(String routeId, List<String> routeStopNos,
			List<String> busStopIds) {
		super();
		this.routeId = routeId;
		this.routeStopNos = routeStopNos;
		this.busStopIds = busStopIds;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getRouteKey() {
		return routeKey;
	}

	public void setRouteKey(String routeKey) {
		this.routeKey = routeKey;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getFirstStation() {
		return firstStation;
	}

	public void setFirstStation(String firstStation) {
		this.firstStation = firstStation;
	}

	public String getLastStation() {
		return lastStation;
	}

	public void setLastStation(String lastStation) {
		this.lastStation = lastStation;
	}

	public String getCurrentStation() {
		return currentStation;
	}

	public void setCurrentStation(String currentStation) {
		this.currentStation = currentStation;
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public String getDaozhangTime() {
		return daozhangTime;
	}

	public void setDaozhangTime(String daozhangTime) {
		this.daozhangTime = daozhangTime;
	}

	public List<String> getRouteStopNos() {
		return routeStopNos;
	}

	public void setRouteStopNos(List<String> routeStopNos) {
		this.routeStopNos = routeStopNos;
	}

	public List<String> getBusStopIds() {
		return busStopIds;
	}

	public void setBusStopIds(List<String> busStopIds) {
		this.busStopIds = busStopIds;
	}

	public String getCurrentStationId() {
		return currentStationId;
	}

	public void setCurrentStationId(String currentStationId) {
		this.currentStationId = currentStationId;
	}

	public boolean isSave() {
		return isSave;
	}

	public void setSave(boolean isSave) {
		this.isSave = isSave;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	// /**
	// * 取得车辆方向的文字表示
	// * @return
	// */
	// public String getDirectionHan() {
	//
	// return DIRECTION_UPLINK.equals(direction)
	// ? DIRECTION_UPLINK_HAN : DIRECTION_DOWNSTREAM_HAN;
	// }

}

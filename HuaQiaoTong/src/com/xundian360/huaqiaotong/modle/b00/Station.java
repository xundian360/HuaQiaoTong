package com.xundian360.huaqiaotong.modle.b00;

import java.util.List;

import com.xundian360.huaqiaotong.modle.com.Baidu;

/**
 * 站点信息
 * @author TengTeng
 *
 */
public class Station extends Baidu {

	private static final long serialVersionUID = 1L;
	
	// 数据库使用主键
	private String _id;
	
	// 站点ID
	private String stationId;
	// 方向
	private String direction;
	// 车站位置（反编译）
	private String stationLocation;
	// 经过该站点的所有公交信息
	private List<Bus> buses;
	
	// 是否收藏(默认没有收藏)
	private boolean isSave = false;
	
	public Station() {
		super();
	}
	
	public Station(String stationId, String name, int longitude,
			int latitude, String direction) {
		super();
		this.stationId = stationId;
		this.name = name;
		this.location_lng = longitude;
		this.location_lat = latitude;
		this.direction = direction;
	}
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String stationName) {
		this.name = stationName;
	}
	public double getLongitude() {
		return location_lng;
	}
	public void setLongitude(int longitude) {
		this.location_lng = longitude;
	}
	public double getLatitude() {
		return location_lat;
	}
	public void setLatitude(int latitude) {
		this.location_lat = latitude;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getStationLocation() {
		return stationLocation;
	}
	public void setStationLocation(String stationLocation) {
		this.stationLocation = stationLocation;
	}
	public List<Bus> getBuses() {
		return buses;
	}
	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}
	public boolean isSave() {
		return isSave;
	}
	public void setSave(boolean isSave) {
		this.isSave = isSave;
	}
	
}

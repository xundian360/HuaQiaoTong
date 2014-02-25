/**
 * 
 */
package com.xundian360.huaqiaotong.modle.b00;

import java.io.Serializable;

/**
 * 线路情况（网络调用) <disc> 请求：http://221.6.86.251:8899/GetRouteBusInfo?routeId=11//
 * routeId代表线路id【注：从线路表中取得线路Id]】 响应的json格式解释： {"routeId":1, //1.1 正在查询的线路id
 * "stopNoList":[ //1.2 线路上公交位置信息数组 {"nextStopId ":20, //2.1 下一站的站台id
 * "licence":苏E-MF800”, //2.2 公交车的车牌号 "nextStopNo":20, //2.3 下一站的站台位置号 "time":0,
 * //2.4 抵达下一站的时间 "distance":409, //2.5抵达下一站的距离 "latitude":3121563, //2.6
 * 公交车位置的纬度 "longitude":12145681}, //2.7公交车位置的经度 … ] }
 * 
 * 
 * </disc>
 * 
 * @author tengteng
 * @date 2013-12-1
 * @version 1.0
 */
public class NetLine implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nextStopId;
	private String licence;
	private String nextStopNo;
	private String time;
	private String distance;
	private String latitude;
	private String longitude;

	public String getNextStopId() {
		return nextStopId;
	}

	public void setNextStopId(String nextStopId) {
		this.nextStopId = nextStopId;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public String getNextStopNo() {
		return nextStopNo;
	}

	public void setNextStopNo(String nextStopNo) {
		this.nextStopNo = nextStopNo;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}

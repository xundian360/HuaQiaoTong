/**
 * 
 */
package com.xundian360.huaqiaotong.modle.b00;

import java.io.Serializable;

/**
 * 换乘对象
 * 
 * @author TengTeng
 * @date 2013-10-3
 * @version 1.0
 */
public class Huancheng implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 换乘信息为无效数据
	public static final String DATA_NULL = "0";
	
	// 开始站点ID
	private String busStopId0;
	private String busStopName0;
	
	// 结束站点ID
	private String busStopId1;
	private String busStopName1;
	
	// 第一次乘车，所乘车的线路id
	private String disNo1;
	private String disName1;
	
	// 第二次乘车，所乘车的线路id
	private String disNo2;
	private String disName2;
	
	// 第三次乘车，所乘车的线路id
	private String disNo3;
	private String disName3;
	
	// 第一次乘车，相隔几站
	private String routeId1;
	// 第二次乘车，相隔几站
	private String routeId2;
	// 第三次乘车，相隔几站
	private String routeId3;
	
	// 第一次乘车，到哪一站下车的站台id
	private String stopId1;
	private String stopName1;
	
	// 第二乘车，到哪一站下车的站台id
	private String stopId2;
	private String stopName2;
	
	public String getBusStopId0() {
		return busStopId0;
	}
	public void setBusStopId0(String busStopId0) {
		this.busStopId0 = busStopId0;
	}
	public String getBusStopName0() {
		return busStopName0;
	}
	public void setBusStopName0(String busStopName0) {
		this.busStopName0 = busStopName0;
	}
	public String getBusStopId1() {
		return busStopId1;
	}
	public void setBusStopId1(String busStopId1) {
		this.busStopId1 = busStopId1;
	}
	public String getBusStopName1() {
		return busStopName1;
	}
	public void setBusStopName1(String busStopName1) {
		this.busStopName1 = busStopName1;
	}
	public String getDisNo1() {
		return disNo1;
	}
	public void setDisNo1(String disNo1) {
		this.disNo1 = disNo1;
	}
	public String getDisNo2() {
		return disNo2;
	}
	public void setDisNo2(String disNo2) {
		this.disNo2 = disNo2;
	}
	public String getDisNo3() {
		return disNo3;
	}
	public void setDisNo3(String disNo3) {
		this.disNo3 = disNo3;
	}
	public String getRouteId1() {
		return routeId1;
	}
	public void setRouteId1(String routeId1) {
		this.routeId1 = routeId1;
	}
	public String getRouteId2() {
		return routeId2;
	}
	public void setRouteId2(String routeId2) {
		this.routeId2 = routeId2;
	}
	public String getRouteId3() {
		return routeId3;
	}
	public void setRouteId3(String routeId3) {
		this.routeId3 = routeId3;
	}
	public String getStopId1() {
		return stopId1;
	}
	public void setStopId1(String stopId1) {
		this.stopId1 = stopId1;
	}
	public String getStopId2() {
		return stopId2;
	}
	public void setStopId2(String stopId2) {
		this.stopId2 = stopId2;
	}
	public String getDisName1() {
		return disName1;
	}
	public void setDisName1(String disName1) {
		this.disName1 = disName1;
	}
	public String getDisName2() {
		return disName2;
	}
	public void setDisName2(String disName2) {
		this.disName2 = disName2;
	}
	public String getDisName3() {
		return disName3;
	}
	public void setDisName3(String disName3) {
		this.disName3 = disName3;
	}
	public String getStopName1() {
		return stopName1;
	}
	public void setStopName1(String stopName1) {
		this.stopName1 = stopName1;
	}
	public String getStopName2() {
		return stopName2;
	}
	public void setStopName2(String stopName2) {
		this.stopName2 = stopName2;
	}
	
}

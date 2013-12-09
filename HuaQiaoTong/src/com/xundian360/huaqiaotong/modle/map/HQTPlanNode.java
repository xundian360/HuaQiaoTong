/**
 * 
 */
package com.xundian360.huaqiaotong.modle.map;

import java.io.Serializable;

/**
 * 路线结点信息类
 * @author  Administrator
 * @date      2013年11月8日
 * @version 1.0
 */
public class HQTPlanNode implements Serializable {
	
	private static final long serialVersionUID = -6018813357062539316L;
	
	// 名称
	private String name;
	// 纬度
	private int latitudeE6;
	// 经度
	private int longitudeE6;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLatitudeE6() {
		return latitudeE6;
	}
	public void setLatitudeE6(int latitudeE6) {
		this.latitudeE6 = latitudeE6;
	}
	public int getLongitudeE6() {
		return longitudeE6;
	}
	public void setLongitudeE6(int longitudeE6) {
		this.longitudeE6 = longitudeE6;
	}
}

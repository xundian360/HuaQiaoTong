/**
 * 
 */
package com.xundian360.huaqiaotong.modle.b02;

import java.io.Serializable;

import com.xundian360.huaqiaotong.modle.com.Baidu;

/**
 * 自行车对象
 * 
 * @author LiZhenteng
 * @date 2013年10月15日
 * @version 1.0
 */
public class Bick extends Baidu implements Serializable {

	private static final long serialVersionUID = 1L;

	// 总数量
	private int allNum;
	// 剩余数量
	private int lastNum;

	public Bick() {
		super();
	}

	public Bick(String address, int allNum, int lastNum, double location_lat,
			double location_lng) {
		super();
		this.address = address;
		this.allNum = allNum;
		this.lastNum = lastNum;
		this.location_lat = location_lat;
		this.location_lng = location_lng;
	}

	public int getAllNum() {
		return allNum;
	}

	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}

	public int getLastNum() {
		return lastNum;
	}

	public void setLastNum(int lastNum) {
		this.lastNum = lastNum;
	}
}

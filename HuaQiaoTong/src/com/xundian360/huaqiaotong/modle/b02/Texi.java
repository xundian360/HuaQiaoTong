package com.xundian360.huaqiaotong.modle.b02;

import java.io.Serializable;

/**
 * 出租车对象
 * 
 * @author LiZhenteng
 * @date 2013年10月15日
 * @version 1.0
 */
public class Texi implements Serializable {

	private static final long serialVersionUID = 1L;

	// ID
	private String id;
	// 名车
	private String name;
	// 公司
	private String company;
	// 车牌
	private String plate;
	// 电话
	private String phone;
	// 好评数
	private String favorateNum;
	// 地址
	private String add;
	// 车类型
	private String carType;
	// 营业时间
	private String time;
	// 其他
	private String com;

	public Texi() {
		super();
	}

	public Texi(String name, String company, String plate, String phone,
			String favorateNum) {
		super();
		this.name = name;
		this.company = company;
		this.plate = plate;
		this.phone = phone;
		this.favorateNum = favorateNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFavorateNum() {
		return favorateNum;
	}

	public void setFavorateNum(String favorateNum) {
		this.favorateNum = favorateNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

}

/**
 * 
 */
package com.xundian360.huaqiaotong.modle.com;

import java.io.Serializable;

/**
 * 用户对象
 * @author  TengTeng
 * @date      2013年11月4日
 * @version 1.0
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 男
	public static final int SEX_NAN = 0;
	// 女
	public static final int SEX_NV = 1;
	// 男
	public static final String SEX_NAN_TEXT = "男";
	// 女
	public static final String SEX_NV_TEXT = "女";
	
	// 用户ID
	private String userId;
	// 登录名
	private String loginName;
	// 昵称
	private String name;
	// 性别(0:男，1：女)
	private int sex;
	// 所在地
	private String location;
	// 简介
	private String disc;
	// QQ
	private String qq;
	// 公司
	private String company;
	// 公司
	private String logoPath;
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDisc() {
		return disc;
	}
	public void setDisc(String disc) {
		this.disc = disc;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

}

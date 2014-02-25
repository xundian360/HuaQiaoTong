/**
 * 
 */
package com.xundian360.huaqiaotong.modle.com;

import java.io.Serializable;

/**
 * 百度对象，评论
 * 
 * @author Administrator
 * @date 2013年12月20日
 * @version 1.0
 */
public class BaiduComment implements Serializable {

	private static final long serialVersionUID = 1L;
	// ID
	private String id;
	// 评论商店ID
	private String shopId;
	// 评论用户ID
	private String userId;
	// 评论用户
	private String userName;
	// 评论用户图片路径
	private String userLogoPath;
	// 评分
	private String score;
	// 评论内容
	private String dic;
	// 评论时间
	private String time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLogoPath() {
		return userLogoPath;
	}

	public void setUserLogoPath(String userLogoPath) {
		this.userLogoPath = userLogoPath;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getDic() {
		return dic;
	}

	public void setDic(String dic) {
		this.dic = dic;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}

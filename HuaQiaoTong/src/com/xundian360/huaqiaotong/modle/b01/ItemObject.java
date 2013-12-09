/**
 * 
 */
package com.xundian360.huaqiaotong.modle.b01;

import java.io.Serializable;

/**
 * 当前显示的对象
 * @author  Administrator
 * @date      2013年12月4日
 * @version 1.0
 */
public class ItemObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Tittle Id
	private int tittleId;
	
	// Key Id
	private int keyId;
	
	// 筛选导航 Id
	private int navId;
	
	// 筛选导航项目名称 Id
	private int[] navItemTextId;
	
	// 筛选导航项目名称 Id
	private int[] navItemKeyId;
	
	public ItemObject(int tittleId, int keyId, int navId, int[] navItemTextId, int[] navItemKeyId) {
		super();
		this.tittleId = tittleId;
		this.keyId = keyId;
		this.navId = navId;
		this.navItemTextId = navItemTextId;
		this.navItemKeyId = navItemKeyId;
	}

	public int getTittleId() {
		return tittleId;
	}

	public int getKeyId() {
		return keyId;
	}

	public int getNavId() {
		return navId;
	}

	public int[] getNavItemTextId() {
		return navItemTextId;
	}

	public int[] getNavItemKeyId() {
		return navItemKeyId;
	}

}

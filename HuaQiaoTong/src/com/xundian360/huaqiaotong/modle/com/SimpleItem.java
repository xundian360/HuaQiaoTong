/**
 * 
 */
package com.xundian360.huaqiaotong.modle.com;

import java.io.Serializable;

/**
 * 共通：简单对象
 * 
 * @author LiZhenteng
 * @date 2013年10月15日
 * @version 1.0
 */
public class SimpleItem implements Serializable {

	private static final long serialVersionUID = 1L;

	public SimpleItem(String name, String code, boolean isTrue) {
		super();
		this.name = name;
		this.code = code;
		this.isTrue = isTrue;
	}

	public SimpleItem() {
		super();
	}

	// 名称
	private String name;
	// 编号
	private String code;
	// 标记
	private boolean isTrue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isTrue() {
		return isTrue;
	}

	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}
}

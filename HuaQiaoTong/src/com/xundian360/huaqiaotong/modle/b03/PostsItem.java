/**
 * 
 */
package com.xundian360.huaqiaotong.modle.b03;

/**
 * 帖子明细列表
 * 
 * @author TengTeng
 * @date 2013年10月28日
 * @version 1.0
 */
public class PostsItem {

	public static final String IMG_KEY = "img";
	public static final String TXT_KEY = "txt";

	// 类型：txt:文本，img:图片
	private String type;
	// 内容：txt的时候，文字。Img的时候，URL
	private String msg;
	// 名称
	private String name;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

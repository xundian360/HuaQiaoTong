/**
 * 
 */
package com.xundian360.huaqiaotong.modle.b03;

import java.io.Serializable;

/**
 * 帖子组对象
 * 
 * @author TeneTeng
 * @date 2014-1-11
 * @version 1.0
 */
public class PostGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	// 组ID
	private String groupId;
	// 组名称
	private String groupName;

	public PostGroup() {
	}

	public PostGroup(String groupId, String groupName) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}

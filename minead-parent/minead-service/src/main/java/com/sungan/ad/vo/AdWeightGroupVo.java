package com.sungan.ad.vo;
/**
 * 说明:
 */

import java.io.Serializable;

public class AdWeightGroupVo implements Serializable {
	private static final long serialVersionUID = -2488219718244324704L;

	private Long id;
	private String groupName;
	private String parentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}

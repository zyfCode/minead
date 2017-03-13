package com.sungan.ad.vo;
/**
 * 说明:
 */

import java.io.Serializable;

public class AdWeightGroupVo implements Serializable {
	private static final long serialVersionUID = -2488219718244324704L;

	private Long id;
	private String groupName;
	private Long parentId;
	private String isLeaf;
	private String isLeafCn;
	private String isDefault;
	private String isDefaultCn;
	private Integer taskCount;
	
	public Integer getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getIsLeafCn() {
		return isLeafCn;
	}

	public void setIsLeafCn(String isLeafCn) {
		this.isLeafCn = isLeafCn;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getIsDefaultCn() {
		return isDefaultCn;
	}

	public void setIsDefaultCn(String isDefaultCn) {
		this.isDefaultCn = isDefaultCn;
	}

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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}

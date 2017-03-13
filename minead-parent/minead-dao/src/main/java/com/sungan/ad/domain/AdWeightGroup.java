package com.sungan.ad.domain;
/**
 * 说明:
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sungan.ad.expand.common.annotation.StatusCn;

@Entity
@Table(name = "t_adhour_weight_group")
public class AdWeightGroup implements Serializable {
	private static final long serialVersionUID = -2488219718244324704L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length=64)
	private String groupName;
	
	/**非叶子节点*/
	public static final String ISNOTLEAF = "0";
	/**叶子节点*/
	public static final String ISLEAF = "1";
	/**
	 * 是否叶子节点
	 */
	@Column(length=2)
	@StatusCn({ISNOTLEAF+"=非叶子",ISLEAF+"=叶子"})
	private String isLeaf;
	
	/**非叶子节点*/
	public static final String ISNOTDEFAULT = "0";
	/**叶子节点*/
	public static final String ISDEFAULT = "1";
	@Column(length=2)
	@StatusCn({ISNOTDEFAULT+"=非默认组",ISDEFAULT+"=默认组"})
	private String isDefault;
	private Long parentId;
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

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
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

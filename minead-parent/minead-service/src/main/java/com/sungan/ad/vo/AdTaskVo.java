package com.sungan.ad.vo;

import java.io.Serializable;
import java.util.Date;

import com.sungan.ad.expand.common.annotation.DateToStr;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class AdTaskVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	/**
	 * 任务名称
	 */
	private String name;
	/**
	 * 任务次数
	 */
	private Long count;
	/**
	 * 任务类型，0 小时任务 1是循环任务
	 */
	private String type;
	private String typeCn;

	private String status;
	private String statusCn;
	/**
	 * 上次任务下发时间
	 */
	private Date preIssuedTime;
	private String preIssuedTimeStr;
	private String clazzName;
	private Date createTime;
	private String createTimeStr;
	private Date updateTime;
	private String updateTimeStr;
	
	public String getClazzName() {
		return clazzName;
	}

	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeCn() {
		return typeCn;
	}

	public void setTypeCn(String typeCn) {
		this.typeCn = typeCn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusCn() {
		return statusCn;
	}

	public void setStatusCn(String statusCn) {
		this.statusCn = statusCn;
	}

	public Date getPreIssuedTime() {
		return preIssuedTime;
	}

	public void setPreIssuedTime(Date preIssuedTime) {
		this.preIssuedTime = preIssuedTime;
	}

	public String getPreIssuedTimeStr() {
		return preIssuedTimeStr;
	}

	public void setPreIssuedTimeStr(String preIssuedTimeStr) {
		this.preIssuedTimeStr = preIssuedTimeStr;
	}
}

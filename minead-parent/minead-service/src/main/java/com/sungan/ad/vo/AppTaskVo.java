package com.sungan.ad.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2017年1月18日 上午10:35:26
 * @version V1.1
 */
public class AppTaskVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long clientId;
	private Long clientIpid;
	private Long adTaskid;
	/** 0-24通过率 */
	private Integer throwRate;
	private Long throwCount;
	private String name;
	private String type;
	private String status;
	private String statusCn;
	private Long count;
	private Long doneCount;
	private Long failCount;
	private Date taskRunTime;
	private String taskRunTimeStr;
	private Date crateTime;
	private String crateTimeStr;
	private Date updateTime;
	private String updateTimeStr;

	public Integer getThrowRate() {
		return throwRate;
	}
	
	public Long getThrowCount() {
		return throwCount;
	}

	public void setThrowCount(Long throwCount) {
		this.throwCount = throwCount;
	}

	public void setThrowRate(Integer throwRate) {
		this.throwRate = throwRate;
	}
	public Long getClientId() {
		return clientId;
	}
	public Long getAdTaskid() {
		return adTaskid;
	}

	public void setAdTaskid(Long adTaskid) {
		this.adTaskid = adTaskid;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getClientIpid() {
		return clientIpid;
	}

	public void setClientIpid(Long clientIpid) {
		this.clientIpid = clientIpid;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getDoneCount() {
		return doneCount;
	}

	public void setDoneCount(Long doneCount) {
		this.doneCount = doneCount;
	}

	public Long getFailCount() {
		return failCount;
	}

	public void setFailCount(Long failCount) {
		this.failCount = failCount;
	}

	public Date getTaskRunTime() {
		return taskRunTime;
	}

	public void setTaskRunTime(Date taskRunTime) {
		this.taskRunTime = taskRunTime;
	}

	public String getTaskRunTimeStr() {
		return taskRunTimeStr;
	}

	public void setTaskRunTimeStr(String taskRunTimeStr) {
		this.taskRunTimeStr = taskRunTimeStr;
	}

	public Date getCrateTime() {
		return crateTime;
	}

	public void setCrateTime(Date crateTime) {
		this.crateTime = crateTime;
	}

	public String getCrateTimeStr() {
		return crateTimeStr;
	}

	public void setCrateTimeStr(String crateTimeStr) {
		this.crateTimeStr = crateTimeStr;
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

}

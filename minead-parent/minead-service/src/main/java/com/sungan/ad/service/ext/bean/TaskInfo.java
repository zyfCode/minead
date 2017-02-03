package com.sungan.ad.service.ext.bean;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class TaskInfo {
	private Long taskId;
	private Long count;
	private Long doneCount;
	private Long throwCount;
	private Long failCount;
	private Long clientId;
	private String clientMac;
	private String clientIp;
	
	public Long getFailCount() {
		return failCount;
	}

	public void setFailCount(Long failCount) {
		this.failCount = failCount;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
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

	public Long getThrowCount() {
		return throwCount;
	}

	public void setThrowCount(Long throwCount) {
		this.throwCount = throwCount;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getClientMac() {
		return clientMac;
	}

	public void setClientMac(String clientMac) {
		this.clientMac = clientMac;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
}

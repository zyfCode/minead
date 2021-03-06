package com.sungan.ad.expand.common.bean;

import java.io.Serializable;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class TaskInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long appTaskId;
	private Long adTaskId;
	private Long count;
	private Long doneCount;
	private Long throwCount;
	private Long failCount;
	private Long clientId;
	private String clientMac;
	private String clientIp;


	public Long getAppTaskId() {
		return appTaskId;
	}

	public void setAppTaskId(Long appTaskId) {
		this.appTaskId = appTaskId;
	}

	public Long getAdTaskId() {
		return adTaskId;
	}

	public void setAdTaskId(Long adTaskId) {
		this.adTaskId = adTaskId;
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

	public Long getFailCount() {
		return failCount;
	}

	public void setFailCount(Long failCount) {
		this.failCount = failCount;
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

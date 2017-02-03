package com.sungan.ad.expand.common.bean;

import java.util.List;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class TaskRequest {
	private String serialNo;
	private String mac;
	private String adClientIp;
	private Long adClientId;
	private List<TaskInfo> info;

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getAdClientIp() {
		return adClientIp;
	}

	public void setAdClientIp(String adClientIp) {
		this.adClientIp = adClientIp;
	}

	public List<TaskInfo> getInfo() {
		return info;
	}

	public void setInfo(List<TaskInfo> info) {
		this.info = info;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Long getAdClientId() {
		return adClientId;
	}

	public void setAdClientId(Long adClientId) {
		this.adClientId = adClientId;
	}
}

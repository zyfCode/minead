package com.sungan.ad.expand.common.bean;
/**
 * 说明:
 * @version V1.1
 */
public class TaskResonseInfo {
	private String serialNo;
	private Long adClientId;
	private Long adTaskId;
	private Long appTaskId;
	private Long count;
	private Long doneCount;
	private Long throwRate;
	private String ip;
	private String mac;
	private String adClazzName;
	private String action;
	
	public String getAdClazzName() {
		return adClazzName;
	}
	public void setAdClazzName(String adClazzName) {
		this.adClazzName = adClazzName;
	}
	public void setAdClientId(Long adClientId) {
		this.adClientId = adClientId;
	}
	public Long getAdClientId() {
		return adClientId;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public Long getAdTaskId() {
		return adTaskId;
	}
	public void setAdTaskId(Long adTaskId) {
		this.adTaskId = adTaskId;
	}
	public Long getAppTaskId() {
		return appTaskId;
	}
	public void setAppTaskId(Long appTaskId) {
		this.appTaskId = appTaskId;
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
	public Long getThrowRate() {
		return throwRate;
	}
	public void setThrowRate(Long throwRate) {
		this.throwRate = throwRate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}

package com.sungan.ad.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class AdClientVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String mac;
	private String currentIp;
	private String preIp;
	private Integer countIps;
	private String ipstDesc;
	/**上次心跳时间*/
	private Date preAccessTime;
	private String preAccessTimeStr;
	private Date createTime;
	private String createTimeStr;
	/**系统类型*/
	private String systemType;
	private String status;
	private String sysOs;
	private String statusCn;
	private Long groupId;
	private String groupName;
	private Date effectTime;
	private String effectTimeStr;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getEffectTimeStr() {
		return effectTimeStr;
	}
	public void setEffectTimeStr(String effectTimeStr) {
		this.effectTimeStr = effectTimeStr;
	}
	public Date getEffectTime() {
		return effectTime;
	}
	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	@Override
	public String toString() {
		return "AdClientVo [id=" + id + ", name=" + name + ", mac=" + mac + ", currentIp=" + currentIp + ", preIp="
				+ preIp + ", countIps=" + countIps + ", ipstDesc=" + ipstDesc + ", preAccessTime=" + preAccessTime
				+ ", preAccessTimeStr=" + preAccessTimeStr + ", createTime=" + createTime + ", createTimeStr="
				+ createTimeStr + ", systemType=" + systemType + ", status=" + status + ", sysOs=" + sysOs
				+ ", statusCn=" + statusCn + "]";
	}
	public String getSysOs() {
		return sysOs;
	}
	public void setSysOs(String sysOs) {
		this.sysOs = sysOs;
	}
	public String getPreAccessTimeStr() {
		return preAccessTimeStr;
	}
	public void setPreAccessTimeStr(String preAccessTimeStr) {
		this.preAccessTimeStr = preAccessTimeStr;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getStatusCn() {
		return statusCn;
	}
	public void setStatusCn(String statusCn) {
		this.statusCn = statusCn;
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
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getCurrentIp() {
		return currentIp;
	}
	public void setCurrentIp(String currentIp) {
		this.currentIp = currentIp;
	}
	public String getPreIp() {
		return preIp;
	}
	public void setPreIp(String preIp) {
		this.preIp = preIp;
	}
	public Integer getCountIps() {
		return countIps;
	}
	public void setCountIps(Integer countIps) {
		this.countIps = countIps;
	}
	public String getIpstDesc() {
		return ipstDesc;
	}
	public void setIpstDesc(String ipstDesc) {
		this.ipstDesc = ipstDesc;
	}
	public Date getPreAccessTime() {
		return preAccessTime;
	}
	public void setPreAccessTime(Date preAccessTime) {
		this.preAccessTime = preAccessTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSystemType() {
		return systemType;
	}
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}









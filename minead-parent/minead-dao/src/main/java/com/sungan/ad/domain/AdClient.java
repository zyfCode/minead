package com.sungan.ad.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Entity
@Table(name="t_adclient")
public class AdClient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(length=128)
	private String name;
	@Column(length=512)
	private String mac;
	@Column(length=64)
	private String currentIp;
	@Column(length=64)
	private String preIp;
	private Integer countIps;
	@Column(length=256)
	private String ipstDesc;
	@Column(length=46)
	private String sysOs;
	/**上次心跳时间*/
	@DateToStr
	private Date preAccessTime;
	@DateToStr
	private Date createTime;
	/**系统类型*/
	@Column(length=64)
	private String systemType;
	public static final String ADCLIENT_STATUS_INVALID="0";
	public static final String ADCLIENT_STATUS_RUNNING="1";
	public static final String ADCLIENT_STATUS_STOP="1";
	/**状态  0停用 1运行中*/
	@StatusCn(value={},dictId=DictEnum.ADCLIENTSTATUS_KEY)
	private String status;
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
	
	public String getSysOs() {
		return sysOs;
	}
	public void setSysOs(String sysOs) {
		this.sysOs = sysOs;
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









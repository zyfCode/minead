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
@Table(name="t_adclient_ip")
public class AdClientIp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 停用
	 */
	public static final String ADCLIENTIP_STATUS_INVALID = "0";
	
	/**
	 * 运行中
	 */
	public static final String ADCLIENTIP_STATUS_RUNNING = "1";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private Long clientId;
	@Column(nullable=false)
	private String ip;
	
	/**状态  0停用 1运行中*/
	@StatusCn(value={ADCLIENTIP_STATUS_INVALID+"=停用",ADCLIENTIP_STATUS_RUNNING+"=运行中"})
	private String status;
	@DateToStr
	private Date updateTime;
	@DateToStr
	private Date createTime;
	
	private String descript;
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

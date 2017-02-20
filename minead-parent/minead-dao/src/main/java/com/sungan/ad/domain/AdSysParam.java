package com.sungan.ad.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sungan.ad.expand.common.annotation.DateToStr;

/**
 * 说明:
 * @version V1.1
 */
@Entity
@Table(name="t_sys_param")
public class AdSysParam {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(length=64)
	private String paramName;
	@Column(length=64)
	private String paramNameCn;
	@Column(length=128)
	private String paramValue; 
	@Column(length=128)
	private String remark;
	@DateToStr
	private Date createDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamNameCn() {
		return paramNameCn;
	}
	public void setParamNameCn(String paramNameCn) {
		this.paramNameCn = paramNameCn;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}

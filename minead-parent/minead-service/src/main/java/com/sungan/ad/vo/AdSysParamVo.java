package com.sungan.ad.vo;

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
public class AdSysParamVo {
	private Long id;
	private String paramName;
	private String paramNameCn;
	private String paramValue; 
	private String remark;
	private Date createDate;
	private String createDateCn;
	
	public String getCreateDateCn() {
		return createDateCn;
	}
	public void setCreateDateCn(String createDateCn) {
		this.createDateCn = createDateCn;
	}
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

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
 * @date 2017年1月18日 上午1:20:43
 * @version V1.1
 */
@Entity
@Table(name="t_adtask")
public class AdTask implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**
	 * 任务名称
	 */
	@Column(length=64)
	private String name;
	/**
	 * 任务次数
	 */
	private Long count;
	/**
	 * 任务类型，0 小时任务 1是循环任务
	 */
	public static final String ADTASK_TYPE_HOUR="0";
	public static final String ADTASK_TYPE_CIRCLE="1";
	@StatusCn({ADTASK_TYPE_HOUR+"=小时任务",ADTASK_TYPE_CIRCLE+"=循环任务"})
	@Column(length=2)
	private String type;
	
	public static final String ADTASK_STATUS_NOTPUBLIC="0";
	public static final String ADTASK_STATUS_PUBLIC="1";
	/**
	 * 0是下架  1是已发布
	 */
	@StatusCn({ADTASK_STATUS_NOTPUBLIC+"=未发布",ADTASK_STATUS_PUBLIC+"=已发布"})
	@Column(length=2)
	private String status;
	/**
	 * 上次任务下发时间
	 */
	@DateToStr
	private Date preIssuedTime;
	@DateToStr
	private Date createTime;
	@DateToStr
	private Date updateTime;
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
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
	public Date getPreIssuedTime() {
		return preIssuedTime;
	}
	public void setPreIssuedTime(Date preIssuedTime) {
		this.preIssuedTime = preIssuedTime;
	}
}



















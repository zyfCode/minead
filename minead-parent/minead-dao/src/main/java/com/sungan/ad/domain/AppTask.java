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
 */
@Entity
@Table(name = "t_apptask")
public class AppTask implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private Long clientId;
	private Long clientIpid;
	@Column(nullable = false)
	private Long adTaskid;
	@Column(length = 64)
	private String name;
	
	/** 0-24通过率 */
	private Integer throwRate;
	/**
	 * appNo编号
	 */
	@Column(length = 64)
	private String appNo;
	/**
	 * appName名称
	 */
	@Column(length = 64)
	private String appName;
	/**
	 * 任务类型，0 小时任务 1是循环任务
	 */
	public static final String APPTASK_TYPE_HOUR = "0";
	public static final String APPTASK_TYPE_CIRCLE = "1";
	@StatusCn({ APPTASK_TYPE_HOUR + "=小时任务", APPTASK_TYPE_CIRCLE + "=循环任务" })
	@Column(length = 2)
	private String type;
	public static final String APPTASK_STATUS_NOTPUBLIC = "0";
	/**
	 * 任务已发布
	 */
	public static final String APPTASK_STATUS_PUBLIC = "1";
	/**
	 * 任务运行中
	 */
	public static final String APPTASK_STATUS_RUNNING = "2";
	/**
	 * 任务完成
	 */
	public static final String APPTASK_STATUS_SUCCESS = "3";
	/**
	 * 任务失败
	 */
	public static final String APPTASK_STATUS_FAIL = "4";
	/**
	 * 任务失效
	 */
	public static final String APPTASK_STATUS_INVALID = "5";
	/**
	 * 0是下架 1是已发布
	 */
	@StatusCn({ APPTASK_STATUS_NOTPUBLIC + "=未发布", APPTASK_STATUS_PUBLIC + "=已发布", 
		APPTASK_STATUS_RUNNING + "=运行中",
			APPTASK_STATUS_SUCCESS + "=成功"
			,APPTASK_STATUS_FAIL + "=失败",
			APPTASK_STATUS_INVALID + "=无效"
	})
	@Column(length = 2)
	private String status;
	/**
	 * 任务量
	 */
	private Long count;
	private Long doneCount;
	private Long failCount;
	private Long throwCount;
	@DateToStr
	private Date taskRunTime;
	@DateToStr
	private Date crateTime;
	@DateToStr
	private Date updateTime;

	private String descript;

	public Long getThrowCount() {
		return throwCount;
	}

	public void setThrowCount(Long throwCount) {
		this.throwCount = throwCount;
	}

	public Long getAdTaskid() {
		return adTaskid;
	}

	public void setAdTaskid(Long adTaskid) {
		this.adTaskid = adTaskid;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getClientIpid() {
		return clientIpid;
	}

	public void setClientIpid(Long clientIpid) {
		this.clientIpid = clientIpid;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
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

	public Long getFailCount() {
		return failCount;
	}

	public void setFailCount(Long failCount) {
		this.failCount = failCount;
	}

	public Date getTaskRunTime() {
		return taskRunTime;
	}

	public void setTaskRunTime(Date taskRunTime) {
		this.taskRunTime = taskRunTime;
	}

	public Date getCrateTime() {
		return crateTime;
	}

	public void setCrateTime(Date crateTime) {
		this.crateTime = crateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getThrowRate() {
		return throwRate;
	}

	public void setThrowRate(Integer throwRate) {
		this.throwRate = throwRate;
	}
	
	
}

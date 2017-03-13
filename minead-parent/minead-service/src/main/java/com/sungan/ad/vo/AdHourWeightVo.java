package com.sungan.ad.vo;

import java.io.Serializable;


/**
 * 说明:
 * 
 * @version V1.1
 */
public class AdHourWeightVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	/** 权重值 */
	private Integer wght;
	/** 0-24小时 */
	public static final String COLUMN_HOUR="hour";
	private Integer hour;
	/** 0-24通过率 */
	private Integer throwRate;
	private String descript;
	private Long groupId;
	
	private String groupName;
	
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getThrowRate() {
		return throwRate;
	}
	public void setThrowRate(Integer throwRate) {
		this.throwRate = throwRate;
	}
	public Integer getWght() {
		return wght;
	}
	public void setWght(Integer wght) {
		this.wght = wght;
	}
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}

}

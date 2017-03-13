package com.sungan.ad.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Entity
@Table(name = "t_adhour_weight")
public class AdHourWeight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** 权重值 */
	@Column(unique = false, nullable = false)
	private Integer wght;
	/** 0-24小时 */
	@Column(unique = false, nullable = false)
	private Integer hour;
	/** 0-24通过率 */
	private Integer throwRate;
	private String descript;
	/**
	 * ####升级脚本####
	 * alter table t_adhour_weight   add groupId bigint;
	 */
	private Long groupId;
	
	

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getId() {
		return id;
	}

	public Integer getThrowRate() {
		return throwRate;
	}


	public void setThrowRate(Integer throwRate) {
		this.throwRate = throwRate;
	}


	public void setId(Long id) {
		this.id = id;
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

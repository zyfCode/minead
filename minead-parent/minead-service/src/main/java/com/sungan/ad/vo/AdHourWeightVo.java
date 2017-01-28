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
	private Integer hour;
	/** 0-24通过率 */
	private Integer throwRate;
	private String descript;
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

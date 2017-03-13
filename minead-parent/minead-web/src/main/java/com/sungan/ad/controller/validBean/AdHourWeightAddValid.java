package com.sungan.ad.controller.validBean;

import javax.validation.constraints.NotNull;

import com.sungan.ad.domain.AdHourWeight;

/**
 * 说明:
 */
public class AdHourWeightAddValid  extends AdHourWeight{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="通过率为空")
	public Integer getThrowRate() {
		return super.getThrowRate();
	}
	
	@NotNull(message="请选择组")
	public Long getGroupId() {
		return super.getGroupId();
	}

	@NotNull(message="权重为空")
	public Integer getWght() {
		return super.getWght();
	}

	@NotNull(message="时间为空")
	public Integer getHour() {
		return super.getHour();
	}
	
	public String getDescript() {
		return super.getDescript();
	}

}

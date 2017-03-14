package com.sungan.ad.controller.validBean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.sungan.ad.domain.AdWeightGroup;

/**
 * 说明:
 */
public class AdWeightGroupValid  extends AdWeightGroup{
	private static final long serialVersionUID = 1L;

	@Override
	@NotEmpty(message="任务组不能为空")
	public String getGroupName() {
		return super.getGroupName();
	}
	
	
}
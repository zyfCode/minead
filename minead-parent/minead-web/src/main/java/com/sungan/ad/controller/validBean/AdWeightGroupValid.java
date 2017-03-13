package com.sungan.ad.controller.validBean;

import javax.validation.constraints.NotNull;

import com.sungan.ad.domain.AdWeightGroup;

/**
 * 说明:
 */
public class AdWeightGroupValid  extends AdWeightGroup{
	private static final long serialVersionUID = 1L;

	@Override
	@NotNull(message="名称不能为空")
	public String getGroupName() {
		return super.getGroupName();
	}
	
	
}
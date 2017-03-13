package com.sungan.ad.controller.validBean;

import javax.validation.constraints.NotNull;

import com.sungan.ad.domain.AdClient;

/**
 * 说明:
 */
public class AdClientValid  extends AdClient{
	private static final long serialVersionUID = 1L;

	@NotNull(message="名称不能为空")
	public String getName() {
		return super.getName();
	}

	@NotNull(message="MAC不能置为空")
	public String getMac() {
		return super.getMac();
	}
	
	
}
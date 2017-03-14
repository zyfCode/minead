package com.sungan.ad.controller.validBean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.sungan.ad.domain.AdClient;

/**
 * 说明:
 */
public class AdClientValid  extends AdClient{
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="名称不能为空")
	public String getName() {
		return super.getName();
	}

	@NotEmpty(message="MAC不能置为空")
	public String getMac() {
		return super.getMac();
	}
	
	
}
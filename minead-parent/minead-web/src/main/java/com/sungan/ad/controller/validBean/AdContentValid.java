package com.sungan.ad.controller.validBean;

import org.hibernate.validator.constraints.NotEmpty;

import com.sungan.ad.domain.AdContent;

/**
 * 说明:
 */
public class AdContentValid  extends AdContent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@NotEmpty(message="类型不能为空")
	public String getType() {
		return super.getType();
	}

	@Override
	@NotEmpty(message="状态不能为空")
	public String getStatus() {
		return super.getStatus();
	}

	@Override
	@NotEmpty(message="名称不能为空")
	public String getName() {
		return super.getName();
	}

	@Override
	public String getContent() {
		return super.getContent();
	}
	
	
}
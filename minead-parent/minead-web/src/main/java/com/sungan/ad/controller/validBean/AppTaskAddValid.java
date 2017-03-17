package com.sungan.ad.controller.validBean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.sungan.ad.domain.AppTask;

/**
 * 说明:
 */
public class AppTaskAddValid  extends AppTask{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@NotNull
	public Long getClientId() {
		return super.getClientId();
	}

	@NotNull
	public Long getClientIpid() {
		return super.getClientIpid();
	}

	@NotEmpty(message="不能为空")
	public String getDescript() {
		return super.getDescript();
	}

	@NotEmpty(message="不能为空")
	public String getAppNo() {
		return super.getAppNo();
	}

	@NotEmpty(message="不能为空")
	public String getAppName() {
		return super.getAppName();
	}

	@NotEmpty(message="不能为空")
	public String getName() {
		return super.getName();
	}

	@NotEmpty(message="不能为空")
	public String getType() {
		return super.getType();
	}


	@NotNull
	public Long getCount() {
		return super.getCount();
	}

	
	@NotNull
	public Integer getThrowRate() {
		return super.getThrowRate();
	}
	
	
}
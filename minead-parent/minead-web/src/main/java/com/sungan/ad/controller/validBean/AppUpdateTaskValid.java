package com.sungan.ad.controller.validBean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.sungan.ad.domain.AppTask;

/**
 * 说明:
 */
public class AppUpdateTaskValid  extends AppTask{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	@NotEmpty(message="不能为空")
	public String getStatus() {
		return super.getStatus();
	}



	@NotNull
	public Long getCount() {
		return super.getCount();
	}
	
	
}
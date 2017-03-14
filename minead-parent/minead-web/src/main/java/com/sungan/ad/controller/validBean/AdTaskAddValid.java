package com.sungan.ad.controller.validBean;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.sungan.ad.domain.AdTask;

/**
 * 说明:
 * @version V1.1
 */
public class AdTaskAddValid extends AdTask{

	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	@NotEmpty(message="任务组不能为空")
	public String getTaskGroup() {
		return super.getTaskGroup();
	}

	@Override
	@NotEmpty(message="实例不能为空")
	public String getClazzName() {
		return super.getClazzName();
	}

	@Override
	@NotEmpty(message="任务名不能为空")
	public String getName() {
		return super.getName();
	}

	@Override
	@NotNull(message="数量不能为空")
	public Long getCount() {
		return super.getCount();
	}

	
	
	
}

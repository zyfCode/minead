package com.sungan.ad.controller.validBean;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

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
	@NotNull
	public String tt;
	

	public String getTt() {
		return tt;
	}

	public void setTt(String tt) {
		this.tt = tt;
	}

	@Override
	@NotNull
	public void setTaskGroup(String taskGroup) {
		super.setTaskGroup(taskGroup);
	}

	@Override
	@NotNull
	public void setClazzName(String clazzName) {
		super.setClazzName(clazzName);
	}

	@Override
	@NotNull
	@Max(value=120)
	public void setDescript(String descript) {
		super.setDescript(descript);
	}

	@Override
	@NotNull
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	@NotNull
	public void setCount(Long count) {
		super.setCount(count);
	}

	@Override
	@NotNull
	public void setType(String type) {
		super.setType(type);
	}

	
	
	
}

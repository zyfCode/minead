package com.sungan.ad.expand.common.bean;

import java.util.List;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class TaskResonse {
	public static final String TR_DESDORY="TASK_STOP";
	private String action;
	private List<TaskResonseInfo> resInfos;

	public List<TaskResonseInfo> getResInfos() {
		return resInfos;
	}
	
	

	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}



	public void setResInfos(List<TaskResonseInfo> resInfos) {
		this.resInfos = resInfos;
	}
	
}

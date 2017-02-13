package com.sungan.ad.expand.common.bean;

import java.util.List;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class TaskResonse {
	public static final String TR_DESDORY="TASK_STOP";
	public static final String TR_INIT="TASK_INIT";
	private String action;
	private TaskResonseInfo[] resInfos;

	

	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}



	public TaskResonseInfo[] getResInfos() {
		return resInfos;
	}



	public void setResInfos(TaskResonseInfo[] resInfos) {
		this.resInfos = resInfos;
	}


	
}

package com.sungan.ad.expand.common.bean;

import java.io.Serializable;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2017年1月12日 下午10:58:46
 * @version V1.1
 */
public class TaskInfo implements Serializable {
	private Long allTask; //任务总数
	private Long done;   //执行成功的
	private Long areadyDo;
	private Long fail;
	public Long getAllTask() {
		return allTask;
	}
	public void setAllTask(Long allTask) {
		this.allTask = allTask;
	}
	public Long getDone() {
		return done;
	}
	public void setDone(Long done) {
		this.done = done;
	}
	public Long getAreadyDo() {
		return areadyDo;
	}
	public void setAreadyDo(Long areadyDo) {
		this.areadyDo = areadyDo;
	}
	public Long getFail() {
		return fail;
	}
	public void setFail(Long fail) {
		this.fail = fail;
	}
	
}

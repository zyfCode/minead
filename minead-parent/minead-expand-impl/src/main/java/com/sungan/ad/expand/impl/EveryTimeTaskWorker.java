package com.sungan.ad.expand.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sungan.ad.expand.common.TaskApp;
import com.sungan.ad.expand.common.bean.TaskInfo;
import com.sungan.ad.expand.common.bean.TaskResonseInfo;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class EveryTimeTaskWorker  implements TaskApp {
	private static final Log  log  = LogFactory.getLog(EveryTimeTaskWorker.class);

	@Override
	public void init(TaskResonseInfo info) {
		log.info("init.....");
	}

	@Override
	public void work() {
		log.info("work.....");
	}

	@Override
	public void destory() {
		log.info("destory.....");
	}

	@Override
	public TaskInfo getTaskInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.sungan.ad.expand.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sungan.ad.expand.common.TaskApp;
import com.sungan.ad.expand.common.bean.TaskInfo;
import com.sungan.ad.expand.common.bean.TaskResonseInfo;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2017年1月12日 下午11:03:53
 * @version V1.1
 */
public class DaillyTaskApp implements TaskApp{
	private static final Log  log  = LogFactory.getLog(DaillyTaskApp.class);

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

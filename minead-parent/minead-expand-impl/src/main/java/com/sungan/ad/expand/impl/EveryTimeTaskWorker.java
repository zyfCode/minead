package com.sungan.ad.expand.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sungan.ad.expand.common.TaskApp;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2017年1月12日 下午11:39:23
 * @version V1.1
 */
public class EveryTimeTaskWorker  implements TaskApp {
	private static final Log  log  = LogFactory.getLog(EveryTimeTaskWorker.class);

	@Override
	public void init() {
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

}

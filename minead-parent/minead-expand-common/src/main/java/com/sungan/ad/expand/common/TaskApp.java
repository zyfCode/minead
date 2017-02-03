package com.sungan.ad.expand.common;

import com.sungan.ad.expand.common.bean.TaskInfo;
import com.sungan.ad.expand.common.bean.TaskResonseInfo;

/**
 * 说明:
 * 
 * @version V1.1
 */
public interface TaskApp {
	void init(TaskResonseInfo info);
	void work();
	void destory();
	
	TaskInfo getTaskInfo();
}

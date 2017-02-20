package com.sungan.ad.service.ext;

import java.util.List;

import com.sungan.ad.expand.common.bean.TaskInfo;
import com.sungan.ad.vo.AdClientVo;
import com.sungan.ad.vo.AppTaskVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
public interface AdTaskManager {
	void clientTaskInfo(TaskInfo taskInfo);
	List<AppTaskVo> getTask(AdClientVo client);
	 void cleanTask();
	 void genTask();
	 void checkInvalidClient();
}

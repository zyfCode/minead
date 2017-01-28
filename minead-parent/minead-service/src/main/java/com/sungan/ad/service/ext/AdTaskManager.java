package com.sungan.ad.service.ext;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.sungan.ad.domain.AdTask;
import com.sungan.ad.service.AdHourWeightService;
import com.sungan.ad.service.AdTaskService;
import com.sungan.ad.service.AppTaskServivce;
import com.sungan.ad.vo.AdHourWeightVo;
import com.sungan.ad.vo.AdTaskVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Component
public class AdTaskManager {
	private static final Log log  = LogFactory.getLog(AdTaskManager.class);
	private AdTaskService adTaskService;
	private AppTaskServivce appTaskServivce;
	private AdHourWeightService adHourWeightService;
	
	
	public void genTask(){
		AdTask condition = new AdTask();
		condition.setStatus(AdTask.ADTASK_STATUS_PUBLIC);
		List<AdTaskVo> queryList = adTaskService.queryList(condition );
		if(queryList==null||queryList.size()<1){
			log.warn("没有发布的任务");
			return ;
		}
		List<AdHourWeightVo> query = adHourWeightService.query();
		if(query==null||query.size()<1){
			log.warn("未配置任务权重");
			return ;
		}
		for(AdTaskVo vo:queryList){
			
		}
	}
	
	public AdTaskService getAdTaskService() {
		return adTaskService;
	}
	public void setAdTaskService(AdTaskService adTaskService) {
		this.adTaskService = adTaskService;
	}
	public AppTaskServivce getAppTaskServivce() {
		return appTaskServivce;
	}
	public void setAppTaskServivce(AppTaskServivce appTaskServivce) {
		this.appTaskServivce = appTaskServivce;
	}
	public AdHourWeightService getAdHourWeightService() {
		return adHourWeightService;
	}
	public void setAdHourWeightService(AdHourWeightService adHourWeightService) {
		this.adHourWeightService = adHourWeightService;
	}
	
	
	
}

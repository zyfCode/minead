package com.sungan.ad.service.ext;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sungan.ad.domain.AdTask;
import com.sungan.ad.domain.AppTask;
import com.sungan.ad.service.AdClientService;
import com.sungan.ad.service.AdHourWeightService;
import com.sungan.ad.service.AdTaskService;
import com.sungan.ad.service.AppTaskServivce;
import com.sungan.ad.service.ext.handler.AppCycleTskHandler;
import com.sungan.ad.service.ext.handler.AppHourTaskHandler;
import com.sungan.ad.vo.AdClientVo;
import com.sungan.ad.vo.AdHourWeightVo;
import com.sungan.ad.vo.AdTaskVo;

/**
 * 说明:
 * 		任务生成器
 * @version V1.1
 */
@Component
public class AdTaskManager {
	private static final Log log  = LogFactory.getLog(AdTaskManager.class);
	@Autowired
	private AdTaskService adTaskService;
	@Autowired
	private AdClientService adClientService;
	@Autowired
	private AppTaskServivce appTaskServivce;
	@Autowired
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
		List<AdClientVo> clients = adClientService.query();
		for(AdTaskVo vo:queryList){
			for(AdClientVo client:clients){
				try {
					if(vo.getType().equals(AdTask.ADTASK_TYPE_CIRCLE)){
						TaskHandler taskHandler = new AppCycleTskHandler(adClientService);
						List<AppTask> genAppTask = taskHandler.genAppTask(vo, client);
						appTaskServivce.insert(genAppTask);
					}else if(vo.getType().equals(AdTask.ADTASK_TYPE_HOUR)){
						TaskHandler taskHandler = new AppHourTaskHandler(adClientService, adHourWeightService);
						List<AppTask> genAppTask = taskHandler.genAppTask(vo, client);
						appTaskServivce.insert(genAppTask);
					} 
				} catch (Exception e) {
					log.error("生成任务异常", e);
				}
			}
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

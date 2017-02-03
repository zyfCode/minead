package com.sungan.ad.service.ext;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.domain.AdClientIp;
import com.sungan.ad.domain.AdTask;
import com.sungan.ad.domain.AppTask;
import com.sungan.ad.service.AdClientService;
import com.sungan.ad.service.AdHourWeightService;
import com.sungan.ad.service.AdTaskService;
import com.sungan.ad.service.AppTaskServivce;
import com.sungan.ad.service.ext.bean.TaskInfo;
import com.sungan.ad.service.ext.handler.AppCycleTskHandler;
import com.sungan.ad.service.ext.handler.AppHourTaskHandler;
import com.sungan.ad.vo.AdClientIpVo;
import com.sungan.ad.vo.AdClientVo;
import com.sungan.ad.vo.AdHourWeightVo;
import com.sungan.ad.vo.AdTaskVo;
import com.sungan.ad.vo.AppTaskVo;

/**
 * 说明:
 * 		任务生成器
 * @version V1.1
 */
@Component
public class AdTaskManagerImpl implements AdTaskManager {
	private static final Log log  = LogFactory.getLog(AdTaskManagerImpl.class);
	@Autowired
	private AdTaskService adTaskService;
	@Autowired
	private AdClientService adClientService;
	@Autowired
	private AppTaskServivce appTaskServivce;
	@Autowired
	private AdHourWeightService adHourWeightService;
	
	public Date getCurrentDate(){
		return new Date();
	}
	
	public Long getClientCount(Long clientId,String clientIp){
		AdClientIp adClientIp = new AdClientIp();
		adClientIp.setIp(clientIp);
		List<AdClientIpVo> queryIps = this.adClientService.queryIps(adClientIp );
		AdClientIpVo clientIpVo = queryIps.get(0);
		AppTask condition = new AppTask();
		condition.setClientId(clientId);
		condition.setClientIpid(clientIpVo.getId());
		List<AppTaskVo> query = this.appTaskServivce.query(condition );
		int hours = this.getCurrentDate().getHours();
		long result = 0;
		for(AppTaskVo vo:query){
			if(hours>=12){
				if(vo.getTaskRunTime().getHours()<=hours&&vo.getTaskRunTime().getHours()>=12){
					result = result+ vo.getDoneCount();
				}
			}else{
				if(vo.getTaskRunTime().getHours()<=hours&&vo.getTaskRunTime().getHours()<12){
					result = result + vo.getDoneCount();
				}
			}
		}
		return result;
	}
	
	/**
	 * 客户端上传任务详细
	 * @param TaskInfo
	 */
	public void clientTaskInfo(TaskInfo taskInfo){

		AppTaskVo find = appTaskServivce.find(taskInfo.getTaskId());
		find.setDoneCount(taskInfo.getDoneCount());
		find.setFailCount(taskInfo.getFailCount());
		find.setThrowCount(taskInfo.getThrowCount());
		AdClientIp adClientIp = new AdClientIp();
		adClientIp.setId(find.getClientIpid());
		List<AdClientIpVo> queryIps = this.adClientService.queryIps(adClientIp);
		if(queryIps==null||queryIps.size()<1){
			log.warn("未打到"+find.getClientIpid()+"的ip记录");
			return ;
		}
		if(!queryIps.get(0).getIp().equals(taskInfo.getClientIp())){
			log.warn("当前ip"+taskInfo.getClientIp()+",期望ip"+queryIps.get(0).getIp()+"不匹配");
			return;
		}
		AdTaskVo queryById = adTaskService.queryById(find.getAdTaskid());
		if(find.getCount()<=find.getDoneCount()){
			find.setStatus(AppTask.APPTASK_STATUS_SUCCESS);
			AppTask task = new AppTask();
			AdCommonsUtil.beanCopyWithoutNull(find, task);
			appTaskServivce.update(task);
			//当前client下其他同一时段的任务置为无效
			AppTask condition = new AppTask();
			condition.setClientId(taskInfo.getClientId());
			condition.setTaskRunTime(find.getTaskRunTime());
			List<AppTaskVo> query = appTaskServivce.query(condition);
			if(query!=null){
				for(AppTaskVo vo:query){
					if(taskInfo.getTaskId().equals(vo.getId())){
						continue;
					}
					vo.setStatus(AppTask.APPTASK_STATUS_INVALID);
					AppTask taskOth = new AppTask();
					AdCommonsUtil.beanCopyWithoutNull(vo, taskOth);
					appTaskServivce.update(taskOth );
				}
			}
		}else if((this.getClientCount(taskInfo.getClientId(), taskInfo.getClientIp())+taskInfo.getDoneCount())>=queryById.getCount()){
			find.setStatus(AppTask.APPTASK_STATUS_SUCCESS);
			AppTask task = new AppTask();
			AdCommonsUtil.beanCopyWithoutNull(find, task);
			appTaskServivce.update(task);
			//当前client下其他同一时段的任务置为无效
			AppTask condition = new AppTask();
			condition.setClientId(taskInfo.getClientId());
			condition.setTaskRunTime(find.getTaskRunTime());
			List<AppTaskVo> query = appTaskServivce.query(condition);
			if(query!=null){
				for(AppTaskVo vo:query){
					if(taskInfo.getTaskId().equals(vo.getId())){
						continue;
					}
					vo.setCount(find.getCount()-find.getDoneCount());
					vo.setUpdateTime(new Date());
					AppTask taskOth = new AppTask();
					AdCommonsUtil.beanCopyWithoutNull(vo, taskOth);
					appTaskServivce.update(taskOth );
				}
			}
		}
	}
	
	
	/**
	 * 根据 Client获取任务
	 * @param client
	 * @return
	 */
	public AppTaskVo getTask(AdClientVo client){
		AdClientIp clientId = new AdClientIp();
		clientId.setClientId(client.getId());
		clientId.setIp(client.getCurrentIp());
		List<AdClientIpVo> queryIps = adClientService.queryIps(clientId);
		if(queryIps==null||queryIps.size()==0){
			clientId = new AdClientIp();
			clientId.setClientId(client.getId());
			clientId.setStatus(AdClientIp.ADCLIENTIP_STATUS_RUNNING);
			queryIps = adClientService.queryIps(clientId);
			if(queryIps!=null&&queryIps.size()>0){
				for(AdClientIpVo ipvo:queryIps){
					AdClientIp updateStatus = new AdClientIp();
					updateStatus.setId(ipvo.getId());
					updateStatus.setStatus(AdClientIp.ADCLIENTIP_STATUS_INVALID);
					adClientService.updateIp(updateStatus);
				}
			}
			AdClientIp clientIp = new AdClientIp();
			clientIp.setClientId(client.getId());
			clientIp.setCreateTime(new Date());
			clientIp.setDescript("");
			clientIp.setStatus(AdClientIp.ADCLIENTIP_STATUS_RUNNING);
			 Long id = adClientService.insertIp(clientIp );
//			 clientIp.setId(id);
//			AdTask condition = new AdTask();
//			condition.setStatus(AdTask.ADTASK_STATUS_PUBLIC);
//			List<AdTaskVo> queryList = adTaskService.queryList(condition );
//			AdClientIpVo parseToVo = AnnotationParser.parseToVo(AdClientIpVo.class, clientIp);
//			for(AdTaskVo vo:queryList){
//				if(vo.getType().equals(AdTask.ADTASK_TYPE_CIRCLE)){
//					TaskHandler taskHandler = new AppCycleTskHandler(adClientService);
//					List<AppTask> genAppTask = taskHandler.genAppTask(vo, client,parseToVo);
//					appTaskServivce.insert(genAppTask);
//				}else if(vo.getType().equals(AdTask.ADTASK_TYPE_HOUR)){
//					TaskHandler taskHandler = new AppHourTaskHandler(adClientService, adHourWeightService);
//					List<AppTask> genAppTask = taskHandler.genAppTask(vo, client,parseToVo);
//					appTaskServivce.insert(genAppTask);
//				} 
//			}
		}else{
			AdClientIpVo adClientIpVo = queryIps.get(0);
			AppTask condition = new AppTask();
			condition.setClientId(client.getId());
			condition.setClientIpid(adClientIpVo.getId());
			List<AppTaskVo> query = appTaskServivce.query(condition );
			AppTaskVo result = null;
			AppTaskVo runningTask = null;
			Date date = this.getCurrentDate();
			for(AppTaskVo vo:query){
				if(vo.getStatus().equals(AppTask.APPTASK_STATUS_RUNNING)){
					runningTask = vo;
				}
				if(vo.getStatus().equals(AppTask.APPTASK_STATUS_PUBLIC)&&vo.getTaskRunTime().getTime()<=date.getTime()){
					if(result==null){
						result = vo;
					}else if(vo.getTaskRunTime().getTime()>result.getTaskRunTime().getTime()){
						result = vo;
					}
				}
			}
			if(runningTask!=null&&runningTask.getDoneCount()<runningTask.getCount()){
				return runningTask;
			}
			return result;
		}
		return null;
	}
	
	
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

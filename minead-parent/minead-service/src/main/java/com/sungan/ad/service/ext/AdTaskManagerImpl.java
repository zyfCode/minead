package com.sungan.ad.service.ext;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdDateUtil;
import com.sungan.ad.domain.AdClient;
import com.sungan.ad.domain.AdClientIp;
import com.sungan.ad.domain.AdTask;
import com.sungan.ad.domain.AppTask;
import com.sungan.ad.expand.common.bean.TaskInfo;
import com.sungan.ad.service.AdClientService;
import com.sungan.ad.service.AdHourWeightService;
import com.sungan.ad.service.AdSysParamService;
import com.sungan.ad.service.AdTaskService;
import com.sungan.ad.service.AdWeightGroupService;
import com.sungan.ad.service.AppTaskServivce;
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
@Component("adTaskManager")
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
	@Autowired
	private AdSysParamService adSysParamService;
	@Autowired
	private AdWeightGroupService adWeightGroupService;
	@Value(value = "${ad.mock}")
	private String isMock;
	
	
	public AdWeightGroupService getAdWeightGroupService() {
		return adWeightGroupService;
	}

	public void setAdWeightGroupService(AdWeightGroupService adWeightGroupService) {
		this.adWeightGroupService = adWeightGroupService;
	}

	public AdClientService getAdClientService() {
		return adClientService;
	}

	public void setAdClientService(AdClientService adClientService) {
		this.adClientService = adClientService;
	}

	public AdSysParamService getAdSysParamService() {
		return adSysParamService;
	}

	public void setAdSysParamService(AdSysParamService adSysParamService) {
		this.adSysParamService = adSysParamService;
	}

	public String getIsMock() {
		return isMock;
	}

	public void setIsMock(String isMock) {
		this.isMock = isMock;
	}

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

		AppTaskVo find = appTaskServivce.find(taskInfo.getAppTaskId());
		find.setDoneCount(taskInfo.getDoneCount());
		find.setFailCount(taskInfo.getFailCount());
		find.setThrowCount(taskInfo.getThrowCount());
		AdClientIp adClientIp = new AdClientIp();
		adClientIp.setId(find.getClientIpid());
		List<AdClientIpVo> queryIps = this.adClientService.queryIps(adClientIp);
		if(queryIps==null||queryIps.size()<1){
			log.warn("未找到"+find.getClientIpid()+"的ip记录");
			return ;
		}
		try {
			if(find.getCount()<=find.getDoneCount()){
				find.setStatus(AppTask.APPTASK_STATUS_SUCCESS);
				AppTask task = new AppTask();
				AdCommonsUtil.beanCopyWithoutNull(find, task);
				appTaskServivce.update(task);
			}else if(AppTask.APPTASK_STATUS_PUBLIC.equals(find.getStatus())){
				AppTask updateTask = new AppTask(); 
				updateTask.setStatus(AppTask.APPTASK_STATUS_RUNNING);
				AdCommonsUtil.beanCopyWithoutNull(find, updateTask);
				appTaskServivce.update(updateTask);
			}
		} catch (Exception e) {
			log.info("", e);
		}
	}
	
	
	/**
	 * 根据 Client获取任务
	 * @param client
	 * @return
	 */
	public List<AppTaskVo> getTask(AdClientVo client){
		AdClientIp clientId = new AdClientIp();
		clientId.setClientId(client.getId());
		clientId.setIp(client.getCurrentIp());
		List<AdClientIpVo> queryIps = adClientService.queryIps(clientId);
		if(queryIps!=null&&queryIps.size()==1){
			 AdClientIpVo adClientIpVo = queryIps.get(0);
			if( !adClientIpVo.getIp().equals(client.getCurrentIp())){
				AdClientIp updateStatus = new AdClientIp();
				updateStatus.setId(adClientIpVo .getId());
				updateStatus.setIp(client.getCurrentIp());
				adClientService.updateIp(updateStatus);
			}
		
			AppTask condition = new AppTask();
			condition.setClientId(client.getId());
			condition.setClientIpid(adClientIpVo.getId());
			condition.setStatus(AppTask.APPTASK_STATUS_RUNNING);
			List<AppTaskVo> queryRunning = appTaskServivce.query(condition );
			 condition.setStatus(AppTask.APPTASK_STATUS_PUBLIC);
			 condition.setClientId(client.getId());
			 if(!StringUtils.isBlank(isMock)&&isMock.equalsIgnoreCase("true")){
				 condition.setTaskRunTime(adSysParamService.getSysRuntime());
			 }else{
				 condition.setTaskRunTime(AdDateUtil.getTaskRunDate());
			 }
			List<AppTaskVo> querypublic = appTaskServivce.query(condition );
			if(queryRunning!=null){
				if(querypublic!=null){
					queryRunning.addAll(querypublic);
				}
			}else{
				queryRunning =querypublic;
			}
			return queryRunning;
		}
		return new ArrayList<AppTaskVo>();
	}
	
	
	public void checkInvalidClient(){
		AdClient adclientContition = new AdClient(); 
		adclientContition.setStatus(AdClient.ADCLIENT_STATUS_RUNNING);
		List<AdClientVo> clients = adClientService.query(adclientContition);
		if(clients!=null&&clients.size()>0){
			for(AdClientVo vo:clients){
				Date preAccessTime = vo.getPreAccessTime();
				//如果上次访问时间与当前时间相差2小时则置为无效
				long timeInterval = System.currentTimeMillis()-preAccessTime.getTime();
				if(timeInterval>2*60*60*1000){
					AdClient client = new AdClient();
					client.setId(vo.getId());
					client.setStatus(AdClient.ADCLIENT_STATUS_INVALID);
					adClientService.update(client );
				}
			}
		}
		
		
	}
	
	
	public void cleanTask(){
		AdTask condition = new AdTask();
		List<AdTaskVo> queryList = adTaskService.queryList(condition);
		List<AdClientVo> clients = adClientService.query();
		for(AdTaskVo vo:queryList){
			for(AdClientVo client:clients){
				try {
					AdClientIp adClientIp = new AdClientIp();
					adClientIp.setClientId(client.getId());
					AppTask appTaskCondition = new AppTask();
					appTaskCondition.setClientId(client.getId());
					appTaskCondition.setAdTaskid(vo.getId());
					//删除前2天的任务
					Date date = new Date(System.currentTimeMillis()-3*24*60*60*1000);
					appTaskCondition.setTaskRunTime(date);
					List<AppTaskVo> appTasks = appTaskServivce.queryLeNow(appTaskCondition);
					for(AppTaskVo tv:appTasks){
						appTaskServivce.delete(tv.getId());
					}
				} catch (Exception e) {
					log.error("清理任务异常", e);
				}
			}
		}
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
		AdClient adclientContition = new AdClient(); 
		adclientContition.setStatus(AdClient.ADCLIENT_STATUS_RUNNING);
		List<AdClientVo> clients = adClientService.query(adclientContition);
		if(clients==null||clients.size()<0){
			log.info("[生成客户端任务]无客户端列表");
			return;
		}
		//过虑掉 	生效时间<当前时间 的客户端
		Iterator<AdClientVo> iterator = clients.iterator();
		while(iterator.hasNext()){
			AdClientVo next = iterator.next();
			if(next.getEffectTime()==null||next.getEffectTime().getTime()>System.currentTimeMillis()){
				iterator.remove();
			}
		}
		
		for(AdTaskVo vo:queryList){
			for(AdClientVo client:clients){
				try {
					AdClientIp adClientIp = new AdClientIp();
					adClientIp.setClientId(client.getId());
					
					AppTask appTaskCondition = new AppTask();
					appTaskCondition.setClientId(client.getId());
					appTaskCondition.setAdTaskid(vo.getId());
					appTaskCondition.setTaskRunTime(new Date(System.currentTimeMillis()-60*60*1000));
					List<AppTaskVo> appTasks = appTaskServivce.queryBigNow(appTaskCondition );
					if(appTasks!=null&&appTasks.size()>0){
						continue;
					}
					
					if(vo.getType().equals(AdTask.ADTASK_TYPE_CIRCLE)){
						TaskHandler taskHandler = new AppCycleTskHandler(adClientService);
						List<AppTask> genAppTask = taskHandler.genAppTask(vo, client);
						appTaskServivce.insert(genAppTask);
					}else if(vo.getType().equals(AdTask.ADTASK_TYPE_HOUR)){
						TaskHandler taskHandler = new AppHourTaskHandler(adClientService, adHourWeightService,adWeightGroupService);
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

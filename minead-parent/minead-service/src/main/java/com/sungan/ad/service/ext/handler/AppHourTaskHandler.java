package com.sungan.ad.service.ext.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sungan.ad.commons.AdDateUtil;
import com.sungan.ad.domain.AdClient;
import com.sungan.ad.domain.AdClientIp;
import com.sungan.ad.domain.AdHourWeight;
import com.sungan.ad.domain.AdWeightGroup;
import com.sungan.ad.domain.AppTask;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.service.AdClientService;
import com.sungan.ad.service.AdHourWeightService;
import com.sungan.ad.service.AdWeightGroupService;
import com.sungan.ad.service.ext.HourUtil;
import com.sungan.ad.service.ext.TaskHandler;
import com.sungan.ad.vo.AdClientIpVo;
import com.sungan.ad.vo.AdClientVo;
import com.sungan.ad.vo.AdHourWeightVo;
import com.sungan.ad.vo.AdTaskVo;
import com.sungan.ad.vo.AdWeightGroupVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class AppHourTaskHandler  extends TaskHandler {
	public AppHourTaskHandler(AdClientService adClientService, AdHourWeightService adHourWeightService,AdWeightGroupService adWeightGroupService) {
		super(adClientService);
		this.adHourWeightService = adHourWeightService;
		this.adWeightGroupService = adWeightGroupService;
	}

	private static final Log log = LogFactory.getLog(AppHourTaskHandler.class);
	private AdHourWeightService adHourWeightService;
	private AdWeightGroupService adWeightGroupService;

	@Override
	public List<AppTask> genAppTask(AdTaskVo taskVo, AdClientVo clientVo, AdClientIpVo ipvo) {
		List<AppTask> result = new ArrayList<AppTask>();
		// 第12小时算一轮任务,一天是两轮
		Long count = taskVo.getCount(); 
		Long groupId = clientVo.getGroupId();
		List<AdHourWeightVo> query = null;
		AdWeightGroupVo currentGroup = null;
		if(groupId==null){
			AdWeightGroup group = new AdWeightGroup();
			group.setIsDefault(AdWeightGroup.ISDEFAULT);
			List<AdWeightGroupVo> queryList = adWeightGroupService.queryList(group);
			if(queryList.size()<1){
				throw new AdRuntimeException("没设置默认权重");
			}
			currentGroup = queryList.get(0);
			AdHourWeight condition = new AdHourWeight();
			condition.setGroupId(currentGroup.getId());
			query = adHourWeightService.query(condition );
		}else{
			currentGroup = adWeightGroupService.find(groupId);
			AdHourWeight condition = new AdHourWeight();
			condition.setGroupId(groupId);
			query = adHourWeightService.query(condition);
			if(query==null){
				throw new AdRuntimeException("[生成任务]"+clientVo.getMac()+"没设置默认权重");
			}
		}
		//如果组上设置了任务数并且任务数大于等于0以组上设置的任务为准
		if(currentGroup.getTaskCount()!=null&&currentGroup.getTaskCount()>=0){
			count = Long.valueOf(currentGroup.getTaskCount());
		}
		
		//获取24小时权重
		if(query.size()!=24){
			log.info("小时权重配置不足24小时，作用默认值");
			query = HourUtil.getDefault();
		}
		int countWeight = HourUtil.countAllweight(query);
		for(AdHourWeightVo hvo : query){
			long taskamount = HourUtil.taskamount(hvo.getWght(), countWeight, count);
			AppTask task = new AppTask();
			task.setAppName(clientVo.getCurrentIp()+"_"+clientVo.getSysOs());
			task.setAppNo(clientVo.getCurrentIp());
			task.setClientId(clientVo.getId());
			task.setClientIpid(ipvo.getId());
			task.setAdTaskid(taskVo.getId());
			task.setCount(taskamount);
			task.setCrateTime(new Date());
			task.setDescript("");
			task.setThrowRate(hvo.getThrowRate());
			task.setDoneCount(0L);
			task.setTaskRunTime(AdDateUtil.getTaskRunDate(hvo.getHour()));
			task.setFailCount(0L);
			task.setName(taskVo.getName());
			task.setStatus(AppTask.APPTASK_STATUS_PUBLIC);
			task.setType(taskVo.getType());
			task.setUpdateTime(new Date());
			result.add(task);
		}
		
		return result;
	}

}

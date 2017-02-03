package com.sungan.ad.service.ext.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sungan.ad.domain.AdClient;
import com.sungan.ad.domain.AdClientIp;
import com.sungan.ad.domain.AppTask;
import com.sungan.ad.service.AdClientService;
import com.sungan.ad.service.AdHourWeightService;
import com.sungan.ad.service.ext.HourUtil;
import com.sungan.ad.service.ext.TaskHandler;
import com.sungan.ad.vo.AdClientIpVo;
import com.sungan.ad.vo.AdClientVo;
import com.sungan.ad.vo.AdHourWeightVo;
import com.sungan.ad.vo.AdTaskVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class AppHourTaskHandler  extends TaskHandler {
	public AppHourTaskHandler(AdClientService adClientService, AdHourWeightService adHourWeightService) {
		super(adClientService);
		this.adHourWeightService = adHourWeightService;
	}

	private static final Log log = LogFactory.getLog(AppHourTaskHandler.class);
	private AdHourWeightService adHourWeightService;


	@Override
	public List<AppTask> genAppTask(AdTaskVo vo, AdClientVo clientVo, AdClientIpVo ipvo) {
		List<AppTask> result = new ArrayList<AppTask>();
		AdClientIp adClientIp = new AdClientIp();
		adClientIp.setClientId(clientVo.getId());
		List<AdClientIpVo> queryIps = adClientService.queryIps(adClientIp);
		// 第12小时算一轮任务,一天是两轮
		Long count = vo.getCount()*2*queryIps.size();
		//获取24小时权重
		List<AdHourWeightVo> query = adHourWeightService.query();
		if(query.size()!=24){
			log.info("小时权重配置不足24小时，作用默认值");
			query = HourUtil.getDefault();
		}
		int countWeight = HourUtil.countAllweight(query);
		for(AdHourWeightVo hvo : query){
			long taskamount = HourUtil.taskamount(hvo.getWght(), countWeight, count);
			Date setHours = DateUtils.setHours(new Date(), hvo.getHour());
			setHours = DateUtils.setMinutes(setHours, 0);
			 setHours =  DateUtils.setSeconds(setHours, 0);
			AppTask task = new AppTask();
			task.setAppName(clientVo.getCurrentIp()+"_"+clientVo.getSysOs());
			task.setAppNo(clientVo.getCurrentIp());
			task.setClientId(clientVo.getId());
			task.setClientId(ipvo.getId());
			task.setAdTaskid(vo.getId());
			task.setCount(taskamount);
			task.setCrateTime(new Date());
			task.setDescript("");
			task.setThrowRate(hvo.getThrowRate());
			task.setDoneCount(0L);
			task.setTaskRunTime(setHours);
			task.setFailCount(0L);
			task.setName(vo.getName());
			task.setStatus(AppTask.APPTASK_STATUS_PUBLIC);
			task.setType(vo.getType());
			task.setUpdateTime(new Date());
			result.add(task);
		}
		
		return result;
	}

}

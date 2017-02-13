package com.sungan.ad.service.ext.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import com.sungan.ad.domain.AppTask;
import com.sungan.ad.service.AdClientService;
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
public class AppCycleTskHandler extends TaskHandler{


	public AppCycleTskHandler(AdClientService adClientService) {
		super(adClientService);
	}

	@Override
	public List<AppTask> genAppTask(AdTaskVo vo, AdClientVo clientVo,AdClientIpVo ipvo) {
		List<AppTask> result = new ArrayList<AppTask>();
			List<AdHourWeightVo> default1 = HourUtil.getDefault();
			for( AdHourWeightVo ah:default1){
				Date setHours = DateUtils.setHours(new Date(), ah.getHour());
				setHours = DateUtils.setMinutes(setHours, 0);
				 setHours =  DateUtils.setSeconds(setHours, 0);
				AppTask task = new AppTask();
				task.setAppName(clientVo.getCurrentIp()+"_"+clientVo.getSysOs());
				task.setAppNo(clientVo.getCurrentIp());
				task.setClientId(clientVo.getId());
				task.setClientIpid(ipvo.getId());
				task.setAdTaskid(vo.getId());
				task.setCount(vo.getCount());
				task.setCrateTime(new Date());
				task.setDescript("");
				task.setThrowRate(ah.getThrowRate());
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

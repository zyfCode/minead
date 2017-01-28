package com.sungan.ad.service.ext;

import java.util.ArrayList;
import java.util.List;

import com.sungan.ad.domain.AppTask;
import com.sungan.ad.service.AdClientService;
import com.sungan.ad.vo.AdClientIpVo;
import com.sungan.ad.vo.AdClientVo;
import com.sungan.ad.vo.AdTaskVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
public abstract class TaskHandler {
	protected AdClientService adClientService;
	
	public TaskHandler(AdClientService adClientService) {
		super();
		this.adClientService = adClientService;
	}
	public abstract List<AppTask> genAppTask(AdTaskVo vo, AdClientVo clientVo,AdClientIpVo ipvo);
	public List<AppTask> genAppTask(AdTaskVo vo, AdClientVo clientVo) {
		List<AdClientIpVo> queryIps = adClientService.queryIps(clientVo.getId());
		List<AppTask> result = new ArrayList<AppTask>();
		for(AdClientIpVo ipVo:queryIps){
			result = this.genAppTask(vo, clientVo, ipVo);
		}
		return result;
	}
}

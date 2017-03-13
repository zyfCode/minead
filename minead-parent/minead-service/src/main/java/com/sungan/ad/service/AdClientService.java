package com.sungan.ad.service;

import java.util.List;

import com.sungan.ad.dao.AdPager;
import com.sungan.ad.domain.AdClient;
import com.sungan.ad.domain.AdClientIp;
import com.sungan.ad.expand.common.bean.InitTaskConnectRequest;
import com.sungan.ad.expand.common.bean.InitTaskConnectResponse;
import com.sungan.ad.expand.common.bean.TaskRequest;
import com.sungan.ad.expand.common.bean.TaskResonse;
import com.sungan.ad.service.common.MadService;
import com.sungan.ad.vo.AdClientIpVo;
import com.sungan.ad.vo.AdClientVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
public interface AdClientService extends MadService {
	List<AdClientIpVo> queryIps(AdClientIp adClientIp);
	 List<AdClientIpVo> queryIps(Long clientId);
	 Long insertIp(AdClientIp adClientIp);
	 Long insert(AdClient client);
	 void update(AdClient client);
	 void delete(Long id);
	 List<AdClientVo> query();
	 List<AdClientVo> query(AdClient condition);
	 AdPager<AdClientVo> queryPager(AdClient condition,int pageIndex,int rows);
	void updateIp(AdClientIp adClientIp);
	InitTaskConnectResponse initConnect(InitTaskConnectRequest connectRequest,String currentIp);
	TaskResonse hearInfo(TaskRequest bean,String currentIp);
	
	void resetGroup(Long id);
	 
}

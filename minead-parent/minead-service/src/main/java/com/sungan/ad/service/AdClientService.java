package com.sungan.ad.service;

import java.util.List;

import com.sungan.ad.dao.AdPager;
import com.sungan.ad.domain.AdClient;
import com.sungan.ad.service.common.MadService;
import com.sungan.ad.vo.AdClientVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
public interface AdClientService extends MadService {
	 Long insert(AdClient client);
	 void update(AdClient client);
	 void delete(Long id);
	 List<AdClientVo> query();
	 List<AdClientVo> query(AdClient condition);
	 AdPager<AdClientVo> queryPager(AdClient condition,int pageIndex,int rows);
	 
}

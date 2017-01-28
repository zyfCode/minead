package com.sungan.ad.service;

import java.util.List;

import com.sungan.ad.dao.AdPager;
import com.sungan.ad.domain.AdHourWeight;
import com.sungan.ad.service.common.MadService;
import com.sungan.ad.vo.AdHourWeightVo;
/**
 * 说明:
 * @version V1.1
 */
public interface AdHourWeightService  extends MadService{
	 Long insert(AdHourWeight t);
	 void update(AdHourWeight t);
	 void delete(Long id);
	 List<AdHourWeightVo> query();
	 List<AdHourWeightVo> query(AdHourWeight condition);
	 AdPager<AdHourWeightVo> queryPager(AdHourWeight condition,int pageIndex,int rows);
	
}

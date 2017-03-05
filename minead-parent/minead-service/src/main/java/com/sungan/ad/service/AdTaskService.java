package com.sungan.ad.service;

import java.util.List;

import com.sungan.ad.dao.AdPager;
import com.sungan.ad.domain.AdTask;
import com.sungan.ad.service.common.MadService;
import com.sungan.ad.vo.AdTaskVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
public interface AdTaskService  extends MadService{
	Long insert(AdTask adtask);
	List<AdTaskVo> queryList(AdTask adTask);
	 AdTaskVo queryById(Long id);
	 void update(AdTask adTask);
	 AdPager<AdTaskVo> queryPager(AdTask adTask,int pageIndex,int rows);
}

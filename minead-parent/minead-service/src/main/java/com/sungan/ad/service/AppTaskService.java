package com.sungan.ad.service;

import java.util.List;

import com.sungan.ad.dao.AdPager;
import com.sungan.ad.domain.AppTask;
import com.sungan.ad.service.common.MadService;
import com.sungan.ad.vo.AppTaskVo;

/**
 * 说明:
 */
public interface AppTaskService extends  MadService{
	AppTaskVo find(Long id);
	Long insert(AppTask record);
	void delete(Long id);
	void update(AppTask task);
	List<AppTaskVo> queryList(AppTask condition);
	AdPager<AppTaskVo> queryPager(AppTask condition,int pageIndex,int rows);
}

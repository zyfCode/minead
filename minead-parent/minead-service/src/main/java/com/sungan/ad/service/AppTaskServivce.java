package com.sungan.ad.service;

import java.util.List;

import com.sungan.ad.dao.AdPager;
import com.sungan.ad.domain.AppTask;
import com.sungan.ad.service.common.MadService;
import com.sungan.ad.vo.AppTaskVo;

/**
 * 说明:
 */
public interface AppTaskServivce extends  MadService{
	Long insert(AppTask task);
	void delete(Long id);
	void update(AppTask task);
	List<AppTaskVo> query(AppTask condition);
	AdPager<AppTaskVo> queryPage(AppTask condition,int pageIndex,int rows);
}

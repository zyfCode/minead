package com.sungan.ad.service;

import java.util.List;

import com.sungan.ad.dao.AdPager;
import com.sungan.ad.domain.AdContent;
import com.sungan.ad.service.common.MadService;
import com.sungan.ad.vo.AdContentVo;

/**
 * 说明:
 */
public interface AdContentService extends  MadService{
	AdContentVo find(Long id);
	Long insert(AdContent record);
	void delete(Long id);
	void update(AdContent task);
	List<AdContentVo> queryList(AdContent condition);
	AdPager<AdContentVo> queryPager(AdContent condition,int pageIndex,int rows);
}

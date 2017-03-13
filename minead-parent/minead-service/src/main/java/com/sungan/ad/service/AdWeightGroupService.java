package com.sungan.ad.service;

import java.util.List;

import com.sungan.ad.dao.AdPager;
import com.sungan.ad.domain.AdWeightGroup;
import com.sungan.ad.service.common.MadService;
import com.sungan.ad.vo.AdWeightGroupVo;

/**
 * 说明:
 */              
public interface AdWeightGroupService extends  MadService{
	AdWeightGroupVo find(Long id);
	Long insert(AdWeightGroup record);
	void delete(Long id);
	void update(AdWeightGroup task);
	 List<AdWeightGroupVo> queryList(AdWeightGroup condition);
	AdPager<AdWeightGroupVo> queryPager(AdWeightGroup condition, int pageIndex, int rows);
	/**
	 * 查询根节点
	 * @return
	 */
	 List<AdWeightGroupVo> queryRoot();
}

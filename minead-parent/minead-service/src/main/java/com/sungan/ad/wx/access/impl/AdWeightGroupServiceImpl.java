package com.sungan.ad.wx.access.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.dao.AdPager;
import com.sungan.ad.dao.base.AdWeightGroupDAO;
import com.sungan.ad.domain.AdWeightGroup;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.AdWeightGroupService;
import com.sungan.ad.vo.AdWeightGroupVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdWeightGroupServiceImpl implements AdWeightGroupService{
	
	@Autowired
	private AdWeightGroupDAO adWeightGroupDAO;
	

	public AdWeightGroupDAO getAdWeightGroupDAO() {
		return adWeightGroupDAO;
	}

	public void setAdWeightGroupDAO(AdWeightGroupDAO adWeightGroupDAO) {
		this.adWeightGroupDAO = adWeightGroupDAO;
	}

	@Override
	public Long insert(AdWeightGroup record) {
		Long insert = (Long) adWeightGroupDAO.insert(record);
		return insert;
	}

	@Override
	public List<AdWeightGroupVo> queryList(AdWeightGroup condition) {
		List<AdWeightGroup> query = (List<AdWeightGroup>) adWeightGroupDAO.query(condition);
		 List<AdWeightGroupVo> parseToVoList = AnnotationParser.parseToVoList(AdWeightGroupVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(Long id) {
		AdWeightGroup find = this.adWeightGroupDAO.find(id);
		if (find != null) {
			adWeightGroupDAO.delete(find);
		}
	}
	
	@Override
	public AdWeightGroupVo find(Long id) {
		AdWeightGroup find = adWeightGroupDAO.find(id);
		if(find==null){
			return null;
		}
		AdWeightGroupVo parseToVo = AnnotationParser.parseToVo(AdWeightGroupVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<AdWeightGroupVo> queryPager(AdWeightGroup condition, int pageIndex, int rows) {
		AdPager<AdWeightGroup> queryPage = adWeightGroupDAO.queryPage(condition, pageIndex, rows);
		List<AdWeightGroup> result = queryPage.getRows();
		List<AdWeightGroupVo> parseToVoList = AnnotationParser.parseToVoList(AdWeightGroupVo.class, result);
		AdPager<AdWeightGroupVo> resultVo = new AdPager<AdWeightGroupVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(AdWeightGroup record) {
		if(record.getId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AdWeightGroup find = adWeightGroupDAO.find(record.getId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
 		adWeightGroupDAO.update(find);
		
	}
}






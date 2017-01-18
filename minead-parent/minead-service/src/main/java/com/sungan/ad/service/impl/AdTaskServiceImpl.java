package com.sungan.ad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.dao.base.AdTaskDAO;
import com.sungan.ad.domain.AdTask;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.AdTaskService;
import com.sungan.ad.vo.AdTaskVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdTaskServiceImpl implements AdTaskService{
	@Autowired
	private AdTaskDAO adTaskDAO;
	

	public AdTaskDAO getAdTaskDAO() {
		return adTaskDAO;
	}

	public void setAdTaskDAO(AdTaskDAO adTaskDAO) {
		this.adTaskDAO = adTaskDAO;
	}

	@Override
	public Long insert(AdTask adtask) {
		Long insert = (Long) adTaskDAO.insert(adtask);
		return insert;
	}

	@Override
	public List<AdTaskVo> queryList(AdTask adTask) {
		List<AdTask> query = (List<AdTask>) adTaskDAO.query(adTask);
		 List<AdTaskVo> parseToVoList = AnnotationParser.parseToVoList(AdTaskVo.class, query);
		return parseToVoList;
	}

	@Override
	public AdTaskVo queryById(Long id) {
		AdTask find = adTaskDAO.find(id);
		AdTaskVo parseToVo = AnnotationParser.parseToVo(AdTaskVo.class, find);
		return parseToVo;
	}

	@Override
	public void update(AdTask adTask) {
		if(adTask.getId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AdTask find = adTaskDAO.find(adTask.getId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(adTask, find);
			adTaskDAO.update(find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
	}
}






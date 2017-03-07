package com.sungan.ad.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.dao.AdPager;
import com.sungan.ad.dao.base.AdTaskDAO;
import com.sungan.ad.domain.AdTask;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.AdTaskService;
import com.sungan.ad.service.biz.AdTaskBiz;
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
		adtask.setCreateTime(new Date());
		adtask.setUpdateTime(new Date());
		adtask.setStatus(AdTask.ADTASK_STATUS_NOTPUBLIC);
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
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		AdTaskBiz bix = new AdTaskBiz(find);
		bix.update(adTaskDAO);
//			adTaskDAO.update(find);
		
	}

	@Override
	public AdPager<AdTaskVo> queryPager(AdTask adTask, int pageIndex, int rows) {
		AdPager<AdTask> queryPage = adTaskDAO.queryPage(adTask, pageIndex, rows);
		List<AdTaskVo> parseToVoList = AnnotationParser.parseToVoList(AdTaskVo.class, queryPage.getRows());
		AdPager<AdTaskVo> result = new AdPager<AdTaskVo>(pageIndex, rows, queryPage.getTotal());
		result.setRows(parseToVoList);
		return result;
	}
}






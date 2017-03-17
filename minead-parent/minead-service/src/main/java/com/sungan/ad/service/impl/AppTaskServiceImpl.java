package com.sungan.ad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.dao.AdPager;
import com.sungan.ad.dao.base.AdClientDAO;
import com.sungan.ad.dao.base.AppTaskDAO;
import com.sungan.ad.domain.AdClient;
import com.sungan.ad.domain.AppTask;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.AppTaskService;
import com.sungan.ad.vo.AppTaskVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AppTaskServiceImpl implements AppTaskService{
	
	@Autowired
	private AppTaskDAO appTaskDAO;
	@Autowired
	private  AdClientDAO adClientDAO;
	

	public AdClientDAO getAdClientDAO() {
		return adClientDAO;
	}

	public void setAdClientDAO(AdClientDAO adClientDAO) {
		this.adClientDAO = adClientDAO;
	}

	public AppTaskDAO getAppTaskDAO() {
		return appTaskDAO;
	}

	public void setAppTaskDAO(AppTaskDAO appTaskDAO) {
		this.appTaskDAO = appTaskDAO;
	}

	@Override
	public Long insert(AppTask record) {
		Long insert = (Long) appTaskDAO.insert(record);
		return insert;
	}

	@Override
	public List<AppTaskVo> queryList(AppTask condition) {
		List<AppTask> query = (List<AppTask>) appTaskDAO.query(condition);
		 List<AppTaskVo> parseToVoList = AnnotationParser.parseToVoList(AppTaskVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(Long id) {
		AppTask find = this.appTaskDAO.find(id);
		if (find != null) {
			appTaskDAO.delete(find);
		}
	}
	
	@Override
	public AppTaskVo find(Long id) {
		AppTask find = appTaskDAO.find(id);
		if(find==null){
			return null;
		}
		AppTaskVo parseToVo = AnnotationParser.parseToVo(AppTaskVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<AppTaskVo> queryPager(AppTask condition, int pageIndex, int rows) {
		AdPager<AppTask> queryPage = appTaskDAO.queryPage(condition, pageIndex, rows);
		List<AppTask> result = queryPage.getRows();
		List<AppTaskVo> parseToVoList = AnnotationParser.parseToVoList(AppTaskVo.class, result);
		for(AppTaskVo vo:parseToVoList){
			Long clientId = vo.getClientId();
			if(clientId!=null){
				AdClient find = adClientDAO.find(clientId);
				vo.setClientName(find.getName());
				vo.setClientIp(find.getCurrentIp());
			}
		}
		AdPager<AppTaskVo> resultVo = new AdPager<AppTaskVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(AppTask record) {
		if(record.getId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AppTask find = appTaskDAO.find(record.getId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
 		appTaskDAO.update(find);
		
	}
}






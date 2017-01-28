package com.sungan.ad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.dao.AdPager;
import com.sungan.ad.dao.base.AppTaskDAO;
import com.sungan.ad.domain.AppTask;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.AppTaskServivce;
import com.sungan.ad.vo.AppTaskVo;

/**
 * 说明:
 * @version V1.1
 */
@Service
public class AppTaskServivceImpl implements AppTaskServivce{
	private  AppTaskDAO appTaskDAO;
	@Autowired
	
	public AppTaskDAO getAppTaskDAO() {
		return appTaskDAO;
	}

	public void setAppTaskDAO(AppTaskDAO appTaskDAO) {
		this.appTaskDAO = appTaskDAO;
	}

	@Override
	public Long insert(AppTask task) {
		Long insert = (Long) appTaskDAO.insert(task);
		return insert;
	}

	@Override
	public void delete(Long id) {
		AppTask find = appTaskDAO.find(id);
		if(find!=null){
			appTaskDAO.delete(find);
		}
	}

	@Override
	public void update(AppTask task) {
		if(task.getId()==null){
			throw new AdRuntimeException("id为空");
		}
		AppTask find = this.appTaskDAO.find(task.getId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		AdCommonsUtil.beanCopyWithoutNull(task, find);
		appTaskDAO.update(find);
	}

	@Override
	public List<AppTaskVo> query(AppTask condition) {
		List<AppTask> query = (List<AppTask>) appTaskDAO.query(condition);
		List<AppTaskVo> parseToVoList = AnnotationParser.parseToVoList(AppTaskVo.class, query);
		return parseToVoList;
	}

	@Override
	public AdPager<AppTaskVo> queryPage(AppTask condition, int pageIndex, int rows) {
		AdPager<AppTask> queryPage = appTaskDAO.queryPage(condition, pageIndex, rows);
		List<AppTask> result = queryPage.getResult();
		List<AppTaskVo> parseToVoList = AnnotationParser.parseToVoList(AppTaskVo.class, result);
		AdPager<AppTaskVo> resultVo = new AdPager<AppTaskVo>(pageIndex, rows, queryPage.getCount());
		resultVo.setResult(parseToVoList);
		return resultVo;
	}

}

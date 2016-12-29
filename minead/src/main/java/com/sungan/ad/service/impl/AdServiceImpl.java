package com.sungan.ad.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.dao.base.AdDAO;
import com.sungan.ad.domain.AdContent;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.service.AdService;

/**
 * 说明:
 * 
 * @date 2016年12月27日 下午6:12:23
 * @version V1.1
 */
@Service
public class AdServiceImpl implements AdService{
	@Autowired
	private AdDAO adDAO;
	@Override
	public Long insert(AdContent content) {
		if(StringUtils.isBlank(content.getAppid())){
			throw new AdRuntimeException("appid不能为空");
		}
		if(StringUtils.isBlank(content.getContent())){
			throw new AdRuntimeException("报文不能为空");
		}
		Long insert = (Long) adDAO.insert(content);
		return insert;
	}
	public AdDAO getAdDAO() {
		return adDAO;
	}
	public void setAdDAO(AdDAO adDAO) {
		this.adDAO = adDAO;
	}
	@Override
	public AdContent query(String appid) {
		AdContent query = adDAO.query(appid);
		return query;
	}
	
	@Override
	public List<AdContent> queryByAppid(String appid) {
		List<AdContent> queryByAppid = adDAO.queryByAppid(appid);
		return queryByAppid;
	}
	@Override
	public AdContent update(Long id, String status) {
		if(id==null){
			throw new AdRuntimeException("不存在此记录");
		}
		AdContent find = adDAO.find(id);
		if(find==null){
			throw new AdRuntimeException("不存在此记录");
		}
		AdContent query = adDAO.query(find.getAppid());
		find.setStatus(status);
		adDAO.update(find);
		if(query!=null&&!query.getId().equals(find.getId())&&AdContent.PUBLIC_1.equals(status)){
			query.setStatus(AdContent.NOTPUBLIC_1);
			adDAO.update(query);
		}
		return find;
	}
}

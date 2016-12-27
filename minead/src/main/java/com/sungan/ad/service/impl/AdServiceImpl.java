package com.sungan.ad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.dao.base.AdDAO;
import com.sungan.ad.domain.AdContent;
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
		Long insert = (Long) adDAO.insert(content);
		AdContent query = adDAO.query(content.getAppid());
		if(query!=null){
			query.setStatus(AdContent.NOTPUBLIC_1);
			adDAO.update(query);
		}
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
}

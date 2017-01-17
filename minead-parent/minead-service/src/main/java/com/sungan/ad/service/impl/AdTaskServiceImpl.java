package com.sungan.ad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.dao.base.AdTaskDAO;
import com.sungan.ad.domain.AdTask;
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
		return null;
	}

	@Override
	public List<AdTaskVo> queryList(AdTask adTask) {
		return null;
	}

	@Override
	public AdTaskVo queryById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(AdTask adTask) {
		// TODO Auto-generated method stub
		
	}
	
}






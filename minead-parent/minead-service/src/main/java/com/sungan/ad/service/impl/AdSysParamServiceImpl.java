package com.sungan.ad.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.dao.base.AdSysParamDAO;
import com.sungan.ad.domain.AdSysParam;
import com.sungan.ad.service.AdSysParamService;

/**
 * 说明:
 * @version V1.1
 */
@Service
public class AdSysParamServiceImpl implements AdSysParamService {
	@Autowired
	private AdSysParamDAO adSysParamDAO;
	
	private static final String SYSRUNTIME="sys_runtime";
	
	@Override
	public Date getSysRuntime() {
		try {
			AdSysParam t = new AdSysParam();
			t.setParamName(SYSRUNTIME);
			List<AdSysParam> query = (List<AdSysParam>) adSysParamDAO.query(t);
			if(query==null||query.size()==0){
				return null;
			}
			AdSysParam adSysParam = query.get(0);
			String paramValue = adSysParam.getParamValue();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
			Date parse = format.parse(paramValue);
			return parse;
		} catch (ParseException e) {
			throw new RuntimeException("",e);
		}
	}
	
	
	public AdSysParamDAO getAdSysParamDAO() {
		return adSysParamDAO;
	}
	public void setAdSysParamDAO(AdSysParamDAO adSysParamDAO) {
		this.adSysParamDAO = adSysParamDAO;
	}
	
}

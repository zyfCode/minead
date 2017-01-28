package com.sungan.ad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.dao.AdPager;
import com.sungan.ad.dao.base.AdClientDAO;
import com.sungan.ad.dao.base.AdClientIpDAO;
import com.sungan.ad.domain.AdClient;
import com.sungan.ad.domain.AdClientIp;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.AdClientService;
import com.sungan.ad.vo.AdClientIpVo;
import com.sungan.ad.vo.AdClientVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdClientServiceImpl implements AdClientService{
	@Autowired
	private AdClientDAO adClientDAO;
	@Autowired
	private AdClientIpDAO adClientIpDAO;

	public AdClientDAO getAdClientDAO() {
		return adClientDAO;
	}

	public void setAdClientDAO(AdClientDAO adClientDAO) {
		this.adClientDAO = adClientDAO;
	}

	@Override
	public Long insert(AdClient client) {
		Long insert = (Long) adClientDAO.insert(client);
		return insert;
	}

	public AdClientIpDAO getAdClientIpDAO() {
		return adClientIpDAO;
	}

	public void setAdClientIpDAO(AdClientIpDAO adClientIpDAO) {
		this.adClientIpDAO = adClientIpDAO;
	}

	@Override
	public List<AdClientIpVo> queryIps(Long clientId) {
		AdClientIp t = new AdClientIp();
		t.setClientId(clientId);
		List<AdClientIp> query = (List<AdClientIp>) adClientIpDAO.query(t );
		List<AdClientIpVo> parseToVoList = AnnotationParser.parseToVoList(AdClientIpVo.class, query);
		return parseToVoList;
	}
	
	@Override
	public void update(AdClient client) {
		if(client.getId()==null){
			throw new AdRuntimeException("id为空");
		}
		AdClient find = this.adClientDAO.find(client.getId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		AdCommonsUtil.beanCopyWithoutNull(client, find);
		this.adClientDAO.update(find);
	}

	@Override
	public void delete(Long id) {
		AdClient find = this.adClientDAO.find(id);
		if(find!=null){
		adClientDAO.delete(find);
		}
	}

	@Override
	public List<AdClientVo> query() {
		List<AdClient> query = (List<AdClient>) adClientDAO.query();
		List<AdClientVo> parseToVoList = AnnotationParser.parseToVoList(AdClientVo.class, query);
		return parseToVoList;
	}

	@Override
	public List<AdClientVo> query(AdClient condition) {
		List<AdClient> query = (List<AdClient>) adClientDAO.query(condition);
		List<AdClientVo> parseToVoList = AnnotationParser.parseToVoList(AdClientVo.class, query);
		return parseToVoList;
	}

	@Override
	public AdPager<AdClientVo> queryPager(AdClient condition, int pageIndex, int rows) {
		AdPager<AdClient> queryPage = adClientDAO.queryPage(condition, pageIndex, rows);
		List<AdClient> result = queryPage.getResult();
		List<AdClientVo> parseToVoList = AnnotationParser.parseToVoList(AdClientVo.class,result);
		AdPager<AdClientVo> resultVo = new AdPager<AdClientVo>(pageIndex, rows, queryPage.getCount());
		resultVo.setResult(parseToVoList);
		return resultVo;
	}

}

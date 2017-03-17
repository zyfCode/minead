package com.sungan.ad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.dao.AdPager;
import com.sungan.ad.dao.base.AdContentDAO;
import com.sungan.ad.domain.AdContent;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.AdContentService;
import com.sungan.ad.vo.AdContentVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdContentServiceImpl implements AdContentService{
	
	@Autowired
	private AdContentDAO adContentDAO;
	

	public AdContentDAO getAdContentDAO() {
		return adContentDAO;
	}

	public void setAdContentDAO(AdContentDAO adContentDAO) {
		this.adContentDAO = adContentDAO;
	}

	@Override
	public Long insert(AdContent record) {
		Long insert = (Long) adContentDAO.insert(record);
		return insert;
	}

	@Override
	public List<AdContentVo> queryList(AdContent condition) {
		List<AdContent> query = (List<AdContent>) adContentDAO.query(condition);
		 List<AdContentVo> parseToVoList = AnnotationParser.parseToVoList(AdContentVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(Long id) {
		AdContent find = this.adContentDAO.find(id);
		if (find != null) {
			adContentDAO.delete(find);
		}
	}
	
	@Override
	public AdContentVo find(Long id) {
		AdContent find = adContentDAO.find(id);
		if(find==null){
			return null;
		}
		AdContentVo parseToVo = AnnotationParser.parseToVo(AdContentVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<AdContentVo> queryPager(AdContent condition, int pageIndex, int rows) {
		AdPager<AdContent> queryPage = adContentDAO.queryPage(condition, pageIndex, rows);
		List<AdContent> result = queryPage.getRows();
		List<AdContentVo> parseToVoList = AnnotationParser.parseToVoList(AdContentVo.class, result);
		AdPager<AdContentVo> resultVo = new AdPager<AdContentVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(AdContent record) {
		if(record.getId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AdContent find = adContentDAO.find(record.getId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
 		adContentDAO.update(find);
		
	}
}






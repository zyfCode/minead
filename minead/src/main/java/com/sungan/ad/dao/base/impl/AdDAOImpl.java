package com.sungan.ad.dao.base.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sungan.ad.dao.base.AdDAOAbstract;
import com.sungan.ad.domain.AdContent;

/**
 * 说明:
 * @date 2016年12月27日 下午6:06:54
 * @version V1.1
 */
@Repository
public class AdDAOImpl extends AdDAOAbstract {

	public AdContent query(String appid){
		Session currentSession = super.template.getSessionFactory().getCurrentSession();
		AdContent uniqueResult = (AdContent) currentSession.createCriteria(AdContent.class).add(Restrictions.eq("appid", appid)).add(Restrictions.eq("status", AdContent.PUBLIC_1)).uniqueResult();
		return uniqueResult;
	}

	@Override
	public List<AdContent> queryByAppid(String appid) {
		Session currentSession = super.template.getSessionFactory().getCurrentSession();
		Criteria createCriteria = currentSession.createCriteria(AdContent.class);//.add(Restrictions.eq("appid", appid)).list();
		if(StringUtils.isNotBlank(appid)){
			createCriteria = createCriteria.add(Restrictions.eq("appid", appid));
		}
		List<AdContent>  list = createCriteria.list();
		return list;
	}

}

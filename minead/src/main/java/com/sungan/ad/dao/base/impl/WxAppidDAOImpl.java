package com.sungan.ad.dao.base.impl;

import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sungan.ad.dao.base.WxAppidDAOAbstract;
import com.sungan.ad.domain.WxAppid;

/**
 * 说明:
 * 
 * @date 2016年12月28日 下午7:55:50
 * @version V1.1
 */
@Repository
public class WxAppidDAOImpl extends WxAppidDAOAbstract{

	@Override
	public WxAppid query(String appId) {
		Session currentSession = super.template.getSessionFactory().getCurrentSession();
		WxAppid wxAppid = (WxAppid) currentSession.createCriteria(WxAppid.class).add(Restrictions.eq("appId", appId)).uniqueResult();
		return wxAppid;
	}

}

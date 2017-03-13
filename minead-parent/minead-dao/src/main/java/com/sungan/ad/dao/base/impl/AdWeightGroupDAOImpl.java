package com.sungan.ad.dao.base.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sungan.ad.dao.base.AdWeightGroupDAOAbstract;
import com.sungan.ad.domain.AdWeightGroup;


/**
 * 说明:
 * 
 * @version V1.1
 */
@Repository
public class AdWeightGroupDAOImpl extends AdWeightGroupDAOAbstract {

	@Override
	public List<AdWeightGroup> queryRoot() {
		List<AdWeightGroup> queryIsEmpty = (List<AdWeightGroup>) super.queryIsEmpty("parentId");
		return queryIsEmpty;
	}


}

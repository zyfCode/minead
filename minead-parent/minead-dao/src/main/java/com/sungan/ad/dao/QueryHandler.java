package com.sungan.ad.dao;

import org.hibernate.Criteria;

/**
 * 说明:
 * @version V1.1
 */
public interface QueryHandler {

	Criteria addCondition(Criteria createCriteria);
}

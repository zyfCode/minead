package com.sungan.ad.dao.base.impl;

import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sungan.ad.dao.QueryHandler;
import com.sungan.ad.dao.base.AppTaskDAOAbstract;
import com.sungan.ad.domain.AppTask;

/**
 * 说明:
 * @version V1.1
 */
@Repository
public class AppTaskDAOImpl extends AppTaskDAOAbstract {

	@Override
	public Collection<AppTask> queryBigNow( AppTask t) {
		 final Date taskRunTime = t.getTaskRunTime();
		QueryHandler handler = new QueryHandler() {
			@Override
			public Criteria addCondition(Criteria createCriteria) {
				Criteria add = createCriteria.add(Restrictions.ge("taskRunTime",taskRunTime));
				return add;
			}
		};
		t.setTaskRunTime(null);
		Collection<AppTask> query = super.query(t, handler );
		return query;
	}

	@Override
	public Collection<AppTask> queryLeNow(AppTask t) {
		 final Date taskRunTime = t.getTaskRunTime();
		QueryHandler handler = new QueryHandler() {
			@Override
			public Criteria addCondition(Criteria createCriteria) {
				Criteria add = createCriteria.add(Restrictions.le("taskRunTime", taskRunTime));
				return add;
			}
		};
		t.setTaskRunTime(null);
		Collection<AppTask> query = super.query(t, handler );
		return query;
	}
	
	
}

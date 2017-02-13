package com.sungan.ad.dao.base;

import java.util.Collection;

import com.sungan.ad.dao.DAO;
import com.sungan.ad.domain.AppTask;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2017年1月18日 上午10:28:13
 * @version V1.1
 */
public interface AppTaskDAO extends DAO<AppTask> {
	Collection<AppTask> queryBigNow( AppTask t);
	Collection<AppTask> queryLeNow( AppTask t);
}

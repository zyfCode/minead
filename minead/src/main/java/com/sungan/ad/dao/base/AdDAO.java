package com.sungan.ad.dao.base;

import java.util.List;

import com.sungan.ad.dao.DAO;
import com.sungan.ad.domain.AdContent;

/**
 * 说明:
 * 
 * @date 2016年12月27日 下午6:03:38
 * @version V1.1
 */
public interface AdDAO extends DAO<AdContent>{
	AdContent query(String appid);

	List<AdContent> queryByAppid(String appid);
}

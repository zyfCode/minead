package com.sungan.ad.dao.base;

import java.util.List;

import com.sungan.ad.dao.DAO;
import com.sungan.ad.domain.AdWeightGroup;

/**
 * 说明:
 * @version V1.1
 */
public interface AdWeightGroupDAO extends DAO<AdWeightGroup> {
	/**
	 * 查询根节点
	 * @return
	 */
	List<AdWeightGroup> queryRoot();


}
package com.sungan.ad.service;

import java.util.List;

import com.sungan.ad.domain.AdContent;

/**
 * 说明:
 * 
 * @date 2016年12月27日 下午6:09:19
 * @version V1.1
 */
public interface AdService {
	Long insert(AdContent content);
	void update(AdContent content);
	AdContent query(String appid);
	AdContent delelte(Long id);
	AdContent query(Long id);
	/**
	 * 根据ippid查询
	 * @param appid
	 * @return
	 */
	List<AdContent> queryByAppid(String appid);
	AdContent update(Long id, String status);
}

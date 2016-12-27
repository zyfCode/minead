package com.sungan.ad.service;

import com.sungan.ad.domain.AdContent;

/**
 * 说明:
 * 
 * @date 2016年12月27日 下午6:09:19
 * @version V1.1
 */
public interface AdService {
	Long insert(AdContent content);
	AdContent query(String appid);
}

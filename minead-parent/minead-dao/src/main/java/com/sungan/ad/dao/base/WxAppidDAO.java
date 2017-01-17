package com.sungan.ad.dao.base;

import com.sungan.ad.dao.DAO;
import com.sungan.ad.domain.WxAppid;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2016年12月28日 下午7:53:51
 * @version V1.1
 */
public interface WxAppidDAO extends DAO<WxAppid>{

	WxAppid query(String appId);

}

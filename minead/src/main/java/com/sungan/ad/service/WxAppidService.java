package com.sungan.ad.service;

import java.util.List;

import com.sungan.ad.domain.WxAppid;
import com.sungan.ad.vo.WxAppidVo;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2016年12月28日 下午7:57:16
 * @version V1.1
 */
public interface WxAppidService {
	 List<WxAppidVo> queryAall();
	 Long insert(WxAppid wx);

}

package com.sungan.ad.web.chain;

import org.springframework.web.context.request.NativeWebRequest;

/**
 * 说明:
 * 
 * @date 2017年3月5日 下午6:50:45
 * @version V1.1
 */
public interface IntercepterHolder {
	 Object dochain(NativeWebRequest request,ChainBean bean) throws Exception;
}

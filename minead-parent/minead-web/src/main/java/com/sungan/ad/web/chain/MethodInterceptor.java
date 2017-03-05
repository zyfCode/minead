package com.sungan.ad.web.chain;

import org.springframework.web.context.request.NativeWebRequest;

/**
 * 说明:
 * @version V1.1
 */
public interface MethodInterceptor {
	/**
	 * 链的执行器
	 * @param holder
	 * @param request
	 * @param bean
	 * @return
	 */
	public Object invokeChain(IntercepterHolder holder,NativeWebRequest request,ChainBean bean) throws Exception;
	
}

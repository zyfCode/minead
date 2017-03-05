package com.sungan.ad.web.chain;

import org.springframework.web.context.request.NativeWebRequest;

/**
 * 说明:
 * @version V1.1
 */
public class MethodIntercepterHolder implements IntercepterHolder {
	private MethodInterceptor interceptor;
	private IntercepterHolder nexHolder;
	
	
	public MethodIntercepterHolder(MethodInterceptor interceptor, IntercepterHolder nexHolder) {
		super();
		this.interceptor = interceptor;
		this.nexHolder = nexHolder;
	}

	public Object dochain(NativeWebRequest request,ChainBean bean) throws Exception{
		Object result = null;
		result = interceptor.invokeChain(nexHolder, request, bean);
		return result;
	}
}

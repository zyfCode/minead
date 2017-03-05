package com.sungan.ad.web.chain.interceptor.impl;

import org.springframework.web.context.request.NativeWebRequest;

import com.sungan.ad.web.chain.ChainBean;
import com.sungan.ad.web.chain.IntercepterHolder;
import com.sungan.ad.web.chain.MethodInterceptor;

/**
 * 说明:
 * @version V1.1
 */
public class MethodInterceptorT1 implements MethodInterceptor{
	@Override
	public Object invokeChain(IntercepterHolder holder, NativeWebRequest request, ChainBean bean) throws Exception{
		System.out.println(this +" before :==================");
 		Object dochain = holder.dochain(request, bean);
		System.out.println(this +" aft :==================");
		return dochain;
	}

}

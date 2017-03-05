package com.sungan.ad.web.chain;

import java.util.List;

import org.springframework.web.context.request.NativeWebRequest;

/**
 * 说明:
 * @version V1.1
 */
public class MethodChainManager {
	private MethodIntercepterHolder root;  //链的第一个节点
	private ThreadLocal<MethodInvoker> threadLocal = new ThreadLocal<MethodInvoker>();
	
	public Object invoke(MethodInvoker invoker,NativeWebRequest request,ChainBean bean) throws Exception{
			if(root!=null){
				try {
					threadLocal.set(invoker);
					Object dochain = root.dochain(request, bean);
					return dochain;
				} finally {
					threadLocal.remove();
				}
			}else{
				return invoker.invoke();
			}
	}
	
	public MethodInvoker getResolver(){
		return threadLocal.get();
	}
	
	
	public void setMethodInterceptors(List<MethodInterceptor> chains){
		if(chains!=null&&chains.size()>0){
			MethodIntercepterHolder next = null;
			for(int i=chains.size()-1;i>=0;i--){
				MethodInterceptor methodInterceptor = chains.get(i);
				MethodIntercepterHolder holder = null;
				if(next==null){  //最后一个节点
					holder = new MethodIntercepterHolder(methodInterceptor, new IntercepterHolder(){
						@Override
						public Object dochain(NativeWebRequest request, ChainBean bean) throws Exception {
							Object invoke = MethodChainManager.this.getResolver().invoke();
							return invoke;
						}
						
					});  //最后一个节点设置执行方法的勾子
				}else{
					holder = new MethodIntercepterHolder(methodInterceptor, next);
				}
				next = holder;
				if(i==0){
					root  = holder;
				}
			}
		}
		
	}
}

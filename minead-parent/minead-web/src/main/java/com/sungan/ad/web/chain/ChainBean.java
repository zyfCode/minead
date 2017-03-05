package com.sungan.ad.web.chain;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 说明:
 * @version V1.1
 */
public class ChainBean {
	private Method method;
	private  Object [] args;
	private Object obj;
	
	public ChainBean(Method method, Object[] args, Object obj) {
		super();
		this.method = method;
		this.args = args;
		this.obj = obj;
	}



	public MethodParam[] getMethodParam(){
		Class<?>[] parameterTypes = method.getParameterTypes();
		if(parameterTypes==null||parameterTypes.length<1){
			return null;
		}
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		 MethodParam [] methodParam = new MethodParam[parameterTypes.length];
		 for(int i=0;i<parameterTypes.length;i++){
			 MethodParam param = new MethodParam(parameterTypes[i], args[i], parameterAnnotations[i]);
			 methodParam[i]=param;
		 }
		 return methodParam;
	}



	public Method getMethod() {
		return method;
	}



	public void setMethod(Method method) {
		this.method = method;
	}



	public Object[] getArgs() {
		return args;
	}



	public void setArgs(Object[] args) {
		this.args = args;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}

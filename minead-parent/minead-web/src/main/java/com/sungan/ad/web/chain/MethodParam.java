package com.sungan.ad.web.chain;

import java.lang.annotation.Annotation;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class MethodParam {
	private Class<?> clazz;
	private Object instance;
	private Annotation[] annotations;
	
	public MethodParam(Class<?> clazz, Object instance, Annotation[] annotations) {
		super();
		this.clazz = clazz;
		this.instance = instance;
		this.annotations = annotations;
	}



	public Object getInstance() {
		return instance;
	}



	public Annotation[] getAnnotations() {
		return annotations;
	}



	public Class<?> getClazz() {
		return clazz;
	}
}

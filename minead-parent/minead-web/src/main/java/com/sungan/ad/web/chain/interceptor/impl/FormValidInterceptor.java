package com.sungan.ad.web.chain.interceptor.impl;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.web.context.request.NativeWebRequest;

import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.web.chain.ChainBean;
import com.sungan.ad.web.chain.IntercepterHolder;
import com.sungan.ad.web.chain.MethodInterceptor;
import com.sungan.ad.web.chain.MethodParam;

/**
 * 说明:
 * @version V1.1
 */
public class FormValidInterceptor implements MethodInterceptor{
	@Override
	public Object invokeChain(IntercepterHolder holder, NativeWebRequest request, ChainBean bean) throws Exception{
		MethodParam[] methodParam = bean.getMethodParam();
		if(methodParam!=null&&methodParam.length>0){
			for(MethodParam param:methodParam){
				Annotation[] annotations = param.getAnnotations();
				if(annotations!=null){
					for(Annotation at:annotations){
						if(at instanceof Valid){
							Object instance = param.getInstance();
							if(instance!=null){
								ValidatorFactory buildDefaultValidatorFactory = Validation.buildDefaultValidatorFactory();
								Validator validator = buildDefaultValidatorFactory.getValidator();
								Set<ConstraintViolation<Object>> validate = validator.validate(instance);
								for(ConstraintViolation<Object> ct:validate){
									String message = ct.getMessage();
									throw new AdRuntimeException(message);
								}
							}
							
						}
					}
				}
			}
		}
 		Object dochain = holder.dochain(request, bean);
		return dochain;
	}

}

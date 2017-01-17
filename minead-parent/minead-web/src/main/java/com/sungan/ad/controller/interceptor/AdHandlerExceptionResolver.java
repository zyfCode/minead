package com.sungan.ad.controller.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.sungan.ad.commons.AdConstants;
import com.sungan.ad.exception.AdRuntimeException;

/**
 * 说明:
 * 
 * @date 2016年12月28日 下午11:09:09
 * @version V1.1
 */
public class AdHandlerExceptionResolver implements HandlerExceptionResolver{
	private static final Log log = LogFactory.getLog(AdHandlerExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		if(ex!=null && ex instanceof AdRuntimeException){
			try {
				if(log.isInfoEnabled()){
					log.info("", ex);
				}
				String message = ex.getMessage();
				response.setContentType(AdConstants.JSONTYPE);
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(message);
				response.getWriter().flush();
			} catch (IOException e) {
				 log.error("", e);

			}
			return null;
		}else{
			throw new RuntimeException("",ex);
		}
	}

}

package com.sungan.ad.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hundsun.jresplus.common.util.StringUtil;
import com.sungan.ad.commons.AdConstants;

/**
 * 说明:
 * 
 * @date 2016年12月27日 下午7:06:53
 * @version V1.1
 */
public class LoginInterceptor   implements HandlerInterceptor  {
	private static Log log = LogFactory.getLog(LoginInterceptor.class);
	private String isMock;
	

	public String getIsMock() {
		return isMock;
	}

	public void setIsMock(String isMock) {
		this.isMock = isMock;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse resonse, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView modelandview)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		StringBuffer requestURL = request.getRequestURL();
		System.out.println(requestURL);
		if(StringUtil.isNotBlank(isMock)&&isMock.equalsIgnoreCase("true")){
			return true;
		}else{
			Object attribute = request.getSession().getAttribute(AdConstants.ISLOGIN);
			String contextPath = request.getContextPath();
			String context = "";
			if(StringUtil.isNotBlank(contextPath)&&!contextPath.endsWith("/")){
				context = contextPath;
			}
			if(attribute!=AdConstants.ISLOGIN_VALUE){
				response.sendRedirect(context+"/login.htm");
				return false;
			}else{
				return true;
			}
		}
		
	}
	
}


























package com.sungan.ad.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sungan.ad.commons.AdConstants;
import com.sungan.ad.commons.JAXBUtil;
import com.sungan.ad.wx.access.bean.AdXMLInterface;

/**
 * 说明:
 * 
 * @date 2016年12月27日 下午7:06:53
 * @version V1.1
 */
public class JsonInterceptor   implements HandlerInterceptor  {
	private static Log log = LogFactory.getLog(JsonInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse resonse, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion............");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView modelandview)
			throws Exception {
			Object object = request.getAttribute(AdConstants.JSONRESPONSE);
			Object xmlobject = request.getAttribute(AdConstants.XMLRESPONSE);
			Object txtobject = request.getAttribute(AdConstants.TXTRESPONSE);
			if(object!=null){
				arg1.setContentType(AdConstants.JSONTYPE);
				arg1.setCharacterEncoding("UTF-8");
				JSONObject obj = new JSONObject(object);
				String jsonStr = obj.toString();
				if(log.isInfoEnabled()){
					log.info("****************响应报文开始**************");
					log.info(jsonStr);
					log.info("****************响应报文结束**************");
				}
				arg1.getWriter().write(jsonStr);
				arg1.getWriter().flush();
			}else if(xmlobject!=null){
				arg1.setContentType(AdConstants.XMLTYPE);
				arg1.setCharacterEncoding("UTF-8");
				String xml=null;
				if(xmlobject instanceof AdXMLInterface){
					xml = JAXBUtil.ojbectToXmlWithCDATA(xmlobject);
				}else{
					xml = xmlobject.toString();
				}
				if(log.isInfoEnabled()){
					log.info("****************响应报文开始**************");
					log.info(xml);
					log.info("****************响应报文结束**************");
				}
				arg1.getWriter().write(xml);
				arg1.getWriter().flush();
			}else if(txtobject!=null){
				arg1.setContentType(AdConstants.TXTTYPE);
				arg1.setCharacterEncoding("UTF-8");
				String result=  txtobject.toString();
				if(log.isInfoEnabled()){
					log.info("****************响应报文开始**************");
					log.info(result);
					log.info("****************响应报文结束**************");
				}
				arg1.getWriter().write(result);
				arg1.getWriter().flush();
			}
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		return true;
	}
	
}


























package com.sungan.ad.commons;

import javax.servlet.http.HttpServletRequest;

/**
 * 说明:
 * 
 * @date 2016年12月27日 下午7:01:29
 * @version V1.1
 */
public class AdConstants {
	private AdConstants(){}
	public static final String XMLRESPONSE = "XMLRESPONSE";
	public static final String JSONRESPONSE = "JSONRESPONSE";
	public static final String JSONTYPE = "application/json;charset=UTF-8";
	
	/**
	 * 获取用户IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteIpAddress(HttpServletRequest request) {
		// 获取登录IP
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)
				|| "null".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)
				|| "null".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)
				|| "null".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return request.getRemoteAddr();
	}
}

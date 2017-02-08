package com.sungan.ad.commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

/**
 * 说明:
 * 
 * @date 2016年12月27日 下午7:01:29
 * @version V1.1
 */
public class AdConstants {
	private AdConstants(){}
	public static final String TXTRESPONSE = "TXTRESPONSE";
	public static final String XMLRESPONSE = "XMLRESPONSE";
	public static final String JSONRESPONSE = "JSONRESPONSE";
	public static final String JSPDATA = "data";
	public static final String JSONTYPE = "application/json;charset=UTF-8";
	public static final String XMLTYPE = "application/xml;charset=UTF-8";
	public static final String TXTTYPE = "text/plain;charset=UTF-8";
	
	
	public static String getStrFromRequest(HttpServletRequest request){
		try {
			ServletInputStream inputStream = request.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
			StringBuffer buf = new StringBuffer();
			String line = null;
			while((line=reader.readLine())!=null){
				buf.append(line);
			}
			return buf.toString();
		} catch (Exception e) {
			throw new RuntimeException("",e);
		}
	}
	
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

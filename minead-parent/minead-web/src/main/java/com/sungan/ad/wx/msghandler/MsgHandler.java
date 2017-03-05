package com.sungan.ad.wx.msghandler;

import javax.servlet.http.HttpServletRequest;

import com.sungan.ad.wx.access.bean.AdXMLInterface;

/**
 * 说明:
 * 
 * @version V1.1
 */
public interface MsgHandler {
	public AdXMLInterface doHandle(String appId,String msg,HttpServletRequest request);
}

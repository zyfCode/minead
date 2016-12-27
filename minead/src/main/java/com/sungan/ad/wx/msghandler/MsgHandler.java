package com.sungan.ad.wx.msghandler;

import javax.servlet.http.HttpServletRequest;

import com.sungan.ad.wx.access.bean.AdXMLInterface;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2016年12月28日 上午12:46:18
 * @version V1.1
 */
public interface MsgHandler {
	public AdXMLInterface doHandle(String appId,String msg,HttpServletRequest request);
}

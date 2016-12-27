package com.sungan.ad.wx.msghandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.sungan.ad.wx.access.bean.AdXMLInterface;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2016年12月28日 上午12:51:38
 * @version V1.1
 */
@Component
public class SubORunsubscribeHandler  implements MsgHandler{

	@Override
	public AdXMLInterface doHandle(String appid,String msg, HttpServletRequest request) {
		return null;
	}

}

package com.sungan.ad.wx.msghandler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sungan.ad.commons.SpringApplicationContextUtil;
import com.sungan.ad.wx.MsgType;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2016年12月28日 上午12:52:21
 * @version V1.1
 */
@Component
public class MsgHandlerFactory {
	
	
	@Autowired
	private MsgHandler subORunsubscribeHandler;
	@Autowired
	private MsgHandler usertowxHandler;
	
	public MsgHandler getUsertowxHandler() {
		return usertowxHandler;
	}

	public void setUsertowxHandler(MsgHandler usertowxHandler) {
		this.usertowxHandler = usertowxHandler;
	}

	public MsgHandler getSubORunsubscribeHandler() {
		return subORunsubscribeHandler;
	}

	public void setSubORunsubscribeHandler(MsgHandler subORunsubscribeHandler) {
		this.subORunsubscribeHandler = subORunsubscribeHandler;
	}

	public static MsgHandlerFactory bean ;
	public static MsgHandler  getHandler(String msg){
		if(MsgHandlerFactory.bean ==null){
			MsgHandlerFactory.bean = SpringApplicationContextUtil.applicationContext().getBean(com.sungan.ad.wx.msghandler.MsgHandlerFactory.class);
		}
		 MsgType type = MsgType.getType(msg);
		if(type==MsgType.SUBSCRIBE_OR_UNSUBSCRIBE){
			return MsgHandlerFactory.bean.getSubORunsubscribeHandler();
		}else if(type==MsgType.USERTOWX){
			return MsgHandlerFactory.bean.getUsertowxHandler();
		}
		return null;
	}
}

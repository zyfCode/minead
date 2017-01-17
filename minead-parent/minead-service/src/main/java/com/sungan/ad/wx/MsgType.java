package com.sungan.ad.wx;

import org.apache.commons.lang.StringUtils;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2016年12月28日 上午12:31:00
 * @version V1.1
 */
public enum MsgType {
	//SUBSCRIBE
	USERTOWX("usertowx","用户给微信号消息"),
	QRCODE("QRCODE","用户扫描二维码"),
	SUBSCRIBE_OR_UNSUBSCRIBE("SUBSCRIBE_OR_UNSUBSCRIBE","用户订阅||取消订阅");
	private String type;
	private String descript;
	private MsgType(String type, String descript) {
		this.type = type;
		this.descript = descript;
	}
	
	public static MsgType getType(String msg){
		if(StringUtils.isBlank(msg)){
			return null;
		}
		//TODO 此处应该改成把报文解释成XML再判断标签是否存在
		if(msg.contains("<MsgId>")&&msg.contains("<Content>")){
			return USERTOWX;
		}else if(msg.contains("<MsgType>")&&msg.contains("<Event>")){
			return SUBSCRIBE_OR_UNSUBSCRIBE;
		}else{
			throw new RuntimeException("【微信请求类型】未支持类型");
		}
		
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	
}

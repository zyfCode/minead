package com.sungan.ad.wx.access.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 说明:
 * 
  <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
  <FromUserName><![CDATA[FromUser]]></FromUserName>
 <CreateTime>123456789</CreateTime> <MsgType><![CDATA[event]]></MsgType>
 <Event><![CDATA[subscribe]]></Event> </xml>
 * 
 * @date 2016年12月29日 下午12:50:23
 * @version V1.1
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubORunsubscribeReuqest {
	public static final String SUBSCRIBE = "subscribe";
	public static final String UNSUBSCRIBE = "unsubscribe";
	@XmlElement(required = true, name = "ToUserName")
	private String toUserName;
	@XmlElement(required = true, name = "FromUserName")
	private String fromUserName;
	@XmlElement(required = true, name = "CreateTime")
	private String createTime;
	@XmlElement(required = true, name = "MsgType")
	private String msgType;
	@XmlElement(required = true, name = "Event")
	private String event;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
}

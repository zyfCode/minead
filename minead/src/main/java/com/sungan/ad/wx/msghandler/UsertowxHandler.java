package com.sungan.ad.wx.msghandler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sungan.ad.commons.JAXBUtil;
import com.sungan.ad.domain.AdContent;
import com.sungan.ad.service.AdService;
import com.sungan.ad.wx.access.bean.AdXMLInterface;
import com.sungan.ad.wx.access.bean.ReciveMsg;
import com.sungan.ad.wx.access.bean.WxResponseMsgTxt;
import com.sungan.ad.wx.access.bean.WxResponseMsgTxtAndImg;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2016年12月28日 上午12:49:05
 * @version V1.1
 */
@Component("usertowxHandler")
public class UsertowxHandler implements MsgHandler{
	private static final Log log = LogFactory.getLog(UsertowxHandler.class);
	@Autowired
	private AdService adService;
	@Override
	public AdXMLInterface doHandle(String appId,String msg, HttpServletRequest request) {
		try {
			ReciveMsg reciveBean = JAXBUtil.unmarshal(ReciveMsg.class, msg);
			String fromUserName = reciveBean.getFromUserName();
			String toUserName = reciveBean.getToUserName();
			AdContent query = adService.query(appId);
			String content = query.getContent();
			if(query.getType().equals(AdContent.TYPE_TEXT)) {
				WxResponseMsgTxt unmarshal = JAXBUtil.unmarshal(WxResponseMsgTxt.class, content);
				unmarshal.setToUserName(fromUserName);
				unmarshal.setFromUserName(toUserName);
				return unmarshal;
			}else if(query.getType().equals(AdContent.TYPE_TEXT_IMG)){
				//TODO 图文消息未实现 
				WxResponseMsgTxtAndImg unmarshal = JAXBUtil.unmarshal(WxResponseMsgTxtAndImg.class, content);
				unmarshal.setToUserName(fromUserName);
				unmarshal.setFromUserName(toUserName);
				return unmarshal;
			}else{
				log.warn("【响应报文件解析】未知类型："+query.getType());
			}
		} catch (Exception e) {
			throw new RuntimeException("",e);
		}
		return null;
	}
	public AdService getAdService() {
		return adService;
	}
	public void setAdService(AdService adService) {
		this.adService = adService;
	}
}

package com.sungan.ad.wx.msghandler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sungan.ad.commons.JAXBUtil;
import com.sungan.ad.domain.AdContent;
import com.sungan.ad.service.AdService;
import com.sungan.ad.wx.access.bean.AdXMLInterface;
import com.sungan.ad.wx.access.bean.SubORunsubscribeReuqest;
import com.sungan.ad.wx.access.bean.WxResponseMsgTxt;
import com.sungan.ad.wx.access.bean.WxResponseMsgTxtAndImg;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2016年12月28日 上午12:51:38
 * @version V1.1
 */
@Component("subORunsubscribeHandler")
public class SubORunsubscribeHandler  implements MsgHandler{
	private static final Log log = LogFactory.getLog(UsertowxHandler.class);
	@Autowired
	private AdService adService;
	@Override
	public AdXMLInterface doHandle(String appid,String msg, HttpServletRequest request) {
		try {
			SubORunsubscribeReuqest reciveBean = JAXBUtil.unmarshal(SubORunsubscribeReuqest.class, msg);
			if(reciveBean.getEvent().equals(SubORunsubscribeReuqest.SUBSCRIBE)){
				String fromUserName = reciveBean.getFromUserName();
				String toUserName = reciveBean.getToUserName();
				AdContent query = adService.query(appid);
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
			}else{  //如果是取消订阅
				WxResponseMsgTxt unmarshal = new WxResponseMsgTxt();
				return unmarshal;
			}
		} catch (Exception e) {
			log .error("【订阅或者取消订阅】异常", e);
		}
		return null;
	}
	public AdService getAdService() {
		return adService;
	}
	public void setAdService(AdService adService) {
		this.adService = adService;
	}
	public static Log getLog() {
		return log;
	}
}

package com.sungan.ad.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sungan.ad.commons.AdConstants;
import com.sungan.ad.domain.AdContent;
import com.sungan.ad.service.AdService;
import com.sungan.ad.vo.AdContentVo;
import com.sungan.ad.wx.access.bean.AdXMLInterface;
import com.sungan.ad.wx.msghandler.MsgHandler;
import com.sungan.ad.wx.msghandler.MsgHandlerFactory;

/**
 * 说明:
 * 
 * @date 2016年12月27日 下午11:56:53
 * @version V1.1
 */
@Controller
@RequestMapping("/wx")
public class WXController {
	private static final Log log =LogFactory.getLog(WXController.class);
	@Autowired
	private AdService adService;
	
	@RequestMapping(value="/add")
	public String insert(AdContentVo vo) throws IllegalAccessException, InvocationTargetException{
		AdContent content = new AdContent();
		BeanUtils.copyProperties(content, vo);
		content.setUpdateTime(new Date());;
		content.setUpdateTime(new Date());
		adService.insert(content );
		return "index";
	}
	
	@RequestMapping(value="/sungan/{appid:\\w+}")
	public void acceptWxRequest(@RequestBody String xml,@PathVariable String appid,HttpServletRequest request){
		MsgHandler handler = MsgHandlerFactory.getHandler(xml);
		if(handler!=null){
			AdXMLInterface doHandle = handler.doHandle(appid, xml, request);
			request.setAttribute(AdConstants.XMLRESPONSE, doHandle);
		}else{
			log.error("【微信交互】获取不到报文处理类");
		}
	}

	public AdService getAdService() {
		return adService;
	}

	public void setAdService(AdService adService) {
		this.adService = adService;
	}
	
	
}

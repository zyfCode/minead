package com.sungan.ad.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sungan.ad.commons.AdConstants;
import com.sungan.ad.domain.AdContent;
import com.sungan.ad.domain.WxAppid;
import com.sungan.ad.service.AdService;
import com.sungan.ad.service.WxAppidService;
import com.sungan.ad.vo.AdContentVo;
import com.sungan.ad.vo.WxAppidVo;
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
	private static final String basepath="/wx";
	@Autowired
	private AdService adService;
	@Autowired
	private WxAppidService wxAppidService;
	
	
	public WxAppidService getWxAppidService() {
		return wxAppidService;
	}

	public void setWxAppidService(WxAppidService wxAppidService) {
		this.wxAppidService = wxAppidService;
	}

	@RequestMapping(value="/appid/toput")
	public String toAddAppid(){
		return "wx/appidadd";
	}
	
	@RequestMapping(value="/put/appid")
	public String putAppid(WxAppidVo vo){
		WxAppid wx = new WxAppid();
		wx.setAppId(vo.getAppId());
		wx.setName(vo.getName());
		wx.setSecret(vo.getSecret());
		wx.setUpdateTime(new Date());
		wx.setCreateTime(new Date());
		wxAppidService.insert(wx);
		return "redirect:"+basepath+"/appid/list";
	}
	
	@RequestMapping(value="/appid/list")
	public String listAppids(Model model){
		List<WxAppidVo> queryAall = wxAppidService.queryAall();
		model.addAttribute(AdConstants.JSPDATA, queryAall);
		return "wx/listappid";
	}
	
	@RequestMapping(value="/tousermsg")
	public String toputUserMsg(Model model){
		List<WxAppidVo> queryAall = wxAppidService.queryAall();
		model.addAttribute(AdConstants.JSPDATA, queryAall);
		return "wx/usermsg";
	}
	
	@RequestMapping(value="/list/usermsg")
	public String listUsermsg(String appid,Model model){
		List<WxAppidVo> queryAall = wxAppidService.queryAall();
			model.addAttribute("appidData", queryAall);
		 List<AdContent> queryByAppid = adService.queryByAppid(appid);
		  model.addAttribute(AdConstants.JSPDATA, queryByAppid);
		  return "wx/listusermsg";
	}
	
	
	@RequestMapping(value="/update/usermsg")
	public String update(Long id,String status,RedirectAttributes redirectAttri) throws IllegalAccessException, InvocationTargetException{
		AdContent update = adService.update(id, status);
		redirectAttri.addAttribute("appid", update.getAppid());
		return "redirect:"+basepath+"/list/usermsg";
	}
	@RequestMapping(value="/put/usermsg")
	public String insert(AdContentVo vo,RedirectAttributes redirectAttri) throws IllegalAccessException, InvocationTargetException{
		AdContent content = new AdContent();
		BeanUtils.copyProperties(content, vo);
		content.setUpdateTime(new Date());;
		content.setUpdateTime(new Date());
		content.setStatus(AdContent.NOTPUBLIC_1);
		adService.insert(content );
		redirectAttri.addAttribute("appid", vo.getAppid());
		return "redirect:"+basepath+"/list/usermsg";
	}
	
	
	
	@RequestMapping(value="/sungan/{appid}")
//	@RequestMapping(value="/sungan",consumes = {"application/json","application/xml"})
	@ResponseBody
	public void acceptWxRequest(String xml,String signature,String timestamp,String echostr,@PathVariable("appid") String appid,HttpServletRequest request) throws IOException{
//	public void acceptWxRequest(String xml,String appid,HttpServletRequest request){
		
		ServletInputStream inputStream = request.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
		StringBuffer buf = new StringBuffer();
		String line = null;
		while((line=reader.readLine())!=null){
			buf.append(line);
		}
		if(log.isInfoEnabled()){
			log.info("****************请求报文件**********************");
			log.info("echostr:"+echostr+"data:"+buf);
			log.info("*****************请求报文件结束*********************");
		}
		
		xml = buf.toString();
		
		if(StringUtils.isNotBlank(echostr)){
			request.setAttribute(AdConstants.TXTRESPONSE, echostr);
			return;
		}
		MsgHandler handler = MsgHandlerFactory.getHandler(xml);
		if(handler!=null){
			AdXMLInterface doHandle = handler.doHandle(appid, xml, request);
			if(doHandle==null){
				request.setAttribute(AdConstants.TXTRESPONSE, "success");
			}else{
				request.setAttribute(AdConstants.XMLRESPONSE, doHandle);
			}
		}else{
			request.setAttribute(AdConstants.TXTRESPONSE, "success");
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

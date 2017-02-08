package com.sungan.ad.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sungan.ad.commons.AdConstants;
import com.sungan.ad.domain.AdContent;
import com.sungan.ad.service.AdService;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2016年12月27日 下午2:11:06
 * @version V1.1
 */
@Controller
@RequestMapping("/test")
public class BaseController {
	@Autowired
	private AdService adService;
	
	@RequestMapping("/index")
	public String toIndex(Model model) {
		AdContent content = new AdContent();
		content.setContent("HELLO");
		content.setCreateTime(new Date());
		content.setUpdateTime(new Date());
		content.setName("forTest");
//		adService.insert(content );
		model.addAttribute("data", "hello");
		return "index";
	}
	
	@RequestMapping(value="/josn",produces="application/json;charset=UTF-8")
	@ResponseBody
	public  void testJson(HttpServletRequest request){
		AdContent content = new AdContent();
		content.setContent("HELLO");
		content.setCreateTime(new Date());
		content.setUpdateTime(new Date());
		content.setName("forTest");
		content.setDescript("中国好");
		request.setAttribute(AdConstants.JSONRESPONSE, content);
	}
	@RequestMapping(value="/jt",produces="application/json;charset=UTF-8")
	@ResponseBody
	public  AdContent testJsonBean(HttpServletRequest request){
		AdContent content = new AdContent();
		content.setContent("HELLO");
		content.setCreateTime(new Date());
		content.setUpdateTime(new Date());
		content.setName("forTest");
		content.setDescript("中国好");
		return content;
	}

	public AdService getAdService() {
		return adService;
	}

	public void setAdService(AdService adService) {
		this.adService = adService;
	}
}

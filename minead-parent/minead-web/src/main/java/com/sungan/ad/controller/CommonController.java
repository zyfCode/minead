package com.sungan.ad.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hundsun.jresplus.common.util.StringUtil;
import com.sungan.ad.commons.AdConstants;
import com.sungan.ad.commons.CheckCodeAgent;
import com.sungan.ad.commons.ImageBuilder;


/**
 * 说明:
 */
@Controller
@RequestMapping("/cm")
public class CommonController {
	private static Random random = new Random();
	
	@Value(value = "${ad.mock}")
	private String isMock;
	
	public String getIsMock() {
		return isMock;
	}


	public void setIsMock(String isMock) {
		this.isMock = isMock;
	}


	@RequestMapping("/login")
	public String login(String user_name,String password,String checkCode,HttpServletRequest request){
		if(StringUtil.isNotBlank(isMock)&&isMock.equalsIgnoreCase("true")){
			request.getSession().setAttribute(AdConstants.ISLOGIN, AdConstants.ISLOGIN_VALUE);
			return "redirect:/home.htm?theme=horn#";
		}else{
			String attribute = request.getSession().getAttribute(CheckCodeAgent.Check_Code_Agent)+"";
			if("zhangyf".equals(user_name)&&"113355".equals(password)&&attribute.equals(checkCode)){
				request.getSession().setAttribute(AdConstants.ISLOGIN, AdConstants.ISLOGIN_VALUE);
				return "redirect:/home.htm?theme=horn#";
			}else{
				return "redirect:/login.htm";
			}
		}
	}
	
	
	@RequestMapping("/checkCode/get")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "width", defaultValue = "100") int width,
			@RequestParam(value = "height", defaultValue = "30") int height)
			throws Exception {
		response.setContentType("image/jpeg");
		String randomString = String.valueOf(random.nextInt(9000) + 1000);
		request.getSession(true).setAttribute(CheckCodeAgent.Check_Code_Agent,
				randomString);
		preventCaching(response);
		ImageBuilder.buildImage(response.getOutputStream(), width, height,
				randomString);
		return null;
	}
	
	private void preventCaching(HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
	}
}

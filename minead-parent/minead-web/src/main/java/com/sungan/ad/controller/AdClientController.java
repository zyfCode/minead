package com.sungan.ad.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sungan.ad.commons.AdConstants;
import com.sungan.ad.expand.common.bean.InitTaskConnectRequest;
import com.sungan.ad.expand.common.bean.InitTaskConnectResponse;
import com.sungan.ad.expand.common.bean.TaskRequest;
import com.sungan.ad.expand.common.bean.TaskResonse;
import com.sungan.ad.service.AdClientService;

import net.sf.json.JSONObject;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Controller
@RequestMapping("/client")
public class AdClientController {
	private static final Log log = LogFactory.getLog(AdClientController.class);
	@Autowired
	private AdClientService adClientService;
	
	@RequestMapping("/info")
	@ResponseBody
	public void heartInfo(Model model,@RequestBody String taskRequestStr,HttpServletRequest request){
//		TaskRequest taskRequest
		JSONObject fromObject = JSONObject.fromObject(taskRequestStr);
		TaskRequest bean = (TaskRequest) JSONObject.toBean(fromObject, TaskRequest.class);
		String remoteIpAddress = AdConstants.getRemoteIpAddress(request);
		TaskResonse hearInfo = adClientService.hearInfo(bean,remoteIpAddress);
		request.setAttribute(AdConstants.JSONRESPONSE,hearInfo);
	}
	
	
	@RequestMapping("/apploader")
	@ResponseBody
	public void clazzLoader(HttpServletRequest request,HttpServletResponse response){
		String realPath = request.getRealPath("/WEB-INF/lib");
		File file = new File(realPath);
		File[] listFiles = file.listFiles();
		for(File f:listFiles){
			if(f.isFile()&&f.getName().contains("minead-expand-impl")){
				InputStream instr = null;
				try {
					try {
						OutputStream outputStream = response.getOutputStream();
						instr = new FileInputStream(f);
						byte[] data =new byte[1024];
						int len = -1;
						while((len=instr.read(data))!=-1){
							outputStream.write(data, 0, len);
						}
					} finally{
						if(instr!=null){
							instr.close();
						}
					}
				} catch (IOException e) {
					log.error("",e);
				}
				break;
			}
		}
	}
	
	@RequestMapping("/initconnector")
	@ResponseBody
	public void connetorInit(Model model,String jsonData, HttpServletRequest request){
		jsonData = AdConstants.getStrFromRequest(request);
		JSONObject fromObject = JSONObject.fromObject(jsonData);
		InitTaskConnectRequest connectRequest = (InitTaskConnectRequest) JSONObject.toBean(fromObject, InitTaskConnectRequest.class);
		InitTaskConnectResponse response = null;
		response = adClientService.initConnect(connectRequest,AdConstants.getRemoteIpAddress(request));
		request.setAttribute(AdConstants.JSONRESPONSE,response);
	}

	public AdClientService getAdClientService() {
		return adClientService;
	}

	public void setAdClientService(AdClientService adClientService) {
		this.adClientService = adClientService;
	}
	
}

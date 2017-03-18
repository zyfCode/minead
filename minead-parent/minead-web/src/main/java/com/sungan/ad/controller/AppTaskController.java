package com.sungan.ad.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdResponse;
import com.sungan.ad.controller.validBean.AppUpdateTaskValid;
import com.sungan.ad.dao.AdPager;
import com.sungan.ad.domain.AppTask;
import com.sungan.ad.service.AppTaskService;
import com.sungan.ad.vo.AppTaskVo;

/**
 * 说明:
 */
@Controller
@RequestMapping("/apptask")
public class AppTaskController {
	private static Log log = LogFactory.getLog(AppTaskController.class);
	@Autowired
	private AppTaskService service;
	
	
	@RequestMapping("/deleteapptask")
	@ResponseBody
	public Object deleteapptask  (AppTask record){
		service.delete(record.getId());
		return new AdResponse();
	}
	@RequestMapping("/addapptask")
	@ResponseBody
	public Object addapptask (@Valid AppUpdateTaskValid record){
		AppTask w = new AppTask();
		AdCommonsUtil.copyProperties(w, record);
		service.insert(w);
		return new AdResponse();
	}
	
	@RequestMapping("/updateapptask")
	@ResponseBody
	public Object updateapptask(@Valid AppUpdateTaskValid record){
		AppTask w = new AppTask();
		AdCommonsUtil.copyProperties(w, record);
		service.update(w);
		return new AdResponse();
	}
	@RequestMapping("/listapptask.json")
	@ResponseBody
	public Object listTaskapptask(AppTask record,String taskRunTimeStr,Integer pageNo,Integer pageSize){
		if(record!=null){
			AdCommonsUtil.proStrEmpytToNull(record);
		}
		if(StringUtils.isNotBlank(taskRunTimeStr)){
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date parse = format.parse(taskRunTimeStr);
				record.setTaskRunTime(parse);
			} catch (Exception e) {
				log.error("", e);
			}
		}
		AdPager<AppTaskVo> queryPager = service.queryPager(record, pageNo, pageSize);
		return queryPager;
	}
}
package com.sungan.ad.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdConstants;
import com.sungan.ad.commons.AdResponse;
import com.sungan.ad.controller.validBean.AdTaskAddValid;
import com.sungan.ad.dao.AdPager;
import com.sungan.ad.domain.AdTask;
import com.sungan.ad.service.AdTaskService;
import com.sungan.ad.vo.AdTaskVo;

/**
 * 说明:
 * @version V1.1
 */
@Controller
@RequestMapping("/task")
public class TaskInfoController {
	@Autowired
	private AdTaskService service;
	
	@RequestMapping("/listtask.json")
	@ResponseBody
	public Object listTask(AdTask task,Integer pageNo,Integer pageSize){
		if(task!=null){
			AdCommonsUtil.proStrEmpytToNull(task);
		}
		AdPager<AdTaskVo> queryPager = service.queryPager(task, pageNo, pageSize);
		return queryPager;
	}
	
	@RequestMapping(value="/update",produces={"application/json"})
	@ResponseBody
	public Object update(AdTask task){
		if(task!=null){
			AdCommonsUtil.proStrEmpytToNull(task);
		}
		service.update(task);
		return new AdResponse();
	}
	@RequestMapping(value="/add",produces={"application/json"})
	@ResponseBody
	public Object add(@Valid AdTaskAddValid task, BindingResult result){
		System.out.println(result.hasErrors());
		  if (result.hasErrors()){
	            List<ObjectError> errorList = result.getAllErrors();
	            for(ObjectError error : errorList){
	                System.out.println(error.getDefaultMessage());
	            }
	        }
		if(task!=null){
			AdCommonsUtil.proStrEmpytToNull(task);
		}
//		service.insert(task);
		return new AdResponse();
	}
	
	
}

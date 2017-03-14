package com.sungan.ad.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdResponse;
import com.sungan.ad.controller.validBean.AdHourWeightAddValid;
import com.sungan.ad.controller.validBean.AdTaskAddValid;
import com.sungan.ad.dao.AdPager;
import com.sungan.ad.domain.AdHourWeight;
import com.sungan.ad.domain.AdTask;
import com.sungan.ad.service.AdHourWeightService;
import com.sungan.ad.service.AdTaskService;
import com.sungan.ad.vo.AdHourWeightVo;
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
	
	@Autowired
	private AdHourWeightService hourService;
	
	
	
	
	
	@RequestMapping("/deleteweight")
	@ResponseBody
	public Object deleteWeight(AdHourWeight weight){
		hourService.delete(weight.getId());
		return new AdResponse();
	}
	@RequestMapping("/addweight")
	@ResponseBody
	public Object addWeight(@Valid  AdHourWeightAddValid weight){
		AdHourWeight w = new AdHourWeight();
		AdCommonsUtil.copyProperties(w, weight);
		hourService.insert(w);
		return new AdResponse();
	}
	@RequestMapping("/updateweight")
	@ResponseBody
	public Object updateWeight(@Valid  AdHourWeightAddValid weight){
		AdHourWeight w = new AdHourWeight();
		AdCommonsUtil.copyProperties(w, weight);
		hourService.update(w);
		return new AdResponse();
	}
	@RequestMapping("/listweight.json")
	@ResponseBody
	public Object listTaskWeight(AdHourWeight weight,Integer pageNo,Integer pageSize){
		if(weight!=null){
			AdCommonsUtil.proStrEmpytToNull(weight);
		}
		AdPager<AdHourWeightVo> queryPager = hourService.queryPager(weight, pageNo, pageSize);
		return queryPager;
	}
	
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
	public Object update(@Valid  AdTask task){
		if(task!=null){
			AdCommonsUtil.proStrEmpytToNull(task);
		}
		service.update(task);
		return new AdResponse();
	}
	@RequestMapping(value="/add",produces={"application/json"})
	@ResponseBody
	public Object add(@Valid AdTaskAddValid task){
		if(task!=null){
			AdCommonsUtil.proStrEmpytToNull(task);
		}
		AdTask adTask = new AdTask();
		AdCommonsUtil.copyProperties(adTask, task);
		service.insert(adTask);
		return new AdResponse();
	}
	@RequestMapping(value="/delete",produces={"application/json"})
	@ResponseBody
	public Object delete(Long id){
		service.delete(id);
		return new AdResponse();
	}
	
	
}

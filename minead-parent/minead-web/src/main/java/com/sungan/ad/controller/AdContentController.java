package com.sungan.ad.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdResponse;
import com.sungan.ad.controller.validBean.AdContentValid;
import com.sungan.ad.dao.AdPager;
import com.sungan.ad.domain.AdContent;
import com.sungan.ad.service.AdContentService;
import com.sungan.ad.vo.AdContentVo;

/**
 * 说明:
 */
@Controller
@RequestMapping("/adcontent")
public class AdContentController {
	@Autowired
	private AdContentService service;
	
	
	@RequestMapping("/deleteadcontent")
	@ResponseBody
	public Object deleteadcontent  (AdContent record){
		service.delete(record.getId());
		return new AdResponse();
	}
	@RequestMapping("/addadcontent")
	@ResponseBody
	public Object addadcontent (@Valid  AdContentValid record){
		AdContent w = new AdContent();
		AdCommonsUtil.copyProperties(w, record);
		service.insert(w);
		return new AdResponse();
	}
	
	@RequestMapping("/updateadcontent")
	@ResponseBody
	public Object updateadcontent(@Valid  AdContentValid record){
		AdContent w = new AdContent();
		AdCommonsUtil.copyProperties(w, record);
		service.update(w);
		return new AdResponse();
	}
	@RequestMapping("/listadcontent.json")
	@ResponseBody
	public Object listTaskadcontent( AdContent record,Integer pageNo,Integer pageSize){
		if(record!=null){
			AdCommonsUtil.proStrEmpytToNull(record);
		}
		AdPager<AdContentVo> queryPager = service.queryPager(record, pageNo, pageSize);
		return queryPager;
	}
}
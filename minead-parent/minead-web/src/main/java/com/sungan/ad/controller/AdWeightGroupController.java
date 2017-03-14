package com.sungan.ad.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdResponse;
import com.sungan.ad.controller.validBean.AdWeightGroupValid;
import com.sungan.ad.controller.validBean.TreeVo;
import com.sungan.ad.dao.AdPager;
import com.sungan.ad.domain.AdWeightGroup;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.service.AdWeightGroupService;
import com.sungan.ad.vo.AdWeightGroupVo;

/**
 * 说明:
 */
@Controller
@RequestMapping("/adweightgroup")
public class AdWeightGroupController {
	@Autowired
	private AdWeightGroupService service;
	
	
	
	private  Object querySubNode(Long pId,HttpServletRequest request){
		if(pId==null){
			throw new AdRuntimeException("无节点 ");
		}
		AdWeightGroup condition = new AdWeightGroup();
		condition.setParentId(pId);
		List<AdWeightGroupVo> queryList = service.queryList(condition );
		if(queryList!=null){
			List<TreeVo> treeVos = new ArrayList<TreeVo>();
			for(AdWeightGroupVo vo:queryList){
				Boolean isLeaf = AdWeightGroup.ISLEAF.equals(vo.getIsLeaf());
				TreeVo treevo = new TreeVo(vo, null,!isLeaf);
				String taskCountStr = vo.getTaskCount()==null?"":vo.getTaskCount().toString();
				treevo.setTaskCount(taskCountStr);
				treevo.setIsDefault(vo.getIsDefault());
				treeVos.add(treevo);
			}
			return treeVos;
		}
		return queryList;
	}
	
	@RequestMapping("/root")
	@ResponseBody
	public Object queryRoot(Long id,Long pId,HttpServletRequest request){
		if(id!=null){
			Object queryLeaf = this.querySubNode(id, request);
			return queryLeaf;
		}
		List<AdWeightGroupVo> queryRoot = service.queryRoot();
		if(queryRoot!=null){
			List<TreeVo> treeVos = new ArrayList<TreeVo>();
			for(AdWeightGroupVo vo:queryRoot){
				TreeVo treevo = new TreeVo(vo, null,true);
				treevo.setIsDefault(vo.getIsDefault());
				String taskCountStr = vo.getTaskCount()==null?"":vo.getTaskCount().toString();
				treevo.setTaskCount(taskCountStr);
				treeVos.add(treevo);
			}
			return treeVos;
		}
		return queryRoot;
	}
	
	@RequestMapping("/deleteadweightgroup")
	@ResponseBody
	public Object deleteadweightgroup(AdWeightGroup record){
		service.delete(record.getId());
		return new AdResponse();
	}
	@RequestMapping("/addadweightgroup")
	@ResponseBody
	public Object addadweightgroup (@Valid AdWeightGroupValid record){
		AdWeightGroup w = new AdWeightGroup();
		AdCommonsUtil.copyProperties(w, record);
		service.insert(w);
		return new AdResponse();
	}
	
	@RequestMapping("/updateadweightgroup")
	@ResponseBody
	public Object updateadweightgroup(@Valid  AdWeightGroupValid record){
		AdWeightGroup w = new AdWeightGroup();
		AdCommonsUtil.copyProperties(w, record);
		service.update(w);
		return new AdResponse();
	}
	@RequestMapping("/listadweightgroup.json")
	@ResponseBody
	public Object listTaskadweightgroup(AdWeightGroup record,Integer pageNo,Integer pageSize){
		if(record!=null){
			AdCommonsUtil.proStrEmpytToNull(record);
		}
		AdPager<AdWeightGroupVo> queryPager = service.queryPager(record, pageNo, pageSize);
		return queryPager;
	}
}
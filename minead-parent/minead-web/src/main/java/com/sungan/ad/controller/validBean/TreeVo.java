package com.sungan.ad.controller.validBean;

import com.hundsun.jresplus.common.util.StringUtil;
import com.sungan.ad.vo.AdWeightGroupVo;

/**
 * 说明:
 */
public class TreeVo {
	private String id;
	private String name;
	private String pId;
	private String url;
	private Boolean isParent=false;
	private String click;
	private String isDefault;
	
	
	public String getClick() {
		return click;
	}
	public void setClick(String click) {
		this.click = click;
	}
	private String taskCount;
	
	public String getTaskCount() {
		return taskCount;
	}
	public void setTaskCount(String taskCount) {
		this.taskCount = taskCount;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public TreeVo (){}
	public TreeVo (AdWeightGroupVo vo,String url,Boolean isParent){
		this.id=vo.getId()+"";
		this.name = vo.getGroupName();
		if(vo.getParentId()!=null){
			this.pId = vo.getParentId().toString();
		}
		if(isParent!=null){
			this.isParent = isParent;
		}
		if(StringUtil.isBlank(url)){
			url="";
		}else{
			this.url = url+"?pId="+this.id;
		}
		this.click = "treeNodeClick('"+this.id+"','"+this.name+"','"+this.pId+"')";
//		this.click = "function(){treeNodeClick('"+this.id+"','"+this.name+"','"+this.pId+"')}";
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}

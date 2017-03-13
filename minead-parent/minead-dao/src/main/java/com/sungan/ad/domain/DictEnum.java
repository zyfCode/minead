package com.sungan.ad.domain;

import com.sungan.ad.commons.dict.DictItem;

/**
 * 说明:
 * 
 * @version V1.1
 */
public enum DictEnum {
	ADTASKTYPE(DictEnum.ADTASKTYPE_KEY,new DictItem[]{new DictItem(AdTask.ADTASK_TYPE_HOUR,"小时任务"),new DictItem(AdTask.ADTASK_TYPE_CIRCLE,"循环任务")},"任务字典"),
	ADTASKSTATUS(DictEnum.ADTASKSTATUS_KEY,new DictItem[]{new DictItem(AdTask.ADTASK_STATUS_PUBLIC,"已发布"),new DictItem(AdTask.ADTASK_STATUS_NOTPUBLIC,"未发布")},"任务状态"),
	ADCONTENTTYPE(DictEnum.ADCONTENTTYPE_KEY,new DictItem[]{new DictItem(AdContent.TYPE_TEXT,"文本"),new DictItem(AdContent.TYPE_TEXT_IMG,"图文")},"微信报文类型字典"),
	APPTASKTYPE(DictEnum.APPTASKTYPE_KEY,new DictItem[]{new DictItem(AppTask.APPTASK_TYPE_HOUR,"小时任务"),new DictItem(AppTask.APPTASK_TYPE_CIRCLE,"循环任务")},"APP任务字典"),
	ADCLIENTSTATUS(DictEnum.ADCLIENTSTATUS_KEY,new DictItem[]{new DictItem(AdClient.ADCLIENT_STATUS_INVALID,"停用"),new DictItem(AdClient.ADCLIENT_STATUS_RUNNING,"运行中")},"客户端状态"),
	ADWEIGHTGROUDEFAULT(DictEnum.ADWEIGHTGROUDEFAULT_KEY,new DictItem[]{new DictItem(AdWeightGroup.ISNOTDEFAULT,"非默认"),new DictItem(AdWeightGroup.ISDEFAULT,"默认")},"权重组是否是默认");
	public static final String ADTASKTYPE_KEY = "0001";   //任务类型
	public static final String ADTASKSTATUS_KEY = "000101";   //任务状态
	public static final String ADWEIGHTGROUDEFAULT_KEY = "000102";   //权重组是否是默认
	public static final String ADCONTENTTYPE_KEY="0002";
	public static final String APPTASKTYPE_KEY="0003";
	public static final String ADCLIENTSTATUS_KEY="0004";   //客户端状态
	
	String key;
	DictItem [] itams;
	String descript;
	private DictEnum(String key, DictItem[] itams, String descript) {
		this.key = key;
		this.itams = itams;
		this.descript = descript;
	}
	
	public String getKey() {
		return key;
	}

	public DictItem[] getItams() {
		return itams;
	}

	public String getDescript() {
		return descript;
	}

	public static DictItem [] getItems(String key){
		DictEnum[] values = DictEnum.values();
		for(DictEnum itam:values){
			if(itam.getKey().equals(key)){
				return itam.getItams();
			}
		}
		return null;
	}
}



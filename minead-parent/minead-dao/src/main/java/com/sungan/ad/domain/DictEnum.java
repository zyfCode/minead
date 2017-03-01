package com.sungan.ad.domain;

import com.sungan.ad.commons.dict.DictItem;

/**
 * 说明:
 * 
 * @version V1.1
 */
public enum DictEnum {
	ADTASKTYPE(DictEnum.ADTASKTYPE_KEY,new DictItem[]{new DictItem(AdTask.ADTASK_TYPE_HOUR,"小时任务"),new DictItem(AdTask.ADTASK_TYPE_CIRCLE,"循环任务")},"任务字典"),
	ADCONTENTTYPE(DictEnum.ADCONTENTTYPE_KEY,new DictItem[]{new DictItem(AdContent.TYPE_TEXT,"文本"),new DictItem(AdContent.TYPE_TEXT_IMG,"图文")},"微信报文类型字典"),
	APPTASKTYPE(DictEnum.APPTASKTYPE_KEY,new DictItem[]{new DictItem(AppTask.APPTASK_TYPE_HOUR,"小时任务"),new DictItem(AppTask.APPTASK_TYPE_CIRCLE,"循环任务")},"APP任务字典"),
	ADCLIENTSTATUS(DictEnum.ADCLIENTSTATUS_KEY,new DictItem[]{new DictItem(AdClient.ADCLIENT_STATUS_INVALID,"停用"),new DictItem(AdClient.ADCLIENT_STATUS_RUNNING,"运行中")},"客户端状态");
	public static final String ADTASKTYPE_KEY = "0001";
	public static final String ADCONTENTTYPE_KEY="0002";
	public static final String APPTASKTYPE_KEY="0003";
	public static final String ADCLIENTSTATUS_KEY="0004";
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



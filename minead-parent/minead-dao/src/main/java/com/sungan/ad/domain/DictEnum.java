package com.sungan.ad.domain;
/**
 * 说明:
 * 
 * @version V1.1
 */
public enum DictEnum {
	ADTASKTYPE("0001",new DictItem[]{new DictItem(AdTask.ADTASK_TYPE_HOUR,"小时任务"),new DictItem(AdTask.ADTASK_TYPE_CIRCLE,"循环任务")},"任务字典"),
	ADCONTENTTYPE("0002",new DictItem[]{new DictItem(AdContent.TYPE_TEXT,"文本"),new DictItem(AdContent.TYPE_TEXT_IMG,"图文")},"微信报文类型字典"),
	APPTASKTYPE("0003",new DictItem[]{new DictItem(AppTask.APPTASK_TYPE_HOUR,"小时任务"),new DictItem(AppTask.APPTASK_TYPE_CIRCLE,"循环任务")},"APP任务字典");
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



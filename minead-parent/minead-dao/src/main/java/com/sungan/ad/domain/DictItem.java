package com.sungan.ad.domain;

import java.io.Serializable;

/**
 * 说明:
 * 
 * @date 2017年1月18日 上午1:40:33
 * @version V1.1
 */
public class DictItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;
	private String value;
	public DictItem(){}
	public DictItem(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}

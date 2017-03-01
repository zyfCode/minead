package com.sungan.ad.commons.dict;

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
	private String label;
	public DictItem(){}
	public DictItem(String key, String label) {
		super();
		this.key = key;
		this.label = label;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Override
	public String toString() {
		return "DictItem [key=" + key + ", label=" + label + "]";
	}
	
}

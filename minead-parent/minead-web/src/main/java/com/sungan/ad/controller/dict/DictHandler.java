package com.sungan.ad.controller.dict;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.hundsun.jresplus.base.dict.DictEntry;

/**
 * 说明:
 */
public abstract class DictHandler implements InitializingBean{
	
   public abstract String getDicName();
   public abstract  DictEntry getDictEntry(String dictName, String keyName);
   public abstract List<DictEntry> getDicts(String dictName);
	@Override
	public void afterPropertiesSet() throws Exception {
		AdDictManager.addHandler(this);
	}
}

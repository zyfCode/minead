package com.sungan.ad.commons.dict;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.sungan.ad.commons.DictUtil;

/**
 * 说明:
 * @version V1.1
 */
public abstract class DictHandler implements InitializingBean{
	/**
	 * 根据数据字典大类
	 * @param dictKind
	 * @return
	 */
	public abstract List<DictItem> getDict(String dictKind);
	
	/**
	 * 根据数据字典以及key值获取数据字典值
	 * @param dictKind
	 * @param key
	 * @return
	 */
	public abstract DictItem getDictItem(String dictKind,String key);

	@Override
	public void afterPropertiesSet() throws Exception {
		DictUtil.setDictHandler(this);
	}
	
	
}

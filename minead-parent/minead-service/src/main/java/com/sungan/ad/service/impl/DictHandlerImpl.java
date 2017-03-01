package com.sungan.ad.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sungan.ad.commons.dict.DictHandler;
import com.sungan.ad.commons.dict.DictItem;
import com.sungan.ad.domain.DictEnum;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Component
public class DictHandlerImpl extends DictHandler{
	@Override
	public List<DictItem> getDict(String dictKind) {
		DictItem[] items = DictEnum.getItems(dictKind);
		if(items!=null){
			return Arrays.asList(items);
		}
		return null;
	}

	@Override
	public DictItem getDictItem(String dictKind, String key) {
		DictItem[] items = DictEnum.getItems(dictKind);
		if(items!=null){
			for(DictItem item:items){
				if(item.getKey().equals(key)){
					return item;
				}
			}
		}
		return null;
	}

}

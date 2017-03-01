package com.sungan.ad.controller.dict;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hundsun.jresplus.base.dict.DictEntry;
import com.hundsun.jresplus.base.dict.DictManager;
import com.sungan.ad.commons.DictUtil;
import com.sungan.ad.commons.dict.DictItem;

/**
 * 说明:
 * 
 */
@Component
public class AdDictManager implements DictManager {

	@Override
	public  DictEntry getDictEntry(String dictName, String keyName) {
		final DictItem dictItem = DictUtil.getDictItem(dictName,keyName);
		if(dictItem==null){
			return null;
		}
		DictEntry entry = new DictEntry() {
			
			@Override
			public String getValue() {
				return dictItem.getLabel();
			}
			
			@Override
			public String getLabel() {
				return dictItem.getKey();
			}
		};
		return entry;
	}

	@Override
	public   List<DictEntry> getDicts(String dictName) {
		List<DictItem> dict = DictUtil.getDict(dictName);
		List<DictEntry> arr = new ArrayList<DictEntry>();
		for(DictItem item:dict){
			final String key = item.getKey();
			final String label = item.getLabel();
			DictEntry entry = new DictEntry() {
				@Override
				public String getValue() {
					return label;
				}
				
				@Override
				public String getLabel() {
					return key;
				}
			};
			arr.add(entry);
		}
		return arr;
	}

}

package com.sungan.ad.controller.dict;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
	private static Set<DictHandler> handlers = new LinkedHashSet<DictHandler>();
	
	public static synchronized void addHandler(DictHandler handler){
		handlers.add(handler);
	}
	
	@Override
	public  DictEntry getDictEntry(String dictName, String keyName) {
		for(DictHandler handler:handlers){
			if(handler.getDicName().equals(dictName)){
				return handler.getDictEntry(dictName, keyName);
			}
		}
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
		for(DictHandler handler:handlers){
			if(handler.getDicName().equals(dictName)){
				return handler.getDicts(dictName);
			}
		}
		
		List<DictItem> dict = DictUtil.getDict(dictName);
		List<DictEntry> arr = new ArrayList<DictEntry>();
		if(dict==null){
			return arr;
		}
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

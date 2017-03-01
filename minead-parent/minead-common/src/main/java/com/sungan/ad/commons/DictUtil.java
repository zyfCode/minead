package com.sungan.ad.commons;

import java.util.List;

import com.sungan.ad.commons.dict.DictHandler;
import com.sungan.ad.commons.dict.DictItem;

/**
 * 说明:
 * @version V1.1
 */
public class DictUtil {
	
	private static DictHandler dictHandler;
	
	public static List<DictItem> getDict(String dictKind){
		List<DictItem> dict = dictHandler.getDict(dictKind);
		return dict;
	}
	
	public static DictItem getDictItem(String dictKind,String key){
		DictItem dictItem = dictHandler.getDictItem(dictKind, key);
		return dictItem;
	}

	
	public static void setDictHandler(DictHandler dictHandler) {
		DictUtil.dictHandler = dictHandler;
	}

	public static DictHandler getDictHandler() {
		return dictHandler;
	}
}

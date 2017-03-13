package com.sungan.ad.controller.dict.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hundsun.jresplus.base.dict.DictEntry;
import com.sungan.ad.commons.dict.DictItem;
import com.sungan.ad.controller.dict.DictHandler;
import com.sungan.ad.domain.AdWeightGroup;
import com.sungan.ad.service.AdWeightGroupService;
import com.sungan.ad.vo.AdWeightGroupVo;

/**
 * 说明:
 */
@Component
public class TreeDictHandler extends DictHandler {
	
	@Autowired
	private AdWeightGroupService service;
	
	public AdWeightGroupService getService() {
		return service;
	}

	public void setService(AdWeightGroupService service) {
		this.service = service;
	}

	@Override
	public String getDicName() {
		return "weightGroupTree";
	}

	@Override
	public DictEntry getDictEntry(String dictName, String keyName) {
		AdWeightGroupVo find = service.find(Long.valueOf(keyName));
		//移根节点
		if(find!=null){
				final String key = find.getId()+"";
				final String label = find.getGroupName();
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
				
				return entry;
		}
		return null;
	}

	@Override
	public List<DictEntry> getDicts(String dictName) {
		AdWeightGroup condition = new AdWeightGroup();
		condition.setIsLeaf(AdWeightGroup.ISLEAF);
		List<AdWeightGroupVo> queryList = service.queryList(condition);
		//移根节点
		if(queryList!=null){
			Iterator<AdWeightGroupVo> iterator = queryList.iterator();
			List<DictEntry> arr = new ArrayList<DictEntry>();
			while(iterator.hasNext()){
				AdWeightGroupVo next = iterator.next(); 
					final String key = next.getId()+"";
					final String label = next.getGroupName();
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
		return null;
	}
}

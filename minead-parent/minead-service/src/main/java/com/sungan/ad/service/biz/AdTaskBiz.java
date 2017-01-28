package com.sungan.ad.service.biz;

import java.util.Collection;

import org.apache.commons.beanutils.BeanUtils;

import com.sungan.ad.dao.base.AdTaskDAO;
import com.sungan.ad.domain.AdTask;
import com.sungan.ad.exception.AdRuntimeException;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class AdTaskBiz extends AdTask {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdTask task;
	
	public AdTaskBiz(AdTask task){
		try {
			BeanUtils.copyProperties(this, task);
			this.task = task;
		} catch (Exception e) {
			throw new RuntimeException("",e);
		}
	}
	
	public void update(AdTaskDAO adTaskDAO){
		if(this.getStatus().equals(AdTask.ADTASK_STATUS_PUBLIC)){
			AdTask t = new AdTask();
			t.setGroup(this.getGroup()); 
			Collection<AdTask> query = adTaskDAO.query(t);
			if(query!=null){
				for(AdTask tk:query){
					if(!tk.getId().equals(this.getId())&&tk.getStatus().equals(AdTask.ADTASK_STATUS_PUBLIC)){
						throw new AdRuntimeException("组"+tk.getGroup()+"已存在发布的任务");
					}
				}
			}
		}
		try {
			BeanUtils.copyProperties(task, this);
			adTaskDAO.update(this);
		} catch (Exception e) {
			throw new RuntimeException("",e);
		}
	}

}

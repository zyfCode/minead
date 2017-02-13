package com.sungan.ad.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sungan.ad.cmmon.test.BaseTest;
import com.sungan.ad.domain.AdTask;
import com.sungan.ad.service.AdTaskService;
import com.sungan.ad.service.ext.AdTaskManager;
import com.sungan.ad.vo.AdTaskVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class ServiceAdTest  extends BaseTest{
	
//	@Autowired
//	private AdService adService;
	@Autowired
	protected HibernateTemplate template;
	@Autowired
	private AdTaskService adTaskService;
	@Autowired
	private AdTaskManager adTaskManager;
	
	public AdTaskManager getAdTaskManager() {
		return adTaskManager;
	}
	public void setAdTaskManager(AdTaskManager adTaskManager) {
		this.adTaskManager = adTaskManager;
	}
	public AdTaskService getAdTaskService() {
		return adTaskService;
	}
	public void setAdTaskService(AdTaskService adTaskService) {
		this.adTaskService = adTaskService;
	}
	@Test
	public void testClean(){
		adTaskManager.cleanTask();
	}
	@Test
	public void testGenTask(){
		adTaskManager.genTask();
	}
	@Test
	public void testTemplate(){
		AdTask condition = new AdTask();
		condition.setStatus(AdTask.ADTASK_STATUS_PUBLIC);
		List<AdTaskVo> queryList = adTaskService.queryList(condition );
		System.out.println(queryList);
	}
//	@Test
//	public void testAd(){
//		try {
//			AdContent query = adService.query(3L);
//			System.out.println(query);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public AdService getAdService() {
//		return adService;
//	}
//
//	public void setAdService(AdService adService) {
//		this.adService = adService;
//	}
	public HibernateTemplate getTemplate() {
		return template;
	}
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	
}

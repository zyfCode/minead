package com.sungan.ad.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sungan.ad.cmmon.test.BaseTest;

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
	
	@Test
	public void testTemplate(){
		System.out.println(template);
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

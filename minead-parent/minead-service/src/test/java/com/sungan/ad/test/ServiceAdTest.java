package com.sungan.ad.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sungan.ad.cmmon.test.BaseTest;
import com.sungan.ad.commons.AdDateUtil;
import com.sungan.ad.domain.AdClient;
import com.sungan.ad.domain.AdTask;
import com.sungan.ad.service.AdClientService;
import com.sungan.ad.service.AdTaskService;
import com.sungan.ad.service.ext.AdTaskManager;
import com.sungan.ad.vo.AdClientVo;
import com.sungan.ad.vo.AdTaskVo;
import com.sungan.ad.vo.AppTaskVo;

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
	@Autowired
	private AdClientService adClientService;
	
	public AdClientService getAdClientService() {
		return adClientService;
	}
	public void setAdClientService(AdClientService adClientService) {
		this.adClientService = adClientService;
	}
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
	public void testGetTask(){
		try {
			AdClient condition = new AdClient();
			condition.setId(1L);
			List<AdClientVo> query = adClientService.query(condition );
			System.out.println(query);
			AdClientVo adClientVo = query.get(0);
			List<AppTaskVo> task = adTaskManager.getTask(adClientVo);
			System.out.println(task);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testDate3(){
		Date taskRunDate = AdDateUtil.getTaskRunDate(new Date(System.currentTimeMillis()- 60*60*1000));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format2 = format.format(taskRunDate);
		System.out.println(format2);
	}
	@Test
	public void testDate2(){
		Date taskRunDate = AdDateUtil.getTaskRunDate(21);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format2 = format.format(taskRunDate);
		System.out.println(format2);
	}
	public void testDate1(){
		Date setHours = DateUtils.setHours(new Date(), 16);
		setHours = DateUtils.setMinutes(setHours, 0);
		 setHours =  DateUtils.setSeconds(setHours, 0); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format2 = format.format(setHours);
		System.out.println(format2);
	}
	@Test
	public void testDate(){
		 Date date2 = new Date();
		 Calendar calendar = Calendar.getInstance();  
			calendar.setTime(date2);
		int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
		 Date setHours = DateUtils.setHours(date2, currentHour);
		 setHours = DateUtils.setMinutes(setHours, 0);
		 setHours =  DateUtils.setSeconds(setHours, 0); 
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String format2 = format.format(setHours);
		 System.out.println(format2);
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

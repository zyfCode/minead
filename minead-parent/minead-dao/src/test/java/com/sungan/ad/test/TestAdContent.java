package com.sungan.ad.test;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.SessionFactory;
import org.hibernate.validator.HibernateValidatorFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.sungan.ad.cmmon.test.BaseTest;
import com.sungan.ad.dao.base.AdClientDAO;
import com.sungan.ad.dao.base.AdDAO;
import com.sungan.ad.dao.base.AdWeightGroupDAO;
import com.sungan.ad.domain.AdClient;
import com.sungan.ad.domain.AdContent;
import com.sungan.ad.domain.AdWeightGroup;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class TestAdContent extends BaseTest{
	
	
	@Before
	public void init(){
		/**
		 * DAO层测试时，session没绑到当前线程，因此需要自己fcu
		 */
		HibernateTemplate bean = (HibernateTemplate) super.applicationContext.getBean("hibernateTemprate");
		SessionFactory sessionFactory = bean.getSessionFactory();
		TransactionSynchronizationManager.bindResource(sessionFactory, sessionFactory.openSession());
	}

	@Autowired
	private AdDAO adDAO;
	@Autowired
	private AdClientDAO adClientDAO;
	
	@Autowired
	protected HibernateTemplate template;
	
	@Autowired
	private AdWeightGroupDAO adWeightGroupDAO;
	
	@Test
	public void testGroup(){
//		AdWeightGroup group = new AdWeightGroup();
//		group.setGroupName("hello");
//		adWeightGroupDAO.insert(group);
		AdWeightGroup find = adWeightGroupDAO.find(1L);
		System.out.println(find);
	}
	
	
	@Test
	public void testAdClientValid() {
//		Validator validator = Validation.buildDefaultValidatorFactory()  
//                .getValidator(); 
//		ValidatorFactory buildDefaultValidatorFactory = Validation.buildDefaultValidatorFactory();
//		Validator validator = buildDefaultValidatorFactory.getValidator();
//		Set<ConstraintViolation<TestValiBean>> validate = validator.validate(new TestValiBean());
//		System.out.println(validate);
	}
	@Test
	public void testAdClientIp() {
		AdClient adClient = new AdClient();
		adClient.setId(2L);
		List<AdClient> query = (List<AdClient>) adClientDAO. query(adClient);
		System.out.println(query);
	}
	
	public AdClientDAO getAdClientDAO() {
		return adClientDAO;
	}



	public void setAdClientDAO(AdClientDAO adClientDAO) {
		this.adClientDAO = adClientDAO;
	}



	@Test
	public void testTemplate(){
		System.out.println(template);
	}
	@Test
	public void testQuest(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Date setHours = DateUtils.setHours(date, 1);
		setHours = DateUtils.setMinutes(setHours, 0);
		 setHours =  DateUtils.setSeconds(setHours, 0);
		String format2 = format.format(setHours);
		System.out.println(format2);
//		try {
//			Collection<AdContent> query = adDAO.query();
//			System.out.println(query);
////		System.out.println(adDAO);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public AdDAO getAdDAO() {
		return adDAO;
	}

	public void setAdDAO(AdDAO adDAO) {
		this.adDAO = adDAO;
	}


	public AdWeightGroupDAO getAdWeightGroupDAO() {
		return adWeightGroupDAO;
	}


	public void setAdWeightGroupDAO(AdWeightGroupDAO adWeightGroupDAO) {
		this.adWeightGroupDAO = adWeightGroupDAO;
	}
	
	
}

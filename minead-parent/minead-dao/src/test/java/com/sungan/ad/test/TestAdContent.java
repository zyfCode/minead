package com.sungan.ad.test;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.sungan.ad.cmmon.test.BaseTest;
import com.sungan.ad.dao.base.AdDAO;
import com.sungan.ad.domain.AdContent;

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
	protected HibernateTemplate template;
	
	@Test
	public void testTemplate(){
		System.out.println(template);
	}
	@Test
	public void testQuest(){
		try {
			Collection<AdContent> query = adDAO.query();
			System.out.println(query);
//		System.out.println(adDAO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	
}

package com.sungan.ad.dao;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sungan.ad.commons.AdCommonsUtil;

public abstract class AbstractDAO<T> implements DAO<T> {
	private static final Log log = LogFactory.getLog(AbstractDAO.class);
	@Autowired
	protected HibernateTemplate template;
	@SuppressWarnings("rawtypes")
	protected Class currentClass;
	
	public AbstractDAO(){}

	protected AbstractDAO(HibernateTemplate template) {
		this.template = template;
	}

	{
		Type type = this.getClass().getSuperclass().getGenericSuperclass();
		if (type != null && type instanceof ParameterizedType) {
			ParameterizedType c = (ParameterizedType) type;
			Type[] typeArr = c.getActualTypeArguments();
			if (typeArr != null && typeArr.length == 1) {
				Type actualType = typeArr[0];
				if (actualType instanceof Class) {
					this.currentClass = (Class) actualType;
				} else {
					log.error("非class类型：" + actualType);
				}
			}
		}
	}

	public void commit() {
		Session currentSession = template.getSessionFactory()
				.getCurrentSession();
		currentSession.getTransaction().commit();
	}

	public int insert(Collection<T> collection) {
		if (collection == null) {
			throw new NullPointerException();
		}
		int count = 0;
		Session currentSession = template.getSessionFactory()
				.getCurrentSession();
		try {
			for (T t : collection) {
				currentSession.save(t);
				count++;
				if (++count % 40 == 0) {
					currentSession.flush();
					currentSession.clear();
				}
			}
		} finally {
			currentSession.flush();
			currentSession.clear();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public Collection<T> query() {
		StringBuffer buf = new StringBuffer();
		buf.append("from ").append(AbstractDAO.this.currentClass.getName());
		Session 
			currentSession = template.getSessionFactory().getCurrentSession();
		List<T> find  = currentSession.createQuery(buf.toString()).list();
		return find;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public AdPager<T> queryPageEq(T t,int pageIndex,int rows) {
		Session currentSession = this.template.getSessionFactory().getCurrentSession();
		Criteria createCriteria = currentSession.createCriteria(currentClass);//.add(Restrictions.eq("appid", appid)).list();
		try {
			Map<String, Object> beanFile = AdCommonsUtil.getBeanFile(t);
			BeanInfo beanInfo = Introspector.getBeanInfo(currentClass);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor pt:propertyDescriptors){
				String name = pt.getName();
				if(name.equals("class")){
					continue;
				}
				Object object = beanFile.get(name);
				if(object!=null){
					createCriteria = createCriteria.add(Restrictions.eq(name, object));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("",e);
		}
		
		int firstResult = (pageIndex-1)*rows;
		createCriteria.setFirstResult(firstResult).setMaxResults(rows);
		List<T> list = createCriteria.list();
		int count = Integer.valueOf(this.count(t).toString());
		AdPager<T> pager = new AdPager<T>(pageIndex, rows, count);
		pager.setRows(list);
		return pager;
	}
	
	@SuppressWarnings("unchecked")
	public AdPager<T> queryPage(T t,int pageIndex,int rows) {
		Session currentSession = this.template.getSessionFactory().getCurrentSession();
		Criteria createCriteria = currentSession.createCriteria(currentClass);//.add(Restrictions.eq("appid", appid)).list();
		try {
			Map<String, Object> beanFile = AdCommonsUtil.getBeanFile(t);
			BeanInfo beanInfo = Introspector.getBeanInfo(currentClass);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor pt:propertyDescriptors){
				String name = pt.getName();
				if(name.equals("class")){
					continue;
				}
				Object object = beanFile.get(name);
				if(object!=null&&(object instanceof String||object instanceof java.util.Date)){
					createCriteria = createCriteria.add(Restrictions.like(name, object+"%"));
				}else if(object!=null){
					createCriteria = createCriteria.add(Restrictions.eq(name, object));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("",e);
		}
		
		int firstResult = (pageIndex-1)*rows;
		createCriteria.setFirstResult(firstResult).setMaxResults(rows);
		List<T> list = createCriteria.list();
		int count = Integer.valueOf(this.count(t).toString());
		AdPager<T> pager = new AdPager<T>(pageIndex, rows, count);
		pager.setRows(list);
		return pager;
	}
	@SuppressWarnings("unchecked")
	public Collection<T> query(T t,QueryHandler handler) {
		Session currentSession = this.template.getSessionFactory().getCurrentSession();
		Criteria createCriteria = currentSession.createCriteria(currentClass);//.add(Restrictions.eq("appid", appid)).list();
		try {
			Map<String, Object> beanFile = AdCommonsUtil.getBeanFile(t);
			BeanInfo beanInfo = Introspector.getBeanInfo(currentClass);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor pt:propertyDescriptors){
				String name = pt.getName();
				if(name.equals("class")){
					continue;
				}
				Object object = beanFile.get(name);
				if(object!=null){
					createCriteria = createCriteria.add(Restrictions.eq(name, object));
				}
			}
			createCriteria = handler.addCondition(createCriteria);
		} catch (Exception e) {
			throw new RuntimeException("",e);
		}
		List<T> list = createCriteria.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public Collection<T> query(T t) {
		Session currentSession = this.template.getSessionFactory().getCurrentSession();
		Criteria createCriteria = currentSession.createCriteria(currentClass);//.add(Restrictions.eq("appid", appid)).list();
		try {
			Map<String, Object> beanFile = AdCommonsUtil.getBeanFile(t);
			BeanInfo beanInfo = Introspector.getBeanInfo(currentClass);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor pt:propertyDescriptors){
				String name = pt.getName();
				if(name.equals("class")){
					continue;
				}
				Object object = beanFile.get(name);
				if(object!=null){
					createCriteria = createCriteria.add(Restrictions.eq(name, object));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("",e);
		}
		List<T> list = createCriteria.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public Long count(T t) {
		Session currentSession = this.template.getSessionFactory().getCurrentSession();
		Criteria createCriteria = currentSession.createCriteria(currentClass);//.add(Restrictions.eq("appid", appid)).list();
		try {
			Map<String, Object> beanFile = AdCommonsUtil.getBeanFile(t);
			BeanInfo beanInfo = Introspector.getBeanInfo(currentClass);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor pt:propertyDescriptors){
				String name = pt.getName();
				if(name.equals("class")){
					continue;
				}
				Object object = beanFile.get(name);
				if(object!=null){
					createCriteria = createCriteria.add(Restrictions.eq(name, object));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("",e);
		}
		  Long uniqueResult = (Long) createCriteria.setProjection(Projections.rowCount()).uniqueResult();
		return uniqueResult; 
	}

	@SuppressWarnings("unchecked")
	public long count() {
		String hql = "select count(*) from " + this.currentClass.getName();
		List<Long> count = template.find(hql);
		if (count != null && count.size() > 0) {
			return count.get(0);
		}
		return 0;
	}

	public Serializable insert(T t) {
		Session  currentSession = template.getSessionFactory().getCurrentSession();
		Serializable  save = currentSession.save(t);
		return save;
	}

	public void delete(T t) {
		Session currentSession = template.getSessionFactory().getCurrentSession();
		 currentSession.delete(t);
	}

	public void delete(Collection<T> collection) {
		Session currentSession = template.getSessionFactory().getCurrentSession();
		for (T t : collection) {
			currentSession.delete(t);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<T> query(final int beginIndex, final int num,
			String orderclum, OrderType orderType) {
		Session  currentSession = template.getSessionFactory().getCurrentSession();
		Criteria setMaxResults = currentSession.createCriteria(AbstractDAO.this.currentClass).setFirstResult(beginIndex).setMaxResults(num);
		if(StringUtils.isNotBlank(orderclum)){
			if(orderType!=null){
				if(orderType==OrderType.DESC){
					setMaxResults.addOrder(Order.desc(orderclum));
				}else{
					setMaxResults.addOrder(Order.asc(orderclum));
				}
			}else{
				setMaxResults.addOrder(Order.asc(orderclum));
			}
		}
		List<T> find = setMaxResults.list();
		return find;
	}

	public void update(T t) {
		Session  currentSession = template.getSessionFactory().getCurrentSession();
		 currentSession.update(t);
	}

	@SuppressWarnings("unchecked")
	public T find(Serializable id) {
		Session currentSession  = template.getSessionFactory().getCurrentSession();
		T  t = (T) currentSession.get(currentClass, id);
		return t;
	}

//	@Override
	@SuppressWarnings("unchecked")
	public T findByLoad(Serializable id) {
		Session currentSession  = template.getSessionFactory().getCurrentSession();
		T  t = (T) currentSession.load(currentClass, id);
		return t;
	}

	
}

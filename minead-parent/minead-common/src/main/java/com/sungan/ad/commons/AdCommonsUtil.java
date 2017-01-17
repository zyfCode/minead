package com.sungan.ad.commons;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 说明:
 * 
 */
public class AdCommonsUtil {
	private AdCommonsUtil(){}
	/**
	 * 获取属性值
	 * @param sourceBean 将sourceBean中的属性复制到targetBean
	 * @param targetBean
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> getBeanFile(Object sourceBean) throws Exception{
		BeanInfo sourceBeanInfo = Introspector.getBeanInfo(sourceBean.getClass());
		
		PropertyDescriptor[] propertyDescriptors = sourceBeanInfo.getPropertyDescriptors(); 
		Map<String,Object> map = new LinkedHashMap<String, Object>();
		for (PropertyDescriptor pro : propertyDescriptors) {
			String name = pro.getName();
			Method readMethod = pro.getReadMethod();
			Object invoke = readMethod.invoke(sourceBean,null);
			map.put(name, invoke);
		}
		return map;
	}
	/**
	 * bean的发属性复制，只复制类型相同的属性。
	 * @param sourceBean 将sourceBean中的属性复制到targetBean
	 * @param targetBean
	 * @return
	 * @throws Exception
	 */
	public static Object beanCopy(Object sourceBean,Object targetBean) throws Exception{
		BeanInfo targetBeanInfo = Introspector.getBeanInfo(targetBean.getClass());
		BeanInfo sourceBeanInfo = Introspector.getBeanInfo(sourceBean.getClass());
		
		PropertyDescriptor[] propertyDescriptors = sourceBeanInfo.getPropertyDescriptors(); 
		Map<String,Object[]> pro_Method = new LinkedHashMap<String,Object[]>();
		for (PropertyDescriptor pro : propertyDescriptors) {
			String name = pro.getName();
			Method readMethod = pro.getReadMethod();
			Object invoke = readMethod.invoke(sourceBean,null);
			Class<?> typeType = pro.getPropertyType();
			Object [] objArr = new Object[]{invoke,typeType};
			pro_Method.put(name, objArr);
		}
		//获取所有的参数
		PropertyDescriptor[] proArr = targetBeanInfo.getPropertyDescriptors();
		for (PropertyDescriptor pro : proArr) {
			String name = pro.getName();
			//如果属性不存在，或是属性的类型不同，不进行任务处理。
			if(!pro_Method.containsKey(name)){
				continue;
			}
		    Object[] objects = pro_Method.get(name);
		    Object value = objects[0];
		    Object tyepe = objects[1];
		    if(pro.getPropertyType()!=tyepe){
		    	continue;
		    }
		    
			Method method = pro.getWriteMethod();
			if (method != null) {
				Class<?>[] parType = method.getParameterTypes();
				if (parType != null) {
					if (parType.length > 1) {
						throw new Exception(
								"Parameter of WriteMethod expected 1 Parameter,but actually is "
										+ parType.length);
					}
					for (Class cc : parType) {
						//如果属性是数组，直接将参数值[数组类型]赋值给此参数.
						boolean array = cc.isArray();
						if (array) {
							method.invoke(targetBean, new Object[] { value });
						} else {
							method.invoke(targetBean, value);
						}
					}
				}
			}
		}
		return targetBean;
	}
	
}

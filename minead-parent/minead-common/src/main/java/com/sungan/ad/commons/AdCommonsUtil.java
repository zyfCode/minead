package com.sungan.ad.commons;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 说明:
 * 
 */
public class AdCommonsUtil {
	private AdCommonsUtil(){}
	
	public static<T> T proStrEmpytToNull(T t){
		if(t==null){
			return null;
		}
		try {
			BeanInfo sourceBeanInfo = Introspector.getBeanInfo(t.getClass());
			PropertyDescriptor[] propertyDescriptors = sourceBeanInfo.getPropertyDescriptors();
			for(PropertyDescriptor pro:propertyDescriptors){
				Method readMethod = pro.getReadMethod();
				Class<?> returnType = readMethod.getReturnType();
				if(returnType.equals(String.class)){
					String value = (String) readMethod.invoke(t);
					if(value==null||value.trim().equals("")){
						Method writeMethod = pro.getWriteMethod();
						writeMethod.invoke(t, new Object[]{null});
					}
				}
			}
			return t;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
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
	public static void beanCopyWithoutNull(Object sourceBean,Object targetBean) {
		try {
			BeanInfo targetBeanInfo = Introspector.getBeanInfo(targetBean.getClass());
			Map<String, Object> valueMap = getBeanFile(sourceBean);
			//获取所有的参数
			PropertyDescriptor[] proArr = targetBeanInfo.getPropertyDescriptors();
			for (PropertyDescriptor pro : proArr) {
			    Object value = valueMap.get(pro.getName());
			    if(value==null||pro.getName().equalsIgnoreCase("class")){
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
		} catch (Exception e) {
			throw new RuntimeException("",e);
		}
	}
	
}

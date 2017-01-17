package com.sungan.ad.expand.common.annotation.parser;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2017年1月16日 上午8:44:07
 * @version V1.1
 */
public class AnnotationParser {
	private static final Log log = LogFactory.getLog(AnnotationParser.class);
	private AnnotationParser(){}
	
	/**
	 * 将JAVABean转化成VO
	 * @param t
	 * @param sourece
	 * @return
	 */
	public static <T> T parseToVo(Class<T> t,Object sourece){
		try {
			T newInstance = t.newInstance(); 
			BeanUtils.copyProperties(sourece, newInstance);
			Field[] declaredFields = sourece.getClass().getDeclaredFields();
			for(Field f:declaredFields){
				f.setAccessible(true);;
				Annotation[] declaredAnnotations = f.getDeclaredAnnotations();
				for(Annotation at:declaredAnnotations){
					String name = f.getName();
					Object object = f.get(sourece);
					if(at instanceof DateToStr){
						if(!(object instanceof java.util.Date)){
							throw new RuntimeException("注解"+at.getClass()+"必须用在java.util.Date上");
						}
						Date date = (Date) object;
						DateToStr dateToStr = (DateToStr) at;
						String value = dateToStr.value();
						SimpleDateFormat fortmat  = new SimpleDateFormat(value);
						String sourceVal = fortmat.format(date);
						String fileName = name+"Str";
						Field targetFile = newInstance.getClass().getDeclaredField(fileName);
						if(targetFile==null){
//							throw new RuntimeException(t.getName()+"类型中不存在"+fileName+"属性");
							log.warn(t.getName()+"类型中不存在"+fileName+"属性");
						}
						targetFile.setAccessible(true);
						targetFile.set(newInstance, sourceVal);
					}else if(at instanceof StatusCn){
						StatusCn statuscn = (StatusCn)at;
						String[] value = statuscn.value();
						Map<String,String> entryMap = new LinkedHashMap<String,String>();
						for(String str:value){
							if(!str.contains("=")){
								throw new RuntimeException("非法属性"+str+",期望属性是key=value");
							}
							String[] split = str.split("=");
							entryMap.put(split[0], split[1]);
						}
						String statusFileCn = name+"Cn";
						Field targetFile = newInstance.getClass().getDeclaredField(statusFileCn);
						String valuCn = entryMap.get(object);
						targetFile.setAccessible(true);
						targetFile.set(newInstance, valuCn);
					}
				}
				
			}
			return newInstance;
		} catch (Exception e) {
			throw new RuntimeException("",e);
		}
	} 

}

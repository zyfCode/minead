package com.sungan.ad.expand.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;



/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2017年1月16日 上午8:36:29
 * @version V1.1
 */
@Target(ElementType.TYPE)
public @interface DateToStr {
	public String value() default "";
}

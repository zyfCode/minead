package com.sungan.ad.client.apploader;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sungan.ad.expand.common.TaskApp;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2017年1月12日 下午11:13:56
 * @version V1.1
 */
public class BaseAppLoader {
	private URLClassLoader classLoder=null;
	private List<URL> list;
	
	public BaseAppLoader(List<URL> list){
		this.list = list;
	}
	
	public TaskApp getApp(String name){
		try {
			if(classLoder==null){
				URL[] array = list.toArray(new URL[list.size()]);
				classLoder = new URLClassLoader(array);
			}
			Class<TaskApp> loadClass = (Class<TaskApp>) classLoder.loadClass(name);
			TaskApp newInstance = loadClass.newInstance();
			return newInstance;
		} catch (Exception e) {
			throw new RuntimeException("",e);
		}
	}
	
}

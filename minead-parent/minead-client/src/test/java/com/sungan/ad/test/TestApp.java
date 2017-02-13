package com.sungan.ad.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sungan.ad.client.apploader.BaseAppLoader;
import com.sungan.ad.client.connector.AppManager;
import com.sungan.ad.cmmon.test.BaseTest;
import com.sungan.ad.expand.common.TaskApp;
import com.sungan.ad.expand.common.bean.TaskResonse;

import net.sf.json.JSONObject;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2017年1月12日 下午11:33:27
 * @version V1.1
 */
public class TestApp extends BaseTest{
	
	@Autowired
	private AppManager appManager;
	
	public AppManager getAppManager() {
		return appManager;
	}

	public void setAppManager(AppManager appManager) {
		this.appManager = appManager;
	}
	
	@Test
	public void test() {
		appManager.heart();
		appManager.heart();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testJson(){
		String json = "{\"resInfos\":[{\"serialNo\":\"2017021023002124717\",\"count\":3826,\"adClazzName\":\"com.sungan.ad.expand.impl.DaillyTaskApp\",\"adTaskId\":145,\"doneCount\":0,\"throwRate\":8,\"adClientId\":1,\"ip\":\"127.0.0.1\"}]}";
		JSONObject fromObject = JSONObject.fromObject(json);
		TaskResonse bean = (TaskResonse) JSONObject.toBean(fromObject, TaskResonse.class);
		System.out.println(bean.getResInfos()[0].getIp());
	
	}

	@Test
	public void testAA() throws MalformedURLException{
		List<URL> list = new ArrayList<URL>();
		File file = new File("testlib/minead-expand-impl-0.0.1-SNAPSHOT.jar");
		URL url = file.toURI().toURL();
		list.add(url);
		BaseAppLoader loder = new BaseAppLoader(list );
		TaskApp app = loder.getApp("com.sungan.ad.expand.impl.EveryTimeTaskWorker");
		app.init(null);
		app.work();
		app.destory();
	}
}

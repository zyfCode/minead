package com.sungan.ad.client.connector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sungan.ad.client.apploader.BaseAppLoader;
import com.sungan.ad.expand.common.TaskApp;
import com.sungan.ad.expand.common.bean.InitTaskConnectRequest;
import com.sungan.ad.expand.common.bean.InitTaskConnectResponse;
import com.sungan.ad.expand.common.bean.TaskInfo;
import com.sungan.ad.expand.common.bean.TaskRequest;
import com.sungan.ad.expand.common.bean.TaskResonse;
import com.sungan.ad.expand.common.bean.TaskResonseInfo;

/**
 * 说明:
 * @version V1.1
 */
public class AppManager {
	/**
	 * key 值 是taskId
	 */
	private static final ConcurrentHashMap<Long,TaskApp> TASKINFO = new ConcurrentHashMap<Long,TaskApp>();
	private  boolean isInit = false;
	private Long adClientId;
	private String adClientMac;
	private String adClientIp;
	private String userName;
	private String pwd;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void init(){
		for(Map.Entry<Long,TaskApp> entry:TASKINFO.entrySet()){
			entry.getValue().destory();
		}
		TASKINFO.clear();
		
		ServerConnector connector = new ServerConnector();
		InitTaskConnectRequest request = new InitTaskConnectRequest();
		String mac = connector.getMac();
		request.setMac(mac);
		request.setUserName(this.userName);
		request.setPwd(this.getPwd());
		request.setSysOs(System.getProperty("os.name"));
		InitTaskConnectResponse connectorInit = connector.connectorInit(request);
		if(connectorInit!=null){
			this.isInit = true;
		}
		Long adClientId = connectorInit.getAdClientId();
		String adClientIp = connectorInit.getAdClientIp();
		this.setAdClientId(adClientId);
		this.setAdClientMac(adClientIp);
		this.setAdClientIp(adClientIp);
	}
	private static final Log log = LogFactory.getLog(AppManager.class);
	public void heart(){
		try {
			if(!this.isInit){
				this.init();
			}
			ServerConnector connector = new ServerConnector();
			TaskRequest request = new TaskRequest();
			request.setAdClientId(this.getAdClientId());
			request.setAdClientIp(this.getAdClientIp());
			request.setMac(this.getAdClientMac());
			request.setSerialNo(this.getSerialNo());
			
			Set<Entry<Long, TaskApp>> entrySet = TASKINFO.entrySet();
			List<TaskInfo> infoList = new ArrayList<TaskInfo>();
			for(Map.Entry<Long, TaskApp> entry:entrySet){
				TaskApp task = entry.getValue();
				TaskInfo info = task.getTaskInfo();
				infoList.add(info);
			}
			request.setInfo(infoList.toArray(new TaskInfo[infoList.size()]));
			TaskResonse taskResponse = connector.taskHeart(request );
			if(TaskResonse.TR_INIT.equals(taskResponse.getAction())){
				this.init();
			}
			 TaskResonseInfo[] resInfos = taskResponse.getResInfos();
			if(resInfos!=null){
				for(TaskResonseInfo info:resInfos){
					TaskApp taskApp = TASKINFO.get(info.getAppTaskId());
					if(taskApp==null){
						BaseAppLoader loader  = connector.getLoader();
						TaskApp app = loader.getApp(info.getAdClazzName());
						app.init(info);
						app.work();
						TASKINFO.put(info.getAppTaskId(), app);
					}else{
						if(TaskResonse.TR_DESDORY.equals(info.getAction())){
							taskApp.destory();
							TASKINFO.remove(info.getAppTaskId());
						}
					}
				}
			}
		} catch (Exception e) {
			log.info("",e);
		}
	}	

	private String getSerialNo(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Random ranDom = new Random();
		int nextInt = ranDom.nextInt(90)+10;
		String serialNo = format.format(new Date())+nextInt;
		return serialNo;
	}
	
	
	public boolean isInit() {
		return isInit;
	}

	public void setInit(boolean isInit) {
		this.isInit = isInit;
	}

	public Long getAdClientId() {
		return adClientId;
	}

	public void setAdClientId(Long adClientId) {
		this.adClientId = adClientId;
	}

	public String getAdClientMac() {
		return adClientMac;
	}

	public void setAdClientMac(String adClientMac) {
		this.adClientMac = adClientMac;
	}

	public String getAdClientIp() {
		return adClientIp;
	}

	public void setAdClientIp(String adClientIp) {
		this.adClientIp = adClientIp;
	}
}










package com.sungan.ad.service.cloud;

import java.util.List;

/**
 * 说明:
 * @version V1.1
 */
public interface CloudIpManager {
	
	/**
	 * 获取ip列表 
	 * @param userName
	 * @param pwd
	 * @return
	 */
	List<String> getIp(String userName,String pwd);
	
	/**
	 * 改变ip
	 * @param clientId
	 * @param targetIp
	 * @return
	 */
	boolean changeIp(Long clientId,String targetIp);
}

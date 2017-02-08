package com.sungan.ad.service.cloud;

import java.util.List;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class WYCloudIpManager implements CloudIpManager{

	@Override
	public List<String> getIp(String userName, String pwd) {
		return null;
	}

	@Override
	public boolean changeIp(Long clientId, String targetIp) {
		return false;
	}

}

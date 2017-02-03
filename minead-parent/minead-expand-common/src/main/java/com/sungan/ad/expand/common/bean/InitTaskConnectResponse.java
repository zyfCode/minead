package com.sungan.ad.expand.common.bean;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class InitTaskConnectResponse {
	private Long adClientId;
	private String adClientIp;
	private String adClientMac;
	

	public String getAdClientMac() {
		return adClientMac;
	}

	public void setAdClientMac(String adClientMac) {
		this.adClientMac = adClientMac;
	}

	public Long getAdClientId() {
		return adClientId;
	}

	public void setAdClientId(Long adClientId) {
		this.adClientId = adClientId;
	}

	public String getAdClientIp() {
		return adClientIp;
	}

	public void setAdClientIp(String adClientIp) {
		this.adClientIp = adClientIp;
	}
}

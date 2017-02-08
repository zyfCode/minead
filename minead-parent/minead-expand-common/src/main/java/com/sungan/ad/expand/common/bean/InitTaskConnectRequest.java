package com.sungan.ad.expand.common.bean;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class InitTaskConnectRequest {
	public static final String CLIENT_ALI = "ALI";
	public static final String CLIENT_163 = "163";
	public static final String CLIENT_BAIDU ="BAIDU";
	private String mac;
	private String userName;
	private String pwd;
	/**
	 * 支服务商。啊里去、网易蜂巢
	 */
	private String source;
	private String sysOs;

	
	public String getSysOs() {
		return sysOs;
	}

	public void setSysOs(String sysOs) {
		this.sysOs = sysOs;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

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

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
}

package com.sungan.ad.client.tools.main;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class LinuxHost implements Comparable<LinuxHost>{
	private String instanceName;
	private String host;
	private String userName;
	private String pwd;
	private String publickey;
	private String port ="22";

	@Override
	public int compareTo(LinuxHost o) {
		return this.getInstanceName().compareTo(o.getInstanceName());
	}
	
	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
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

	public String getPublickey() {
		return publickey;
	}

	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}

}

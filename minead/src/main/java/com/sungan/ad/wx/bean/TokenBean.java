package com.sungan.ad.wx.bean;
/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2016年12月27日 下午9:50:20
 * @version V1.1
 */
public class TokenBean {
	private String appId;
	private String token;
	private String secret;
	private String url;
	
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getToken() {
		return token;
	}
	public String getUrl() {
		return url;
	}
}

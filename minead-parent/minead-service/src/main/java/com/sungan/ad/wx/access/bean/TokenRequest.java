package com.sungan.ad.wx.access.bean;
/**
 * 说明:
 * 
 * @date 2016年12月27日 下午11:34:16
 * @version V1.1
 */
public class TokenRequest extends WXGetRequest{
	private String url = "https://api.weixin.qq.com/cgi-bin/token";
	private String grantType="client_credential";
	private String appid;
	private String secret;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	@Override
	public String formatGetUrl() {
		String parm = "grant_type="+grantType+"&appid="+appid+"&secret="+secret;
		return this.url+"?"+parm;
	}
	
}

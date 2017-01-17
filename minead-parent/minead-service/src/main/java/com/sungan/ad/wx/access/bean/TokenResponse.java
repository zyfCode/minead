package com.sungan.ad.wx.access.bean;

import java.io.Serializable;

/**
 * 说明:
 * 
 * @date 2016年12月27日 下午11:30:28
 * @version V1.1
 */
public class TokenResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6253552836262967685L;
	//正常情况
	private String access_token;
	private String expires_in;
	//失败情况
	private String errcode;
	private String errmsg;
	
	

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

}

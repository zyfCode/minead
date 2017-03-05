package com.sungan.ad.commons;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class AdResponse {
	public Boolean success = true;
	private String errorMesg;
	private String errorCode;


	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getErrorMesg() {
		return errorMesg;
	}

	public void setErrorMesg(String errorMesg) {
		this.errorMesg = errorMesg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}

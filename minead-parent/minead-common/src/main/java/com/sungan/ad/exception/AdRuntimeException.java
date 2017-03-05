package com.sungan.ad.exception;
/**
 * 说明:
 * 
 * @date 2016年12月28日 下午11:10:52
 * @version V1.1
 */
public class AdRuntimeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	
	

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public AdRuntimeException() {
		super();
	}

	public AdRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
	public AdRuntimeException(String errorCode,String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public AdRuntimeException(String message) {
		super(message);
	}

	public AdRuntimeException(Throwable cause) {
		super(cause);
	}
	

}

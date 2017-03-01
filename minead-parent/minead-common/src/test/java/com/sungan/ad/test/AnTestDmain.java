package com.sungan.ad.test;

import java.util.Date;

import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

/**
 * 说明:
 */
public class AnTestDmain {
	@DateToStr("yyyy-MM-dd HH:mm:ss")
	private Date date;
	@StatusCn(value={ "1=你好", "2=不谢" })
	private String status;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AnTestDmain [date=" + date + ", status=" + status + "]";
	}
	
	
}

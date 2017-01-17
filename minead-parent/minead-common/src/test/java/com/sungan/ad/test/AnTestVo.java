package com.sungan.ad.test;

import java.util.Date;

import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

/**
 * 说明:
 */
public class AnTestVo {
	private Date date;
	private String dateStr;
	private String status;
	private String statusCn;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getStatusCn() {
		return statusCn;
	}

	public void setStatusCn(String statusCn) {
		this.statusCn = statusCn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AnTestVo [date=" + date + ", dateStr=" + dateStr + ", status=" + status + ", statusCn=" + statusCn
				+ "]";
	}

}

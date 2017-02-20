package com.sungan.ad.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 说明:
 * @version V1.1
 */
public class AdDateUtil {
	private AdDateUtil(){}
	
	
	public static Date getTaskRunDate(Date date){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
			String dateStr = format.format(date);
			Date parse = format.parse(dateStr);
			return parse;
		} catch (ParseException e) {
			throw new RuntimeException("",e);
		}
	}
	
	public static Date getTaskRunDate(int hourOfDate){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String dateStr = format.format(new Date());
			String hourStr = null;
			if(hourOfDate<10){
				hourStr = "0"+hourOfDate;
			}else{
				hourStr = hourOfDate+"";
			}
			dateStr = dateStr+hourStr;
			format = new SimpleDateFormat("yyyyMMddHH");
			Date parse = format.parse(dateStr);
			return parse;
		} catch (ParseException e) {
			throw new RuntimeException("",e);
		}
	}
	public static Date getTaskRunDate(){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
			String dateStr = format.format(new Date());
			Date parse = format.parse(dateStr);
			return parse;
		} catch (ParseException e) {
			throw new RuntimeException("",e);
		}
	}

}

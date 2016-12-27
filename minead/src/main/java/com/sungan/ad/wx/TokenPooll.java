package com.sungan.ad.wx;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sungan.ad.wx.bean.TokenBean;

/**
 * 说明:
 * 
 * @date 2016年12月27日 下午9:53:00
 * @version V1.1
 */
public class TokenPooll {
	private static List<WxWorker> pool = new ArrayList<WxWorker>();
	private static final Log log = LogFactory.getLog(TokenPooll.class);
	
	public static void add(WxWorker wxorker){
		try {
			wxorker.refleshToken();
		} catch (Exception e) {
			log.error("", e);
		}
		pool.add(wxorker);
	}
	
	public static TokenBean getAppId(String appId){
		for(WxWorker wx:pool){
			TokenBean tokenBean = wx.getTokenBean();
			if(tokenBean.getAppId().equals(appId)){
				return tokenBean;
			}
		}
		return null;
	}
	
	public void reflsh(){
		if(log.isInfoEnabled()){
			log.info("更新token任务开始...");
		}
		for(WxWorker wx:pool){
			try {
				wx.refleshToken();
			} catch (Exception e) {
				log.error("",e);
			}
		}
		if(log.isInfoEnabled()){
			log.info("更新token任务完成...");
		}
	}
}

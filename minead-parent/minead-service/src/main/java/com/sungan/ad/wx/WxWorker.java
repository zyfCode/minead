package com.sungan.ad.wx;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.sungan.ad.wx.access.WxDataConnector;
import com.sungan.ad.wx.access.bean.TokenRequest;
import com.sungan.ad.wx.access.bean.TokenResponse;
import com.sungan.ad.wx.bean.TokenBean;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2016年12月27日 下午9:48:04
 * @version V1.1
 */
public class WxWorker implements InitializingBean{
	private TokenBean tokenBean;
	private static Log log = LogFactory.getLog(WxWorker.class);
	private WxDataConnector wxDataConnector;
	
	public WxDataConnector getWxDataConnector() {
		return wxDataConnector;
	}
	public void setWxDataConnector(WxDataConnector wxDataConnector) {
		this.wxDataConnector = wxDataConnector;
	}
	public void refleshToken(){
		if(log.isInfoEnabled()){
			log.info(tokenBean.getAppId()+" 刷新");
		}
		TokenRequest request = new TokenRequest();
		request.setAppid(this.getTokenBean().getAppId());
		request.setSecret(this.getTokenBean().getSecret());
		TokenResponse token = wxDataConnector.getToken(request );
		if(StringUtils.isBlank(token.getErrcode())&&StringUtils.isNotBlank(token.getAccess_token())){
			this.getTokenBean().setToken(token.getAccess_token());
		}
	}
	public TokenBean getTokenBean() {
		return tokenBean;
	}

	public void setTokenBean(TokenBean tokenBean) {
		this.tokenBean = tokenBean;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		TokenPooll.add(this);
	}
}

package com.sungan.ad.wx.access.impl;

import org.springframework.stereotype.Component;

import com.sungan.ad.wx.access.WxDataConnectorAbstract;
import com.sungan.ad.wx.access.bean.TokenRequest;
import com.sungan.ad.wx.access.bean.TokenResponse;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2016年12月27日 下午10:39:18
 * @version V1.1
 */
@Component("wxDataConnector")
public class WxDataConnectorImpl extends WxDataConnectorAbstract{

	@Override
	public TokenResponse getToken(TokenRequest request) {
		String formatGetUrl = request.formatGetUrl();
		TokenResponse dataFromWx = super.getDataFromWx(formatGetUrl, TokenResponse.class);
		return dataFromWx;
	}
}

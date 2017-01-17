package com.sungan.ad.wx.access;

import com.sungan.ad.wx.access.bean.TokenRequest;
import com.sungan.ad.wx.access.bean.TokenResponse;

/**
 * 说明:
 * 
 * @date 2016年12月27日 下午10:35:39
 * @version V1.1
 */
public interface WxDataConnector {
	
	TokenResponse getToken(TokenRequest request);
}

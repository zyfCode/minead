package com.sungan.ad.wx.access;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;


/**
 * 说明:
 * 
 * @date 2016年12月27日 下午10:38:19
 * @version V1.1
 */
public abstract class WxDataConnectorAbstract implements WxDataConnector {
	
	private static final Log log = LogFactory.getLog(WxDataConnectorAbstract.class);

	
	/**
	 *  用全路径名(如http://localhost/xx)连接服务器，获取设备XML信息
	 * @param fullUrl 全路经名 如http://localhost/xx
	 * @return String
	 * @throws Exception
	 */ 
	public <T>T getDataFromWx(String fullUrl,Class<T> clazz) {
//		if(StringUtils.isBlank(fullUrl)||!fullUrl.matches("^http\\:\\/\\/.+\\/.*")){
//			throw new RuntimeException();
//		}
		DefaultHttpClient client = new DefaultHttpClient();
        String result = "";
        HttpGet get = null;
        HttpResponse response = null;
        try {
			get = new HttpGet(fullUrl);
			get.addHeader("accept", "application/json");
			response = client.execute(get);
			result = "";
			log.info(fullUrl+" 响应为:"+response.getStatusLine().getStatusCode());
			if (null != response.getEntity()
					&& HttpStatus.SC_OK == response.getStatusLine()
							.getStatusCode()) {
				result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
				if(log.isDebugEnabled()){
					log.debug("响应的报文件:"+result);
				}
				JSONObject fromObject = net.sf.json.JSONObject.fromObject(result);
				T bean = (T) JSONObject.toBean(fromObject,clazz);
				return bean;
			}else{
				throw new RuntimeException(fullUrl+" "+response.getStatusLine().getStatusCode()+"获取数据异常");
			}
		} catch(Exception e){
			String mesge = fullUrl+" ";
			if(response!=null){
				mesge += response.getStatusLine().getStatusCode();
			}
			log.error(mesge+" 错误信息："+e.getMessage());
			throw new RuntimeException(mesge,e);
		}finally {
			if(get!=null)get.abort();
			client.getConnectionManager().shutdown();
		}
	}
	
}

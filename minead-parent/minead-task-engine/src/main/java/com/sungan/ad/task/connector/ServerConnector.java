package com.sungan.ad.task.connector;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.http.conn.util.InetAddressUtils;

import com.sungan.ad.expand.common.bean.InitConnectResponse;
import com.sungan.ad.expand.common.bean.InitTaskConnectRequest;
import com.sungan.ad.expand.common.bean.InitTaskConnectResponse;

import net.sf.json.JSONObject;

/**
 * 说明:
 * @version V1.1
 */
public class ServerConnector {
	private static final String baseIp = "139.196.240.242";
	private static final String initUrl = "/client/init";
	private static final String conntorInitUrl = "/client/initconnector";
	private static final String  infoUrl = "/client/info";
	
	public  String getUrl(String url){
		return "http://"+baseIp+url;
	}
	
	/**
	 * 获取内网ip
	 * @return
	 */
	public InitConnectResponse init(){
		String doPost = HttpClientUtil.doPost(this.getUrl(initUrl), null);
		if(doPost!=null){
			JSONObject fromObject = JSONObject.fromObject(doPost);
			  InitConnectResponse bean = (InitConnectResponse) JSONObject.toBean(fromObject,InitConnectResponse.class);
			  return bean;
		} 
		return null;
	}
	
	public String getMac(){
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			Set<String> macs = new LinkedHashSet<String>();
			while(networkInterfaces.hasMoreElements()){
				NetworkInterface nextElement = networkInterfaces.nextElement();
				Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
				byte[] mac = nextElement.getHardwareAddress();
				if(mac==null){
					continue;
				}
				StringBuffer macStr = new StringBuffer();
				for(int i=0; i<mac.length; i++) {
					if(i!=0) {
						macStr.append("-");
					}
					//字节转换为整数
					int temp = mac[i]&0xff;
					String str = Integer.toHexString(temp);
					if(str.length()==1) {
						macStr.append("0"+str);
					}else {
						macStr.append(str);
					}
				}
				while(inetAddresses.hasMoreElements()){
					InetAddress nextElement2 = inetAddresses.nextElement();
					String hostAddress = nextElement2.getHostAddress();
					boolean isIPv4Address = InetAddressUtils.isIPv4Address(hostAddress);
					if(isIPv4Address){
						macs.add(macStr.toString());
					}
				}
			}
			StringBuffer buf = new StringBuffer();
			for(String m:macs){
				if(buf.length()<1){
					buf.append(m);
				}else{
					buf.append(",").append(m);
				}
			}
			return buf.toString();
		} catch (SocketException e) {
			throw new RuntimeException();
		}
		
	}
	
	public InitTaskConnectResponse connectorInit(){
		InitTaskConnectRequest request = new InitTaskConnectRequest();
		String mac = this.getMac();
		request.setMac(mac);
		String doPost = HttpClientUtil.doPost(conntorInitUrl, request);
		if(doPost!=null){
			JSONObject fromObject = JSONObject.fromObject(doPost);
			InitTaskConnectResponse bean = (InitTaskConnectResponse) JSONObject.toBean(fromObject, InitTaskConnectResponse.class);
			return bean;
		}
		return null;
	}
	
	
}

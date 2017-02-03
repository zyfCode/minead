package com.sungan.ad.expand.common.bean;
/**
 * 说明:
 * @version V1.1
 */
public class InitConnectResponse {
	//内网ip
	private String ignoresIps;
	/**
	 * 公网出口ip
	 */
	private String pubNetIp;

	public String getIgnoresIps() {
		return ignoresIps;
	}
	public void setIgnoresIps(String ignoresIps) {
		this.ignoresIps = ignoresIps;
	}
	
	public String getPubNetIp() {
		return pubNetIp;
	}
	public void setPubNetIp(String pubNetIp) {
		this.pubNetIp = pubNetIp;
	}
	public boolean match(String ip){
		if(this.getIgnoresIps()!=null){
			if(this.getIgnoresIps().contains(",")){
				String[] split = this.getIgnoresIps().split(",");
				for(String ipprex:split){
					if(ip.contains(ipprex)){
						return true;
					}
				}
			}else{
				if(ip.contains(this.getIgnoresIps())){
					return true;
				}
			}
		}else{
			if(ip.startsWith("10.")){
				return true;
			}
		}
		return false;
	}
}

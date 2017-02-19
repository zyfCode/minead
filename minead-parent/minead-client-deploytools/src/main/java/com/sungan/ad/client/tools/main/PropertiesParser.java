package com.sungan.ad.client.tools.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.sungan.ad.client.tools.commons.CommonsUtil;


/**
 * 说明:
 * @version V1.1
 */
public class PropertiesParser {
	private File conFile;
	
	
	public PropertiesParser(File conFile) {
		super();
		this.conFile = conFile;
	}



	public List<LinuxHost> getLinux(){
		if(!conFile.exists()){
			throw new RuntimeException(this.conFile.getAbsolutePath()+"文件不存在");
		}
		File[] listFiles = null;
		if(conFile.isFile()){
			listFiles = new File[]{conFile};
		}else{
			listFiles = conFile.listFiles();
		}
	 
		List<LinuxHost> result = new ArrayList<LinuxHost>();
		for(File file:listFiles){
			if(file.exists()&&file.isFile()&&file.getName().endsWith(".properties")){
				System.out.println("解析文件:"+file.getAbsolutePath());
				Properties linuxPro = this.getLinuxPro(file);
				//获取公钥路径,一个配置文件中如果没有给*.publickey,统一用common.publickey
				String cmmonPublick = linuxPro.getProperty("common.publickey");
				Set<Object> keySet = linuxPro.keySet();
				
				List<Object> keyHost = new ArrayList<Object>();
				for(Object k:keySet){
					if(!k.toString().contains(".")){
						keyHost.add(k);
					}
				}
				for(Object instancename:keyHost){ 
					String host = linuxPro.getProperty(instancename.toString());
					this.valid(instancename.toString(), linuxPro);
					String port =linuxPro.getProperty(instancename+".port");
					if(CommonsUtil.isBlank(port)){
						port = "22";
					}
					String username = linuxPro.getProperty(instancename+".username");
					String pwd = linuxPro.getProperty(instancename+".pwd");
					String publickey = linuxPro.getProperty(instancename+".publickey");
					if(CommonsUtil.isBlank(publickey)){
						publickey = cmmonPublick;
					}
					if(CommonsUtil.isBlank(publickey)&&CommonsUtil.isBlank(pwd)){
						throw new RuntimeException("请设置密码或者公钥.");
					}
					LinuxHost linuxHost = new LinuxHost();
					linuxHost.setInstanceName(instancename.toString());
					linuxHost.setHost(host);
					linuxHost.setUserName(username);
					linuxHost.setPwd(pwd);
					linuxHost.setPublickey(publickey);
					linuxHost.setPort(port);
					//校验重复性
					for(LinuxHost temLinuxHost:result){
						if(temLinuxHost.getHost().equals(linuxHost.getHost())){
							throw new RuntimeException("存在重复主机:"+temLinuxHost.getHost());
						}
					}
					result.add(linuxHost);
				}
			}
		}
		LinuxHost[] array = result.toArray(new LinuxHost[result.size()]);
		Arrays.sort(array);
		return result;
	}
	
	private void valid(String instantName,Properties pro){
		boolean isContain = true;
		isContain = isContain&&pro.containsKey(instantName+".port");
		isContain = isContain&&pro.containsKey(instantName+".username");
		isContain = isContain&&pro.containsKey(instantName+".pwd");
		isContain = isContain&&pro.containsKey(instantName+".publickey");
		if(!isContain){
			throw new RuntimeException(instantName+"属性不全");
		}
	}
	
	
	public Properties getLinuxPro(File file){
		try {
			Properties pro = new Properties();
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			pro.load(reader);
			reader.close();
			return pro;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

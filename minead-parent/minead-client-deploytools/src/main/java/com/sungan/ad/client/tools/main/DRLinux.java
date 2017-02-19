package com.sungan.ad.client.tools.main;

import java.io.File;

import com.sungan.ad.client.tools.commons.CommonsUtil;
import com.sungan.ad.client.tools.linux.ssh.SSHUtils;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2017年2月19日 下午5:28:32
 * @version V1.1
 */
public class DRLinux extends LinuxHost {
	public DRLinux(){}
	
	public DRLinux(LinuxHost host){
		this.setInstanceName(host.getInstanceName());
		this.setHost(host.getHost());
		this.setPublickey(host.getPublickey());
		this.setPwd(host.getPwd());
		this.setUserName(host.getUserName());
		this.setPort(host.getPort());
	}
	
	private void initConnect(){ 
		if(!CommonsUtil.isBlank(this.getPwd())){
			SSHUtils.connectSSH(this.getHost(), Integer.valueOf(this.getPort()), this.getUserName(), this.getPwd());
		}else if(!CommonsUtil.isBlank(this.getPublickey())){
			SSHUtils.connectSSHInPublicKey(this.getHost(), Integer.valueOf(this.getPort()), this.getUserName(), this.getPublickey());
		}
		String execCommand = SSHUtils.execCommand("cd / \r\n ls");
		if(!execCommand.contains("sungan")){
			SSHUtils.execCommand("cd / \r\n mkdir sungan");
		}
	}
	
	/**
	 * 清除所有文件
	 */
	public void removeClient(){
		this.stop();
		SSHUtils.execCommand("cd /sungan \r\n rm -f -r ./*");
		String execCommand = SSHUtils.execCommand("cd /sungan \r\n ls");
		if(!execCommand.contains("tomcat-client")){
			System.out.println("tomcat-client 已移除");
		}
	}
	
	/**
	 * 清除所有文件
	 */
	public void execCommand(String comand){
		try {
			this.initConnect();
			String execCommand = SSHUtils.execCommand(comand);
			System.out.println(execCommand);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 部署文件
	 * @param files
	 */
	public void uploadFile(File[] files){
		if(true){
			throw new RuntimeException("暂时不可用");
		}
		this.initConnect();
		String execCommand = SSHUtils.execCommand("cd /sungan \r\n ls");
		System.out.println(execCommand);
		for(File srcFile:files){
			this.putFile(srcFile);
		}
	}
	/**
	 * 部署文件
	 * @param files
	 */
	public void deploy(File[] files){
		this.initConnect();
		String execCommand = SSHUtils.execCommand("cd /sungan \r\n ls");
		System.out.println(execCommand);
		if(execCommand.contains("tomcat-client")){
			System.out.println(this.getInstanceName()+"  "+this.getHost()+"tomcat-client 已部署");
		}else{
			for(File srcFile:files){
				this.putFile(srcFile);
			}
			String result = SSHUtils.execCommand("cd /sungan \r\n ls");
			if(result.contains("tomcat-client.tar")){
				SSHUtils.execCommand("cd /sungan \r\n tar -xf tomcat-client.tar");
				SSHUtils.execCommand("cd /sungan \r\n rm tomcat-client.tar");
			}
		}
	}
	
	public void disConnect(){
		SSHUtils.disconnect();
	}
	
	public void startClient(){
		this.initConnect();
		String result = SSHUtils.execCommand("ps -ef | grep tomcat-client");
//		System.out.println(result);
		if(result.contains("java")){
			System.out.println(this.getInstanceName()+"="+this.getHost()+"已经启动");	
		}else{
			SSHUtils.execCommand("cd /sungan/tomcat-client/bin \r\n ./startup.sh");
		}
	}
	
	public void info(){
		this.initConnect();
		String result = SSHUtils.execCommand("ps -ef | grep tomcat-client");
		if(result.contains("java")){
			System.out.println(this.getInstanceName()+"="+this.getHost()+"已经启动");	
		}else{
			System.out.println(this.getInstanceName()+"="+this.getHost()+"应用停止");	
		}
	}
	
	public void stop(){
		this.initConnect();
		String result = SSHUtils.execCommand("ps -ef | grep tomcat-client");
//		System.out.println(result);
		if(result.contains("java")){
			String[] split = result.split("\r\n");
			for(String str:split){
				if(str.contains("org.apache.catalina.startup.Bootstrap")){
					String[] subMesg = str.split("\\s+");
					String pid = subMesg[1];
					String stopComand = "kill -9 "+pid;
					System.out.println("停止命令"+stopComand);
					SSHUtils.execCommand(stopComand);
				} 
			}
			String stopResult = SSHUtils.execCommand("ps -ef | grep tomcat-client");
			if(!stopResult.contains("java")){
				System.out.println(this.getInstanceName()+"="+this.getHost()+"停止成功");
			}
		}else{
			System.out.println(this.getInstanceName()+"="+this.getHost()+"未启动");	
		}
	}
	
	public void putFile(File srcFile){
		if(!srcFile.exists()){
			throw new RuntimeException();
		}
		if(srcFile.isFile()){
			SSHUtils.sftpPutByte(srcFile.getAbsolutePath(), "/sungan/"+srcFile.getName());
		}else{
			File[] listFiles = srcFile.listFiles();
			for(File f:listFiles){
				this.putFile(f);
			}
		}
	}
	
}

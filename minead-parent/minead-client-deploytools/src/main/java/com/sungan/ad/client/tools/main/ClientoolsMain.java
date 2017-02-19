package com.sungan.ad.client.tools.main;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import com.sungan.ad.client.tools.linux.ssh.SSHUtils;

/**
 * 说明:
 * @version V1.1
 */
public class ClientoolsMain {
	private static final String confFile = "./conf";
	
	private static final String INFO = "info" ;
	private static final String DEPLOY = "deploy" ;
	private static final String START = "start" ;
	private static final String STOP = "stop" ;
	private static final String REMOVE = "remove" ;
	private static final String EXIT = "exit" ;
	private static final String UPLOAD = "upload" ;
	private static final String COMAND = "COMAND" ;
	
	public static void main(String[] args) {
		File file = new File(confFile);
		PropertiesParser propertiesParser = new PropertiesParser(file);
		List<LinuxHost> linux = propertiesParser.getLinux();
		System.out.println("客户端命令:"+INFO+" 查看所有客户端详细");
		System.out.println("客户端命令:"+DEPLOY+" 对所有部署");
		System.out.println("客户端命令:"+START+" 对所有启动");
		System.out.println("客户端命令:"+STOP+" 对所有停止");
		System.out.println("客户端命令:"+REMOVE+" 移除所有");
		System.out.println("客户端命令:"+EXIT+" 退出客户端");
		System.out.println("客户端命令:"+UPLOAD+" 上传文件");
		System.out.println("客户端命令:"+COMAND+" 执行命令");
		Scanner scanner = new Scanner(System.in);
		String nextLine = scanner.nextLine();
		while(nextLine==null||!nextLine.equals(EXIT)){
				if(nextLine==null){
					System.out.println("请输入命令:");
					nextLine = scanner.nextLine();
				}
				//查看系统是否启动
				if(nextLine.equals(INFO)){
					for(LinuxHost lh:linux){
						DRLinux drLinux = new DRLinux(lh);
						drLinux.info();
						drLinux.disConnect();
					}
				}
				
				//执行命令
				if(nextLine.equals(COMAND)){
					System.out.println("请输入shell命令:");
					String nextLine2 = scanner.nextLine();
					for(LinuxHost lh:linux){
						DRLinux drLinux = new DRLinux(lh);
						drLinux.execCommand(nextLine2);
						drLinux.disConnect();
					}
				}
				//上传文件
				if(nextLine.equals(UPLOAD)){
					System.out.println("文件上传命令:");
					System.out.println("请先择上传文件|文件夹:");
					String nextLine2 = scanner.nextLine();
					File file2 = new File(nextLine2);
					if(file2.exists()){
						System.out.println("命令参数设置成功，即将开始上传文件...");
						for(LinuxHost lh:linux){
							DRLinux drLinux = new DRLinux(lh);
							drLinux.uploadFile(new File[]{file2});
							drLinux.execCommand("cd /sungan \r\n ls");
							drLinux.disConnect();
						}
					}else{
						System.out.println("文件不存在:"+file);
					}
				}
				//部署客户端
				if(nextLine.equals(DEPLOY)){
					System.out.println("文件部署命令:");
					System.out.println("请先择部署文件|文件夹:");
					String nextLine2 = scanner.nextLine();
					File file2 = new File(nextLine2);
					if(file2.exists()){
						System.out.println("命令参数设置成功，即将开始上传文件...");
						for(LinuxHost lh:linux){
							DRLinux drLinux = new DRLinux(lh);
							drLinux.deploy(new File[]{file2});
				//			drLinux.deploy(new File[]{new File("C:\\Users\\zhangyf18255\\Desktop\\11\\test\\tomcat-client.tar")});
				//			drLinux.deploy(new File[]{new File("C:\\Users\\zhangyf18255\\Desktop\\11\\test\\tomcat-client.txt")});
							drLinux.startClient();
							drLinux.info();
							drLinux.disConnect();
						}
					}else{
						System.out.println("文件不存在:"+file);
					}
				}
				
				
				
				if(nextLine.equals(REMOVE)){
					//清除客户商
					for(LinuxHost lh:linux){
						Boolean isStop = null;
						if(isStop==null){
							System.out.println("确定要删除客户端"+lh.getInstanceName()+" "+lh.getHost()+"吗?(true确定/false取消):");  
							String comand = scanner.nextLine();
							if(!comand.equalsIgnoreCase("true")&&!comand.equalsIgnoreCase("false")){
								System.out.println("未适命令:"+comand+" 不符合期望值");
								break;
							}
							isStop = Boolean.valueOf(comand);
						}
						if(isStop){
							DRLinux drLinux = new DRLinux(lh);
							drLinux.removeClient();
							drLinux.disConnect();
						}
					}
				}
				if(nextLine.equals(START)){
					for(LinuxHost lh:linux){
						DRLinux drLinux = new DRLinux(lh);
						drLinux.startClient();
						drLinux.info();
						drLinux.disConnect();
					}
				}
				if(nextLine.equals(STOP)){
					Boolean isStop = null;
					for(LinuxHost lh:linux){
						if(isStop==null){
							System.out.println("确定要停止客户端"+lh.getInstanceName()+" "+lh.getHost()+"吗?(true确定/false取消):");  
							String comand = scanner.nextLine();
							if(!comand.equalsIgnoreCase("true")&&!comand.equalsIgnoreCase("false")){
								System.out.println("未适命令:"+comand+" 不符合期望值");
							}
							isStop = Boolean.valueOf(comand);
							break;
						}
						if(isStop){
							DRLinux drLinux = new DRLinux(lh);
							drLinux.stop();
							drLinux.info();
							drLinux.disConnect();
						}
					}
				}
				nextLine = null;
		}
	}
	
	
	
	
	
}

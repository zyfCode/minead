package com.sungan.ad.client.tools.main;

import java.io.File;
import java.util.List;

/**
 * 说明:
 * @version V1.1
 */
public class ClientoolsMain {
	private static final String confFile = "./conf";
	
	public static void main(String[] args) {
		File file = new File(confFile);
		PropertiesParser propertiesParser = new PropertiesParser(file);
		List<LinuxHost> linux = propertiesParser.getLinux();
		for(LinuxHost lh:linux){
			DRLinux drLinux = new DRLinux(lh);
			drLinux.info();
			drLinux.disConnect();
		}
		
	}
}

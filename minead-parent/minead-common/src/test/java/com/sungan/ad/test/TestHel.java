package com.sungan.ad.test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;

import net.sf.json.JSONObject;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class TestHel {
	
	@Test
	public void testOval(){
//		AnTestVo test = new AnTestVo();
//		 Validator validator = new Guard(); 
//		 List<ConstraintViolation> validate = validator.validate(test);
//	        System.out.println(validate);
	}
	
	@Test
	public void test1(){
		try {
			AnTestDmain atDmain = new AnTestDmain();
			atDmain.setDate(new Date());
			atDmain.setStatus("1");
			AnTestVo parseToVo = AnnotationParser.parseToVo(AnTestVo.class, atDmain);
			System.out.println(parseToVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() throws SocketException{
		Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
		Set<String> macs = new LinkedHashSet<String>();
		while(networkInterfaces.hasMoreElements()){
			NetworkInterface nextElement = networkInterfaces.nextElement();
			Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
			byte[] mac = nextElement.getHardwareAddress();
			if(mac==null){
				continue;
			}
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<mac.length; i++) {
				if(i!=0) {
					sb.append("-");
				}
				//字节转换为整数
				int temp = mac[i]&0xff;
				String str = Integer.toHexString(temp);
				if(str.length()==1) {
					sb.append("0"+str);
				}else {
					sb.append(str);
				}
			}
			System.out.println(sb);
			while(inetAddresses.hasMoreElements()){
				InetAddress nextElement2 = inetAddresses.nextElement();
				String hostAddress = nextElement2.getHostAddress();
				System.out.println(hostAddress);
				System.out.println();
			}
		}
	}
	
	@Test
	public void testJsonArr(){
//		AnTestVo testVo3 = new AnTestVo();
//		testVo3.setStatus("2");
//		AnTestVo testVo2 = new AnTestVo();
//		testVo2.setStatus("1");
//		AnTestVo testVo1 = new AnTestVo();
//		testVo1.setStatus("0");
//		AnTestVo [] arr = new AnTestVo[]{testVo1,testVo2,testVo3};
//		JSONArray fromObject = JSONArray.fromObject(arr);
//		String string = fromObject.toString();
//		System.out.println(string);
//		JSONArray fromObject3 = JSONArray.fromObject(string);
//		AnTestVo [] array = (AnTestVo[]) JSONArray.toArray(fromObject3, AnTestVo.class);
////		Object[] array = fromObject3.toArray();
//		System.out.println(Arrays.asList(array));
//	 
	}
	@Test
	public void test2(){
		AnTestJson json = new AnTestJson();
		json.setId(112233L);
		json.setSeralNo("bbbbbbbb123");
		AnTestDmain testDmain = new AnTestDmain();
		testDmain.setDate(new Date());
		testDmain.setStatus("1");
		json.setTestDmain(testDmain );
		JSONObject obj = JSONObject.fromObject(json);
		System.out.println(obj.toString());
		JSONObject fromObject = JSONObject.fromObject(obj.toString());
		Object bean = JSONObject.toBean(fromObject, AnTestJson.class);
		System.out.println(bean.toString());
	}
}














package common.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.controller.validBean.AdClientValid;
import com.sungan.ad.controller.validBean.AdTaskAddValid;
import com.sungan.ad.controller.validBean.AdWeightGroupValid;
import com.sungan.ad.domain.AdClient;
import com.sungan.ad.domain.AdContent;
import com.sungan.ad.domain.AdHourWeight;
import com.sungan.ad.domain.AdWeightGroup;
import com.sungan.ad.domain.AppTask;
import com.sungan.ad.exception.AdRuntimeException;

import net.sf.json.JSONObject;


/**
 * 说明:
 * @version V1.1
 */
public class TaskMain {
	
	@Test
	public void test2() throws IOException{
		FileOutputStream out = new FileOutputStream("D:\\work\\1.txt");
		for (int i=1;i<1000000;i++){
			TT rb = new TT("123456789011131517192123", "123456789011131517192123", "123456789011131517192123", "123456789011131517192123", "123456789011131517192123");
			out.write(JSONObject.fromObject(rb).toString().getBytes());
		}
		out.close();
	}
	public static class TT{
		private String ip;
		private String url;
		private String orderId;
		private String storId;
		private String count;
		
		
		public TT(String ip, String url, String orderId, String storId, String count) {
			super();
			this.ip = ip;
			this.url = url;
			this.orderId = orderId;
			this.storId = storId;
			this.count = count;
		}
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getOrderId() {
			return orderId;
		}
		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}
		public String getStorId() {
			return storId;
		}
		public void setStorId(String storId) {
			this.storId = storId;
		}
		public String getCount() {
			return count;
		}
		public void setCount(String count) {
			this.count = count;
		}
		
		
		
	}
	
	@Test
	public void test3(){
		AdTaskAddValid adWeightGroup = new AdTaskAddValid();
//		adWeightGroup.setName("11");
		Object object = adWeightGroup;
		ValidatorFactory buildDefaultValidatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = buildDefaultValidatorFactory.getValidator();
		Set<ConstraintViolation<Object>> validate = validator.validate(object);
		for(ConstraintViolation<?> ct:validate){
			String message = ct.getMessage();
			System.out.println(message);
		}
		
	}
	
	@Test
	public void test1() throws URISyntaxException, UnsupportedEncodingException{
		try {
			URL resource = TaskMain.class.getClassLoader().getResource("template");
			File f = new  File(resource.toURI());
			 String[] list = f.list();
				
			VelocityEngine ve = new VelocityEngine();
			 ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			 ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			 ve.init();
			 
			 String pk = "com.sungan.ad";
			 String daminPk = "com.sungan.ad";
			 Class clazz = AppTask.class;
			 String[] split = clazz.getName().split("\\.");
			 String domainName = split[split.length-1];
			 String domainLow = domainName.substring(0,1).toLowerCase() + domainName.substring(1); ;//"adWeightGroup" ;
			 String varname = domainLow+"DAO";
			 
//			 Map<String, Object> beanFile = AdCommonsUtil.getBeanFile(clazz);
//			 Set<String> keySet = beanFile.keySet();
			 Field[] declaredFields = clazz.getDeclaredFields();
			 List<String> keySet = new ArrayList<String>();
			 for(Field fname :declaredFields){
				 if(fname.getName().equals("class")){
					 continue;
				 }
				 keySet.add(fname.getName());
			 }
			 
			 //conroller
			 String contollerMapping = domainName.toLowerCase();
			 String suffix = domainName.toLowerCase();
			 
			 //VM
			 String vcKey = "#";
			 
			 
			 for(String tname:list){
				 Template t = ve.getTemplate("/template/"+tname);
				 VelocityContext ctx = new VelocityContext();
				 String javaName = domainName+tname.replace(".vm", ".java");
				 if(tname.equals("list.vm")){
					 javaName = domainName.toLowerCase()+".vm";
				 }
				 File newF= new File("D:/log/"+javaName);
				 if(!newF.exists()){
					 new File("D:/log/").mkdirs();
					 newF.createNewFile();
				 }
				 FileOutputStream out = new FileOutputStream(newF);
				 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
				 ctx.put("package", pk);
				 ctx.put("domainPackage",daminPk);
				 ctx.put("domain", domainName);
				 ctx.put("domainLow", domainLow);
				 ctx.put("varname", varname);
				 ctx.put("contollerMapping", contollerMapping);
				 ctx.put("suffix", suffix);
				 ctx.put("vcKey", vcKey);
				 ctx.put("beanPro", keySet);
//			 List temp = new ArrayList();
//			 temp.add("1");
//			 temp.add("2");
//			 ctx.put("list", temp);
				 
				 StringWriter sw = new StringWriter();
//				 t.setEncoding("UTF-8");
				 t.merge(ctx, sw);
				 System.out.println(new String(sw.toString().getBytes("ISO-8859-1"),"UTF-8"));
				 writer.write(new String(sw.toString().getBytes("ISO-8859-1"),"UTF-8"));
				 writer.close();
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
				
	}
}

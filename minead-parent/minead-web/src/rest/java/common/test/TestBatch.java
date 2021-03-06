package common.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2017年1月6日 下午10:14:33
 * @version V1.1
 */
public class TestBatch {
	private static final Log log = LogFactory.getLog(TestBatch.class);
	private String defaultUserAgent= "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_2 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Mobile/13F69 MicroMessenger/6.3.21 NetType/WIFI Language/zh_CN";
	private String userAgent= "Mozilla/5.0 (iPhone; CPU iPhone OS ${version} like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Mobile/13F69 MicroMessenger/6.3.21 NetType/WIFI Language/zh_CN";
	private static  ScheduledThreadPoolExecutor excutor = new ScheduledThreadPoolExecutor(5);
	private static int count = 0;
	private static int actuallCount = 0;
	private static int[] arr = new int[100];
	private static int[] hit = new int[5];
	
	private int nexInt(int i){
		return new Random().nextInt(i);
	}
	private String getUserAgent(){
//		int [] arr1 = {4,5,6,7,8,9,10};
//		int [] arr2 = {0,1,2,3};
//		int [] arr3 = {1,2,3,4};
//		String version = arr1[this.nexInt(arr1.length)]+"_"+arr2[this.nexInt(arr2.length)]+"_"+arr3[this.nexInt(arr3.length)]; 
//		String newAgent  = defaultUserAgent;
//		if(userAgent.contains("${version}")){
//			newAgent = userAgent.replace("${version}", version);
//		}
//		return newAgent;
		
		return defaultUserAgent;
	} 
	
	
	public synchronized static int actuallCount(int num){
		actuallCount = actuallCount +num;
		return actuallCount;
	}
	public synchronized static int add(int num){
		count = count +num;
		return count;
	}
	
	public static boolean canExcute(){
//		return true;
		Random random = new Random();
		int randomVal = arr[random.nextInt(arr.length)];
		for(int val:hit){
			if(randomVal==val){
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) throws Exception {
		for(int i=0;i<arr.length;i++){
			arr[i]=i;
		}
		Random random = new Random();
		for(int i=0;i<hit.length;i++){
			hit[i] = arr[random.nextInt(arr.length)];
		}
//		for(int j=0;j<100;j++){
//			for(int i=0;i<1000;i++){
//				add(1);
//				if(canExcute()){
//					actuallCount(1);
//				}
//			}
//			
//			System.out.println(actuallCount+"/"+count);
//		}
//		new TestBatch().doExcute();
		
		excutor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				if(count>50000){
					excutor.shutdown();
				}
				excutor.execute(new Runnable() {
					@Override
					public void run() {
						try {
							log.info(actuallCount+"/"+TestBatch.add(1)+"任务开始....");
							new TestBatch().doExcute();
							log.info(actuallCount+"/"+TestBatch.add(0)+"===任务完成...");
						} catch (Exception e) {
							log.error("任务异常", e);
						}
					}
				});
			}
		}, 0, 1, TimeUnit.MILLISECONDS);
	}
	public void doExcute() throws Exception{
	
//		GET /vs-22876 HTTP/1.1
//		Host: m.mansorychina.net
//		Connection: keep-alive
//		Accept: */*
//		User-Agent: Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_2 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Mobile/13F69 MicroMessenger/6.3.21 NetType/WIFI Language/zh_CN
//		Accept-Language: zh-cn
//		Referer: http://www.qxyy66.com/static/game/navigator/default.htm
//		Accept-Encoding: gzip, deflate
		String randomIp = TestBatch.getRandomIp();
		log.info("随机产生的randomip:"+randomIp);
		boolean canExcute = canExcute();
		Set<String> subUrl = this.getSubUrl(randomIp);
		for(String url:subUrl){
			if(url.endsWith(".jpeg")||url.endsWith(".png")||url.endsWith(".jpg")||url.endsWith(".gif")){
				log.info("图片不访问："+url);
				continue;
			}
			if(url.endsWith("t=")&&url.contains("uu.php")){
				url = url+ new Random().nextFloat();
				if(!canExcute){  //点击率不执行
					continue;
				}
				log.info("实行点击数："+actuallCount(1));
			}
			this.justExcut(url,randomIp);
		}
		
	}
	
	private void justExcut(String url,String randomIp) throws Exception{
		try {
			String host = this.getHost(url);
			HttpGet get = this.getUrl(url, "m.mansorychina.net", this.getUserAgent(),randomIp);
			DefaultHttpClient client = new DefaultHttpClient();
			try {
				client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 1000); 
				client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 1000);
				CloseableHttpResponse execute = client.execute(get);
				int statusCode = execute.getStatusLine().getStatusCode();
				log.info(url+"  CODE:"+statusCode);
				execute.getEntity();
			} finally{
				client.getConnectionManager().shutdown();
			}
		} catch (Exception e) {
			log.error(url+"   "+e.getMessage());
		}
	}
	
	
	public String getHost(String url){
		  Matcher matcher = Pattern.compile("http:\\/\\/([^\\/:]+)").matcher(url);
		  if(matcher.find()){
			  String group = matcher.group(1);
			  return group;
		  }
		  return "m.mansorychina.net";
	}
	
	public Set<String> getSubUrl(String randomIp) throws Exception{
		Set<String> urls = new LinkedHashSet<String>();
		HttpGet get = this.getUrl("http://m.mansorychina.net/vs-22876", "m.mansorychina.net", this.getUserAgent(),randomIp);
		DefaultHttpClient client = new DefaultHttpClient();
		try {
			CloseableHttpResponse response = client.execute(get);
			if (null != response.getEntity()
					&& HttpStatus.SC_OK == response.getStatusLine()
							.getStatusCode()) {
				byte[] byteArray = EntityUtils.toByteArray(response.getEntity());
				ByteArrayInputStream arrIn = new ByteArrayInputStream(byteArray);
				GZIPInputStream inputString = new GZIPInputStream(arrIn);
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputString, "UTF-8"));
				StringBuffer bufStr = new StringBuffer();
				String line = null;
				while((line=reader.readLine())!=null){
					//忽略var fMkwW9376_curl_extend
//					if(line.contains("var fMkwW9376_curl_extend")){
//						log.info("已经忽略:"+line);
//						continue;
//					}
					bufStr.append(line).append("\r\n");
				}
				if(log.isDebugEnabled()){
					log.debug(bufStr);
				}
				reader.close();
				String regex = "[\"|\']{1}(htt[^\"\']+)[\"|\']{1}";
				Matcher matcher = Pattern.compile(regex).matcher(bufStr);
				while(matcher.find()){
					String group = matcher.group(1);
					urls.add(group);
				}
//			String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
//			System.out.println(result);
			}else{
				System.out.println("访问异常:"+get);
			}
		}catch (Exception e){
			log.error(e.getMessage());
		} finally {
			client.getConnectionManager().shutdown();
		}
		return urls;
	}
	
	
	private HttpGet getUrl(String url,String host,String userAgent,String randomIp){
		HttpGet get = new HttpGet(url);
		get.addHeader("Host", host);
//		get.addHeader("Host", "www.qxyy66.com");
//		get.addHeader("x-forwarded-for", randomIp+","+getRandomIp());
		get.addHeader("Accept", "*/*");
		get.addHeader("User-Agent", userAgent);
		get.addHeader("Referer", "http://www.qxyy66.com/static/game/navigator/default.htm");
		get.addHeader("accept-language", "zh-cn");
		get.addHeader("Accept-Encoding", "gzip, deflate");
		return get;
		
	}
	
	
	/*
     * 随机生成国内IP地址
     */
    public static String getRandomIp(){
         
        //ip范围
        int[][] range = {{607649792,608174079},//36.56.0.0-36.63.255.255
                         {1038614528,1039007743},//61.232.0.0-61.237.255.255
                         {1783627776,1784676351},//106.80.0.0-106.95.255.255
                         {2035023872,2035154943},//121.76.0.0-121.77.255.255
                         {2078801920,2079064063},//123.232.0.0-123.235.255.255
                         {-1950089216,-1948778497},//139.196.0.0-139.215.255.255
                         {-1425539072,-1425014785},//171.8.0.0-171.15.255.255
                         {-1236271104,-1235419137},//182.80.0.0-182.92.255.255
                         {-770113536,-768606209},//210.25.0.0-210.47.255.255
                         {-569376768,-564133889}, //222.16.0.0-222.95.255.255
        };
         
        Random rdint = new Random();
        int index = rdint.nextInt(10);
        String ip = num2ip(range[index][0]+new Random().nextInt(range[index][1]-range[index][0]));
        return ip;
    }
 
/*
     * 将十进制转换成ip地址
     */
    public static String num2ip(int ip) {
        int [] b=new int[4] ;
        String x = "";
         
        b[0] = (int)((ip >> 24) & 0xff);
        b[1] = (int)((ip >> 16) & 0xff);
        b[2] = (int)((ip >> 8) & 0xff);
        b[3] = (int)(ip & 0xff);
        x=Integer.toString(b[0])+"."+Integer.toString(b[1])+"."+Integer.toString(b[2])+"."+Integer.toString(b[3]); 
         
        return x; 
     }
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	@Test
	public void TestHost() throws Exception{
		String str = new Random().nextFloat()+"";
		System.out.println(str);
//		String randomIp = getRandomIp();
//		Set<String> subUrl = this.getSubUrl(randomIp);
//		for(String url:subUrl){
//			String host = getHost(url);
//			System.out.println(url);
//			System.out.println(host);
//		}
	}
	
	@Test
	public void testGetRandomIp(){
		Set<String> set = new LinkedHashSet<String>();
		for(int i=0;i<1000;i++){
			System.out.println(getRandomIp());
//			set.add(getRandomIp());
//			if(i%100000==0){
//				log.info(set.size());
//			}
		}
	}
}

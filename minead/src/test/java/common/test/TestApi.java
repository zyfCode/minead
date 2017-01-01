package common.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.sungan.ad.commons.JAXBUtil;
import com.sungan.ad.controller.WXController;
import com.sungan.ad.wx.access.bean.WxResponseMsgTxtAndImg;
import com.sungan.ad.wx.access.bean.WxResponseMsgTxtAndImg.Articles;
import com.sungan.ad.wx.access.bean.WxResponseMsgTxtAndImg.Articles.Item;
import com.sungan.ad.wx.access.impl.WxDataConnectorImpl;

import net.sf.json.JSONObject;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2016年12月27日 下午11:21:31
 * @version V1.1
 */
public class TestApi {
	private static final Log log =LogFactory.getLog(WXController.class);
	public static void main(String[] args) {
		String url = "http://localhost:8080/minead/wx/sungan/wx1d173e30beb9889b?hello=123";
//		String url = "http://139.196.240.242/wx/sungan/wx1d173e30beb9889b?hello=123";
		String data = "";
//		 data = "<xml><ToUserName><![CDATA[toUser]]></ToUserName>"
//				 +"<FromUserName><![CDATA[fromUser]]></FromUserName>"
//				 +"<CreateTime>1348831860</CreateTime>"
//				 +" <MsgType><![CDATA[text]]></MsgType>"
//				 +"<Content><![CDATA[this is a test]]></Content>"
//				 +"<MsgId>1234567890123456</MsgId>"
//				 +"</xml>";
		data = "<xml> <ToUserName><![CDATA[toUser]]></ToUserName>"
				+"<FromUserName><![CDATA[FromUser]]></FromUserName>"
				+"<CreateTime>123456789</CreateTime> <MsgType><![CDATA[event]]></MsgType>"
				+"<Event><![CDATA[subscribe]]></Event> </xml>";
		TestApi.getDataFromWx(url, data, null);
	}
	
	private static SimpleDateFormat forMat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	@Test
	public void testRealIp() throws ClientProtocolException, IOException{
//		host:139.196.240.242
//		x-real-ip:115.200.234.236
//		x-scheme:http
//		connection:close
//		accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
//		upgrade-insecure-requests:1
//		user-agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36
//		accept-encoding:gzip, deflate, sdch
//		accept-language:zh-CN,zh;q=0.8
//		cookie:td_cookie=18446744072442819459
		DefaultHttpClient client = new DefaultHttpClient();
//		HttpGet get = new HttpGet("http://139.196.240.242/wx/list/usermsg?appid=wx1d173e30beb9889b");
		HttpGet get = new HttpGet("http://127.0.0.1:8080/minead/wx/list/usermsg?appid=wx1d173e30beb9889b");
		get.addHeader("x-forwarded-for", "10.153.43.21,10.172.22.2");
		get.addHeader("x-scheme", "connection:close");
		get.addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		get.addHeader("upgrade-insecure-requests", "1");
		get.addHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36");
		get.addHeader("accept-encoding", "gzip, deflate, sdch");
		get.addHeader("accept-language", "zh-CN,zh;q=0.8");
		CloseableHttpResponse response = client.execute(get);
		Header[] allHeaders = response.getAllHeaders();
		for(Header header:allHeaders){ 
			String name = header.getName();
			String value = header.getValue();
			System.out.println(name+":"+value);
		}
		if (null != response.getEntity()
				&& HttpStatus.SC_OK == response.getStatusLine()
						.getStatusCode()) {
			String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			if(log.isDebugEnabled()){
				log.debug("响应的报文件:"+result);
			}
//			JSONObject fromObject = net.sf.json.JSONObject.fromObject(result);
//			T bean = (T) JSONObject.toBean(fromObject,clazz);
		}else{
		}
	}
	
	
	@Test
	public void testStrToBean() throws JAXBException, IOException{
		String data = "<xml>"
				 +"<ToUserName><![CDATA[toUser]]></ToUserName>"
				 +"<FromUserName><![CDATA[fromUser]]></FromUserName>"
				 +"<CreateTime>12345678</CreateTime>"
				 +"<MsgType><![CDATA[news]]></MsgType>"
				 +"<ArticleCount>2</ArticleCount>"
				 +"<Articles>"
				 +"<item>"
				 +"<Title><![CDATA[title1]]></Title>" 
				 +"<Description><![CDATA[description1]]></Description>"
				 +"<PicUrl><![CDATA[picurl]]></PicUrl>"
				 +"<Url><![CDATA[url]]></Url>"
				 +"</item>"
				 +"<item>"
				 +"<Title><![CDATA[title]]></Title>"
				 +"<Description><![CDATA[description]]></Description>"
				 +"<PicUrl><![CDATA[picurl]]></PicUrl>"
				 +"<Url><![CDATA[url]]></Url>"
				 +"</item>"
				 +"</Articles>"
				 +"</xml>";
		WxResponseMsgTxtAndImg unmarshal = JAXBUtil.unmarshal(WxResponseMsgTxtAndImg.class,data);
		System.out.println(unmarshal);
		
	}
	
	@Test
	public void testTxtMsg() throws Exception{
		
		WxResponseMsgTxtAndImg txtImg =new WxResponseMsgTxtAndImg();
		txtImg.setCreateTime(forMat.format(new Date()));
		txtImg.setFromUserName("");
		txtImg.setToUserName("");
		txtImg.setMsgType("news");
		Articles acticles = new Articles();
		txtImg.setArticles(acticles);
		List<Item> item = acticles.getItem(); 
		String strImgUrl = "http://139.196.240.242/static/";
		String imgur0 = strImgUrl+"order0.jpg";
		String  imgur1 = strImgUrl+"order1.jpg";
		String  imgur2 = strImgUrl+"order2.jpg";
		String  imgur3 = strImgUrl+"order3.jpg";
		

		Item  order0 = new Item();
		order0.setDescription("中国良心之作NO1");
		order0.setPicUrl(imgur0);
		order0.setTitle("元旦精选影视巨作0");
		order0.setUrl(imgur0);
		item.add(order0);

		Item  order1 = new Item();
		order1.setDescription("中国良心之作NO2");
		order1.setPicUrl(imgur1);
		order1.setTitle("元旦精选影视巨作0");
		order1.setUrl(imgur1);
		item.add(order1);
		
		Item  order2 = new Item();
		order2.setDescription("中国良心之作NO3");
		order2.setPicUrl(imgur2);
		order2.setTitle("元旦精选影视巨作0");
		order2.setUrl(imgur2);
		item.add(order2);
		
		Item  order3 = new Item();
		order3.setDescription("中国良心之作NO4");
		order3.setPicUrl(imgur3);
		order3.setTitle("元旦精选影视巨作0");
		order3.setUrl(imgur3);
		item.add(order3);
		txtImg.setArticleCount(item.size()+"");
		
		String ojbectToXmlWithCDATA = JAXBUtil.ojbectToXmlWithCDATA(txtImg);
		System.out.println(ojbectToXmlWithCDATA);
	}
	/**
	 *  用全路径名(如http://localhost/xx)连接服务器，获取设备XML信息
	 * @param fullUrl 全路经名 如http://localhost/xx
	 * @return String
	 * @throws Exception
	 */ 
	public static <T>T getDataFromWx(String fullUrl,String data,Class<T> clazz) {
//		if(StringUtils.isBlank(fullUrl)||!fullUrl.matches("^http\\:\\/\\/.+\\/.*")){
//			throw new RuntimeException();
//		}
		DefaultHttpClient client = new DefaultHttpClient();
        String result = "";
        HttpPost post = null;
        HttpResponse response = null;
        try {
        	post = new HttpPost(fullUrl);
        	post.addHeader("accept", "application/xml");
        	post.setEntity(new StringEntity(data, "UTF-8"));
        	post.addHeader("Content-Type", "application/xml;charset=UTF-8");
			response = client.execute(post);
			result = "";
			log.info(fullUrl+" 响应为:"+response.getStatusLine().getStatusCode());
			if (null != response.getEntity()
					&& HttpStatus.SC_OK == response.getStatusLine()
							.getStatusCode()) {
				result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
				if(log.isDebugEnabled()){
					log.debug("响应的报文件:"+result);
				}
//				JSONObject fromObject = net.sf.json.JSONObject.fromObject(result);
//				T bean = (T) JSONObject.toBean(fromObject,clazz);
				return null;
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
			if(post!=null)post.abort();
			client.getConnectionManager().shutdown();
		}
	}
	
}

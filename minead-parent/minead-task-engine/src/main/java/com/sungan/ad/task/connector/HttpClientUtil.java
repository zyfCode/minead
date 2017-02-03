package com.sungan.ad.task.connector;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

/**
 * 说明:
 * @version V1.1
 */
public class HttpClientUtil {
	private static final String encoding="UTF-8";
	private HttpClientUtil(){}
	public static String doPost(String url,Object postData){
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			if(postData!=null){
				JSONObject obj = JSONObject.fromObject(postData);
				post.setEntity(new StringEntity(obj.toString(), encoding));
			}
			CloseableHttpResponse response = client.execute(post);
			if (null != response.getEntity()
					&& HttpStatus.SC_OK == response.getStatusLine()
							.getStatusCode()) {
				String string = EntityUtils.toString(response.getEntity(), encoding);
				return string;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException("",e);
		}
		
	}
}

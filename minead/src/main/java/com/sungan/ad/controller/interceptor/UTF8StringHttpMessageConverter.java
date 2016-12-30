package com.sungan.ad.controller.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

public class UTF8StringHttpMessageConverter extends StringHttpMessageConverter{
	private Log log = LogFactory.getLog(UTF8StringHttpMessageConverter.class);
	public UTF8StringHttpMessageConverter(){
		super(Charset.forName("UTF-8"));
//		log.info("UTF8StringHttpMessageConverter已经初始化");
	}

	@Override
	public void setWriteAcceptCharset(boolean writeAcceptCharset) {
		super.setWriteAcceptCharset(writeAcceptCharset);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return super.supports(clazz);
	}

	@Override
	protected String readInternal(Class<? extends String> clazz,
			HttpInputMessage inputMessage) throws IOException {
//		if(log.isDebugEnabled()){
//			log.debug("clazz:"+clazz.getName());
//		}
		return super.readInternal(clazz, inputMessage);
	}

	@Override
	protected Long getContentLength(String s, MediaType contentType) {
		if(log.isDebugEnabled()){
			log.debug("contentType:"+contentType);
		}
		return super.getContentLength(s, contentType);
	}

	@Override
	protected void writeInternal(String s, HttpOutputMessage outputMessage)
			throws IOException {
//		if(log.isDebugEnabled()){
//			log.debug("contentType:"+getAcceptedCharsets());
//		}
		super.writeInternal(s, outputMessage);
	}

	@Override
	protected List<Charset> getAcceptedCharsets() {
//		if(log.isDebugEnabled()){
//			log.debug("charset:"+super.getAcceptedCharsets());
//		}
		return super.getAcceptedCharsets();
	}
	
	
}

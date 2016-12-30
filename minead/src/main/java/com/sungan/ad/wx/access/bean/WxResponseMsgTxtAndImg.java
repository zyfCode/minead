package com.sungan.ad.wx.access.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 说明:
 * 
<xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[fromUser]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[news]]></MsgType>
<ArticleCount>2</ArticleCount>
<Articles>
<item>
<Title><![CDATA[title1]]></Title> 
<Description><![CDATA[description1]]></Description>
<PicUrl><![CDATA[picurl]]></PicUrl>
<Url><![CDATA[url]]></Url>
</item>
<item>
<Title><![CDATA[title]]></Title>
<Description><![CDATA[description]]></Description>
<PicUrl><![CDATA[picurl]]></PicUrl>
<Url><![CDATA[url]]></Url>
</item>
</Articles>
</xml>
 * @date 2016年12月28日 上午1:34:53
 * @version V1.1
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WxResponseMsgTxtAndImg implements AdXMLInterface {
	@XmlElement(required=true,name="ToUserName")
	private String toUserName;
	@XmlElement(required=true,name="FromUserName")
	private String fromUserName;
	@XmlElement(required=true,name="CreateTime")
	private String createTime;
	@XmlElement(required=true,name="MsgType")
	private String msgType;
	@XmlElement(required=true,name="ArticleCount")
	private String articleCount;
	@XmlElement(required=true,name="Articles")
	private Articles articles;

	public String getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}
	
	public Articles getArticles() {
		return articles;
	}
	public void setArticles(Articles articles) {
		this.articles = articles;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static 	class Articles{
		@XmlElement(required=true,name="item")
		private List<Item> item = new ArrayList<WxResponseMsgTxtAndImg.Articles.Item>();
		
		@XmlAccessorType(XmlAccessType.FIELD)
		public static class Item{
			@XmlElement(required=true,name="Title")
			private String title;
			@XmlElement(required=true,name="Description")
			private String description;
			@XmlElement(required=true,name="PicUrl")
			private String picUrl;
			@XmlElement(required=true,name="Url")
			private String url;
			public String getTitle() {
				return title;
			}
			public void setTitle(String title) {
				this.title = title;
			}
			public String getDescription() {
				return description;
			}
			public void setDescription(String description) {
				this.description = description;
			}
			public String getPicUrl() {
				return picUrl;
			}
			public void setPicUrl(String picUrl) {
				this.picUrl = picUrl;
			}
			public String getUrl() {
				return url;
			}
			public void setUrl(String url) {
				this.url = url;
			}
		}

		public List<Item> getItem() {
			return item;
		}

		public void setItem(List<Item> item) {
			this.item = item;
		}
		
	}
	
	
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
}

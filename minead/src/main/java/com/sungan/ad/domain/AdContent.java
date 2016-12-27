package com.sungan.ad.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 说明:
 * 
 * @date 2016年12月27日 下午5:33:40
 * @version V1.1
 */
@Entity
@Table(name="t_adcontent")
public class AdContent implements Serializable{
	public static final String NOTPUBLIC_1 = "0";
	public static final String PUBLIC_1 = "1";
	
	public static final String TYPE_TEXT="1";  //文本
	public static final String TYPE_TEXT_IMG="2";  //图文
	/**
	 * 
	 */
	private static final long serialVersionUID = 711983513506441819L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(length=32)
	private String name;
	@Column(length=64)
	private String appid;
	@Column(columnDefinition="TEXT")
	private String content;
	@Column(length=32)
	private String status;
	private Date createTime;
	private Date updateTime;
	@Column(length=128)
	private String descript;
	@Column(length=2)
	private String type;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
}

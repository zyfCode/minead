package com.sungan.ad.test;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 说明:
 * 
 * @date 2017年3月5日 下午5:32:38
 * @version V1.1
 */
public class TestValiBean {
//	@NotBlank(message="名字不能为空或者空串")
//	@Length(min=2,max=10,message="名字必须由2~10个字组成")
	private String name;
//	@Email(message="邮箱格式不正确")
	private String email;
	
//	@Pattern(regexp="(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,10}",message="密码必须是5~10位数字和字母的组合")
	private String password;
	
//	@AssertTrue(message="字段必须为真")
	private boolean valid;

	@NotBlank(message="名字不能为空或者空串")
	@Length(min=2,max=10,message="名字必须由2~10个字组成")
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public boolean isValid() {
		return valid;
	}
	
	
}

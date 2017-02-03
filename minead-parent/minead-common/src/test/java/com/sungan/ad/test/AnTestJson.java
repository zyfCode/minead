package com.sungan.ad.test;
/**
 * 说明:
 * 
 * @version V1.1
 */
public class AnTestJson {
	private String seralNo;
	private Long id;
	private AnTestDmain testDmain;
	public String getSeralNo() {
		return seralNo;
	}
	@Override
	public String toString() {
		return "AnTestJson [seralNo=" + seralNo + ", id=" + id + ", testDmain=" + testDmain + "]";
	}
	public void setSeralNo(String seralNo) {
		this.seralNo = seralNo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public AnTestDmain getTestDmain() {
		return testDmain;
	}
	public void setTestDmain(AnTestDmain testDmain) {
		this.testDmain = testDmain;
	}
	
	
}

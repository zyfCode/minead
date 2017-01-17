package com.sungan.ad.test;

import java.util.Date;

import org.junit.Test;

import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class TestHel {
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
}

package com.sungan.ad.service.ext;

import java.util.ArrayList;
import java.util.List;

import com.sungan.ad.vo.AdHourWeightVo;


/**
 * 说明:
 * @version V1.1
 */
public class HourUtil {
	private HourUtil(){}
	
	public static int countAllweight(List<AdHourWeightVo> result){
		int count = 0;
		for(AdHourWeightVo vo:result){
			count = count+vo.getWght();
		}
		return count;
	}
	
	public static long taskamount(int currentWeight,int allWeight,Long count){
		Long all = currentWeight*count/allWeight;
		return all;
	}
	
	public static List<AdHourWeightVo> getDefault(){
		List<AdHourWeightVo> result = new ArrayList<AdHourWeightVo>();
		AdHourWeightVo weight23 = new AdHourWeightVo();
		weight23.setHour(23);
		weight23.setThrowRate(8);
		weight23.setWght(10);
		result.add(weight23);
		AdHourWeightVo weight22 = new AdHourWeightVo();
		weight22.setHour(22);
		weight22.setThrowRate(8);
		weight22.setWght(10);
		result.add(weight22);
		AdHourWeightVo weight21 = new AdHourWeightVo();
		weight21.setHour(21);
		weight21.setThrowRate(8);
		weight21.setWght(10);
		result.add(weight21);
		AdHourWeightVo weight20 = new AdHourWeightVo();
		weight20.setHour(20);
		weight20.setThrowRate(8);
		weight20.setWght(10);
		result.add(weight20);
		AdHourWeightVo weight19 = new AdHourWeightVo();
		weight19.setHour(19);
		weight19.setThrowRate(8);
		weight19.setWght(10);
		result.add(weight19);
		AdHourWeightVo weight18 = new AdHourWeightVo();
		weight18.setHour(18);
		weight18.setThrowRate(8);
		weight18.setWght(10);
		result.add(weight18);
		AdHourWeightVo weight17 = new AdHourWeightVo();
		weight17.setHour(17);
		weight17.setThrowRate(8);
		weight17.setWght(10);
		result.add(weight17);
		AdHourWeightVo weight16 = new AdHourWeightVo();
		weight16.setHour(16);
		weight16.setThrowRate(8);
		weight16.setWght(10);
		result.add(weight16);
		AdHourWeightVo weight15 = new AdHourWeightVo();
		weight15.setHour(15);
		weight15.setThrowRate(8);
		weight15.setWght(10);
		result.add(weight15);
		AdHourWeightVo weight14 = new AdHourWeightVo();
		weight14.setHour(14);
		weight14.setThrowRate(8);
		weight14.setWght(10);
		result.add(weight14);
		AdHourWeightVo weight13 = new AdHourWeightVo();
		weight13.setHour(13);
		weight13.setThrowRate(8);
		weight13.setWght(10);
		result.add(weight13);
		AdHourWeightVo weight12= new AdHourWeightVo();
		weight12.setHour(12);
		weight12.setThrowRate(8);
		weight12.setWght(10);
		result.add(weight12);
		AdHourWeightVo weight11 = new AdHourWeightVo();
		weight11.setHour(11);
		weight11.setThrowRate(8);
		weight11.setWght(10);
		result.add(weight11);
		AdHourWeightVo weight10 = new AdHourWeightVo();
		weight10.setHour(10);
		weight10.setThrowRate(8);
		weight10.setWght(10);
		result.add(weight10);
		AdHourWeightVo weight9 = new AdHourWeightVo();
		weight9.setHour(9);
		weight9.setThrowRate(8);
		weight9.setWght(10);
		result.add(weight9);
		AdHourWeightVo weight8 = new AdHourWeightVo();
		weight8.setHour(8);
		weight8.setThrowRate(8);
		weight8.setWght(10);
		result.add(weight8);
		AdHourWeightVo weight7 = new AdHourWeightVo();
		weight7.setHour(7);
		weight7.setThrowRate(8);
		weight7.setWght(10);
		result.add(weight7);
		AdHourWeightVo weight6 = new AdHourWeightVo();
		weight6.setHour(6);
		weight6.setThrowRate(8);
		weight6.setWght(10);
		result.add(weight6);
		AdHourWeightVo weight5 = new AdHourWeightVo();
		weight5.setHour(5);
		weight5.setThrowRate(8);
		weight5.setWght(10);
		result.add(weight5);
		AdHourWeightVo weight4= new AdHourWeightVo();
		weight4.setHour(4);
		weight4.setThrowRate(8);
		weight4.setWght(10);
		result.add(weight4);
		AdHourWeightVo weight3 = new AdHourWeightVo();
		weight3.setHour(3);
		weight3.setThrowRate(8);
		weight3.setWght(10);
		result.add(weight3);
		AdHourWeightVo weight2 = new AdHourWeightVo();
		weight2.setHour(2);
		weight2.setThrowRate(8);
		weight2.setWght(10);
		result.add(weight2);
		AdHourWeightVo weight1 = new AdHourWeightVo();
		weight1.setHour(1);
		weight1.setThrowRate(8);
		weight1.setWght(10);
		result.add(weight1);
		AdHourWeightVo weight0 = new AdHourWeightVo();
		weight0.setHour(0);
		weight0.setThrowRate(8);
		weight0.setWght(10);
		return result;
	}
}

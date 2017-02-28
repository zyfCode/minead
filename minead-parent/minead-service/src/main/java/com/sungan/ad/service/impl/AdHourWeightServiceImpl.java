package com.sungan.ad.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.dao.AdPager;
import com.sungan.ad.dao.base.AdHourWeightDAO;
import com.sungan.ad.domain.AdHourWeight;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.AdHourWeightService;
import com.sungan.ad.vo.AdHourWeightVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdHourWeightServiceImpl implements AdHourWeightService,ApplicationContextAware,InitializingBean {
	private static final Log log = LogFactory.getLog(AdHourWeightServiceImpl.class);
	@Autowired
	private AdHourWeightDAO adHourWeightDAO;
	@Autowired
   private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	/**
	 * 初始化权重值 
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		AdHourWeightService bean = applicationContext.getBean(AdHourWeightService.class);
		List<AdHourWeightVo> query = bean.query();
		if(query!=null&&query.size()>0){
			log.info("权重配置已经初始化...");
			return;
		}
		AdHourWeight weight23 = new AdHourWeight();
		weight23.setHour(23);
		weight23.setThrowRate(8);
		weight23.setWght(40);
		bean.insert(weight23);
		AdHourWeight weight22 = new AdHourWeight();
		weight22.setHour(22);
		weight22.setThrowRate(8);
		weight22.setWght(20);
		bean.insert(weight22);
		AdHourWeight weight21 = new AdHourWeight();
		weight21.setHour(21);
		weight21.setThrowRate(8);
		weight21.setWght(18);
		bean.insert(weight21);
		AdHourWeight weight20 = new AdHourWeight();
		weight20.setHour(20);
		weight20.setThrowRate(8);
		weight20.setWght(12);
		bean.insert(weight20);
		AdHourWeight weight19 = new AdHourWeight();
		weight19.setHour(19);
		weight19.setThrowRate(8);
		weight19.setWght(10);
		bean.insert(weight19);
		AdHourWeight weight18 = new AdHourWeight();
		weight18.setHour(18);
		weight18.setThrowRate(8);
		weight18.setWght(12);
		bean.insert(weight18);
		AdHourWeight weight17 = new AdHourWeight();
		weight17.setHour(17);
		weight17.setThrowRate(8);
		weight17.setWght(12);
		bean.insert(weight17);
		AdHourWeight weight16 = new AdHourWeight();
		weight16.setHour(16);
		weight16.setThrowRate(8);
		weight16.setWght(12);
		bean.insert(weight16);
		AdHourWeight weight15 = new AdHourWeight();
		weight15.setHour(15);
		weight15.setThrowRate(8);
		weight15.setWght(12);
		bean.insert(weight15);
		AdHourWeight weight14 = new AdHourWeight();
		weight14.setHour(14);
		weight14.setThrowRate(8);
		weight14.setWght(22);
		bean.insert(weight14);
		AdHourWeight weight13 = new AdHourWeight();
		weight13.setHour(13);
		weight13.setThrowRate(8);
		weight13.setWght(32);
		bean.insert(weight13);
		AdHourWeight weight12= new AdHourWeight();
		weight12.setHour(12);
		weight12.setThrowRate(8);
		weight12.setWght(28);
		bean.insert(weight12);
		AdHourWeight weight11 = new AdHourWeight();
		weight11.setHour(11);
		weight11.setThrowRate(8);
		weight11.setWght(24);
		bean.insert(weight11);
		AdHourWeight weight10 = new AdHourWeight();
		weight10.setHour(10);
		weight10.setThrowRate(8);
		weight10.setWght(16);
		bean.insert(weight10);
		AdHourWeight weight9 = new AdHourWeight();
		weight9.setHour(9);
		weight9.setThrowRate(8);
		weight9.setWght(12);
		bean.insert(weight9);
		AdHourWeight weight8 = new AdHourWeight();
		weight8.setHour(8);
		weight8.setThrowRate(8);
		weight8.setWght(10);
		bean.insert(weight8);
		AdHourWeight weight7 = new AdHourWeight();
		weight7.setHour(7);
		weight7.setThrowRate(8);
		weight7.setWght(8);
		bean.insert(weight7);
		AdHourWeight weight6 = new AdHourWeight();
		weight6.setHour(6);
		weight6.setThrowRate(8);
		weight6.setWght(5);
		bean.insert(weight6);
		AdHourWeight weight5 = new AdHourWeight();
		weight5.setHour(5);
		weight5.setThrowRate(8);
		weight5.setWght(5);
		bean.insert(weight5);
		AdHourWeight weight4= new AdHourWeight();
		weight4.setHour(4);
		weight4.setThrowRate(8);
		weight4.setWght(5);
		bean.insert(weight4);
		AdHourWeight weight3 = new AdHourWeight();
		weight3.setHour(3);
		weight3.setThrowRate(8);
		weight3.setWght(5);
		bean.insert(weight3);
		AdHourWeight weight2 = new AdHourWeight();
		weight2.setHour(2);
		weight2.setThrowRate(8);
		weight2.setWght(20);
		bean.insert(weight2);
		AdHourWeight weight1 = new AdHourWeight();
		weight1.setHour(1);
		weight1.setThrowRate(8);
		weight1.setWght(60);
		bean.insert(weight1);
		AdHourWeight weight0 = new AdHourWeight();
		weight0.setHour(0);
		weight0.setThrowRate(8);
		weight0.setWght(60);
		bean.insert(weight0);
	}
	

	@Override
	public Long insert(AdHourWeight client) {
		Long insert = (Long) adHourWeightDAO.insert(client);
		return insert;
	}


	@Override
	public void update(AdHourWeight client) {
		if(client.getId()==null){
			throw new AdRuntimeException("id为空");
		}
		AdHourWeight find = this.adHourWeightDAO.find(client.getId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		AdCommonsUtil.beanCopyWithoutNull(client, find);
		 adHourWeightDAO.update(find);
	}


	@Override
	public void delete(Long id) {
		AdHourWeight find = this.adHourWeightDAO.find(id);
		if(find!=null){
			adHourWeightDAO.delete(find);
		}	
	}


	@Override
	public List<AdHourWeightVo> query() {
		List<AdHourWeight> query = (List<AdHourWeight>) adHourWeightDAO.query();
		 List<AdHourWeightVo> parseToVoList = AnnotationParser.parseToVoList(AdHourWeightVo.class,query);
		return parseToVoList;
	}


	@Override
	public List<AdHourWeightVo> query(AdHourWeight condition) {
		List<AdHourWeight> query = (List<AdHourWeight>) adHourWeightDAO.query(condition);
		 List<AdHourWeightVo> parseToVoList = AnnotationParser.parseToVoList(AdHourWeightVo.class,query);
		return parseToVoList;
	}


	@Override
	public AdPager<AdHourWeightVo> queryPager(AdHourWeight condition, int pageIndex, int rows) {
		AdPager<AdHourWeight> queryPage = adHourWeightDAO.queryPage(condition, pageIndex, rows);
		List<AdHourWeight> result = queryPage.getRows();
		List<AdHourWeightVo> parseToVoList = AnnotationParser.parseToVoList(AdHourWeightVo.class,result);
		AdPager<AdHourWeightVo> resultVo = new AdPager<AdHourWeightVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	


	public AdHourWeightDAO getAdHourWeightDAO() {
		return adHourWeightDAO;
	}

	public void setAdHourWeightDAO(AdHourWeightDAO adHourWeightDAO) {
		this.adHourWeightDAO = adHourWeightDAO;
	}


}

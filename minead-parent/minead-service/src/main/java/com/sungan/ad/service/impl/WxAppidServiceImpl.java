package com.sungan.ad.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.dao.base.WxAppidDAO;
import com.sungan.ad.domain.WxAppid;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.service.WxAppidService;
import com.sungan.ad.vo.WxAppidVo;

/**
 * 说明:
 * 
 * @author zhangyf18255 <zhangyf18255@hundsun.com>
 * @date 2016年12月28日 下午7:57:52
 * @version V1.1
 */
@Service
public class WxAppidServiceImpl  implements WxAppidService{
	@Autowired
	private WxAppidDAO wxAppidDAO;
	private static final Log log = LogFactory.getLog(WxAppidServiceImpl.class);
	
	public Long insert(WxAppid wx){
		if(StringUtils.isBlank(wx.getAppId())){
			throw new AdRuntimeException("appid不能为空");
		}
		WxAppid query = wxAppidDAO.query(wx.getAppId());
		if(query!=null){
			throw new AdRuntimeException("appid已经存在，不可重复添加");
		}
		Long insert = (Long) wxAppidDAO.insert(wx);
		return insert;
	}
	
	public List<WxAppidVo> queryAall(){
		List<WxAppid> query = (List<WxAppid>) wxAppidDAO.query();
		List<WxAppidVo> queryVo  = new ArrayList<WxAppidVo>();
		SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-hh HH:mm:ss");
		if(query!=null){
			for(WxAppid pi:query){
				try {
					WxAppidVo vo = new WxAppidVo();
					BeanUtils.copyProperties(vo, pi);
					String updateTimeStr = "";
					if(pi.getUpdateTime()!=null){
						updateTimeStr = fromat.format(pi.getUpdateTime());
					}
					vo.setUpdateTimeStr(updateTimeStr );
					String createTimeStr = "";
					if(pi.getCreateTime()!=null){
						createTimeStr = fromat.format(pi.getCreateTime());
					}
					vo.setCreateTimeStr(createTimeStr );
					queryVo.add(vo);
				} catch (Exception e) {
					log.error("",e);
				}
			}
		}
		return queryVo;
	}
	

	public WxAppidDAO getWxAppidDAO() {
		return wxAppidDAO;
	}

	public void setWxAppidDAO(WxAppidDAO wxAppidDAO) {
		this.wxAppidDAO = wxAppidDAO;
	}
	
}

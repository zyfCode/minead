package com.sungan.ad.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.dao.AdPager;
import com.sungan.ad.dao.base.AdClientDAO;
import com.sungan.ad.dao.base.AdClientIpDAO;
import com.sungan.ad.dao.base.AdTaskDAO;
import com.sungan.ad.domain.AdClient;
import com.sungan.ad.domain.AdClientIp;
import com.sungan.ad.domain.AdTask;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.expand.common.bean.InitTaskConnectRequest;
import com.sungan.ad.expand.common.bean.InitTaskConnectResponse;
import com.sungan.ad.expand.common.bean.TaskInfo;
import com.sungan.ad.expand.common.bean.TaskRequest;
import com.sungan.ad.expand.common.bean.TaskResonse;
import com.sungan.ad.expand.common.bean.TaskResonseInfo;
import com.sungan.ad.service.AdClientService;
import com.sungan.ad.service.cloud.CloudIpManager;
import com.sungan.ad.service.cloud.WYCloudIpManager;
import com.sungan.ad.service.ext.AdTaskManager;
import com.sungan.ad.vo.AdClientIpVo;
import com.sungan.ad.vo.AdClientVo;
import com.sungan.ad.vo.AppTaskVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdClientServiceImpl implements AdClientService{
	@Autowired
	private AdClientDAO adClientDAO;
	@Autowired
	private AdClientIpDAO adClientIpDAO;

	public AdClientDAO getAdClientDAO() {
		return adClientDAO;
	}

	public void setAdClientDAO(AdClientDAO adClientDAO) {
		this.adClientDAO = adClientDAO;
	}

	@Override
	public Long insert(AdClient client) {
		Long insert = (Long) adClientDAO.insert(client);
		return insert;
	}

	public AdClientIpDAO getAdClientIpDAO() {
		return adClientIpDAO;
	}

	public void setAdClientIpDAO(AdClientIpDAO adClientIpDAO) {
		this.adClientIpDAO = adClientIpDAO;
	}

	@Override
	public List<AdClientIpVo> queryIps(Long clientId) {
		AdClientIp t = new AdClientIp();
		t.setClientId(clientId);
		List<AdClientIp> query = (List<AdClientIp>) adClientIpDAO.query(t );
		List<AdClientIpVo> parseToVoList = AnnotationParser.parseToVoList(AdClientIpVo.class, query);
		return parseToVoList;
	}
	
	@Override
	public void update(AdClient client) {
		if(client.getId()==null){
			throw new AdRuntimeException("id为空");
		}
		AdClient find = this.adClientDAO.find(client.getId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		AdCommonsUtil.beanCopyWithoutNull(client, find);
		this.adClientDAO.update(find);
	}

	@Override
	public void delete(Long id) {
		AdClient find = this.adClientDAO.find(id);
		if(find!=null){
		adClientDAO.delete(find);
		}
	}

	@Override
	public List<AdClientVo> query() {
		List<AdClient> query = (List<AdClient>) adClientDAO.query();
		List<AdClientVo> parseToVoList = AnnotationParser.parseToVoList(AdClientVo.class, query);
		return parseToVoList;
	}

	@Override
	public List<AdClientVo> query(AdClient condition) {
		List<AdClient> query = (List<AdClient>) adClientDAO.query(condition);
		List<AdClientVo> parseToVoList = AnnotationParser.parseToVoList(AdClientVo.class, query);
		return parseToVoList;
	}

	@Override
	public AdPager<AdClientVo> queryPager(AdClient condition, int pageIndex, int rows) {
		AdPager<AdClient> queryPage = adClientDAO.queryPage(condition, pageIndex, rows);
		List<AdClient> result = queryPage.getResult();
		List<AdClientVo> parseToVoList = AnnotationParser.parseToVoList(AdClientVo.class,result);
		AdPager<AdClientVo> resultVo = new AdPager<AdClientVo>(pageIndex, rows, queryPage.getCount());
		resultVo.setResult(parseToVoList);
		return resultVo;
	}

	@Override
	public List<AdClientIpVo> queryIps(AdClientIp adClientIp) {
		List<AdClientIp> query = (List<AdClientIp>) adClientIpDAO.query(adClientIp);
		List<AdClientIpVo> parseToVoList = AnnotationParser.parseToVoList(AdClientIpVo.class, query);
		return parseToVoList;
	}

	@Override
	public Long insertIp(AdClientIp adClientIp) {
		Long insert = (Long) adClientIpDAO.insert(adClientIp);
		return insert;
	}

	@Override
	public void updateIp(AdClientIp adClientIp) {
		if (adClientIp.getId() == null) {
			throw new AdRuntimeException("不存在此记录");
		}
		AdClientIp find = adClientIpDAO.find(adClientIp.getId());
		if (find == null) {
			throw new AdRuntimeException("不存在此记录");
		}
		AdCommonsUtil.beanCopyWithoutNull(adClientIp, find);
		
	}

	@Override
	public InitTaskConnectResponse initConnect(InitTaskConnectRequest connectRequest,String currentIp) {
		List<AdClient> query = (List<AdClient>) this.adClientDAO.query();
		AdClient client = null;
		if(query!=null){
			boolean isExist = false;
			for(AdClient ct:query){
				String mac = ct.getMac();
				isExist = this.matchMac(connectRequest.getMac(), mac);
				if(isExist){
					client = ct;
					break;
				}
			}
			//如果当前客户端不存在，新增加
			if(!isExist){
				client = new AdClient();
				client.setCreateTime(new Date());
				client.setCurrentIp(currentIp);
				client.setMac(connectRequest.getMac());
				client.setSysOs(connectRequest.getSysOs());
				client.setName(connectRequest.getSysOs());
				client.setStatus(AdClient.ADCLIENT_STATUS_RUNNING);
				client.setSysOs(connectRequest.getMac());
				Long clientId = (Long) this.adClientDAO.insert(client);
				client.setId(clientId);
				List<AdClientIp> ipsBeanList = new ArrayList<AdClientIp>();
				AdClientIp currentIpBean = new AdClientIp();
				currentIpBean.setCreateTime(new Date());
				currentIpBean.setClientId(clientId);
				currentIpBean.setIp(currentIp);
				currentIpBean.setStatus(AdClientIp.ADCLIENTIP_STATUS_RUNNING);
				currentIpBean.setUpdateTime(new Date());
				ipsBeanList.add(currentIpBean);
				 
				List<String> ips = null;
				if(InitTaskConnectRequest.CLIENT_163.equals(connectRequest.getSource())){
					CloudIpManager  ipManager = new WYCloudIpManager();
					ips = ipManager.getIp(connectRequest.getUserName(), connectRequest.getPwd());
				}else{
					ips = new ArrayList<String>();
					ips.add(currentIp);
				}
				if(ips!=null&&ips.size()>0){
					for(String ip:ips){
						AdClientIp ipBean = new AdClientIp();
						ipBean.setCreateTime(new Date());
						ipBean.setClientId(clientId);
						ipBean.setIp(ip);
						ipBean.setStatus(AdClientIp.ADCLIENTIP_STATUS_RUNNING);
						ipBean.setUpdateTime(new Date());
						ipsBeanList.add(ipBean);
					}
				}
				this.adClientIpDAO.insert(ipsBeanList);
			}
			InitTaskConnectResponse response = new  InitTaskConnectResponse();
			response.setAdClientId(client.getId());
			response.setAdClientIp(client.getCurrentIp());
			response.setAdClientMac(client.getMac());
			return response;
		}
		return null;
	}
	/**
	 * 配置Mac
	 * @return
	 */
	private boolean matchMac(String sourcesMac,String targetMac){
		if(sourcesMac==null||targetMac==null){
			return false;
		}
		String sourcesMacs[] = null;
		if(sourcesMac.contains(",")){
			sourcesMacs = sourcesMac.split(",");
		}else{
			sourcesMacs = new String[]{sourcesMac};
		}
		
		String[] targetMacs = null;
		if(targetMac.contains(",")){
			targetMacs = targetMac.split(",");
		}else{
			targetMacs = new String[]{targetMac};
		}
		
		for(String sMac:sourcesMacs){
			for(String tMac:targetMacs){
				if(sMac.equals(tMac)){
					return true;
				}
			}
		}
		return false;
	}

	@Autowired
	private AdTaskManager adTaskManager;
	@Autowired
	private AdTaskDAO adTaskDAO;
	@Override
	public TaskResonse hearInfo(TaskRequest bean,String currentIp) {
		Long adClientId = bean.getAdClientId();
		AdClient find = this.adClientDAO.find(adClientId);
		if(find==null){
			TaskResonse resonse = new TaskResonse();
			resonse.setAction(TaskResonse.TR_INIT);
			return resonse;
		}else{
			find.setCurrentIp(currentIp);
			find.setPreAccessTime(new Date());
			adClientDAO.update(find);
			List<TaskInfo> info = bean.getInfo();
			if(info!=null&&info.size()>0){
				for(TaskInfo taksif:info){
					adTaskManager.clientTaskInfo(taksif);
				}
			}
			
			AdClientVo clientVo = AnnotationParser.parseToVo(AdClientVo.class, find);
			AppTaskVo task = adTaskManager.getTask(clientVo);
			AdTask adTask = this.adTaskDAO.find(task.getAdTaskid());
			TaskResonse resonse = new TaskResonse();

			List<TaskResonseInfo> resInfos = new ArrayList<TaskResonseInfo>();
			TaskResonseInfo tinfo = new TaskResonseInfo();
			tinfo.setAdClazzName(adTask.getClazzName());
			tinfo.setAdClientId(find.getId());
			tinfo.setAdTaskId(task.getId());
			tinfo.setCount(task.getCount());
			tinfo.setDoneCount(task.getDoneCount());
			tinfo.setIp(currentIp);
			if(tinfo.getDoneCount()!=null&&tinfo.getDoneCount().compareTo(tinfo.getCount())>=0){
				tinfo.setAction(TaskResonse.TR_DESDORY);
			}
			if(!find.getCurrentIp().equals(currentIp)){
				find.setCurrentIp(currentIp);
				this.adClientDAO.update(find);
				AdClientIp condition = new AdClientIp();
				condition.setClientId(find.getId());
				List<AdClientIp> query = (List<AdClientIp>) this.adClientIpDAO.query(condition );
				for(AdClientIp ipBean:query){
					if(ipBean.getStatus().equals(AdClientIp.ADCLIENTIP_STATUS_RUNNING)&&!ipBean.getIp().equals(currentIp)){
						ipBean.setStatus(AdClientIp.ADCLIENTIP_STATUS_INVALID);
						 this.adClientIpDAO.update(ipBean);
					}else if(ipBean.getIp().equals(currentIp)){
						ipBean.setStatus(AdClientIp.ADCLIENTIP_STATUS_RUNNING);
						 this.adClientIpDAO.update(ipBean);
					}
				}
			}
			tinfo.setSerialNo(bean.getSerialNo());
			tinfo.setThrowRate(task.getThrowRate());
			resInfos.add(tinfo);
			resonse.setResInfos(resInfos );
			return resonse;
		}
	}

	public AdTaskManager getAdTaskManager() {
		return adTaskManager;
	}

	public void setAdTaskManager(AdTaskManager adTaskManager) {
		this.adTaskManager = adTaskManager;
	}

	public AdTaskDAO getAdTaskDAO() {
		return adTaskDAO;
	}

	public void setAdTaskDAO(AdTaskDAO adTaskDAO) {
		this.adTaskDAO = adTaskDAO;
	}
}

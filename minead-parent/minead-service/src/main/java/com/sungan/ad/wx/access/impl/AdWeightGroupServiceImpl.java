package com.sungan.ad.wx.access.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.dao.AdPager;
import com.sungan.ad.dao.base.AdClientDAO;
import com.sungan.ad.dao.base.AdHourWeightDAO;
import com.sungan.ad.dao.base.AdWeightGroupDAO;
import com.sungan.ad.domain.AdClient;
import com.sungan.ad.domain.AdHourWeight;
import com.sungan.ad.domain.AdWeightGroup;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.AdWeightGroupService;
import com.sungan.ad.vo.AdWeightGroupVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdWeightGroupServiceImpl implements AdWeightGroupService{
	
	@Autowired
	private AdWeightGroupDAO adWeightGroupDAO;
	@Autowired
	private AdClientDAO clientDao;
	@Autowired
	private AdHourWeightDAO adHourWeightDAO;

	public AdHourWeightDAO getAdHourWeightDAO() {
		return adHourWeightDAO;
	}

	public void setAdHourWeightDAO(AdHourWeightDAO adHourWeightDAO) {
		this.adHourWeightDAO = adHourWeightDAO;
	}

	public AdClientDAO getClientDao() {
		return clientDao;
	}

	public void setClientDao(AdClientDAO clientDao) {
		this.clientDao = clientDao;
	}

	public AdWeightGroupDAO getAdWeightGroupDAO() {
		return adWeightGroupDAO;
	}

	public void setAdWeightGroupDAO(AdWeightGroupDAO adWeightGroupDAO) {
		this.adWeightGroupDAO = adWeightGroupDAO;
	}

	@Override
	public Long insert(AdWeightGroup record) {
		AdWeightGroup t = new AdWeightGroup();
		t.setGroupName(record.getGroupName());
		List<AdWeightGroup> recoreList = (List<AdWeightGroup>) adWeightGroupDAO.query(t );
		if(recoreList!=null&&recoreList.size()>0){
			throw new AdRuntimeException("节点名字不能重复");
		}
		if(record.getParentId()!=null){
			AdWeightGroup find = adWeightGroupDAO.find(record.getParentId());
			AdClient client = new AdClient();
			client.setGroupId(find.getId());
			Collection<AdClient> query = clientDao.query(client );
			if(query!=null&&query.size()>0){
				throw new AdRuntimeException(find.getGroupName()+"已使用，不允许添加子节点");
			}
			AdHourWeight adWeightCondition = new AdHourWeight();
			adWeightCondition.setGroupId(find.getId());
			Collection<AdHourWeight> adHourWeight = adHourWeightDAO.query(adWeightCondition );
			if(adHourWeight!=null&&adHourWeight.size()>0){
				throw new AdRuntimeException(find.getGroupName()+"已被权重重表使用，不允许添加子节点");
			}
			if(find.getIsLeaf()==null||AdWeightGroup.ISLEAF.equals(find.getIsLeaf())){
				find.setIsLeaf(AdWeightGroup.ISNOTLEAF);
			   adWeightGroupDAO.update(find);
			}
		}
		record.setIsLeaf(AdWeightGroup.ISLEAF);
		Long insert = (Long) adWeightGroupDAO.insert(record);
		return insert;
	}

	@Override
	public List<AdWeightGroupVo> queryList(AdWeightGroup condition) {
		List<AdWeightGroup> query = (List<AdWeightGroup>) adWeightGroupDAO.query(condition);
		 List<AdWeightGroupVo> parseToVoList = AnnotationParser.parseToVoList(AdWeightGroupVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(Long id) {
		AdWeightGroup find = this.adWeightGroupDAO.find(id);
		if (find != null) {
			AdClient client = new AdClient();
			client.setGroupId(find.getId());
			Collection<AdClient> query = clientDao.query(client );
			if(query!=null&&query.size()>0){
				throw new AdRuntimeException(find.getGroupName()+"已使用，不允许删除");
			}
			AdWeightGroup t = new AdWeightGroup();
			t.setParentId(find.getId());
			List<AdWeightGroup> recoreList = (List<AdWeightGroup>) adWeightGroupDAO.query(t );
			if(recoreList!=null&&recoreList.size()>0){
				throw new AdRuntimeException(find.getGroupName()+"存在子节点，不允许删除");
			}
			AdHourWeight adWeightCondition = new AdHourWeight();
			adWeightCondition.setGroupId(find.getId());
			Collection<AdHourWeight> adHourWeight = adHourWeightDAO.query(adWeightCondition );
			if(adHourWeight!=null&&adHourWeight.size()>0){
				throw new AdRuntimeException(find.getGroupName()+"已被权重重表使用，不允许添加子节点");
			}
			//查询同一层的节点
			adWeightGroupDAO.delete(find);
			AdWeightGroup groupCondition = new AdWeightGroup();
			groupCondition.setParentId(find.getParentId());
			Collection<AdWeightGroup> query2 = adWeightGroupDAO.query(groupCondition );
			if(query2==null||query2.size()<1){
				AdWeightGroup find2 = adWeightGroupDAO.find(find.getParentId());
				find2.setIsLeaf(AdWeightGroup.ISLEAF);
				adWeightGroupDAO.update(find2);
			}
		}
	}
	
	@Override
	public AdWeightGroupVo find(Long id) {
		AdWeightGroup find = adWeightGroupDAO.find(id);
		if(find==null){
			return null;
		}
		AdWeightGroupVo parseToVo = AnnotationParser.parseToVo(AdWeightGroupVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<AdWeightGroupVo> queryPager(AdWeightGroup condition, int pageIndex, int rows) {
		AdPager<AdWeightGroup> queryPage = adWeightGroupDAO.queryPage(condition, pageIndex, rows);
		List<AdWeightGroup> result = queryPage.getRows();
		List<AdWeightGroupVo> parseToVoList = AnnotationParser.parseToVoList(AdWeightGroupVo.class, result);
		AdPager<AdWeightGroupVo> resultVo = new AdPager<AdWeightGroupVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(AdWeightGroup record) {
		if(record.getId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AdWeightGroup find = adWeightGroupDAO.find(record.getId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		AdWeightGroup t = new AdWeightGroup();
		t.setGroupName(record.getGroupName());
		List<AdWeightGroup> recoreList = (List<AdWeightGroup>) adWeightGroupDAO.query(t );
		if(recoreList!=null&&recoreList.size()>0){
			for(AdWeightGroup gruop:recoreList){
				if(gruop.getId().equals(find.getId())){
					continue;
				}
				throw new AdRuntimeException("节点名字不能重复");
			}
		}
		//只能设置一个默认的组
		t = new AdWeightGroup();
		t.setIsDefault(AdWeightGroup.ISDEFAULT);
		List<AdWeightGroup> query = (List<AdWeightGroup>) adWeightGroupDAO.query(t);
		if(query!=null&&query.size()>0){
			for(AdWeightGroup gruop:recoreList){
				if(gruop.getId().equals(find.getId())){
					continue;
				}
				throw new AdRuntimeException("已存在默认组:"+gruop.getGroupName());
			}
			
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
 		adWeightGroupDAO.update(find);
		
	}

	@Override
	public List<AdWeightGroupVo> queryRoot() {
		List<AdWeightGroup> list = this.adWeightGroupDAO.queryRoot();
		List<AdWeightGroupVo> parseToVoList = AnnotationParser.parseToVoList(AdWeightGroupVo.class, list);
		return parseToVoList;
	}
}






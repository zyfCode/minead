package com.sungan.ad.dao;

import java.io.Serializable;
import java.util.Collection;

public interface DAO<T>{
	public static final String ORDERTYPE_DESC="desc";
	public static final String ORDERTYPE_ASC="asc";
	
	void commit();
	/**
	 * 统计记录总数
	 * @return
	 */
	long count();
	/**
	 * 插入一条记录
	 * @param t
	 * @return
	 */
	Serializable insert(T t);
	/**
	 * 删除一条记录
	 * @param t
	 */
	void delete(T t);
	/**
	 * 删除多条记录
	 * @param collection
	 */
	void delete(Collection<T> collection);
	/**
	 * 增加多条记录
	 * @param collection
	 */
	int insert(Collection<T> collection);
	/**
	 * 查询
	 * @param beginIndex 开始索引
	 * @param num 每页显示数据的数量
	 * @param orderclum 排序列 ，如果此列为Null则不进行排序
	 * @param orderType 排序类型
	 * @return
	 */
	Collection<T> query(int beginIndex,int num,String orderclum,OrderType orderType);
	/**
	 * 查询所有记录
	 * @return
	 */
	Collection<T> query();
	
	/**
	 *查询一条记录
	 * @param id
	 * @return
	 */
	T find(Serializable id);
	/**
	 *查询一条记录通过load方式
	 * @param id
	 * @return
	 */
	T findByLoad(Serializable id);
	/**
	 * 更新一条记录
	 * @param t
	 */
	void update(T t);
}











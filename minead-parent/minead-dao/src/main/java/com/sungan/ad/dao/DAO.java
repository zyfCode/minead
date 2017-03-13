package com.sungan.ad.dao;

import java.io.Serializable;
import java.util.Collection;

public interface DAO<T>{
	public static final String ORDERTYPE_DESC="desc";
	public static final String ORDERTYPE_ASC="asc";
	/**
	 * 分页查询，并根据字段进行排序
	 * @param t
	 * @param pageIndex
	 * @param rows
	 * @param orderType
	 * @param orderColumn
	 * @return
	 */
	AdPager<T> queryPageInOrder(T t,int pageIndex,int rows,OrderType orderType,String orderColumn);
	/**
	 * 查询某个字段为空
	 * @param t
	 * @param proNames
	 * @return
	 */
	Collection<T> queryIsEmpty(String... proNames);
	/**
	 * 分布查询
	 * @param t
	 * @param pageIndex
	 * @param rows
	 * @return
	 */
	AdPager<T> queryPage(T t,int pageIndex,int rows);
	
	/**
	 * 查询分页结果，没有模糊查询
	 * @param t
	 * @param pageIndex
	 * @param rows
	 * @return
	 */
	AdPager<T> queryPageEq(T t,int pageIndex,int rows);
	/**
	 * 根据条件查询记录数 
	 * @param t
	 * @return
	 */
	Long count(T t);
	void commit();
	/**
	 *  根据条件查询
	 * @param t
	 * @return
	 */
	 Collection<T> query(T t);
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











package com.sungan.ad.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明:
 */
public class AdPager<T> {
	private List<T> rows = new ArrayList<T>();
	private int pageNo;  //第n页
	private int total;  //总条算
	private int pages;  //总页数
	private int pageSize;  //每行显示的数据
	
	
	
	public AdPager(int pageNo, int pageSize, int total) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.total = total;
		pages = total/pageSize;
		if(total%pageSize>0){
			pages++;
		}
	}

	
	
	
	public void setRows(List<T> rows) {
		this.rows = rows;
	}




	public List<T> getRows() {
		return rows;
	}




	public int getPageNo() {
		return pageNo;
	}




	public int getTotal() {
		return total;
	}




	public int getPages() {
		return pages;
	}




	public int getPageSize() {
		return pageSize;
	}




	public int getCountPagers() {
		return pageSize;
	}


	public void setCountPagers(int countPagers) {
		this.pageSize = countPagers;
	}
}

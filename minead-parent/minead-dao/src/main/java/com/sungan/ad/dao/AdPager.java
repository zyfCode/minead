package com.sungan.ad.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明:
 */
public class AdPager<T> {
	private List<T> result = new ArrayList<T>();
	private int pageIndex;
	private int rows; 
	private int count;
	private int nextIndex;
	private int preIndex;
	private int countPagers;
	
	
	public AdPager(int pageIndex, int rows, int count) {
		super();
		this.pageIndex = pageIndex;
		this.rows = rows;
		this.count = count;
		if(pageIndex>0){
			this.preIndex = pageIndex-1;
		}
		countPagers = count/rows;
		if(count%rows>0){
			countPagers++;
		}
		this.nextIndex = this.pageIndex+1;
		if(nextIndex>countPagers){
			this.nextIndex=countPagers;
		}
	}

	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNextIndex() {
		return nextIndex;
	}
	public void setNextIndex(int nextIndex) {
		this.nextIndex = nextIndex;
	}
	public int getPreIndex() {
		return preIndex;
	}
	public int getCountPagers() {
		return countPagers;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public void setPreIndex(int preIndex) {
		this.preIndex = preIndex;
	}

	public void setCountPagers(int countPagers) {
		this.countPagers = countPagers;
	}
}

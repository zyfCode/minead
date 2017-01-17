package com.sungan.ad.dao;

public enum OrderType {
	DESC("DESC"), ASC("ASC");
	String type = null;

	OrderType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}
}

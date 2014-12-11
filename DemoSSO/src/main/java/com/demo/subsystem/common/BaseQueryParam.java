package com.demo.subsystem.common;

import java.io.Serializable;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 查询参数基类 </p> 
 * <p>Author:aaron</p>
 */
public class BaseQueryParam implements Serializable {
	private static final long serialVersionUID = -169314165016407073L;
	
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	
	private int start;
	private int size;
	private String sort;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}

}

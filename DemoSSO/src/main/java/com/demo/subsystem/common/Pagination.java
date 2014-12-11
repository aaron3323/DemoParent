package com.demo.subsystem.common;

import java.io.Serializable;
import java.util.List;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 分页查询结果 </p> 
 * <p>Author:aaron</p>
 * @param <T>
 */
public class Pagination<T> implements Serializable {

	private static final long serialVersionUID = -707510216915120786L;

	/**
	 * 当前页码
	 */
	private int number;
	
	/**
	 * 总页数
	 */
	private int totalPages;
	
	/**
	 * 总记录数
	 */
	private int totalElements;

	/**
	 * 起始记录数
	 */
	private int elementsNo;
	
	/**
	 * 查询记录条数
	 */
	private int limit;
	
	/**
	 * 查询结果
	 */
	private List<T> content;
	
	public Pagination(){}
	
	public Pagination(List<T> content ,int pageNo,int pageSize,int count) {
		this.totalElements = count;
		this.content = content;
		this.elementsNo = (pageNo - 1) * pageSize + 1;
		this.limit = pageSize;
		this.number = pageNo;
		
		if(totalElements > 0){
			if(totalElements % pageSize == 0){
				totalPages = totalElements / pageSize;
			}else{
				totalPages = (totalElements / pageSize) + 1;
			}
		}
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getElementsNo() {
		return elementsNo;
	}

	public void setElementsNo(int elementsNo) {
		this.elementsNo = elementsNo;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}

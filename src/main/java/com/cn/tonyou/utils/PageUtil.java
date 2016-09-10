package com.cn.tonyou.utils;

import java.util.List;

/**
 * 使用方法
 * 
 * 
 * 得到总记录数
 * int allrows = 调用方法;  
 * 得到总页数
 * int totalpage = pageBean.gettotalpage(pagesize, allrows); // pagesize 每页条数   
 * 得到当前页
 * int currentpage = pageBean.getcurrentpage(page);
 * 得到开始条数
 * int offset = pageBean.getcurrentpageoffset(pagesize, currentpage);
 * @author Administrator
 *
 */
public class PageUtil {
	private List<?> list;
	private int allrows;//总纪录数
	private int totalpage;//总页数
	private int currentpage;//当前页
	/**
	 * 得到总页数
	 * @param pagesize 每页记录数
	 * @param allrows	总页数
	 * @return
	 */
	public int gettotalpage(int pagesize,int allrows){
		int totalpage = (allrows % pagesize == 0)?(allrows / pagesize):(allrows / pagesize)+1;
		return totalpage;
	} 
	/**
	 * 得到当前开始记录号
	 * @param pagesize 每页记录数
	 * @param currentpage 当前页
	 * @return
	 */	
	public int getcurrentpageoffset(int pagesize,int currentpage){
		int offset = pagesize*(currentpage - 1);
		return offset;
	}
	/**
	 * 得到当前页，如果为0，则开始第一页，否则为当前页
	 * @param page
	 * @return
	 */
	public int getcurrentpage(int page){
		int currentpage = (page == 0) ? 1:page;
		return currentpage;
	}
	
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public int getAllrows() {
		return allrows;
	}
	public void setAllrows(int allrows) {
		this.allrows = allrows;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	
}

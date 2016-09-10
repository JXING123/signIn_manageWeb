package com.cn.tonyou.utils;

import java.util.List;

/**
 * ʹ�÷���
 * 
 * 
 * �õ��ܼ�¼��
 * int allrows = ���÷���;  
 * �õ���ҳ��
 * int totalpage = pageBean.gettotalpage(pagesize, allrows); // pagesize ÿҳ����   
 * �õ���ǰҳ
 * int currentpage = pageBean.getcurrentpage(page);
 * �õ���ʼ����
 * int offset = pageBean.getcurrentpageoffset(pagesize, currentpage);
 * @author Administrator
 *
 */
public class PageUtil {
	private List<?> list;
	private int allrows;//�ܼ�¼��
	private int totalpage;//��ҳ��
	private int currentpage;//��ǰҳ
	/**
	 * �õ���ҳ��
	 * @param pagesize ÿҳ��¼��
	 * @param allrows	��ҳ��
	 * @return
	 */
	public int gettotalpage(int pagesize,int allrows){
		int totalpage = (allrows % pagesize == 0)?(allrows / pagesize):(allrows / pagesize)+1;
		return totalpage;
	} 
	/**
	 * �õ���ǰ��ʼ��¼��
	 * @param pagesize ÿҳ��¼��
	 * @param currentpage ��ǰҳ
	 * @return
	 */	
	public int getcurrentpageoffset(int pagesize,int currentpage){
		int offset = pagesize*(currentpage - 1);
		return offset;
	}
	/**
	 * �õ���ǰҳ�����Ϊ0����ʼ��һҳ������Ϊ��ǰҳ
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

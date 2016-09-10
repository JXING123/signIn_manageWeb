package com.cn.tonyou.pojo;

import java.util.List;

public class Echarts implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ClassInfo> axis;	//老师姓名
	private List<String[]> series;	//教室到勤率和寝室到寝率
	 
	 
	public List<ClassInfo> getAxis() {
		return axis;
	}
	public void setAxis(List<ClassInfo> axis) {
		this.axis = axis;
	}
	public List<String[]> getSeries() {
		return series;
	}
	public void setSeries(List<String[]> series) {
		this.series = series;
	}
	 
	 
}

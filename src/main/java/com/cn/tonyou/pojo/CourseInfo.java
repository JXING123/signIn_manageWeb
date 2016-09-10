package com.cn.tonyou.pojo;
/**
 * CourseInfo entity. @author 课程表师徒类
 */

public class CourseInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String courseId;
	private SubjectInfo subject;//科目
	private String week;//
	private String schoolBuilding;//教学楼
	private String classRoom;//教室
	private String weekNext;//周次
	private String setSuji;//节次（上课的节数）
	private String startTime;
	private String endTime;
	private String wifiName;
	private String wifiRouteId;//wifi唯一标识
	private ClassInfo classes;//班级
	private SysUserInfo sysUser; 

	// Constructors

	/** default constructor */
	public CourseInfo() {
	}


	public String getCourseId() {
		return courseId;
	}


	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}


	public SubjectInfo getSubject() {
		return subject;
	}


	public void setSubject(SubjectInfo subject) {
		this.subject = subject;
	}


	public String getWeek() {
		return week;
	}


	public void setWeek(String week) {
		this.week = week;
	}


	public String getSchoolBuilding() {
		return schoolBuilding;
	}


	public void setSchoolBuilding(String schoolBuilding) {
		this.schoolBuilding = schoolBuilding;
	}


	public String getClassRoom() {
		return classRoom;
	}


	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}


	public String getWeekNext() {
		return weekNext;
	}


	public void setWeekNext(String weekNext) {
		this.weekNext = weekNext;
	}


	public String getSetSuji() {
		return setSuji;
	}


	public void setSetSuji(String setSuji) {
		this.setSuji = setSuji;
	}

	 


	public String getWifiName() {
		return wifiName;
	}
	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public void setWifiName(String wifiName) {
		this.wifiName = wifiName;
	}


	public String getWifiRouteId() {
		return wifiRouteId;
	}


	public void setWifiRouteId(String wifiRouteId) {
		this.wifiRouteId = wifiRouteId;
	}


	public ClassInfo getClasses() {
		return classes;
	}


	public void setClasses(ClassInfo classes) {
		this.classes = classes;
	}


	public SysUserInfo getSysUser() {
		return sysUser;
	}


	public void setSysUser(SysUserInfo sysUser) {
		this.sysUser = sysUser;
	}


	
	
	
}
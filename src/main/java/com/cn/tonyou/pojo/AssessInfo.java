package com.cn.tonyou.pojo;

import java.util.Date;

/**
 * AssessInfo entity. @author MyEclipse Persistence Tools
 */

public class AssessInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String assessId;
	private String assesscontent;
	private SysUserInfo sysUser;
	private Date assessDate;
	private StuInfo stu;
	private CourseInfo clocking;

	// Constructors

	/** default constructor */
	public AssessInfo() {
	}

	public String getAssessId() {
		return assessId;
	}

	public void setAssessId(String assessId) {
		this.assessId = assessId;
	}

	public String getAssesscontent() {
		return assesscontent;
	}

	public void setAssesscontent(String assesscontent) {
		this.assesscontent = assesscontent;
	}

	public SysUserInfo getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUserInfo sysUser) {
		this.sysUser = sysUser;
	}

	public Date getAssessDate() {
		return assessDate;
	}

	public void setAssessDate(Date assessDate) {
		this.assessDate = assessDate;
	}

	public StuInfo getStu() {
		return stu;
	}

	public void setStu(StuInfo stu) {
		this.stu = stu;
	}

	public CourseInfo getClocking() {
		return clocking;
	}

	public void setClocking(CourseInfo clocking) {
		this.clocking = clocking;
	}


	
}
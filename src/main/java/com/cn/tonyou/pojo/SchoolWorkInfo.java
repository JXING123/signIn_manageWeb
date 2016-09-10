package com.cn.tonyou.pojo;

import java.util.Date;

/**
 * SchoolworkInfo entity. @author 作业表实体类
 */

public class SchoolWorkInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String schoolWorkId;//作业编号
	private SysUserInfo sysUser;
	private String schoolWorkName;
	private String upworkId;//上传作业编号
	private String questionIds;//所用到的题目编号以‘,’隔开
	private String stuIds;//做作业的学生
	private Date startWorkTime;
	private Date endWorkTime;
	private Double scoreSum;
	private String workDesc;

	// Constructors

	/** default constructor */
	public SchoolWorkInfo() {
	}

	public String getSchoolWorkId() {
		return schoolWorkId;
	}

	public void setSchoolWorkId(String schoolWorkId) {
		this.schoolWorkId = schoolWorkId;
	}

	public SysUserInfo getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUserInfo sysUser) {
		this.sysUser = sysUser;
	}

	public String getSchoolWorkName() {
		return schoolWorkName;
	}

	public void setSchoolWorkName(String schoolWorkName) {
		this.schoolWorkName = schoolWorkName;
	}

	public String getUpworkId() {
		return upworkId;
	}

	public void setUpworkId(String upworkId) {
		this.upworkId = upworkId;
	}

	public String getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(String questionIds) {
		this.questionIds = questionIds;
	}

	public String getStuIds() {
		return stuIds;
	}

	public void setStuIds(String stuIds) {
		this.stuIds = stuIds;
	}

	public Date getStartWorkTime() {
		return startWorkTime;
	}

	public void setStartWorkTime(Date startWorkTime) {
		this.startWorkTime = startWorkTime;
	}

	public Date getEndWorkTime() {
		return endWorkTime;
	}

	public void setEndWorkTime(Date endWorkTime) {
		this.endWorkTime = endWorkTime;
	}

	public Double getScoreSum() {
		return scoreSum;
	}

	public void setScoreSum(Double scoreSum) {
		this.scoreSum = scoreSum;
	}

	public String getWorkDesc() {
		return workDesc;
	}

	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}


}
package com.cn.tonyou.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * DepartmentInfo entity. @author 系别表实体类
 */

public class DepartmentInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String departmentId;//系别编号
	private String deprtmentName;
	private String deprtmentDesc;
	private SchoolInfo school;//属于哪个学校的
	private Set<ClassInfo> classInfo = new HashSet<ClassInfo>();
	// Constructors

	/** default constructor */
	public DepartmentInfo() {
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDeprtmentName() {
		return deprtmentName;
	}

	public void setDeprtmentName(String deprtmentName) {
		this.deprtmentName = deprtmentName;
	}

	public String getDeprtmentDesc() {
		return deprtmentDesc;
	}

	public void setDeprtmentDesc(String deprtmentDesc) {
		this.deprtmentDesc = deprtmentDesc;
	}

	public SchoolInfo getSchool() {
		return school;
	}

	public void setSchool(SchoolInfo school) {
		this.school = school;
	}

	public Set<ClassInfo> getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(Set<ClassInfo> classInfo) {
		this.classInfo = classInfo;
	}
	
	

}
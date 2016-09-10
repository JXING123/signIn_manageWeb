package com.cn.tonyou.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * ClassInfo entity. @author 班级表实体类
 */

public class ClassInfo implements java.io.Serializable {

	// Fields

	/**-
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String classId;
	private String className;
	private SysUserInfo sysUser;
	private DepartmentInfo department;//系别实体类
	private String classYear;//年份
	private Set<DormInfo> dormInfo = new HashSet<DormInfo>();
	

	// Constructors

	/** default constructor */
	public ClassInfo() {
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	
	public SysUserInfo getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUserInfo sysUser) {
		this.sysUser = sysUser;
	}

	public DepartmentInfo getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentInfo department) {
		this.department = department;
	}

	public String getClassYear() {
		return classYear;
	}

	public void setClassYear(String classYear) {
		this.classYear = classYear;
	}

	public Set<DormInfo> getDormInfo() {
		return dormInfo;
	}

	public void setDormInfo(Set<DormInfo> dormInfo) {
		this.dormInfo = dormInfo;
	}

	 
	

}
package com.cn.tonyou.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * SysUserInfo entity. @author 管理员表实体类
 */

public class SysUserInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sysUserId;
	private String sysUserName;
	private String sysUserTel;
	private String sysUserPassWord;
	private String sysUserSex;
	private int sysUserAge;
	private String sysUserAddress;
	private ClassInfo classes;
	private String lastLoginTime;
	private String createTime;
	private String sysUserState;
	private int loginCount;
	private SchoolInfo school;

	
	/**
	 * 一个用户对应多个角色
	 */
	private Set<SysRoleInfo> sysRoleInfos = new HashSet<SysRoleInfo>();
	// Constructors

	/** default constructor */
	public SysUserInfo() {
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	public String getSysUserTel() {
		return sysUserTel;
	}

	public void setSysUserTel(String sysUserTel) {
		this.sysUserTel = sysUserTel;
	}

	public String getSysUserPassWord() {
		return sysUserPassWord;
	}

	public void setSysUserPassWord(String sysUserPassWord) {
		this.sysUserPassWord = sysUserPassWord;
	}

	public String getSysUserSex() {
		return sysUserSex;
	}

	public void setSysUserSex(String sysUserSex) {
		this.sysUserSex = sysUserSex;
	}

	public int getSysUserAge() {
		return sysUserAge;
	}

	public void setSysUserAge(int sysUserAge) {
		this.sysUserAge = sysUserAge;
	}

	public String getSysUserAddress() {
		return sysUserAddress;
	}

	public void setSysUserAddress(String sysUserAddress) {
		this.sysUserAddress = sysUserAddress;
	}

	public ClassInfo getClasses() {
		return classes;
	}

	public void setClasses(ClassInfo classes) {
		this.classes = classes;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getSysUserState() {
		return sysUserState;
	}

	public void setSysUserState(String sysUserState) {
		this.sysUserState = sysUserState;
	}

	

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public SchoolInfo getSchool() {
		return school;
	}

	public void setSchool(SchoolInfo school) {
		this.school = school;
	}

	public Set<SysRoleInfo> getSysRoleInfos() {
		return sysRoleInfos;
	}

	public void setSysRoleInfos(Set<SysRoleInfo> sysRoleInfos) {
		this.sysRoleInfos = sysRoleInfos;
	}

	
}
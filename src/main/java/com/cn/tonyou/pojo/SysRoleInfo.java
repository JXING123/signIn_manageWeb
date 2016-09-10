package com.cn.tonyou.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SysRoleInfo entity. @author 角色表实体类
 */

public class SysRoleInfo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sysRoleId;
	private String sysRoleName;
	private String sysRoleDesc;
	private Date createRoleTime;

	private String insertCreateRoleTime;
	/**
	 * 一个角色对应多个权限
	 */
	private Set<SysAuthInfo> sysAuthInfos = new HashSet<SysAuthInfo>();
	/**
	 * 一个角色对应多个用户
	 */
	private Set<SysUserInfo> sysUserInfos = new HashSet<SysUserInfo>();
	// Constructors

	/** default constructor */
	public SysRoleInfo() {
	}

	

	public String getSysRoleId() {
		return sysRoleId;
	}



	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}



	public String getSysRoleName() {
		return sysRoleName;
	}



	public void setSysRoleName(String sysRoleName) {
		this.sysRoleName = sysRoleName;
	}



	public String getSysRoleDesc() {
		return sysRoleDesc;
	}



	public void setSysRoleDesc(String sysRoleDesc) {
		this.sysRoleDesc = sysRoleDesc;
	}



	public Date getCreateRoleTime() {
		return createRoleTime;
	}



	public void setCreateRoleTime(Date createRoleTime) {
		this.createRoleTime = createRoleTime;
	}



	public Set<SysAuthInfo> getSysAuthInfos() {
		return sysAuthInfos;
	}



	public void setSysAuthInfos(Set<SysAuthInfo> sysAuthInfos) {
		this.sysAuthInfos = sysAuthInfos;
	}



	public Set<SysUserInfo> getSysUserInfos() {
		return sysUserInfos;
	}



	public void setSysUserInfos(Set<SysUserInfo> sysUserInfos) {
		this.sysUserInfos = sysUserInfos;
	}



	public String getInsertCreateRoleTime() {
		return insertCreateRoleTime;
	}



	public void setInsertCreateRoleTime(String insertCreateRoleTime) {
		this.insertCreateRoleTime = insertCreateRoleTime;
	}



	
	
	
	
}
package com.cn.tonyou.pojo;

/**
 * SysUserRole entity. 角色权限实体类
 */

public class SysUserRole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String surId;
	private String sysUserId;
	private String sysRoleId;

	// Constructors

	/** default constructor */
	public SysUserRole() {
	}

	public String getSurId() {
		return surId;
	}

	public void setSurId(String surId) {
		this.surId = surId;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

}
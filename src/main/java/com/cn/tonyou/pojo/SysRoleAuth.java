package com.cn.tonyou.pojo;

/**
 * SysRoleAuth entity. @author MyEclipse Persistence Tools
 */

public class SysRoleAuth implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String sraId;
	private String sysRoleId;
	private String sysAuthId;
	/** default constructor */
	public SysRoleAuth() {
	}

	public String getSraId() {
		return sraId;
	}

	public void setSraId(String sraId) {
		this.sraId = sraId;
	}

	public String getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public String getSysAuthId() {
		return sysAuthId;
	}

	public void setSysAuthId(String sysAuthId) {
		this.sysAuthId = sysAuthId;
	}

}
package com.cn.tonyou.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * SysAuthInfo entity. @author MyEclipse Persistence Tools
 */

public class SysAuthInfo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sysAuthId;
	private String sysAuthName;
	private String sysAuthModel;
	private String sysAuthDesc;
	private String sysAuthParentId;
	private String sysAuthPath;
	/**
	 * 一个权限对应多个角色
	 */
	private Set<SysRoleInfo> sysRoleInfos = new HashSet<SysRoleInfo>();
	/** default constructor */
	public SysAuthInfo() {
	}

	public String getSysAuthId() {
		return sysAuthId;
	}

	public void setSysAuthId(String sysAuthId) {
		this.sysAuthId = sysAuthId;
	}

	public String getSysAuthName() {
		return sysAuthName;
	}

	public void setSysAuthName(String sysAuthName) {
		this.sysAuthName = sysAuthName;
	}

	public String getSysAuthModel() {
		return sysAuthModel;
	}

	public void setSysAuthModel(String sysAuthModel) {
		this.sysAuthModel = sysAuthModel;
	}

	public String getSysAuthDesc() {
		return sysAuthDesc;
	}

	public void setSysAuthDesc(String sysAuthDesc) {
		this.sysAuthDesc = sysAuthDesc;
	}

	

	public String getSysAuthParentId() {
		return sysAuthParentId;
	}

	public void setSysAuthParentId(String sysAuthParentId) {
		this.sysAuthParentId = sysAuthParentId;
	}

	public String getSysAuthPath() {
		return sysAuthPath;
	}

	public void setSysAuthPath(String sysAuthPath) {
		this.sysAuthPath = sysAuthPath;
	}

	public Set<SysRoleInfo> getSysRoleInfos() {
		return sysRoleInfos;
	}

	public void setSysRoleInfos(Set<SysRoleInfo> sysRoleInfos) {
		this.sysRoleInfos = sysRoleInfos;
	}

	

	

}
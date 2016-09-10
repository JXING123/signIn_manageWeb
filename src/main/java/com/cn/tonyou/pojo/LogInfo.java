package com.cn.tonyou.pojo;

import java.util.Date;

/**
 * LogInfo entity. @author 日志表实体类
 */

public class LogInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String logId;
	private String optype;
	private String content;//操作内容
	private SysUserInfo sysUser;
	private Date genTime;//操作时间
	private String url;

	// Constructors

	/** default constructor */
	public LogInfo() {
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getOptype() {
		return optype;
	}

	public void setOptype(String optype) {
		this.optype = optype;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SysUserInfo getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUserInfo sysUser) {
		this.sysUser = sysUser;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

}
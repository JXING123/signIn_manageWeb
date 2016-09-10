package com.cn.tonyou.pojo;
import java.util.Date;

/**
 * UpworkInfo entity. @author 上传作业实体类
 */

public class UpworkInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String upworkId;
	private SysUserInfo sysUser;
	private String upworkName;
	private int upworkSize;//文件大小
	private String upworkType;//文件类型
	private Date upworkTime;

	// Constructors

	/** default constructor */
	public UpworkInfo() {
	}

	public String getUpworkId() {
		return upworkId;
	}

	public void setUpworkId(String upworkId) {
		this.upworkId = upworkId;
	}

	public SysUserInfo getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUserInfo sysUser) {
		this.sysUser = sysUser;
	}

	public String getUpworkName() {
		return upworkName;
	}

	public void setUpworkName(String upworkName) {
		this.upworkName = upworkName;
	}

	

	public int getUpworkSize() {
		return upworkSize;
	}

	public void setUpworkSize(int upworkSize) {
		this.upworkSize = upworkSize;
	}

	public String getUpworkType() {
		return upworkType;
	}

	public void setUpworkType(String upworkType) {
		this.upworkType = upworkType;
	}

	public Date getUpworkTime() {
		return upworkTime;
	}

	public void setUpworkTime(Date upworkTime) {
		this.upworkTime = upworkTime;
	}

	

}
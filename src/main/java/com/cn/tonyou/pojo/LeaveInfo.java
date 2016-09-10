package com.cn.tonyou.pojo;

import java.util.Date;

/**
 * LeaveInfo entity. @author 	请假表实体类
 */

public class LeaveInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String leaveId;
	private StuInfo stu;
	private String startLeaveTime;
	private Date startLeaveDate;
	private String endLeaveTime;
	private Date endLeaveDate;
	private Date extendLeaveDate;
	private String leaveType;
	private String leaveDesc;
	private String leaveState;

	// Constructors

	/** default constructor */
	public LeaveInfo() {
	}

	public String getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}

	public StuInfo getStu() {
		return stu;
	}

	public void setStu(StuInfo stu) {
		this.stu = stu;
	}

	public Date getStartLeaveDate() {
		return startLeaveDate;
	}

	public void setStartLeaveDate(Date startLeaveDate) {
		this.startLeaveDate = startLeaveDate;
	}

	public Date getEndLeaveDate() {
		return endLeaveDate;
	}

	public void setEndLeaveDate(Date endLeaveDate) {
		this.endLeaveDate = endLeaveDate;
	}

	public Date getExtendLeaveDate() {
		return extendLeaveDate;
	}

	public void setExtendLeaveDate(Date extendLeaveDate) {
		this.extendLeaveDate = extendLeaveDate;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveDesc() {
		return leaveDesc;
	}

	public void setLeaveDesc(String leaveDesc) {
		this.leaveDesc = leaveDesc;
	}

	public String getLeaveState() {
		return leaveState;
	}

	public void setLeaveState(String leaveState) {
		this.leaveState = leaveState;
	}

	public String getStartLeaveTime() {
		return startLeaveTime;
	}

	public void setStartLeaveTime(String startLeaveTime) {
		this.startLeaveTime = startLeaveTime;
	}

	public String getEndLeaveTime() {
		return endLeaveTime;
	}

	public void setEndLeaveTime(String endLeaveTime) {
		this.endLeaveTime = endLeaveTime;
	}

	

}
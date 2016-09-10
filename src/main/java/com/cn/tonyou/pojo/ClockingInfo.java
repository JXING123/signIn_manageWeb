package com.cn.tonyou.pojo;

import java.util.Date;

/**
 * ClockingInfo entity. @author 考勤表对应实体类
 */

public class ClockingInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String clockingId;//考勤编号
	private StuInfo stu;
	private String clockingAddress;
	private Date clockingDate;
	private CourseInfo course;
	private String clockState;
	private String isvalid;//是否有效
	private String insertDate;
	
	// Constructors

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	

	public Date getClockingDate() {
		return clockingDate;
	}

	public void setClockingDate(Date clockingDate) {
		this.clockingDate = clockingDate;
	}

	/** default constructor */
	public ClockingInfo() {
	}

	public String getClockingId() {
		return clockingId;
	}

	public void setClockingId(String clockingId) {
		this.clockingId = clockingId;
	}

	public StuInfo getStu() {
		return stu;
	}

	public void setStu(StuInfo stu) {
		this.stu = stu;
	}

	public String getClockingAddress() {
		return clockingAddress;
	}

	public void setClockingAddress(String clockingAddress) {
		this.clockingAddress = clockingAddress;
	}

	
	public CourseInfo getCourse() {
		return course;
	}

	public void setCourse(CourseInfo course) {
		this.course = course;
	}

	 
	

	public String getClockState() {
		return clockState;
	}

	public void setClockState(String clockState) {
		this.clockState = clockState;
	}

	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	
}
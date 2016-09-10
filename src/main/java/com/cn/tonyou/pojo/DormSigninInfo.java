package com.cn.tonyou.pojo;

import java.util.Date;

/**
 * DormsigninInfo entity. @author 宿舍考勤表实体
 */

public class DormSigninInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dormSigninId;
	private StuInfo stu;
	private String dormSigninAddress;
	private Date dormSigninTime;
	private String dormSigninState;
	private String isvalid;
	
	private String insertSigninTime;
	private String selectStartTime;
	private String selectEndTime;

	// Constructors

	/** default constructor */
	public DormSigninInfo() {
	}



	public String getInsertSigninTime() {
		return insertSigninTime;
	}


	public void setInsertSigninTime(String insertSigninTime) {
		this.insertSigninTime = insertSigninTime;
	}
	


	public String getSelectStartTime() {
		return selectStartTime;
	}



	public void setSelectStartTime(String selectStartTime) {
		this.selectStartTime = selectStartTime;
	}




	public String getSelectEndTime() {
		return selectEndTime;
	}



	public void setSelectEndTime(String selectEndTime) {
		this.selectEndTime = selectEndTime;
	}



	public String getDormSigninId() {
		return dormSigninId;
	}

	public void setDormSigninId(String dormSigninId) {
		this.dormSigninId = dormSigninId;
	}

	public StuInfo getStu() {
		return stu;
	}

	public void setStu(StuInfo stu) {
		this.stu = stu;
	}

	public String getDormSigninAddress() {
		return dormSigninAddress;
	}

	public void setDormSigninAddress(String dormSigninAddress) {
		this.dormSigninAddress = dormSigninAddress;
	}

	public Date getDormSigninTime() {
		return dormSigninTime;
	}



	public void setDormSigninTime(Date dormSigninTime) {
		this.dormSigninTime = dormSigninTime;
	}


	

	public String getDormSigninState() {
		return dormSigninState;
	}



	public void setDormSigninState(String dormSigninState) {
		this.dormSigninState = dormSigninState;
	}



	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	

}
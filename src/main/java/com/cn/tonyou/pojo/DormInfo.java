package com.cn.tonyou.pojo;
import java.util.HashSet;
import java.util.Set;

/**
 * DormInfo entity. @author 宿舍表实体类
 */

public class DormInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dormId;
	private String dormFloorName;//宿舍楼名称
	private String wifiName;
	private String wifiRouteid;//wifi唯一标识
	private String dormDesc;
	private String dormPopulation;//宿舍人数
	private String dormAddress;
	private String dormSigninStartTime;//宿舍规定开始签到时间
	private String dormSigninEndTime;//宿舍规定签退时间
	private ClassInfo classes;
	private Set<StuInfo> stuInfos = new HashSet<StuInfo>();
	
	// Constructors

	/** default constructor */
	public DormInfo() {
	}

	public String getDormId() {
		return dormId;
	}

	public void setDormId(String dormId) {
		this.dormId = dormId;
	}

	public String getDormFloorName() {
		return dormFloorName;
	}

	public void setDormFloorName(String dormFloorName) {
		this.dormFloorName = dormFloorName;
	}

	public String getWifiName() {
		return wifiName;
	}

	public void setWifiName(String wifiName) {
		this.wifiName = wifiName;
	}

	public String getWifiRouteid() {
		return wifiRouteid;
	}

	public void setWifiRouteid(String wifiRouteid) {
		this.wifiRouteid = wifiRouteid;
	}

	public String getDormDesc() {
		return dormDesc;
	}

	public void setDormDesc(String dormDesc) {
		this.dormDesc = dormDesc;
	}

	public String getDormPopulation() {
		return dormPopulation;
	}

	public void setDormPopulation(String dormPopulation) {
		this.dormPopulation = dormPopulation;
	}

	public String getDormAddress() {
		return dormAddress;
	}

	public void setDormAddress(String dormAddress) {
		this.dormAddress = dormAddress;
	}
 
	public String getDormSigninStartTime() {
		return dormSigninStartTime;
	}

	public void setDormSigninStartTime(String dormSigninStartTime) {
		this.dormSigninStartTime = dormSigninStartTime;
	}

	public String getDormSigninEndTime() {
		return dormSigninEndTime;
	}

	public void setDormSigninEndTime(String dormSigninEndTime) {
		this.dormSigninEndTime = dormSigninEndTime;
	}

	public Set<StuInfo> getStuInfos() {
		return stuInfos;
	}

	public ClassInfo getClasses() {
		return classes;
	}

	public void setClasses(ClassInfo classes) {
		this.classes = classes;
	}

	public void setStuInfos(Set<StuInfo> stuInfos) {
		this.stuInfos = stuInfos;
	}
}
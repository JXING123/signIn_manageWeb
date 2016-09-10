package com.cn.tonyou.pojo;

import java.util.Date;

/**
 * StuInfo entity. @author 学生表实体类
 */

public class StuInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String stuId;
	private String stuTel;
	private String stuPassword;
	private String stuPhoneIndentifying;//学生手机唯一标识
	private String stuName;
	private String stuIdCard;
	private String stuSex;
	private String stuBirthday;
	private String stuNative;//民族
	private ClassInfo classes;//班级
	private String stuAddress;
	private String stuImage;
	private String stuEmail; 
	private String stuDesc;
	private DormInfo dorm;//宿舍
	private String stuState;
	private Date lastLoginTime;
	private Date createTime;
	private int loginCount;//登陆次数

	// Constructors

	/** default constructor */
	public StuInfo() {
	}

	public String getStuTel() {
		return stuTel;
	}

	public void setStuTel(String stuTel) {
		this.stuTel = stuTel;
	}

	public String getStuPassword() {
		return stuPassword;
	}

	public void setStuPassword(String stuPassword) {
		this.stuPassword = stuPassword;
	}

	public String getStuPhoneIndentifying() {
		return stuPhoneIndentifying;
	}

	public void setStuPhoneIndentifying(String stuPhoneIndentifying) {
		this.stuPhoneIndentifying = stuPhoneIndentifying;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getStuIdCard() {
		return stuIdCard;
	}

	public void setStuIdCard(String stuIdCard) {
		this.stuIdCard = stuIdCard;
	}

	public String getStuSex() {
		return stuSex;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}

	public String getStuBirthday() {
		return stuBirthday;
	}

	public void setStuBirthday(String stuBirthday) {
		this.stuBirthday = stuBirthday;
	}

	public String getStuNative() {
		return stuNative;
	}

	public void setStuNative(String stuNative) {
		this.stuNative = stuNative;
	}

	public ClassInfo getClasses() {
		return classes;
	}

	public void setClasses(ClassInfo classes) {
		this.classes = classes;
	}

	public String getStuAddress() {
		return stuAddress;
	}

	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}

	public String getStuImage() {
		return stuImage;
	}

	public void setStuImage(String stuImage) {
		this.stuImage = stuImage;
	}

	public String getStuEmail() {
		return stuEmail;
	}

	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	}

	public String getStuDesc() {
		return stuDesc;
	}

	public void setStuDesc(String stuDesc) {
		this.stuDesc = stuDesc;
	}

	public DormInfo getDorm() {
		return dorm;
	}

	public void setDorm(DormInfo dorm) {
		this.dorm = dorm;
	}

	public String getStuState() {
		return stuState;
	}

	public void setStuState(String stuState) {
		this.stuState = stuState;
	}

	

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	
	
}
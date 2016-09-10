package com.cn.tonyou.pojo;
import java.util.HashSet;
import java.util.Set;

/**
 * SchoolInfo entity. @author 学校表实体类
 */

public class SchoolInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String schoolId;
	private String schoolAddress;
	private String schoolName;
	private String schoolMessage;
	private String schoolImage;
	private String schoolTel;
	private String schoolStartTime;//创建学校时间
	private String schoolCode;//学校代码
	private String schoolRegio;//学校地址
	private String schoolType;
	private Set<DepartmentInfo> departmentInfos = new HashSet<DepartmentInfo>();
	// Constructors0

	/** default constructor */
	public SchoolInfo() {
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolMessage() {
		return schoolMessage;
	}

	public void setSchoolMessage(String schoolMessage) {
		this.schoolMessage = schoolMessage;
	}

	public String getSchoolImage() {
		return schoolImage;
	}

	public void setSchoolImage(String schoolImage) {
		this.schoolImage = schoolImage;
	}

	public String getSchoolTel() {
		return schoolTel;
	}

	public void setSchoolTel(String schoolTel) {
		this.schoolTel = schoolTel;
	}
 

	public String getSchoolStartTime() {
		return schoolStartTime;
	}

	public void setSchoolStartTime(String schoolStartTime) {
		this.schoolStartTime = schoolStartTime;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getSchoolRegio() {
		return schoolRegio;
	}

	public void setSchoolRegio(String schoolRegio) {
		this.schoolRegio = schoolRegio;
	}

	public String getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	public Set<DepartmentInfo> getDepartmentInfos() {
		return departmentInfos;
	}

	public void setDepartmentInfos(Set<DepartmentInfo> departmentInfos) {
		this.departmentInfos = departmentInfos;
	}
	
	
}
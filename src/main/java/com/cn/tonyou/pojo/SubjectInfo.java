package com.cn.tonyou.pojo;

/**
 * SubjectInfo entity. @author 科目表实体类
 */

public class SubjectInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String subjectId;
	private String subjectName;

	// Constructors

	/** default constructor */
	public SubjectInfo() {
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	

}
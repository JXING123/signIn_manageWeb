package com.cn.tonyou.pojo;
/**
 * QuestionInfo entity. @author 题目信息表
 */

public class QuestionInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String questionId;
	private SysUserInfo sysUser;
	private String questionTitle;//题目
	private int questionType;//题目类型（1单选，2多选）
	private String difficulty;//难度（高、中、低）
	private SubjectInfo subject;//科目
	private String questionAnswer;//题目答案
	private String questionParse;//题目解析

	// Constructors

	/** default constructor */
	public QuestionInfo() {
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public SysUserInfo getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUserInfo sysUser) {
		this.sysUser = sysUser;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public SubjectInfo getSubject() {
		return subject;
	}

	public void setSubject(SubjectInfo subject) {
		this.subject = subject;
	}

	public String getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public String getQuestionParse() {
		return questionParse;
	}

	public void setQuestionParse(String questionParse) {
		this.questionParse = questionParse;
	}

	

}
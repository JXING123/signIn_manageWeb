package com.cn.tonyou.pojo;

import java.util.Date;

/**
 * AnswerInfo entity. @author 答题表实体类
 */

public class AnswerInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String answerId;
	private StuInfo stu;
	private SchoolWorkInfo schoolWork;//作业实体类
	private QuestionInfo question;
	private Date answerDate;
	private String answer;//学生答题答案
	
	/** default constructor */
	public AnswerInfo() {
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public StuInfo getStu() {
		return stu;
	}

	public void setStu(StuInfo stu) {
		this.stu = stu;
	}

	public SchoolWorkInfo getSchoolWork() {
		return schoolWork;
	}

	public void setSchoolWork(SchoolWorkInfo schoolWork) {
		this.schoolWork = schoolWork;
	}

	public QuestionInfo getQuestion() {
		return question;
	}

	public void setQuestion(QuestionInfo question) {
		this.question = question;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	
	

	

}
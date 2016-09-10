package com.cn.tonyou.pojo;

/**
 * ScoreInfo entity. @author 成绩表实体类
 */

public class ScoreInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String scoreId;
	private StuInfo stu;
	private SchoolWorkInfo schoolWork;
	private Double score;//成绩

	// Constructors

	/** default constructor */
	public ScoreInfo() {
	}

	public String getScoreId() {
		return scoreId;
	}

	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
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

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	

}
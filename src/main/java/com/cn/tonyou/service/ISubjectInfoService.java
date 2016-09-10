package com.cn.tonyou.service;

import java.util.List;

import com.cn.tonyou.pojo.SubjectInfo;

/**
 * 科目管理
 * @author Administrator
 *
 */
public interface ISubjectInfoService {
	/**
	 * 查询所有科目
	 */
	public List<SubjectInfo> selectAllSubject();
	/**
	 * 根据科目名称查询科目
	 * @param subjectName
	 * @return
	 */
	public SubjectInfo  findSubjectInfoByName(String subjectName);
	/**
	 * 添加科目
	 */
	public void addSubjectInfo(SubjectInfo subjectInfo);
	
	/**
	 * 删除科目
	 */
	public void deleteSubjectInfo(String subjectId);
	
	/**
	 * 根据id查询科目
	 */
	public SubjectInfo findSubjectInfoById(String subjectId);
	
	/**
	 * 修改科目
	 */
	public void updateSubjectInfo(SubjectInfo subjectInfo);
}

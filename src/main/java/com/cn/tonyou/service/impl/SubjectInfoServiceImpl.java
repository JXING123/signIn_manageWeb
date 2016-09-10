package com.cn.tonyou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.tonyou.mapper.SubjectInfoMapper;
import com.cn.tonyou.pojo.SubjectInfo;
import com.cn.tonyou.service.ISubjectInfoService;
import com.cn.tonyou.utils.UUIDGenerator;

/**
 * 科目管理
 * @author Administrator
 *
 */
@Service
public class SubjectInfoServiceImpl implements ISubjectInfoService{
	@Autowired
	private SubjectInfoMapper subjectInfoMapper;
	
	
	/**
	 * 查询所有科目
	 */
	public List<SubjectInfo> selectAllSubject() {
		return subjectInfoMapper.selectAllSubject();
	}

	/**
	 * 添加科目
	 */
	public void addSubjectInfo(SubjectInfo subjectInfo) {
		subjectInfo.setSubjectId(UUIDGenerator.getUUID());
		subjectInfoMapper.addSubjectInfo(subjectInfo);
	}
	/**
	 * 删除科目
	 */
	public void deleteSubjectInfo(String subjectId) {
		subjectInfoMapper.deleteSubjectInfo(subjectId);
	}
	/**
	 * 根据id查询科目
	 */
	public SubjectInfo findSubjectInfoById(String subjectId) {
		return subjectInfoMapper.findSubjectInfoById(subjectId);
	}

	/**
	 * 修改科目
	 */
	public void updateSubjectInfo(SubjectInfo subjectInfo) {
		subjectInfoMapper.updateSubjectInfo(subjectInfo);
	}

	public SubjectInfo findSubjectInfoByName(String subjectName) {
		return subjectInfoMapper.findSubjectInfoByName(subjectName);
	}
}

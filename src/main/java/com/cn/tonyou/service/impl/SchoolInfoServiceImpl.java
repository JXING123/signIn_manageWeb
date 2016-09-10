package com.cn.tonyou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.tonyou.mapper.SchoolInfoMapper;
import com.cn.tonyou.pojo.SchoolInfo;
import com.cn.tonyou.service.ISchoolInfoService;
import com.cn.tonyou.utils.UUIDGenerator;
/**
 * 学校管理
 * @author HY
 *
 */
@Service
public class SchoolInfoServiceImpl implements ISchoolInfoService{
	@Autowired
	private SchoolInfoMapper schoolInfoMapper;
	
	
	/**
	 * 查询所有学校
	 */
	public List<SchoolInfo> selectSchoolList() {
		return schoolInfoMapper.selectSchoolList();
	}
	
	/**
	 * 查询所有学校2
	 */
	public List<SchoolInfo> selectSchoolList2(String school) {
		return schoolInfoMapper.selectSchoolList2(school);
	}
	/**
	 * 根据学校名字查询学校
	 * @param schoolName
	 * @return
	 */
	public SchoolInfo selectSchoolByName(String schoolName){
		return schoolInfoMapper.selectSchoolByName(schoolName);
	}
	/**
	 * 删除学校信息
	 */
	public void deleteSchool(String schoolId) {
		schoolInfoMapper.deleteSchool(schoolId);
	}
	
	/**
	 * 添加学校信息
	 */
	public void addSchool(SchoolInfo schoolInfo) {
		schoolInfo.setSchoolId(UUIDGenerator.getUUID());
		schoolInfoMapper.addSchool(schoolInfo);
	}
	
	/**
	 * 根据id查询某个学校信息
	 */
	public SchoolInfo findByIdSchool(String schoolId) {
		return schoolInfoMapper.findByIdSchool(schoolId);
	}
	
	/**
	 * 修改学校信息
	 */
	public void updateSchoolInfo(SchoolInfo schoolInfo) {
		schoolInfoMapper.updateSchoolInfo(schoolInfo);
	}

	/**
	 * 查询所有学校院系以及班级
	 * @return
	 */
	public List<SchoolInfo> selectAllSchools(){
		return schoolInfoMapper.selectAllSchools();
	}
	
	/**
	 * 添加学校 不能添加名称相同的学校
	 */
	public List<SchoolInfo> checkAddSchoolinfo(String schoolName) {
		return schoolInfoMapper.checkAddSchoolinfo(schoolName);
	}
}

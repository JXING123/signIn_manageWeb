package com.cn.tonyou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.tonyou.mapper.DormInfoMapper;
import com.cn.tonyou.pojo.DormInfo;
import com.cn.tonyou.service.IDormInfoService;
import com.cn.tonyou.utils.UUIDGenerator;

/**
 * 宿舍管理
 * @author HY
 *
 */
@Service
public class DormInfoServiceImpl implements IDormInfoService{
	@Autowired
	private DormInfoMapper dormInfoMapper;
	
	/**
	 * 查询所有宿舍
	 */
	public List<DormInfo> findDormList(Map<String, String> paramsMap) {
		return dormInfoMapper.findDormList(paramsMap);
	}
	/**
	 * 根据学生id查询宿舍考勤表
	 * @return
	 */
	public DormInfo getDormListForStuId(String stuId){
		return dormInfoMapper.getDormListForStuId(stuId);
	}
	
	/**
	 * 添加宿舍
	 */
	public void addDormInfo(DormInfo dormInfo) {
		dormInfo.setDormId(UUIDGenerator.getUUID());
		dormInfoMapper.addDormInfo(dormInfo);
	}
	/**
	 * 删除宿舍
	 */
	public void deleteDormInfo(String dormId) {
		dormInfoMapper.deleteDormInfo(dormId);
	}
	/**
	 * 根据学校id和班级id查询宿舍
	 */
	public List<DormInfo> getDormInfo2(String schoolId, String classId) {
		return dormInfoMapper.getDormInfo2(schoolId, classId);
	}
	/**
	 * 根据id查询宿舍
	 */
	public DormInfo findDormInfoById(String dormId) {
		return dormInfoMapper.findDormInfoById(dormId);
	}
	/**
	 * 修改宿舍
	 */
	public void updateDormInfo(DormInfo dormInfo) {
		dormInfoMapper.updateDormInfo(dormInfo);
	}
	public DormInfo findDormByClassIdAndDormName(String classId, String dormName) {
		return dormInfoMapper.findDormByClassIdAndDormName(classId, dormName);
	}
}

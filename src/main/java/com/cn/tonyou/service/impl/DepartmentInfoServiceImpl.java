package com.cn.tonyou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.tonyou.mapper.DepartmentInfoMapper;
import com.cn.tonyou.pojo.DepartmentInfo;
import com.cn.tonyou.service.IDepartmentInfoService;
import com.cn.tonyou.utils.UUIDGenerator;

/**
 * 系别操作
 * @author HY
 *
 */
@Service
public class DepartmentInfoServiceImpl implements IDepartmentInfoService{
	@Autowired
	private DepartmentInfoMapper departmentInfoMapper;
	
	public List<DepartmentInfo> findListDepartmentInfo(String schoolId) {
		return departmentInfoMapper.findListDepartmentInfo(schoolId);
	}

	/**
	 * 添加系别
	 */
	public void addDepartmentInfo(DepartmentInfo departmentInfo) {
		
		departmentInfo.setDepartmentId(UUIDGenerator.getUUID());
		departmentInfoMapper.addDepartmentInfo(departmentInfo);
	}

	/**
	 * 删除系别
	 */
	public void deleteDepartmentInfo(String departmentId) {
		departmentInfoMapper.deleteDepartmentInfo(departmentId);
	}

	/**
	 * 根据id查询系别
	 */
	public DepartmentInfo findDepartmentById(String departmentId) {
		return departmentInfoMapper.findDepartmentById(departmentId);
	}
	/**
	 * 根据系名称以及学校id查询系
	 * @param deprtmentName
	 * @param schoolId
	 * @return
	 */
	public DepartmentInfo findDepartmentByName(String deprtmentName,String schoolId){
		return departmentInfoMapper.findDepartmentByName(deprtmentName, schoolId);
	}
	/**
	 * 修改系别信息
	 */
	public void updateDepartmentInfo(DepartmentInfo departmentInfo) {
		departmentInfoMapper.updateDepartmentInfo(departmentInfo);
	}
	
	/**
	 * 添加系别时 根据学校id和系别名称来判断该学校是否存在改系别
	 */
	public List<DepartmentInfo> checkAddDepartmentinfo(String schoolId, String deprtmentName) {
		return departmentInfoMapper.checkAddDepartmentinfo(schoolId, deprtmentName);
	}
	
	/**
	 * 根据学校id查询系别
	*/ 
	public List<DepartmentInfo> getDepartmentBySchoolId(String schoolId) {
		return departmentInfoMapper.getDepartmentBySchoolId(schoolId);
	}

}

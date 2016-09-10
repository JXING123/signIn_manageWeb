package com.cn.tonyou.service;

import java.util.List;

import com.cn.tonyou.pojo.DepartmentInfo;

/**
 * 系别操作
 * @author HY
 *
 */
public interface IDepartmentInfoService {
	/**
	 * 查询所有系别
	 */
	public List<DepartmentInfo> findListDepartmentInfo(String schoolId);
	
	/**
	 * 根据学校id查询系别
	*/ 
	public List<DepartmentInfo> getDepartmentBySchoolId(String schoolId);
	
	/**
	 * 添加系别
	 */
	public void addDepartmentInfo(DepartmentInfo departmentInfo);
	
	/**
	 * 删除系别
	 */
	public void deleteDepartmentInfo(String departmentId);
	
	/**
	 * 根据id查询系别
	 */
	public DepartmentInfo findDepartmentById(String departmentId);
	/**
	 * 根据系名称以及学校id查询系
	 * @param deprtmentName
	 * @param schoolId
	 * @return
	 */
	public DepartmentInfo findDepartmentByName(String deprtmentName,String schoolId);
	/**
	 * 修改系别信息
	 */
	public void updateDepartmentInfo(DepartmentInfo departmentInfo);
	
	/**
	 * 添加系别时 根据学校id和系别名称来判断该学校是否存在改系别
	 */
	public List<DepartmentInfo> checkAddDepartmentinfo(String schoolId,String deprtmentName);
	
}

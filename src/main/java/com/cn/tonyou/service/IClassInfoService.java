package com.cn.tonyou.service;

import java.util.List;
import java.util.Map;

import com.cn.tonyou.pojo.ClassInfo;

/**
 * 班级操作
 * @author HY
 *
 */
public interface IClassInfoService {
	/**
	 * 根据系别id查询班级
	 */
	public List<ClassInfo> findListClassInfo(Map<String, String> paramsMap);
	
	/**
	 * 查询所有班级
	 */
	public List<ClassInfo> getClassinfo(String schoolId);
	
	/**
	 * 客户端联表查询，班级、系别、学校
	 */
	public List<ClassInfo> selectAll();
	
	/**
	 * 删除班级
	 */
	public void deleteClassInfo(String classId);
	
	/**
	 * 添加班级
	 */
	public void addClassInfo(ClassInfo classInfo);
	
	/**
	 * 根据班级id查询班级
	 */
	public ClassInfo findByIdClass(String classId);
	
	/**
	 * 修改班级
	 */
	public void updateClassInfo(ClassInfo classInfo);
	
	/**
	 * 根据系别id查询这个系是否有这个班级名
	 */
	public List<ClassInfo> checkAddClassinfo(String departmentId,String className);
	/**
	 * 根据系id以及班级名字查询班级
	 * @param className
	 * @param departmentId
	 * @return
	 */
	public ClassInfo selectClassByIdAndName(String className,String departmentId);
	
	/**
	 * 根据系别id查询这个学校这个系所在的所有班级
	 */
	public List<ClassInfo> getAllClassByDepartmentId(String departmentId);
	/**
	 * 查询某个老师所有的学生
	 * @param params
	 * @return
	 */
	public List<ClassInfo> findSysClassId(Map<String, String> params);
}

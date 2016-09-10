package com.cn.tonyou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.tonyou.mapper.ClassInfoMapper;
import com.cn.tonyou.pojo.ClassInfo;
import com.cn.tonyou.service.IClassInfoService;
import com.cn.tonyou.utils.UUIDGenerator;

/**
 * 班级操作
 * @author HY
 *
 */
@Service
public class ClassInfoServiceImpl implements IClassInfoService{
	@Autowired
	private ClassInfoMapper classInfoMapper;

	public List<ClassInfo> findListClassInfo(Map<String, String> paramsMap) {
		return classInfoMapper.findListClassInfo(paramsMap);
	}
	
	 
	public List<ClassInfo> getClassinfo(String schoolId){
		return classInfoMapper.getClassinfo(schoolId);
	}

	/**
	 * 客户端联表查询，班级、系别、学校
	 */
	public List<ClassInfo> selectAll() {
		return classInfoMapper.selectAll();
	}

	/**
	 * 删除班级
	 */
	public void deleteClassInfo(String classId) {
		classInfoMapper.deleteClassInfo(classId);
	}

	/**
	 * 添加班级
	 */
	public void addClassInfo(ClassInfo classInfo) {
		classInfo.setClassId(UUIDGenerator.getUUID());
		classInfoMapper.addClassInfo(classInfo);
	}

	/**
	 * 根据班级id查询班级
	 */
	public ClassInfo findByIdClass(String classId) {
		return classInfoMapper.findByIdClass(classId);
	}

	/**
	 * 修改班级
	 */
	public void updateClassInfo(ClassInfo classInfo) {
		classInfoMapper.updateClassInfo(classInfo);
	}

	/**
	 * 根据系别id查询这个系是否有这个班级名
	 */
	public List<ClassInfo> checkAddClassinfo(String departmentId, String className) {
		return classInfoMapper.checkAddClassinfo(departmentId, className);
	}
	/**
	 * 根据系id以及班级名字查询班级
	 * @param className
	 * @param departmentId
	 * @return
	 */
	public ClassInfo selectClassByIdAndName(String className,String departmentId){
		return classInfoMapper.selectClassByIdAndName(className, departmentId);
	}


	/**
	 * 根据系别id查询这个学校这个系所在的所有班级
	 */
	public List<ClassInfo> getAllClassByDepartmentId(String departmentId) {
		return classInfoMapper.getAllClassByDepartmentId(departmentId);
	}


	public List<ClassInfo> findSysClassId(Map<String, String> params) {
		return classInfoMapper.findSysClassId(params);
	}
	
}

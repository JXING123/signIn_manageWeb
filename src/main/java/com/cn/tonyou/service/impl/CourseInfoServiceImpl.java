package com.cn.tonyou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cn.tonyou.mapper.CourseInfoMapper;
import com.cn.tonyou.pojo.CourseInfo;
import com.cn.tonyou.service.ICourseInfoService;
import com.cn.tonyou.utils.DateUtil;
import com.cn.tonyou.utils.UUIDGenerator;

/**
 * 课程管理
 * @author Administrator
 *
 */
@Service
public class CourseInfoServiceImpl implements ICourseInfoService{
	@Autowired
	private CourseInfoMapper courseInfoMapper;
	/**
	 * 通过学生班级获取课程信息
	 * @param classId
	 * @return
	 */
	public List<CourseInfo> getCourseListForStu(String classId){
		return courseInfoMapper.getCourseListForStu(classId);
	}
	/**
	 * 查询所有课程
	 */
	public List<CourseInfo> findCourseList(Map<String, String> paramsMap){
		return courseInfoMapper.findCourseList(paramsMap);
	}
	
	/**
	 * 添加课程
	 */
	public void addCourseInfo(CourseInfo courseInfo) {
		courseInfo.setCourseId(UUIDGenerator.getUUID());
		courseInfoMapper.addCourseInfo(courseInfo);
	}
	
	/**
	 * 删除课程
	 */
	public void deleteCourseInfo(String courseId) {
		courseInfoMapper.deleteCourseInfo(courseId);
	}
	/**
	 * 根据id查询课程
	 */
	public CourseInfo findCourseInfoById(String courseId) {
		return courseInfoMapper.findCourseInfoById(courseId);
	}

	/**
	 * 修改课程
	 */
	public void updateCourseInfo(CourseInfo courseInfo) {
		courseInfoMapper.updateCourseInfo(courseInfo);
	}
	/**
	 * 按星期查询课程
	 */
	public  List<CourseInfo> getWeekCourseInfoList(String stuId, String week) {
		return courseInfoMapper.getWeekCourseInfoList(stuId, week);
	}

	public  List<CourseInfo> findClassByIdCourse(String classId) {
		return courseInfoMapper.findClassByIdCourse(classId);
	}
	public List<CourseInfo> findCourseByClassIdAndTime(String classId, String time) {
		String weekOfDate = DateUtil.getWeekOfDate(DateUtil.ConverToDate(time));
		return courseInfoMapper.findCourseByClassIdAndTime(classId, weekOfDate);
	}
}

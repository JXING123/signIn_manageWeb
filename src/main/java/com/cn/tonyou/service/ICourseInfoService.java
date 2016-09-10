package com.cn.tonyou.service;

import java.util.List;
import java.util.Map;

import com.cn.tonyou.pojo.CourseInfo;

/**
 * 课程管理
 * @author Administrator
 *
 */
public interface ICourseInfoService {
	/**
	 * 通过学生班级获取课程信息
	 * @param classId
	 * @return
	 */
	public List<CourseInfo> getCourseListForStu(String classId);
	/**
	 * 查询所有课程
	 */
	public List<CourseInfo> findCourseList(Map<String, String> paramsMap);

	/**
	 * 添加课程
	 */
	public void addCourseInfo(CourseInfo courseInfo);
	
	/**
	 * 删除课程
	 */
	public void deleteCourseInfo(String courseId);
	
	/**
	 * 根据id查询课程
	 */
	public CourseInfo findCourseInfoById(String courseId);
	
	/**
	 * 修改课程
	 */
	public void updateCourseInfo(CourseInfo courseInfo);
	/**
	 * 根据学生信息和星期获取课程信息
	 * @param stuId
	 * @param week
	 * @return
	 */
	public List<CourseInfo> getWeekCourseInfoList(String stuId,String week);
	
	/**
	 * 获取某个班的所有的课程
	 */
	public   List<CourseInfo> findClassByIdCourse(String classId);
	/**
	 * 通过时间和班级id来获取课程信息
	 * @param classId
	 * @param time
	 * @return
	 */
	public List<CourseInfo> findCourseByClassIdAndTime(String classId,String time);
}

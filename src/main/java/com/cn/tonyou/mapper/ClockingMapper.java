package com.cn.tonyou.mapper;
import java.util.List;
import java.util.Map;

import com.cn.tonyou.pojo.ClockingInfo;
import com.cn.tonyou.pojo.CourseInfo;
import com.cn.tonyou.pojo.StuInfo;
/**
 * 
 * @author CJW
 *上课考勤
 */
public interface ClockingMapper {
	/**
	 * 获取单个的考勤信息
	 * @return
	 */
	public ClockingInfo findByIdClocking(ClockingInfo clocking);
	/**
	 * 查询所有的上课考勤信息
	 * @return
	 */
	public List<ClockingInfo> findListClocking();
	/**
	 * 添加上课考勤信息
	 * @param clicking
	 */
	public int addClocking(ClockingInfo clicking);
	/**
	 * 修改上课考勤信息
	 * @param clocking
	 */
	public int updateClocking(ClockingInfo clocking);
	/**
	 * 删除上课考勤信息
	 * @param clocking
	 */
	public void deleteClocking(ClockingInfo clocking);
	
	public void findByIdStu(StuInfo stu);
	
	public void findByIdCourse(CourseInfo course);
	/**
	 * 根据学生id查询学生考勤信息
	 * @param stuId
	 * @return
	 */
	public List<ClockingInfo> getStuByIdClocking(String stuId,String dateTime);
	/**
	 * 根据学生id以及时间查询考勤
	 * @param stuId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<ClockingInfo> getStuByIdAndTime(String stuId,String startTime,String endTime);
	/**
	 * 根据班级id和时间(当前年月日、当前年月1)查询这个班所有学生状态为正常的总和 
	 */
	public int selectClockingCount(String classId,String dayTime2,String dayTime);
	/**
	 * 通过参数去查询不同的考勤信息
	 * @param paramsMap
	 * @return
	 */
	public List<ClockingInfo> findByParamsClocking(Map<String, String> paramsMap);
	/**
	 * 获取某个班某个段时间的考勤情况
	 * @param paramsMap
	 * @return
	 */
	public List<ClockingInfo> findTimeAndClassByIdClocking (Map<String, String> paramsMap);
	/**
	 * 获取某个课程某段时间的考勤
	 * @param paramsMap
	 * @return
	 */
	public List<ClockingInfo> findTimeAndCourseByIdClocking (Map<String, String> paramsMap);
	/**
	 * 根据班级id以及时间查询考勤
	 * @param stuId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<ClockingInfo> getClassByIdAndTime(String stuId,String startTime,String endTime);
}

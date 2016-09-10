package com.cn.tonyou.service;
import java.util.List;
import java.util.Map;
import com.cn.tonyou.pojo.ClockingInfo;
public interface IClockingService {
	/**
	 * 通过id来获取单个的用户考勤信息
	 * @return
	 */
	public ClockingInfo findByIdClocking(ClockingInfo clock);
	/**
	 * 获取所有的考勤信息
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
	 * 删除考勤信息
	 * @param clocking
	 */
	public void deleteClocking(ClockingInfo clocking);
	

	/**
	 * 根据学生id查询考勤信息
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
	 * 根据所选条件查询所需要的考勤信息
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
	 * @param classId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<ClockingInfo> getClassByIdAndTime(String classId,String startTime,String endTime);

}

package com.cn.tonyou.service;
import java.util.List;
import java.util.Map;

import com.cn.tonyou.pojo.LeaveInfo;

/**
 * 请假管理
 * @author HY
 *
 */
public interface ILeaveInfoService {
	/**
	 * 查询所有请假信息
	 */
	public List<LeaveInfo> findLeaveList(Map<String, String> paramsMap);
	/**
	 * 添加请假
	 * @param leaveinfo
	 * @return
	 */
	public int addLeaveForStu(LeaveInfo leaveinfo);

	/**
	 * 查询某个学生某天的请假信息
	 */
	public LeaveInfo getStuDayLeave(LeaveInfo leaveInfo);
	/**
	 * 获取某个学生的请假信息
	 * @param stuId
	 * @return
	 */
	public List<LeaveInfo> getStuLeaveList(String stuId);

	/**
	 * 添加请假信息
	 */
	public void addLeaveInfo(LeaveInfo leaveInfo);
	
	/**
	 * 删除请假
	 */
	public void deleteLeaveInfo(String leaveId);
	
	/**
	 * 修改请假信息
	 */
	public void updateLeaveInfo(LeaveInfo leaveinfo);
	
	/**
	 * 根据id查询请假信息
	 */
	public LeaveInfo selectLeaveInfoById(String leaveId);
	
	/**
	 * 修改审核状态
	 */
	public void updateState(LeaveInfo leaveinfo);
	/**
	 * 获取某个学生或者某个班级的请假信息
	 * @return
	 */
	public List<LeaveInfo> findPeriodOfTimeLeave(Map<String, String> parmasMap);
	/**
	 * 根据课程id获取某段时间的请假信息
	 * @param parmasMap
	 * @return
	 */
	public List<LeaveInfo> findCourseByIdTimeLeave(Map<String, String> parmasMap);
	
}

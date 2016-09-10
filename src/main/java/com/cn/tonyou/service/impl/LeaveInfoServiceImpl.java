package com.cn.tonyou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.tonyou.mapper.LeaveInfoMapper;
import com.cn.tonyou.pojo.LeaveInfo;
import com.cn.tonyou.service.ILeaveInfoService;
import com.cn.tonyou.utils.UUIDGenerator;
/**
 * 请假管理
 * @author HY
 *
 */
@Service
public class LeaveInfoServiceImpl implements ILeaveInfoService{
	@Autowired
	private LeaveInfoMapper leaveInfoMapper;
	
	
	/**
	 * 查询所有请假信息
	 */
	public List<LeaveInfo> findLeaveList(Map<String, String> paramsMap) {
		return leaveInfoMapper.findLeaveList(paramsMap);
	}

	/**
	 * web添加请假信息
	 */
	public void addLeaveInfo(LeaveInfo leaveInfo) {
		leaveInfo.setLeaveId(UUIDGenerator.getUUID());
		leaveInfoMapper.addLeaveInfo(leaveInfo);
	}
	/**
	 * 添加请假
	 * @param leaveinfo
	 * @return
	 */
	public int addLeaveForStu(LeaveInfo leaveinfo){
		return leaveInfoMapper.addLeaveForStu(leaveinfo);
	}
	public LeaveInfo getStuDayLeave(LeaveInfo leaveInfo) {
		return leaveInfoMapper.getStuDayLeave(leaveInfo);
	}
	public List<LeaveInfo> getStuLeaveList(String stuId) {
		return leaveInfoMapper.getStuLeaveList(stuId);
	}
	
	/**
	 * 删除请假
	 */
	public void deleteLeaveInfo(String leaveId) {
		leaveInfoMapper.deleteLeaveInfo(leaveId);
	}
	
	/**
	 * 根据id查询请假信息
	 */
	public LeaveInfo selectLeaveInfoById(String leaveId) {
		return leaveInfoMapper.selectLeaveInfoById(leaveId);
	}
	/**
	 * 修改请假信息
	 */
	public void updateLeaveInfo(LeaveInfo leaveinfo) {
		leaveInfoMapper.updateLeaveInfo(leaveinfo);
	}
	
	/**
	 * 修改审核状态
	 */
	public void updateState(LeaveInfo leaveinfo) {
		leaveInfoMapper.updateState(leaveinfo);
	}

	public List<LeaveInfo> findPeriodOfTimeLeave(Map<String, String> parmasMap) {
		return leaveInfoMapper.findPeriodOfTimeLeave(parmasMap);
	}

	public List<LeaveInfo> findCourseByIdTimeLeave(Map<String, String> parmasMap) {
		return leaveInfoMapper.findCourseByIdTimeLeave(parmasMap);
	}

}

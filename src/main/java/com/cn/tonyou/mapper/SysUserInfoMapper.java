package com.cn.tonyou.mapper;

import java.util.List;
import java.util.Map;

import com.cn.tonyou.pojo.SysUserInfo;

/**
 * 管理员管理
 * @author Administrator
 *
 */
public interface SysUserInfoMapper {
	/**
	 * 根据id查询用户
	 */
	public SysUserInfo getUserById(String sysUserId);
	/**
	 * 根据用户查询用户
	 * @param sysUserName
	 * @return
	 */
	public SysUserInfo getUserByName(String sysUserName);
	/**
	 * 获取某个学校所有角色为任课教师的用户
	 * @param paramsMap
	 * @return
	 */
	public List<SysUserInfo> findRoleTeacherSysUserList(String schoolId,String departmentId);
	/**
	 * 获取某个学校所有角色为辅导员的用户
	 * @param schoolId
	 * @return
	 */
	public List<SysUserInfo> findRoleCoachSysUserList(String schoolId);
	/**
	 * 查询所有管理员
	 */
	public List<SysUserInfo> findSysUserInfoList(Map<String, String> paramsMap);
	
	/**
	 * 添加用户
	 * @param sysUserInfo
	 * @return
	 */
	public int addSysUserInfo(SysUserInfo sysUserInfo);
	/**
	 * 修改用户信息
	 * @param sysUserInfo
	 * @return
	 */
	public int updateByIdSysUser(SysUserInfo sysUserInfo);
	/**
	 * 删除用户信息
	 * @param sysUserId
	 * @return
	 */
	public int deleteByIdSysUser(String sysUserId);
	/**
	 * 根据科目名称和学校id获取管理员信息 
	 * @param schoolId
	 * @param subjectName
	 * @return
	 */
	public List<SysUserInfo> findSubjectByNameAndSchoolId(String schoolId,String subjectName);
}

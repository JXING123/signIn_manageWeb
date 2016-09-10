package com.cn.tonyou.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.tonyou.mapper.SysUserInfoMapper;
import com.cn.tonyou.mapper.SysUserRoleMapper;
import com.cn.tonyou.pojo.SysUserInfo;
import com.cn.tonyou.pojo.SysUserRole;
import com.cn.tonyou.service.ISysUserInfoService;
import com.cn.tonyou.shiro.MyShiro;
import com.cn.tonyou.utils.DateUtil;
import com.cn.tonyou.utils.MD5Util;
import com.cn.tonyou.utils.UUIDGenerator;
/**
 * 管理员管理
 * @author Administrator
 *
 */
@Service
public class SysUserInfoServiceImpl implements ISysUserInfoService{
	@Autowired
	private SysUserInfoMapper sysUserInfoMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	/**
	 * 根据id查询用户
	 */
	public SysUserInfo getUserById(String sysUserId) {
		return sysUserInfoMapper.getUserById(sysUserId);
	}
	/**
	 * 根据管理员名称获取用户
	 */
	public SysUserInfo getUserByName(String sysUserName) {
		return sysUserInfoMapper.getUserByName(sysUserName);
	}
	
	
	/**
	 * 查询所有管理员
	 */
	public List<SysUserInfo> findSysUserInfoList(Map<String, String> paramsMap) {
		return sysUserInfoMapper.findSysUserInfoList(paramsMap);
	}




	
	/**
	 * 添加用户
	 */
	public int addSysUserInfo(SysUserInfo sysUserInfo,String[] roleIds){
		// 分配角色
		sysUserInfo.setCreateTime(DateUtil.format(new Date(), DateUtil.DATE_TIME_PATTERN));
		sysUserInfo.setLoginCount(0);
		sysUserInfo.setSysUserState("离线");
		int addSysUserInfo = sysUserInfoMapper.addSysUserInfo(sysUserInfo);
		if (addSysUserInfo>=1 && roleIds!=null) {
			for (String roleId : roleIds) {
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setSurId(UUIDGenerator.getUUID());
				sysUserRole.setSysRoleId(roleId);
				sysUserRole.setSysUserId(sysUserInfo.getSysUserId());
				sysUserRoleMapper.addSysUserRole(sysUserRole);
			}
		}
		return addSysUserInfo;
	}
	/**
	 * 修改用户
	 */
	public int updateByIdSysUser(SysUserInfo sysUserInfo, String[] roleIds) {
		//密码也使用md5加密
		if (sysUserInfo.getSysUserPassWord().length()!=32) {
			sysUserInfo.setSysUserPassWord(MD5Util.getMD5(sysUserInfo.getSysUserPassWord()));
		}
		//然后添加该用户的角色信息
		if (roleIds!=null) {
			//删除该用户的角色信息
			sysUserRoleMapper.deleteByIdUserRole(sysUserInfo.getSysUserId());
			for (String roleId : roleIds) {
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setSurId(UUIDGenerator.getUUID());
				sysUserRole.setSysRoleId(roleId);
				sysUserRole.setSysUserId(sysUserInfo.getSysUserId());
				sysUserRoleMapper.addSysUserRole(sysUserRole);
			}
		}
		//修改用户信息
		int updateByIdSysUser = sysUserInfoMapper.updateByIdSysUser(sysUserInfo);
		//清空shiro缓存重新获取授权
		new MyShiro().clearAllCachedAuthorizationInfo();
		return updateByIdSysUser;
	}
	
	public int deleteByIdSysUser(String sysUserId) {
		return sysUserInfoMapper.deleteByIdSysUser(sysUserId);
	}
	
	public List<SysUserInfo> findRoleTeacherSysUserList(String schoolId,String departmentId) {
		return sysUserInfoMapper.findRoleTeacherSysUserList(schoolId,departmentId);
	}
	public List<SysUserInfo> findRoleCoachSysUserList(String schoolId) {
		return sysUserInfoMapper.findRoleCoachSysUserList(schoolId);
	}
	public List<SysUserInfo> findSubjectByNameAndSchoolId(String schoolId, String subjectName) {
		return sysUserInfoMapper.findSubjectByNameAndSchoolId(schoolId, subjectName);
	}

}

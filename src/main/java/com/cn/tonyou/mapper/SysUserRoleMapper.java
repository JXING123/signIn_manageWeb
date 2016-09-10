package com.cn.tonyou.mapper;
import com.cn.tonyou.pojo.SysUserRole;

/**
 * 用户角色管理
 * @author Administrator
 *
 */
public interface SysUserRoleMapper {
	/**
	 * 删除某个用户的角色信息
	 * @param roleId
	 * @return
	 */
	public int deleteByIdUserRole(String sysUserId);
	/**
	 * 增加某个用户的角色信息
	 * @param sysRoleInfo
	 * @return
	 */
	public int addSysUserRole(SysUserRole sysUserRole);
	
}

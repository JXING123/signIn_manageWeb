package com.cn.tonyou.mapper;
import com.cn.tonyou.pojo.SysRoleAuth;

/**
 * 角色权限管理
 * @author Administrator
 *
 */
public interface SysRoleAuthMapper {
	/**
	 * 删除某个角色的权限信息
	 * @param roleId
	 * @return
	 */
	public int deleteByIdRoleAuth(String roleId);
	/**
	 * 增加某个角色的权限信息
	 * @param sysRoleInfo
	 * @return
	 */
	public int addSysRoleAuth(SysRoleAuth sysRoleAuth);
	
}

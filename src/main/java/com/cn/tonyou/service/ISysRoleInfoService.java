package com.cn.tonyou.service;
import java.util.List;
import com.cn.tonyou.pojo.SysRoleInfo;

/**
 * 角色管理
 * @author Administrator
 *
 */
public interface ISysRoleInfoService {
	
	/**
	 * 获取所有的角色信息
	 * @return
	 */
	public List<SysRoleInfo> findRoleList();
	
	/**
	 * 获取某个角色的信息
	 * @param roleId
	 * @return
	 */
	public SysRoleInfo findByIdRole(String roleId);
	
	/**
	 * 删除某个角色的信息
	 * @param roleId
	 * @return
	 */
	public int deleteByIdRole(String roleId);
	/**
	 * 增加某个角色的信息
	 * @param sysRoleInfo
	 * @return
	 */
	public int addSysRoleInfo(SysRoleInfo sysRoleInfo,String[] authIds);
	/**
	 * 修改角色信息
	 * @param sysRoleInfo
	 * @return
	 */
	public int updateByIdRole(SysRoleInfo sysRoleInfo,String[] authIds);
	/**
	 * 根据角色名称来查询角色
	 * @param sysRoleName
	 * @return
	 */
	public SysRoleInfo findRoleByName(String sysRoleName);
	
}

package com.cn.tonyou.mapper;
import java.util.List;
import com.cn.tonyou.pojo.SysAuthInfo;
/**
 * 权限管理
 * @author Administrator
 *
 */
public interface SysAuthInfoMapper {
	/**
	 * 获取所有的权限信息
	 * @return
	 */
	public List<SysAuthInfo> findAuthList();
	
	/**
	 * 获取某个权限的信息
	 * @param AuthId
	 * @return
	 */
	public SysAuthInfo findByIdAuth(String AuthId);
	
	/**
	 * 删除某个权限的信息
	 * @param AuthId
	 * @return
	 */
	public int deleteByIdAuth(String AuthId);
	/**
	 * 增加某个权限的信息
	 * @param sysAuthInfo
	 * @return
	 */
	public int addSysAuthInfo(SysAuthInfo sysAuthInfo);
	/**
	 * 修改权限信息
	 * @param sysAuthInfo
	 * @return
	 */
	public int updateByIdAuth(SysAuthInfo sysAuthInfo);
	
}

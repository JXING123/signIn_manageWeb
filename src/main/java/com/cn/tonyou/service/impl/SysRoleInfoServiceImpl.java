package com.cn.tonyou.service.impl;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.tonyou.mapper.SysRoleAuthMapper;
import com.cn.tonyou.mapper.SysRoleInfoMapper;
import com.cn.tonyou.pojo.SysRoleAuth;
import com.cn.tonyou.pojo.SysRoleInfo;
import com.cn.tonyou.service.ISysRoleInfoService;
import com.cn.tonyou.shiro.MyShiro;
import com.cn.tonyou.utils.DateUtil;
import com.cn.tonyou.utils.UUIDGenerator;

/**
 * 权限管理
 * @author Administrator
 *
 */
@Service
public class SysRoleInfoServiceImpl implements ISysRoleInfoService{
	@Autowired
	private SysRoleInfoMapper sysRoleInfoMapper;
	@Autowired
	private SysRoleAuthMapper sysRoleAuthMapper;

	public List<SysRoleInfo> findRoleList() {
		return sysRoleInfoMapper.findRoleList();
	}

	public SysRoleInfo findByIdRole(String roleId) {
		return sysRoleInfoMapper.findByIdRole(roleId);
	}

	public int deleteByIdRole(String roleId) {
		//删除该角色所有的权限
		sysRoleAuthMapper.deleteByIdRoleAuth(roleId);
		return sysRoleInfoMapper.deleteByIdRole(roleId);
	}

	public int addSysRoleInfo(SysRoleInfo sysRoleInfo,String[] authIds) {
		String sysRoleId = UUIDGenerator.getUUID();
		sysRoleInfo.setSysRoleId(sysRoleId);
		sysRoleInfo.setInsertCreateRoleTime(DateUtil.format(new Date(), DateUtil.DATE_TIME_PATTERN));
		int addSysRoleInfo = sysRoleInfoMapper.addSysRoleInfo(sysRoleInfo);
		if (addSysRoleInfo>=1 && authIds!=null) {
			for (String authId : authIds) {
				SysRoleAuth sysRoleAuth = new SysRoleAuth();
				sysRoleAuth.setSraId(UUIDGenerator.getUUID());
				sysRoleAuth.setSysAuthId(authId);
				sysRoleAuth.setSysRoleId(sysRoleId);
				sysRoleAuthMapper.addSysRoleAuth(sysRoleAuth);
			}
		}
		return addSysRoleInfo;
	}

	public int updateByIdRole(SysRoleInfo sysRoleInfo,String[] authIds) {
		//删除该角色所有的权限
		sysRoleAuthMapper.deleteByIdRoleAuth(sysRoleInfo.getSysRoleId());
		//添加用户新选择的权限
		if (authIds!=null) {
			for (String authId : authIds) {
				SysRoleAuth sysRoleAuth = new SysRoleAuth();
				sysRoleAuth.setSraId(UUIDGenerator.getUUID());
				sysRoleAuth.setSysAuthId(authId);
				sysRoleAuth.setSysRoleId(sysRoleInfo.getSysRoleId());
				sysRoleAuthMapper.addSysRoleAuth(sysRoleAuth);
			}
		}
		//清空shiro缓存重新获取授权
		new MyShiro().clearAllCachedAuthorizationInfo();
		return sysRoleInfoMapper.updateByIdRole(sysRoleInfo);
	}

	public SysRoleInfo findRoleByName(String sysRoleName) {
		return sysRoleInfoMapper.findRoleByName(sysRoleName);
	}
	
}

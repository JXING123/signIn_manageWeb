package com.cn.tonyou.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cn.tonyou.mapper.SysAuthInfoMapper;
import com.cn.tonyou.pojo.SysAuthInfo;
import com.cn.tonyou.service.ISysAuthInfoService;

/**
 * 权限管理
 * @author Administrator
 *
 */
@Service
public class SysAuthInfoServiceImpl implements ISysAuthInfoService{
	@Autowired
	private SysAuthInfoMapper sysAuthInfoMapper;

	public List<SysAuthInfo> findAuthList() {
		return sysAuthInfoMapper.findAuthList();
	}

	public SysAuthInfo findByIdAuth(String AuthId) {
		return sysAuthInfoMapper.findByIdAuth(AuthId);
	}

	public int deleteByIdAuth(String AuthId) {
		return sysAuthInfoMapper.deleteByIdAuth(AuthId);
	}

	public int addSysAuthInfo(SysAuthInfo sysAuthInfo) {
		return sysAuthInfoMapper.addSysAuthInfo(sysAuthInfo);
	}

	public int updateByIdAuth(SysAuthInfo sysAuthInfo) {
		return sysAuthInfoMapper.updateByIdAuth(sysAuthInfo);
	}
	
}

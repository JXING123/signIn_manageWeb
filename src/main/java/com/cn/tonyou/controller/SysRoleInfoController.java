package com.cn.tonyou.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cn.tonyou.pojo.SysAuthInfo;
import com.cn.tonyou.pojo.TreeInfoModel;
import com.cn.tonyou.pojo.SysRoleInfo;
import com.cn.tonyou.service.ISysAuthInfoService;
import com.cn.tonyou.service.ISysRoleInfoService;
import com.google.gson.Gson;

/**
 * 角色管理
 * @author Administrator
 */
@Controller
public class SysRoleInfoController {
	@Resource
	ISysRoleInfoService sysRoleInfoService;
	@Resource
	ISysAuthInfoService sysAuthInfoService;
	/**
	 * 获取所有角色信息
	 */
	@RequiresPermissions(value="查询角色")
	@RequestMapping(value = "findRoleList")
	public String findRoleList(ModelMap map){
		List<SysRoleInfo> findRoleList = sysRoleInfoService.findRoleList();
		map.addAttribute("findRoleList",findRoleList);
		return "reception/sysRoleInfoManage";
	}
	/**
	 * 获取某个角色信息
	 */
	@RequiresPermissions(value="查询角色")
	@RequestMapping(value = "findByIdRole",method=RequestMethod.GET)
	public String findByIdRole(ModelMap map,String roleId){
		SysRoleInfo roleInfo = sysRoleInfoService.findByIdRole(roleId);
		//获取到该角色所有的权限
		Set<SysAuthInfo> sysAuthInfos = roleInfo.getSysAuthInfos();
		List<SysAuthInfo> findAuthList = sysAuthInfoService.findAuthList();
		//创建一个权限模型来存储权限对象
		List<TreeInfoModel> allAuths = new ArrayList<TreeInfoModel>();
		for(SysAuthInfo sai : findAuthList){
			TreeInfoModel sa = new TreeInfoModel();
			sa.setId(sai.getSysAuthId());
			sa.setpId(sai.getSysAuthParentId());
			sa.setName(sai.getSysAuthName());
			for(SysAuthInfo s : sysAuthInfos){
				if(s.getSysAuthId().equals(sai.getSysAuthId())){
					sa.setChecked(true);
				}
			}
			allAuths.add(sa);
		}
		Gson gson = new Gson();
		String gsonAllAuths = gson.toJson(allAuths);
		map.addAttribute("roleInfo",roleInfo);
		map.addAttribute("gsonAllAuths", gsonAllAuths);
		return "reception/sysRoleInfoDetails";
	}
	
	/**
	 * 删除某个角色信息
	 */
	@RequiresPermissions(value="删除角色")
	@RequestMapping(value = "deleteByIdRole",method=RequestMethod.GET)
	public String deleteByIdRole(ModelMap map,String roleId){
		sysRoleInfoService.deleteByIdRole(roleId);
		return "forward:findRoleList";
	}
	/**
	 * 增加某个角色信息
	 */
	@RequiresPermissions(value="新增角色")
	@RequestMapping(value = "addSysRoleInfo")
	public String addSysRoleInfo(ModelMap map,SysRoleInfo sysRoleInfo,String[] authIds){
		sysRoleInfoService.addSysRoleInfo(sysRoleInfo,authIds);
		return "forward:findRoleList";
	}
	/**
	 * 跳转到添加角色信息页面
	 */
	@RequiresPermissions(value="新增角色")
	@RequestMapping(value = "sysRoleInfoAddFtl",method=RequestMethod.GET)
	public String sysRoleInfoAddFtl(ModelMap map){
		//获取到所有的权限信息
		List<SysAuthInfo> findAuthList = sysAuthInfoService.findAuthList();
		//定义权限树模型转发到页面
		List<TreeInfoModel> allAuths = new ArrayList<TreeInfoModel>();
		for(SysAuthInfo sai: findAuthList){
			TreeInfoModel sa = new TreeInfoModel();
			sa.setId(sai.getSysAuthId());
			sa.setpId(sai.getSysAuthParentId());
			sa.setName(sai.getSysAuthName());
			allAuths.add(sa);
		}
		Gson gson = new Gson();
		String gsonAllAuths = gson.toJson(allAuths); 
		map.addAttribute("gsonAllAuths", gsonAllAuths);
		return "reception/sysRoleInfoAdd";
	}
	/**
	 * 修改某个角色信息
	 */
	@RequiresPermissions(value="修改角色")
	@RequestMapping(value = "updateByIdRole")
	public String updateByIdRole(ModelMap map,SysRoleInfo sysRoleInfo,String[] authIds){
		sysRoleInfoService.updateByIdRole(sysRoleInfo,authIds);
		return "forward:findRoleList";
	}
	/**
	 * 判断某个角色名称是否存在
	 * @param sysRoleName
	 */
	@RequestMapping(value="checkAddsysRoleName")
	public void checkAddsysRoleName(String sysRoleName,HttpServletResponse response){
		try {
			SysRoleInfo findRoleByName = sysRoleInfoService.findRoleByName(sysRoleName);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			if (findRoleByName!=null) {
				writer.println(1);
			}else{
				writer.println(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

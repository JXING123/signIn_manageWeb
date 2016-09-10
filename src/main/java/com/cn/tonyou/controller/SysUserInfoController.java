package com.cn.tonyou.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cn.tonyou.pojo.SchoolInfo;
import com.cn.tonyou.pojo.TreeInfoModel;
import com.cn.tonyou.pojo.SysRoleInfo;
import com.cn.tonyou.pojo.SysUserInfo;
import com.cn.tonyou.service.IClassInfoService;
import com.cn.tonyou.service.ISchoolInfoService;
import com.cn.tonyou.service.ISysRoleInfoService;
import com.cn.tonyou.service.ISysUserInfoService;
import com.cn.tonyou.shiro.MyShiro.ShiroUser;
import com.cn.tonyou.utils.DateUtil;
import com.cn.tonyou.utils.MD5Util;
import com.google.gson.Gson;
/**
 * 用户管理
 * @author HY
 *
 */
@RequiresPermissions(value="用户管理")
@Controller
public class SysUserInfoController {
	@Resource
	ISysUserInfoService sysUserInfoService;
	@Resource
	IClassInfoService classInfoService;
	@Resource
	ISysRoleInfoService sysRoleInfoService;
	@Resource
	ISchoolInfoService schoolInfoService;
	/** 
	 * 进入登入页面
	 */
	@RequestMapping("/goLogin")
	public void goLoginContol(HttpServletRequest req,HttpServletResponse repon) {
	      PrintWriter out;
		try {
			out = repon.getWriter();
			 out.println("<html>");  
		     out.println("<script type='text/javascript'>");  
		     out.println("window.open ('login.jsp','_top')");  
		     out.println("</script>");  
		     out.println("</html>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用户登入
	 * @param sysUserName
	 * @param sysUserPassWord
	 * @param req
	 * @return
	 */
	@RequestMapping(value="login")//,method = RequestMethod.POST
	public String userLogin(String sysUserName,String sysUserPassWord,ModelMap modelMap) {
		if (sysUserName==null || sysUserName.equals("") || sysUserPassWord.equals("") || sysUserPassWord ==null) {
			return "forward:goLogin";
		}
		Session session = null;
		//使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！  
		UsernamePasswordToken token = new UsernamePasswordToken(sysUserName, sysUserPassWord);
		try {
			Subject subject = SecurityUtils.getSubject();
			session = subject.getSession();
			//如果用户已登录，先踢出
			subject.login(token);
			ShiroUser principal = (ShiroUser) subject.getPrincipal();
			//修改用户的登陆信息
			SysUserInfo userById = sysUserInfoService.getUserById(principal.id);
			userById.setLastLoginTime(DateUtil.format(new Date(), DateUtil.DATE_TIME_PATTERN));
			int loginCount = userById.getLoginCount();
			userById.setLoginCount(loginCount++);
			userById.setSysUserState("在线");
			sysUserInfoService.updateByIdSysUser(userById, null);
			if (principal.schoolId!=null) {
				SchoolInfo findByIdSchool = schoolInfoService.findByIdSchool(principal.schoolId);
				session.setAttribute("school", findByIdSchool);
			}
			session.setAttribute("name", principal.loginName);
			return "reception/mainFrame";
		}catch (IncorrectCredentialsException e) {  
			session.setAttribute("message", getAlertValue("登录密码错误. Password for account " + token.getPrincipal() + " was incorrect."));
        } catch (ExcessiveAttemptsException e) {  
            session.setAttribute("message", getAlertValue("登录失败次数过多"));
        } catch (LockedAccountException e) {  
            session.setAttribute("message",getAlertValue("帐号已被锁定. 请联系管理员 " + token.getPrincipal()));
        } catch (DisabledAccountException e) {  
            System.out.println("帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.");  
        } catch (ExpiredCredentialsException e) {  
            session.setAttribute("message",getAlertValue("帐号已过期. the account for username " + token.getPrincipal() + "  was expired."));
        } catch (UnknownAccountException e) {  
            session.setAttribute("message",getAlertValue("帐号不存在. There is no user with username of " + token.getPrincipal()));
        }
		return "forward:goLogin"; 
	}
	/**
	 * 错误提示
	 * @param val
	 * @return
	 */
	public static String getAlertValue(String val) {
		String alertValue = "<script type='text/javascript'>$(function(){ShowFailure('"+val+"');});</script>";
		return alertValue;
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)    
	public String logout(RedirectAttributes redirectAttributes){   
	        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息  
	        Subject subject = SecurityUtils.getSubject();
	        ShiroUser principal = (ShiroUser) subject.getPrincipal();
			subject.logout(); 
	        SysUserInfo userById = sysUserInfoService.getUserById(principal.id);
	        userById.setSysUserState("离线");
	        sysUserInfoService.updateByIdSysUser(userById, null);
	        redirectAttributes.addFlashAttribute("message", "您已安全退出");    
	        return "redirect:/login.jsp";  
	}   
	/**
	 * 没有权限要跳转的页面      
	 * @return
	 */
    @RequestMapping("/403")  
    public String unauthorizedRole(){  
        return "redirect:/403.jsp";  
    }  
	/**
	 * 取出Shiro中的当前用户Id.
	 */
	protected String getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (user != null) {
			return user.id;
		} else {
			return "false";
		}
	}
	
	
	/**
	 * 查询该学校所有用户
	 */
	@RequiresPermissions(value="查询用户")
	@RequestMapping("findSysUserInfoList")
	public String getAllSysUser(ModelMap map,String schoolId){
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Map<String, String> paramsMap = new HashMap<String, String>();
		List<SchoolInfo> schoolInfoList = null;
		//获取到当前用户的学校
		if (shiroUser.schoolId!=null) {
			schoolId = shiroUser.schoolId;
		}else {
			schoolInfoList = schoolInfoService.selectSchoolList();
		}
		//根据条件获取用户信息
		paramsMap.put("schoolId", schoolId);
		//paramsMap.put("departmentId", departmentId);
		//paramsMap.put("classId", classId);
		List<SysUserInfo> sysUserList = sysUserInfoService.findSysUserInfoList(paramsMap);
		//查询所有班级
		//查询所有角色
		List<SysRoleInfo> findRoleList = sysRoleInfoService.findRoleList();
		map.addAttribute("findRoleList", findRoleList);
		map.addAttribute("sysUserList", sysUserList);
		map.addAttribute("schoolId", schoolId);
		map.addAttribute("schoolInfoList", schoolInfoList);
		return "reception/sysUserInfoManage";
	}
	/**
	 * 获取该学校角色为老师的所有用户
	 * @param repon
	 */
	@RequestMapping(value="findRoleTeacherSysUserList")
	public void findRoleTeacherSysUserList(String schoolId,HttpServletResponse repon) {
		repon.setContentType("text/html; charset=utf-8");
		try {
			PrintWriter writer = repon.getWriter();
			ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			if (shiroUser.schoolId!=null) {
				schoolId = shiroUser.schoolId;
			}
			List<SysUserInfo> sysUserList = sysUserInfoService.findRoleTeacherSysUserList(schoolId,null);
			Gson gson = new Gson();
			String json = gson.toJson(sysUserList);
			writer.println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取该学校角色为辅导员的所有用户
	 * @param repon
	 */
	@RequestMapping(value="findRoleCoachSysUserList")
	public void findRoleCoachSysUserList(String schoolId,HttpServletResponse repon) {
		repon.setContentType("text/html; charset=utf-8");
		try {
			PrintWriter writer = repon.getWriter();
			ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			if (shiroUser.schoolId!=null) {
				schoolId = shiroUser.schoolId;
			}
			List<SysUserInfo> sysUserList = sysUserInfoService.findRoleCoachSysUserList(schoolId);
			Gson gson = new Gson();
			String json = gson.toJson(sysUserList);
			writer.println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 添加用户
	 */
	@RequiresPermissions(value="新增用户")
	@RequestMapping("addSysUserInfo")
	public String addSysUser(SysUserInfo sysUserInfo,String[] roleIds,String shcoolId){
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String passWordMd5 = MD5Util.getMD5(sysUserInfo.getSysUserPassWord());
		if (shiroUser.schoolId != null) {
			shcoolId = shiroUser.schoolId;
			SchoolInfo schoolInfo = new SchoolInfo();
			schoolInfo.setSchoolId(shcoolId);
			sysUserInfo.setSchool(schoolInfo);
		}
		sysUserInfo.setSysUserPassWord(passWordMd5);
		sysUserInfoService.addSysUserInfo(sysUserInfo, roleIds);
		return "forward:findSysUserInfoList";
	}
	/**
	 * 跳转到添加用户信息页面
	 */
	@RequiresPermissions(value="新增用户")
	@RequestMapping(value = "sysUserInfoAddFtl",method=RequestMethod.GET)
	public String sysInfoAddFtl(ModelMap map){
		//获取到所有的角色信息
		List<SysRoleInfo> findRoleList = sysRoleInfoService.findRoleList();
		//定义权限树模型转发到页面
		List<TreeInfoModel> allRoles = new ArrayList<TreeInfoModel>();
		for(SysRoleInfo sai: findRoleList){
			TreeInfoModel sa = new TreeInfoModel();
			sa.setId(sai.getSysRoleId());
			sa.setName(sai.getSysRoleName());
			allRoles.add(sa);
		}
		Gson gson = new Gson();
		String gsonAllRoles = gson.toJson(allRoles); 
		//获取所有的学校信息
		List<SchoolInfo> schoolList = schoolInfoService.selectSchoolList();
		map.addAttribute("gsonAllRoles", gsonAllRoles);
		map.addAttribute("schoolList", schoolList);
		return "reception/sysUserInfoAdd";
	}
	/**
	 * 获取某个用户的信息
	 */
	@RequiresPermissions(value="查询用户")
	@RequestMapping(value = "findByIdSysUser",method=RequestMethod.GET)
	public String findByIdSysUser(ModelMap map,String sysUserId){
		SysUserInfo sysUser = sysUserInfoService.getUserById(sysUserId);
		//获取到所有的角色信息
		List<SysRoleInfo> roleList = sysRoleInfoService.findRoleList();
		//定义权限树模型转发到页面
		List<TreeInfoModel> allRoles = new ArrayList<TreeInfoModel>();
		//获取到该角色所有的权限
		Set<SysRoleInfo> sysRoleInfos = sysUser.getSysRoleInfos();
		for(SysRoleInfo sysRoleInfo:roleList){
			TreeInfoModel treeInfoModel = new TreeInfoModel();
			treeInfoModel.setId(sysRoleInfo.getSysRoleId());
			treeInfoModel.setName(sysRoleInfo.getSysRoleName());
			for(SysRoleInfo sysRoleInfo1 :sysRoleInfos){
				if (sysRoleInfo.getSysRoleId().equals(sysRoleInfo1.getSysRoleId())) {
					treeInfoModel.setChecked(true);
				}
			}
			allRoles.add(treeInfoModel);
		}
 		Gson gson = new Gson();
		String gsonAllRoles = gson.toJson(allRoles);
		map.addAttribute("sysUser", sysUser);
		map.addAttribute("gsonAllRoles",gsonAllRoles);
		
		return "reception/sysUserInfoDetails";
	}
	
	/**
	 * 获取某个用户的信息
	 */
	@RequestMapping(value = "checkAddSysUser")
	public void checkAddSysUser(String sysUserId,HttpServletResponse repon){
		SysUserInfo sysUser = sysUserInfoService.getUserById(sysUserId);
		repon.setContentType("text/html; charset=utf-8");
		try {
			PrintWriter writer = repon.getWriter();
			if (sysUser!=null) {
				writer.println(1);
			}else{
				writer.println(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改用户信息
	 */
	@RequiresPermissions(value="修改用户")
	@RequestMapping(value = "updateByIdSysUser")
	public String updateByIdSysUser(ModelMap map,SysUserInfo sysUser,String[] roleIds){
		sysUserInfoService.updateByIdSysUser(sysUser, roleIds);
		return "forward:findSysUserInfoList";
	}
	/**
	 * 删除用户信息
	 * @param map
	 * @param sysUserId
	 * @return
	 */
	@RequiresPermissions(value="删除用户")
	@RequestMapping(value="deleteByIdSysUser")
	public String deleteByIdSysUser(String sysUserId){
		sysUserInfoService.deleteByIdSysUser(sysUserId);
		return "forward:findSysUserInfoList";
	}
}
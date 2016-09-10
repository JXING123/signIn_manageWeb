package com.cn.tonyou.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.tonyou.pojo.ClassInfo;
import com.cn.tonyou.pojo.DepartmentInfo;
import com.cn.tonyou.pojo.DormInfo;
import com.cn.tonyou.pojo.SchoolInfo;
import com.cn.tonyou.service.IClassInfoService;
import com.cn.tonyou.service.IDepartmentInfoService;
import com.cn.tonyou.service.IDormInfoService;
import com.cn.tonyou.service.ISchoolInfoService;
import com.cn.tonyou.shiro.MyShiro.ShiroUser;
import com.google.gson.Gson;
/**
 * 宿舍管理
 * @author Administrator
 *
 */
@RequiresPermissions(value="宿舍管理")
@Controller
public class DormInfoController {
	@Resource
	IDormInfoService dormInfoService;
	@Resource
	IDepartmentInfoService departmentInfoService;
	@Resource
	ISchoolInfoService schoolInfoService;
	@Resource
	IClassInfoService classInfoService;//班级
	
	/**
	 * 查询所有宿舍
	 */
	@RequiresPermissions(value="查询宿舍")
	@RequestMapping("selectAllDormInfo")
	public String selectAllDormInfo(ModelMap map,String schoolId,String departmentId,String classId){
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		List<SchoolInfo> schoolInfoList = null;
		List<ClassInfo> classInfoList = null;
		List<DepartmentInfo> departmentInfoList = null;
		//获取到当前用户的学校
		if (shiroUser.schoolId!=null) {
			schoolId = shiroUser.schoolId;
		}else {
			schoolInfoList = schoolInfoService.selectSchoolList();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();
		//根据所需条件获取获取考勤信息
		paramsMap.put("schoolId", schoolId);
		paramsMap.put("departmentId", departmentId);
		paramsMap.put("classId", classId);
		if (schoolId!=null) {
			departmentInfoList = departmentInfoService.findListDepartmentInfo(schoolId);
		}
		
		//查询某个系别所有的班级
		if (departmentId!=null && !departmentId.equals("")) {
			classInfoList = classInfoService.findListClassInfo(paramsMap);
		}
		String clSysUserId = "";
		if (!subject.hasRole("系统管理员") && !subject.hasRole("学校管理员")){
			if (subject.hasRole("辅导员")) {
				clSysUserId = shiroUser.id;
			}
			paramsMap.put("clSysUserId", clSysUserId);
			//获取到自己管理的班级信息
			classInfoList = classInfoService.findSysClassId(paramsMap);
		}
		
		List<DormInfo> dormInfo = dormInfoService.findDormList(paramsMap);
		//返回到页面
		map.addAttribute("schoolInfoList", schoolInfoList);
		map.addAttribute("classInfoList", classInfoList);
		map.addAttribute("departmentInfoList", departmentInfoList);
		map.addAttribute("schoolId", schoolId);
		map.addAttribute("classId", classId);
		map.addAttribute("departmentId", departmentId);
		map.addAttribute("dormInfoList",dormInfo);	
		return "reception/dormInfoManage";
	}
	/**
	 * 查询所有宿舍
	 */
	@RequestMapping("getDormInfo")
	public void getDormInfo(String classId,ModelMap map,HttpServletRequest req,HttpServletResponse repon){
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String schoolId = shiroUser.schoolId;		
		List<DormInfo> dormInfo = dormInfoService.getDormInfo2(schoolId,classId);		
		Gson geon = new Gson();
		String json = geon.toJson(dormInfo);		
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();			
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加宿舍
	 */
	@RequiresPermissions(value="新增宿舍")
	@RequestMapping("addDormInfo")
	public String addDormInfo(DormInfo dormInfo){
		dormInfoService.addDormInfo(dormInfo);
		return "forward:selectAllDormInfo";
	}
	/**
	 * 添加宿舍页面
	 */
	@RequiresPermissions(value="新增宿舍")
	@RequestMapping("addDormInfoFtl")
	public String addDormInfoFtl(ModelMap map){
		//获取所有的学校信息
		List<SchoolInfo> schoolList = schoolInfoService.selectSchoolList();
		List<DepartmentInfo> departmentInfoList = null;
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		if (shiroUser.schoolId!=null) {
			departmentInfoList = departmentInfoService.findListDepartmentInfo(shiroUser.schoolId);
		}
		map.addAttribute("schoolList", schoolList);
		map.addAttribute("departmentInfoList", departmentInfoList);
		return "reception/dormInfoAdd";
	}
	
	/**
	 * 删除宿舍
	 */
	@RequiresPermissions(value="删除宿舍")
	@RequestMapping("deleteDormInfo")
	public String deleteDormInfo(String dormId){
		dormInfoService.deleteDormInfo(dormId);
		return "forward:selectAllDormInfo";
	}
	
	/** 
	 * 根据宿舍id查询宿舍详情
	 */
	@RequiresPermissions(value="查询宿舍")
	@RequestMapping("findDormInfoById")
	public String findDormInfoById(String dormId,ModelMap map){
		DormInfo findDormInfoById = dormInfoService.findDormInfoById(dormId);
		map.addAttribute("findDormInfoById", findDormInfoById);
		return "reception/dormInfoDetails";
	}
	
	/**
	 * 修改宿舍
	 */
	@RequiresPermissions(value="查询宿舍")
	@RequestMapping("updateDormInfo")
	public String updateDormInfo(DormInfo dormInfo){
		dormInfoService.updateDormInfo(dormInfo);
		return "forward:selectAllDormInfo";
	}
	/**
	 * 根据班级获取宿舍信息
	 */
	@RequestMapping(value="findDormInfoByClassId")
	public void findDormInfoByClassId(HttpServletResponse repon,String classId){
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Gson gson = new Gson();	
		try {		
			repon.setContentType("text/html; charset=utf-8");		
			List<DormInfo> dormInfo = dormInfoService.getDormInfo2(shiroUser.schoolId, classId);
			String json = gson.toJson(dormInfo);	
			PrintWriter writer = repon.getWriter();		
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {		
			e.printStackTrace();	
		}
	}
}




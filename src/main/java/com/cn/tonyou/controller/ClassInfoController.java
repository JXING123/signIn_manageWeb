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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cn.tonyou.pojo.ClassInfo;
import com.cn.tonyou.pojo.DepartmentInfo;
import com.cn.tonyou.pojo.SchoolInfo;
import com.cn.tonyou.service.IClassInfoService;
import com.cn.tonyou.service.IDepartmentInfoService;
import com.cn.tonyou.service.ISchoolInfoService;
import com.cn.tonyou.shiro.MyShiro.ShiroUser;
import com.google.gson.Gson;

/**
 * 班级管理
 * @author Administrator
 *
 */
@RequiresPermissions(value="班级管理")
@Controller
public class ClassInfoController {
	@Autowired
	IClassInfoService classInfoService;
	@Resource
	IDepartmentInfoService departmentInfoService;
	@Resource
	ISchoolInfoService schoolInfoService;
	  
	/**
	 * 查询所有班级
	 */
	@RequiresPermissions(value="查询班级")
	@RequestMapping("findClassList")
	public String findClaaInfo(ModelMap map,String schoolId,String departmentId){
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		List<SchoolInfo> schoolInfoList = null;
		List<DepartmentInfo> departmentInfoList = null;
		if (shiroUser.schoolId!=null) {
			schoolId = shiroUser.schoolId;
		}else{
			schoolInfoList = schoolInfoService.selectSchoolList();
		}
		//查询所有系别
		if (schoolId!=null) {
			departmentInfoList = departmentInfoService.findListDepartmentInfo(schoolId);
		}
		Map<String, String> paramsMap = new HashMap<String, String>();
		//根据所需条件班级信息
		paramsMap.put("schoolId", schoolId);
		paramsMap.put("departmentId", departmentId);
		List<ClassInfo> findListClassInfo = classInfoService.findListClassInfo(paramsMap);
		map.addAttribute("schoolInfoList", schoolInfoList);
		map.addAttribute("departmentInfoList", departmentInfoList);
		map.addAttribute("findListClassInfo", findListClassInfo);
		map.addAttribute("schoolId", schoolId);
		map.addAttribute("departmentId", departmentId);
		return "reception/classInfoManage";
	}
	/**
	 * 查询某个学校所有的班级
	 */
	@RequiresPermissions(value="查询班级")
	@RequestMapping("selectAllClasses")
	public void selectAllClasses(String schoolId,HttpServletResponse repon){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (user.schoolId!=null) {
			schoolId = user.schoolId;
		}
		List<ClassInfo> getClassinfo = classInfoService.getClassinfo(schoolId);		
		Gson geon = new Gson();
		String json = geon.toJson(getClassinfo);
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除班级
	 */
	@RequiresPermissions(value="删除班级")
	@RequestMapping("deleteClassInfo")
	public String deleteClassInfo(String classId){
		classInfoService.deleteClassInfo(classId);
		return "forward:findClassList";
	}
	
	/**
	 * 添加班级
	 */
	@RequiresPermissions(value="新增班级")
	@RequestMapping("addClassInfo")
	public String addClassInfo(ClassInfo classInfo){
		classInfoService.addClassInfo(classInfo);
		return "forward:findClassList";
	}
	
	/**
	 * 添加班级时 根据系别id和班级名称来判断该系别是否存在该班级
	 */
	@RequestMapping("checkAddClassinfo")
	public void checkAddClassinfo(String departmentId,String className,HttpServletRequest req,HttpServletResponse repon){
		//根据系别id查询这个系是否有这个班级名
		List<ClassInfo> checkAddClassinfo = classInfoService.checkAddClassinfo(departmentId,className);
		int a = 0;
		//判断是否查询出班级 如果有班级就为1，否则为0
		if(!checkAddClassinfo.isEmpty()){
			a = 1;
		}
		Gson geon = new Gson();
		String json = geon.toJson(a);
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据班级id查询班级
	 */
	@RequiresPermissions(value="查询班级")
	@RequestMapping("findByIdClass")
	public String findByIdClass(String classId,ModelMap map){
		ClassInfo findByIdClass = classInfoService.findByIdClass(classId);
		//遍历截取字符串
		map.addAttribute("findByIdClass", findByIdClass);
		return "reception/classInfoDetails";
	}
	
	/**
	 * 修改班级
	 */
	@RequiresPermissions(value="修改班级")
	@RequestMapping("updateClassInfo")
	public String updateClassInfo(ClassInfo classInfo){
		classInfoService.updateClassInfo(classInfo);
		return "forward:findClassList";
	}
	/**
	 * 点击系别所对应的班级
	 */
	@RequiresPermissions(value="查询班级")
	@RequestMapping("getClass")
	public void getClass(String departmentId,HttpServletResponse repon){
		//根据系别id查询班级
		Map<String, String> paramsMap = new HashMap<String, String>();
		//根据所需条件班级信息
		paramsMap.put("departmentId", departmentId);
		List<ClassInfo> findListClassInfo = classInfoService.findListClassInfo(paramsMap);
		Gson geon = new Gson();
		String json = geon.toJson(findListClassInfo);
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

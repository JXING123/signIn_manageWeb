package com.cn.tonyou.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cn.tonyou.pojo.DepartmentInfo;
import com.cn.tonyou.pojo.SchoolInfo;
import com.cn.tonyou.service.IDepartmentInfoService;
import com.cn.tonyou.service.ISchoolInfoService;
import com.cn.tonyou.shiro.MyShiro.ShiroUser;
import com.google.gson.Gson;

/**
 * 系别管理
 * @author Administrator
 *
 */
@RequiresPermissions(value="系别管理")
@Controller
public class DepartmentInfoController {
	@Resource
	IDepartmentInfoService departmentInfoService;
	@Resource
	ISchoolInfoService schoolInfoService;
	/**
	 * 查询所有系别
	 */
	@RequiresPermissions(value="查询系别")
	@RequestMapping("findDepartmentInfo")
	public String findDepartmentInfo(ModelMap map,String schoolId){
		//获取登陆用户的信息
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		//获取到当前用户的学校
		if (shiroUser.schoolId!=null) {
			schoolId = shiroUser.schoolId;
		}
		List<SchoolInfo> schoolInfoList = schoolInfoService.selectSchoolList();
		List<DepartmentInfo> findListDepartmentInfo = departmentInfoService.findListDepartmentInfo(schoolId);
		map.addAttribute("schoolInfoList", schoolInfoList);
		map.addAttribute("findListDepartmentInfo", findListDepartmentInfo);
		map.addAttribute("schoolId", schoolId);
		return "reception/departmentInfoManage";
	}
	/**
	 * 根据学校id查询该学校所有的系别
	 * @param map
	 * @param repon
	 * @param schoolId
	 */
	@RequestMapping("selectAllDepartment")
	public void selectAllDepartment(ModelMap map,HttpServletResponse repon,String schoolId){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (user.schoolId!=null) {
			schoolId = user.schoolId;
		}
		List<DepartmentInfo> findListDepartmentInfo = departmentInfoService.findListDepartmentInfo(schoolId);	
		Gson geon = new Gson();
		String json = geon.toJson(findListDepartmentInfo);
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 添加系别
	 */
	@RequiresPermissions(value="新增系别")
	@RequestMapping("addDepartmentInfo")
	public String addDepartmentInfo(DepartmentInfo departmentInfo){
		if (departmentInfo.getSchool()==null) {
			SchoolInfo  schoolInfo = new SchoolInfo();
			ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			schoolInfo.setSchoolId(user.schoolId);
			departmentInfo.setSchool(schoolInfo);
		}
		departmentInfoService.addDepartmentInfo(departmentInfo);
		return "forward:findDepartmentInfo";
	}
	
	
	/**
	 * 添加系别时 根据学校id和系别名称来判断该学校是否存在该系别
	 */
	@RequiresPermissions(value="新增系别")
	@RequestMapping("checkAddDepartmentinfo")
	public void checkAddDepartmentinfo(String schoolId,String deprtmentName,HttpServletRequest req,HttpServletResponse repon){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (user.schoolId!=null) {
			schoolId = user.schoolId;
		}
		//根据学校id查询这个学校是否有这个系别
		List<DepartmentInfo> checkAddDepartmentinfo = departmentInfoService.checkAddDepartmentinfo(schoolId,deprtmentName);
		int a = 0;		
		//判断是否查询出班级 如果有班级就为1，否则为0
		if(!checkAddDepartmentinfo.isEmpty()){
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
	 * 删除系别
	 */
	@RequiresPermissions(value="删除系别")
	@RequestMapping("deleteDepartmentInfo")
	public String deleteDepartmentInfo(String departmentId){
		departmentInfoService.deleteDepartmentInfo(departmentId);
		return "forward:findDepartmentInfo";
	}
	
	/**
	 * 根据id查询系别
	 */
	@RequiresPermissions(value="查询系别")
	@RequestMapping("findDepartmentById")
	public String findDepartmentById(String departmentId,ModelMap map){
		DepartmentInfo findDepartmentById = departmentInfoService.findDepartmentById(departmentId);
		
		map.addAttribute("findDepartmentById", findDepartmentById);
		return "reception/departmentInfoDetails";
	}
	
	/**
	 * 修改系别信息
	 */
	@RequiresPermissions(value="修改系别")
	@RequestMapping("updateDepartmentInfo")
	public String updateDepartmentInfo(DepartmentInfo departmentInfo){
		departmentInfoService.updateDepartmentInfo(departmentInfo);
		return "forward:findDepartmentInfo";
	}
}

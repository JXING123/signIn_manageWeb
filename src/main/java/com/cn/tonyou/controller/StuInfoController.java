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
import com.cn.tonyou.pojo.StuInfo;
import com.cn.tonyou.service.IClassInfoService;
import com.cn.tonyou.service.IDepartmentInfoService;
import com.cn.tonyou.service.IDormInfoService;
import com.cn.tonyou.service.ISchoolInfoService;
import com.cn.tonyou.service.IStuInfoService;
import com.cn.tonyou.shiro.MyShiro.ShiroUser;
import com.google.gson.Gson;

/**
 * 学生管理
 * @author HY
 *
 */
@RequiresPermissions(value="学生管理")
@Controller
public class StuInfoController {
	@Resource
	IStuInfoService stuInfoService;
	@Resource 
	IClassInfoService classInfoService;
	@Resource
	IDormInfoService dormInfoService;
	@Resource
	IDepartmentInfoService departmentInfoService;	//系别
	@Resource
	ISchoolInfoService schoolInfoService;
	
	/**
	 * 查询所有学生信息
	 */
	@RequiresPermissions(value="查询学生")
	@RequestMapping("findListStu")
	public String findListStu(ModelMap map,String schoolId,String departmentId,String classId){
		//查询所有学生信息
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		//获取到当前用户的学校
		if (shiroUser.schoolId!=null) {
			schoolId = shiroUser.schoolId;
		}
		List<SchoolInfo> schoolInfoList = schoolInfoService.selectSchoolList();
		List<ClassInfo> classInfoList = null;
		List<DepartmentInfo> departmentInfoList = null;
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
		
		List<StuInfo> findListStu = stuInfoService.findListStu(paramsMap);
		//查询所有班级(添加学生用)
		List<ClassInfo> getClassinfo = classInfoService.getClassinfo(schoolId);
		map.addAttribute("schoolInfoList", schoolInfoList);
		map.addAttribute("findListStu", findListStu);
		map.addAttribute("getClassinfo", getClassinfo);
		map.addAttribute("departmentInfoList", departmentInfoList);
		map.addAttribute("classInfoList", classInfoList);
		map.addAttribute("schoolId", schoolId);
		map.addAttribute("classId", classId);
		map.addAttribute("departmentId", departmentId);
		return "reception/stuInfoManage";
	}
	
	/**
	 * 查询所有学生
	 
	@RequiresPermissions(value="查询学生")
	@RequestMapping("selectAllStuInfo")
	public void selectAllStuInfo(HttpServletRequest req,HttpServletResponse repon){
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String schoolId = shiroUser.schoolId;
		List<StuInfo> findListStu = stuInfoService.findListStu(schoolId);
		Gson geon = new Gson();
		String json = geon.toJson(findListStu);
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	 
	
	
	
	/**
	 * 添加学生
	 */
	@RequiresPermissions(value="新增学生")
	@RequestMapping("addStu")
	public String addStu(StuInfo stuInfo){
		stuInfoService.addStu(stuInfo);
		return "forward:findListStu";
	}
	
	/**
	 * 根据id获取某个学生信息
	 */
	@RequiresPermissions(value="查询学生")
	@RequestMapping("findByIdStu")
	public String findByIdStu(ModelMap map,String stuId) {
		StuInfo findByIdStu = stuInfoService.findByIdStu(stuId);
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("schoolId", findByIdStu.getClasses().getDepartment().getSchool().getSchoolId());
		//获取到该学生所在的学校的所有学生
		List<ClassInfo> classList = classInfoService.findListClassInfo(paramsMap);
		List<DormInfo> dormList = dormInfoService.findDormList(paramsMap);
		map.addAttribute("findByIdStu",findByIdStu);
		map.addAttribute("classList", classList);
		map.addAttribute("dormList", dormList);
		return "reception/stuInfoDetails";
	}
	  
	/**
	 * 删除学生
	 */
	@RequiresPermissions(value="删除学生")
	@RequestMapping("deleteStuInfo")
	public String deleteStuinfo(String stuId) {
		stuInfoService.deleteStuinfo(stuId);
		return "forward:findListStu";
	}
	
	/**
	 * web端修改学生信息
	 * @return
	 */
	@RequiresPermissions(value="修改学生")
	@RequestMapping("updateStuInfo")
	public String updateStuInfo(StuInfo stuInfo) {
		stuInfoService.updateStuInfo(stuInfo);
		return "forward:findListStu";
	}
	
	
	/**
	 * 根据班级id查询学生表
	 */
	@RequiresPermissions(value="查询学生")
	@RequestMapping("selectStuInfoByClassId")
	public void selectStuInfoByClassId(String classId,HttpServletRequest req,HttpServletResponse repon){
		List<StuInfo> selectStuInfoByClassId = stuInfoService.selectStuInfoByClassId(classId);
		int a = 0;
		//判断是否查询出学生如果有学生就为1，否则为0
		if(!selectStuInfoByClassId.isEmpty()){
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
	 * 根据班级id查询学生
	 */
	@RequiresPermissions(value="查询学生")
	@RequestMapping("getStuByClassId")
	public void getStuByClassId(String classId,HttpServletRequest req,HttpServletResponse repon){
		List<StuInfo> selectStuInfoByClassId = stuInfoService.selectStuInfoByClassId(classId);
		Gson geon = new Gson();
		String json = geon.toJson(selectStuInfoByClassId);
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 根据宿舍id查询学生表
	 */
	@RequiresPermissions(value="查询学生")
	@RequestMapping("selectStuInfoByDormId")
	public void selectStuInfoByDormId(String dormId,HttpServletRequest req,HttpServletResponse repon){
		List<StuInfo> selectStuInfoByDormId = stuInfoService.selectStuInfoByDormId(dormId);
		int a = 0;
		//判断是否查询出学生如果有学生就为1，否则为0
		if(!selectStuInfoByDormId.isEmpty()){
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
	 * 修改审核状态
	 */
	@RequiresPermissions(value="修改学生")
	@RequestMapping("updateStuState")
	public String updateStuState(StuInfo stuInfo){
		stuInfo.setStuState("已审核");
		stuInfoService.updateStuInfo(stuInfo);
		return "forward:findListStu";
	}
	
	
}

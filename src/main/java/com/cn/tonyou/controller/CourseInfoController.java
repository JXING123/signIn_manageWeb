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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cn.tonyou.pojo.ClassInfo;
import com.cn.tonyou.pojo.CourseInfo;
import com.cn.tonyou.pojo.DepartmentInfo;
import com.cn.tonyou.pojo.SchoolInfo;
import com.cn.tonyou.pojo.SubjectInfo;
import com.cn.tonyou.service.IClassInfoService;
import com.cn.tonyou.service.ICourseInfoService;
import com.cn.tonyou.service.IDepartmentInfoService;
import com.cn.tonyou.service.ISchoolInfoService;
import com.cn.tonyou.service.ISubjectInfoService;
import com.cn.tonyou.shiro.MyShiro.ShiroUser;
import com.google.gson.Gson;
/**
 * 課程管理
 * @author Administrator
 *
 */
@Controller
@RequiresPermissions(value="课程管理")//@RequiresRoles(value="辅导员")//必须要有老师这个角色才能访问
public class CourseInfoController {
	@Resource
	ICourseInfoService	courseInfoService;
	@Resource
	ISchoolInfoService schoolInfoService;
	@Resource
	IDepartmentInfoService departmentInfoService;
	@Resource
	IClassInfoService classInfoService;
	@Resource
	ISubjectInfoService subjectInfoService;
	
	/**
	 * 查詢所有課程
	 */
	@RequiresPermissions(value="查询课程")
	@RequestMapping("selectAllCourse")
	public String selectAllCourse(ModelMap map,String schoolId,String departmentId,String classId){
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		List<SchoolInfo> schoolInfoList = null;
		List<ClassInfo> classInfoList = null;
		List<DepartmentInfo> departmentInfoList = null;
		//获取到当前用户的学校
		if (shiroUser.schoolId!=null) {
			schoolId = shiroUser.schoolId;
		}else {
			schoolInfoList = schoolInfoService.selectSchoolList();
		}
		if (schoolId!=null) {
			departmentInfoList = departmentInfoService.findListDepartmentInfo(schoolId);
		}
		
		Map<String, String> paramsMap = new HashMap<String, String>();
		//根据所需条件获取获取考勤信息
		paramsMap.put("schoolId", schoolId);
		paramsMap.put("departmentId", departmentId);
		paramsMap.put("classId", classId);
		//查询某个系别所有的班级
		if (departmentId!=null && !departmentId.equals("")) {
			classInfoList = classInfoService.findListClassInfo(paramsMap);
		}
		List<CourseInfo> courseList = courseInfoService.findCourseList(paramsMap);
		List<SubjectInfo> subjectInfos = subjectInfoService.selectAllSubject();
		map.addAttribute("courseList",courseList);
		map.addAttribute("schoolInfoList", schoolInfoList);
		map.addAttribute("departmentInfoList", departmentInfoList);
		map.addAttribute("classInfoList", classInfoList);
		map.addAttribute("classId", classId);
		map.addAttribute("schoolId", schoolId);
		map.addAttribute("departmentId", departmentId);
		map.addAttribute("subjectInfos", subjectInfos);
		return "reception/courseInfoManage";
	}
	
	/**
	 * 添加课程
	 */
	@RequiresPermissions(value="新增课程")
	@RequestMapping("addCourseInfo")
	public String addCourseInfo(CourseInfo courseInfo){
		courseInfoService.addCourseInfo(courseInfo);
		return "forward:selectAllCourse";
	}
	
	
	/**
	 * 删除课程
	 */
	@RequiresPermissions(value="删除课程")
	@RequestMapping("deleteCourseInfo")
	public String deleteCourseInfo(String courseId,HttpServletRequest req,HttpServletResponse repon){
		courseInfoService.deleteCourseInfo(courseId);
		return "forward:selectAllCourse";
	}
	
	/**
	 * 根据id查询课程
	 */
	@RequiresPermissions(value="查询课程")
	@RequestMapping("findCourseInfoById")
	public String findCourseInfoById(ModelMap map,String courseId){
		CourseInfo findCourseInfoById = courseInfoService.findCourseInfoById(courseId);
		map.addAttribute("findCourseInfoById",findCourseInfoById);
		return "reception/courseInfoDetails";
	}
	
	/**
	 * 修改课程
	 */
	@RequiresPermissions(value="修改课程")
	@RequestMapping("updateCourseInfo")
	public String updateCourseInfo(CourseInfo courseInfo){
		courseInfoService.updateCourseInfo(courseInfo);
		return "forward:selectAllCourse";
	}
	/**
	 * 获取某天某个班课程的考勤情况
	 */
	@RequestMapping("findCourseByClassIdAndTime")
	public void fingCourseByClassIdAndTime(String classId,String time,HttpServletResponse response){
		List<CourseInfo> courseInfos = courseInfoService.findCourseByClassIdAndTime(classId, time);
		Gson gson = new Gson();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter writer = response.getWriter();
			String json = gson.toJson(courseInfos);
			writer.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}

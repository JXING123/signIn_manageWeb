package com.cn.tonyou.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cn.tonyou.pojo.ClassInfo;
import com.cn.tonyou.pojo.DepartmentInfo;
import com.cn.tonyou.pojo.LeaveInfo;
import com.cn.tonyou.pojo.SchoolInfo;
import com.cn.tonyou.service.IClassInfoService;
import com.cn.tonyou.service.IDepartmentInfoService;
import com.cn.tonyou.service.ILeaveInfoService;
import com.cn.tonyou.service.ISchoolInfoService;
import com.cn.tonyou.shiro.MyShiro.ShiroUser;
import com.google.gson.Gson;

/**
 * 请假管理
 * @author HY
 *
 */
@RequiresPermissions(value="请假管理")
@Controller
public class LeaveInfoController {
	@Resource 
	ILeaveInfoService leaveInfoService;
	@Resource
	IDepartmentInfoService departmentInfoService;
	@Resource
	ISchoolInfoService schoolInfoService;
	@Resource
	IClassInfoService classInfoService;//班级
	/**
	 * 查询所有请假信息
	 */
	@RequiresPermissions(value="查询请假")
	@RequestMapping("findLeaveList")
	public String findLeaveList(ModelMap map,String schoolId,String departmentId,String classId,String selDate){
		//查询某个学校的
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
		paramsMap.put("leaveTime", selDate);
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
		List<LeaveInfo> selectLeaveInfoList = leaveInfoService.findLeaveList(paramsMap);
		
		//返回到页面
		map.addAttribute("schoolInfoList", schoolInfoList);
		map.addAttribute("classInfoList", classInfoList);
		map.addAttribute("departmentInfoList", departmentInfoList);
		map.addAttribute("schoolId", schoolId);
		map.addAttribute("classId", classId);
		map.addAttribute("departmentId", departmentId);
		map.addAttribute("departmentBySchoolId", departmentInfoList);
		map.addAttribute("selectLeaveInfoList", selectLeaveInfoList);
		return "reception/leaveInfoManage";
	}
	
	/**
	 *web添加请假信息
	 */
	@RequiresPermissions(value="修改请假")
	@RequestMapping("addLeaveInfo")
	public String addLeaveInfo(LeaveInfo leaveInfo){
		leaveInfo.setLeaveState("审核中");
		leaveInfoService.addLeaveInfo(leaveInfo);
		return "forward:findLeaveList";
	}
 
	/**
	 * 删除请假信息
	 */
	@RequiresPermissions(value="删除请假")
	@RequestMapping("deleteLeaveInfo")
	public String deleteLeaveInfo(String leaveId){
		leaveInfoService.deleteLeaveInfo(leaveId);
		return "forward:findLeaveList";
	}
	
	/**
	 * 根据id查询请假信息
	 */
	@RequiresPermissions(value="查询请假")
	@RequestMapping("selectLeaveInfoById")
	public String selectLeaveInfoById(String leaveId,ModelMap map){
		LeaveInfo selectLeaveInfoById = leaveInfoService.selectLeaveInfoById(leaveId);
		map.addAttribute("selectLeaveInfoById",selectLeaveInfoById);
		return "reception/leaveInfoDetails";
	}
	
	/**
	 * 修改请假信息
	 */
	@RequiresPermissions(value="修改请假")
	@RequestMapping("updateLeaveInfo")
	public String updateLeaveInfo(LeaveInfo leaveInfo){
		leaveInfoService.updateLeaveInfo(leaveInfo);
		return "forward:findLeaveList";
	}
	
	/**
	 * 修改审核状态
	 */
	@RequiresPermissions(value="修改请假")
	@RequestMapping("updateState")
	public String updateState(LeaveInfo leaveInfo){
		leaveInfo.setLeaveState("已审核");
		leaveInfoService.updateState(leaveInfo);
		return "forward:findLeaveList";
	}
	
	
	/**
	 * 查询某个学生某段时间的请假信息
	 */
	@RequestMapping(value="findStuPeriodOfTimeLeave")
	public void findStuPeriodOfTimeLeave(LeaveInfo leaveinfo,HttpServletResponse repon) {
		Map<String, String> parmasMap = new HashMap<String, String>();
		parmasMap.put("stuId", leaveinfo.getStu().getStuId());
		parmasMap.put("startTime", leaveinfo.getStartLeaveTime());
		parmasMap.put("endTime", leaveinfo.getEndLeaveTime());
		parmasMap.put("leaveType", leaveinfo.getLeaveType());
		List<LeaveInfo> findPeriodOfTimeLeave = leaveInfoService.findPeriodOfTimeLeave(parmasMap);
		Gson geon = new Gson();
		String json = geon.toJson(findPeriodOfTimeLeave);
		try {
			repon.setContentType("text/html; charset=utf-8");
			System.out.println(json);
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package com.cn.tonyou.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cn.tonyou.pojo.SchoolInfo;
import com.cn.tonyou.service.IDepartmentInfoService;
import com.cn.tonyou.service.ISchoolInfoService;
import com.cn.tonyou.shiro.MyShiro.ShiroUser;
import com.google.gson.Gson;

/**
 * 学校管理
 * @author HY
 *
 */

@Controller
public class SchoolInfoController {
	
	@Resource
	ISchoolInfoService schoolInfoService;
	@Resource
	IDepartmentInfoService	departmentInfoService;
	
	/**
	 * 查询所有学校
	 */
	@RequiresRoles(value="系统管理员")
	@RequestMapping("selectSchoolInfo")
	public String selectSchoolInfo(ModelMap map){
		List<SchoolInfo> selectSchoolList1 = new ArrayList<SchoolInfo>();
		List<SchoolInfo> selectSchoolList = schoolInfoService.selectSchoolList();
		//遍历截取字符串
		for (SchoolInfo sch : selectSchoolList){
			String schStarDate = sch.getSchoolStartTime();
			if (schStarDate!=null) {
				schStarDate = schStarDate.substring(0, 10);
			}
			sch.setSchoolStartTime(schStarDate);
			selectSchoolList1.add(sch);
		}
		map.addAttribute("selectSchoolList",selectSchoolList1);
		return "reception/schoolInfoManage";
	}
	
	/**
	 * 查询所有学校（返回json）
	 */
	@RequiresRoles(value="系统管理员")
	@RequestMapping("selectAllSchool")
	public void selectAllSchool(ModelMap map,HttpServletRequest req,HttpServletResponse repon){
		//查询所有学生信息
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String schoolId = user.schoolId;
		List<SchoolInfo> selectSchoolList = schoolInfoService.selectSchoolList2(schoolId);
		Gson geon = new Gson();
		String json = geon.toJson(selectSchoolList);
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除学校信息
	 */
	@RequiresRoles(value="系统管理员")
	@RequestMapping("deleteSchool")
	public String deleteSchool(String schoolId){
		schoolInfoService.deleteSchool(schoolId);
		return "forward:selectSchoolInfo";
	}
	
	/**
	 * 添加学校
	 */
	@RequiresRoles(value="系统管理员")
	@RequestMapping("addSchool")
	public String addSchool(SchoolInfo schoolInfo){
		schoolInfoService.addSchool(schoolInfo);
		return "forward:selectSchoolInfo";
	}
	
	/**
	 * 添加学校 不能添加名称相同的学校
	 */
	@RequiresRoles(value="系统管理员")
	@RequestMapping("checkAddSchoolinfo")
	public void checkAddSchoolinfo(String schoolName,HttpServletRequest req,HttpServletResponse repon){
		//根据系别id查询这个系是否有这个班级名
		List<SchoolInfo> checkAddSchoolinfo = schoolInfoService.checkAddSchoolinfo(schoolName);
		int a = 0;
		//判断是否查询出班级 如果有班级就为1，否则为0
		if(!checkAddSchoolinfo.isEmpty()){
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
	 * 根据id查询某个学校信息
	 */
	@RequiresRoles(value="系统管理员")
	@RequestMapping("findByIdSchool")
	public String findByIdSchool(ModelMap map,String schoolId){
		//根据id查询学校
		SchoolInfo findByIdSchool = schoolInfoService.findByIdSchool(schoolId);
		/**
		 * 根据学校id查询出学校所属的系别
		List<DepartmentInfo> selectDepartmentBySchoolId = departmentInfoService.selectDepartmentBySchoolId(schoolId);
		map.addAttribute("selectDepartmentBySchoolId", selectDepartmentBySchoolId);*/
		//遍历截取字符串
		String schStarDate = findByIdSchool.getSchoolStartTime().substring(0, 10);
		map.addAttribute("schStarDate",schStarDate);
		map.addAttribute("findByIdSchool", findByIdSchool);
		return "reception/schoolInfoDetails";
	}
	
	/**
	 * 修改学校信息
	 */
	@RequiresRoles(value="系统管理员")
	@RequestMapping("updateSchoolInfo")
	public String updateSchoolInfo(SchoolInfo schoolInfo){
		schoolInfoService.updateSchoolInfo(schoolInfo);
		return "forward:selectSchoolInfo";
	}
}
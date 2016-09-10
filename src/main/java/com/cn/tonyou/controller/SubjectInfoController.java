package com.cn.tonyou.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cn.tonyou.pojo.SubjectInfo;
import com.cn.tonyou.service.ISchoolInfoService;
import com.cn.tonyou.service.ISubjectInfoService;
import com.cn.tonyou.service.ISysUserInfoService;
import com.google.gson.Gson;

/**
 * 科目管理
 * @author Administrator
 */
@Controller
@RequiresPermissions(value="科目管理")
public class SubjectInfoController {
	@Resource
	ISubjectInfoService subjectInfoService;
	@Resource
	ISysUserInfoService sysUserInfoService;
	@Resource
	ISchoolInfoService schoolInfoService;
	
	/**
	 * 查询所有科目
	 */
	@RequiresPermissions(value="查询科目")
	@RequestMapping("selectAllSubjectInfo")
	public String selectAllSubjectInfo(ModelMap map,String schoolId){
		List<SubjectInfo> selectAllSubject = subjectInfoService.selectAllSubject();
		map.addAttribute("selectAllSubject",selectAllSubject);
		return "reception/subjectInfoManage";
	}
	/**
	 * 查询所有科目
	 */
	@RequiresPermissions(value="查询科目")
	@RequestMapping("selectAllSubject")
	public void selectAllSubject(HttpServletRequest req,HttpServletResponse repon){
		List<SubjectInfo> selectAllSubject = subjectInfoService.selectAllSubject();		
		Gson geon = new Gson();
		String json = geon.toJson(selectAllSubject);		
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加科目	
	 */
	@RequiresPermissions(value="新增科目")
	@RequestMapping("addSubjectInfo")
	public String addSubjectInfo(SubjectInfo subjectInfo){
		subjectInfoService.addSubjectInfo(subjectInfo);
		return "forward:selectAllSubjectInfo";
	}
	
	/**
	 * 删除科目
	 */
	@RequiresPermissions(value="删除科目")
	@RequestMapping("deleteSubjectInfo")
	public String deleteSubjectInfo(String subjectId){
		subjectInfoService.deleteSubjectInfo(subjectId);
		return "forward:selectAllSubjectInfo";
	}
	
	/**
	 * 根据id查询科目
	 */
	@RequiresPermissions(value="查询科目")
	@RequestMapping("findSubjectInfoById")
	public String findSubjectInfoById(String subjectId,ModelMap map){
		SubjectInfo findSubjectInfoById = subjectInfoService.findSubjectInfoById(subjectId);
		map.addAttribute("findSubjectInfoById",findSubjectInfoById);
		return "reception/subjectInfoDetails";
	}
	
	/**
	 * 根据科目名称查询科目
	 * @param subjectName
	 * @return
	 */

	@RequestMapping(value = "checkAddSubject")
	public void checkAddSubject(String subjectName,HttpServletResponse repon){
		SubjectInfo findSubjectInfoByName = subjectInfoService.findSubjectInfoByName(subjectName);
		repon.setContentType("text/html; charset=utf-8");
		try {
			PrintWriter writer = repon.getWriter();
			if (findSubjectInfoByName!=null) {
				writer.println(1);
			}else{
				writer.println(0);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改科目
	 */
	@RequiresPermissions(value="修改科目")
	@RequestMapping("updateSubjectInfo")
	public String updateSubjectInfo(SubjectInfo subjectInfo){
		subjectInfoService.updateSubjectInfo(subjectInfo);
		return "forward:selectAllSubjectInfo";
	}
}

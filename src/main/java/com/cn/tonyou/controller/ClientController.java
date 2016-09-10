package com.cn.tonyou.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cn.tonyou.pojo.ClassInfo;
import com.cn.tonyou.pojo.ClockingInfo;
import com.cn.tonyou.pojo.CourseInfo;
import com.cn.tonyou.pojo.DepartmentInfo;
import com.cn.tonyou.pojo.DormInfo;
import com.cn.tonyou.pojo.DormSigninInfo;
import com.cn.tonyou.pojo.LeaveInfo;
import com.cn.tonyou.pojo.SchoolInfo;
import com.cn.tonyou.pojo.StuInfo;
import com.cn.tonyou.service.IClassInfoService;
import com.cn.tonyou.service.IClockingService;
import com.cn.tonyou.service.ICourseInfoService;
import com.cn.tonyou.service.IDepartmentInfoService;
import com.cn.tonyou.service.IDormInfoService;
import com.cn.tonyou.service.IDormSigninInfoService;
import com.cn.tonyou.service.ILeaveInfoService;
import com.cn.tonyou.service.ISchoolInfoService;
import com.cn.tonyou.service.IStuInfoService;
import com.cn.tonyou.utils.DateUtil;
import com.cn.tonyou.utils.MD5Util;
import com.cn.tonyou.utils.UploadImage;
import com.google.gson.Gson;

@Controller
@RequestMapping(value="client")
public class ClientController {
	@Autowired
	IClassInfoService classInfoService;
	@Resource
	IClockingService clockingService;	//上课考勤
	@Resource
	ICourseInfoService	courseInfoService;
	@Resource
	IDormInfoService dormInfoService;
	@Resource
	IDormSigninInfoService dormSigninInfoService;	//宿舍考勤
	@Resource
	ILeaveInfoService leaveInfoService;//请假
	@Resource
	IStuInfoService stuInfoService;
	@Resource
	ISchoolInfoService schoolInfoService;
	@Resource
	IDepartmentInfoService departmentInfoService;//系别
	/**
	 * 客户端联表查询，班级、院系、学校
	 */
	@RequestMapping("selectAll")
	public void selectAll(ModelMap map,HttpServletRequest req,HttpServletResponse repon){
		List<ClassInfo> selectAll = classInfoService.selectAll();
		Gson geon = new Gson();
		String json = geon.toJson(selectAll);
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 客户端联表查询，班级、院系、学校
	 */
	@RequestMapping("findAllSchool")
	public void findAllSchool(HttpServletResponse repon){
		List<SchoolInfo> findAllSchool = schoolInfoService.selectAllSchools();
		Gson geon = new Gson();
		String json = geon.toJson(findAllSchool);
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 手机端获取学生上课考勤信息
	 */
	@RequestMapping("getStuByIdClocking")
	public void getStuByIdClocking(String stuId,String dateTime,HttpServletRequest req,HttpServletResponse repon){
		List<ClockingInfo> clockingInfos = new ArrayList<ClockingInfo>();
		//根据班级id查询考勤
		List<ClockingInfo> clockingBystuId = clockingService.getStuByIdClocking(stuId,dateTime);     
		for(ClockingInfo clock : clockingBystuId){
			clock.setInsertDate(DateUtil.format(clock.getClockingDate(), DateUtil.DATE_TIME_PATTERN));
			clockingInfos.add(clock);
		}
		Gson geon = new Gson();
		String json = geon.toJson(clockingInfos);
		try {    
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 手机端根据学生以及时间查询上课考勤信息
	 * @param stuId
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@RequestMapping("getClassByIdAndTime")
	public void getClassByIdAndTime(String stuId,String startTime,String endTime,HttpServletResponse repon,HttpServletRequest req) throws Exception{
		StuInfo stuInfo = stuInfoService.findByIdStu(stuId);
		
		List<ClockingInfo> clockingInfos = new ArrayList<ClockingInfo>();
		//根据班级id查询考勤
		List<ClockingInfo> clockingBystuId = clockingService.getClassByIdAndTime(stuInfo.getClasses().getClassId(), startTime, endTime);     
		
		for(ClockingInfo clock : clockingBystuId){
			clock.setInsertDate(DateUtil.format(clock.getClockingDate(), DateUtil.DATE_TIME_PATTERN));
			clockingInfos.add(clock);
		}
		Gson geon = new Gson();
		String json = geon.toJson(clockingInfos);
		System.out.println(json);
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 手机端根据学生星期查询当天所有的课程信息
	 */
	@RequestMapping("getWeekCourseInfoList")
	public void getWeekCourseInfoList(String stuId,String week,HttpServletResponse repon){
		List<CourseInfo>  weekCourseInfoList = courseInfoService.getWeekCourseInfoList(stuId, week);
		Gson geon = new Gson();
		String json = geon.toJson(weekCourseInfoList);
		
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** 
	 * 	客户端获取学生课程表信息	 
	 * @param sutId 
	 * @param repon
	 */
	
	@RequestMapping("getCourseListForStu")
	public void getCourseListForStu(String stuId,HttpServletResponse repon){		
		@SuppressWarnings("rawtypes")	
 		Map<String, List> map = new HashMap<String, List>();
		List<CourseInfo> courseList = courseInfoService.getCourseListForStu(stuId);
		List<String> timeList = new ArrayList<String>();
		timeList.add(DateUtil.format(new Date(), DateUtil.DATE_TIME_PATTERN));
		map.put("result", courseList);	
		map.put("curTime",timeList);
		Gson gson = new Gson();	
		String json = gson.toJson(map);	
		try {		
			repon.setContentType("text/html; charset=utf-8");		
			System.out.println(json);
			PrintWriter writer = repon.getWriter();		
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {		
			e.printStackTrace();	
		}
	}
	/**
	 * 添加请假
	 * @param leaveinfo
	 * @return
	 */
	@RequestMapping("addLeaveForStu")
	public void addLeaveForStu(LeaveInfo leaveinfo,String startLeaveTime,String endLeaveTime,HttpServletResponse repon){
		leaveinfo.setStartLeaveTime(startLeaveTime);
		leaveinfo.setEndLeaveTime(endLeaveTime);
		LeaveInfo selectLeaveInfoById = leaveInfoService.selectLeaveInfoById(leaveinfo.getLeaveId());
		int i = leaveInfoService.addLeaveForStu(leaveinfo);
		Gson geon = new Gson();
		String json = geon.toJson(i);
		try {
			repon.setContentType("text/html; charset=utf-8");
			if (selectLeaveInfoById!=null) {
				json = "0";
			}
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询该学生的宿舍信息(客户端)
	 * @param stuId
	 * @param repon
	 */
	@RequestMapping("getDormListForStuId")
	public void getDormListForStuId(String stuId,HttpServletResponse repon){
		Map<String, Object> map = new HashMap<String, Object>();
		DormInfo dormList = dormInfoService.getDormListForStuId(stuId);
		List<String> timeList = new ArrayList<String>();
		timeList.add(DateUtil.format(new Date(), DateUtil.DATE_TIME_PATTERN));
		map.put("result", dormList);
		map.put("curTime",timeList);
		Gson gson = new Gson();
		String json = gson.toJson(map);
		try {
			repon.setContentType("text/html; charset=utf-8");
			System.out.println(json);
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 手机端获取学生宿舍
	 * 考勤信息
	 */
	@RequestMapping("getStuByIdDormSigninList")
	public void getStuByIdDormSigninList(DormSigninInfo dormSigninInfo,HttpServletRequest req,HttpServletResponse repon){
		
		List<DormSigninInfo> clockingBystuId = dormSigninInfoService.getStuByIdDormSigninList(dormSigninInfo);   
		Gson geon = new Gson();
		String json = geon.toJson(clockingBystuId);
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();   
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 学生宿舍考勤
	 * @param dorm
	 * @return
	 */
	@RequestMapping("addDormsiginiAttend")
	public void addDormsiginiAttend(DormSigninInfo dormSignin,HttpServletResponse repon){
		List<DormSigninInfo> dormSig = dormSigninInfoService.getStuByIdDormSigninList(dormSignin);
		int i = 0;
		if(!dormSig.isEmpty()){
			i = dormSigninInfoService.updateDormSignin(dormSignin);
		}else{
			i = dormSigninInfoService.addDormsiginiAttend(dormSignin);
		}
		Gson gson = new Gson();
		String json = gson.toJson(i);
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 手机端获取某天的请假信息
	 * @param leaveinfo
	 * @return
	 */
	@RequestMapping("getStuDayLeave")
	public void getStuDayLeave(LeaveInfo leaveInfo,HttpServletResponse repon){
		LeaveInfo leInfo = leaveInfoService.getStuDayLeave(leaveInfo);
		if (leInfo!=null) {
			leInfo.setStartLeaveTime(DateUtil.format(leInfo.getStartLeaveDate(), DateUtil.DATE_PATTERN));
			leInfo.setEndLeaveTime(DateUtil.format(leInfo.getEndLeaveDate(), DateUtil.DATE_PATTERN));
		}
 		Gson geon = new Gson();
		String json = geon.toJson(leInfo);
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 手机端获取某个学生的所有的请假信息
	 * @param leaveinfo
	 * @return
	 */
	@RequestMapping("getStuLeaveList")
	public void getStuLeaveList(String stuId,HttpServletResponse repon){
		List<LeaveInfo> LeaveInfoList = leaveInfoService.getStuLeaveList(stuId);
		for (LeaveInfo leaveInfo :LeaveInfoList) {
			leaveInfo.setStartLeaveTime(DateUtil.format(leaveInfo.getStartLeaveDate(), DateUtil.DATE_PATTERN));
			leaveInfo.setEndLeaveTime(DateUtil.format(leaveInfo.getEndLeaveDate(), DateUtil.DATE_PATTERN));
		}
 		Gson geon = new Gson();
		String json = geon.toJson(LeaveInfoList);
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 手机端修改学生信息
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("updateStuInfoClient")
	public void updateStuInfoClient(StuInfo stuInfo,HttpServletResponse repon,HttpServletRequest req) throws Exception {
		repon.setContentType("text/html; charset=utf-8");
		String imagePath = "";
		PrintWriter writer = repon.getWriter();
	    StuInfo stu = stuInfoService.findByIdStu(stuInfo.getStuId());
	    int updateStuInfo = 0;
	    if (stu !=null) {
	    	String password = stuInfo.getStuPassword();
	    	if (stuInfo.getStuImage()!=null&& !stuInfo.getStuImage().equals("")) {
	    		boolean isDelete = new UploadImage().delegeImage(stu.getStuImage());
	    		if (isDelete) {
		    		System.out.println("删除原来头像成功...");
		    	}
	    		imagePath = new UploadImage().oneImage(stuInfo.getStuId(),stuInfo.getStuImage(), req);
	    		stuInfo.setStuImage(imagePath);
	    		updateStuInfo = stuInfoService.updateStuInfo(stuInfo);
			}else if (password!=null&&!password.equals("")) {
				if (stuInfo.getStuTel()==null || !stuInfo.getStuTel().equals(stu.getStuTel())) {
					writer.print("手机号码不匹配");
					return;
				}else {
					stuInfo.setStuPassword(MD5Util.getMD5(password));
					updateStuInfo = stuInfoService.updateStuInfo(stuInfo);
				}
			}else {
				updateStuInfo = stuInfoService.updateStuInfo(stuInfo);
			}
	    }
	      
		Gson geon = new Gson();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("result", updateStuInfo);
		String json = geon.toJson(map);
		writer.print(json);
	}
	
	/**
	 * 手机端根据id获取某个学生信息
	 *  
	 */
	@RequestMapping("findByIdStuClient")
	public void findByIdStuClient(String stuId,HttpServletRequest req,HttpServletResponse repon){
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			Gson geon = new Gson();
			Map<String, Object> map = new HashMap<String, Object>();
			//根据id查询学生
			StuInfo stu = stuInfoService.findByIdStu(stuId);
			if (stu.getStuImage() == null || stu.getStuImage().equals("")) {
				stu.setStuImage("defaultIcon.png");
			}
			//String imgBase64Str = new UploadImage().getImageForBase64(stu.getStuImage(), req);
			map.put("result", stu);			
			//map.put("image", imgBase64Str);
			String json = geon.toJson(map);
			writer.print(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 根据班级id查询所有学生（客户端）
	 */
	@RequestMapping("getClassAllStuByStuId")
	public void getClassAlLStuByStuId(String stuInfoId,HttpServletRequest req,HttpServletResponse repon){
		StuInfo stuInfo = stuInfoService.findByIdStu(stuInfoId);
		@SuppressWarnings("rawtypes")	
 		Map<String, List> map = new HashMap<String, List>();
		List<String> timeList = new ArrayList<String>();
		List<StuInfo> getStuInfoByClassId = stuInfoService.selectStuInfoByClassId(stuInfo.getClasses().getClassId());
		for (StuInfo stu : getStuInfoByClassId) {
			if (stu.getStuImage() == null) {
				stu.setStuImage("defaultIcon.png");
			}
		}
		timeList.add(DateUtil.format(new Date(), DateUtil.DATE_TIME_PATTERN));
		map.put("result", getStuInfoByClassId);	
		map.put("curTime",timeList);
		Gson geon = new Gson();
		String json = geon.toJson(map);		
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * 客户端注册
	 * @throws Exception 
	 */
	@RequestMapping("userReg")
	public void userReg(@RequestParam("uploadFile") MultipartFile[] uploadFile,InputStream inputImg,String schoolName,StuInfo stuInfo,HttpServletRequest req,HttpServletResponse repon) throws Exception{
		// 定义变量存储图片地址
        String imagePath = "";

        String image = req.getParameter("image");
        if (image != null) {
        	imagePath = new UploadImage().oneImage(stuInfo.getStuId(),image, req);
		}
		//imagePath = new UploadImage().oneImage(inputImg, req);    
		Map<String, String> map = new HashMap<String, String>();
		// 通过学校系班级名字查询班级id
		String schStr[] = schoolName.split(",");
		SchoolInfo sch = schoolInfoService.selectSchoolByName(schStr[0]);
		DepartmentInfo departmentInfo = departmentInfoService.findDepartmentByName(schStr[1], sch.getSchoolId());
		System.out.println(schStr[2]);
		ClassInfo classInfo = classInfoService.selectClassByIdAndName(schStr[2],departmentInfo.getDepartmentId());
		stuInfo.setClasses(classInfo);  
		stuInfo.setStuImage(imagePath);
		// 注册密码加密
		stuInfo.setStuPassword(MD5Util.getMD5(stuInfo.getStuPassword()));
		//查询该用户是否存在
		StuInfo userReg1 = stuInfoService.findByIdStu(stuInfo.getStuId());
		StuInfo userReg2 = stuInfoService.selectStuInfoByTel(stuInfo.getStuTel());
		if(userReg1 != null){
			map.put("result", "-1");
		}else if(userReg2 != null) {
			map.put("result", "-2");
		}else{
			//注册成功返回1
			int isUser = stuInfoService.userReg(stuInfo);
			if(isUser == 1){
				map.put("result", "1");
			}else{
				map.put("result", "0");
			}
		}
		Gson gson = new Gson();
		String json = gson.toJson(map);
		try {
			repon.setContentType("text/html; charset=utf-8");
			System.out.println(json);
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * android客户端注册
	 * @throws Exception 
	 */
	@RequestMapping("androidUserReg")
	public void androidUserReg(String school,StuInfo stuInfo,HttpServletRequest req,HttpServletResponse repon) throws Exception{
		// 定义变量存储图片地址
        String imagePath = "";
        String image = req.getParameter("image");
        if (image != null) {
        	imagePath = new UploadImage().oneImage(stuInfo.getStuId(),image, req);
		}
		//imagePath = new UploadImage().oneImage(inputImg, req);    
		Map<String, String> map = new HashMap<String, String>();
		String schStr[] = school.split(",");
		SchoolInfo sch = schoolInfoService.selectSchoolByName(schStr[0]);
		DepartmentInfo departmentInfo = departmentInfoService.findDepartmentByName(schStr[1], sch.getSchoolId());
		System.out.println(schStr[2]);
		ClassInfo classInfo = classInfoService.selectClassByIdAndName(schStr[2],departmentInfo.getDepartmentId());
		stuInfo.setClasses(classInfo);  
		stuInfo.setStuImage(imagePath);
		// 注册密码加密
		stuInfo.setStuPassword(MD5Util.getMD5(stuInfo.getStuPassword()));
		//查询该用户是否存在
		StuInfo userReg1 = stuInfoService.findByIdStu(stuInfo.getStuId());
		StuInfo userReg2 = stuInfoService.selectStuInfoByTel(stuInfo.getStuTel());
		if(userReg1 != null){
			map.put("result", "-1");
		}else if(userReg2 != null) {
			map.put("result", "-2");
		}else{ 
			//注册成功返回1
			int isUser = stuInfoService.userReg(stuInfo);
			if(isUser == 1){ 
				map.put("result", "1");
			}else{
				map.put("result", "0");
			}
		}
		Gson gson = new Gson();
		String json = gson.toJson(map);
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 客户端学生登录
	 * @param stuId
	 * @param stuPassword
	 * @param repon
	 */
	@RequestMapping("stuLogin")
	public void stuLogin(String stuId,String stuPassword,HttpServletResponse repon){
		Map<String, String> map = new HashMap<String, String>();
		StuInfo stuInfo = stuInfoService.findByIdStu(stuId);
		StuInfo userLogin = null;
		if (stuInfo != null) {
			if (stuInfo.getStuState() == null ||  stuInfo.getStuState().equals("") ||stuInfo.getStuState().equals("未审核") ) {
				map.put("result", "-1");
			}else{
				userLogin = stuInfoService.stuLogin(stuId, MD5Util.getMD5(stuPassword));
				if (userLogin != null) {
					map.put("result", "1");
				}else{
					map.put("result", "-2");
				}
			}
		}else{
			map.put("result", "0");
		}
		
		
		Gson geon = new Gson();
		String json = geon.toJson(map);
		try {
			repon.setContentType("text/html; charset=utf-8");
			System.out.println(json);
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * (客户端)添加考勤信息
	 * @param clockingInfo
	 */
	@RequestMapping("stuAttentClick")
	public void stuAttentClick(ClockingInfo clockingInfo,String clockDate,HttpServletResponse repon){
		ClockingInfo clocking =  clockingService.findByIdClocking(clockingInfo);
		int i = 0;
		clockingInfo.setInsertDate(clockDate);
		if (clocking == null) {
			i = clockingService.addClocking(clockingInfo);
		}else{
			i = clockingService.updateClocking(clockingInfo);
		}
		
		Gson geon = new Gson();
		String json = geon.toJson(i);
		try {
			repon.setContentType("text/html; charset=utf-8");
			System.out.println(json);
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询所有的学校院系班级（客户端）
	 * @param map
	 * @return
	 */
	@RequestMapping("selectAllSchools")
	public void selectAllSchools(ModelMap map,HttpServletResponse repon){
		List<SchoolInfo> getClassinfo = schoolInfoService.selectAllSchools();
		@SuppressWarnings("rawtypes")
		Map<String, List> schMap = new HashMap<String, List>();
		schMap.put("school", getClassinfo);
		Gson geon = new Gson();
		String json = geon.toJson(schMap);
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 	获取当前时间
	 * @param clockingInfo
	 */
	@RequestMapping("getIpCurTime")
	public void getIpCurTime(HttpServletResponse repon){
		Gson geon = new Gson();
		String json = geon.toJson(DateUtil.format(new Date(), DateUtil.DATE_TIME_PATTERN));
		try {
			repon.setContentType("textml; charset=utf-8");
			System.out.println(json);
			PrintWriter writer = repon.getWriter();
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

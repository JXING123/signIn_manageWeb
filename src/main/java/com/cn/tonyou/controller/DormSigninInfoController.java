package com.cn.tonyou.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.cn.tonyou.pojo.DormInfo;
import com.cn.tonyou.pojo.DormSigninInfo;
import com.cn.tonyou.pojo.LeaveInfo;
import com.cn.tonyou.pojo.SchoolInfo;
import com.cn.tonyou.pojo.StuInfo;
import com.cn.tonyou.service.IClassInfoService;
import com.cn.tonyou.service.IDepartmentInfoService;
import com.cn.tonyou.service.IDormInfoService;
import com.cn.tonyou.service.IDormSigninInfoService;
import com.cn.tonyou.service.ILeaveInfoService;
import com.cn.tonyou.service.ISchoolInfoService;
import com.cn.tonyou.service.IStuInfoService;
import com.cn.tonyou.shiro.MyShiro.ShiroUser;
import com.cn.tonyou.utils.DateUtil;
import com.google.gson.Gson;

@RequiresPermissions(value="宿舍考勤管理")
@Controller
public class DormSigninInfoController {
	@Resource
	IDormSigninInfoService dormSigninInfoService;	//考勤
	@Resource
	IDepartmentInfoService departmentInfoService;	//系别
	@Resource
	IDormInfoService dormInfoService;
	@Resource
	IClassInfoService classInfoService;//班级
	@Resource
	ISchoolInfoService schoolInfoService;
	@Resource
	IStuInfoService stuInfoService;
	@Resource
	ILeaveInfoService leaveInfoService;
	
	/**
	 * 根据各种条件获取宿舍考勤信息
	 * @param dormSigninInfo
	 * @param schoolId
	 * @return
	 */
	@RequiresPermissions(value="查询宿舍考勤")
	@RequestMapping(value="findDormSigninList")
	public String findDormSigninList(ModelMap map,String schoolId,String departmentId,String classId,String selDate){
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
		paramsMap.put("selDate", selDate);
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
			paramsMap.put("siSysUserId", "");
			//获取到自己管理的班级信息
			classInfoList = classInfoService.findSysClassId(paramsMap);
		}
		
		
		
		List<DormSigninInfo> dormSigninList = dormSigninInfoService.findDormSigninList(paramsMap);
		//返回到页面
		map.addAttribute("schoolInfoList", schoolInfoList);
		map.addAttribute("dormSigninList", dormSigninList);
		map.addAttribute("classInfoList", classInfoList);
		map.addAttribute("departmentInfoList", departmentInfoList);
		map.addAttribute("schoolId", schoolId);
		map.addAttribute("classId", classId);
		map.addAttribute("departmentId", departmentId);
		map.addAttribute("selDate", selDate);
		return "reception/dormSigninManage";
	}
	@RequiresPermissions(value="查询宿舍考勤")
	@RequestMapping(value="findByIdDormSignin")
	public String findByIdDormSignin(ModelMap map,DormSigninInfo dormSigninInfo){
		List<DormSigninInfo> dormSigninList = dormSigninInfoService.getStuByIdDormSigninList(dormSigninInfo);
		map.addAttribute("dormSignin", dormSigninList.get(0));
		return "reception/dormSigninDetails";
	}
	/**
	 * 更新宿舍考勤
	 * @param dormSignin
	 * @return
	 */
	@RequiresPermissions(value="修改宿舍考勤")
	@RequestMapping(value="updateDormSignin")
	public String updateDormSignin(DormSigninInfo dormSignin){
		dormSigninInfoService.updateDormSignin(dormSignin);
		return "forward:findDormSigninList";
	}
	/**
	 * 删除宿舍考勤信息
	 * @param dormSigninId
	 * @return
	 */
	@RequiresPermissions(value="删除宿舍考勤")
	@RequestMapping(value="deleteByIdDormSignin")
	public String deleteByIdDormSignin(String dormSigninId){
		dormSigninInfoService.deleteDormSignin(dormSigninId);
		return "forward:findDormSigninList";
	}
	
	 /**
     * 某个学校的宿舍考勤签到统计
     * @param departmentId
     */
    @RequestMapping(value="schoolDormSignInStatistics")
    public String schoolDormSignInStatistics(String startTime,String endTime,ModelMap map){
    	/**
    	 * 定义总共的集合
    	 */
    	int zhengchangTotal =0;//所有的正常
    	int wanguiTotal = 0;
		int weiguiTotal = 0;
    	ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String schoolId = shiroUser.schoolId;
		SchoolInfo school = schoolInfoService.findByIdSchool(schoolId);
		List<DepartmentInfo> departmentInfos = departmentInfoService.findListDepartmentInfo(schoolId);
		List<Map<String, Object>> departmentCountList = new ArrayList<Map<String,Object>>();
		if (startTime==null && endTime==null) {
    		Date date = new Date();//当前日期
    		Calendar calendar = Calendar.getInstance();//日历对象
    		calendar.setTime(date);//设置当前日期
    		calendar.add(Calendar.MONTH, -1);//月份减一
    		startTime = DateUtil.format(calendar.getTime(), DateUtil.DATE_PATTERN);
    		endTime = DateUtil.format(date, DateUtil.DATE_PATTERN);
		}
		int dateSize = DateUtil.findDates(startTime,endTime).size();//需要签到的次数
		for (DepartmentInfo departmentInfo : departmentInfos) {
			Map<String, Object> departmentCountMap = new HashMap<String, Object>();
			Map<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("startTime", startTime);
			paramsMap.put("endTime", endTime);
			long qingjia = 0;
			int zhengchang = 0;//正常签到
			int wangui = 0;//定义晚归的次数
			int departmentNNT =0;//定义系别总人数
			long departmentAllnumber=0;//总共需要的签到次数
			List<ClassInfo> classInfos = classInfoService.getAllClassByDepartmentId(departmentInfo.getDepartmentId());
			for (ClassInfo classInfo : classInfos) {
				int size = stuInfoService.selectStuInfoByClassId(classInfo.getClassId()).size();//获取到该系别这个班的人数
				departmentNNT = departmentNNT+ size;
				departmentAllnumber = departmentAllnumber+dateSize*size;
				paramsMap.put("classId", classInfo.getClassId());
				List<DormSigninInfo> dormSigninList = dormSigninInfoService.findDormSigninList(paramsMap);
				for (DormSigninInfo dormSigninInfo : dormSigninList) {
					String dormSigninState = dormSigninInfo.getDormSigninState();
					if (dormSigninState.equals("正常")) {
						zhengchang++;
					}else if (dormSigninState.equals("晚归")) {
						wangui++;
					}
					
				}
				List<LeaveInfo> findLeaveList = leaveInfoService.findLeaveList(paramsMap);
				for (LeaveInfo leaveInfo : findLeaveList) {
					//获取到请假的时间的所有日期
					String startLeaveTime = leaveInfo.getStartLeaveTime();
					String endLeaveTime = leaveInfo.getEndLeaveTime();
					//获取请假的天数
					long qingjiaDate = DateUtil.getDateMinus(startLeaveTime, endLeaveTime, DateUtil.DATE_TIME_PATTERN)/60/24;
					qingjiaDate++;
					qingjia=qingjia+qingjiaDate;//该班级总共请假的天数
				}
			}
			wanguiTotal = wanguiTotal+wangui;
			weiguiTotal = (int) (weiguiTotal+(departmentAllnumber-wangui-zhengchang));
			zhengchangTotal = zhengchangTotal+zhengchang;
			departmentCountMap.put("name", departmentInfo.getDeprtmentName());
			departmentCountMap.put("wanGui", wangui);
			departmentCountMap.put("weiGui", departmentAllnumber-wangui-zhengchang-qingjia);
			departmentCountMap.put("zhengChang", zhengchang);
			departmentCountMap.put("qingJia", qingjia);
			departmentCountMap.put("NNT", departmentNNT);
			departmentCountMap.put("allNumber", departmentAllnumber);
			departmentCountList.add(departmentCountMap);
		}
		Map<String, Object> countTotal = new HashMap<String, Object>();
		countTotal.put("zhengChang", zhengchangTotal);
		countTotal.put("wanGui", wanguiTotal);
		countTotal.put("weiGui", weiguiTotal);
		map.addAttribute("departmentInfos", departmentInfos);	
		map.addAttribute("startTime", startTime);
		map.addAttribute("endTime", endTime);
		map.addAttribute("countList", departmentCountList);
		map.addAttribute("schoolName", school.getSchoolName());
		map.addAttribute("countTotal", countTotal);
		return "reception/dromAttendanceStatistics";
    }
	
    /**
     * 某个系别宿舍考勤签到统计
     * @param departmentId
     */
    @RequestMapping(value="departmentDormSignInStatistics")
    public String departmentDormSignInStatistics(String departmentId,String startTime,String endTime,ModelMap map,String departmentName){
    	/**
    	 * 定义总共的集合
    	 */
    	int zhengchangTotal =0;//所有的正常
    	int wanguiTotal = 0;
		int weiguiTotal = 0;
    	ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String schoolId = shiroUser.schoolId;
		if (departmentName!=null) {
			DepartmentInfo findDepartmentByName = departmentInfoService.findDepartmentByName(departmentName, schoolId);
			departmentId = findDepartmentByName.getDepartmentId();
		}
		
		List<DepartmentInfo> departmentInfos = departmentInfoService.findListDepartmentInfo(schoolId);
		DepartmentInfo findDepartment= departmentInfoService.findDepartmentById(departmentId);
		List<Map<String, Object>> departmentCountList = new ArrayList<Map<String,Object>>();
		int dateSize = DateUtil.findDates(startTime,endTime).size();//需要签到的次数
		List<ClassInfo> classInfos = classInfoService.getAllClassByDepartmentId(departmentId);
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("startTime", startTime);
		paramsMap.put("endTime", endTime);
		for (ClassInfo classInfo : classInfos) {
			long qingjia = 0;
			int zhengchang = 0;//正常签到
			int wangui = 0;//定义晚归的次数
			int departmentNNT =0;//定义系别总人数
			long departmentAllnumber=0;//总共需要的签到次数
			paramsMap.put("classId", classInfo.getClassId());
			Map<String, Object> departmentCountMap = new HashMap<String, Object>();
			departmentNNT = stuInfoService.selectStuInfoByClassId(classInfo.getClassId()).size();//获取这个班的人数
			departmentAllnumber = departmentAllnumber+dateSize*departmentNNT;
			List<DormSigninInfo> dormSigninList = dormSigninInfoService.findDormSigninList(paramsMap);
			for (DormSigninInfo dormSigninInfo : dormSigninList) {
				String dormSigninState = dormSigninInfo.getDormSigninState();
				if (dormSigninState.equals("正常")) {
					zhengchang++;
				}else if (dormSigninState.equals("晚归")) {
					wangui++;
				}
				
			}
			List<LeaveInfo> findLeaveList = leaveInfoService.findLeaveList(paramsMap);
			for (LeaveInfo leaveInfo : findLeaveList) {
				//获取到请假的时间的所有日期
				String startLeaveTime = leaveInfo.getStartLeaveTime();
				String endLeaveTime = leaveInfo.getEndLeaveTime();
				//获取请假的天数
				long qingjiaDate = DateUtil.getDateMinus(startLeaveTime, endLeaveTime, DateUtil.DATE_TIME_PATTERN)/60/24;
				qingjiaDate++;
				qingjia=qingjia+qingjiaDate;//该班级总共请假的天数
			}
			wanguiTotal = wanguiTotal+wangui;
			weiguiTotal = (int) (weiguiTotal+(departmentAllnumber-wangui-zhengchang));
			zhengchangTotal = zhengchangTotal+zhengchang;
			departmentCountMap.put("name", classInfo.getClassName());
			departmentCountMap.put("wanGui", wangui);
			departmentCountMap.put("weiGui", departmentAllnumber-wangui-zhengchang-qingjia);
			departmentCountMap.put("zhengChang", zhengchang);
			departmentCountMap.put("qingJia", qingjia);
			departmentCountMap.put("NNT", departmentNNT);
			departmentCountMap.put("allNumber", departmentAllnumber);
			departmentCountList.add(departmentCountMap);
		}
		Map<String, Object> countTotal = new HashMap<String, Object>();
		countTotal.put("zhengChang", zhengchangTotal);
		countTotal.put("wanGui", wanguiTotal);
		countTotal.put("weiGui", weiguiTotal);
		map.addAttribute("departmentInfos", departmentInfos);	
		map.addAttribute("startTime", startTime);
		map.addAttribute("endTime", endTime);
		map.addAttribute("departmentId", departmentId);
		map.addAttribute("countList", departmentCountList);
		map.addAttribute("deprtmentName", findDepartment.getDeprtmentName());
		map.addAttribute("countTotal", countTotal);
		map.addAttribute("classInfos", classInfos);
		return "reception/dromAttendanceStatistics";
    }
    
    /**
     * 某个班级宿舍考勤签到统计
     * @param departmentId
     */
    @RequestMapping(value="classDormSignInStatistics")
    public String classDormSignInStatistics(String departmentId,String classId,String className,String startTime,String endTime,ModelMap map,String departmentName){
    	/**
    	 * 定义总共的集合
    	 */
    	List<Map<String, Object>> classCountList = new ArrayList<Map<String,Object>>();//返回页面的集合
    	ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String schoolId = shiroUser.schoolId;
    	List<DepartmentInfo> departmentInfos = departmentInfoService.findListDepartmentInfo(schoolId);
    	List<ClassInfo> classInfos = classInfoService.getAllClassByDepartmentId(departmentId);
    	ClassInfo classInfo = null;
    	if (className!=null) {
			classInfo = classInfoService.selectClassByIdAndName(className, departmentId);
			classId = classInfo.getClassId();
		}else{
			classInfo = classInfoService.findByIdClass(classId);
		}
    	int zhengchangTotal =0;//所有的正常
    	int wanguiTotal = 0;
		int weiguiTotal = 0;
		//根据班级获取到宿舍信息
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("startTime", startTime);
		paramsMap.put("endTime", endTime);
		paramsMap.put("classId", classId);
		List<DormInfo> findDormList = dormInfoService.findDormList(paramsMap);//获取到这个班所有的宿舍
		int dateSize = DateUtil.findDates(startTime,endTime).size();//需要签到的次数
		for (DormInfo dormInfo : findDormList) {
			paramsMap.put("dormId", dormInfo.getDormId());
			long qingjia = 0;
			int zhengchang = 0;//正常签到
			int wangui = 0;//定义晚归的次数
			int departmentNNT =0;//定义宿舍总人数
			long departmentAllnumber=0;//总共需要的签到次数
			Map<String, Object> classCountMap = new HashMap<String, Object>();//定义返回的map
			departmentNNT = stuInfoService.selectStuInfoByDormId(dormInfo.getDormId()).size();
			departmentAllnumber = dateSize*departmentNNT;
			List<DormSigninInfo> dormSigninList = dormSigninInfoService.findDormSigninList(paramsMap);
			for (DormSigninInfo dormSigninInfo : dormSigninList) {
				String dormSigninState = dormSigninInfo.getDormSigninState();
				if (dormSigninState.equals("正常")) {
					zhengchang++;
				}else if (dormSigninState.equals("晚归")) {
					wangui++;
				}
				
			}
			List<LeaveInfo> findLeaveList = leaveInfoService.findLeaveList(paramsMap);
			for (LeaveInfo leaveInfo : findLeaveList) {
				//获取到请假的时间的所有日期
				String startLeaveTime = leaveInfo.getStartLeaveTime();
				String endLeaveTime = leaveInfo.getEndLeaveTime();
				//获取请假的天数
				long qingjiaDate = DateUtil.getDateMinus(startLeaveTime, endLeaveTime, DateUtil.DATE_TIME_PATTERN)/60/24;
				qingjiaDate++;
				qingjia=qingjia+qingjiaDate;//该班级总共请假的天数
			}
			wanguiTotal = wanguiTotal+wangui;
			weiguiTotal = (int) (weiguiTotal+(departmentAllnumber-wangui-zhengchang-qingjia));
			zhengchangTotal = zhengchangTotal+zhengchang;
			classCountMap.put("name", dormInfo.getDormAddress());
			classCountMap.put("wanGui", wangui);
			classCountMap.put("weiGui", departmentAllnumber-wangui-zhengchang-qingjia);
			classCountMap.put("zhengChang", zhengchang);
			classCountMap.put("qingJia", qingjia);
			classCountMap.put("NNT", departmentNNT);
			classCountMap.put("allNumber", departmentAllnumber);
			classCountList.add(classCountMap);
		}
		Map<String, Object> countTotal = new HashMap<String, Object>();
		countTotal.put("zhengChang", zhengchangTotal);
		countTotal.put("wanGui", wanguiTotal);
		countTotal.put("weiGui", weiguiTotal);
		map.addAttribute("departmentInfos", departmentInfos);	
		map.addAttribute("countTotal", countTotal);
		map.addAttribute("departmentId", departmentId);
		map.addAttribute("classInfos", classInfos);
		map.addAttribute("classId", classId);
		map.addAttribute("startTime", startTime);
		map.addAttribute("endTime", endTime);
		map.addAttribute("className", classInfo.getClassName());
		map.addAttribute("countList", classCountList);
		map.addAttribute("findDormList", findDormList);
		return "reception/dromAttendanceStatistics";
    }
    /**
     * 某个宿舍的学生考勤签到统计
     * @param departmentId
     */
    @RequestMapping(value="dormDormSignInStatistics")
    public String dormDormSignInStatistics(String dormName,String dormId,String departmentId,String classId,String startTime,String endTime,ModelMap map){
    	/**
    	 * 定义总共的集合
    	 */
    	List<Map<String, Object>> dormCountList = new ArrayList<Map<String,Object>>();//返回页面的集合
    	ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String schoolId = shiroUser.schoolId;
    	List<DepartmentInfo> departmentInfos = departmentInfoService.findListDepartmentInfo(schoolId);
    	List<ClassInfo> classInfos = classInfoService.getAllClassByDepartmentId(departmentId);
    	if (dormId==null) {
			//通过班级和宿舍名称来查询宿舍
    		dormId = dormInfoService.findDormByClassIdAndDormName(classId, dormName).getDormId();
		}
    	int zhengchangTotal =0;//所有的正常
    	int wanguiTotal = 0;
		int weiguiTotal = 0;
		//根据班级获取到宿舍信息
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("startTime", startTime);
		paramsMap.put("endTime", endTime);
		List<DormInfo> findDormList = dormInfoService.getDormInfo2(schoolId, classId);//获取到这个班所有的宿舍
		DormInfo dorm = dormInfoService.findDormInfoById(dormId);
		List<StuInfo> stuInfos = stuInfoService.selectStuInfoByDormId(dormId);
		for (StuInfo stuInfo : stuInfos) {
			Map<String, Object> stuCoutnMap = new HashMap<String, Object>();//定义返回的map
			long qingjia = 0;
			int zhengchang = 0;//正常签到
			int wangui = 0;//定义晚归的次数
			long departmentAllnumber=DateUtil.findDates(startTime,endTime).size();//需要签到的次数;//总共需要的签到次数
			paramsMap.put("stuId", stuInfo.getStuId());
			List<DormSigninInfo> dormSigninList = dormSigninInfoService.findDormSigninList(paramsMap);
			for (DormSigninInfo dormSigninInfo : dormSigninList) {
				String dormSigninState = dormSigninInfo.getDormSigninState();
				if (dormSigninState.equals("正常")) {
					zhengchang++;
				}else if (dormSigninState.equals("晚归")) {
					wangui++;
				}
				
			}
			List<LeaveInfo> findLeaveList = leaveInfoService.findPeriodOfTimeLeave(paramsMap);
			for (LeaveInfo leaveInfo : findLeaveList) {
				//获取到请假的时间的所有日期
				String startLeaveTime = leaveInfo.getStartLeaveTime();
				String endLeaveTime = leaveInfo.getEndLeaveTime();
				//获取请假的天数
				long qingjiaDate = DateUtil.getDateMinus(startLeaveTime, endLeaveTime, DateUtil.DATE_TIME_PATTERN)/60/24;
				qingjiaDate++;
				qingjia=qingjia+qingjiaDate;//该班级总共请假的天数
			}
			wanguiTotal = wanguiTotal+wangui;
			weiguiTotal = (int) (weiguiTotal+(departmentAllnumber-wangui-zhengchang-qingjia));
			zhengchangTotal = zhengchangTotal+zhengchang;
			stuCoutnMap.put("stuId", stuInfo.getStuId());
			stuCoutnMap.put("stuName", stuInfo.getStuName());
			stuCoutnMap.put("wanGui", wangui);
			stuCoutnMap.put("weiGui", departmentAllnumber-wangui-zhengchang-qingjia);
			stuCoutnMap.put("zhengChang", zhengchang);
			stuCoutnMap.put("qingJia", qingjia);
			stuCoutnMap.put("allNumber", departmentAllnumber);
			dormCountList.add(stuCoutnMap);
		}
		Map<String, Object> countTotal = new HashMap<String, Object>();
		countTotal.put("zhengChang", zhengchangTotal);
		countTotal.put("wanGui", wanguiTotal);
		countTotal.put("weiGui", weiguiTotal);
		map.addAttribute("departmentInfos", departmentInfos);	
		map.addAttribute("countTotal", countTotal);
		map.addAttribute("departmentId", departmentId);
		map.addAttribute("classInfos", classInfos);
		map.addAttribute("classId", classId);
		map.addAttribute("dormId", dormId);
		map.addAttribute("startTime", startTime);
		map.addAttribute("endTime", endTime);
		map.addAttribute("dromName", dorm.getDormAddress());
		map.addAttribute("dormCountList", dormCountList);
		map.addAttribute("findDormList", findDormList);
		return "reception/dromStuAttendanceStatistics";
    }
    /**
     * 某个班级宿舍考勤签到统计
     * @param departmentId
     */
    @RequestMapping(value="findStuPeriodOfTimeWeiGui")
    public void findStuPeriodOfTimeWeiGui(HttpServletResponse repon,String stuId,String startTime,String state,String endTime,ModelMap map){
    	List<Map<String, Object>> zhengChangList = new ArrayList<Map<String,Object>>();//晚归
    	List<Map<String, Object>> wanGuiList = new ArrayList<Map<String,Object>>();//晚归
 	    List<Map<String, Object>> weiGuiList = new ArrayList<Map<String,Object>>();//未归
		List<Date> findDates = DateUtil.findDates(startTime, endTime);
		Map<String, String> paramsMap = new HashMap<String, String>();
    	paramsMap.put("startTime", startTime);
    	paramsMap.put("endTime", endTime);
    	paramsMap.put("stuId", stuId);
    	DormInfo dormListForStuId = dormInfoService.getDormListForStuId(stuId);
    	//获取该学生这段时间内所需要签到的所有宿舍考勤id
    	List<String> dormSigninIdList = getDormSigninIdList(findDates,dormListForStuId,stuId);
    	List<DormSigninInfo> dormList = dormSigninInfoService.findDormSigninList(paramsMap);
    	for (DormSigninInfo dormSigninInfo : dormList) {
    		//移除已经签到的id
    		if (dormSigninIdList.contains(dormSigninInfo.getDormSigninId())) {
    			dormSigninIdList.remove(dormSigninInfo.getDormSigninId());
			}
			if (dormSigninInfo.getDormSigninState().equals("晚归")) {
				Map<String, Object> wanGuiMap = new HashMap<String, Object>();
				wanGuiMap.put("signinDate", DateUtil.format(dormSigninInfo.getDormSigninTime(), DateUtil.DATE_PATTERN));
				wanGuiMap.put("week", DateUtil.getWeekOfDate(dormSigninInfo.getDormSigninTime()));
				String signinDate = DateUtil.format(dormSigninInfo.getDormSigninTime(), DateUtil.TIME_PATTERN);
				String dateMinus = DateUtil.getTimeDifference(dormListForStuId.getDormSigninEndTime()+":00",signinDate);//获取到迟到的总分钟数
				wanGuiMap.put("date", dateMinus);
				wanGuiMap.put("normalTime",dormListForStuId.getDormSigninStartTime()+"-"+dormListForStuId.getDormSigninEndTime());
				wanGuiList.add(wanGuiMap);
			}else if (dormSigninInfo.getDormSigninState().equals("正常")) {
				Map<String, Object> zhengChangMap = new HashMap<String, Object>();
				zhengChangMap.put("signinDate", DateUtil.format(dormSigninInfo.getDormSigninTime(), DateUtil.DATE_PATTERN));
				zhengChangMap.put("week", DateUtil.getWeekOfDate(dormSigninInfo.getDormSigninTime()));
				zhengChangList.add(zhengChangMap);
			}
		}
    	List<LeaveInfo> findLeaveList = leaveInfoService.findPeriodOfTimeLeave(paramsMap);
    	for (LeaveInfo leaveInfo : findLeaveList) {
			//获取到请假的时间的所有日期
			String startLeaveTime = leaveInfo.getStartLeaveTime();
			String endLeaveTime = leaveInfo.getEndLeaveTime();
			List<Date> findDates1 =  DateUtil.findDates(startLeaveTime, endLeaveTime);
			List<String> leaveDormSigninIdList = getDormSigninIdList(findDates1,dormListForStuId,stuId);
			for (String string : leaveDormSigninIdList) {
				if (dormSigninIdList.contains(string)) {
					dormSigninIdList.remove(string);
				}
			}
		}
    	if (state.equals("未归")) {
			for (String dormSigninId : dormSigninIdList) {
				String date = dormSigninId.substring(0, 10).replaceAll("_", "-");
				Map<String, Object> weiGuiMap = new HashMap<String, Object>();
				weiGuiMap.put("signinDate", date);
				weiGuiMap.put("week", DateUtil.getWeekOfDate(DateUtil.ConverToDate(date)));
				weiGuiList.add(weiGuiMap);
			}
		}
    	Gson gson = new Gson();
		String json = null;
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			if (state.equals("晚归")) {
				json = gson.toJson(wanGuiList);
			}else if (state.equals("未归")) {
				json = gson.toJson(weiGuiList);
			}else if (state.equals("正常")) {
				json = gson.toJson(zhengChangList);
			}
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * 获取到选择的时间某个学生宿舍签到id（只需要Id）
     */
    public List<String> getDormSigninIdList(List<Date> findDates,DormInfo dormInfo,String stuId){
    	List<String> dormSigninIdList = new ArrayList<String>();
    	for (Date date : findDates) {
			String format = DateUtil.format(date, DateUtil.DATE_PATTERN1);
			String startTime = dormInfo.getDormSigninStartTime().replaceAll(":", "_");
			String endTime = dormInfo.getDormSigninEndTime().replaceAll(":", "_");
			String dormSigninId = format+"_"+startTime+"_"+endTime+"_"+stuId;
			dormSigninIdList.add(dormSigninId);
		}
    	return dormSigninIdList;
    }
}

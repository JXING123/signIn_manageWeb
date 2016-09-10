package com.cn.tonyou.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cn.tonyou.pojo.ClassInfo;
import com.cn.tonyou.pojo.ClockingInfo;
import com.cn.tonyou.pojo.CourseInfo;
import com.cn.tonyou.pojo.DepartmentInfo;
import com.cn.tonyou.pojo.LeaveInfo;
import com.cn.tonyou.pojo.SchoolInfo;
import com.cn.tonyou.pojo.StuInfo;
import com.cn.tonyou.pojo.SubjectInfo;
import com.cn.tonyou.pojo.SysUserInfo;
import com.cn.tonyou.service.IClassInfoService;
import com.cn.tonyou.service.IClockingService;
import com.cn.tonyou.service.ICourseInfoService;
import com.cn.tonyou.service.IDepartmentInfoService;
import com.cn.tonyou.service.ILeaveInfoService;
import com.cn.tonyou.service.ISchoolInfoService;
import com.cn.tonyou.service.IStuInfoService;
import com.cn.tonyou.service.ISubjectInfoService;
import com.cn.tonyou.service.ISysUserInfoService;
import com.cn.tonyou.shiro.MyShiro.ShiroUser;
import com.cn.tonyou.utils.DateUtil;
import com.google.gson.Gson;
@Controller
@RequiresPermissions(value="上课考勤管理")
public class ClockingController {
	@Resource
	IClockingService clockingService;	//考勤
	@Resource
	IDepartmentInfoService departmentInfoService;	//系别
	@Resource
	IClassInfoService classInfoService;
	@Resource
	IStuInfoService stuInfoService;
	@Resource
	ISchoolInfoService schoolInfoService;
	@Autowired
	ICourseInfoService courseInfoService;
	@Resource
	ILeaveInfoService leaveInfoService;
	@Resource
	ISysUserInfoService sysUserInfoService;
	@Resource
	ISubjectInfoService subjectInfoService;
	/**
	 * 根据所选条件查询所需要的考勤信息
	 * @param map
	 * @param departmentId
	 * @param classId
	 * @param selDate
	 * @return
	 */
	@RequiresPermissions(value="查询上课考勤")
	@RequestMapping("findClockingList")
	public String findClockingList(ModelMap map,String schoolId,String departmentId,String classId,String selDate){
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
		paramsMap.put("selDate", selDate);
		if (schoolId!=null && !schoolId.equals("")) {
			departmentInfoList = departmentInfoService.findListDepartmentInfo(schoolId);
		}
		//查询某个系别所有的班级
		if (departmentId!=null && !departmentId.equals("")) {
			classInfoList = classInfoService.findListClassInfo(paramsMap);
		}
		String clSysUserId = "";
		String siSysUserId = "";
		
		if (!subject.hasRole("系统管理员") && !subject.hasRole("学校管理员")){
			if (subject.hasRole("辅导员")) {
				clSysUserId = shiroUser.id;
			}
			if (subject.hasRole("任课教师")) {
				siSysUserId = shiroUser.id;
			}
			paramsMap.put("clSysUserId", clSysUserId);
			paramsMap.put("siSysUserId", siSysUserId);
			//获取到自己管理的班级信息
			classInfoList = classInfoService.findSysClassId(paramsMap);
		}
		//获取考勤信息
		List<ClockingInfo> clockingInfos = clockingService.findByParamsClocking(paramsMap);
		//返回到页面
		map.addAttribute("schoolInfoList", schoolInfoList);
		map.addAttribute("departmentInfoList", departmentInfoList);
		map.addAttribute("classInfoList", classInfoList);
		map.addAttribute("clockingInfos", clockingInfos);
		map.addAttribute("classId", classId);
		map.addAttribute("schoolId", schoolId);
		map.addAttribute("departmentId", departmentId);
		map.addAttribute("selDate", selDate);
		return "reception/attendance";
	}
	/**
	 * 获取单个考勤签到信息
	 * @return
	 */
	@RequiresPermissions(value="查询上课考勤")
	@RequestMapping("findByIdClocking")
	public String findByIdClocking(ModelMap map,ClockingInfo clockingInfo) {
		ClockingInfo clocking =  clockingService.findByIdClocking(clockingInfo);
		map.addAttribute("clockingInfo",clocking);
		return "reception/attendanceDetails";
	}
	/**
	 * 添加上课考勤信息
	 * @return
	 */
	@RequiresPermissions(value="新增上课考勤")
	@RequestMapping("addClocking")
	public String addClocking(ClockingInfo clockingInfo) {
		clockingService.addClocking(clockingInfo);
		return "forward:findClockingList";
	}
	
	/**
	 * 修改上课考勤信息
	 * @return
	 */
	@RequiresPermissions(value="修改上课考勤")
	@RequestMapping("updateClocking")
	public String updateClocking(ClockingInfo clockingInfo) {
		clockingService.updateClocking(clockingInfo);
		return "forward:findClockingList";
	} 
	/**   
	 * 删除上课考勤信息
	 * @param clockingInfo
	 * @return
	 */
	@RequiresPermissions(value="删除上课考勤")
	@RequestMapping("deleteClocking")
	public String deleteClocking(ClockingInfo clockingInfo){
		clockingService.deleteClocking(clockingInfo);
		return "forward:findClockingList";
	}
	
	 /**
     * 所有系别上课考勤签到统计
     * @param departmentId
     */
    @RequestMapping(value="departmentAattendanceStatistics")
    public String departmentAattendanceStatistics(String startTime,String endTime,ModelMap map){
		if (startTime==null && endTime==null) {
    		Date date = new Date();//当前日期
    		Calendar calendar = Calendar.getInstance();//日历对象
    		calendar.setTime(date);//设置当前日期
    		calendar.add(Calendar.MONTH, -1);//月份减一
    		startTime = DateUtil.format(calendar.getTime(), DateUtil.DATE_PATTERN);
    		endTime = DateUtil.format(date, DateUtil.DATE_PATTERN);
		}
		setStart(startTime);
		setEnd(endTime);
		departmentCount(map);
		return "reception/departmentAattendanceStatistics";
    }
    public void departmentCount(ModelMap map){
    	ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String schoolId = shiroUser.schoolId;
		List<Map<String, Object>> departmentCountList = new ArrayList<Map<String,Object>>();
		List<DepartmentInfo> departmentInfos = departmentInfoService.findListDepartmentInfo(schoolId);
		for (DepartmentInfo departmentInfo : departmentInfos) {
			Map<String, Object> departmentMap = new HashMap<String, Object>();
			int chidao = 0;//迟到打卡次数
			int zaotui = 0;//早退打卡次数
			int kuangke = 0;//旷课节
			int qiandaoChishu = 0;
			int queKa = 0;//上课缺卡
			long qingjia = 0;//事假
			List<ClassInfo> classInfos = classInfoService.getAllClassByDepartmentId(departmentInfo.getDepartmentId());
			Map<String, String> paramsMap = new HashMap<String, String>();
	    	paramsMap.put("startTime", start);
	    	paramsMap.put("endTime", end);
			for (ClassInfo classInfo : classInfos) {
				List<StuInfo> stuInfos = stuInfoService.selectStuInfoByClassId(classInfo.getClassId());
		     	for (StuInfo stuInfo : stuInfos) {
		    		paramsMap.put("stuId", stuInfo.getStuId());
					//获取到某段时间这个学生的考勤
		    		List<ClockingInfo> clockingList = clockingService.getStuByIdAndTime(stuInfo.getStuId(), start, end);
		    		qiandaoChishu =qiandaoChishu +clockingList.size();
					//获取该学生这段时间内所需要签到的所有考勤id
					List<String> clockingIdList = getClockingIdList(paramsMap,false);
					for (ClockingInfo clockingInfo : clockingList) {
						//剔除所有已经签到的考勤id
						if (clockingIdList.contains(clockingInfo.getClockingId())) {
							clockingIdList.remove(clockingInfo.getClockingId());
						}
						//获取到考勤状态
						String clockState = clockingInfo.getClockState();
						//获取正常的结束考勤时间、
						if (clockState.equals("迟到")) {
							chidao++;
						}else if (clockState.equals("早退")) {
							zaotui++;
						}
					}
					//获取这段时间这个学生的请假信息，请假视为正常考勤
					List<LeaveInfo> leaveList = leaveInfoService.findPeriodOfTimeLeave(paramsMap);
					//请假所需要的签到的考勤Id
					List<String> leaveClockingIdList = null;
					for (LeaveInfo leaveInfo : leaveList) {
						Map<String, String> leaveMap = new HashMap<String, String>();
						leaveMap.put("stuId", stuInfo.getStuId());
						//获取到请假的时间的所有日期
						String startLeaveTime = leaveInfo.getStartLeaveTime();
						leaveMap.put("startTime", startLeaveTime);
						String endLeaveTime = leaveInfo.getEndLeaveTime();
						leaveMap.put("endTime", endLeaveTime);
						//获取请假的天数
						long qingjiaDate = DateUtil.getDateMinus(startLeaveTime, endLeaveTime, DateUtil.DATE_TIME_PATTERN)/60/24;
						if (qingjiaDate==0) {
							qingjiaDate++;
						}
						qingjia += qingjia+qingjiaDate;
						//获取请假需要签到的所有考勤id
						leaveClockingIdList= getClockingIdList(leaveMap,false);
						for (String string : leaveClockingIdList) {
							//移除所有的请假需要签到的考勤id
							if (clockingIdList.contains(string)) {
								clockingIdList.remove(string);
							}
						}
					}
					/**	获取旷课的正常节数
					 * 正常：上课打卡，下班打卡均无迟到、早退；
						迟到：晚于上课班打卡时间；
						早退：早于下课打卡时间；
						缺卡：上课或下课未打卡；
						旷课：上下课均未打卡；
					 */
					//计算旷课的节数
					List<String> clockingIdList1 = new ArrayList<String>();
					for (String clockingId : clockingIdList) {
						String substring = clockingId.substring(1);
						//将去除上下课的id放入到一个新的集合重
						clockingIdList1.add(substring);
					}
					Set<String> uniqueSet = new HashSet<String>(clockingIdList1);
			        for (String temp : uniqueSet) {
			            int frequency = Collections.frequency(clockingIdList1, temp);
			            if (frequency==2) {
							kuangke++;
						}else{
							queKa++;//将只出现过一次的加入到新集合
						}
			        }
				}
			}
			//统计总数
	        departmentMap.put("departmentName", departmentInfo.getDeprtmentName());
	        departmentMap.put("departmentId",departmentInfo.getDepartmentId());
	        departmentMap.put("qianDaoChiShu",qiandaoChishu);
	        departmentMap.put("chiDao", chidao);//迟到次数
	        departmentMap.put("zaoTui", zaotui);
	        departmentMap.put("kuangKe", kuangke);
	        departmentMap.put("queKa", queKa);
	        departmentMap.put("qingJia", qingjia);
	        departmentMap.put("startTime", start);
	        departmentMap.put("endTime", end);
	        departmentMap.put("schoolName", departmentInfo.getSchool().getSchoolName());
	        departmentCountList.add(departmentMap);
		}
		map.addAttribute("departmentCountList",departmentCountList);
    }
    /**
     * 某个系别上课考勤签到统计
     * @param departmentId
     */
    @RequestMapping(value="classAttendanceStatistics")
    public String attendanceInClassSignInStatistics(String departmentId,String startTime,String endTime,ModelMap map,String departmentName){
    	
    	ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String schoolId = shiroUser.schoolId;
		if (departmentName!=null) {
			DepartmentInfo findDepartmentByName = departmentInfoService.findDepartmentByName(departmentName, schoolId);
			departmentId = findDepartmentByName.getDepartmentId();
		}
		
		List<DepartmentInfo> departmentInfos = departmentInfoService.findListDepartmentInfo(schoolId);
		map.addAttribute("departmentInfos", departmentInfos);
		if (departmentId==null) {
			departmentId = departmentInfos.get(0).getDepartmentId();
		}
		if (startTime==null && endTime==null) {
    		Date date = new Date();//当前日期
    		Calendar calendar = Calendar.getInstance();//日历对象
    		calendar.setTime(date);//设置当前日期
    		calendar.add(Calendar.MONTH, -1);//月份减一
    		startTime = DateUtil.format(calendar.getTime(), DateUtil.DATE_PATTERN);
    		endTime = DateUtil.format(date, DateUtil.DATE_PATTERN);
		}
		map.addAttribute("departmentId", departmentId);
		setdId(departmentId);
		setStart(startTime);
		setEnd(endTime);
		return "reception/classAttendanceStatistics";
    }
    
    /**
     *获取某个系别所有班级某段时间的上课考勤情况返回到页面ajax
     */
    @RequestMapping(value="getDIClassAttendanceStatus")
    public void getDIClassAttendanceStatus(HttpServletResponse response,String total) {
    	/**
    	 * 定义总共的集合
    	 */
    	int kuangkeTotal = 0;//总体应该签到的此处
    	int zhengchangTotal =0;//所有的正常
    	int chidaoTotal = 0;
		int zaotuiTotal = 0;
    	//获取某个系别所有的所有考勤信息
    	Map<String, String> paramsMap = new HashMap<String, String>();
    	paramsMap.put("startTime", start);
    	paramsMap.put("endTime", end);
    	//定义返回页面的对象的集合
    	List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
    	List<ClassInfo> classInfos = classInfoService.getAllClassByDepartmentId(dId);
    	for (ClassInfo classInfo : classInfos) {
    		paramsMap.put("classId", classInfo.getClassId());
    		//定义
    		Map<String, Object> count = new HashMap<String, Object>();
    		//获取到这个班的的某段时间考勤信息
			List<ClockingInfo> clockingList = clockingService.findTimeAndClassByIdClocking(paramsMap);
			//获取到这个班的总人数
			int stuNumber = stuInfoService.selectStuInfoByClassId(classInfo.getClassId()).size();
			List<CourseInfo> courseList = courseInfoService.findClassByIdCourse(classInfo.getClassId());
			//获取这个班的每个人应该正常签到的次数
			int normalClockingCount = getNormalClockingCount(paramsMap,courseList);
			int chidao = 0;
			int zaotui = 0;
			int zhengchang = 0;
			for (ClockingInfo clockingInfo : clockingList) {
				String clockState = clockingInfo.getClockState();
				if (clockState.equals("正常")) {
					zhengchang++;
				}else if (clockState.equals("迟到")) {
					chidao++;
				}else if (clockState.equals("早退")) {
					zaotui++;
				}
			}
			//获取这段时间的这个班请假信息，请假视为正常考勤
			List<LeaveInfo> leaveList = leaveInfoService.findPeriodOfTimeLeave(paramsMap);
			int leaveClockingCount = 0;
			for (LeaveInfo leaveInfo : leaveList) {
				Map<String, String> leaveMap = new HashMap<String, String>();
				//获取到请假的时间的所有日期
				leaveMap.put("startTime", leaveInfo.getStartLeaveTime());
				leaveMap.put("endTime", leaveInfo.getEndLeaveTime());
				leaveClockingCount += getNormalClockingCount(leaveMap,courseList);
			}
			zhengchang = zhengchang+leaveClockingCount;
			//旷课
			int kuangke = normalClockingCount*stuNumber-zhengchang-zaotui-chidao;
			//统计总数
			zhengchangTotal = zhengchangTotal+zhengchang;
			chidaoTotal = chidaoTotal+chidao;
			zaotuiTotal = zaotuiTotal+zaotui;
			kuangkeTotal = kuangkeTotal +kuangke;
			
			count.put("zhengChang", zhengchang);
			count.put("chiDao", chidao);
			count.put("zaoTui", zaotui);
			count.put("kuangKe", kuangke);
			count.put("className", classInfo.getClassName());
			count.put("startTime", start);
			count.put("endTime", end);
			mapList.add(count);
		}
    	response.setContentType("text/html;charset=utf-8");
         PrintWriter out;
         try {
             out = response.getWriter();
             Gson gson = new Gson();
             if (total==null) {
            	 String str = gson.toJson(mapList);
                 out.write(str);
			}else {
				Map<String, Integer> countTotal = new HashMap<String, Integer>();
				countTotal.put("zhengChang", zhengchangTotal);
				countTotal.put("chiDao", chidaoTotal);
				countTotal.put("zaoTui", zaotuiTotal);
				countTotal.put("kuangKe", kuangkeTotal);
				String str = gson.toJson(countTotal);
                out.write(str);
			}
             out.flush();
             out.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
	}
    /**
     * 某个班的考勤统计
     * @param departmentId
     * @param startTime
     * @param endTime
     * @param className
     * @return
     */
	@RequestMapping(value="classStatistics")
     public String classStatistics(String departmentId,String startTime,String endTime,String className,ModelMap map){
     	if (startTime==null && endTime==null) {
     		Date date = new Date();//当前日期
     		Calendar calendar = Calendar.getInstance();//日历对象
     		calendar.setTime(date);//设置当前日期
     		calendar.add(Calendar.MONTH, -1);//月份减一
     		startTime = DateUtil.format(calendar.getTime(), DateUtil.DATE_PATTERN);
     		endTime = DateUtil.format(date, DateUtil.DATE_PATTERN);
 		}
     	setdId(departmentId);
		setStart(startTime);
		setEnd(endTime);
		setClassName1(className);
		stuCount(map);
     	map.addAttribute("departmentId", departmentId);
     	map.addAttribute("className", className);
     	return "reception/classStatistics";
     }
     /**
      * 统计这个班所有的学生的考勤情况（学生考勤报表）
      */
     public void stuCount(ModelMap map){
    	//获取该班级信息
     	ClassInfo classInfo = classInfoService.selectClassByIdAndName(className1, dId);
     	//获取到该班级所有的学生
     	Map<String, String> paramsMap = new HashMap<String, String>();
    	paramsMap.put("startTime", start);
    	paramsMap.put("endTime", end);
    	List<Map<String, Object>> stuCountMaps = new ArrayList<Map<String,Object>>();
     	List<StuInfo> stuInfos = stuInfoService.selectStuInfoByClassId(classInfo.getClassId());
     	for (StuInfo stuInfo : stuInfos) {
    		paramsMap.put("stuId", stuInfo.getStuId());
    		Map<String, Object> stuCount = new HashMap<String, Object>();
			//获取到某段时间这个学生的考勤
    		List<ClockingInfo> clockingList = clockingService.getStuByIdAndTime(stuInfo.getStuId(), start, end);
			int chidao = 0;//迟到打卡次数
			int zaotui = 0;//早退打卡次数
			long chidaoTime = 0L;//迟到总时间
			long zaotuiTime = 0L;//早退总时间
			int shangkeQueKa = 0;//上课缺卡
			int kuangke = 0;
			int xiakeQueKa = 0;//下课签到次数
			long shijia = 0;//事假
			long bingjia = 0;
			//获取该学生这段时间内所需要签到的所有考勤id
			List<String> clockingIdList = getClockingIdList(paramsMap,false);
			for (ClockingInfo clockingInfo : clockingList) {
				//剔除所有已经签到的考勤id
				if (clockingIdList.contains(clockingInfo.getClockingId())) {
					clockingIdList.remove(clockingInfo.getClockingId());
				}
				//获取到考勤状态
				String clockState = clockingInfo.getClockState();
				//获取正常的结束考勤时间、
				String startTime = clockingInfo.getCourse().getStartTime()+":00";
				String endTime = clockingInfo.getCourse().getEndTime()+":00";
				if (clockState.equals("迟到")) {
					chidao++;
					String clockingDate = DateUtil.format(clockingInfo.getClockingDate(), DateUtil.TIME_PATTERN);
					chidaoTime += DateUtil.getDateMinus(startTime, clockingDate,DateUtil.TIME_PATTERN);//获取到迟到的总分钟数
				}else if (clockState.equals("早退")) {
					String clockingDate = DateUtil.format(clockingInfo.getClockingDate(), DateUtil.TIME_PATTERN);
					zaotuiTime= DateUtil.getDateMinus(clockingDate, endTime,DateUtil.TIME_PATTERN);//获取到早退的总分钟数
					zaotui++;
				}
			}
			//获取这段时间这个学生的请假信息，请假视为正常考勤
			List<LeaveInfo> leaveList = leaveInfoService.findPeriodOfTimeLeave(paramsMap);
			//请假所需要的签到的考勤Id
			List<String> leaveClockingIdList = null;
			for (LeaveInfo leaveInfo : leaveList) {
				Map<String, String> leaveMap = new HashMap<String, String>();
				leaveMap.put("stuId", stuInfo.getStuId());
				//获取到请假的时间的所有日期
				String startLeaveTime = leaveInfo.getStartLeaveTime();
				leaveMap.put("startTime", startLeaveTime);
				String endLeaveTime = leaveInfo.getEndLeaveTime();
				leaveMap.put("endTime", endLeaveTime);
				//获取请假的天数
				long qingjia = DateUtil.getDateMinus(startLeaveTime, endLeaveTime, DateUtil.DATE_TIME_PATTERN)/60/24;
				if (qingjia==0) {
					qingjia++;
				}
				if (leaveInfo.getLeaveType().equals("病假")) {
					bingjia += qingjia;
				}else {
					shijia +=qingjia;
				}
				//获取请假需要签到的所有考勤id
				leaveClockingIdList= getClockingIdList(leaveMap,false);
				for (String string : leaveClockingIdList) {
					//移除所有的请假需要签到的考勤id
					if (clockingIdList.contains(string)) {
						clockingIdList.remove(string);
					}
				}
			}
			/**	获取旷课的正常节数
			 * 正常：上课打卡，下班打卡均无迟到、早退；
				迟到：晚于上课班打卡时间；
				早退：早于下课打卡时间；
				缺卡：上课或下课未打卡；
				旷课：上下课均未打卡；
			 */
			//计算旷课的节数
			List<String> clockingIdList1 = new ArrayList<String>();
			for (String clockingId : clockingIdList) {
				String substring = clockingId.substring(1);
				//将去除上下课的id放入到一个新的集合重
				clockingIdList1.add(substring);
			}
			Set<String> uniqueSet = new HashSet<String>(clockingIdList1);
			List<String> clockingIdList2 = new ArrayList<String>();//定义缺卡的考勤id集合
	        for (String temp : uniqueSet) {
	            int frequency = Collections.frequency(clockingIdList1, temp);
	            if (frequency==2) {
					kuangke++;
				}else{
					clockingIdList2.add(temp);//将只出现过一次的加入到新集合
				}
	        }
	        //计算缺卡的次数
	        for (ClockingInfo clocking : clockingList) {
				for (String string : clockingIdList2) {
					String clockingId = clocking.getClockingId();
					if (clockingId.substring(1).equals(string)) {
						if (clockingId.substring(0, 1).equals("0")) {//0标识下课
							shangkeQueKa++;//上课缺卡加一
						}else{
							xiakeQueKa++;//下课缺卡加一
						}
					}
				}
			}
			//统计总数
	        stuCount.put("className", classInfo.getClassName());
	        stuCount.put("stuName",stuInfo.getStuName());
	        stuCount.put("stuId", stuInfo.getStuId());
			stuCount.put("clockingCount", clockingList.size());//签到次数
			stuCount.put("chiDao", chidao);//迟到次数
			stuCount.put("zaoTui", zaotui);
			stuCount.put("kuangKe", kuangke);
			stuCount.put("shangKeQueKa", shangkeQueKa);
			stuCount.put("xiaKeQueKa", xiakeQueKa);
			stuCount.put("chiDaoTime", chidaoTime);
			stuCount.put("zaoTuiTime", zaotuiTime);
			stuCount.put("shiJia", shijia);
			stuCount.put("bingJia", bingjia);
			stuCountMaps.add(stuCount);
		}
     	map.addAttribute("stuCountMaps", stuCountMaps);
     }

    /**
     * 获取某个班的上课考勤统计返回到页面ajax
     */
    @RequestMapping(value="getClassAttendanceStatus")
    public void getClassAttendanceStatus(HttpServletResponse response,String total) {
    	
    	Map<String, String> paramsMap = new HashMap<String, String>();
    	paramsMap.put("startTime", start);
    	paramsMap.put("endTime", end);
    	//定义返回页面的对象的集合
    	List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
    	//获取该班级信息
    	ClassInfo classInfo = classInfoService.selectClassByIdAndName(className1, dId);
    	//获取到该班级所有的学生
    	List<StuInfo> stuInfos = stuInfoService.selectStuInfoByClassId(classInfo.getClassId());
    	/**
    	 * 定义总共的集合
    	 */
    	int kuangkeTotal = 0;//总体应该签到的此处
    	int zhengchangTotal =0;//所有的正常
    	int chidaoTotal = 0;
		int zaotuiTotal = 0;
    	for (StuInfo stuInfo : stuInfos) {
    		paramsMap.put("stuId", stuInfo.getStuId());
    		Map<String, Object> stuCount = new HashMap<String, Object>();
			//获取到某段时间这个学生的考勤
    		List<ClockingInfo> clockingList = clockingService.getStuByIdAndTime(stuInfo.getStuId(), start, end);
    		List<CourseInfo> courseList = courseInfoService.findClassByIdCourse(classInfo.getClassId());
			int normalClockingCount = getNormalClockingCount(paramsMap,courseList);
			int chidao = 0;
			int zaotui = 0;
			int zhengchang = 0;
			for (ClockingInfo clockingInfo : clockingList) {
				String clockState = clockingInfo.getClockState();
				if (clockState.equals("正常")) {
					zhengchang++;
				}else if (clockState.equals("迟到")) {
					chidao++;
				}else if (clockState.equals("早退")) {
					zaotui++;
				}
			}
			//获取这段时间这个学生的请假信息，请假视为正常考勤
			List<LeaveInfo> leaveList = leaveInfoService.findPeriodOfTimeLeave(paramsMap);
			int leaveClockingCount = 0;
			for (LeaveInfo leaveInfo : leaveList) {
				Map<String, String> leaveMap = new HashMap<String, String>();
				//获取到请假的时间的所有日期
				leaveMap.put("startTime", leaveInfo.getStartLeaveTime());
				leaveMap.put("endTime", leaveInfo.getEndLeaveTime());
				leaveClockingCount += getNormalClockingCount(leaveMap,courseList);
			}
			zhengchang = zhengchang+leaveClockingCount;
			int kuangke = normalClockingCount-zhengchang-zaotui-chidao;
			//统计总数
			zhengchangTotal = zhengchangTotal+zhengchang;
			chidaoTotal = chidaoTotal+chidao;
			zaotuiTotal = zaotuiTotal+zaotui;
			kuangkeTotal = kuangkeTotal +kuangke;
			
			stuCount.put("zhengChang", zhengchang);
			stuCount.put("chiDao", chidao);
			stuCount.put("zaoTui", zaotui);
			stuCount.put("kuangKe", kuangke);
			stuCount.put("stuName", stuInfo.getStuId()+"-"+stuInfo.getStuName());
			stuCount.put("startTime", start);
			stuCount.put("endTime", end);
			mapList.add(stuCount);
		}
    	response.setContentType("text/html;charset=utf-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            Gson gson = new Gson();
            if (total==null) {
            	 String str = gson.toJson(mapList);
                 out.write(str);
			}else {
				Map<String, Integer> countTotal = new HashMap<String, Integer>();
				countTotal.put("zhengChang", zhengchangTotal);
				countTotal.put("chiDao", chidaoTotal);
				countTotal.put("zaoTui", zaotuiTotal);
				countTotal.put("kuangKe", kuangkeTotal);
				String str = gson.toJson(countTotal);
                out.write(str);
			}
           
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
	}
    
    /**
     * 某个系别老师的出勤状况
     * @param departmentId
     * @param startTime
     * @param endTime
     * @param className
     * @return
     */
     @RequestMapping(value="classTeacherInStatistics")
     public String classTeacherInStatistics(String departmentId,String startTime,String endTime,ModelMap map){
    	 ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
 		String schoolId = shiroUser.schoolId;
 		List<DepartmentInfo> departmentInfos = departmentInfoService.findListDepartmentInfo(schoolId);
 		if (departmentId==null) {
 			departmentId = departmentInfos.get(0).getDepartmentId();
 		}
    	 if (startTime==null && endTime==null) {
     		Date date = new Date();//当前日期
     		Calendar calendar = Calendar.getInstance();//日历对象
     		calendar.setTime(date);//设置当前日期
     		calendar.add(Calendar.MONTH, -1);//月份减一
     		startTime = DateUtil.format(calendar.getTime(), DateUtil.DATE_PATTERN);
     		endTime = DateUtil.format(date, DateUtil.DATE_PATTERN);
 		}
     	setdId(departmentId);
		setStart(startTime);
		setEnd(endTime);
     	map.addAttribute("departmentId", departmentId);
     	map.addAttribute("departmentInfos", departmentInfos);
     	return "reception/classTeacherInStatistics";
     }
     /**
      * 某个系别老师某段时间的上课考勤统计返回到页面ajax
      */
     @RequestMapping(value="getDIClassTeacherInStatistics")
     public void getDIClassTeacherInStatistics(HttpServletResponse response,String total) {
     	Map<String, String> paramsMap = new HashMap<String, String>();
     	paramsMap.put("startTime", start);
     	paramsMap.put("endTime", end);
     	/**
    	 * 定义总共的集合
    	 */
    	int kuangkeTotal = 0;//总体应该签到的此处
    	int zhengchangTotal =0;//所有的正常
    	int chidaoTotal = 0;
		int zaotuiTotal = 0;
     	//定义返回页面的对象的集合
     	List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
     	//获取该系别所有的老师
     	List<SysUserInfo> findRoleTeacherSysUserList = sysUserInfoService.findRoleTeacherSysUserList(null,dId);
     	//获取到该班级所有的学生
     	for (SysUserInfo sysUserInfo : findRoleTeacherSysUserList) {
     		paramsMap.put("sysUserId", sysUserInfo.getSysUserId());
			List<CourseInfo> findCourseList = courseInfoService.findCourseList(paramsMap);
			//定义每个老师的到勤集合
    		Map<String, Object> count = new HashMap<String, Object>();
			int chidao = 0;
			int zaotui = 0;
			int zhengchang = 0;
			int normalClockingCount = 0;
			//根据课程数获取到需要签到的次数
			for (CourseInfo courseInfo : findCourseList){
				paramsMap.put("courseId", courseInfo.getCourseId());
	    		String classId = courseInfo.getClasses().getClassId();
				//获取到这个课程这个班的总人数
				int stuNumber = stuInfoService.selectStuInfoByClassId(classId).size();
				//获取这个班的总共应该正常签到的次数(一次课程需要打两次卡)
				normalClockingCount += 2*stuNumber;
				//获取到这个课程某段时间签到
 				List<ClockingInfo> clockingList = clockingService.findTimeAndCourseByIdClocking(paramsMap);
				for (ClockingInfo clockingInfo : clockingList) {
					String clockState = clockingInfo.getClockState();
					if (clockState.equals("正常")) {
						zhengchang++;
					}else if (clockState.equals("迟到")) {
						chidao++;
					}else if (clockState.equals("早退")) {
						zaotui++;
					}
				}
				
				//获取这段时间的这个班请假信息，请假视为正常考勤...........
				List<LeaveInfo> leaveList = leaveInfoService.findCourseByIdTimeLeave(paramsMap);
				int leaveClockingCount = 0;
				for (int i = 0; i < leaveList.size(); i++) {
					leaveClockingCount += 2;
				}
				zhengchang = zhengchang+leaveClockingCount; 
			}
			//旷课
			int kuangke = normalClockingCount-zhengchang-zaotui-chidao;
			//统计总数
			zhengchangTotal = zhengchangTotal+zhengchang;
			chidaoTotal = chidaoTotal+chidao;
			zaotuiTotal = zaotuiTotal+zaotui;
			kuangkeTotal = kuangkeTotal +kuangke;
			
			count.put("zhengChang", zhengchang);
			count.put("chiDao", chidao);
			count.put("zaoTui", zaotui);
			count.put("kuangKe", kuangke);
			count.put("sysUserName", sysUserInfo.getSysUserName());
			count.put("startTime", start);
			count.put("endTime", end);
			mapList.add(count);
		}
     	response.setContentType("text/html;charset=utf-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            Gson gson = new Gson();
            if (total==null) {
           	 String str = gson.toJson(mapList);
                out.write(str);
			}else {
				Map<String, Integer> countTotal = new HashMap<String, Integer>();
				countTotal.put("zhengChang", zhengchangTotal);
				countTotal.put("chiDao", chidaoTotal);
				countTotal.put("zaoTui", zaotuiTotal);
				countTotal.put("kuangKe", kuangkeTotal);
				String str = gson.toJson(countTotal);
               out.write(str);
			}
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
     	
     	

    /**
     * 获取某个班某段时间正常考勤的总次数(注入必须经过三层关系)
     */
    public int getNormalClockingCount(Map<String, String> paramsMap,List<CourseInfo> courseList){
    	//定义课程签到的总数
    	int MondayCICount = 0,TuesdayCICount = 0,WednesdayCICount = 0,ThursdayCICount = 0,FridayCICount = 0,SaturdayCICount = 0,SundayCICount = 0;
    	for (CourseInfo courseInfo : courseList) {
			String week = courseInfo.getWeek();
			if (week.equals("星期一")) {
				MondayCICount++;
			}else if (week.equals("星期二")) {
				TuesdayCICount++;
			}else if (week.equals("星期三")) {
				WednesdayCICount++;
			}else if (week.equals("星期四")) {
				ThursdayCICount++;
			}else if (week.equals("星期五")) {
				FridayCICount++;
			}else if (week.equals("星期六")) {
				SaturdayCICount++;
			}else if (week.equals("星期日")) {
				SundayCICount++;
			}
		}
    	
    	
    	//定义星期一到星期天出现的次数(默认为0)
    	int Monday = 0,Tuesday = 0,Wednesday = 0,Thursday = 0,Friday = 0,Saturday = 0,Sunday = 0;
    	List<Date> findDates = DateUtil.findDates(paramsMap.get("startTime"),paramsMap.get("endTime"));
    	for (Date date : findDates) {
			int week = DateUtil.getWeek(date);
			switch (week) {
			case 1:
				Monday++;
				break;
			case 2:
				Tuesday++;
				break;
			case 3:
				Wednesday++;			
				break;
			case 4:
				Thursday++;
				break;
			case 5:
				Friday++;
				break;
			case 6:
				Saturday++;
				break;
			case 0:
				Sunday++;
				break;
			default:
				break;
			}
		}
    	//定义一个list来存放这段时间所有需要上的课程（有重复）
    	int courseCount = MondayCICount*Monday+TuesdayCICount*Tuesday+WednesdayCICount*Wednesday+ThursdayCICount*Thursday+FridayCICount*Friday+SaturdayCICount*Saturday+SundayCICount*Sunday;
    	return courseCount*2;
    }
    /**
     * 获取到选择的时间某个学生所需要签到的所有签到信息（只需要Id）
     */
    public List<String> getClockingIdList(Map<String, String> paramsMap,boolean is){
    	List<String> clockingIdList = new ArrayList<String>();
    	List<Date> findDates = DateUtil.findDates(paramsMap.get("startTime"),paramsMap.get("endTime"));
    	String stuId = paramsMap.get("stuId");
    	for (Date date : findDates) {
    		String weekOfDate = DateUtil.getWeekOfDate(date);
			List<CourseInfo> courseList = courseInfoService.getWeekCourseInfoList(stuId, weekOfDate);
    		for (CourseInfo courseInfo : courseList) {
				//根据课程获取签到的id
    			String startTime = courseInfo.getStartTime().replaceAll(":", "_");
    			String endTime = courseInfo.getEndTime().replaceAll(":", "_");
    			String clockingId = "0_"+DateUtil.format(date, DateUtil.DATE_PATTERN1)+"_"+startTime+"_"+endTime+"_"+courseInfo.getSubject().getSubjectId()+"_"+stuId;
    			String clockingId1 = "1_"+DateUtil.format(date, DateUtil.DATE_PATTERN1)+"_"+startTime+"_"+endTime+"_"+courseInfo.getSubject().getSubjectId()+"_"+stuId;
    			if (is) {
    				clockingId=clockingId+"-"+courseInfo.getCourseId();
    				clockingId1=clockingId1+"-"+courseInfo.getCourseId();
				}
    			clockingIdList.add(clockingId);
    			clockingIdList.add(clockingId1);
			}
		}
    	
    	return clockingIdList;
    }
    
    
   
	/**
	 * 跳转考勤详情查询
	 * @return
	 */
	@RequestMapping(value="classAttendanceThedetail")
	public String classAttendanceThedetail(ModelMap map){
		ShiroUser shiroUser =  (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		List<DepartmentInfo> departmentInfos = departmentInfoService.findListDepartmentInfo(shiroUser.schoolId);
     	map.addAttribute("departmentInfos", departmentInfos);
		return "reception/classAttendanceThedetail";
	}
	
	/**
	 * 跳转考勤汇总查询
	 * @return
	 */
	@RequestMapping(value="classCourseAttendanceThedetail")
	public String classCourseAttendanceThedetail(ModelMap map){
		ShiroUser shiroUser =  (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		List<DepartmentInfo> departmentInfos = departmentInfoService.findListDepartmentInfo(shiroUser.schoolId);
     	map.addAttribute("departmentInfos", departmentInfos);
		return "reception/classCourseAttendanceThedetail";
	}
	
	/**
	 * 查询某个学生某段时间的旷课详情
	 */
	@RequestMapping(value="findStuPeriodOfTimeTruant")
	public void findStuPeriodOfTimeTruant(String stuId,String startTime,String endTime,HttpServletResponse repon,String state) {
		List<ClockingInfo> clockingList = clockingService.getStuByIdAndTime(stuId, startTime, endTime);
		List<Map<String, Object>> kuangkeList = new ArrayList<Map<String,Object>>();//旷课集合
		List<Map<String, Object>> shangkeQuekaList = new ArrayList<Map<String,Object>>();//上课缺卡集合
	    List<Map<String, Object>> xiakeQuekaList = new ArrayList<Map<String,Object>>();//下课缺卡集合
	    List<Map<String, Object>> chidaoList = new ArrayList<Map<String,Object>>();//迟到
	    List<Map<String, Object>> zaotuiList = new ArrayList<Map<String,Object>>();//早退
		//获取该学生这段时间内所需要签到的所有考勤id
		Map<String, String> paramsMap = new HashMap<String, String>();
    	paramsMap.put("startTime", startTime);  
    	paramsMap.put("endTime", endTime);
    	paramsMap.put("stuId", stuId);
		List<String> clockingIdList = getClockingIdList(paramsMap,true);
		for (ClockingInfo clockingInfo : clockingList) {
			//剔除所有已经签到的考勤id
			String clockingId = clockingInfo.getClockingId()+"-"+clockingInfo.getCourse().getCourseId();
			if (clockingIdList.contains(clockingId)) {
				clockingIdList.remove(clockingId);
			}
			if (!state.equals("上课缺卡")&&!state.equals("下课缺卡")&&!state.equals("旷课")) {
				//获取到考勤状态
				String clockState = clockingInfo.getClockState();
				//获取正常的结束考勤时间、
				String normalStartTime = clockingInfo.getCourse().getStartTime()+":00";
				String normalEndTime = clockingInfo.getCourse().getEndTime()+":00";
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("courseName", clockingInfo.getCourse().getSubject().getSubjectName());
				map.put("clockingDate",DateUtil.format( clockingInfo.getClockingDate(), DateUtil.DATE_PATTERN));
				map.put("startTime", clockingInfo.getCourse().getStartTime());
				map.put("endTime", clockingInfo.getCourse().getEndTime());
				map.put("week", clockingInfo.getCourse().getWeek());
	        	
	        	String dateMinus = null;
				if (clockState.equals("迟到")) {
					String clockingDate = DateUtil.format(clockingInfo.getClockingDate(), DateUtil.TIME_PATTERN);
					dateMinus = DateUtil.getTimeDifference(normalStartTime, clockingDate);//获取到迟到的总分钟数
					map.put("date", dateMinus);
					chidaoList.add(map);
				}else if (clockState.equals("早退")) {
					String clockingDate = DateUtil.format(clockingInfo.getClockingDate(), DateUtil.TIME_PATTERN);
					dateMinus = DateUtil.getTimeDifference(clockingDate, normalEndTime);//获取到早退的总分钟数
					map.put("date", dateMinus);
					zaotuiList.add(map);
				}
			}
			
		}
		//获取这段时间这个学生的请假信息，请假视为正常考勤
		List<LeaveInfo> leaveList = leaveInfoService.findPeriodOfTimeLeave(paramsMap);
		//请假所需要的签到的考勤Id
		List<String> leaveClockingIdList = null;
		for (LeaveInfo leaveInfo : leaveList) {
			Map<String, String> leaveMap = new HashMap<String, String>();
			leaveMap.put("stuId",stuId);
			//获取到请假的时间的所有日期
			String startLeaveTime = leaveInfo.getStartLeaveTime();
			leaveMap.put("startTime", startLeaveTime);
			String endLeaveTime = leaveInfo.getEndLeaveTime();
			leaveMap.put("endTime", endLeaveTime);
			//获取请假需要签到的所有考勤id
			leaveClockingIdList= getClockingIdList(leaveMap,true);
			for (String string : leaveClockingIdList) {
				//移除所有的请假需要签到的考勤id
				if (clockingIdList.contains(string)) {
					clockingIdList.remove(string);
				}
			}
		}
		if (!state.equals("迟到")&& !state.equals("早退") &&!state.equals("迟到时长")&& !state.equals("早退时长")) {
			//计算旷课的节数
			List<String> clockingIdList1 = new ArrayList<String>();
			for (String clockingId : clockingIdList) {
				String substring = clockingId.substring(1);
				//将去除上下课的id放入到一个新的集合重
				clockingIdList1.add(substring);
			}
			Set<String> uniqueSet = new HashSet<String>(clockingIdList1);
			List<String> clockingIdList2 = new ArrayList<String>();//定义缺卡的考勤id集合
			
	        for (String temp : uniqueSet) {
	            int frequency = Collections.frequency(clockingIdList1, temp);
	            if (frequency==2) {//旷课
	            	Map<String, Object> map = new HashMap<String, Object>();
	            	String clockingDate = temp.substring(1,11).replaceAll("_", "-");
	            	String courseId = temp.substring(temp.indexOf("-")+1);
	            	CourseInfo courseInfo = courseInfoService.findCourseInfoById(courseId);
	            	map.put("week", courseInfo.getWeek());
	            	map.put("clockingDate", clockingDate);
	            	map.put("courseName", courseInfo.getSubject().getSubjectName());
	            	map.put("startTime", courseInfo.getStartTime());
	            	map.put("endTime", courseInfo.getEndTime());
	            	kuangkeList.add(map);
				}else{
					clockingIdList2.add(temp);//将只出现过一次的加入到新集合
				}
	        }
	       if (state.equals("上课缺卡")||state.equals("下课缺卡")) {
		        //计算缺卡的次数
		        for (ClockingInfo clockingInfo : clockingList) {
					for (String string : clockingIdList2) {
						String clockingId = clockingInfo.getClockingId()+"-"+clockingInfo.getCourse().getCourseId();
						if (clockingId.substring(1).equals(string)) {
							Map<String, Object> map = new HashMap<String, Object>();
			            	String clockingDate = string.substring(1,11).replaceAll("_", "-");
			            	String courseId = string.substring(string.indexOf("-")+1);
			            	CourseInfo courseInfo = courseInfoService.findCourseInfoById(courseId);
			            	map.put("week", courseInfo.getWeek());
			            	map.put("clockingDate", clockingDate);
			            	map.put("courseName", courseInfo.getSubject().getSubjectName());
			            	map.put("startTime", courseInfo.getStartTime());
			            	map.put("endTime", courseInfo.getEndTime());
							if (clockingId.substring(0, 1).equals("0")){//0标识下课(上课缺卡)
								shangkeQuekaList.add(map);
							}else{
								xiakeQuekaList.add(map);
							}
						}
					}
				}
	       }
		}
		Gson gson = new Gson();
		String json = null;
		try {
			repon.setContentType("text/html; charset=utf-8");
			PrintWriter writer = repon.getWriter();
			if (state.equals("上课缺卡")) {
				json = gson.toJson(shangkeQuekaList);
			}else if (state.equals("下课缺卡")) {
				json = gson.toJson(xiakeQuekaList);
			}else if (state.equals("迟到") || state.equals("迟到时长")) {
				json = gson.toJson(chidaoList);
			}else if (state.equals("早退") || state.equals("早退时长")) {
				json = gson.toJson(zaotuiList);
			}else if (state.equals("旷课")) {
				json = gson.toJson(kuangkeList);
			}
			writer.print(json);		//把json通过输入流返回出去
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 /**
	 * 查询某个科目某段时间的考勤
	 */
	@RequestMapping(value="findCoursePeriodOfTimeAttendance")
	public String findCoursePeriodOfTimeAttendance(String startTime,String endTime,ModelMap map) {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		
		if (startTime==null && endTime==null) {
     		Date date = new Date();//当前日期
     		Calendar calendar = Calendar.getInstance();//日历对象
     		calendar.setTime(date);//设置当前日期
     		calendar.add(Calendar.MONTH, -1);//月份减一
     		startTime = DateUtil.format(calendar.getTime(), DateUtil.DATE_PATTERN);
     		endTime = DateUtil.format(date, DateUtil.DATE_PATTERN);
 		}
		List<SubjectInfo> selectAllSubject = subjectInfoService.selectAllSubject();
		List<Map<String, Object>> subjectCountList = new ArrayList<Map<String,Object>>();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("startTime", startTime);
		paramsMap.put("endTime", endTime);
		paramsMap.put("schoolId",shiroUser.schoolId);
		SchoolInfo school = schoolInfoService.findByIdSchool(shiroUser.schoolId);
		for (SubjectInfo subjectInfo : selectAllSubject) {
			Map<String, Object> mapCount = new HashMap<String, Object>();
			int tolCount = 0;//这个科目这段时间需要打卡的次数
			int zhengchang = 0;
			int chidao = 0;//迟到打卡次数
			int zaotui = 0;//早退打卡次数
			paramsMap.put("subjectName", subjectInfo.getSubjectName());
			List<CourseInfo> findCourseList = courseInfoService.findCourseList(paramsMap);
			for (CourseInfo courseInfo : findCourseList) {
				List<CourseInfo> arrayList = new ArrayList<CourseInfo>();
				arrayList.add(courseInfo);
				int normalClockingCount = getNormalClockingCount(paramsMap,arrayList);//这段时间需要打卡次数
				int size = stuInfoService.selectStuInfoByClassId(courseInfo.getClasses().getClassId()).size();//获取总人数
				int count = normalClockingCount*size;//这个课程总共需要打卡的次数
				tolCount = tolCount+count;
				paramsMap.put("courseId", courseInfo.getCourseId());
				List<ClockingInfo> classByIdAndTime = clockingService.findTimeAndCourseByIdClocking(paramsMap);
				for (ClockingInfo clockingInfo : classByIdAndTime) {
					String clockState = clockingInfo.getClockState();
					if (clockState.equals("迟到")) {
						chidao++;
					}else if (clockState.equals("早退")) {
						zaotui++;
					}else if (clockState.equals("正常")) {
						zhengchang++;
					}
				}
			}
			mapCount.put("subjectName", subjectInfo.getSubjectName());
			mapCount.put("qianDaoChiShu", zhengchang+chidao+zaotui);
			mapCount.put("startTime", startTime);
			mapCount.put("endTime", endTime);
			mapCount.put("chiDao", chidao);
			mapCount.put("zaoTui", zaotui);
			mapCount.put("kuangKe", (tolCount-zhengchang-chidao-zaotui)/2);
			subjectCountList.add(mapCount);
		}
		map.addAttribute("selectAllSubject", selectAllSubject);
		map.addAttribute("subjectCountList", subjectCountList);
		map.addAttribute("schoolName", school.getSchoolName());
		return "reception/courseAttendanceCollect";
	}
	/**
	 * 获取某天的某节课的信息考勤情况
	 * @param courseName
	 * @param startTime
	 * @param endTime
	 * @param map
	 * @return
	 */
	@RequestMapping(value="findCourseTimeAttendance")
	public String findCourseTimeAttendance(String departmentId,String classId,String courseId,String time,ModelMap map) {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		List<DepartmentInfo> departmentInfos = departmentInfoService.findListDepartmentInfo(shiroUser.schoolId);
		List<ClassInfo> classInfos = classInfoService.getAllClassByDepartmentId(departmentId);
		List<CourseInfo> courseInfos = courseInfoService.findCourseByClassIdAndTime(classId, time);
		List<Map<String, Object>> kuangkeList = new ArrayList<Map<String,Object>>();//旷课集合
		List<Map<String, Object>> queKaList = new ArrayList<Map<String,Object>>();//上课缺卡集合
	    List<Map<String, Object>> chidaoList = new ArrayList<Map<String,Object>>();//迟到
	    List<Map<String, Object>> zaotuiList = new ArrayList<Map<String,Object>>();//早退
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("courseId", courseId);
		paramsMap.put("time", time);
		CourseInfo course = courseInfoService.findCourseInfoById(courseId);
		//获取到某天某堂课的所有考勤信息
		List<String> clockingIdList = new ArrayList<String>();
		List<ClockingInfo> clockingInfos = clockingService.findTimeAndCourseByIdClocking(paramsMap);
		List<StuInfo> stuInfos = stuInfoService.selectStuInfoByClassId(course.getClasses().getClassId());
		//计算所有出所有需要考勤的id
		for (StuInfo stuInfo : stuInfos) {
			String date = "_"+time.replaceAll("-", "_")+"_";
			String startTime = course.getStartTime().replaceAll(":", "_")+"_";
			String endTime = course.getEndTime().replace(":", "_")+"_";
			String subjectId = course.getSubject().getSubjectId()+"_";
			String stuId = stuInfo.getStuId();
			String clockingId = date+startTime+endTime+subjectId+stuId;
			clockingIdList.add("1"+clockingId);//上课考勤id
			clockingIdList.add("0"+clockingId);//下课考勤id
		}
		
		//移除已经签到的id
		for (ClockingInfo clockingInfo :clockingInfos) {
			String clockingId = clockingInfo.getClockingId();
			if (clockingIdList.contains(clockingId)) {
				clockingIdList.remove(clockingId);
			}
			String clockState = clockingInfo.getClockState();
			//获取正常的结束考勤时间、
			String normalStartTime = clockingInfo.getCourse().getStartTime()+":00";
			String normalEndTime = clockingInfo.getCourse().getEndTime()+":00";
			Map<String, Object> stuMap = new HashMap<String, Object>();
			stuMap.put("stuId", clockingInfo.getStu().getStuId());
			stuMap.put("stuName", clockingInfo.getStu().getStuName());
			stuMap.put("clockingDate",DateUtil.format( clockingInfo.getClockingDate(), DateUtil.DATE_PATTERN));
			stuMap.put("week", clockingInfo.getCourse().getWeek());
        	String dateMinus = null;
			if (clockState.equals("迟到")) {
				String clockingDate = DateUtil.format(clockingInfo.getClockingDate(), DateUtil.TIME_PATTERN);
				dateMinus = DateUtil.getTimeDifference(normalStartTime, clockingDate);//获取到迟到的总分钟数
				stuMap.put("date", dateMinus);
				chidaoList.add(stuMap);
			}else if (clockState.equals("早退")) {
				String clockingDate = DateUtil.format(clockingInfo.getClockingDate(), DateUtil.TIME_PATTERN);
				dateMinus = DateUtil.getTimeDifference(clockingDate, normalEndTime);//获取到早退的总分钟数
				stuMap.put("date", dateMinus);
				zaotuiList.add(stuMap);
			}
			
		}
		Map<String, String> paramsMap1 = new HashMap<String, String>();
		paramsMap1.put("classId", course.getClasses().getClassId());
		paramsMap1.put("startTime", time);
		paramsMap1.put("endTime", time);
		//获取到这节课课的请假信息
		List<LeaveInfo> leaveInfos = leaveInfoService.findCourseByIdTimeLeave(paramsMap1);
		List<String> leaveClockingIdList = null;
		for (LeaveInfo leaveInfo : leaveInfos) {
			Map<String, String> leaveMap = new HashMap<String, String>();
			leaveMap.put("stuId", leaveInfo.getStu().getStuId());
			//获取到请假的时间的所有日期
			String startLeaveTime = leaveInfo.getStartLeaveTime();
			leaveMap.put("startTime", startLeaveTime);
			String endLeaveTime = leaveInfo.getEndLeaveTime();
			leaveMap.put("endTime", endLeaveTime);
			//获取请假需要签到的所有考勤id
			leaveClockingIdList= getClockingIdList(leaveMap,false);
			for (String string : leaveClockingIdList) {
				//移除所有的请假需要签到的考勤id
				if (clockingIdList.contains(string)) {
					clockingIdList.remove(string);
				}
			}
		}
		//计算旷课的节数
		List<String> clockingIdList1 = new ArrayList<String>();
		for (String clockingId : clockingIdList) {
			String substring = clockingId.substring(1);
			//将去除上下课的id放入到一个新的集合重
			clockingIdList1.add(substring);
		}
		//计算旷课和缺卡
		List<String> listClockingId = new ArrayList<String>();
		Set<String> uniqueSet = new HashSet<String>(clockingIdList1);
        for (String temp : uniqueSet) {
        	Map<String, Object> tempMap = new HashMap<String, Object>();
        	int lastIndexOf = temp.lastIndexOf("_")+1;
        	String stuId = temp.substring(lastIndexOf);
			StuInfo stuInfo = stuInfoService.findByIdStu(stuId);
			tempMap.put("stuId", stuId);
			tempMap.put("stuName", stuInfo.getStuName());
            int frequency = Collections.frequency(clockingIdList1, temp);
            if (frequency==2) {//旷课
            	kuangkeList.add(tempMap);
			}else{//缺卡
				listClockingId.add(temp);
			}
        }
        for (ClockingInfo clockingInfo : clockingInfos) {
        	Map<String, Object> tempMap = new HashMap<String, Object>();
        	String clockingId = clockingInfo.getClockingId();
        	String index = clockingId.substring(0, 1);
			String substring = clockingId.substring(1);
			boolean contains = listClockingId.contains(substring);
			tempMap.put("stuId", clockingInfo.getStu().getStuId());
			tempMap.put("stuName", clockingInfo.getStu().getStuName());
			if (contains) {
				if (index.equals("1")) {//上课
					tempMap.put("state", "下课缺卡");
				}else{
					tempMap.put("state", "上课缺卡");
				}
				queKaList.add(tempMap);
			}
		}
        map.addAttribute("kuangkeList", kuangkeList);
        map.addAttribute("queKa", queKaList);
        map.addAttribute("chidaoList", chidaoList);
        map.addAttribute("zaotuiList", zaotuiList);
        map.addAttribute("leaveInfos", leaveInfos);
        map.addAttribute("departmentInfos", departmentInfos);
        map.addAttribute("classInfos", classInfos);
        map.addAttribute("courseInfos", courseInfos);
        map.addAttribute("departmentId", departmentId);
        map.addAttribute("classId", classId);
        map.addAttribute("courseId", courseId);
        map.addAttribute("time", time);
		return "reception/classCourseAttendanceThedetail";
	}
	

	@RequestMapping(value="findCourseTeacherPeriodOfTimeAttendance")
	public String findCourseTeacherPeriodOfTimeAttendance(String courseName,String startTime,String endTime,ModelMap map) {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (startTime==null && endTime==null) {
     		Date date = new Date();//当前日期
     		Calendar calendar = Calendar.getInstance();//日历对象
     		calendar.setTime(date);//设置当前日期
     		calendar.add(Calendar.MONTH, -1);//月份减一
     		startTime = DateUtil.format(calendar.getTime(), DateUtil.DATE_PATTERN);
     		endTime = DateUtil.format(date, DateUtil.DATE_PATTERN);
 		}
		List<SubjectInfo> selectAllSubject = subjectInfoService.selectAllSubject();
		if (courseName==null) {
			courseName = selectAllSubject.get(0).getSubjectName();
			
		}
		SubjectInfo subject = subjectInfoService.findSubjectInfoByName(courseName);
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("startTime", startTime);
		paramsMap.put("endTime", endTime);
		paramsMap.put("subjectName", courseName);
		List<Map<String, Object>> sysUserCountList = new ArrayList<Map<String,Object>>();
		SchoolInfo school = schoolInfoService.findByIdSchool(shiroUser.schoolId);
		List<SysUserInfo> sysUserInfos = sysUserInfoService.findSubjectByNameAndSchoolId(shiroUser.schoolId, courseName);
		for (SysUserInfo sysUserInfo : sysUserInfos) {
			Map<String, Object> mapCount = new HashMap<String, Object>();
			int tolCount = 0;//这个科目这段时间需要打卡的次数
			int zhengchang = 0;
			int chidao = 0;//迟到打卡次数
			int zaotui = 0;//早退打卡次数
			paramsMap.put("sysUserId", sysUserInfo.getSysUserId());
			List<CourseInfo> findCourseList = courseInfoService.findCourseList(paramsMap);
			for (CourseInfo courseInfo : findCourseList) {
				List<CourseInfo> arrayList = new ArrayList<CourseInfo>();
				arrayList.add(courseInfo);
				int normalClockingCount = getNormalClockingCount(paramsMap,arrayList);//这段时间需要打卡次数
				int size = stuInfoService.selectStuInfoByClassId(courseInfo.getClasses().getClassId()).size();//获取总人数
				int count = normalClockingCount*size;//这个课程总共需要打卡的次数
				tolCount = tolCount+count;
				paramsMap.put("courseId", courseInfo.getCourseId());
				List<ClockingInfo> classByIdAndTime = clockingService.findTimeAndCourseByIdClocking(paramsMap);
				for (ClockingInfo clockingInfo : classByIdAndTime) {
					String clockState = clockingInfo.getClockState();
					if (clockState.equals("迟到")) {
						chidao++;
					}else if (clockState.equals("早退")) {
						zaotui++;
					}else if (clockState.equals("正常")) {
						zhengchang++;
					}
				}
			}
			mapCount.put("sysUserName", sysUserInfo.getSysUserName());
			mapCount.put("qianDaoChiShu", zhengchang+chidao+zaotui);
			mapCount.put("startTime", startTime);
			mapCount.put("endTime", endTime);
			mapCount.put("chiDao", chidao);
			mapCount.put("zaoTui", zaotui);
			mapCount.put("kuangKe", (tolCount-zhengchang-chidao-zaotui)/2);
			sysUserCountList.add(mapCount);
		}
		map.addAttribute("selectAllSubject", selectAllSubject);
		map.addAttribute("sysUserCountList", sysUserCountList);
		map.addAttribute("schoolName", school.getSchoolName());
		map.addAttribute("subject", subject);
		return "reception/courseAttendanceThedetail";
	}
    //声明一个三个零时属性用来放置参数
    private String dId;
    private String start;
    private String end;
    private String className1;
    
	public String getClassName1() {
		return className1;
	}
	public void setClassName1(String className1) {
		this.className1 = className1;
	}
	public String getdId() {
		return dId;
	}
	public void setdId(String dId) {
		this.dId = dId;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	
    
}

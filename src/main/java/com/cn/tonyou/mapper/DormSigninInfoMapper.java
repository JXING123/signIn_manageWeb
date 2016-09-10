package com.cn.tonyou.mapper;
import java.util.List;
import java.util.Map;

import com.cn.tonyou.pojo.DormSigninInfo;

/**
 * 宿舍签到操作
 * @author HY
 *
 */
public interface DormSigninInfoMapper {
	/**
	 * 获取宿舍考勤签到信息
	 * 
	 * @param dormSigninInfo
	 * @return
	 */
	public List<DormSigninInfo> getStuByIdDormSigninList(DormSigninInfo dormSigninInfo);
	/**
	 * 学生宿舍考勤
	 * @param dorm
	 * @return
	 */
	public int addDormsiginiAttend(DormSigninInfo dormSignin);
	/**
	 * 更新宿舍考勤
	 * @param dormSignin
	 * @return
	 */
	public int updateDormSignin(DormSigninInfo dormSignin);
	
	/**
	 * 根据班级id和时间(当前年月日、当前年月1)查询这个班所有学生寝室考勤状态为正常的总和
	 */
	public int selectDormSigninCount(String classId,String dayTime2,String dayTime);
	/**
	 * 根据各种条件获取宿舍考勤信息
	 * @param dormSigninInfo
	 * @param schoolId
	 * @return
	 */
	public List<DormSigninInfo> findDormSigninList(Map<String, String> paramsMap);
	/**
	 * 删除宿舍考勤
	 * @param dormSigninId
	 * @return
	 */
	public int deleteDormSignin(String dormSigninId);
}

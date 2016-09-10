package com.cn.tonyou.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cn.tonyou.mapper.DormSigninInfoMapper;
import com.cn.tonyou.pojo.DormSigninInfo;
import com.cn.tonyou.service.IDormSigninInfoService;

@Service
public class DormSigninInfoServiceImpl implements IDormSigninInfoService{
	@Autowired
	private DormSigninInfoMapper dormSigninInfoMapper;

	public List<DormSigninInfo> getStuByIdDormSigninList(DormSigninInfo dormSigninInfo) {
		return dormSigninInfoMapper.getStuByIdDormSigninList(dormSigninInfo);
	}
	
	
	/**
	 * 学生宿舍考勤
	 * @param dorm
	 * @return
	 */
	public int addDormsiginiAttend(DormSigninInfo dormSignin){
		return dormSigninInfoMapper.addDormsiginiAttend(dormSignin);
	}
	
	/**
	 * 更新宿舍考勤
	 * @param dormSignin
	 * @return
	 */
	public int updateDormSignin(DormSigninInfo dormSignin){
		return dormSigninInfoMapper.updateDormSignin(dormSignin);
	}


	/**
	 * 根据班级id和时间(当前年月日、当前年月1)查询这个班所有学生状态为正常的总和 
	 */
	public int selectDormSigninCount(String classId, String dayTime2, String dayTime) {
		return dormSigninInfoMapper.selectDormSigninCount(classId, dayTime2, dayTime);
	}

	/**
	 *获取某个学校的宿舍考勤信息
	 */
	public List<DormSigninInfo> findDormSigninList(Map<String, String> paramsMap) {
		return dormSigninInfoMapper.findDormSigninList(paramsMap);
	}


	public int deleteDormSignin(String dormSigninId) {
		return dormSigninInfoMapper.deleteDormSignin(dormSigninId);
	}

}

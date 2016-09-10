package com.cn.tonyou.mapper;

import java.util.List;
import java.util.Map;

import com.cn.tonyou.pojo.DormInfo;
/**
 * 宿舍管理
 * @author HY
 *
 */
public interface DormInfoMapper {
	/**
	 * 查询所有宿舍
	 */
	public List<DormInfo> findDormList(Map<String, String> paramsMap);
	/**
	 * 根据学生id查询宿舍考勤表
	 * @return
	 */
	public DormInfo getDormListForStuId(String stuId);

	
	/**
	 * 添加宿舍
	 */
	public void addDormInfo(DormInfo dormInfo);
	
	/**
	 * 删除宿舍
	 */
	public void deleteDormInfo(String dormId);
	
	/**
	 * 根据学校id和班级id查询宿舍
	 */
	public List<DormInfo> getDormInfo2(String schoolId,String classId);
	
	/**
	 * 根据id查询宿舍
	 */
	public DormInfo findDormInfoById(String dormId);
	
	/**
	 * 修改宿舍
	 */
	public void updateDormInfo(DormInfo dormInfo);
	/**
	 * 根据班级id和宿舍名称来查询宿舍信息
	 * @param classId
	 * @param dormName
	 * @return
	 */
	public DormInfo findDormByClassIdAndDormName(String classId,String dormName);

}

package com.cn.tonyou.service;

import java.util.List;
import java.util.Map;

import com.cn.tonyou.pojo.StuInfo;

/**
 * 学生管理
 * @author HY
 *
 */
public interface IStuInfoService {
	/**
	 * 查询所有学生信息
	 */
	public List<StuInfo> findListStu(Map<String, String> params);
	
	/**
	 * 添加上课学生信息
	 * @param clicking
	 */
	public void addStu(StuInfo stuInfo);
	
	/**
	 * 根据id获取某个学生信息
	 */
	public StuInfo findByIdStu(String stuId);
	
	/**
	 * 删除学生
	 */
	public void deleteStuinfo(String stuId);
	

	
	/**
	 * 修改学生信息
	 */
	public int updateStuInfo(StuInfo stuInfo);
	/**
	 * 客户端注册，添加学生
	 */
	public int userReg(StuInfo stuInfo);
	/**
	 * 客户端登录
	 * @param stuId
	 * @param stuPassword
	 * @return
	 */
	public StuInfo stuLogin(String stuId,String stuPassword);
	
	/**
	 * 根据班级id查询学生表
	 */
	public List<StuInfo> selectStuInfoByClassId(String classId);
	
	/**
	 * 根据宿舍id查询学生表
	 */
	public List<StuInfo> selectStuInfoByDormId(String dormId);
	/**
	 * 根据手机号查询
	 * @param stuTel
	 * @return
	 */
	public StuInfo selectStuInfoByTel(String stuTel);
}

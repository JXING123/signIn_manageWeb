package com.cn.tonyou.mapper;
import java.util.List;
import java.util.Map;

import com.cn.tonyou.pojo.StuInfo;
/**
 * 
 * @author CJW
 *上课学生
 */
public interface StuInfoMapper {
	/**
	 * 获取单个的学生信息
	 * @return
	 */
	public StuInfo findByIdStu(String stuId);
	/**
	 * 查询所有的上课学生信息
	 * @return
	 */
	public List<StuInfo> findListStu(Map<String, String> params);
	
	/**
	 * 添加上课学生信息
	 * @param clicking
	 */
	public void addStu(StuInfo stuInfo);
	/**
	 * 删除上课学生信息
	 * @param Stu
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

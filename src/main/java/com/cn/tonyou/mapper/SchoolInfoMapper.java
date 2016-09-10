package com.cn.tonyou.mapper;
import java.util.List;
import com.cn.tonyou.pojo.SchoolInfo;
/**
 * 学校管理
 * @author HY
 *
 */
public interface SchoolInfoMapper {
	/**
	 * 查询所有学校
	 */
	public List<SchoolInfo> selectSchoolList();
	
	/**
	 * 查询所有学校2
	 */
	public List<SchoolInfo> selectSchoolList2(String school);
	/**
	 * 根据学校名字查询学校
	 * @param schoolName
	 * @return
	 */
	public SchoolInfo selectSchoolByName(String schoolName);
	/**
	 * 删除学校信息
	 */
	public void deleteSchool(String schoolId);
	
	/**
	 * 添加学校信息
	 */
	public void addSchool(SchoolInfo schoolInfo);
	
	/**
	 * 根据id查询某个学校信息
	 */
	public SchoolInfo findByIdSchool(String schoolId);
	
	/**
	 * 修改学校信息
	 */
	public void updateSchoolInfo(SchoolInfo schoolInfo);
	
	/**
	 * 查询所有学校院系以及班级
	 * @return
	 */
	public List<SchoolInfo> selectAllSchools();
	
	/**
	 * 添加学校 不能添加名称相同的学校
	 */
	public List<SchoolInfo> checkAddSchoolinfo(String schoolName);
}

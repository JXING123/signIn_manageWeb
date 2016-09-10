package com.cn.tonyou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cn.tonyou.mapper.StuInfoMapper;
import com.cn.tonyou.pojo.StuInfo;
import com.cn.tonyou.service.IStuInfoService;
import com.cn.tonyou.utils.UUIDGenerator;

/**
 * 学生管理
 * @author HY
 *
 */
@Service
public class StuInfoServiceImpl implements IStuInfoService{
	@Autowired
	private StuInfoMapper stuInfoMapper;
	/**
	 * 查询所有学生信息
	 */
	public List<StuInfo> findListStu(Map<String, String> params) {
		return stuInfoMapper.findListStu(params);
	}
	
	/**
	 * 添加上课学生信息
	 * @param clicking
	 */
	public void addStu(StuInfo stuInfo) {
		stuInfo.setStuId(UUIDGenerator.getUUID());
		stuInfo.setStuState("未审核");
		stuInfoMapper.addStu(stuInfo);
	}
	/**
	 * 获取单个的学生信息
	 * @return
	 */
	public StuInfo findByIdStu(String stuId) {
		return stuInfoMapper.findByIdStu(stuId);
	}

	/**
	 * 删除学生
	 */
	public void deleteStuinfo(String stuId) {
		stuInfoMapper.deleteStuinfo(stuId);;
	}
	
	
	/**
	 * 修改学生信息
	 */
	public int updateStuInfo(StuInfo stuInfo) {
		String stuBirthday = stuInfo.getStuBirthday();
		if (stuBirthday!=null) {
			stuBirthday = stuBirthday.substring(0, 4)+"年"+stuBirthday.substring(5,7)+"月"+stuBirthday.substring(8, 10)+"日";
			stuInfo.setStuBirthday(stuBirthday);
		}
		return stuInfoMapper.updateStuInfo(stuInfo);
	}
	/**
	 * 客户端注册，添加学生
	 */
	public int userReg(StuInfo stuInfo) {
		return stuInfoMapper.userReg(stuInfo);
	
	}
	/**
	 * 客户端登录
	 * @param stuId
	 * @param stuPassword
	 * @return
	 */
	public StuInfo stuLogin(String stuId,String stuPassword){
		return stuInfoMapper.stuLogin(stuId, stuPassword);
	}

	/**
	 * 根据班级id查询学生表
	 */
	public List<StuInfo> selectStuInfoByClassId(String classId) {
		return stuInfoMapper.selectStuInfoByClassId(classId);
	}
	/**
	 * 根据宿舍id查询学生表
	 */
	public List<StuInfo> selectStuInfoByDormId(String dormId) {
		return stuInfoMapper.selectStuInfoByDormId(dormId);
	}
	/**
	 * 根据手机号查询
	 * @param stuTel
	 * @return
	 */
	public StuInfo selectStuInfoByTel(String stuTel){
		return stuInfoMapper.selectStuInfoByTel(stuTel);
	}

}

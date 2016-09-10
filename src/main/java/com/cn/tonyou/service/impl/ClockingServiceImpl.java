package com.cn.tonyou.service.impl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cn.tonyou.mapper.ClockingMapper;
import com.cn.tonyou.pojo.ClockingInfo;
import com.cn.tonyou.service.IClockingService;
import com.cn.tonyou.utils.DateUtil;
@Service
public class ClockingServiceImpl implements IClockingService{
	@Autowired
	private  ClockingMapper clockingMapper;
	public List<ClockingInfo> findListClocking() {
		return clockingMapper.findListClocking();
	}

	public int addClocking(ClockingInfo clocking) {
		return clockingMapper.addClocking(clocking);
	}

	public int updateClocking(ClockingInfo clocking) {
		return clockingMapper.updateClocking(clocking);
	}

	public void deleteClocking(ClockingInfo clocking) {
		clockingMapper.deleteClocking(clocking);
	}

	public ClockingInfo findByIdClocking(ClockingInfo clock) {
		return clockingMapper.findByIdClocking(clock);
	}

	public List<ClockingInfo> getStuByIdClocking(String stuId,String dateTime) {
		return clockingMapper.getStuByIdClocking(stuId,dateTime);
	}
	/**
	 * 根据学生id以及时间查询考勤
	 * @param stuId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<ClockingInfo> getStuByIdAndTime(String stuId,String startTime,String endTime){
		if (startTime.equals(endTime)) {
			endTime = DateUtil.addDay(endTime, 1);//日期加一天
		}
		return clockingMapper.getStuByIdAndTime(stuId, startTime, endTime);
	}

	/**
	 * 根据班级id和时间(当前年月日、当前年月1)查询这个班所有学生状态为正常的总和 
	 */
	public int selectClockingCount(String classId, String dayTime2, String dayTime) {
		return clockingMapper.selectClockingCount(classId, dayTime2, dayTime);
	}

	public List<ClockingInfo> findByParamsClocking(Map<String, String> paramsMap){
		return clockingMapper.findByParamsClocking(paramsMap);
	}

	public List<ClockingInfo> findTimeAndClassByIdClocking(Map<String, String> paramsMap) {
		return clockingMapper.findTimeAndClassByIdClocking(paramsMap);
	}

	public List<ClockingInfo> findTimeAndCourseByIdClocking(Map<String, String> paramsMap) {
		return clockingMapper.findTimeAndCourseByIdClocking(paramsMap);
	}

	public List<ClockingInfo> getClassByIdAndTime(String classId, String startTime, String endTime) {
		if (startTime.equals(endTime)) {
			endTime = DateUtil.addDay(endTime, 1);//日期加一天
		}
		return clockingMapper.getClassByIdAndTime(classId, startTime, endTime);
	}
	
	
	
}

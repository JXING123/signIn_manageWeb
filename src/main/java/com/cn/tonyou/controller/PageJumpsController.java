package com.cn.tonyou.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 页面跳转控制器
 * @author CJW
 *
 */
@Controller
public class PageJumpsController {
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("index")
	public String jumpIndex() {
		return "reception/index";
	}
	
	

	/**
	 * 跳转到在线作业页面
	 * @return
	 */
	@RequestMapping("workOnline")
	public String jumpWorkOnline() {
		return"reception/workOnline";
	}
	/**
	 * 跳转到作业统计
	 * @return
	 */
	@RequestMapping("workCount")
	public String jumpWorkCount() {
		return"reception/workCount";
	}
	/**
	 * 跳转到课程管理页面
	 * @return
	 */
	@RequestMapping("course")
	public String jumpCourse() {
		return "reception/courseManage";
	}
	/**
	 * 跳转到资料共享页面
	 * @return
	 */
	@RequestMapping("means")
	public String jumpMeans() {
		return "reception/means";
	}
	/**
	 * 跳转到教师互动页面
	 * @return
	 */
	@RequestMapping("interaction")
	public String jumpInteraction() {
		return "reception/interaction";
	}
	
	/**
	 * 资讯推送
	 * @return
	 */
	@RequestMapping("consult")
	public String jumpConsult() {
		return "reception/consult";
	}
	/**
	 * 跳转到帮助页面
	 * @return
	 */
	@RequestMapping("help")
	public String jumpHelp() {
		return "reception/help";
	}
	/**
	 * 跳转到帮助页面
	 * @return
	 */
	@RequestMapping("consume")
	public String jumpConsume() {
		return "reception/consume";
	}
	/**
	 * 跳转到帮助页面
	 * @return
	 */
	@RequestMapping("insertHomework")
	public String insertHomework() {
		return "reception/insertHomework";
	}
	/**
	 * 跳转到帮助页面
	 * @return
	 */
	@RequestMapping("insertCourse")
	public String insertCourse() {
		return "reception/insertCourse";
	}
	
	/**
	 * 跳转到帮助页面
	 * @return
	 */
	@RequestMapping("insertCourse1")
	public String insertCourse1() {
		return "reception/insertCourse";
	}
}

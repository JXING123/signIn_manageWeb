package com.cn.tonyou.aop;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

/**
 * 日志
 * @author Administrator
 *
 */
public class LogAspectj {
	private Logger logger = Logger.getLogger(LogAspectj.class);
	/**
	 * 在方法执行之前	 前置通知
	 * @param joinpoint  JoinPoint连接点，就是通知和方法相交叉的地方
	 */
	public void myBeforeAdvice(JoinPoint joinPoint){
		System.out.println("进入了前置通知---");
		logger.info("执行类的名称" +  joinPoint.getTarget().getClass().getName());
		logger.info("执行的方法：" + joinPoint.getSignature().getName());
		System.out.println("退出了前置通知---");
	}
	
	/**
	 * 返回（return）之前  后置返回通知
	 * @param joinPoint
	 */
	public void myAfterRetuingAdvice(JoinPoint joinPoint){
		System.out.println("进入后置返回通知---在执行目标方法return之前");
		logger.info("执行类的名称："+joinPoint.getTarget().getClass().getName());
		logger.info("执行类中的--"+joinPoint.getSignature().getName()+"--方法");
		System.out.println("在方法返回之前--退出后置返回通知");
	}
	
	
	
	
}
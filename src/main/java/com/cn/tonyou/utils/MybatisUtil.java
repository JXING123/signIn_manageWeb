package com.cn.tonyou.utils;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	private MybatisUtil(){
		try {
			InputStream inputStream = Resources.getResourceAsStream("configuration.xml");
			
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private SqlSessionFactory sqlSessionFactory = null;
	private static MybatisUtil instance = null;
	
	public static MybatisUtil getIntance(){
		if(null == instance){
			instance = new MybatisUtil();
		}
		return instance;
	}
	
	public SqlSession getSession(){
		return sqlSessionFactory.openSession();
	};
	
	public void closeSession(SqlSession session){
		if(null != session){
			session.close();
		}
	}
	
}
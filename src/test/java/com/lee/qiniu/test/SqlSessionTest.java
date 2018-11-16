package com.lee.qiniu.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lee.qiniu.dao.ArticleDao;

public class SqlSessionTest {
	
	private SqlSession session;
	
	@Before
	public void setUp() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		SqlSessionFactory sessionFactory = (SqlSessionFactory)ac.getBean("sqlSessionFactory");
		session = sessionFactory.openSession();
	}
	
	@After
	public void cleanUp() {
		session.commit();
		session.close();
	}
	
	@Test
	public void demo(){
		System.out.println(session);
		ArticleDao dao = session.getMapper(ArticleDao.class);
		System.out.println(dao);
		
	}
}

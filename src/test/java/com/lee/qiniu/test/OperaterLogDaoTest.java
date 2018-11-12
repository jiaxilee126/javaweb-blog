package com.lee.qiniu.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-*.xml"})
public class OperaterLogDaoTest {
	
	
	/*@Test
	public void demo(){
		InputStream is = null;
		
		try{
			is = OperaterLogDaoTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			System.out.println(factory);
			SqlSession session = factory.openSession();
			ArticleDao dao = session.getMapper(ArticleDao.class);
			Article article = new Article();
			article.setTitle("ces");
			article.setImage("png");
			article.setContent("aaaaaaaaaaaaaaaaaaa");
			article.setCreateTime(new Date());
			dao.insert(article);
			session.commit();
			TestDemoDao demoDao = session.getMapper(TestDemoDao.class);
			//System.out.println(demoDao);
			//demoDao.getById(1);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
}

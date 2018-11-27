package com.lee.qiniu.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lee.qiniu.dao.ArticleDao;
import com.lee.qiniu.entity.Article;
import com.lee.qiniu.service.IArticleService;
import com.lee.qiniu.service.impl.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({ "classpath*:spring-aop-transaction.xml", "classpath*:spring-daotest.xml"})
public class TestArticelDao {
	
	
	
	//@Autowired ArticleController articleController;
	
	@Autowired
	IArticleService articleService;
	
	/*@Test
	public void save(){
		System.out.println(articleDao);
		Article article = new Article();
		article.setCreateId(1);
		article.setTitle("ces111111111111");
		article.setImage("png");
		article.setContent("aaaaaaaaaaaaaaaaaaa");
		article.setCreateTime(new Date());
		articleDao.insert(article);
	}*/
	
	/*@Test
	public void test(){
		System.out.println(articleService);
		Article article = new Article();
		article.setCreateId(1);
		article.setTitle("ces111111111111");
		article.setImage("png666");
		article.setContent("aaaaaaaaaaaaaaaaaaa");
		article.setCreateTime(new Date());
		articleService.save(article);
	}*/
	
	@Test
	public void test(){
		//ArticleService articleService = new ArticleService();
		ArticleDao dao = articleService.getArticleDao();
		System.out.println(dao);
	}
}

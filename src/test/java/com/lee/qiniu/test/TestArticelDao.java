package com.lee.qiniu.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lee.qiniu.controller.ArticleController;
import com.lee.qiniu.dao.ArticleDao;
import com.lee.qiniu.entity.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-*.xml"})
public class TestArticelDao {
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired ArticleController articleController;
	
	@Test
	public void save(){
		System.out.println(articleDao);
		Article article = new Article();
		article.setCreateId(1);
		article.setTitle("ces");
		article.setImage("png");
		article.setContent("aaaaaaaaaaaaaaaaaaa");
		article.setCreateTime(new Date());
		articleDao.insert(article);
	}
	
	@Test
	public void test(){
		System.out.println(articleController);
	}
}

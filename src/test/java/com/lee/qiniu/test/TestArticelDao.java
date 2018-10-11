package com.lee.qiniu.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lee.qiniu.dao.ArticleDao;
import com.lee.qiniu.entity.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-*.xml"})
public class TestArticelDao {
	
	@Autowired
	private ArticleDao articleDao;
	
	@Test
	public void save(){
		System.out.println(articleDao);
		Article article = new Article();
		article.setTitle("ces");
		article.setImage("png");
		article.setContent("aaaaaaaaaaaaaaaaaaa");
		article.setCreateTime(new Date());
		articleDao.insert(article);
	}
}

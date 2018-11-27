package com.lee.qiniu.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lee.qiniu.controller.ArticleController;
import com.lee.qiniu.entity.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-*.xml"})
public class ExceptionHandlerTest {
	
	@Autowired
	private ArticleController articleController;
	
	@Test
	public void demo() {
		Article article = new Article();
		article.setContent(null);
		article.setTitle("test");
		articleController.save(article);
	}
}

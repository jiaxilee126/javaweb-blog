package com.lee.qiniu.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.qiniu.dao.ArticleDao;
import com.lee.qiniu.entity.Article;
import com.lee.qiniu.json.JsonResult;
import com.lee.qiniu.service.IArticleService;

@Service
public class ArticleService implements IArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public JsonResult save(Article article) {
		System.out.println(article.getContent()+"=========");
		article.setCreateTime(new Date());
		articleDao.insert(article);
		return JsonResult.ok();
	}

}

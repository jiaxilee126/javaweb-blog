package com.lee.qiniu.service;

import com.lee.qiniu.dao.ArticleDao;
import com.lee.qiniu.entity.Article;
import com.lee.qiniu.json.JsonResult;

public interface IArticleService {
	
	
	JsonResult save(Article article);
	
	ArticleDao getArticleDao();
}

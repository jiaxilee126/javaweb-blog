package com.lee.qiniu.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lee.qiniu.dao.ArticleDao;
import com.lee.qiniu.entity.Article;
import com.lee.qiniu.exception.RollBackException;
import com.lee.qiniu.json.JsonResult;
import com.lee.qiniu.service.IArticleService;

@Service
@Scope("prototype")
public class ArticleService implements IArticleService {
	
	@Autowired
	private ArticleDao articleDao;
	
	public ArticleDao getArticleDao(){
		return articleDao;
	} 
	
	@Override
	@Transactional(rollbackForClassName={"RollBackException"})
	public JsonResult save(Article article){
		try {
			article.setCreateTime(new Date());
			articleDao.insert(article);
			//System.out.println(1/0);
		} catch (RollBackException e) {
			
			throw new RollBackException(e.getMessage());
			//return null;
		}
		return JsonResult.ok();
	}

}

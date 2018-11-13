package com.lee.qiniu.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lee.qiniu.dao.ArticleDao;
import com.lee.qiniu.entity.Article;
import com.lee.qiniu.exception.RollBackException;
import com.lee.qiniu.json.JsonResult;
import com.lee.qiniu.service.IArticleService;

@Service
public class ArticleService implements IArticleService {
	
	@Autowired
	private ArticleDao articleDao;
	
	@Override
	@Transactional(rollbackForClassName={"RollBackException"})
	public JsonResult save(Article article){
		try {
			article.setCreateTime(new Date());
			articleDao.insert(article);
			//System.out.println(1/0);
		} catch (Exception e) {
			throw new RollBackException(e.getMessage());
		}
		return JsonResult.ok();
	}

}

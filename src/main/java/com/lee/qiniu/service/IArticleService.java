package com.lee.qiniu.service;

import org.springframework.transaction.annotation.Transactional;

import com.lee.qiniu.entity.Article;
import com.lee.qiniu.json.JsonResult;

public interface IArticleService {
	
	@Transactional(rollbackForClassName={"RollBackException"})
	JsonResult save(Article article);
}

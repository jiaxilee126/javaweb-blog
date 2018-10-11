package com.lee.qiniu.dao;

import org.springframework.stereotype.Repository;

import com.lee.qiniu.entity.Article;

@Repository
public interface ArticleDao {
	
	void insert(Article article);
	
}

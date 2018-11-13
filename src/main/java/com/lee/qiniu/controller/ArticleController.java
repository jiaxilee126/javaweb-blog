package com.lee.qiniu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lee.qiniu.aspect.annotation.EMethodType;
import com.lee.qiniu.aspect.annotation.SysLog;
import com.lee.qiniu.entity.Article;
import com.lee.qiniu.json.JsonResult;
import com.lee.qiniu.service.impl.ArticleService;

@RequestMapping("/article")
@Controller
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/publish")
	public String article(){
		return "/article";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	@SysLog(module="文章管理",type = EMethodType.INSERT)
	public JsonResult save(Article article){
		return articleService.save(article);
	}
	
	@RequestMapping("/edit")
	public JsonResult edit(Article article){
		return null;
	}
	
	public JsonResult delete(Integer id){
		return null;
	}
}

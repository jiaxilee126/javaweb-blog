package com.lee.qiniu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lee.qiniu.json.JsonResult;
import com.lee.qiniu.service.IQiniuService;

@RequestMapping("/qiniu")
@Controller
public class QiniuController {
	
	@Autowired
	private IQiniuService qiniuService;
	
	@RequestMapping("/upload")
	@ResponseBody
	public JsonResult upload(@RequestParam("file") MultipartFile file){
		  return qiniuService.uploadImg(file);  
	}
	
}

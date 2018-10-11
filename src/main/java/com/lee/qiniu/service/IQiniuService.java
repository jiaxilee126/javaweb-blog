package com.lee.qiniu.service;

import org.springframework.web.multipart.MultipartFile;

import com.lee.qiniu.json.JsonResult;

public interface IQiniuService {
	
	JsonResult uploadImg(MultipartFile file);
}

package com.lee.qiniu.serviceImpl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lee.qiniu.file.FileTypeHelper;
import com.lee.qiniu.file.QiniuImg;
import com.lee.qiniu.file.QiniuStorage;
import com.lee.qiniu.json.JsonResult;
import com.lee.qiniu.json.ResultCodeEnum;
import com.lee.qiniu.service.IQiniuService;

@Service
public class QiniuService implements IQiniuService {
	
	private static final Logger logger = Logger.getLogger(QiniuService.class);
	
	@Override
	public JsonResult uploadImg(MultipartFile file) {
		//获取源文件
	    String filename = file.getOriginalFilename();
	    String[] names=filename.split("\\.");//
	    String type = names[names.length-1];
	    boolean isImg = FileTypeHelper.isImg(type);
	    String url = null;
	    	
	    try {
			byte[] fileBytes = file.getBytes();
			if(isImg) {
		    	String key = QiniuStorage.uploadImage(fileBytes);
		    	QiniuImg img = new QiniuImg();
		    	img.setKey(key);
		    	url = img.getUrl512();
		    }else {
				logger.info("please input right support image");
				return JsonResult.code(ResultCodeEnum.UPLOAD_FILE_INVALID);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return JsonResult.data(url);
	}

}

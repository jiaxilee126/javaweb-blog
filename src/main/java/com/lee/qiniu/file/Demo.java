package com.lee.qiniu.file;

import com.lee.qiniu.utils.CommonUtil;

public class Demo {
	public static void main(String[] args) {
		  //测试上传图片
        byte[] buff = CommonUtil.getBytesByFile("D:/Documents/桌面/bbb.jpg");
        String key = QiniuStorage.uploadImage(buff);
        System.out.println("key = " + key);

        //测试下载图片
        String url = QiniuStorage.getUrl(key);
        System.out.println("url = " + url);
        QiniuImg img = new QiniuImg();
        img.setKey(key);
        System.out.println(img.getUrl128());
        //测试下载不同大小的图片

	}
}


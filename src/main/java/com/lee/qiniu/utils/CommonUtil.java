package com.lee.qiniu.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class CommonUtil {
	
	/**
	 * @Description: 文件转换为字节数组
	 * @param filePath
	 * @return byte[]  
	 * @throws
	 * @author lijiaxi
	 * @date 2018年10月10日
	 */
	public static byte[] getBytesByFile(String filePath){
		File file = new File(filePath);
		try {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while((n = fis.read(b)) != -1){
				bos.write(b, 0, n);
			}
			fis.close();
			byte[] data = bos.toByteArray();
			bos.close();
			return data;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

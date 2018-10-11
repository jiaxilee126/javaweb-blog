package com.lee.qiniu.file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.lee.qiniu.utils.CommonUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * 七牛云的包装类
 */
public class QiniuWrapper {
	private static final Logger logger = Logger.getLogger(QiniuWrapper.class);

    private static final String CONFIG_BUCKET="qiniu.bucket";

    private static final String CONFIG_AK="qiniu.accesskey";
    private static final String CONFIG_SK="qiniu.secretkey";
    private static final String CONFIG_CDN="qiniu.cdns";

    private static final Auth auth;
    private static final UploadManager uploadManager;

    private static final String bucketName;
    private static final List<String> cdns;

    /**
     * 从外部文件中初始化七牛存储相关的配置信息
     */
    static{
        Properties properties =PropertiesUtil.getDefaultProperties();
        auth = Auth.create(properties.getProperty(CONFIG_AK), properties.getProperty(CONFIG_SK));
        Configuration cfg = new Configuration(Zone.zone2());//设置空间上传域名
        uploadManager = new UploadManager(cfg);
        bucketName=properties.getProperty(CONFIG_BUCKET);
        String cdn = properties.getProperty(CONFIG_CDN);
        cdns = Arrays.asList(cdn.split(","));
    }

    /**
     * 获取上传资源的token
     * @return
     */
    public static String getUploadToken(){
        return auth.uploadToken(bucketName);
    }

    /**
     * 获取更新资源的token，只能用于更新参数key所代表的资源
     * @param key 存储空间中已存在的资源key
     * @return
     */
    public static String getUploadToken(String key){
        return auth.uploadToken(bucketName,key);
    }

    /**
     * 上传文件
     * @param data 二进制格式的文件内容
     * @param key 文件在七牛中的key
     * @param update 是否是更新
     * @return
     */
    public static String upload(byte[] data,String key,boolean update){
        try {
            String token = update?auth.uploadToken(bucketName,key):auth.uploadToken(bucketName);
            Response response = uploadManager.put(data, getFullKey(data, key), token);
            DefaultPutRet ret = response.jsonToObject(DefaultPutRet.class);
            return ret.key;
        } catch (QiniuException e) {
        	e.printStackTrace();
        	logger.error("upload file to qiniu cloud storage failed",e);
        }
        return null;
    }
    
    /**
     * @Description: 上传文件
     * @param filePath
     * @param key
     * @param update
     * @return String  
     * @throws
     * @author lijiaxi
     * @date 2018年9月29日
     */
    public static String upload(String filePath, String key,boolean update){
    	byte[] data = CommonUtil.getBytesByFile(filePath);
    	return upload(data, key, update);
    }

    private static String getFullKey(byte[] data,String key){
        FileType type= FileTypeHelper.getType(data);
        if(type!=null){
            return key+"."+type.name().toLowerCase();
        }else{
            return key;
        }
    }

    public static String getUrl(String key,String model){
        return getUrl(key, model, 3600);
    }

    /**
     * 获取多个key图片；
     * @param keys 逗号隔开的多个key;
     * @param model
     * @return
     */
    public static List<String> getUrls(String keys,String model){
        List<String> list = null;
        if (org.apache.commons.lang3.StringUtils.isNotBlank(keys)) {
            list = new ArrayList<String>();
            for (String key : keys.split(",")) {
                list.add(getUrl(key, model, 3600));
            }
        }
        return list;
    }

    public static String getUrl(String key){
        if(!StringUtils.isEmpty(key)){
            return auth.privateDownloadUrl("http://"+getCDN()+"/@"+key);
        }
        return null;
    }

    /**
     * @param key
     * @param expires 单位：秒
     * @return
     */
    public static String getUrl(String key,long expires){
        if(!StringUtils.isEmpty(key)){
            long time = System.currentTimeMillis()/1000+expires;
            return auth.privateDownloadUrl("http://"+getCDN()+"/@"+key,time);
        }
        return null;
    }
    
    
    public static String getUrl(String key,String model, long expires){
    	if(!StringUtils.isEmpty(key)){
    		long time = System.currentTimeMillis()/1000 + expires;
    		return auth.privateDownloadUrl("http://"+getCDN()+"/@"+key+"?"+model,time);
    	}
    	
    	return null;
    }
    
    /**
     * @Description: 获取访问域名
     * @return String  
     * @throws
     * @author lijiaxi
     * @date 2018年10月10日
     */
    public static String getCDN(){
    	StringBuilder sb = new StringBuilder();
    	String pre = "";
    	for(String item : cdns){
    		sb.append(pre);
    		sb.append(item);
    		pre = ".";
    	}
    	return sb.toString();
    }
    
    
    public static void main(String[] args) {
		System.out.println(getUploadToken());
		String key = upload("D:/Documents/桌面/bbb.jpg", QiniuKeyGenerator.generateKey(), false);
		
		System.out.println("key = " + key);

        //String key = "/default/all/0/0755ffd19e3e416db0a69ca3b23d744a.jpeg";
        //测试下载图片
        String url = QiniuStorage.getUrl(key);
        System.out.println("url = " + url);
        
        url = QiniuStorage.getUrl(key,ThumbModel.THUMB_256);
        System.out.println("url = " + url);
	}
}

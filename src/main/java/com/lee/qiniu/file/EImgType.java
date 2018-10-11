package com.lee.qiniu.file;

/**
 * @Description: 图片枚举类
 * @author Jussi Lee
 * @date 2018年10月10日
 */
public enum EImgType {
	JPG(1,"JPG"),
	JPEG(2,"JPEG"),
	PNG(3,"PNG");
	
	private int value;
	private String name;
	
	public int getValue(){
		return this.value;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static EImgType valueOf(int value){
		for(EImgType item : EImgType.values()){
			if(item.value == value)
				return item;
		}
		return null;
	}
	
	public static EImgType nameOf(String name){
		if(name == null || ("").equals(name))
			return null;
		name = name.trim();
		for(EImgType item : EImgType.values()){
			if(item.getName().equalsIgnoreCase(name))
				return item;
		}
		return null;
	}
	private EImgType(int value,String name){
		this.value = value;
		this.name = name;
	}
}

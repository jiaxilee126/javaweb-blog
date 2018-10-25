package com.lee.qiniu.aspect.annotation;
/**
 * @Description: 切片方法枚举类
 * @author Jussi Lee
 * @date 2018年10月25日
 */
public enum EMethodType {
	INSERT(1,"增加"),
	DELETE(2,"删除"),
	UPDATE(3,"更新");
	
	private Integer value;
	
	private String name;
	
	public int getValue(){
        return value;
    }

    public String getName(){
        return name;
    }
    public static EMethodType valueOf(int value) {
        for(EMethodType item : EMethodType.values()){
            if(item.value == value){
                return item;
            }
        }
        return null;
    }
    public static EMethodType nameOf(String name){
        if(name == null || ("").equals(name))
				return null;
		name = name.trim();
		for(EMethodType type : EMethodType.values()){
			if(type.getName().equalsIgnoreCase(name))
				return type;
		}
		return null;
    }
	
	private EMethodType(Integer value, String name){
		this.value = value;
		this.name = name;
	}
}

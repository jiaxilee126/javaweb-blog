package com.lee.qiniu.enums;

public enum EDataType {
	LONG(1,"整型");
	
	private Integer value;
	private String name;
	
	public int getValue(){
        return value;
    }

    public String getName(){
        return name;
    }
    public static EDataType valueOf(int value) {
        for(EDataType item : EDataType.values()){
            if(item.value == value){
                return item;
            }
        }
        return null;
    }
    public static EDataType nameOf(String name){
        if(name == null || ("").equals(name))
				return null;
		name = name.trim();
		for(EDataType type : EDataType.values()){
			if(type.getName().equalsIgnoreCase(name))
				return type;
		}
		return null;
    }
	
	private EDataType(Integer value, String name){
		this.value = value;
		this.name = name;
	}
}

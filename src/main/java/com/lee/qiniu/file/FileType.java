package com.lee.qiniu.file;

/**
 * @Description: 文件类型及魔数
 * @author lijiaxi
 * @date 2018年9月29日
 */
public enum FileType {
	
	JPEG("FFD8FF"),
    PNG("89504E47"),

    GIF("47494638");

    private String value = "";

    private FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}

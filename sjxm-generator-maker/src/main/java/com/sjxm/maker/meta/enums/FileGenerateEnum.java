package com.sjxm.maker.meta.enums;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/18
 * @Description:
 */
public enum FileGenerateEnum {

    DYNAMIC("动态","dynamic"),
    STATIC("静态","static");

    private final String text;

    private final String value;

    FileGenerateEnum(String text, String value){
        this.text = text;
        this.value = value;
    }

    public String getText(){
        return text;
    }

    public String getValue(){
        return value;
    }
}

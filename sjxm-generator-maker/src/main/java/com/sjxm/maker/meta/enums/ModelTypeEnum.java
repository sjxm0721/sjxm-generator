package com.sjxm.maker.meta.enums;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/18
 * @Description:
 */
public enum ModelTypeEnum {

    STRING("字符串","String"),
    BOOLEAN("布尔","boolean");

    private final String text;

    private final String value;

    ModelTypeEnum(String text, String value){
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

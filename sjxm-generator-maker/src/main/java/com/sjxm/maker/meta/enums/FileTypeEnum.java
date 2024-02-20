package com.sjxm.maker.meta.enums;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/18
 * @Description:
 */
public enum FileTypeEnum {

    DIR("目录","dir"),
    FILE("文件","file"),
    GROUP("文件组","group");

    private final String text;

    private final String value;

    FileTypeEnum(String text,String value){
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

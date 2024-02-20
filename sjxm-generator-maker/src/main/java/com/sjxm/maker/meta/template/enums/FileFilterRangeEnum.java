package com.sjxm.maker.meta.template.enums;

import cn.hutool.core.util.ObjectUtil;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/18
 * @Description: 文件过滤范围枚举
 */
public enum FileFilterRangeEnum {

    FILE_NAME("文件名称","file_name"),
    FILE_CONTENT("文件内容","file_content");

    private final String text;

    private final String value;

    FileFilterRangeEnum(String text, String value){
        this.text = text;
        this.value = value;
    }

    public String getText(){
        return text;
    }

    public String getValue(){
        return value;
    }

    /**
     * 获取枚举
     * @param value
     * @return
     */
    public static FileFilterRangeEnum getEnumByValue(String value){
        if(ObjectUtil.isEmpty(value)){
            return null;
        }
        for(FileFilterRangeEnum anEnum: FileFilterRangeEnum.values()){
            if(anEnum.value.equals(value)){
                return anEnum;
            }
        }
        return null;
    }
}

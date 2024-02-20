package com.sjxm.maker.meta.template.enums;

import cn.hutool.core.util.ObjectUtil;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/18
 * @Description: 文件过滤规则枚举
 */
public enum FileFilterRuleEnum {

    CONTAINS("包含","contains"),
    STARTS_WITH("前缀匹配","starts_with"),
    REGEX("正则","regex"),
    EQUALS("相等","equals"),
    ENDS_WITH("后缀匹配","ends_with");

    private final String text;

    private final String value;

    FileFilterRuleEnum(String text, String value){
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
    public static FileFilterRuleEnum getEnumByValue(String value){
        if(ObjectUtil.isEmpty(value)){
            return null;
        }
        for(FileFilterRuleEnum anEnum:FileFilterRuleEnum.values()){
            if(anEnum.value.equals(value)){
                return anEnum;
            }
        }
        return null;
    }
}

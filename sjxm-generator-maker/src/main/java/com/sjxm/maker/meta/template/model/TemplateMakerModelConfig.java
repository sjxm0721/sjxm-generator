package com.sjxm.maker.meta.template.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/25
 * @Description:
 */
@Data
public class TemplateMakerModelConfig {

    private List<ModelInfoConfig> models;

    private ModelGroupConfig modelGroupConfig;

    @NoArgsConstructor
    @Data
    public static class ModelInfoConfig{
        private String fieldName;

        private String type;

        private String description;

        private Object defaultValue;

        private String abbr;

        //用于替换哪些配置
        private String replaceText;
    }

    @NoArgsConstructor
    @Data
    public static class ModelGroupConfig{
        private String condition;
        private String groupKey;
        private String groupName;

        private String type;
        private String description;
    }
}

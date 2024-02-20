package com.sjxm.maker.meta.template.model;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/25
 * @Description: 文件过滤配置
 */
@Builder
@Data
public class FileFilterConfig {
    /**
     * 过滤范围
     */
    private String range;

    /**
     * 过滤规则
     */
    private String rule;

    /**
     * 过滤值
     */
    private String value;
}

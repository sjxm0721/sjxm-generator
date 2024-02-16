package com.sjxm.maker.model;

import lombok.Data;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/13
 * @Description:静态模板配置
 */

@Data
public class DataModel {

    /**
     * 作者
     */
    private String author = "四季夏目";

    /**
     * 输出文本
     */
    private String outputText = "输出结果：";

    /**
     * 是否循环
     */
    private boolean loop;

}

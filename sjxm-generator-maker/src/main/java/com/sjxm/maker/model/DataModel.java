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
     * 是否生成.gitignore文件
     */
    public boolean needGit = true;

    /**
     * 是否循环
     */
    private boolean loop;

    /**
     * 核心模板
     */
    private MainTemplate mainTemplate;

}
